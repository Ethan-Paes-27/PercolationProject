// TestPercolates.java
// Tests for: percolates()
// Compile: javac-algs4 Percolation.java PercolationStats.java TestPercolates.java
// Run:     java-algs4 TestPercolates

public class TestPercolates {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        /*
        System.out.println("--- fresh and 1x1 grids ---");
        Percolation fresh = new Percolation(5);
        check("fresh 5x5 grid does not percolate", !fresh.percolates());

        Percolation one = new Percolation(1);
        one.open(1, 1);
        check("open 1x1 grid percolates", one.percolates());

        System.out.println("--- complete vertical path ---");
        Percolation column = new Percolation(5);
        for (int row = 1; row <= 5; row++) {
            column.open(row, 3);
        }
        check("open column 3 from top to bottom percolates", column.percolates());

        System.out.println("--- incomplete path ---");
        Percolation almost = new Percolation(5);
        for (int row = 1; row <= 4; row++) {
            almost.open(row, 2);
        }
        check("path missing its last site does not percolate", !almost.percolates());
        almost.open(5, 2);
        check("opening the last site makes the path percolate", almost.percolates());

        System.out.println("--- 2x2 connectivity cases ---");
        Percolation diagonal = new Percolation(2);
        diagonal.open(1, 1);
        diagonal.open(2, 2);
        check("diagonal 2x2 openings do not percolate", !diagonal.percolates());

        Percolation adjacent = new Percolation(2);
        adjacent.open(1, 1);
        adjacent.open(2, 1);
        check("vertically connected 2x2 openings percolate", adjacent.percolates());

         */
    }
}
