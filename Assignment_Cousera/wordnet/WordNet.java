import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class WordNet {
    private int V;
    private BST<String, ArrayList<Integer>> Nouns;
    private ArrayList<String> id;
    private Digraph G;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        String[] str1 = (new In(synsets)).readAllLines();
        String[] str2 = (new In(hypernyms)).readAllLines();
        V = str1.length;
        G = new Digraph(V);
        id = new ArrayList<String>();
        Nouns = new BST<String, ArrayList<Integer>>();

        for (int i = 0; i < V; i++) {
            id.add((str1[i].split(","))[1]);
            String[] hyp = str2[i].split(",");
            for (int j = 1; j < hyp.length; j++) {
                G.addEdge(Integer.parseInt(hyp[0]), Integer.parseInt(hyp[j]));
            }
            for (String noun : (str1[i].split(","))[1].split(" ")) {
                if (Nouns.contains(noun)) {
                    ArrayList<Integer> index = Nouns.get(noun);
                    index.add(i);
                    Nouns.put(noun, index);
                }
                else {
                    ArrayList<Integer> index = new ArrayList<Integer>();
                    index.add(i);
                    Nouns.put(noun, index);
                }
            }

            /*********
             * Vấn đề là nhìn dòng 65-> 69: Aberdon khi cập nhật cuối cùng là dòng 69, nó vô tình làm thay đổi bfs
             * v nó có thể là điểm 66, 65, 67, và tr hợp ngắn nhất lại là 66 => bị sai
             */
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return Nouns.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return Nouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || (!isNoun(nounB))) throw new IllegalArgumentException();

        int minl = Integer.MAX_VALUE;
        for (int v : Nouns.get(nounA)) {
            for (int w : Nouns.get(nounB)) {
                BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
                BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);
                for (int i = 0; i < V; i++) {
                    if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
                        minl = Math.min(minl, bfsV.distTo(i) + bfsW.distTo(i));
                }
            }
        }
        return minl;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || (!isNoun(nounB))) throw new IllegalArgumentException();

        int minl = Integer.MAX_VALUE;
        int minAncestor = -1;
        for (int v : Nouns.get(nounA)) {
            for (int w : Nouns.get(nounB)) {
                BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
                BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);
                for (int i = 0; i < V; i++) {
                    if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
                        if (bfsV.distTo(i) + bfsW.distTo(i) < minl) {
                            minl = bfsV.distTo(i) + bfsW.distTo(i);
                            minAncestor = i;
                        }
                }
            }
        }
        return id.get(minAncestor);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordNet = new WordNet(args[0], args[1]);
        Iterable<String> nouns = wordNet.nouns();
        StdOut.println(nouns);
        StdOut.println("Type noun you want to search :");
        while (!StdIn.isEmpty()) {
            String danhtu = StdIn.readString();
            StdOut.println(danhtu + "is noun? " + wordNet.isNoun(danhtu));
        }
        String a = args[2];//"Aachen",
        String b = args[3];//"Aberdeen";
        // StdOut.println(wordNet.Nouns.get(a));
        // StdOut.println(wordNet.Nouns.get(b));
        StdOut.println(wordNet.distance(a, b));
        String ancent = wordNet.sap(a, b);
        StdOut.println(ancent);//+ wordNet.id.indexOf(ancent));
    }
}
