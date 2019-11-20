package sort;

import java.util.Arrays;
import java.util.HashMap;

public class QuickM5 extends QuickM3 {

    public static void main(String[] args) {
        //String[] comparables = StdIn.readAllStrings();
        //Comparable[] comparables = new Comparable[]{2, 3, 5, 4, 1, 12, 11, 13, 16, 7, 8, 6, 10, 9, 17, 15, 19, 20, 18, 23, 21, 22, 25, 24, 14};
        Comparable[] comparables = new Comparable[]{5, 1, 1, 3, 7};

        System.out.printf("%s m5:%d", Arrays.toString(comparables), new QuickM5().median(comparables, 0, 2, 4));
        //new QuickM5().start(comparables);
    }

    @Override
    public int median(Comparable[] list, int lo, int middle, int hi) {
        int leftMiddle = (lo + middle) / 2;
        int rightMiddle = (hi + middle) / 2;

        Comparable[] elements = new Comparable[]{list[lo], list[middle], list[hi], list[leftMiddle], list[rightMiddle]};
        HashMap<Comparable, Integer> indexMap = new HashMap<>();
        indexMap.put(list[lo], lo);
        indexMap.put(list[hi], hi);
        indexMap.put(list[middle], middle);
        indexMap.put(list[leftMiddle], leftMiddle);
        indexMap.put(list[rightMiddle], rightMiddle);

        Arrays.sort(elements);

        System.out.println("value median: 5 " + elements[middle]);
        System.out.println("index median: 5 " + indexMap.get(elements[middle]));
        return indexMap.get(elements[middle]);
    }
}
