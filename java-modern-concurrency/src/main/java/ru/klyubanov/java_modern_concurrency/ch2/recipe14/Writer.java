package ru.klyubanov.java_modern_concurrency.ch2.recipe14;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Writer extends Thread {
    private final PricesInfo pricesInfo;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s: Writer: Attempt to modify the prices.\n", LocalDateTime.now());

            pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);

            System.out.printf("%s: Writer: Prices have been modified.\n", LocalDateTime.now());
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
