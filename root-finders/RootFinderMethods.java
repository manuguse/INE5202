import static java.lang.Math.*;

/**
 * This class contains methods for finding roots of equations using various numerical methods.
 */
public class RootFinderMethods {

    /**
     * Prints the result of a root-finding method.
     *
     * @param result      The root found by the method.
     * @param iterations  The number of iterations the method took to find the root.
     * @param elapsedTime The time taken by the method to find the root.
     */
    private static void printResult(double result, int iterations, long elapsedTime) {
        System.out.printf("iterations: %d\nresult: %.10f\nelapsed time: %d ns\n", iterations, result, elapsedTime);
    }

    private static double equation(double x) {
        return exp(x) * sin(x) - 1;
        // return exp(x) - 2 * cos(x);
    }

    private static double derivative(double x) {
        return exp(x) * (sin(x) + cos(x));
        // return exp(x) + 2 * sin(x);
    }

    private static double secantEquation(double x0, double x1) {
        return (x1 - x0) / (equation(x1) - equation(x0));
    }

    /**
     * The bisection method for finding roots.
     *
     * @param a         The lower bound of the interval.
     * @param b         The upper bound of the interval.
     * @param precision The desired precision.
     */
    public static void bisectionMethod(double a, double b, double precision) {
        long start = System.nanoTime();
        int iterations = 0;
        double xm = (a + b) / 2;
        while (abs(equation(xm)) > precision) {
            iterations++;
            if (equation(xm) > 0) {
                b = xm;
            } else {
                a = xm;
            }
            xm = (a + b) / 2;
        }
        printResult(xm, iterations, System.nanoTime() - start);
        // return xm;
    }

    /**
     * The Regula Falsi method for finding roots.
     *
     * @param a         The lower bound of the interval.
     * @param b         The upper bound of the interval.
     * @param precision The desired precision.
     */
    public static void regulaFalsiMethod(double a, double b, double precision) {
        long start = System.nanoTime();
        int iterations = 0;
        double fa = equation(a), fb = equation(b);
        double xk = a - (fa * (b - a)) / (fb - fa);
        double fxk = equation(xk);
        while (abs(fxk) > precision) {
            iterations++;
            if (fxk > 0) {
                b = xk;
                fb = fxk;
            } else {
                a = xk;
                fa = fxk;
            }
            xk = (a * fb - b * fa) / (fb - fa);
            fxk = equation(xk);
        }
        printResult(xk, iterations, System.nanoTime() - start);
    }

    /**
     * The modified Regula Falsi method for finding roots.
     *
     * @param a         The lower bound of the interval.
     * @param b         The upper bound of the interval.
     * @param precision The desired precision.
     */
    public static void modifiedRegulaFalsiMethod(double a, double b, double precision) {
        long start = System.nanoTime();
        int iterations = 0;
        double F = equation(a), G = equation(b);
        double an = a, bn = b, xn = 1;
        while (Math.abs(equation(xn)) > precision) {
            iterations++;
            xn = (an * G - bn * F) / (G - F);
            double functionAtXn = equation(xn);
            if (functionAtXn * F > 0) {
                an = xn;
                F = functionAtXn;
            } else {
                bn = xn;
                G = functionAtXn; }}
        printResult(xn, iterations, System.nanoTime() - start);
    }

    /**
     * The Newton's method for finding roots.
     *
     * @param x0        The initial guess.
     * @param precision The desired precision.
     */
    public static void newtonMethod(double x0, double precision) {
        long start = System.nanoTime();
        int iterations = 0;
        double fx0 = equation(x0), dfx0 = derivative(x0);
        double xk = x0 - fx0 / dfx0;
        double fxk = equation(xk);
        while (abs(equation(xk)) > precision) {
            iterations++;
            xk = xk - equation(xk) / derivative(xk);
        }
        printResult(xk, iterations, System.nanoTime() - start);
    }

    /**
     * The secant method for finding roots.
     *
     * @param a         The first initial guess.
     * @param b         The second initial guess.
     * @param precision The desired precision.
     */
    public static void secantMethod(double a, double b, double precision) {
        long start = System.nanoTime();
        int iterations = 0;
        double x0 = a, x1 = b;
        double xk;

        while (abs(equation(x1)) > precision) {
            iterations++;
            xk = x1 - secantEquation(x0, x1) * equation(x1);
            x0 = x1;
            x1 = xk;
        }
        printResult(x1, iterations, System.nanoTime() - start);
    }

    /**
     * The Birge-Vieta method for finding roots of polynomials.
     *
     * @param coefficients The coefficients of the polynomial.
     * @param precision    The desired precision.
     */
    public static void birgeVietaMethod(int[] coefficients, double precision) {
        long start = System.nanoTime();
        double b, c;
        double xk = 1, r = 1;
        int iterations = 0;
        while (abs(r) > precision) {
            iterations++;
            b = coefficients[0];
            c = coefficients[0];
            for (int i = 1; i < coefficients.length; i++) {
                b = b * xk + coefficients[i];
                if (i < coefficients.length - 1) {
                    c = c * xk + b;
                }
            }
            xk = xk - b / c;
            r = b;
        }
        printResult(xk, iterations, System.nanoTime() - start);
    }
}
