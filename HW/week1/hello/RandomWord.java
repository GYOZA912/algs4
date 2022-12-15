import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double cnt = 0.0;
        String champion = " ";
        String temp;
        while (!StdIn.isEmpty()) {
            temp = StdIn.readString();
            cnt++;
            if (StdRandom.bernoulli(1 / cnt)) {
                champion = temp;
            }
        }
        StdOut.println(champion);
    }

}
