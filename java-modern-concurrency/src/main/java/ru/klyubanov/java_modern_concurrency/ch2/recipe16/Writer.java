package ru.klyubanov.java_modern_concurrency.ch2.recipe16;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

@RequiredArgsConstructor
public class Writer extends Thread {
    private final Position position;
    private final StampedLock stampedLock;

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            long stamp = stampedLock.writeLock();

            try {
                System.out.printf("Writer: Lock acquired %d\n",stamp);
                position.setX(position.getX()+1);
                position.setY(position.getY()+1);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                stampedLock.unlockWrite(stamp);
                System.out.printf("Writer: Lock released %d\n",stamp);
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
