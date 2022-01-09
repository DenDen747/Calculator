package com.denesgarda.Calculator;

import java.text.DecimalFormat;

public class Number {
    public double numerator;
    public double denominator;

    public Number(double number) {
        String computed = convertDecimalToFraction(number);

        this.numerator = Double.parseDouble(computed.split("/")[0]);
        this.denominator = Double.parseDouble(computed.split("/")[1]);
    }

    public Number(double numerator, double denominator) {
        String computed = simplify(numerator, denominator);

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

    public static String simplify(double x, double y) {
        double gcd = gcd(x, y);
        return (x / gcd) + "/" + (y / gcd);
    }

    public static double gcd(double x, double y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public double getAsDecimal() {
        return numerator / denominator;
    }

    public String getAsFraction() {
        return numerator + "/" + denominator;
    }

    public static class Operation {
        public static Number add(Number x, Number y) {
            double commonFactor = x.denominator * y.denominator;
            double numerator = x.numerator * commonFactor + y.numerator * commonFactor;
            return new Number(numerator, commonFactor);
        }

        public static Number subtract(Number x, Number y) {
            double commonFactor = x.denominator * y.denominator;
            double numerator = x.numerator * commonFactor - y.numerator * commonFactor;
            return new Number(numerator, commonFactor);
        }

        public static Number multiply(Number x, Number y) {
            return new Number(x.numerator * y.numerator, x.denominator * y.denominator);
        }

        public static Number divide(Number x, Number y) {
            return new Number(x.numerator * y.denominator, x.denominator * y.numerator);
        }

        public static Number pow(Number x, Number y) {
            return new Number(Math.pow(x.numerator, (y.numerator / y.denominator)), Math.pow(x.denominator, (y.numerator / y.denominator)));
        }

        public static Number factorial(int x) {
            double result = 1;

            for (int factor = 2; factor <= x; factor++) {
                result *= factor;
            }

            return new Number(result);
        }

        public static Number abs(Number x) {
            return new Number(Math.abs(x.numerator), Math.abs(x.denominator));
        }

        public static Number sqrt(Number x) {
            return new Number(Math.sqrt(x.numerator), Math.sqrt(x.denominator));
        }

        public static Number cbrt(Number x) {
            return new Number(Math.cbrt(x.numerator), Math.cbrt(x.denominator));
        }

        public static Number sin(Number x) {
            return new Number(Math.sin(x.numerator / x.denominator));
        }

        public static Number cos(Number x) {
            return new Number(Math.cos(x.numerator / x.denominator));
        }

        public static Number tan(Number x) {
            return new Number(Math.tan(x.numerator / x.denominator));
        }

        public static Number asin(Number x) {
            return new Number(Math.asin(x.numerator / x.denominator));
        }

        public static Number atan(Number x) {
            return new Number(Math.atan(x.numerator / x.denominator));
        }

        public static Number acos(Number x) {
            return new Number(Math.acos(x.numerator / x.denominator));
        }

        public static Number sinh(Number x) {
            return new Number(Math.sinh(x.numerator / x.denominator));
        }

        public static Number cosh(Number x) {
            return new Number(Math.cosh(x.numerator / x.denominator));
        }

        public static Number tanh(Number x) {
            return new Number(Math.tanh(x.numerator / x.denominator));
        }
    }
}
