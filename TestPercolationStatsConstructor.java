// TestPercolationStatsConstructor.java
// Tests for: PercolationStats(int n, int trials) constructor
// Compile: javac-algs4 Percolation.java PercolationStats.java TestPercolationStatsConstructor.java
// Run:     java-algs4 TestPercolationStatsConstructor

public class TestPercolationStatsConstructor {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        /*
        System.out.println("--- valid constructor arguments ---");
        boolean oneByOneValid = true;
        try {
            new PercolationStats(1, 1);
        } catch (Exception e) {
            oneByOneValid = false;
        }
        check("new PercolationStats(1,1) does not throw", oneByOneValid);

        boolean largeValid = true;
        try {
            new PercolationStats(200, 100);
        } catch (Exception e) {
            largeValid = false;
        }
        check("new PercolationStats(200,100) does not throw", largeValid);

        boolean smallTrialsValid = true;
        try {
            new PercolationStats(5, 1);
        } catch (Exception e) {
            smallTrialsValid = false;
        }
        check("new PercolationStats(5,1) does not throw", smallTrialsValid);

        System.out.println("--- invalid constructor arguments ---");
        boolean zeroNThrows = false;
        try {
            new PercolationStats(0, 100);
        } catch (IllegalArgumentException e) {
            zeroNThrows = true;
        }
        check("n=0 throws IllegalArgumentException", zeroNThrows);

        boolean zeroTrialsThrows = false;
        try {
            new PercolationStats(100, 0);
        } catch (IllegalArgumentException e) {
            zeroTrialsThrows = true;
        }
        check("trials=0 throws IllegalArgumentException", zeroTrialsThrows);

        boolean negativeNThrows = false;
        try {
            new PercolationStats(-1, 100);
        } catch (IllegalArgumentException e) {
            negativeNThrows = true;
        }
        check("n=-1 throws IllegalArgumentException", negativeNThrows);

        boolean negativeTrialsThrows = false;
        try {
            new PercolationStats(100, -1);
        } catch (IllegalArgumentException e) {
            negativeTrialsThrows = true;
        }
        check("trials=-1 throws IllegalArgumentException", negativeTrialsThrows);

        boolean bothNegativeThrows = false;
        try {
            new PercolationStats(-1, -1);
        } catch (IllegalArgumentException e) {
            bothNegativeThrows = true;
        }
        check("n=-1, trials=-1 throws IllegalArgumentException", bothNegativeThrows);

         */
    }
}
