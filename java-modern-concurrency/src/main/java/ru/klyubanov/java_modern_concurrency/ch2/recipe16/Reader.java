package ru.klyubanov.java_modern_concurrency.ch2.recipe16;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

@RequiredArgsConstructor
public class Reader extends Thread {
    private final Position position;
    private final StampedLock stampedLock;

    @Override
    public void run() {

        for (int i = 0; i < 50; i++) {
            long stamp = stampedLock.readLock();

            try {
                System.out.printf("Reader: %d - (%d,%d)\n", stamp,
                        position.getX(), position.getY());
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                stampedLock.unlockRead(stamp);
                System.out.printf("Reader: %d - Lock released\n", stamp);
            }
        }
    }
}
