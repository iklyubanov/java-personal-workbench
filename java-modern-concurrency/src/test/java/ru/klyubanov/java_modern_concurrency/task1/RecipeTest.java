package ru.klyubanov.java_modern_concurrency.task1;

import org.junit.Test;
import ru.klyubanov.java_modern_concurrency.recipe2_thread_interruption.PrimeGenerator;
import ru.klyubanov.java_modern_concurrency.recipe3.FileSearch;
import ru.klyubanov.java_modern_concurrency.recipe4.ConsoleClock;

import java.util.concurrent.TimeUnit;

public class RecipeTest {

    @Test
    public void testRecipe2() throws InterruptedException {
        Thread task = new PrimeGenerator();
        task.start();

        Thread.sleep(5000);
        task.interrupt();

        status(task);

        Thread.sleep(1000);

        status(task);
    }

    @Test
    public void testRecipe3() {
        FileSearch searcher = new FileSearch("/home/ivan/",
                "Joel2.djv");
        Thread thread=new Thread(searcher, "FileSearch");
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        status(thread);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        status(thread);
    }

    @Test
    public void testRecipe4() {
        ConsoleClock clockThread = new ConsoleClock();
        clockThread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("The FileClock has been interrupted!");
        }
        clockThread.interrupt();
    }


    private void status(Thread task) {
        System.out.printf("Main: Status of the Thread: %s\n",
                task.getState());
        System.out.printf("Main: isInterrupted: %s\n",
                task.isInterrupted());
        System.out.printf("Main: isAlive: %s\n", task.isAlive());
    }
}
