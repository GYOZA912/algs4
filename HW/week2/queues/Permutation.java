import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> test = new RandomizedQueue<>();
        String s;
        int rdm;
        int k = Integer.parseInt(args[0]);
        if (k == 0) return;

        //蓄水池算法
        for (int i = 1; !StdIn.isEmpty(); i++) {
            s = StdIn.readString();
            if (i <= k) {
                test.enqueue(s);
            }
            else {
                rdm = StdRandom.uniformInt(i) + 1;
                if (rdm <= k) {
                    test.dequeue();
                    test.enqueue(s);
                }
            }

        }
        while (!test.isEmpty()) {
            StdOut.println(test.dequeue());
        }
    }

}
