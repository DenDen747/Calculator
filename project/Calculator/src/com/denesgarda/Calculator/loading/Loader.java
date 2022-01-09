package com.denesgarda.Calculator.loading;

import com.denesgarda.Calculator.Main;

public class Loader implements Runnable {
    public int status = 0;

    @Override
    public void run() {
        try {
            while (true) {
                System.out.print(".  ");
                status = 3;
                Thread.sleep(500);
                System.out.print("\b\b\b");
                status = 0;
                System.out.print(".. ");
                status = 3;
                Thread.sleep(500);
                System.out.print("\b\b\b");
                status = 0;
                System.out.print("...");
                status = 3;
                Thread.sleep(500);
                System.out.print("\b\b\b");
                status = 0;
                System.out.print(" ..");
                status = 3;
                Thread.sleep(500);
                System.out.print("\b\b\b");
                status = 0;
                System.out.print("  .");
                status = 3;
                Thread.sleep(500);
                System.out.print("\b\b\b");
                status = 0;
                System.out.print("   ");
                status = 3;
                Thread.sleep(500);
                System.out.print("\b\b\b");
                status = 0;
            }
        } catch (InterruptedException ignore) {} catch (Exception e) {
            Main.printBreaker();
            System.out.println("!ERROR OCCURRED!");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
