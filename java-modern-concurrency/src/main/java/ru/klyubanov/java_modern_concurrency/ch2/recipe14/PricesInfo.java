package ru.klyubanov.java_modern_concurrency.ch2.recipe14;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo {
    private double price1;
    private double price2;

    private final ReadWriteLock lock;

    public PricesInfo() {
        this.price1 = 1.0;
        this.price2 = 2.0;
        this.lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        double temp = price1;
        lock.readLock().unlock();
        return temp;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double temp = price2;
        lock.readLock().unlock();
        return temp;
    }

    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        System.out.println(LocalDateTime.now() + ": PricesInfo: Write Lock Adquired.");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.price1 = price1;
        this.price2 = price2;

        System.out.println(LocalDateTime.now() + ": PricesInfo: Write Lock Released.");
        lock.writeLock().unlock();
    }
}
