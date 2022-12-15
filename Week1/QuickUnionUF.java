public class QuickUnionUF {
    private int[] parents;
    private int cnt;

    public QuickUnionUF(int N) {
        cnt = N;
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    public void validate(int p) {
        int n = parents.length;
        if (p >= n || p < 0) {
            throw new IllegalArgumentException("index " + p + " is not " +
                    "between 0 and " + (n - 1));
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parents[p]) {
            p = parents[p];
        }
        return p;
    }

    public int count() {
        return cnt;
    }

    @Deprecated
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        parents[rootP] = rootQ;

        cnt--;
    }
}
