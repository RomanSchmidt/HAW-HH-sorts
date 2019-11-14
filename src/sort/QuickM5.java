package sort;

public class QuickM5 extends QuickM3 {

    public static void main(String[] args) {
        //String[] comparables = StdIn.readAllStrings();
        Comparable[] comparables = new Comparable[]{1, 4, 5, 2, 3, 7, 8, 6, 10, 9};
        new QuickM5().start(comparables);
    }

    @Override
    protected int median(Comparable[] list, int lo, int j, int hi) {
        if (list.length <= 5) {
            return super.median(list, lo, j, hi);
        }
        int middle = list.length / 2;
        int leftMiddle = middle / 2;
        int rightMiddle = middle + leftMiddle;

        return super.median(list, lo, super.median(list, leftMiddle, middle, rightMiddle), hi);
    }
}
