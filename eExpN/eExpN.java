import static java.lang.Math.abs;

public class eExpN {

    /**
     * Calculates the exponential function e^x using the first five terms of the Taylor series.
     * @param x The exponent to which e is raised.
     * @return The approximate value of e^x.
     */
    public static double firstMode(int x) {
        double sum = 1;
        int fac = 1;
        int xn = 1;
        for (int i = 1; i < 5; i++) {
            xn *= x;
            fac *= i;
            sum += (double) xn /fac;
        }
        return sum;
    }

    /**
     * Calculates the exponential function e^x using a specified precision.
     * @param x The exponent to which e is raised.
     * @param p The precision to which the result should be calculated.
     * @return The approximate value of e^x.
     */
    public static double secondMode(int x, double p) {
        double sum = 1.0;
        double term = 1.0;
        double diff = p + 1;
        for (int i = 1; abs(term) > p && diff > p; i++) {
            term *= (double) x / i;
            double last = sum;
            sum += term;
            double actual = sum;
            diff = abs(actual - last);
        }
        return sum;
    }
}
