// TestOpen.java
// Tests for: open(int row, int col)
// Compile: javac-algs4 Percolation.java PercolationStats.java TestOpen.java
// Run:     java-algs4 TestOpen

public class TestOpen {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        /*
        System.out.println("--- opening individual sites ---");
        Percolation grid = new Percolation(5);
        grid.open(1, 1);
        check("open(1,1) makes (1,1) open", grid.isOpen(1, 1));
        grid.open(1, 1);
        check("opening the same site twice keeps count at 1", grid.numberOfOpenSites() == 1);

        grid.open(5, 5);
        check("open(5,5) makes (5,5) open", grid.isOpen(5, 5));

        System.out.println("--- out-of-bounds arguments ---");
        boolean rowZeroThrows = false;
        try {
            grid.open(0, 1);
        } catch (IllegalArgumentException e) {
            rowZeroThrows = true;
        }
        check("open(0,1) throws IllegalArgumentException", rowZeroThrows);

        boolean colZeroThrows = false;
        try {
            grid.open(1, 0);
        } catch (IllegalArgumentException e) {
            colZeroThrows = true;
        }
        check("open(1,0) throws IllegalArgumentException", colZeroThrows);

        boolean rowSixThrows = false;
        try {
            grid.open(6, 1);
        } catch (IllegalArgumentException e) {
            rowSixThrows = true;
        }
        check("open(6,1) on 5x5 throws IllegalArgumentException", rowSixThrows);

        System.out.println("--- count behavior ---");
        Percolation one = new Percolation(1);
        one.open(1, 1);
        check("opening the only 1x1 site gives count 1", one.numberOfOpenSites() == 1);

        Percolation multiple = new Percolation(5);
        multiple.open(1, 1);
        check("one distinct site gives count 1", multiple.numberOfOpenSites() == 1);
        multiple.open(2, 2);
        multiple.open(3, 3);
        check("three distinct sites give count 3", multiple.numberOfOpenSites() == 3);

         */
    }
}
