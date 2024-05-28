public static void main(String[] args) {
    RootFinderMethods.bisectionMethod(2.5, 3.5, 10e-5);
    System.out.println();
    RootFinderMethods.regulaFalsiMethod(2.5, 3.5, 10e-5);
    System.out.println();

    RootFinderMethods.newtonMethod(2.5, 10e-5);
    System.out.println();

    RootFinderMethods.secantMethod(1.5, 2.5, 10e-5);
}