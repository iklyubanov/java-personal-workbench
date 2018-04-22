package ru.klyubanov.java_modern_concurrency.ch3.recipe17;

public class Job extends Thread {
    private final PrintQueue printQueue;

    public Job(String name, PrintQueue printQueue) {
        super(name);
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Going to print a job");
        printQueue.printJob(new Object());
        System.out.println(Thread.currentThread().getName() + ": The document has been printed");
    }
}
