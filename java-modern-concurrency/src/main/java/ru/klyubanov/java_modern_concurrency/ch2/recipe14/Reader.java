package ru.klyubanov.java_modern_concurrency.ch2.recipe14;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Reader extends Thread {
    private final PricesInfo pricesInfo;

    @Override
    public void run() {
        for (int i=0; i<20; i++){
            System.out.printf("%s: %s: Price 1: %f\n", LocalDateTime.now(),
                    Thread.currentThread().getName(),
                    pricesInfo.getPrice1());
            System.out.printf("%s: %s: Price 2: %f\n", LocalDateTime.now(),
                    Thread.currentThread().getName(),
                    pricesInfo.getPrice2());
        }
    }
}
