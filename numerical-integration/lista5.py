from math import exp, sin, pi
from numerical_integration_methods import NumericalIntegrationMethods

def ex1():
    print(f'exercicio 1:')
    nim = NumericalIntegrationMethods()

    params = [
        (0, 1, 2, lambda x: x),
        (0, 1, 2, lambda x: exp(x)),
        (0, pi/2, 2, lambda x: sin(x)),
        (0, 1, 2, lambda x: x**2),
        (0, 1, 2, lambda x: x**3)
    ]

    for i, param in enumerate(params):
        print(f'{chr(97 + i)})')
        nim.trapezoidal_rule(
            param[0], param[1], param[2], param[3]
        )
        nim.simpsons_rule(
            param[0], param[1], param[2], param[3]
        )
    
def ex2():
    nim = NumericalIntegrationMethods()
    params = [
        (-1, 1, 5, lambda x: exp(x)),
        (0, pi, 5, lambda x: sin(x)),
        (2, 3, 5, lambda x: 1/x)
    ]
    
    for i, param in enumerate(params):
        print(f'{chr(97 + i)})')
        nim.gaussian_quadrature(
            param[0], param[1], param[2], param[3]
        )
        
def main():
    ex1()
    ex2()
    
if __name__ == '__main__':
    main()