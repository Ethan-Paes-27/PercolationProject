// TestStddev.java
// Tests for: stddev()
// Compile: javac-algs4 Percolation.java PercolationStats.java TestStddev.java
// Run:     java-algs4 TestStddev

public class TestStddev {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        System.out.println("--- single trial returns NaN ---");
        PercolationStats oneTrial = new PercolationStats(10, 1);
        check("stddev with 1 trial is Double.NaN", Double.isNaN(oneTrial.stddev()));

        System.out.println("--- large grid stddev is small ---");
        PercolationStats large = new PercolationStats(200, 1000);
        check("stddev for (200,1000) is less than 0.05", large.stddev() < 0.05);

        System.out.println("--- stddev is non-negative ---");
        PercolationStats ten = new PercolationStats(10, 50);
        check("stddev for (10,50) is >= 0", ten.stddev() >= 0.0);

        PercolationStats fifty = new PercolationStats(50, 100);
        check("stddev for (50,100) is >= 0", fifty.stddev() >= 0.0);

        System.out.println("--- stddev is finite for trials > 1 ---");
        PercolationStats finite = new PercolationStats(20, 10);
        check("stddev for (20,10) is not NaN", !Double.isNaN(finite.stddev()));
        check("stddev for (20,10) is not Infinite", !Double.isInfinite(finite.stddev()));
    }
}
