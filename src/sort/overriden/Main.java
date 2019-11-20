package sort.overriden;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int length = 100000;
        Random rand = new Random();
        for (int i = 1; ; ++i) {
            Comparable[] comparables = new Comparable[i * length];
            for (int j = 0; j < i * length; ++j) {
                comparables[j] = rand.nextInt(length);
            }
            Merge.sort(comparables.clone());
            MergeBU.sort(comparables.clone());
            double TheoMerge = 6 * comparables.length * Math.log(comparables.length) / Math.log(2);
            double TheoMergeBu = 6 * comparables.length * Math.log(comparables.length) / Math.log(2);
            System.out.println("N: " + (i * length));
            System.out.println("Theo Merge: " + TheoMerge);
            System.out.println("Prakti Merge: " + Merge.getCounter());
            System.out.println("Verh. Merge: " + (Merge.getCounter() / TheoMerge));
            System.out.println("Theo MergeBU: " + TheoMergeBu);
            System.out.println("Prakti MergeBU: " + MergeBU.getCounter());
            System.out.println("Verh. Merge: " + (MergeBU.getCounter() / TheoMergeBu));
            System.out.println("\n\n");

            Merge.resetCounter();
            MergeBU.resetCounter();
        }
    }
}
