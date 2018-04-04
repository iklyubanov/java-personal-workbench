package ru.klyubanov.java_modern_concurrency.package8;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //unsafeThreadExecution();
        safeThreadExecution();
    }

    private static void unsafeThreadExecution() {
        UnsafeTask task=new UnsafeTask();
        execute10Threads(task);
    }

    private static void safeThreadExecution() {
        SafeTask task=new SafeTask();
        execute10Threads(task);
    }

    private static void execute10Threads(Runnable task) {
        for (int i=0; i<10; i++){
            Thread thread=new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
