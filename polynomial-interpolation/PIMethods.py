import numpy as np

class PolynomialInterpolation:
    def __init__(self) -> None:
        pass
    
    def linear_interpolation(self, xi:list, yi:list, k:float, n=None) -> None:
        
        n = (n if n else len(xi))
        V = np.zeros((n, n))
        
        for i in range(n):
            for j in range(n):
                V[i, j] = xi[i]**(j)

        a = np.linalg.solve(V, yi)

        p = 0
        for i in range(n):
            p += a[i] * k**(i)

        print(f"linear para x = {k}: {p}\n")
        
    def lagrange_interpolation(self, xi:list, yi:list, k:float, n=None) -> None:
        
        n = (n if n else len(xi))
        px = 0.0
        
        for i in range(n):
            term = yi[i]
            for j in range(n):
                if j != i:
                    term = term * (k - xi[j]) / (xi[i] - xi[j])
            px += term
        print(f"lagrange para x = {k}: {px}\n")

    
    def newton_divided_differences_interpolation(self, xi:list, yi:list, k:float, n=None) -> None:
        
        n = (n if n else len(xi))
        result = 0.0

        for i in range(1, n):
            for j in range(n-1, i-1, -1):
                yi[j] = (yi[j] - yi[j-1]) / (xi[j] - xi[j-i])
                
        for i in range(n):
            term = yi[i]
            for j in range(i):
                term *= (k - xi[j])
            result += term

        print(f"newton para x = {k}: {result}")