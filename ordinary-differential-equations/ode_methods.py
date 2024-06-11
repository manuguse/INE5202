from math import exp
import matplotlib.pyplot as plt
import numpy as np

class ODEMethods:
    def __init__(self, a=None, b=None, n=None, y0=None) -> None:
        if a is not None:
            self.euler_method(a, b, n,  y0)
            self.runge_kutta_2_order_method(a, b, n, y0)
            self.runge_kutta_4_order_method(a, b, n, y0)
    
    def plot_graph(self, x, estimado, exato):
        plt.plot(x, estimado, label='estimado')
        plt.legend()
        plt.plot(x, exato, label='exato')
        plt.legend()
        plt.show()
    
    def _expression(self, x:float) -> float:
        # return -3*exp(-x) - 2*x + 2
        return exp(-5*x)
    
    def _fxy(self, x, y):
        # return -2*x-y # derivada
        return -5*y
    
    def euler_method(self, a, b, n, y0, plot=False):
        print(f'euler method:')
        
        h = (b - a) / n
        x = np.zeros(n)
        y = np.zeros(n)
        y[0] = y0
        for i in range(1, n):
            x[i] = x[i-1] + h
            y[i] = y[i-1] + h * self._fxy(x[i-1], y[i-1])

        print(f'x: {x}')
        print(f'y: {y}')
        
        exact_y = [self._expression(x[i]) for i in range(n)]
        print(f'exact y: {exact_y}')
        
        print(f'error: {y - exact_y}\n')
        
        if plot:
            self.plot_graph(x, y, exact_y)
        
    def runge_kutta_2_order_method(self, a, b, n, y0=None, plot=False):
        print(f'runge kutta 2 order method:')
        
        h = (b - a) / n
        x = np.zeros(n)
        y = np.zeros(n)
        y[0] = y0 if y0 is not None else self._expression(a)
        
        for i in range(1, n):
            x[i] = x[i-1] + h
            k1 = h * self._fxy(x[i-1], y[i-1])
            k2 = h * self._fxy(x[i-1] + h, y[i-1] + k1)
            y[i] = y[i-1] + (k1 + k2) / 2

        print(f'x: {x}')
        print(f'y: {y}')
        
        exact_y = [self._expression(x[i]) for i in range(n)]
        print(f'exact y: {exact_y}')
        
        print(f'error: {y - exact_y}\n')
        
        if plot:
            self.plot_graph(x, y, exact_y)
            
    def runge_kutta_4_order_method(self, a, b, n, y0=None, plot=False):
        print(f'runge kutta 4 order method:')
        
        h = (b - a) / n
        x = np.zeros(n)
        y = np.zeros(n)
        y[0] = y0 if y0 is not None else self._expression(a)
        
        for i in range(1, n):
            x[i] = x[i-1] + h
            k1 = h * self._fxy(x[i-1], y[i-1])
            k2 = h * self._fxy(x[i-1] + h/2, y[i-1] + k1/2)
            k3 = h * self._fxy(x[i-1] + h/2, y[i-1] + k2/2)
            k4 = h * self._fxy(x[i-1] + h, y[i-1] + k3)
            y[i] = y[i-1] + (k1 + 2*k2 + 2*k3 + k4) / 6
            
        print(f'x: {x}')
        print(f'y: {y}')
        
        exact_y = [self._expression(x[i]) for i in range(n)]
        print(f'exact y: {exact_y}')
        
        print(f'erro: {y - exact_y}\n')
        
        if plot:
            self.plot_graph(x, y, exact_y)