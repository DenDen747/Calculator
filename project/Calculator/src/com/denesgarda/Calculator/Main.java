package com.denesgarda.Calculator;

public class Main {
    public static Window window;

    public static double VERSION = 0.1;
    public static int inputID = 0;
    public static String breaker = "..................................................";

    public static String menuMessage = "---Calculator v" + VERSION + "---\n[A] Basic computation\n[B] Variable identification\n[C] Usage\n[~] Exit";

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
                inputID = 2;
            } else if (input.equalsIgnoreCase("B")) {
                printBreaker();
                System.out.println("[THIS MODE IS NOT YET AVAILABLE]");
                printBreaker();
                System.out.println(menuMessage);
            } else if (input.equalsIgnoreCase("C")) {
                printBreaker();
                System.out.println("*Usage goes here*");
                printBreaker();
                System.out.println(menuMessage);
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
                inputID = 1;
            } else {
                String ans = Logic.compute(input);
                printEdge(ans);
                printBreaker();
            }
        }
    }
}
