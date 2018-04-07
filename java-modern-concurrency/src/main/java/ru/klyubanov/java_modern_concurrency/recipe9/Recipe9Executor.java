package ru.klyubanov.java_modern_concurrency.recipe9;

public class Recipe9Executor {
    public static void main(String[] args) {
        int numberOfThreads = 2 * Runtime.getRuntime().availableProcessors();
        MyThreadGroup threadGroup=new MyThreadGroup("MyThreadGroup");
        Task task=new Task();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread t = new Thread(threadGroup, task);
            t.start();
        }
        System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
        System.out.print("Information about the Thread Group");
        threadGroup.list();
        //write the status of the threads that form the group
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i = 0; i < threadGroup.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n", threads[i].getName(),
                    threads[i].getState());
        }
    }
}
