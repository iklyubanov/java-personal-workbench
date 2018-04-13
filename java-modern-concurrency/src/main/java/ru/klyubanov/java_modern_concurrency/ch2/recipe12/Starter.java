package ru.klyubanov.java_modern_concurrency.ch2.recipe12;

public class Starter {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);

        producer.start();
        consumer.start();
    }
}
