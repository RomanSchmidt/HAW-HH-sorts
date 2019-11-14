package sort;

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;
import java.util.PriorityQueue;

// Data structure for Min Heap
class KSort extends ASort {
    private static final int k = 2;

    public static void main(String[] args) {
        Comparable[] comparables = new Comparable[]{1, 4, 5, 2, 3, 7, 8, 6, 10, 9};
        //String[] comparables = StdIn.readAllStrings();
        new KSort().start(comparables);
    }

    // Function to sort a K-Sorted Array
    public void sort(Comparable[] list) {
        // create an empty min heap and insert first k+1 elements in the heap
        PriorityQueue<Comparable> pq = new PriorityQueue<>(Arrays.asList(list).subList(0, KSort.k + 1));

        int index = 0;

        // do for remaining elements of the array
        for (int i = KSort.k + 1; i < list.length; i++) {
            // pop top element from min-heap and assign it to
            // next available array index
            list[index++] = pq.poll();

            // push next array element into min-heap
            pq.add(list[i]);
        }

        // pop all remaining elements from the min heap and assign it to
        // next available array index
        while (!pq.isEmpty()) {
            list[index++] = pq.poll();
        }
    }
}