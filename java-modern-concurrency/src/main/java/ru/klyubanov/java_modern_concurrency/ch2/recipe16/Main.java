package ru.klyubanov.java_modern_concurrency.ch2.recipe16;

import java.util.concurrent.locks.StampedLock;

public class Main {

    public static void main(String[] args) {
        Position position = new Position();
        StampedLock stampedLock = new StampedLock();

        Writer writer = new Writer(position, stampedLock);
        Reader reader = new Reader(position, stampedLock);
        OptimisticReader optimisticReader = new OptimisticReader(position, stampedLock);

        writer.start();
        reader.start();
        optimisticReader.start();

        try {
            writer.join();
            reader.join();
            optimisticReader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
