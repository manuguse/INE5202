import static java.lang.Math.*;

public class RootFinderMethods {
    private static double equation(double x) {
        return exp(x)*sin(x) - 1;
    }
    public static void bisectionMethod(double a, double b, double precision) {
        int iterations = 0;
        double xm = (a + b)/2;
        while (abs(equation(xm)) > precision) {
            iterations++;
            if (equation(xm) > 0) {
                b = xm;
            } else {
                a = xm;
            }
            xm = (a + b)/2;
        }
        System.out.printf("iterations: %d\nresult: %f\n", iterations, xm);
        // return xm;
    }

}
