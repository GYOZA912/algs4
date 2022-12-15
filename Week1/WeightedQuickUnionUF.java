public class WeightedQuickUnionUF {
    private int[] parents;
    private int[] size;
    private int cnt;

    /**
     * @param N - the number of elements
     */
    public WeightedQuickUnionUF(int N) {
        cnt = N;
        parents = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    public void validate(int p) {
        int n = parents.length;
        if (p >= n || p < 0) {
            throw new IllegalArgumentException("index " + p + " is not " +
                    "between 0 and " + (n - 1));
        }
    }

    public int count() {
        return cnt;
    }

    public int find(int p) {
        validate(p);
        while (p != parents[p]) {
            parents[p] = parents[parents[p]];   // Make the node point to its
            // grandparent to compress the path
            p = parents[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private void root(int p) {

    }
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (size[q] > size[p]) {
            parents[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parents[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        cnt--;
    }
}
