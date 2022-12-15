public class SuccessorWithDelete {
    private WQUWithLargest uf;

    public SuccessorWithDelete(int N) {
        uf = new WQUWithLargest(N);
    }

    public int remove(int x) {
        uf.union(x, x+1);
        return uf.find(x);
    }

    public static void main(String[] args) {
        int N = 50;
        SuccessorWithDelete swd = new SuccessorWithDelete(50);
        for (int j = 30; j > 20; j--)
            System.out.println(swd.remove(j));
    }
}
