package ru.klyubanov.java_modern_concurrency.ch1.recipe4;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


/**
 * the program writes a Date object per
 second and also the message indicating that the FileClock thread has been interrupted.
 * */
public class ConsoleClock extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("The FileClock has been interrupted!");
            }
        }
    }
}
