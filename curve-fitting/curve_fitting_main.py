from curve_fitting_methods import CurveFittingMethods

def main():
    
    cfm = CurveFittingMethods()
    
    x= [1.3, 3.4, 5.1, 6.8, 8]
    y= [2, 5.2, 3.8, 6.1, 5.8]
    M = 1
    
    cfm.polynomial_regression(x, y, M)
    cfm.exponential_regression(x, y)
    cfm.logarithmic_regression(x, y)
    cfm.power_regression(x, y)
    
if __name__ == "__main__":
    main()