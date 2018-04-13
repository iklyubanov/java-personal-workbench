package ru.klyubanov.java_modern_concurrency.ch2.recipe12;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public class EventStorage {
    private int maxSize;
    private Queue<LocalDateTime> storage;
    public EventStorage(){
        maxSize=10;
        storage=new LinkedList<>();
    }

    /**method to store an event in storage*/
    public synchronized void set() {
        //wait when the queue is full until some free space will be released
        while(storage.size() == maxSize) {
           /* Sneaky.sneaked((SneakyRunnable) this::wait);*/
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        storage.offer(LocalDateTime.now());
        System.out.printf("Set: %d",storage.size());
        //to wake up all the threads that are sleeping in the wait() method
        notify();
    }

    public synchronized void get() {
        while(storage.isEmpty()) {
            /*Sneaky.sneaked((SneakyRunnable) this::wait);*/
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String element = storage.poll().toString();
        System.out.printf("Get: %d: %s\n",storage.size(),element);
        notify();
    }
}
