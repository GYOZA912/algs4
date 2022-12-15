import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /**
     * Corner cases:
     * By convention, the row and column indices are integers
     * between 1 and n, where (1, 1) is the upper-left site: Throw an
     * IllegalArgumentException if any argument to open(), isOpen(), or
     * isFull() is outside its prescribed range. Throw a
     * IllegalArgumentException in the constructor if n ≤ 0.
     */

    /** Performance requirements:
     *  The constructor must take time proportional to n2; all instance
     *  methods must take constant time plus a constant number of calls to
     *  union() and find().
     */

    private boolean[] openStatus;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufWithoutRoot;
    private int size;
    private int n;
    private int cnt; // count the open sites;

    // creates n-by-n grid, with all sites initially blocked
    // each site is named by row * n + column
    // All array are of two extra grids for the dummy nodes.
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n has to be positive integer");
        }

        this.n = n;
        cnt = 0;
        size = n * n + 2; // n*n grids plus two dummy
        openStatus = new boolean[size];
        uf = new WeightedQuickUnionUF(size);
        ufWithoutRoot = new WeightedQuickUnionUF(size - 1);
        for (int i = 0; i < size; i++) {
            openStatus[i] = false;
        }

        // initialize two dummy
        for (int i = 1; i <= n; i++) {
            // The lecture slide is actually a little misleading. To get right
            // resul for isFull, no button dummy shall be used, otherwise if
            // there is any percolation, the nodes not in percolation but
            // connected to the isolated open button node will all be counted
            // as connected to the top. See last question percolation:
            // https://coursera.cs.princeton.edu/algs4/assignments/percolation/faq.php
            uf.union(0, i);
            ufWithoutRoot.union(0, i);


            uf.union(n * n - n + i, n * n + 1);
        }

    }
    private int locate(int row, int col) {
        return (row - 1) * n + col;
    }

    private void validateLocation(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException("row and column should be in" +
                    "tergers in [1, " + n + "]. Row is: " + row + ", and Col " +
                    "is: " + col);
        }
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateLocation(row, col);
        if (!openStatus[locate(row, col)]) {
            openStatus[locate(row, col)] = true;

            // down
            if (row - 1 >= 1 && isOpen(row - 1, col)) {
                uf.union(locate(row - 1, col), locate(row, col));
                ufWithoutRoot.union(locate(row - 1, col), locate(row, col));
            }

            // up
            if (row + 1 <= n && isOpen(row + 1, col)) {
                uf.union(locate(row + 1, col), locate(row, col));
                ufWithoutRoot.union(locate(row + 1, col), locate(row, col));
            }

            // right
            if (col - 1 >= 1 && isOpen(row, col - 1)) {
                uf.union(locate(row, col - 1), locate(row, col));
                ufWithoutRoot.union(locate(row, col - 1), locate(row, col));
            }

            // left
            if (col + 1 <= n && isOpen(row, col + 1)) {
                uf.union(locate(row, col + 1), locate(row, col));
                ufWithoutRoot.union(locate(row, col + 1), locate(row, col));
            }

            cnt++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateLocation(row, col);
        return openStatus[locate(row, col)];
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
            if (row <= 0 || row > n || col <= 0 || col > n) {
                throw new IllegalArgumentException("row and column should be in" +
                        "tergers in [1, N] for isOpen() method");
        }
            return isOpen(row, col) && ufWithoutRoot.find(locate(row, col)) == ufWithoutRoot.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return cnt;
    }

    // does the system percolate?
    public boolean percolates() {
        if (n == 1) return isOpen(1, 1);
        return uf.find(0) == uf.find(size - 1);
    }

    // test client (optional)
    public static void main(String[] args) {
    Percolation test1 = new Percolation(4);
    for (int i = 1; i <= 4; i++) {
        for (int j = 1; j <= 4; j++) {
            if (j >= i) test1.open(i, j);
            System.out.println(test1.isOpen(i, j) + " " + test1.isFull(i, j));
        }
        System.out.println();
    }
    System.out.println("\n" + test1.percolates());

/*    Percolation test2 = new Percolation(100);

        test2.open(1,1);
        test2.open(2,1);
        test2.open(3,2);
        test2.open(2,2);
        System.out.print(test2.isFull(3,2));*/

    }
}