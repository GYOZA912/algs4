import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> fastCollinear = new ArrayList<>();
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument to constructor is null");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("one point is null");
            }
        }
        int len = points.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("repeated point");
                }
            }
        }
        if (len < 4) {
            return;
        }
        Point[] copy = points.clone();
        Arrays.sort(copy);
        Point[] temp = copy.clone();
        for (Point p : copy) {
            Arrays.sort(temp, p.slopeOrder());
            int i = 1;
            while (i < len) {
                int j = i + 1;
                int max = i;
                int min = i;
                while (j < len && temp[i].slopeTo(p) == temp[j].slopeTo(p)) {
                    if (temp[j].compareTo(temp[max]) > 0) max = j;
                    else if (temp[j].compareTo(temp[min]) < 0) min = j;
                    j++;
                }
                if (j - i >= 3 && temp[min].compareTo(temp[0]) > 0) {
                    fastCollinear.add(new LineSegment(temp[0], temp[max]));
                }
                if (j == len) break;
                i = j;
            }
        }

    }
    // the number of line segments
    public int numberOfSegments() {
        return fastCollinear.size();
    }
    // the line segments
    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[numberOfSegments()];
        int i = 0;
        for (LineSegment x : fastCollinear) {
            ret[i++] = x;
        }
        return ret;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}