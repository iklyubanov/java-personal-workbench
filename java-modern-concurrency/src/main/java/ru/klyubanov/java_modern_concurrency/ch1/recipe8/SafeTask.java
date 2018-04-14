package ru.klyubanov.java_modern_concurrency.ch1.recipe8;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {
    private static ThreadLocal<LocalDateTime> startTime = ThreadLocal.withInitial(LocalDateTime::now);

    @Override
    public void run() {
        System.out.printf("Starting Thread: %s : %s\n",
                Thread.currentThread().getId(), startTime.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n",
                Thread.currentThread().getId(), startTime.get());
    }
}
