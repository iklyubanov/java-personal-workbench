package ru.klyubanov.java_modern_concurrency.recipe6;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<Event> deque = new ConcurrentLinkedDeque<>();

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            WriteTask writeTask = new WriteTask(deque);
            writeTask.start();
        }
        CleanTask cleanTask = new CleanTask(deque);
        cleanTask.start();
    }
}
