package sort;

public class MinMaxHeap<Type extends Comparable<? super Type>> {
    private Type[] mMH;
    private int size;

    public MinMaxHeap(int maxSize) {
        this.size = 0;
        mMH = (Type[]) new Comparable[maxSize + 1];
    }

    public static void main(String[] args){
        //  int[] ary = {1,2,3,4,5,6,7,8,9,10, 20, 30, 40, 50, 60, 70, 80};
        MinMaxHeap hepp = new MinMaxHeap(2);
        for(int i=1;i<16;i++){
            hepp.insert(i);
        }
        hepp.print();
        System.out.println("\nMin: " + hepp.min() + "\nMax: " + hepp.max());
        System.out.println("\ndelete max and min... :");
        hepp.delMin();
        hepp.delMax();
        hepp.print();
        System.out.println("\nMin: " + hepp.min() + "\nMax: " + hepp.max());
    }

    public void insert(Type value) {
        if (size == mMH.length - 1) {
            increaseMaxSize();
        }
        mMH[++size] = value;
        bubbleUp(size);
    }

    public void delMin() {
        int tempMin = positionOfMin();
        exch(tempMin, size);
        mMH[size--] = null;
        trickleDown(tempMin);
    }

    public void delMax() {
        int tmpMax = positionOfMax();
        exch(tmpMax, size);
        mMH[size--] = null;
        trickleDown(tmpMax);
    }

    public Type min() {
        return mMH[positionOfMin()];
    }

    public Type max() {
        return mMH[positionOfMax()];
    }


    private void bubbleUp(int current) {
        if (isMinLevel(current)) {
            if (hasParent(current) && !(less(current, parentOf(current)))) {
                exch(current, parentOf(current));
                bubbleUpMax(parentOf(current));
            } else {
                bubbleUpMin(current);
            }
        } else {
            if (hasParent(current) && mMH[current].compareTo(mMH[parentOf(current)]) < 0) {
                exch(current, parentOf(current));
                bubbleUpMin(parentOf(current));
            } else {
                bubbleUpMax(current);
            }
        }
    }

    private void bubbleUpMin(int current) {
        if (hasGrandParent(current)) {
            if (less(current, grandparentOf(current))) {
                exch(current, grandparentOf(current));
                bubbleUpMin(grandparentOf(current));
            }
        }
    }

    private void bubbleUpMax(int current) {
        if (hasGrandParent(current)) {
            if (!(less(current, grandparentOf(current)))) {
                exch(current, grandparentOf(current));
                bubbleUpMax(grandparentOf(current));
            }
        }
    }

    private void trickleDown(int current) {
        if (isMinLevel(current)) {
            trickleDownMin(current);
        } else {
            trickleDownMax(current);
        }
    }

    private void trickleDownMin(int current) {
        if (hasLeftChild(current)) {
            int m = smallestGrandChildOf(current);
            if (isGrandparent(current, m)) {
                if (less(m, current)) {
                    exch(m, current);
                    if (!(less(m, parentOf(m)))) {
                        exch(m, parentOf(m));
                    }
                    trickleDownMin(m);
                }
            } else {
                if (less(m, current)) {
                    exch(m, current);
                }
            }
        }
    }

    private void trickleDownMax(int current) {
        if (hasLeftChild(current)) {
            int m = biggestGrandChildOf(current);
            if (isGrandparent(current, m)) {
                if (!(less(m, current))) {
                    exch(m, current);
                    if (less(m, parentOf(m))) {
                        exch(m, parentOf(m));
                    }
                    trickleDownMax(m);
                }
            } else {
                if (!(less(m, current))) {
                    exch(m, current);
                }
            }
        }
    }

    private static boolean isMinLevel(int position) {
        return (int) (Math.log(position) / Math.log(2.0)) % 2 == 0;
    }


    private boolean less(int i, int j) {
        return mMH[i].compareTo(mMH[j]) < 0;
    }

    private void exch(int posi, int posj) {
        Type tmp = mMH[posi];
        mMH[posi] = mMH[posj];
        mMH[posj] = tmp;
    }

    private void increaseMaxSize() {
        Type[] temp = (Type[]) new Comparable[mMH.length * 2];
        System.arraycopy(mMH, 0, temp, 0, mMH.length);
        mMH = temp;
    }

    /*
    //some boolean-methods to verify that an index is valid
    */
    private boolean hasLeftChild(int position) {
        return (leftChildOf(position) <= size);
    }

    private boolean hasRightChild(int position) {
        return (rightChildOf(position) <= size);
    }

    private boolean hasParent(int position) {
        return (position > 1);
    }

    private boolean hasGrandParent(int position) {
        return (position > 3);
    }

    // is x a grandparent of y?
    private boolean isGrandparent(int x, int y) {
        return y / 4 == x;
    }

    /*
    // some methods to improve readability and avoid "magic numbers"
    */
    private int positionOfMin() {
        if (size < 1) throw new IndexOutOfBoundsException();
        return 1;
    }

    private int positionOfMax() {
        if (size < 1) throw new IndexOutOfBoundsException();
        if (size <= 2 || less(3, 2)) {
            return Math.min(size, 2);
        }
        return 3;
    }

    private int leftChildOf(int position) {
        return position * 2;
    }

    private int rightChildOf(int position) {
        return position * 2 + 1;
    }

    private int parentOf(int position) {
        return (position / 2);
    }

    private int grandparentOf(int position) {
        return (position / 4);
    }

    private int smallestChildOf(int position) {
        int smallest = this.leftChildOf(position);
        if (this.hasRightChild(position) && !less(leftChildOf(position), rightChildOf(position))) {
            smallest = this.rightChildOf(position);
        }
        return smallest;
    }

    // returns the smallest one of the children and grandchildren(if any) of given position
    private int smallestGrandChildOf(int position) {
        int posOfSmallest = smallestChildOf(position);
        if (hasLeftChild(leftChildOf(position)) && !less(posOfSmallest, smallestChildOf(leftChildOf(position)))) {
            posOfSmallest = smallestChildOf(leftChildOf(position));
        }
        if (hasLeftChild(rightChildOf(position)) && !less(posOfSmallest, smallestChildOf(rightChildOf(position)))) {
            posOfSmallest = smallestChildOf(rightChildOf(position));
        }
        return posOfSmallest;
    }

    private int biggestChildOf(int position) {
        int biggest = this.leftChildOf(position);
        if (this.hasRightChild(position) && less(leftChildOf(position), rightChildOf(position))) {
            biggest = this.rightChildOf(position);
        }
        return biggest;
    }

    // returns the biggest one of the children and grandchildren(if any) of given position
    private int biggestGrandChildOf(int position) {
        int posOfBiggest = biggestChildOf(position);
        if (hasLeftChild(leftChildOf(position)) && less(posOfBiggest, biggestChildOf(leftChildOf(position)))) {
            posOfBiggest = biggestChildOf(leftChildOf(position));
        }
        if (hasLeftChild(rightChildOf(position)) && less(posOfBiggest, biggestChildOf(rightChildOf(position)))) {
            posOfBiggest = biggestChildOf(rightChildOf(position));
        }
        return posOfBiggest;
    }


    void print() {
        for (int i = 1; i <= this.size; i++) {
            System.out.print(mMH[i] + " ");
        }
    }
}
