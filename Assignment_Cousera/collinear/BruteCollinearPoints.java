/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int n;
    private ArrayList<ArrayList<Point>> id;
    private Point[] points;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point p : points)
            if (p == null) throw new IllegalArgumentException();

        this.points = Arrays.copyOf(points, points.length);
        Arrays.sort(this.points);

        for (int i = 0; i < this.points.length - 1; i++)
            if (this.points[i].compareTo(this.points[i + 1]) == 0)
                throw new IllegalArgumentException();

        // n = 0;
        id = new ArrayList<ArrayList<Point>>();
        for (int i = 0; i < this.points.length; i++) {
            ArrayList<Point> p = new ArrayList<Point>();
            p.add(this.points[i]);
            id.add(p);
        }

        for (int i = 0; i < this.points.length; i++) {
            for (int j = i + 1; j < this.points.length; j++) {
                for (int h = j + 1; h < this.points.length; h++) {
                    for (int k = h + 1; k < this.points.length; k++) {
                        double slope1 = this.points[j].slopeTo(this.points[i]);
                        double slope2 = this.points[h].slopeTo(this.points[i]);
                        double slope3 = this.points[k].slopeTo(this.points[i]);
                        if (slope1 == slope2 && slope1 == slope3) {
                            // if (!id.get(i).contains(points[k])) {
                            // n++;
                            boolean conflict = false;
                            ArrayList<Point> p = id.get(i);
                            for (int tt = 1; tt < p.size(); tt++) {
                                if (p.get(tt).slopeTo(this.points[i]) == this.points[k].slopeTo(
                                        points[i])) {
                                    conflict = true;
                                    p.set(tt, this.points[k]);
                                    break;
                                }
                            }
                            if (!conflict) {
                                p.add(this.points[k]);
                                n++;
                            }
                            //}
                            id.set(i, p);
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return n;
    }

    // line segments
    public LineSegment[] segments() {
        LineSegment[] lines = new LineSegment[n];
        int j = 0;
        for (int i = 0; i < id.size(); i++) {
            Point[] p = new Point[id.get(i).size()];
            id.get(i).toArray(p);
            for (int f = 1; f < p.length; f++)
                lines[j++] = new LineSegment(p[0], p[f]);
        }
        return lines;
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
