import java.util.Arrays;

public class countingInversions {
    private static Comparable[] aux;
    private static int cnt;

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                cnt += mid - i + 1;
                a[k] = aux[j++];
            }
            else a[k] = aux[i++];
        }
    }

    public static int inversionNum(Comparable[] a) {
        cnt = 0;
        int N  = a.length;
        aux = new Comparable[N];
        sort(a, 0, N - 1);
        return cnt;
    }

    public static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo)/2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    public static void main(String[] args) {
        Integer[] a = { 6,5,2,3,4,1 };
        System.out.println(inversionNum(a));
        System.out.println(Arrays.toString(a));
        }
}
