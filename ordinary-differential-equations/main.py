from ode_methods import ODEMethods as ode

def main():
    a = 0
    b = 1
    n = 10
    y0 = 1
    
    # o = ode(a, b, n, y0)
    
    o = ode()
    o.euler_method(a, b, n, y0)
    o.runge_kutta_2_order_method(a, b, n, y0)
    o.runge_kutta_4_order_method(a, b, n, y0)
    
if __name__ == '__main__':
    main()