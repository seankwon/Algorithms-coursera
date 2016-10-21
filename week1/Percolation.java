import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;

public class Percolation {
    WeightedQuickUnionUF uf;
    public int[][] sites;
    public int N;

    public Percolation(int n) {
        this.N = n;
        this.uf = new WeightedQuickUnionUF(n*n+2);
        this.sites = new int[n][n];
        for (int[] a : sites) Arrays.fill(a, 0);
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();
        return sites[row-1][col-1] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();
        return !isOpen(row, col);
    }

    public void open(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();

        if (isOpen(row, col))
            return;

        if (row != 1)
            uf.union((N*(col-1))+row-1, (N*(col-1))+row-2);

        if (row != N)
            uf.union((N*(col-1))+row-1, (N*(col-1))+row);

        if (col != 1)
            uf.union((N*(col-1))+row, (N*(col-2))+row);

        if (col != N)
            uf.union((N*(col-1))+row, (N*col)+row);

        //Union virtual vertex
        if (col == 1)
            uf.union(0, (N*col)+row);
        else if (col == N)
            uf.union((N*N+1), (N*(col-1))+row);

        sites[row-1][col-1] = 1;
    }


    public boolean percolates() {
        return uf.find(0) == uf.find(N+1);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 1);

        if (p.isOpen(1, 1))
            System.out.println("Test 1 Pass");

        if (!p.isFull(1,1))
            System.out.println("Test 2 Pass");

        p.open(1, 2);
        p.open(1, 3);

        if (p.percolates())
            System.out.println("Test 3 Pass");

        Percolation v = new Percolation(4);
        v.open(2, 1);
        v.open(2, 2);
        v.open(3, 2);
        v.open(3, 3);
        v.open(4, 3);
        v.open(4, 4);
        if (v.percolates())
            System.out.println("Test 4 Pass");
    }
}
