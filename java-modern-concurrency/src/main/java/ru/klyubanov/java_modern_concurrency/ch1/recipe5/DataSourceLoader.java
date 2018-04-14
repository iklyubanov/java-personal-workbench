package ru.klyubanov.java_modern_concurrency.ch1.recipe5;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class DataSourceLoader extends Thread {
    /**
     * It writes a message to indicate that it starts its
     execution, sleeps for 4 seconds, and writes another message to indicate that it
     ends its execution:
     @see Runnable
     * */
    @Override
    public void run() {
        System.out.println("Beginning data sources loading: " + LocalTime.now());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Data sources loading has finished: " + LocalTime.now());

    }
}
