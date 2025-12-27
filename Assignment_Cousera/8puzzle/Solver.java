/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solver {
    private static class Node {
        private final Board board;
        private final int moves, manhattan;
        private final Node previous;

        private Node(Board board1, int move, Node previous1) {
            board = board1;
            previous = previous1;
            moves = move;
            manhattan = board.manhattan() + moves;
        }
    }

    private static class ManhattanPriority implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return n1.manhattan - n2.manhattan;
        }
    }

    // private ArrayList<Board> previous; private ArrayList<Board> current;
    private Node currentNode, currentTwinNode;
    private ArrayList<Board> road;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        road = new ArrayList<Board>();
        currentNode = new Node(initial, 0, null);
        currentTwinNode = new Node(initial.twin(), 0, null);
        solve();
    }

    private void solve() {
        MinPQ<Node> pq = new MinPQ<Node>(new ManhattanPriority());
        MinPQ<Node> pqTwin = new MinPQ<Node>(new ManhattanPriority());

        pq.insert(currentNode);
        pqTwin.insert(currentTwinNode);
        // Khi nào mà ta tìm thấy Node có điểm ưu tiên nhó nhất và Node đó có Board là isGoal() thì dừng
        // Ta đang xét cả Board và Board đã đổi chỗ (twin())
        // Khi pq hoặc pqTwin tìm được ra isGoal() thì phải dừng
        while (!currentNode.board.isGoal() && !currentTwinNode.board.isGoal()) {
            currentNode = pq.delMin();
            currentTwinNode = pqTwin.delMin();

            for (Board nei : currentNode.board.neighbors()) {
                if (currentNode.previous == null || !currentNode.previous.board.equals(nei)) {
                    pq.insert(new Node(nei, currentNode.moves + 1, currentNode));
                }
            }
            for (Board nei : currentTwinNode.board.neighbors()) {
                if (currentTwinNode.previous == null
                        || !currentTwinNode.previous.board.equals(nei)) {
                    pqTwin.insert(new Node(nei, currentTwinNode.moves + 1, currentTwinNode));
                }
            }
        }
        if (currentNode.board.isGoal() && !currentTwinNode.board.isGoal()) {
            while (currentNode != null) {
                road.add(currentNode.board);
                currentNode = currentNode.previous;
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return road.size() > 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return road.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Collections.reverse(road);
        return road;
    }


    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);
        // StdOut.println(solver.last);
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
