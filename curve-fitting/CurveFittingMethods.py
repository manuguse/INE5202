import numpy as np
import matplotlib.pyplot as plt

class CurveFittingMethods:
    
    def __init__(self):
        pass
    
    def linear_regression(self, x:list, y:list) -> None:
        pass
    
    def polynomial_regression(self, x, y, degree):
        
        coeficients = [0.0] * (degree + 1)
    
        X = np.array(x)
        Y = np.array(y)
        n = len(X)
        
        # Calcula as somas necessárias para a fórmula dos coeficients
        sum_X = [sum(x ** i for x in X) for i in range(2 * degree + 1)]
        sum_YX = [sum(Y[i] * X[i] ** j for i in range(n)) for j in range(degree + 1)]
        
        # Cria a matriz dos coeficientes
        A = np.zeros((degree + 1, degree + 1))
        for i in range(degree + 1):
            for j in range(degree + 1):
                A[i][j] = sum_X[i + j]
                
        # Cria o vetor dos coeficientes
        B = np.array(sum_YX)
        for i in range(degree + 1):
            coeficients[i] = B[i]
            
        # Resolve o sistema linear
        coeficients = np.linalg.solve(A, B)      
        
        print("f(x) = ", end="")
        for i in range(len(coeficients)-1, -1, -1):
            print(f"{coeficients[i]}x^{i} ", end=('\n' if i == 0 else (" + " if coeficients[i-1] > 0 else "")))
    
    def exponential_regression(self, x, y):
        
        X = np.array(x)   # vetor x
        Y = np.array(y)   # vetor y
        logY = np.log(Y)  # logaritmo natural de y (utlizado para linearizar a equação)
        
        # cria a matriz A com os valores de x e 1
        A = np.vstack([X, np.ones(len(X))]).T
        
        # resolve o sistema linear para encontrar os coeficients a e b
        b, a = np.linalg.lstsq(A, logY, rcond=None)[0] 
        
        a = np.exp(a) # serve para desfazer o logaritmo natural
        
        print(f"y = {a} * e^({b} * x)")
        
    def logarithmic_regression(self, x, y):
        pass
    
    def power_regression(self, x, y):
        pass
    
def main():
    
    cfm = CurveFittingMethods()
    
    x= [1.3, 3.4, 5.1, 6.8, 8]
    y= [2, 5.2, 3.8, 6.1, 5.8]
    M = 1
    
    print("Polynomial Regression")
    cfm.polynomial_regression(x, y, M)
    
    # print("Exponential Regression")
    # cfm.exponential_regression(x, y)
    
if __name__ == "__main__":
    main()