package com.denesgarda.Calculator.loading;

public class LoadRunner extends Thread {
    private Loader runnable;

    public LoadRunner(Loader runnable) {
        super(runnable);
        this.runnable = runnable;
    }

    public void stopDelete() {
        this.interrupt();
        for (int i = 0; i < runnable.status; i++) {
            System.out.print("\b");
        }
    }
}
