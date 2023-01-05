import edu.princeton.cs.algs4.StdRandom;

public class Merge {
    private static Comparable[] aux;
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void merge(Comparable[] a, int low, int mid, int hi) {
        int i = low, j = mid + 1;
        for (int k = low; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = low; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    /**
     * Time complexity: O(NlgN)
     */
    public static void topDownMergeSort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        topDownMergeSort(a, 0, N - 1);
    }

    private static void topDownMergeSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        topDownMergeSort(a, lo, mid);
        topDownMergeSort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    /**
     * Time complexity: O(NlgN)
     */
    public static void buttonUpMergeSort(Comparable[] a) {
        int len = a.length;
        aux = new Comparable[len];
        for (int sz = 1; sz < len; sz *= 2) {
            for (int lo = 0; lo < len - 1; lo += sz+sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, len-1));
            }
        }
    }

    /**
     * Time complexity: O(NlgN) if with guaranteed randomness by shuffle
     */
    public static void quickSort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j+1, hi);
    }
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(a[--j], v)) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    /**
     * Goal. Given an array of N items, find a kth smallest item.
     * Ex. Min (k = 0), max (k = N - 1), median (k = N/2).
     */
    public static Comparable quickSelect(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
            else return a[k];
        }
        return a[k];
    }

    public static void quick3way(Comparable[] a, int lo, int hi) {
        if (hi >= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, i++, lt++);
            else if (cmp > 0) exch(a, i, gt++);
            else i++;
        }
        quick3way(a, lo, lt - 1);
        quick3way(a, gt + 1, lo);
    }
}
