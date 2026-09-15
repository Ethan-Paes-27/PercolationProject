// TestIsOpen.java
// Tests for: isOpen(int row, int col)
// Compile: javac-algs4 Percolation.java PercolationStats.java TestIsOpen.java
// Run:     java-algs4 TestIsOpen

public class TestIsOpen {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        System.out.println("--- fresh grid ---");
        Percolation fresh = new Percolation(5);
        boolean allClosed = true;
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 5; col++) {
                allClosed = allClosed && !fresh.isOpen(row, col);
            }
        }
        check("all sites are closed on a fresh 5x5 grid", allClosed);

        System.out.println("--- state after opening a site ---");
        Percolation grid = new Percolation(5);
        grid.open(2, 3);
        check("isOpen(2,3) is true after opening", grid.isOpen(2, 3));
        check("isOpen(2,4) remains false", !grid.isOpen(2, 4));

        System.out.println("--- out-of-bounds arguments ---");
        boolean rowZeroThrows = false;
        try {
            grid.isOpen(0, 1);
        } catch (IllegalArgumentException e) {
            rowZeroThrows = true;
        }
        check("isOpen(0,1) throws IllegalArgumentException", rowZeroThrows);

        boolean colZeroThrows = false;
        try {
            grid.isOpen(1, 0);
        } catch (IllegalArgumentException e) {
            colZeroThrows = true;
        }
        check("isOpen(1,0) throws IllegalArgumentException", colZeroThrows);

        boolean rowSixThrows = false;
        try {
            grid.isOpen(6, 1);
        } catch (IllegalArgumentException e) {
            rowSixThrows = true;
        }
        check("isOpen(6,1) on 5x5 throws IllegalArgumentException", rowSixThrows);

        boolean colSixThrows = false;
        try {
            grid.isOpen(1, 6);
        } catch (IllegalArgumentException e) {
            colSixThrows = true;
        }
        check("isOpen(1,6) on 5x5 throws IllegalArgumentException", colSixThrows);

        System.out.println("--- every cell after opening all sites ---");
        Percolation two = new Percolation(2);
        for (int row = 1; row <= 2; row++) {
            for (int col = 1; col <= 2; col++) {
                two.open(row, col);
            }
        }
        boolean allOpen = true;
        for (int row = 1; row <= 2; row++) {
            for (int col = 1; col <= 2; col++) {
                allOpen = allOpen && two.isOpen(row, col);
            }
        }
        check("every cell in a fully opened 2x2 grid is open", allOpen);
    }
}
