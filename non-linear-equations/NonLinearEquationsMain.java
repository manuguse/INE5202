public class NonLinearEquationsMain {

    public static void main(String[] args) {

        NonLinearEquations.newtonMethod(
                new int[][] {
                        new int[] {1, 2, 3},
                        new int[] {4, 5, 6},
                        new int[] {7, 8, 9}
                },
                10e-10
        );

    }

}