package week1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import week1.Percolation;

public class PercolationStats {
    private double[] probabilities;
    private int trials;
    private Percolation p;
    private int n;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        //Initialize all necessary variables
        this.trials = trials;
        this.n = n;
        probabilities = new double[trials];
        int numOpen = 0;
        int chooseRow = 0;
        int chooseCol = 0;

        //start trials
        for (int i = 0; i < trials; i++) {
            numOpen = 0;
            p = new Percolation(n);
            while(!p.percolates()) {
                //get random nums
                chooseRow = StdRandom.uniform(1, 1+n);
                chooseCol = StdRandom.uniform(1, 1+n);
                if (!p.isOpen(chooseRow, chooseCol)) {
                    numOpen += 1;
                    p.open(chooseRow, chooseCol);
                }
            }


            //push probability
            probabilities[i] = ((double)numOpen / (n*n));
            System.out.println(probabilities[i]);

            //reset variables
        }
    }

    public double mean() {
        return StdStats.mean(probabilities);
    }

    public double stddev() {
        return StdStats.stddev(probabilities);
    }

    public double confidenceLo() {
        return (mean()) - ( (1.96 * stddev()) / Math.sqrt(trials) );
    }

    public double confidenceHi() {
        return (mean()) + ( (1.96 * stddev()) / Math.sqrt(trials) );
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
