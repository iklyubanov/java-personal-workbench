package ru.klyubanov.java_modern_concurrency.ch3.recipe17;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    private final Semaphore semaphore;
    private final boolean[] freePrinters;
    private final Lock lockPrinters;

    public PrintQueue() {
        semaphore = new Semaphore(3);
        freePrinters = new boolean[]{true, true, true};
        lockPrinters = new ReentrantLock();
    }

    /**
     * Simulate the printing of a
     * document.<br>
     * This method
     shows three steps you must follow when you use a semaphore to implement a critical
     section and protect access to a shared resource:<br>
     1. First, you acquire the semaphore with the acquire() method.<br>
     2. Then, you do the necessary operations with the shared resource.<br>
     3. Finally, release the semaphore with the release() method.
     */
    public void printJob(Object document) {
        try {
            semaphore.acquire();

            int assignedPrinter = getPrinter();
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s - %s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",
                    LocalTime.now(), Thread.currentThread().getName(), assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);

            freePrinters[assignedPrinter] = true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private int getPrinter() {
        int result = -1;
        try {
            lockPrinters.lock();

            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    result = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lockPrinters.unlock();
        }
        return result;
    }
}
