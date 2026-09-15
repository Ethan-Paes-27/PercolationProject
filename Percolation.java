import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[] openSites;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF fullnessUf;
    private final int virtualTop;
    private final int virtualBottom;
    private int openCount;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        this.openSites = new boolean[n * n + 1];
        this.virtualTop = 0;
        this.virtualBottom = n * n + 1;
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.fullnessUf = new WeightedQuickUnionUF(n * n + 1);
        this.openCount = 0;
    }

    public void open(int row, int col) {
        validate(row, col);
        int site = index(row, col);

        if (openSites[site]) {
            return;
        }

        openSites[site] = true;
        openCount++;

        if (row == 1) {
            uf.union(virtualTop, site);
            fullnessUf.union(virtualTop, site);
        }
        if (row == n) {
            uf.union(virtualBottom, site);
        }

        connectIfOpen(row, col, row - 1, col);
        connectIfOpen(row, col, row + 1, col);
        connectIfOpen(row, col, row, col - 1);
        connectIfOpen(row, col, row, col + 1);
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col)
                && fullnessUf.find(virtualTop) == fullnessUf.find(index(row, col));
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    private void connectIfOpen(int row, int col, int neighborRow, int neighborCol) {
        if (neighborRow < 1 || neighborRow > n || neighborCol < 1 || neighborCol > n) {
            return;
        }

        int site = index(row, col);
        int neighbor = index(neighborRow, neighborCol);
        if (openSites[neighbor]) {
            uf.union(site, neighbor);
            fullnessUf.union(site, neighbor);
        }
    }

    private int index(int row, int col) {
        return (row - 1) * n + col;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        }
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(3, 2);
        System.out.println("Percolates: " + percolation.percolates());
    }
}
