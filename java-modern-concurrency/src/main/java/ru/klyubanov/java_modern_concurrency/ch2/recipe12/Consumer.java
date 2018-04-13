package ru.klyubanov.java_modern_concurrency.ch2.recipe12;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Consumer extends Thread {
    private final EventStorage storage;

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            storage.get();
        }
    }
}
