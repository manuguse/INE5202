/**
 * This class contains methods for solving linear equations.
 */
public class LinearEquationsMethods {

    /**
     * This method implements the Gaussian Elimination method for solving linear equations.
     * It modifies the input matrix and vector in-place to find the solution.
     * The method does not use pivoting, so it may fail if the matrix is singular or ill-conditioned.
     *
     * @param matrix The matrix of coefficients for the linear equations. It should be a square matrix.
     * @param b      The vector of constant terms in the linear equations.
     */
    public static void gaussianEliminationMethod(double[][] matrix, double[] b) {

        int n = b.length;
        int iterations = 0;
        long start = System.nanoTime();

        // escalonamento da matriz
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Utils.eliminateRow(matrix, b, n, i, j);
                iterations++;
            }
        }

        iterations = Utils.retroactiveReplacement(matrix, b, n, iterations);

        Utils.printResults(b, n, iterations, System.nanoTime() - start);

    }

    /**
     * This method implements the Gaussian Elimination method for solving linear equations.
     * It modifies the input matrix and vector in-place to find the solution.
     * The method uses partial pivoting to avoid division by zero and reduce errors.
     *
     * @param matrix The matrix of coefficients for the linear equations. It should be a square matrix.
     * @param b      The vector of constant terms in the linear equations.
     */
    public static void gaussianEliminationPartialPivoting(double[][] matrix, double[] b) {
        long start = System.nanoTime();
        int n = b.length;
        int iterations = 0;

        // alteração da ordem das linhas, deixando os maiores valores na diagonal
        for (int i = 0; i < n - 1; i++) {
            int pivot = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[pivot][i])) {
                    pivot = j;
                }
            }
            if (pivot != i) {
                double[] temp = matrix[i];
                matrix[i] = matrix[pivot];
                matrix[pivot] = temp;
                double t = b[i];
                b[i] = b[pivot];
                b[pivot] = t;
            }

            for (int j = i + 1; j < n; j++) {
                Utils.eliminateRow(matrix, b, n, i, j);
                iterations++;
            }
        }

        // substituição retroativa
        iterations = Utils.retroactiveReplacement(matrix, b, n, iterations);

        long endTime = System.nanoTime() - start;
        System.out.println("matriz escalonada:");
        Utils.printCurrentMatrix(matrix, b, n);
        Utils.printResults(b, n, iterations, endTime);

    }

    public static void GaussianEliminationMethod(double[][] matrix, double[] b) {
        // TODO: implementar o método de eliminação gaussiana com pivoteamento parcial sem troca física de linhas
    }

    public static void LUDecompositionMethod(double[][] matrix, double[] b) {
        // TODO: implementar o método de decomposição LU
    }

    /**
     * This method implements the Jacobi method for solving linear equations.
     * It uses an iterative approach to find the solution.
     * The method uses the updated values to calculate the new values.
     * The method stops when the error is less than the tolerance.
     * @param matrix    The matrix of coefficients for the linear equations. It should be a square matrix.
     * @param b         The vector of constant terms in the linear equations.
     * @param tolerance The maximum error allowed for the solution.
     */
    public static void gaussJacobiMethod(double[][] matrix, double[] b, double tolerance) {

        double error = tolerance + 1;    // inicializa o erro com um valor maior que a tolerância
        int n = b.length;                // número de equações
        double[] x = new double[n];      // vetor de soluções
        double[] xNew = new double[n];   // vetor de soluções atualizado
        int iterations = 0;              // contador de iterações
        long start = System.nanoTime();  // contador de tempo

        while (error > tolerance) {
            for (int i = 0; i < n; i++) {              // para cada linha
                double sum = 0;                        // cria uma variável para armazenar a soma
                for (int j = 0; j < n; j++) {          // para cada coluna
                    if (j != i) {                      // exceto a diagonal principal
                        sum += matrix[i][j] * x[j];      // soma os termos para calcular a nova solução
                    }
                }
                xNew[i] = (b[i] - sum) / matrix[i][i]; // calcula a nova solução
            }
            error = 0;
            for (int i = 0; i < n; i++) {           // fazemos a soma dos erros
                error += Math.abs(xNew[i] - x[i]);  // somamos o erro absoluto
                x[i] = xNew[i];                     // atualizamos o vetor de soluções
            }
            iterations++;
        }

        Utils.printResults(x, n, iterations, System.nanoTime() - start);
        System.out.println("error: " + error + "\n");
    }

    /**
     * This method implements the Gauss-Seidel method for solving linear equations.
     * It modifies the input matrix and vector in-place to find the solution.
     * The method uses partial pivoting to avoid division by zero and reduce errors.
     * This method is more efficient than the Jacobi method because it uses the updated values
     *
     * @param matrix    The matrix of coefficients for the linear equations. It should be a square matrix.
     * @param b         The vector of constant terms in the linear equations.
     * @param tolerance The maximum error allowed for the solution.
     */
    public static void gaussSeidelMethod(double[][] matrix, double[] b, double tolerance) {

        double error = tolerance + 1;    // inicializa o erro com um valor maior que a tolerância
        int n = b.length;                // número de equações
        double[] x = new double[n];      // vetor de soluções
        double oldX = 0;                 // valor do x antigo
        int iterations = 0;              // contador de iterações
        long start = System.nanoTime();  // contador de tempo

        while (error > tolerance) {
            for (int i = 0; i < n; i++) {              // para cada linha
                double sum = 0;                        // cria uma variável para armazenar a soma
                for (int j = 0; j < n; j++) {          // para cada coluna
                    if (j != i) {                      // exceto a diagonal principal
                        sum += matrix[i][j] * x[j];      // soma os termos para calcular a nova solução
                    }
                }
                x[i] = (b[i] - sum) / matrix[i][i]; // calcula a nova solução
                oldX = x[i];
            }
            error = 0;
            for (int i = 0; i < n; i++) {           // fazemos a soma dos erros
                error += Math.abs(x[i] - oldX);  // somamos o erro absoluto
            }
            iterations++;
        }

        Utils.printResults(x, n, iterations, System.nanoTime() - start);
        System.out.println("error: " + error + "\n");
    }





}