import numpy as np
import matplotlib.pyplot as plt

class CurveFittingMethods:
    
    def __init__(self):
        pass
    
    def linear_regression(self, x:list, y:list) -> None:
        print("linear regression")
                
        n = len(x)
        sum_x = sum(x)
        sum_y = sum(y)
        sum_xy = sum(xi * yi for xi, yi in zip(x, y))
        sum_xx = sum(xi * xi for xi in x)
        
        denominator = n * sum_xx - sum_x * sum_x
        a = (n * sum_xy - sum_x * sum_y) / denominator
        b = (sum_y * sum_xx - sum_x * sum_xy) / denominator
        
        print(f"f(x) = {a}x + {b}\n")
    
    
    def polynomial_regression(self, x:list, y:list, degree:int) -> None:
        
        print(f"polynomial regression of degree {degree}")
        
        coeficients = [0.0] * (degree + 1)
        n = len(x)
        
        sum_x = [sum(x ** i for x in x) for i in range(2 * degree + 1)]
        sum_yx = [sum(y[i] * x[i] ** j for i in range(n)) for j in range(degree + 1)]
        
        A = np.zeros((degree + 1, degree + 1))
        for i in range(degree + 1):
            for j in range(degree + 1):
                A[i][j] = sum_x[i + j]
                
        B = np.array(sum_yx)
        for i in range(degree + 1):
            coeficients[i] = B[i]
            
        coeficients = np.linalg.solve(A, B)      
        
        print("f(x) = ", end="")
        for i in range(len(coeficients)-1, -1, -1): # imprime a equação de forma decrescente
            print(f"{coeficients[i]}x^{i} ", end=('\n\n' if i == 0 else (" + " if coeficients[i-1] > 0 else "")))
    
    def exponential_regression(self, x:list, y:list):
        
        print("exponential regression")
        
        logy = np.log(y)  # logaritmo natural de y (utlizado para linearizar a equação)
        
        # cria a matriz A com os valores de x e 1
        A = np.vstack([x, np.ones(len(x))]).T
        
        # resolve o sistema linear para encontrar os coeficients a e b
        b, a = np.linalg.lstsq(A, logy, rcond=None)[0] 
    
        a = np.exp(a) # desfaz o logaritmo natural
        
        print(f"f(x) = {a} * e^({b} * x)\n")
        
    def logarithmic_regression(self, x:list, y:list):
        print(f"logarithmic regression")
        
        logx = np.log(x)
        
        n = len(x)
        sum_logx = sum(logx)
        sum_y = sum(y)
        sum_logxy = sum(logxi * yi for                       # produto de logx e y
                        logxi, yi in zip(logx, y))
        sum_logxlogx = sum(logxi * logxi for logxi in logx)  # produto de logx e logx
        
        denominator = n * sum_logxlogx - sum_logx * sum_logx
        a = (n * sum_logxy - sum_logx * sum_y) / denominator
        b = (sum_y * sum_logxlogx - sum_logx * sum_logxy) / denominator
        
        print(f"f(x) = {b} + {a}*ln(x)\n")
    
    def power_regression(self, x:list, y:list):
        print(f"power regression")
        
        logx = np.log(x)
        logy = np.log(y)
        n = len(x)
        
        sum_logx = sum(logx)
        sum_logy = sum(logy)
        sum_logxlogy = sum(logxi * yi for logxi, yi in zip(logx, y))
        sum_logxlogx = sum(logxi * logxi for logxi in logx)
        
        denominator = n * sum_logxlogx - sum_logx * sum_logx
        b = (n * sum_logxlogy - sum_logx * sum_logy) / denominator
        a = np.exp((sum_logy * sum_logxlogx - sum_logx * sum_logxlogy) / denominator)
        
        print(f"f(x) = {a} * x^{b}\n")