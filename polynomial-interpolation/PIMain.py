from PIMethods import PolynomialInterpolation

def main():
    
    pi = PolynomialInterpolation()
    
    xi = [1.3, 2.1, 3, 4]
    yi = [-1, 2, -1, 10]
    x = 2
    n = 4
    
    pi.linear_interpolation(xi, yi, x, n)
    pi.lagrange_interpolation(xi, yi, x)
    pi.newton_divided_differences_interpolation(xi, yi, x)

if __name__ == "__main__":
    main()