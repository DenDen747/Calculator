package com.denesgarda.Calculator;

public class Number {
    public double numerator;
    public double denominator;

    public Number(double number) {
        String computed = convertDecimalToFraction(number);

        this.numerator = Double.parseDouble(computed.split("/")[0]);
        this.denominator = Double.parseDouble(computed.split("/")[1]);
    }

    private static String convertDecimalToFraction(double x){
        if (x < 0) {
            return "-" + convertDecimalToFraction(-x);
        }
        double tolerance = 1.0E-6;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a*h1+h2; h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-h1/k1) > x*tolerance);
        return h1+"/"+k1;
    }
}
