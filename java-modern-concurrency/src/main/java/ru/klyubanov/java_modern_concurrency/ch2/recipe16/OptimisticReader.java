package ru.klyubanov.java_modern_concurrency.ch2.recipe16;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

@RequiredArgsConstructor
public class OptimisticReader extends Thread {
    private final Position position;
    private final StampedLock stampedLock;

    @Override
    public void run() {
        long stamp;
        for (int i = 0; i < 100; i++) {
            try {
                stamp= stampedLock.tryOptimisticRead();
                int x = position.getX();
                int y = position.getY();
                if (stampedLock.validate(stamp)) {
                    System.out.printf("OptmisticReader: %d - (%d,%d)\n",
                            stamp,x, y);
                } else {
                    System.out.printf("OptmisticReader: %d - Not Free\n",
                            stamp);
                }
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
