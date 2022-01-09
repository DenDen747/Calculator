package com.denesgarda.Calculator;

import javax.script.ScriptException;

public class Math {
    public static String calculate(String raw) throws ScriptException {
        try {
            String evaluated = String.valueOf(evaluate(raw));
            return evaluated;
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public static double evaluate(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            /**
             * `+` adds two numbers
             * `-` subtracts two numbers
             * `*` multiplies two numbers
             * `/` divides two numbers
             * `(` `)` parenthesis
             * `|` `|` absolute value
             * `sqrt`
             * `cbrt`
             * `sin`
             * `cos`
             * `tan`
             * `asin`
             * `acos`
             * `atan`
             */

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | `|` expression `|`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if (eat('|')) {
                    x = java.lang.Math.abs(parseExpression());
                    eat('|');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = java.lang.Math.sqrt(x);
                    if (func.equals("cbrt")) x = java.lang.Math.cbrt(x);
                    else if (func.equals("sin")) x = java.lang.Math.sin(java.lang.Math.toRadians(x));
                    else if (func.equals("cos")) x = java.lang.Math.cos(java.lang.Math.toRadians(x));
                    else if (func.equals("tan")) x = java.lang.Math.tan(java.lang.Math.toRadians(x));
                    else if (func.equals("asin")) x = java.lang.Math.asin(java.lang.Math.toRadians(x));
                    else if (func.equals("atan")) x = java.lang.Math.atan(java.lang.Math.toRadians(x));
                    else if (func.equals("acos")) x = java.lang.Math.acos(java.lang.Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = java.lang.Math.pow(x, parseFactor()); // exponentiation

                if (eat('!')) x = factorial((int) x);

                return x;
            }
        }.parse();
    }

    public static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}
