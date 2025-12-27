/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private Percolation per;
    private double[] x;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.T = trials;
        x = new double[trials];
        for (int numeric = 0; numeric < T; numeric++) {
            double numberOfopen = 0;
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int vt = StdRandom.uniformInt(0, n * n);
                if (!percolation.isOpen(vt / n + 1, vt % n + 1)) {
                    numberOfopen += 1;
                    percolation.open(vt / n + 1, vt % n + 1);
                }
            }
            x[numeric] = numberOfopen / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(x.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(x.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]),
                                                                 Integer.parseInt(args[1]));
        StdOut.printf("mean                    = %f\n", percolationStats.mean());
        StdOut.printf("stddev                  = %f\n", percolationStats.stddev());
        StdOut.print("95% " + "confidence interval = [" + percolationStats.confidenceLo() + ","
                             + percolationStats.confidenceHi() + "]\n");
    }
}
