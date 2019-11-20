package sort;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class QuickM3 extends ASort {
    // cutoff to insertion sort, must be >= 1
    private static final int INSERTION_SORT_CUTOFF = 1;

    public static void main(String[] args) {
        //String[] comparables = StdIn.readAllStrings();
        Comparable[] comparables = new Comparable[]{1, 4, 5, 2, 3, 7, 8, 6, 10, 9};
        new QuickM3().start(comparables);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param list the array to be sorted
     */
    public void sort(Comparable[] list) {
        StdRandom.shuffle(list);
        this.sort(list, 0, list.length - 1);
    }

    // quicksort the subarray from list[lo] to list[hi]
    protected void sort(Comparable[] list, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        System.out.print("Part: ");
        for (int i = lo; i <= hi; ++i) {
            System.out.print(list[i] + ",");
        }
        System.out.println();

        // cutoff to insertion sort (Insertion.sort() uses half-open intervals)
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_CUTOFF) {
            Insertion.sort(list, lo, hi + 1);
            return;
        }

        int j = this.partition(list, lo, hi);
        this.sort(list, lo, j - 1);
        this.sort(list, j + 1, hi);
    }

    // partition the subarray list[lo..hi] so that list[lo..j-1] <= list[j] <= list[j+1..hi]
    // and return the index j.
    protected int partition(Comparable[] list, int lo, int hi) {
        int n = hi - lo + 1;
        int median = median(list, lo, lo + n / 2, hi);
        this.exch(list, median, lo);

        int i = lo;
        int j = hi + 1;
        Comparable v = list[lo];

        // list[lo] is unique largest element
        while (less(list[++i], v)) {
            if (i == hi) {
                this.exch(list, lo, hi);
                return hi;
            }
        }

        // list[lo] is unique smallest element
        while (less(v, list[--j])) {
            if (j == lo + 1) {
                return lo;
            }
        }

        // the main loop
        while (i < j) {
            this.exch(list, i, j);
            while (less(list[++i], v)) ;
            while (less(v, list[--j])) ;
        }

        // put partitioning item v at list[j]
        this.exch(list, lo, j);

        // now, list[lo .. j-1] <= list[j] <= list[j+1 .. hi]
        return j;
    }

    // return the index of the median element among list[i], list[j], and list[k]
    protected int median(Comparable[] list, int i, int j, int k) {
        return less(list[i], list[j]) ?
                (
                        less(list[j], list[k]) ?
                                j :
                                less(list[i], list[k]) ? k : i
                ) :
                (
                        less(list[k], list[j]) ?
                                j :
                                less(list[k], list[i]) ? k : i
                );
    }
}
