// não to conseguindo mostrar com a precisao que quero por alguma razao

#include <iostream>
#include <cmath>
using namespace std;

double eExpN(int n) {
    double sum = 1.0;
    double term = 1.0;
    double precisao = 1e-16; 
    double diff = precisao + 1; 
    for (int i = 1; fabs(term) > precisao && diff > precisao; i++) {
        term *= n / double(i);
        double anterior = sum;
        sum += term;
        double atual = sum;
        diff = fabs(atual - anterior);
    }
    return sum;
}

int main() {
    int exp;
    cout << "insira o expoente ";
    cin >>  exp;
    cout << eExpN(exp) << endl;
    return 0;
}
