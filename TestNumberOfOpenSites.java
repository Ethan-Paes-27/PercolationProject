// TestNumberOfOpenSites.java
// Tests for: numberOfOpenSites()
// Compile: javac-algs4 Percolation.java PercolationStats.java TestNumberOfOpenSites.java
// Run:     java-algs4 TestNumberOfOpenSites

public class TestNumberOfOpenSites {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        System.out.println("--- initial count ---");
        Percolation fresh = new Percolation(5);
        check("fresh grid has zero open sites", fresh.numberOfOpenSites() == 0);

        System.out.println("--- distinct and repeated openings ---");
        Percolation grid = new Percolation(5);
        grid.open(1, 1);
        check("opening one site gives count 1", grid.numberOfOpenSites() == 1);
        grid.open(1, 1);
        check("opening the same site again keeps count at 1", grid.numberOfOpenSites() == 1);

        grid.open(2, 2);
        grid.open(3, 3);
        check("opening three distinct sites gives count 3", grid.numberOfOpenSites() == 3);

        System.out.println("--- all sites in a 3x3 grid ---");
        Percolation three = new Percolation(3);
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                three.open(row, col);
            }
        }
        check("opening all 9 sites gives count 9", three.numberOfOpenSites() == 9);

        System.out.println("--- 1x1 grid ---");
        Percolation one = new Percolation(1);
        one.open(1, 1);
        check("opening the only 1x1 site gives count 1", one.numberOfOpenSites() == 1);
    }
}
