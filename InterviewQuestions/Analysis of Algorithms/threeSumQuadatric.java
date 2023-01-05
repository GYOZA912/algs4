/**
 * 3-SUM in quadratic time.
 * Design an algorithm for the 3-SUM problem that takes time proportional to
 * n^2 in the worst case. You may assume that you can sort the nn integers in
 * time proportional to n^2 or better.
 *
 * Hint: given an integer 𝚡 and a sorted array 𝚊[] of n distinct integers,
 * design a linear-time algorithm to determine if there exists two distinct
 * indices 𝚒 and 𝚓 such that 𝚊[𝚒]+𝚊[𝚓] == 𝚡.
 */


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class threeSumQuadatric {

    public ArrayList<Integer> arr;

    public threeSumQuadatric() {
        arr = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            arr.add(scan.nextInt());
        }
    }

    /**
     * I don't understand why this could be O(n^2)...Anyway...
     */
    public boolean threeSum() {
        Collections.sort(arr);

        for (int i = 0; i < arr.size(); i++){
            int x = -1 * arr.get(i);
            int j = i + 1;
            int k = arr.size() - 1;

            while (j < k) {
                if (arr.get(j) + arr.get(k) == x) {
                    System.out.println(i + " " + j + " " + k);
                    return true;
                }
                else if (arr.get(j) + arr.get(k) < x) {
                    j++;
                }
                else {
                    k--;
                }
            }
        }
        return false;
    }


}
