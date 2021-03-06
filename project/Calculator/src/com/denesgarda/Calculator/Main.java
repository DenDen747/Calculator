package com.denesgarda.Calculator;

public class Main {
    public static Window window;

    public static double VERSION = 1.0;
    public static int inputID = 0;
    public static String breaker = "..................................................";

    public static String menuMessage = "---Calculator v" + VERSION + "---\n[A] Basic computation\n[B] Variable identification\n[C] Usage\n[~] Exit";
    public static String usage = "Usage:\n" +
            "- Basic computation\n" +
            "    - `+` Addition\n" +
            "    - `-` Subtraction\n" +
            "    - `*` Multiplication\n" +
            "    - `/` Division\n" +
            "    - `(x+y)` Parenthesis\n" +
            "    - `|x|` Absolute value\n" +
            "    - `\\` Putting before an expression returns decimal form\n" +
            "    - `^` Power\n" +
            "    - `!` Factorial\n" +
            "    - Functions (Format example: sqrt(14+2))\n" +
            "        - sqrt\n" +
            "        - cbrt\n" +
            "        - sin\n" +
            "        - cos\n" +
            "        - tan\n" +
            "        - asin\n" +
            "        - acos\n" +
            "        - atan\n" +
            "        - sinh\n" +
            "        - cosh\n" +
            "        - tanh\n" +
            "[ENTER] Continue";

    public static void main(String[] args) {
        window = new Window();
        printBreaker();
        System.out.println(menuMessage);
        inputID = 1;
    }

    public static void printBreaker() {
        System.out.println(breaker);
    }

    public static void printEdge(String s) {
        int space = breaker.length();
        for (int i = 0; i < s.length(); i++) {
            space--;
        }
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println(s);
    }

    public static void processInput(String input) {
        if (inputID == 1) {
            if (input.equalsIgnoreCase("A")) {
                printBreaker();
                System.out.println("---Basic computation---\n[~] Exit");
                window.setTitle("Calculator v" + VERSION + " - Basic computation");
                inputID = 2;
            } else if (input.equalsIgnoreCase("B")) {
                printBreaker();
                System.out.println("[THIS MODE IS NOT YET AVAILABLE]");
                printBreaker();
                System.out.println(menuMessage);
            } else if (input.equalsIgnoreCase("C")) {
                printBreaker();
                System.out.println(usage);
                window.setTitle("Calculator v" + VERSION + " - Usage");
                inputID = 3;
            } else if (input.equalsIgnoreCase("~")) {
                System.exit(0);
            } else {
                printBreaker();
                System.out.println("Invalid input");
                printBreaker();
                System.out.println(menuMessage);
            }
        } else if (inputID == 2) {
            if (input.equalsIgnoreCase("~")) {
                printBreaker();
                System.out.println(menuMessage);
                window.setTitle("Calculator v" + VERSION + " - Menu");
                inputID = 1;
            } else {
                String ans = String.valueOf(Logic.compute(input));
                if (ans.equalsIgnoreCase("Infinity/NaN")) {
                    ans = "MATH ERROR";
                }
                if (ans.contains(".0/")) {
                    ans = ans.replace(".0/", "/");
                }
                if (ans.endsWith(".0")) {
                    ans = ans.substring(0, ans.length() - 2);
                }
                if (ans.contains("/")) {
                    if (ans.split("/")[1].equals("1")) {
                        ans = ans.split("/")[0];
                    } else if (ans.split("/")[1].equals("0")) {
                        ans = "Undefined";
                    }
                }
                printEdge(ans);
                printBreaker();
            }
        } else if (inputID == 3) {
            printBreaker();
            System.out.println(menuMessage);
            window.setTitle("Calculator v" + VERSION + " - Menu");
            inputID = 1;
        }
    }
}
