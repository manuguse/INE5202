from numerical_integration_methods import NumericalIntegrationMethods as nim
import numpy as np

def main():
    n = nim(0, 1, 4, lambda x: x**3 * np.exp(x))
    n = nim()
    # n.trapezoidal_rule(0, 1, 4, lambda x: x**3 * np.exp(x))
    # n.simpsons_rule(0, 1, 4, lambda x: x**3 * np.exp(x))
    # n.gaussian_quadrature(0, 1, 4, lambda x: x**3 * np.exp(x))

if __name__ == '__main__':
    main()