package ru.klyubanov.java_modern_concurrency.ch1.recipe8;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class UnsafeTask implements Runnable {
    private LocalDateTime startTime;
    @Override
    public void run() {
        startTime = LocalDateTime.now();
        System.out.printf("Starting Thread: %s : %s\n",
                Thread.currentThread().getId(),startTime);
        try {
            TimeUnit.SECONDS.sleep((long) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n",
                Thread.currentThread().getId(),startTime);
    }
}
