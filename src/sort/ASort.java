package sort;

import java.util.Arrays;

public abstract class ASort {
    protected boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public abstract void sort(Comparable[] a);

    protected void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void show(Comparable[] a) {
        System.out.println(Arrays.toString(a));
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public void start(Comparable[] args) {
        sort(args);
        assert isSorted(args);
        show(args);
    }
}
