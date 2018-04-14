package ru.klyubanov.java_modern_concurrency.ch2.recipe13;

public class Job extends Thread {
    private final PrintQueue printQueue;

    public Job(String name, PrintQueue printQueue) {
        super(name);
        this.printQueue = printQueue;
        start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Going to print a document");
        printQueue.printJob(new Object());
        System.out.println(Thread.currentThread().getName() + ": The document has been printed");
    }
}
