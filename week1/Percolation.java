import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int[][] sites;
    private int n;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n*n+2);
        this.sites = new int[n][n];
        for (int[] a : sites) Arrays.fill(a, 0);
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();
        return sites[row-1][col-1] == 1;
    }

    public boolean isFull(int row, int col) {
        if (0 < row && row <= n && 0 < col && col <= n)
            return uf.connected(0, (n*(row-1))+col);
        else
            throw new IllegalArgumentException();
    }

    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();

        if (isOpen(row, col))
            return;

        if (row > 1 && isOpen(row-1, col))
            uf.union((n*(row-1))+col, (n*(row-2))+col);

        if (row < n && isOpen(row+1, col))
            uf.union((n*(row-1))+col, (n*(row))+col);

        if (col > 1 && isOpen(row, col-1))
            uf.union((n*(row-1))+col, (n*(row-1))+col-1);

        if (col < n && isOpen(row, col+1))
            uf.union((n*(row-1))+col, (n*(row-1))+col+1);

        //Union virtual vertex
        if (row == 1)
            uf.union(0, (n*(row-1))+col);
        else if (row == n)
            uf.union((n*n+1), (n*(row-1))+col);

        sites[row-1][col-1] = 1;
    }


    public boolean percolates() {
        return uf.connected(0, (n*n)+1);
    }

}
