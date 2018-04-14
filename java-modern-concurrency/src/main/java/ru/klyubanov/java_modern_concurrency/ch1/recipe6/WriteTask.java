package ru.klyubanov.java_modern_concurrency.ch1.recipe6;

import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class WriteTask extends Thread {
    private final ConcurrentLinkedDeque<Event> deque;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Event event = new Event(LocalTime.now(), String.format("The thread %s has generated an event",
                    Thread.currentThread().getId()));
            deque.addFirst(event);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
