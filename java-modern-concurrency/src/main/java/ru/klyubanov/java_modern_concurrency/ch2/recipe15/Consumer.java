package ru.klyubanov.java_modern_concurrency.ch2.recipe15;

import java.util.Random;

public class Consumer extends Thread {

    private Buffer buffer;

    public Consumer (Buffer buffer, String name){
        super(name);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
