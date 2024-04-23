public class Utils {
    public static void printResults(double[] results, int n, int iterations, long time) {
        System.out.println("iterations: " + iterations);
        System.out.println("elapsed time: " + time + " ns\n");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.11f\n", i, results[i]);
        }
        System.out.println();
    }

    public static void printCurrentMatrix(double[][] matrix, double[] b, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%.3f ", matrix[i][j]);
            }
            System.out.printf("| %.3f\n", b[i]);
        }
        System.out.println();
    }

    public static int retroactiveReplacement(double[][] matrix, double[] b, int n, int iterations) {
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * b[j];
            }
            b[i] = (b[i] - sum) / matrix[i][i];
            iterations++;
        }
        return iterations;
    }

    public static void eliminateRow(double[][] matrix, double[] b, int n, int i, int j) {
        double m = matrix[j][i] / matrix[i][i];
        for (int k = i; k < n; k++) {
            matrix[j][k] -= m * matrix[i][k];
        }
        b[j] -= m * b[i];
    }
}
