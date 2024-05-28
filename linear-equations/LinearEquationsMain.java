public class LinearEquationsMain {

    public static void main(String[] args) {

        System.out.println("gaussian elimination method");
        LinearEquationsMethods.gaussianEliminationMethod(new double[][]{
                {-0.421, 0.784, 0.279},
                {0.448, 0.832, 0.193},
                {0.421, 0.784, -0.207}
        }, new double[]{0, 1, 0});

        System.out.println("gaussian elimination method with partial pivoting");
        LinearEquationsMethods.gaussianEliminationPartialPivoting(new double[][]{
                        {1, 1, 1.5, 1, 1.5, 0, 0, 0, 0, 0},
                        {0, 1, 0.01, 0.51, 1.5, 0.5, 0, 0, 0, 0},
                        {2.9, 1, 2, 1, 1, 0, 5, 0, 0, 0},
                        {9, 1, 0.2, 1, 1, 0, 0, 1.5, 0, 0},
                        {1, 0, 2, 0, 0, 1, 1, 1, 0, 2},
                        {0, 1, 0, 0, -2, 0, 1, -1, 1, 1},
                        {1, 0, 2, 0, 0, 0, 1, 1, 1, 0},
                        {0, 1, 0, 0, 2, 0, 1, 1, 1, -1},
                        {0, 0, 1, 0, 2, 1, -1, 0, -1, -1},
                        {0, 1, 0, 0, 2, 0, 1, 0, 1, 1}
                },
                new double[]{4, -3, 1, -1, -1, 0, -1, 1, 3, -2});

        System.out.println("gauss-jacobi method");
        LinearEquationsMethods.gaussJacobiMethod(new double[][]{
                {3, -1, -1},
                {1, 3, 1},
                {2, -2, 4}
        }, new double[]{1, 5, 4}, 10e-11);

        System.out.println("gauss-seidel method");
        LinearEquationsMethods.gaussSeidelMethod(new double[][]{
                {3, -1, -1},
                {1, 3, 1},
                {2, -2, 4}
        }, new double[]{1, 5, 4}, 10e-11);

        System.out.println("successive over-relaxation method");
        LinearEquationsMethods.successiveOverRelaxationMethod(new double[][]{
                {3, -1, -1},
                {1, 3, 1},
                {2, -2, 4}
        }, new double[]{1, 5, 4}, 0.85, 10e-11); // vamos testar o omega até achar o valor ideal

        System.out.println("tridiagonal matrix algorithm");
        LinearEquationsMethods.triDiagonalMatrixAlgorithm(
                new double[][]{
                        new double[]{1, 1, 0, 0, 0},
                        new double[]{1, 1, 1, 0, 0},
                        new double[]{0, 1, 1, 1, 0},
                        new double[]{0, 0, 1, 1, 1},
                        new double[]{0, 0, 0, 1, 1}
                }, new double[]{1, 2, 3, 4, 5});

        System.out.println("lu decomposition method");
        LinearEquationsMethods.LUDecompositionMethod(new double[][]{
                {2, 1, 1, 0},
                {4, 3, 3, 1},
                {8, 7, 9, 5},
                {6, 7, 9, 8}
        }, new double[]{1, 2, 3, 4});
    }

}
