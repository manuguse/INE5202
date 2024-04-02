public class RootFinderMain {
    // elapsed time will be different in each run
    public static void main(String[] args) {
        System.out.println("bisection method:");
        RootFinderMethods.bisectionMethod(0, 1, 10e-10);

        System.out.println("\nregula falsi method:");
        RootFinderMethods.regulaFalsiMethod(0, 1, 10e-10);

        System.out.println("\nnewton method:");
        RootFinderMethods.newtonMethod(1, 10e-10);

        System.out.println("\nsecant method:");
        RootFinderMethods.secantMethod(0, 1, 10e-10);
    }
}
