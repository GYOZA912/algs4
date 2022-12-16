/**
 * Successor with delete. Given a set of nn integers  S = {0,1,...,n−1} and a
 * sequence of requests of the following form:
 * Remove x from SS
 * Find the successor of x: the smallest y in S such that y ≥ x.
 *
 * design a data type so that all operations (except construction)  take
 * logarithmic time or better in the worst case.
 */

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
