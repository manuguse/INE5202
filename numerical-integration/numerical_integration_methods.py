import numpy as np

class NumericalIntegrationMethods:
    def __init__(self, a:float=None, b:float=None, n:int=None, f=None): # type: ignore
        """
        pass the arguments to calculate the integral with all methods.\n
        if you want to calculate the integral with only one method, instantiate the class without arguments and call the method you want.\n
        """
        if a is not None:
            self.trapezoidal_rule(a, b, n, f)
            self.simpsons_rule(a, b, n, f)
            self.gaussian_quadrature(a, b, n, f)

    def trapezoidal_rule(self, a:float, b:float, n:int, f) -> None:
        """
        a: lower limit
        b: upper limit
        n: number of subintervals
        f: lambda function of the function to integrate
        """
        print(f'trapezoidal rule:')
        h = (b - a) / n
        x = np.linspace(a, b, n+1)
        y = [f(i) for i in x]
        
        sum = 0
        for i in range(1, n):
            sum += y[i]
            
        t = h / 2 * (y[0] + 2 * sum + y[n]) 
        
        print(f'x: {x}')
        print(f'y: {y}')
        print(f'integer: {t}\n')
    
    def simpsons_rule(self, a:float, b:float, n:int, f) -> None:
        """
        a: lower limit
        b: upper limit
        n: number of subintervals. n must be even.
        f: lambda function of the function to integrate
        """
        print(f'simpsons rule:')
        if n % 2 != 0:
            raise ValueError("n must be even.")
        
        h = (b - a) / n
        x = np.linspace(a, b, n + 1)
        y = [f(i) for i in x]

        sum1 = sum(y[2*i+1] for i in range((n // 2)))
        sum2 = sum(y[2*i] for i in range(1, (n // 2)))

        s = (h / 3) * (y[0] + 4 * sum1 + 2 * sum2 + y[n])
        
        
        print(f'x: {x}')
        print(f'y: {y}')
        print(f'integer: {s}\n')

    def gaussian_quadrature(self, a:float, b:float, n:int, f) -> None:
        """
        a: lower limit
        b: upper limit
        n: number of subintervals
        f: lambda function of the function to integrate
        """
        print(f'gaussian quadrature:')
        x, w = np.polynomial.legendre.leggauss(n)
        x = (x * (b - a) + (b + a)) / 2  # x é o ponto de integração
        w = (b - a) * w / 2  # w é o peso associado a cada x
        y = [f(i) for i in x]
        integer = 0
        for i in range(n):
            integer += w[i] * y[i]
        print(f'x: {x}')
        print(f'y: {y}')
        print(f'integer: {integer}\n')
