public class LinearEquationsMain {
    public static void main(String[] args) {

        System.out.println("gaussian elimination method");
        LinearEquationsMethods.gaussianEliminationMethod(new double[][]{
                {-0.421, 0.784, 0.279},
                {0.448, 0.832, 0.193},
                {0.421, 0.784, -0.207}
        }, new double[]{0, 1, 0}, 3);
    }
}
