import java.util.Arrays;

/**
 * Merging with smaller auxiliary array:
 * Suppose that the subarray a[0] to a[n−1] is sorted and the subarray, a[n]
 * to a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to
 * a[2∗n−1] is sorted using an auxiliary array of length nn (instead of 2n)?
 *
 * Hint: copy only the left half into the auxiliary array.
 */
public class MergingWithSmallerAuxiliaryArray {
    public static void merge(Comparable[] arr) {
        int len = arr.length;

        // Copy left half of array to the auxiliary array.
        Comparable[] aux = new Comparable[len / 2];
        for (int i  = 0; i < len / 2; i++) {
            aux[i] = arr[i];
        }

        // Merge the aux with the right half.
        int idxL = 0, idxR = len / 2;
        for (int i = 0; i < len; i++) {
            if (idxL >= len / 2) break;
            else if (idxR >= len) arr[i] = aux[idxL++];
            else if (aux[idxL].compareTo(arr[idxR]) <= 0) arr[i] = aux[idxL++];
            else arr[i] = arr[idxR++];
        }
    }

    public static void main(String args[]) {
        Comparable[] test1 = {'a', 'f', 'q', 'z', 'b', 'h', 'l', 'x'};
        Comparable[] test2 = {1, 55, 90, 100, 4, 50, 99, 101};

        merge(test1);
        merge(test2);

        System.out.println(Arrays.toString(test1));
        System.out.println(Arrays.toString(test2));
    }
}
