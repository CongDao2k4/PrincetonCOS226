/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> set;

    // construct an empty set of points
    public PointSET() {
        // set = new TreeSet<Point2D>();
        init();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point2D must has value");
        addPoint(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point2D must has value");
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        drawEachpoint();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("parameter RectHV is null !");

        ArrayList<Point2D> a = new ArrayList<Point2D>();
        for (Point2D p : set) {
            if (rect.contains(p)) a.add(p);
        }
        return a;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("parameter Point2D is null !");
        if (set.size() == 0) return null;

        double minDistance = Double.POSITIVE_INFINITY;
        Point2D point2D = null;
        for (Point2D point : set) {
            if (p.distanceTo(point) < minDistance) {
                minDistance = p.distanceTo(point);
                point2D = new Point2D(point.x(), point.y());
            }
        }
        return point2D;
    }

    private void init() {
        set = new TreeSet<Point2D>();
    }

    private void addPoint(Point2D p) {
        set.add(p);
    }

    private void drawEachpoint() {
        for (Point2D p : set)
            p.draw();
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
