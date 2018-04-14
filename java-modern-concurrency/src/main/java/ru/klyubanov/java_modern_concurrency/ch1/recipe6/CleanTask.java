package ru.klyubanov.java_modern_concurrency.ch1.recipe6;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CleanTask extends Thread {
    private final ConcurrentLinkedDeque<Event> deque;

    public CleanTask(ConcurrentLinkedDeque<Event> deque) {
        this.deque = deque;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            LocalTime time = LocalTime.now();
            clean(time);
        }
    }

    /**
     * It gets the last event, and if it was created more
     * than 10 seconds ago, it deletes it and checks the next event. If an event is deleted,
     * it writes the message of the event and the new size of the queue so you can see its
     * evolution
     */
    private void clean(LocalTime time) {
        if (deque.isEmpty()) {
            return;
        }
        Duration difference;
        boolean delete = false;

        do {
            if (deque.isEmpty()) {
                return;
            }
            Event e = deque.getLast();
            difference = Duration.between(e.getTime(), time);
            if (difference.toMillis() > 10000) {
                System.out.printf("Cleaner: %s\n", e.getType());
                deque.removeLast();
                delete = true;
            }
        } while (difference.toMillis() > 10000);
        if (delete) {
            System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
        }

    }
}
