public class RootFinderMain {
    // elapsed time will be different in each run

    public static void main(String[] args) {

        double precision = 10e-11;

        System.out.println("bisection method:");
        RootFinderMethods.bisectionMethod(0, 1, precision);

        System.out.println("\nregula falsi method:");
        RootFinderMethods.regulaFalsiMethod(0, 1, precision);

        System.out.println("\nmodified regula falsi method:");
        RootFinderMethods.modifiedRegulaFalsiMethod(0, 1, precision);

        System.out.println("\nnewton method:");
        RootFinderMethods.newtonMethod(1, precision);

        System.out.println("\nsecant method:");
        RootFinderMethods.secantMethod(0, 1, precision);

        System.out.println("\nbirge vieta method:");
        RootFinderMethods.birgeVietaMethod(new int[]{1, 0, 2, -1}, precision); // y = x^3 + 2x - 1
    }
}
