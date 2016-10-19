import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF uf;
    int[][] sites;
    int N;

    public Percolation(int n) {
        N = n;
        uf = new WeightedQuickUnionUF(n+2);
        sites = new int[n][n];
        for (int[] a : sites) Array.fill(a, 0);
    }
    public void open(int row, int col) {
        // col * row uf.union(row, row+1)
        // uf.union(row, )
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();

        sites[row][col] = 1;

        if (row != 1)
            uf.union(row-1, row);

        if (row != N)
            uf.union(row, row+1);

        if (col != 1)
            uf.union(col-1, col);

        if (col != N)
            uf.union(col, col+1);
    }

    public boolean isOpen(int row, int col) {
        return true;
    }

    public boolean isFull(int row, int col) {
        return true;
    }

    public boolean percolates() {
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Test");
    }
}
