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

public class FastCollinearPoints {
    private int n;
    private ArrayList<ArrayList<Point>> pointEnd;
    private Point[] points;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point p : points)
            if (p == null) throw new IllegalArgumentException();
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++)
            if (points[i].compareTo(points[i + 1]) == 0) throw new IllegalArgumentException();

        this.points = Arrays.copyOf(points, points.length);
        this.pointEnd = new ArrayList<ArrayList<Point>>();

        for (int i = 0; i < points.length; i++) {
            // StdOut.println(points[i]);
            Point[] points2 = Arrays.copyOf(points, points.length);
            Arrays.sort(points2, points[i].slopeOrder());

            ArrayList<ArrayList<Point>> clusterBySlope = new ArrayList<ArrayList<Point>>();
            ArrayList<Point> arr = new ArrayList<Point>();
            // clusterBySlope.add(arr);
            for (int j = 1; j < points2.length; j++) {
                // StdOut.print(points2[j].toString() + "-" + points[i].slopeTo(points2[j]) + " ");
                if (!((Double) points[i].slopeTo(points2[j])).isInfinite()) {
                    if (arr.size() == 0) {
                        arr.add(points2[j]);
                    }
                    else if (points[i].slopeTo(arr.get(0)) == points[i].slopeTo(points2[j])) {
                        arr.add(points2[j]);
                    }
                    else {
                        clusterBySlope.add(arr);
                        // for (Point pa : arr) {
                        //    StdOut.print(pa.toString() + ",");
                        //}
                        // StdOut.print(" ; ");
                        arr = new ArrayList<Point>();
                        arr.add(points2[j]);
                    }
                }
                else {
                    clusterBySlope.add(arr);
                    arr = new ArrayList<Point>();
                    int jj = j;
                    while (jj < points2.length) {
                        arr.add(points2[jj]);
                        jj++;
                    }
                    // clusterBySlope.add(arr);
                    break;
                }
            }
            clusterBySlope.add(arr);
            // StdOut.println();

            ArrayList<Point> pointend = new ArrayList<Point>();
            for (ArrayList<Point> list : clusterBySlope) {
                if (list.size() >= 3) {
                    list.add(points[i]);
                    // Sắp xếp theo góc mà các điểm tạo với gốc tọa độ, và các điểm cùng tọa độ x=0
                    // với O(0,0) thì sẽ dứngđầu list
                    list.sort((new Point(0, 0)).slopeOrder().reversed()
                                               .thenComparing(Point::compareTo));

                    if (list.get(0).compareTo(points[i]) == 0)
                        pointend.add(list.get(list.size() - 1));

                }

            }

            this.pointEnd.add(pointend);
            n += pointend.size();
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return n;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[n];
        int stt = 0;
        for (int i = 0; i < points.length; i++) {
            ArrayList<Point> end = pointEnd.get(i);
            for (Point point : end) {
                lineSegments[stt++] = new LineSegment(points[i], point);
            }
        }
        return lineSegments;
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
