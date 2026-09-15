// TestMean.java
// Tests for: mean()
// Compile: javac-algs4 Percolation.java PercolationStats.java TestMean.java
// Run:     java-algs4 TestMean

public class TestMean {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        System.out.println("--- exact 1x1 mean ---");
        PercolationStats one = new PercolationStats(1, 1);
        check("mean for 1x1 grid with 1 trial is 1.0", Math.abs(one.mean() - 1.0) < 0.001);

        System.out.println("--- large-grid threshold estimate ---");
        PercolationStats large = new PercolationStats(200, 1000);
        check("mean for 200x200 with 1000 trials is between 0.55 and 0.65",
              large.mean() > 0.55 && large.mean() < 0.65);

        System.out.println("--- mean range for other configurations ---");
        PercolationStats ten = new PercolationStats(10, 50);
        check("mean for (10,50) is between 0 and 1", ten.mean() >= 0.0 && ten.mean() <= 1.0);

        PercolationStats fifty = new PercolationStats(50, 100);
        check("mean for (50,100) is between 0 and 1", fifty.mean() >= 0.0 && fifty.mean() <= 1.0);
    }
}
