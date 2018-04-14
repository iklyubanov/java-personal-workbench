package ru.klyubanov.java_modern_concurrency.ch2.recipe13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    private Lock queueLock;

    public PrintQueue(boolean fairMode) {
        queueLock = new ReentrantLock(fairMode);
    }

    public void printJob(Object document){
        //get control of the lock
        queueLock.lock();

        //simulation of the process of printing a document
        try {
            Long duration=(long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+ ": PrintQueue: Printing a Job during " + (duration/1000)+" seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //free the control of the Lock
            queueLock.unlock();
        }
    }
}
