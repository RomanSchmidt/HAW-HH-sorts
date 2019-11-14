package sort;

import java.util.Arrays;

public abstract class ASort {
    protected boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public abstract void sort(Comparable[] list);

    protected void exch(Comparable[] list, int i, int j) {
        Comparable t = list[i];
        list[i] = list[j];
        list[j] = t;
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

    public void start(Comparable[] list) {
        sort(list);
        assert isSorted(list);
        show(list);
    }
}
