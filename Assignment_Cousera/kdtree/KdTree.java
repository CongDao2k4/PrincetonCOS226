/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Color;
import java.util.ArrayList;

public class KdTree {
    private final boolean XOrder = true, YOrder = false;
    private Node root;

    private class Node {
        private Point2D key;
        private boolean orderPartiton;
        private Node left, right;
        private int size;

        public Node(Point2D key, int size, boolean order) {
            this.key = key;
            this.size = size;
            this.orderPartiton = order;
        }

        private double getXcoordition() {
            return key.x();
        }

        private double getYcoordition() {
            return key.y();
        }

        private double distanceToPoint2D(Point2D queryPoint) {
            Point2D thispoint2D = key;
            return thispoint2D.distanceTo(queryPoint);
        }
    }

    // construct an empty set of points
    public KdTree() {

    }

    // is the set empty?
    public boolean isEmpty() {
        if (size(root) > 0) return false;
        return true;
    }

    // number of points in the set
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point2D must has value");
        // root.orderPartiton = XOrder;
        root = insert(root, p, YOrder);
    }

    private Node insert(Node x, Point2D key, boolean order) {
        if (x == null) {
            x = new Node(key, 1, !order);
            return x;
        }
        double xKey, keyKey;
        boolean partitionOrder = !order;

        if (partitionOrder == XOrder) {
            xKey = x.getXcoordition();
            keyKey = key.x();
        }
        else {
            xKey = x.getYcoordition();
            keyKey = key.y();
        }

        if (keyKey < xKey) {
            x.left = insert(x.left, key, partitionOrder);
            x.size = size(x.left) + 1 + size(x.right);
        }
        else if (keyKey > xKey) {
            x.right = insert(x.right, key, partitionOrder);
            x.size = size(x.left) + 1 + size(x.right);
        }
        else {
            if (x.key.compareTo(key) != 0) {
                x.right = insert(x.right, key, partitionOrder);
                x.size = size(x.left) + 1 + size(x.right);
            }
        }
        return x;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point2D must has value");
        return search(root, p);
    }

    private boolean search(Node x, Point2D key) {
        if (x == null) return false;
        double xKey, keyKey;
        boolean partitionOrder = x.orderPartiton;

        if (partitionOrder == XOrder) {
            xKey = x.getXcoordition();
            keyKey = key.x();
        }
        else {
            xKey = x.getYcoordition();
            keyKey = key.y();
        }

        if (keyKey < xKey) {
            return search(x.left, key);
        }
        else if (keyKey > xKey) {
            return search(x.right, key);
        }
        else {
            Point2D x1 = x.key;
            Point2D y = key;
            if (x1.compareTo(y) == 0)
                return true;
            else return search(x.right, key);
        }
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setScale(0.0, 1.0);
        StdDraw.enableDoubleBuffering();
        draw(root, 0.0, 1.0, 0.0, 1.0);
        // StdDraw.show();
    }

    private void draw(Node x, double XminExtreme, double XmaxExtreme, double YminExtreme,
                      double YmaxExtreme) {
        if (x == null) return;


        if (x.orderPartiton == XOrder) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(x.getXcoordition(), YminExtreme, x.getXcoordition(), YmaxExtreme);
            // StdDraw.show();
            // StdDraw.pause(1000);
            draw(x.left, XminExtreme, x.getXcoordition(), YminExtreme, YmaxExtreme);
            draw(x.right, x.getXcoordition(), XmaxExtreme, YminExtreme, YmaxExtreme);
        }
        else {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(XminExtreme, x.getYcoordition(), XmaxExtreme, x.getYcoordition());
            // StdDraw.show();
            // StdDraw.pause(1000);
            draw(x.left, XminExtreme, XmaxExtreme, YminExtreme, x.getYcoordition());
            draw(x.right, XminExtreme, XmaxExtreme, x.getYcoordition(), YmaxExtreme);
        }
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        //(new Point2D(x.getXcoordition(), x.getYcoordition())).draw();
        StdDraw.point(x.getXcoordition(), x.getYcoordition());
        // StdDraw.show();
        // StdDraw.pause(1000);
        StdDraw.setPenRadius();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("parameter RectHV is null !");
        ArrayList<Point2D> result = new ArrayList<Point2D>();
        for (Point2D k : range(root, rect)) {
            result.add(k);
        }
        return result;
    }

    private ArrayList<Point2D> range(Node x, RectHV rect) {
        ArrayList<Point2D> arrayList = new ArrayList<>();
        if (x == null) return arrayList;

        if (rect.contains(x.key)) {
            // StdOut.println("Rect contains :" + x.key.toString());
            arrayList.add(x.key);
        }

        // boolean order = x.orderPartiton;
        boolean above, between, under;
        if (x.orderPartiton == XOrder) {
            above = (x.getXcoordition() > rect.xmax());
            between = (x.getXcoordition() >= rect.xmin() && x.getXcoordition() <= rect.xmax());
            under = (x.getXcoordition() < rect.xmin());
        }
        else {
            above = (x.getYcoordition() > rect.ymax());
            between = (x.getYcoordition() >= rect.ymin() && x.getYcoordition() <= rect.ymax());
            under = (x.getYcoordition() < rect.ymin());
        }

        if (above) {
            arrayList.addAll(range(x.left, rect));
        }
        else if (under) {
            arrayList.addAll(range(x.right, rect));
        }
        else {
            arrayList.addAll(range(x.left, rect));
            arrayList.addAll(range(x.right, rect));
        }
        return arrayList;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("parameter Point2D is null !");
        if (size(root) == 0) return null;
        Node minNode = root;
        Node minNeed = nearest(root, p, minNode, 0.0, 1.0, 0.0, 1.0);
        return minNeed.key;
    }

    private Node nearest(Node x, Point2D p, Node minNode, double XminExtreme, double XmaxExtreme,
                         double YminExtreme, double YmaxExtreme) {
        if (x == null) return minNode;
        if (x.distanceToPoint2D(p) < minNode.distanceToPoint2D(p)) {
            minNode = x;
        }
        Node leftsub = x.left, rightsub = x.right;
        if (sameSide(p, leftsub, x)) {
            if (x.orderPartiton == XOrder) {
                minNode = nearest(leftsub, p, minNode, XminExtreme, x.getXcoordition(), YminExtreme,
                                  YmaxExtreme);
                if (minNode.distanceToPoint2D(p) > distanceFromQuerypointToReactHVofSubnode(x, p,
                                                                                            rightsub,
                                                                                            XminExtreme,
                                                                                            XmaxExtreme,
                                                                                            YminExtreme,
                                                                                            YmaxExtreme)) {
                    minNode = nearest(rightsub, p, minNode, x.getXcoordition(), XmaxExtreme,
                                      YminExtreme, YmaxExtreme);
                }
            }
            else {
                minNode = nearest(leftsub, p, minNode, XminExtreme, XmaxExtreme, YminExtreme,
                                  x.getYcoordition());
                if (minNode.distanceToPoint2D(p) > distanceFromQuerypointToReactHVofSubnode(x, p,
                                                                                            rightsub,
                                                                                            XminExtreme,
                                                                                            XmaxExtreme,
                                                                                            YminExtreme,
                                                                                            YmaxExtreme)) {
                    minNode = nearest(rightsub, p, minNode, XminExtreme, XmaxExtreme,
                                      x.getYcoordition(), YmaxExtreme);
                }
            }
        }
        else {
            if (x.orderPartiton == XOrder) {
                minNode = nearest(rightsub, p, minNode, x.getXcoordition(), XmaxExtreme,
                                  YminExtreme,
                                  YmaxExtreme);
                if (minNode.distanceToPoint2D(p) > distanceFromQuerypointToReactHVofSubnode(x, p,
                                                                                            leftsub,
                                                                                            XminExtreme,
                                                                                            XmaxExtreme,
                                                                                            YminExtreme,
                                                                                            YmaxExtreme)) {
                    minNode = nearest(leftsub, p, minNode, XminExtreme, x.getXcoordition(),
                                      YminExtreme, YmaxExtreme);
                }
            }
            else {
                minNode = nearest(rightsub, p, minNode, XminExtreme, XmaxExtreme,
                                  x.getYcoordition(),
                                  YmaxExtreme);
                if (minNode.distanceToPoint2D(p) > distanceFromQuerypointToReactHVofSubnode(x, p,
                                                                                            leftsub,
                                                                                            XminExtreme,
                                                                                            XmaxExtreme,
                                                                                            YminExtreme,
                                                                                            YmaxExtreme)) {
                    minNode = nearest(leftsub, p, minNode, XminExtreme, XmaxExtreme,
                                      YminExtreme, x.getYcoordition());
                }
            }
        }
        return minNode;
    }

    private boolean sameSide(Point2D queryPoint, Node sub, Node parent) {
        if (sub == null) return false;
        if (parent.orderPartiton == XOrder) {
            double x1 = queryPoint.x() - parent.key.x(), x2 = sub.key.x() - parent.key.x();
            return (x1 < 0 && x2 < 0) || (x1 > 0 && x2 >= 0);
        }
        double y1 = queryPoint.y() - parent.key.y(), y2 = sub.key.y() - parent.key.y();
        return (y1 < 0 && y2 < 0) || (y1 > 0 && y2 >= 0);
    }

    private double distanceFromQuerypointToReactHVofSubnode(Node parent, Point2D p, Node sub,
                                                            double XminExtreme,
                                                            double XmaxExtreme,
                                                            double YminExtreme,
                                                            double YmaxExtreme) {
        if (sub == null) return Double.POSITIVE_INFINITY;
        if (parent.orderPartiton == XOrder) {
            if (sub.getXcoordition() < parent.getXcoordition()) {
                return (new RectHV(XminExtreme, YminExtreme, parent.getXcoordition(),
                                   YmaxExtreme)).distanceTo(p);
            }
            else return (new RectHV(parent.getXcoordition(), YminExtreme, XmaxExtreme,
                                    YmaxExtreme)).distanceTo(p);
        }
        else {
            if (sub.getYcoordition() < parent.getYcoordition()) {
                return (new RectHV(XminExtreme, YminExtreme, XmaxExtreme,
                                   parent.getYcoordition())).distanceTo(p);
            }
            else return (new RectHV(XminExtreme, parent.getYcoordition(), XmaxExtreme,
                                    YmaxExtreme)).distanceTo(p);
        }
    }


    // unit testing of the methods (optional)
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        // process nearest neighbor queries
        StdDraw.enableDoubleBuffering();

        while (true) {

            // the location (x, y) of the mouse
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            Point2D query = new Point2D(x, y);

            // draw all of the points
            StdDraw.clear();
            StdDraw.setPenRadius();
            kdtree.draw();

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            brute.draw();

            // draw in red the nearest neighbor (using brute-force algorithm)
            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.RED);
            brute.nearest(query).draw();
            StdDraw.setPenRadius(0.02);


            // draw in blue the nearest neighbor (using kd-tree algorithm)
            StdDraw.setPenColor(StdDraw.BLUE);
            kdtree.nearest(query).draw();
            StdDraw.show();
            StdDraw.pause(40);

            if (StdDraw.isMousePressed()) {
                double mx = StdDraw.mouseX(), my = StdDraw.mouseY();
                StdOut.println(mx + ", " + my);
                StdOut.println(brute.nearest(query));
                StdOut.println(kdtree.nearest(query));
            }
        }
        /*A  0.0 1.0
        B  0.0 0.75
        C  0.25 0.5
        D  1.0 0.25
        E  0.0 0.0
        F  0.75 0.25
        G  1.0 0.5
        H  0.25 1.0
        I  1.0 0.0
        J  0.75 0.75
        kdTree.insert(new Point2D(0.0, 1.0));
        kdTree.insert(new Point2D(0.0, 0.75));
        kdTree.insert(new Point2D(0.25, 0.5));
        kdTree.insert(new Point2D(1.0, 0.25));
        kdTree.insert(new Point2D(0.0, 0.0));
        kdTree.insert(new Point2D(0.75, 0.25));
        kdTree.insert(new Point2D(1.0, 0.5));
        kdTree.insert(new Point2D(0.25, 1.0));
        kdTree.insert(new Point2D(1.0, 0.0));
        kdTree.insert(new Point2D(0.75, 0.75));*/

        // StdOut.println(kdTree.contains(new Point2D(0.66, 0.75)));
        // StdOut.println(kdTree.contains(new Point2D(0.6, 0.75)));
        // StdOut.println(kdTree.size());
        // StdOut.println(kdTree.size(kdTree.root.left));
        // RectHV rect = new RectHV(0.25, 0.25, 0.5, 0.75);
        // RectHV rect = new RectHV(0.09375, 0.15625, 0.5, 0.5);
        // rect.draw();
        // StdDraw.show();
        // kdTree.draw();

        // for (Point2D p : kdTree.range(rect)) {
        //    StdOut.println(p);
        //}
    }
}
