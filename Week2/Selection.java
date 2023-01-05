public class Selection {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void sort(Comparable[] a) {
        int min;
        for (int i = 0; i < a.length; i++) {
            min = i;
            for (int j = i; j < a.length; j++) {
                if (less(a[j], a[min])) min = j;
                exch(a, i, min);
            }
        }
    }
}
