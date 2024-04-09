/**
 * This class contains methods for solving linear equations.
 */
public class LinearEquationsMethods {

    /**
     * This method implements the Gaussian Elimination method for solving linear equations.
     * It modifies the input matrix and vector in-place to find the solution.
     *
     * @param matrix The matrix of coefficients for the linear equations. It should be a square matrix.
     * @param b The vector of constant terms in the linear equations.
     * @param n The number of equations (and variables). It should be equal to the dimensions of the matrix and the length of the vector.
     */
    public static void gaussianEliminationMethod(double[][] matrix, double[] b, int n) { // método sem pivot

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double m = matrix[j][i] / matrix[i][i];
                for (int k = i; k < n; k++) {
                    matrix[j][k] -= m * matrix[i][k]; }
                b[j] -= m * b[i]; }}

        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * b[j];}
            b[i] = (b[i] - sum) / matrix[i][i]; }

        for (int i = 0; i < n; i++) {
            System.out.println("x" + i + " = " + b[i]); }
    }
}
