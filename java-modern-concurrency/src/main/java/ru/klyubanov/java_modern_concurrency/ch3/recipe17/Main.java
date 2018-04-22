package ru.klyubanov.java_modern_concurrency.ch3.recipe17;

public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        for (int i = 0; i < 12; i++) {
            Job job = new Job("Thread"+i, printQueue);
            job.start();
        }
    }
}
