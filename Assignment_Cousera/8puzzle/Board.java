/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Board {
    private int x;
    private int y;
    // private final int[][] goal;
    private final int[][] Tiles;
    private final int Dimension;
    private final int Hamming;
    private final int Manhattan;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        Dimension = tiles.length;
        Tiles = new int[Dimension][Dimension];
        // goal = new int[Dimension][Dimension];
        for (int i = 0; i < Dimension; i++) {
            for (int j = 0; j < Dimension; j++) {
                Tiles[i][j] = tiles[i][j];
                // goal[i][j] = i * Dimension + j + 1;
            }
        }
        // goal[Dimension - 1][Dimension - 1] = 0;
        Manhattan = Calcmanhattan();
        Hamming = Calchamming();
        find0index();
    }

    // string representation of this board
    public String toString() {
        String output = Dimension + "\n";
        for (int i = 0; i < Dimension; i++) {
            for (int j = 0; j < Dimension; j++) {
                output += " " + Tiles[i][j];
            }
            output += "\n";
        }
        return output;
    }

    // board dimension n
    public int dimension() {
        return Dimension;
    }

    // number of tiles out of place
    public int hamming() {
        return Hamming;
    }

    public int manhattan() {
        return Manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int[][] goal = new int[Dimension][Dimension];
        for (int i = 0; i < Dimension; i++) {
            for (int j = 0; j < Dimension; j++) {
                goal[i][j] = i * Dimension + j + 1;
            }
        }
        goal[Dimension - 1][Dimension - 1] = 0;
        return Arrays.deepEquals(Tiles, goal);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.Dimension != this.Dimension) return false;
        return Arrays.deepEquals(this.Tiles, that.Tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> q = new Queue<Board>();
        int row = x, col = y;
        if (row - 1 >= 0) {
            Tiles[row][col] = Tiles[row - 1][col];
            Tiles[row - 1][col] = 0;
            q.enqueue(new Board(Tiles));
            Tiles[row - 1][col] = Tiles[row][col];
            Tiles[row][col] = 0;
        }
        if (row + 1 < Dimension) {
            Tiles[row][col] = Tiles[row + 1][col];
            Tiles[row + 1][col] = 0;
            q.enqueue(new Board(Tiles));
            Tiles[row + 1][col] = Tiles[row][col];
            Tiles[row][col] = 0;
        }
        if (col - 1 >= 0) {
            Tiles[row][col] = Tiles[row][col - 1];
            Tiles[row][col - 1] = 0;
            q.enqueue(new Board(Tiles));
            Tiles[row][col - 1] = Tiles[row][col];
            Tiles[row][col] = 0;
        }
        if (col + 1 < Dimension) {
            Tiles[row][col] = Tiles[row][col + 1];
            Tiles[row][col + 1] = 0;
            q.enqueue(new Board(Tiles));
            Tiles[row][col + 1] = Tiles[row][col];
            Tiles[row][col] = 0;
        }
        return q;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board board = null;
        int row = x, col = y;
        if (col > 0 /*&& Tiles[row][col - 1] != 0*/) {
            int exch = Tiles[row][col];
            Tiles[row][col] = Tiles[row][col - 1];
            Tiles[row][col - 1] = exch;
            board = new Board(this.Tiles);
            Tiles[row][col - 1] = Tiles[row][col];
            Tiles[row][col] = exch;

        }
        else if (col < Dimension - 1 /*&& Tiles[row][col + 1] != 0*/) {
            int exch = Tiles[row][col];
            Tiles[row][col] = Tiles[row][col + 1];
            Tiles[row][col + 1] = exch;
            board = new Board(this.Tiles);
            Tiles[row][col + 1] = Tiles[row][col];
            Tiles[row][col] = exch;
        }
        else if (row < Dimension - 1 /*&& Tiles[row + 1][col] != 0*/) {
            int exch = Tiles[row][col];
            Tiles[row][col] = Tiles[row + 1][col];
            Tiles[row + 1][col] = exch;
            board = new Board(this.Tiles);
            Tiles[row + 1][col] = Tiles[row][col];
            Tiles[row][col] = exch;
        }
        else if (row > 0 /*&& Tiles[row - 1][col] != 0*/) {
            int exch = Tiles[row][col];
            Tiles[row][col] = Tiles[row - 1][col];
            Tiles[row - 1][col] = exch;
            board = new Board(this.Tiles);
            Tiles[row - 1][col] = Tiles[row][col];
            Tiles[row][col] = exch;
        }
        return board;
    }

    private void find0index() {
        for (int i = 0; i < Dimension; i++) {
            for (int j = 0; j < Dimension; j++) {
                if (Tiles[i][j] == 0) {
                    x = i;
                    y = j;
                    return;
                }
            }
        }
    }

    private int Calchamming() {
        int HammingDistance = 0;
        for (int i = 0; i < Dimension; i++) {
            for (int j = 0; j < Dimension; j++) {
                if (Tiles[i][j] != 0 && Tiles[i][j] != i * Dimension + j + 1) HammingDistance++;
            }
        }
        return HammingDistance;
    }

    // sum of Manhattan distances between tiles and goal
    private int Calcmanhattan() {
        int ManhattanDistance = 0;
        for (int i = 0; i < Dimension; i++) {
            for (int j = 0; j < Dimension; j++) {
                if (Tiles[i][j] != 0) {
                    if (Tiles[i][j] % Dimension == 0) {
                        ManhattanDistance += Math.abs(Tiles[i][j] / Dimension - 1 - i) + Math.abs(
                                Dimension - 1 - j);
                    }
                    else {
                        ManhattanDistance += Math.abs(Tiles[i][j] / Dimension - i) + Math.abs(
                                Tiles[i][j] % Dimension - 1 - j);
                    }
                }
            }
        }
        return ManhattanDistance;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) matrix[i][j] = in.readInt();
        }
        Board b1 = new Board(matrix);
        in = new In(args[1]);
        n = in.readInt();
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) matrix[i][j] = in.readInt();
        }
        Board b2 = new Board(matrix);
        StdOut.println("Ma tran b1:");
        StdOut.println(b1 + " " + b1.isGoal() + " " + b1.Hamming + " " + b1.Manhattan);

        StdOut.println("Ma tran b2:");
        StdOut.println(b2 + " " + b2.isGoal() + " " + b2.Hamming + " " + b2.Manhattan);
        StdOut.println(b1.equals(b2));
        for (Board nei : b2.neighbors())
            StdOut.print(nei);
    }
}
