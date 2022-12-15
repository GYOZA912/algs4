import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private double[] trailRecord;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trails should be " +
                    "positive integers, while n is " + n + " and trails is " + trials);
        }
        trailRecord = new double[trials];
        Percolation test;
        int nSquare = n * n;
        for (int i = 0; i < trials; i++) {
            test = new Percolation(n);
            while (!test.percolates()) {
                test.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1,
                        n + 1));
            }
            trailRecord[i] = (double) test.numberOfOpenSites() / nSquare;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trailRecord);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trailRecord);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev())/Math.sqrt(trailRecord.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev())/Math.sqrt(trailRecord.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats p1 = new PercolationStats(n, trails);
        System.out.println(
                "mean                    = " + p1.mean() + "\n" +
                "stddev                  = " + p1.stddev() + "\n" +
                "95% confidence interval = [" + p1.confidenceLo() + ", " + p1.confidenceHi() +
                "]");

    }
}
