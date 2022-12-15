import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] idx;
    private int cnt;

    public QuickFindUF(int N) {
        idx = new int[N];
        cnt = N;
        for (int i = 0; i < N; i++) {
            idx[i] = i;
        }
    }

    private void validate(int p) {
        int n = idx.length;
        if (p >= n || p < 0) {
            throw new IllegalArgumentException("index " + p + " is not " +
                    "between 0 and " + (n - 1));
        }
    }

    public int find(int num) {
        validate(num);
        return idx[num];
    }

    @Deprecated
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pID = idx[p];
        int qID = idx[q];

        if (pID == qID) return;

        for (int i = 0; i < idx.length; i++) {
            if (idx[i] == pID) {
                idx[i] = qID;
            }
        }

        cnt--;
    }

    public int count() {
        return cnt;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
