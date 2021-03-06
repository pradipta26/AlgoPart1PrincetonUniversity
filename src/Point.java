import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    private final int upperNumericLimit = 32767;
    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        if (x < 0 || y < 0 || x > upperNumericLimit || y > upperNumericLimit)
            throw new IllegalArgumentException("Coordinates must be in between 0 to 32,767");
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (that.y == this.y && that.x == this.x)
            return Double.NEGATIVE_INFINITY;
        else if (that.x == this.x)
            return Double.POSITIVE_INFINITY;
        else if (that.y == this.y)
            return 0.0;
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.y > that.y || (this.y == that.y && this.x > that.x)) return 1;
        else if (this.y < that.y || this.x < that.x) return -1;
        return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new SlopeCompare(this);
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        int x;
        int y;
        int arraySize = 2;
        int arrayCounter = 0;
        Point[] pointArray = new Point[arraySize];
        while (!StdIn.isEmpty()) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            if (x < 0 || y < 0)
                StdOut.println("invalid coordinate - Out of Range!!!");
            Point point = new Point(x, y);
            pointArray[arrayCounter++] = point;
            if (arrayCounter == arraySize) {
                Point[] tempPointArray = new Point[arraySize * 2];
                if (arraySize >= 0) System.arraycopy(pointArray, 0, tempPointArray, 0, arraySize);
                pointArray = tempPointArray;
                arraySize *= 2;
            }
           // StdOut.println("Array Size = " + arraySize + " Element is " + pointArray[arrayCounter - 1] + " Array Counter " + (arrayCounter));
        }

    }
    private static class SlopeCompare implements Comparator<Point> {
        private final Point point;
        private SlopeCompare(Point point) {
            this.point = point;
        }
        @Override
        public int compare(Point p, Point q) {
            return Double.compare(point.slopeTo(p), point.slopeTo(q));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point)
                return point.slopeTo((Point) obj) == 0;
            return false;
        }
    }
}
