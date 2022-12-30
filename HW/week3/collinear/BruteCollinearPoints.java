import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;


public class BruteCollinearPoints {
    private ArrayList<LineSegment> collinearList = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("the argument " +
                "is null");
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("One point is " +
                    "null");
        }
        int len = points.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("repeated points");
            }
        }
        if (len < 4) return;

        Point[] copy = points.clone();
        Arrays.sort(copy);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int h = k + 1; h < len; h++) {
                        if (copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[k])
                            && copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[h])) {
                            collinearList.add(new LineSegment(copy[i],
                                    copy[h]));
                        }
                    }
                }
            }
        }
    }
    // the number of line segments
    public int numberOfSegments() {
        return collinearList.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] returnSegments = new LineSegment[numberOfSegments()];
        int i = 0;
        for (LineSegment x : collinearList) returnSegments[i++] = x;
        return returnSegments;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}