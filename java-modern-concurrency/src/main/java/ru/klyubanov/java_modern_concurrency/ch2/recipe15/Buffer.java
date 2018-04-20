package ru.klyubanov.java_modern_concurrency.ch2.recipe15;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final LinkedList<String> buffer;
    private final int maxSize;
    private final ReentrantLock lock;
    private final Condition lines;
    private final Condition space;

    /**indicate whether there are lines in the buffer*/
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines =true;
    }

    /**receives String as a parameter and tries to
store it in the buffer*/
    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                space.await();
            }

            insert0(line);

            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void insert0(String line) {
        buffer.offer(line);

        System.out.printf("%s: Inserted Line: %d\n",
                Thread.currentThread().getName(),
                buffer.size());
    }

    public String get() {
        String line = null;
        lock.lock();
        try {
            while (buffer.size() == 0 && hasPendingLines()) {
                lines.await();
            }

            line = get0();

            space.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return line;
    }

    private String get0() {
        String line = null;
        if (hasPendingLines()) {
            line = buffer.poll();
            System.out.printf("%s: Line Readed: %d\n",
                    Thread.currentThread().getName(),
                    buffer.size());
            space.signalAll();
        }
        return line;
    }

    public synchronized void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public synchronized boolean hasPendingLines() {
        return pendingLines || buffer.size()>0;
    }


}
