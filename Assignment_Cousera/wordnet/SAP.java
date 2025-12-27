import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private int V;
    private Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();

        V = G.V();
        this.G = new Digraph(G);
        /*id = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            id[i] = new Bag<Integer>();
        for (int i = 0; i < V; i++) {
            for (int j : G.adj(i))
                id[i].add(j);
        }*/
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);

        int minl = Integer.MAX_VALUE;
        // if (bfsV.hasPathTo(w)) minl = Math.min(minl, bfsV.distTo(w));
        // if (bfsW.hasPathTo(v)) minl = Math.min(minl, bfsW.distTo(v));

        for (int i = 0; i < V; i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
                minl = Math.min(minl, bfsV.distTo(i) + bfsW.distTo(i));
        }
        if (minl == Integer.MAX_VALUE) return -1;
        return minl;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException();

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);

        int minAncestor = -1;
        int minl = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
                if (bfsV.distTo(i) + bfsW.distTo(i) < minl) {
                    minl = bfsV.distTo(i) + bfsW.distTo(i);
                    minAncestor = i;
                }
        }
        return minAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        for (Integer ele : v) if (ele == null) throw new IllegalArgumentException();
        for (Integer ele : w) if (ele == null) throw new IllegalArgumentException();

        int minlength = Integer.MAX_VALUE;
        for (int ver : v) {
            for (int wer : w) {
                minlength = Math.min(minlength, length(ver, wer));
            }
        }
        if (minlength == Integer.MAX_VALUE) return -1;
        return minlength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        for (Integer ele : v) if (ele == null) throw new IllegalArgumentException();
        for (Integer ele : w) if (ele == null) throw new IllegalArgumentException();

        int minlength = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int ver : v) {
            for (int wer : w) {
                if (minlength > length(ver, wer)) {
                    minlength = length(ver, wer);
                    ancestor = ancestor(ver, wer);
                }
            }
        }
        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        Queue<Integer> iterable1 = new Queue<Integer>();
        Queue<Integer> iterable2 = new Queue<Integer>();
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            iterable1.enqueue(v);
            iterable2.enqueue(w);
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
        int length = sap.length(iterable1, iterable2);
        int ancestor = sap.ancestor(iterable1, iterable2);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
}
