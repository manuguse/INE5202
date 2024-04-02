import static java.lang.Math.*;

public class RootFinderMethods {

    private static void printResult(double result, int iterations, long elapsedTime) {
        System.out.printf("iterations: %d\nresult: %.10f\nelapsed time: %d ns\n", iterations, result, elapsedTime);
    }
    private static double equation(double x) {
        // return exp(x) * sin(x) - 1;
        return exp(x) - 2*cos(x);
    }

    private static double derivative(double x) {
        // return exp(x) * (sin(x) + cos(x));
        return exp(x) + 2*sin(x);
    }

    private static double secantEquation(double x0, double x1) {
        return (x1 - x0) / (equation(x1) - equation(x0));
    }

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

    public static void regulaFalsiMethod(double a, double b, double precision) {
        long start = System.nanoTime();
        int iterations = 0;
        double xk = a - (equation(a) * (b - a)) / (equation(b) - equation(a));
        while (abs(equation(xk)) > precision) {
            iterations++;
            if (equation(xk) > 0) {
                b = xk;
            } else {
                a = xk;
            }
            xk = (a * equation(b) - b * equation(a)) / (equation(b) - equation(a));
        }
        printResult(xk, iterations, System.nanoTime() - start);
    }

    // para o xk precisamos de algo perto da raiz ≥ como fazer isso?
    // para saber que está convergindo, devemos ver f(xk) diminuindo
    public static void newtonMethod(double x0, double precision) {
        long start = System.nanoTime();
        int iterations = 0;
        double xk = x0 - equation(x0) / derivative(x0);
        while (abs(equation(xk)) > precision) {
            iterations++;
            xk = xk - equation(xk) / derivative(xk);
        }
        printResult(xk, iterations, System.nanoTime() - start);
    }

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
}
