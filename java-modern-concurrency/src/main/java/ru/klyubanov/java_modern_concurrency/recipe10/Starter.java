package ru.klyubanov.java_modern_concurrency.recipe10;

import java.util.concurrent.TimeUnit;

public class Starter {

    public static void main(String[] args) {
        MyThreadFactory factory=new MyThreadFactory("MyThreadFactory");
        Runnable task= () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread;
        System.out.print("Starting the Threads");
        for (int i=0; i<10; i++){
            thread=factory.newThread(task);
            thread.start();
        }

        System.out.println("Factory stats:");
        System.out.println(factory.getStats());
    }
}
