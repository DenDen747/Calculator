package com.denesgarda.Calculator;

import com.denesgarda.Calculator.loading.LoadRunner;
import com.denesgarda.Calculator.loading.Loader;

import javax.script.ScriptException;

public class Main {
    public static Double VERSION = 0.1;
    public static int statusID = 0;
    public static String breaker = "..................................................";
    public static String menuMessage = "---Calculator v" + VERSION + "---\n[A] Basic computation\n[B] Variable identification\n[C] Usage\n[~] Exit";

    public static Window Window;

    public static void main(String[] args) {
        Window = new Window();
        printBreaker();
        System.out.println(menuMessage);
        statusID = 1;
    }

    public static void processInput(String input) throws InterruptedException, ScriptException {
        if (statusID == 1) {
            printBreaker();
            if (input.equalsIgnoreCase("A")) {
                System.out.println("---Basic computation---\n[~] Exit");
                statusID = 2;
            } else if (input.equalsIgnoreCase("B")) {
                System.out.println("[THIS MODE IS NOT YET AVAILABLE]");
                printBreaker();
                System.out.println(menuMessage);
            } else if (input.equalsIgnoreCase("C")) {
                System.out.println("*Usage goes here*");
                printBreaker();
                System.out.println(menuMessage);
            } else if (input.equalsIgnoreCase("~")) {
                System.exit(0);
            } else {
                System.out.println("Invalid input");
                printBreaker();
                System.out.println(menuMessage);
            }
        } else if (statusID == 2) {
            if (input.equals("~")) {
                printBreaker();
                System.out.println(menuMessage);
                statusID = 1;
            } else {
                LoadRunner loader = new LoadRunner(new Loader());
                loader.start();
                String ans = Math.calculate(input);
                Thread.sleep(5);
                loader.stopDelete();
                printEdge(ans);
                printBreaker();
            }
        }
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
}
