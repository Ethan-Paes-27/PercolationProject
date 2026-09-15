// TestIsFull.java
// Tests for: isFull(int row, int col)
// Compile: javac-algs4 Percolation.java PercolationStats.java TestIsFull.java
// Run:     java-algs4 TestIsFull

public class TestIsFull {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        System.out.println("--- fresh grid and top-row behavior ---");
        Percolation fresh = new Percolation(5);
        check("isFull(1,1) is false on a fresh grid", !fresh.isFull(1, 1));

        Percolation top = new Percolation(5);
        top.open(1, 1);
        check("an open top-row site is full", top.isFull(1, 1));

        System.out.println("--- connection through the top ---");
        Percolation disconnected = new Percolation(5);
        disconnected.open(2, 1);
        check("(2,1) is not full without an open top connection", !disconnected.isFull(2, 1));

        Percolation connected = new Percolation(5);
        connected.open(1, 1);
        connected.open(2, 1);
        check("(2,1) is full through (1,1)", connected.isFull(2, 1));

        System.out.println("--- backwash prevention ---");
        Percolation backwash = new Percolation(3);
        backwash.open(1, 2);
        backwash.open(2, 2);
        backwash.open(3, 2);
        check("column 2 creates percolation", backwash.percolates());
        backwash.open(3, 1);
        check("bottom site outside the path is not full", !backwash.isFull(3, 1));

        System.out.println("--- out-of-bounds arguments ---");
        boolean rowZeroThrows = false;
        try {
            fresh.isFull(0, 1);
        } catch (IllegalArgumentException e) {
            rowZeroThrows = true;
        }
        check("isFull(0,1) throws IllegalArgumentException", rowZeroThrows);

        boolean rowSixThrows = false;
        try {
            fresh.isFull(6, 1);
        } catch (IllegalArgumentException e) {
            rowSixThrows = true;
        }
        check("isFull(6,1) on 5x5 throws IllegalArgumentException", rowSixThrows);
    }
}
