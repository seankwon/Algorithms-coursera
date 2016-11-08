import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import edu.princeton.cs.algs4.*;
public class FastCollinearPoints {
    Point[] p;
    ArrayList<Double> slopes = new ArrayList<Double>();
    ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        p = points;
        Point[] sortedPoints = points.clone();
        double currSlope;
        Point currPoint;
        ArrayList<Point> pointsOfInterest;

        for (int i = 0; i < p.length; i++) {
            currPoint = p[i];
            Arrays.sort(sortedPoints, currPoint.slopeOrder());
            currSlope = currPoint.slopeTo(sortedPoints[1]);
            pointsOfInterest = new ArrayList<Point>(10);

            for (int j = 2; j < sortedPoints.length; j++) {
                if (Double.compare(currSlope, currPoint.slopeTo(sortedPoints[j])) == 0) {
                    pointsOfInterest.add(sortedPoints[j]);
                } else {
                    checkPOI(pointsOfInterest, currSlope);
                    pointsOfInterest = new ArrayList<Point>(5);
                    currSlope = currPoint.slopeTo(sortedPoints[j]);
                }
            }

            checkPOI(pointsOfInterest, currSlope);
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
    private double getDistance(Point a, Point b) {
        return Math.sqrt(((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y)));
    }
    private void checkPOI(ArrayList<Point> p, double currSlope) {
        System.out.println(p.size());
        if (p.size() >= 4 && !slopes.contains(currSlope)) {
            Collections.sort(p);
            segments.add(new LineSegment(p.get(0), p.get(p.size()-1)));
            slopes.add(currSlope);
        }
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

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
    }

}

class LineSegment {
    private final Point p;   // one endpoint of this line segment
    private final Point q;   // the other endpoint of this line segment

    /**
     * Initializes a new line segment.
     *
     * @param  p one endpoint
     * @param  q the other endpoint
     * @throws NullPointerException if either <tt>p</tt> or <tt>q</tt>
     *         is <tt>null</tt>
     */
    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.q = q;
    }

    
    /**
     * Draws this line segment to standard draw.
     */
    public void draw() {
        p.drawTo(q);
    }

    /**
     * Returns a string representation of this line segment
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this line segment
     */
    public String toString() {
        return p + " -> " + q;
    }

    /**
     * Throws an exception if called. The hashCode() method is not supported because
     * hashing has not yet been introduced in this course. Moreover, hashing does not
     * typically lead to good *worst-case* performance guarantees, as required on this
     * assignment.
     *
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}
