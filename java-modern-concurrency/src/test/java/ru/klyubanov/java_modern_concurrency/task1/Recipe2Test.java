package ru.klyubanov.java_modern_concurrency.task1;

import org.junit.Test;
import ru.klyubanov.java_modern_concurrency.recipe2_thread_interruption.PrimeGenerator;

public class Recipe2Test {

    @Test
    public void test() throws InterruptedException {
        Thread task = new PrimeGenerator();
        task.start();

        Thread.sleep(5000);
        task.interrupt();

        status(task);

        Thread.sleep(1000);

        status(task);
    }

    private void status(Thread task) {
        System.out.printf("Main: Status of the Thread: %s\n",
                task.getState());
        System.out.printf("Main: isInterrupted: %s\n",
                task.isInterrupted());
        System.out.printf("Main: isAlive: %s\n", task.isAlive());
    }
}
