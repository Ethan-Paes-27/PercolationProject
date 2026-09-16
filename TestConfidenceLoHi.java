// TestConfidenceLoHi.java
// Tests for: confidenceLo() and confidenceHi()
// Compile: javac-algs4 Percolation.java PercolationStats.java TestConfidenceLoHi.java
// Run:     java-algs4 TestConfidenceLoHi

public class TestConfidenceLoHi {

    static void check(String label, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + ": " + label);
    }

    public static void main(String[] args) {
        /*System.out.println("--- mean is between lo and hi ---");
        PercolationStats large = new PercolationStats(200, 1000);
        double lo = large.confidenceLo();
        double hi = large.confidenceHi();
        double mean = large.mean();
        check("confidenceLo < mean for (200,1000)", lo < mean);
        check("mean < confidenceHi for (200,1000)", mean < hi);

        System.out.println("--- bounds are in [0, 1] ---");
        check("confidenceLo >= 0.0 for (200,1000)", lo >= 0.0);
        check("confidenceHi <= 1.0 for (200,1000)", hi <= 1.0);

        System.out.println("--- lo is strictly less than hi ---");
        PercolationStats ten = new PercolationStats(10, 50);
        check("confidenceLo < confidenceHi for (10,50)",
              ten.confidenceLo() < ten.confidenceHi());

        System.out.println("--- single trial produces NaN interval ---");
        PercolationStats oneTrial = new PercolationStats(10, 1);
        check("confidenceLo is NaN when trials=1", Double.isNaN(oneTrial.confidenceLo()));
        check("confidenceHi is NaN when trials=1", Double.isNaN(oneTrial.confidenceHi()));

        System.out.println("--- interval shrinks as trials increases ---");
        PercolationStats fewTrials = new PercolationStats(50, 10);
        PercolationStats manyTrials = new PercolationStats(50, 1000);
        double widthFew  = fewTrials.confidenceHi()  - fewTrials.confidenceLo();
        double widthMany = manyTrials.confidenceHi() - manyTrials.confidenceLo();
        check("interval for (50,1000) is narrower than for (50,10)", widthMany < widthFew);

         */
    }
}
