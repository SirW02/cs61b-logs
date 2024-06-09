package hw2;

import static edu.princeton.cs.introcs.StdRandom.uniform;

import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private PercolationFactory pf;
    private Percolation pc;
    private double[] fraction;

    public PercolationStats(int N, int T, PercolationFactory pf) {

        this.N = N;
        this.T = T;
        this.pf = pf;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        fraction = new double[T];

        for (int i = 0; i < T; i++) {
            pc = pf.make(N);
            while (!pc.percolates()) {
                int x, y;
                x = uniform(N);
                y = uniform(N);
                pc.open(x, y);
            }
            fraction[i] = (double) pc.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(fraction);
    }

    public double stddev() {
        return StdStats.stddev(fraction);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

//     public static void main(String[] args) {
//     PercolationStats ps = new PercolationStats(23, -1, new PercolationFactory());
//     System.out.println(ps.mean());
//     System.out.println(ps.stddev());
//     }
}
