package com.denesgarda.Calculator;

public class Logic {
    public static String compute(String input) {
        double n = Double.parseDouble(input);
        Number number = new Number(n);
        return number.numerator + " / " + number.denominator;
    }
}
