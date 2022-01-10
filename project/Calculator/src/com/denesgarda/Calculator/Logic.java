package com.denesgarda.Calculator;

import javax.swing.*;
import java.awt.*;

public class Logic {
    public static String compute(String input) {
        try {
            if (input.startsWith("\\")) {
                String result = compute(input.substring(1));
                return String.valueOf(new Number(Double.parseDouble(result.split("/")[0]), Double.parseDouble(result.split("/")[1])).getAsDecimal());
            }
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < input.length()) ? input.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                Number parse() {
                    nextChar();
                    Number x = parseExpression();
                    if (pos < input.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                    return x;
                }

                /**
                 * `+` adds two numbers
                 * `-` subtracts two numbers
                 * `*` multiplies two numbers
                 * `/` divides two numbers
                 * `(` `)` parenthesis
                 * `|` `|` absolute value
                 * `\` converts a fraction to a decimal
                 * `^` Power
                 * `!` Factorial
                 * `sqrt`
                 * `cbrt`
                 * `sin`
                 * `cos`
                 * `tan`
                 * `asin`
                 * `acos`
                 * `atan`
                 * `sinh`
                 * `cosh`
                 * `tanh`
                 */

                // Grammar:
                // expression = term | expression `+` term | expression `-` term
                // term = factor | term `*` factor | term `/` factor
                // factor = `+` factor | `-` factor | `(` expression `)` | `|` expression `|`
                //        | number | functionName factor | factor `^` factor

                Number parseExpression() {
                    Number x = parseTerm();
                    for (; ; ) {
                        if (eat('+')) x = Number.Operation.add(x, parseTerm()); // addition
                        else if (eat('-')) x = Number.Operation.subtract(x, parseTerm()); // subtraction
                        else return x;
                    }
                }

                Number parseTerm() {
                    Number x = parseFactor();
                    for (; ; ) {
                        if (eat('*')) x = Number.Operation.multiply(x, parseFactor()); // multiplication
                        else if (eat('/')) x = Number.Operation.divide(x, parseFactor()); // division
                        else return x;
                    }
                }

                Number parseFactor() {
                    if (eat('+')) return parseFactor(); // unary plus
                    if (eat('-')) return Number.Operation.multiply(parseFactor(), new Number(-1)); // unary minus

                    Number x;
                    int startPos = this.pos;
                    if (eat('(')) { // parentheses
                        x = parseExpression();
                        eat(')');
                    } else if (eat('|')) {
                        x = Number.Operation.abs(parseExpression());
                        eat('|');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = new Number(Double.parseDouble(input.substring(startPos, this.pos)));
                    } else if (ch >= 'a' && ch <= 'z') { // functions
                        while (ch >= 'a' && ch <= 'z') nextChar();
                        String func = input.substring(startPos, this.pos);
                        x = parseFactor();
                        if (func.equals("sqrt")) x = Number.Operation.sqrt(x);
                        else if (func.equals("cbrt")) x = Number.Operation.cbrt(x);
                        else if (func.equals("sin")) x = Number.Operation.sin(x);
                        else if (func.equals("cos")) x = Number.Operation.cos(x);
                        else if (func.equals("tan")) x = Number.Operation.tan(x);
                        else if (func.equals("asin")) x = Number.Operation.asin(x);
                        else if (func.equals("atan")) x = Number.Operation.atan(x);
                        else if (func.equals("acos")) x = Number.Operation.acos(x);
                        else if (func.equals("sinh")) x = Number.Operation.sinh(x);
                        else if (func.equals("cosh")) x = Number.Operation.cosh(x);
                        else if (func.equals("tanh")) x = Number.Operation.tanh(x);
                        else throw new RuntimeException("Unknown function: " + func);
                    } else {
                        throw new RuntimeException("Unexpected: " + (char) ch);
                    }

                    if (eat('^')) x = Number.Operation.pow(x, parseFactor()); // exponentiation

                    if (eat('!')) x = Number.Operation.factorial((int) x.getAsDecimal());

                    return x;
                }
            }.parse().getAsFraction();
        } catch (Exception e) {
            return "SYNTAX ERROR";
        }
    }

    public static String graph(String input) {
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setTitle("Graphs");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return "Graph successful/failed";
    }
}
