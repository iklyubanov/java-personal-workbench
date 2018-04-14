package ru.klyubanov.java_modern_concurrency.ch2.recipe13;

public class Starter {
    public static void main(String[] args) {
        System.out.println("Running example with fair-mode = false");
        testPrintQueue(false);
        System.out.println("Running example with fair-mode = true");
        testPrintQueue(true);
    }

    private static void testPrintQueue(boolean fairMode) {
        PrintQueue printQueue=new PrintQueue(fairMode);
        Job[] jobs=new Job[10];
        //Start the 10 threads
        for (int i=0; i<10; i++){
            jobs[i]=new Job("Thread "+ i, printQueue);
        }

        //wait for the finalization of the 10 threads
        for (int i=0; i<10; i++) {
            try {
                jobs[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
