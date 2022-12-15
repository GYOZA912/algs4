public class WQUWithLargest{
    private int[] largest;
    private int[] size;
    private int[] parents;
    private int cnt;

    public WQUWithLargest(int N) {
        cnt = N;
        largest = new int[N];
        size = new int[N];
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
            size[1] = 1;
            largest[i] = i;
        }
    }

    private int root(int p) {
        while ((p != parents[p])) {
            parents[p] = parents[parents[p]];
            p = parents[p];
        }
        return p;
    }

    public int find(int p) {
        return largest[root(p)];
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if (size[rootP] < size[rootQ]) {
            parents[rootP] = rootQ;
            size[rootQ] += size[rootP];
            if (largest[rootP] > largest[rootQ]) {
                largest[rootQ] = largest[rootP];
            }
        }
        else {
            parents[rootQ] = rootP;
            size[rootP] += size[rootQ];
            if (largest[rootQ] > largest[rootP]) {
                largest[rootP] = largest[rootQ];
            }
        }

        cnt--;
    }

}
