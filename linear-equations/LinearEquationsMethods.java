import Utils.Utils;

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
    public static void gaussianEliminationMethod(double[][] matrix, double[] b) { // método direto

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
        long finishTime = System.nanoTime() - start;
        Utils.printResults(b, n, iterations, finishTime);

    }

    /**
     * This method implements the Gaussian Elimination method for solving linear equations.
     * It modifies the input matrix and vector in-place to find the solution.
     * The method uses partial pivoting to avoid division by zero and reduce errors.
     *
     * @param matrix The matrix of coefficients for the linear equations. It should be a square matrix.
     * @param b      The vector of constant terms in the linear equations.
     */
    public static void gaussianEliminationPartialPivoting(double[][] matrix, double[] b) { // método direto
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

    public static void GaussianEliminationMethod(double[][] matrix, double[] b) { // método direto
        // TODO: implementar o método de eliminação gaussiana com pivoteamento parcial sem troca física de linhas
    }

    public static void LUDecompositionMethod(double[][] a, double[] b) { //

        int n = b.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        int iterations = 0;

        // Decomposição LU
        for (int k = 0; k < n; k++) {
            for (int i = k; i < n; i++) {
                iterations++;
                double soma = 0;
                for (int t = 0; t < k; t++) {
                    soma += L[i][t] * U[t][k];
                }
                L[i][k] = a[i][k] - soma;
            }
            U[k][k] = 1;
            for (int j = k + 1; j < n; j++) {
                iterations++;
                double soma = 0;
                for (int t = 0; t < k; t++) {
                    soma += L[k][t] * U[t][j];
                }
                U[k][j] = (a[k][j] - soma) / L[k][k];
            }
        }

        // Resolvendo o sistema LU
        // Substituição direta
        double[] y = new double[n];
        y[0] = b[0] / L[0][0];
        for (int i = 1; i < n; i++) {
            double soma = 0;
            for (int j = 0; j < i; j++) {
                soma += L[i][j] * y[j];
            }
            y[i] = (b[i] - soma) / L[i][i];
        }

        // Retrosubstituição
        double[] x = new double[n];
        for (int i = n - 2; i >= 0; i--) {
            double soma = 0;
            for (int j = i + 1; j < n; j++) {
                iterations++;
                soma += U[i][j] * x[j];
            }
            x[i] = y[i] - soma;
        }

        // Resíduo
        // x(n)=y(n)/U(n,n);

        double[] residual = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                iterations++;
                sum += a[i][j] * x[j]; // Multiplicação da matriz A pelos valores de x
            }
            residual[i] = Math.abs(b[i] - sum); // Subtração dos resultados de b e Ax
        }
        printResidual(residual);
        Utils.printResults(x, n, iterations, 0);

    }

    private static void printResidual(double[] residual) {
        System.out.println("Resíduo: ");
        for (double r : residual) {
            System.out.printf("%.11f\n", r);
        }
    }

    /**
     * This method implements the Jacobi method for solving linear equations.
     * It uses an iterative approach to find the solution.
     * The method uses the updated values to calculate the new values.
     * The method stops when the error is less than the tolerance.
     *
     * @param matrix    The matrix of coefficients for the linear equations. It should be a square matrix.
     * @param b         The vector of constant terms in the linear equations.
     * @param tolerance The maximum error allowed for the solution.
     */
    public static void gaussJacobiMethod(double[][] matrix, double[] b, double tolerance) { // método iterativo

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

        long finishTime = System.nanoTime() - start;
        Utils.printResults(x, n, iterations, finishTime);
        System.out.println("error: " + error + "\n");
    }

    /**
     * This method implements the Gauss-Seidel method for solving linear equations.
     * It modifies the input matrix and vector in-place to find the solution.
     * The method uses partial pivoting to avoid division by zero and reduce errors.
     * This method is more efficient than the Jacobi method because it uses the updated values
     *
     * @param matrix    The matrix of coefficients for the linear equations. It should be a square matrix.
     *                  The matrix should be diagonally dominant to ensure convergence.
     * @param b         The vector of constant terms in the linear equations.
     * @param tolerance The maximum error allowed for the solution.
     */
    public static void gaussSeidelMethod(double[][] matrix, double[] b, double tolerance) { // método iterativo

        double error = tolerance + 1;    // inicializa o erro com um valor maior que a tolerância
        int n = b.length;                // número de equações
        double[] x = new double[n];      // vetor de soluções
        double oldX = 0;                 // valor do x antigo
        int iterations = 0;              // contador de iterações
        long start = System.nanoTime();  // contador de tempo

        while (error > tolerance && iterations < 1000) {
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

        long finishTime = System.nanoTime() - start;
        Utils.printResults(x, n, iterations, finishTime);
        System.out.println("error: " + error + "\n");
    }

    /**
     * This method implements the Successive Over-Relaxation method for solving linear equations.
     * It modifies the input matrix and vector in-place to find the solution.
     * The method stops when the error is less than the tolerance.
     *
     * @param matrix    The matrix of coefficients for the linear equations. It should be a square matrix.
     *                  The matrix should be diagonally dominant to ensure convergence.
     * @param b         The vector of constant terms in the linear equations.
     * @param omega     The relaxation factor for the method. It should be between 0 and 2.
     *                  A value of 1 corresponds to the Gauss-Seidel method.
     * @param tolerance The maximum error allowed for the solution.
     **/
    public static void successiveOverRelaxationMethod(double[][] matrix, double[] b, double omega, double tolerance) { // método iterativo
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
                x[i] = (1 - omega) * x[i] + omega * ((b[i] - sum) / matrix[i][i]); // calcula a nova solução
                oldX = x[i];
            }
            error = 0;
            for (int i = 0; i < n; i++) {           // fazemos a soma dos erros
                error += Math.abs(x[i] - oldX);     // somamos o erro absoluto
            }
            iterations++;
        }
        long finishTime = System.nanoTime() - start;

        Utils.printResults(x, n, iterations, finishTime);
        System.out.println("error: " + error + "\n");
    }

    /**
     * This method implements the Band Type Matrix method for solving linear equations.
     * It modifies the input matrix and vector in-place to find the solution.
     * The method stops when the error is less than the tolerance.
     *
     * @param matrix The matrix of coefficients for the linear equations.
     *               It should have elements only in the diagonal and the first upper and lower diagonals.
     * @param sol    The vector of constant terms in the linear equations.
     **/
    public static void triDiagonalMatrixAlgorithm(double[][] matrix, double[] sol) { // método direto
        // TODO: arrumar, ainda não está funcionando
        int iterations = 0;
        int n = sol.length;    // número de equações

        double[] d = new double[n];      // vetor de d1 a d(n-1)
        double[] r = new double[n];        // vetor de r1 a rn
        double[] t = new double[n];      // vetor de t2 a tn
        double[] b = new double[n];        // vetor de b1 a bn

        long start = System.nanoTime();  // contador de tempo

        // inicializa os vetores
        for (int i = 0; i < n; i++) {
            d[i] = matrix[i][i];
            b[i] = sol[i];
            r[i] = i == 0 ? matrix[i][i + 1] : matrix[i][i - 1];
            t[i] = i == n - 1 ? matrix[i][i - 1] : matrix[i][i + 1];
            iterations++;
        }

        // substituição retroativa
        b[n - 1] /= d[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            b[i] = (b[i] - t[i] * b[i + 1]) / d[i];
            iterations++;
        }
        long finishTime = System.nanoTime() - start;
        Utils.printResults(b, n, iterations, finishTime);

    }
}