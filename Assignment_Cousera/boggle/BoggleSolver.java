/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashSet;

public class BoggleSolver {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Node root;

    private final static class Node {
        private boolean exist;
        private Node[] next = new Node[26];

    }

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        root = null;
        init(dictionary);
    }

    private void init(String[] dictionary) {
        for (String key : dictionary) {
            root = put(root, key.toUpperCase(), 0);
        }
    }

    private Node put(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.exist = true;
            return x;
            // return x;
        }
        if (!x.exist) x.exist = false;
        int c = ALPHABET.indexOf(key.charAt(d));
        x.next[c] = put(x.next[c], key, d + 1);
        return x;
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        HashSet<String> allValidWords = new HashSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                allValidWords.addAll(DFS(board, i, j));
            }
        }
        return allValidWords;
    }

    private ArrayList<String> DFS(BoggleBoard board, int i, int j) {
        ArrayList<String> validWords = new ArrayList<>();
        String word = "";
        int row = board.rows(), col = board.cols();
        boolean[] marked = new boolean[board.rows() * board.cols()];
        marked[i * col + j] = true;

        if (board.getLetter(i, j) != 'Q')
            word += board.getLetter(i, j);
        else word += "QU";

        Try(board, validWords, word, marked, row, col, i * col + j);
        return validWords;
    }

    private void Try(BoggleBoard board, ArrayList<String> validWords, String word,
                     boolean[] marked, int row, int col, int u) {
        boolean[] check = get(root, word, 0);
        if (check[0] && word.length() >= 3) validWords.add(word);
        if (check[1]) {
            for (int v : adj(u, row, col)) {
                if (!marked[v]) {
                    marked[v] = true;
                    if (board.getLetter(v / col, v % col) != 'Q')
                        word += board.getLetter(v / col, v % col);
                    else word += "QU";

                    Try(board, validWords, word, marked, row, col, v);

                    marked[v] = false;
                    if (board.getLetter(v / col, v % col) != 'Q')
                        word = word.substring(0, word.length() - 1);
                    else word = word.substring(0, word.length() - 2);
                }
            }
        }
    }

    private boolean[] get(Node x, String key, int d) {
        if (x == null) return new boolean[] { false, false };
        if (d == key.length()) return new boolean[] { x.exist, true };
        int c = ALPHABET.indexOf(key.charAt(d));
        return get(x.next[c], key, d + 1);
    }

    private ArrayList<Integer> adj(int u, int row, int col) {
        int i = u / col, j = u % col;
        ArrayList<Integer> adj = new ArrayList<>();
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int j1 = j - 1; j1 <= j + 1; j1++) {
                if (i1 >= 0 && i1 < row && j1 >= 0 && j1 < col && (i1 * col + j1) != u)
                    adj.add(i1 * col + j1);
            }
        }
        return adj;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (get(root, word, 0)[0] && word.length() >= 3)
            return word.length();
        return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
        // for (int neibor : solver.adj(3, 4, 4))
        //    StdOut.print(neibor + " ");
    }
}
