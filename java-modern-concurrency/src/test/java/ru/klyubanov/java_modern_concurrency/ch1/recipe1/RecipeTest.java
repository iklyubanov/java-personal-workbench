package ru.klyubanov.java_modern_concurrency.ch1.recipe1;

import org.junit.Ignore;
import org.junit.Test;
import ru.klyubanov.java_modern_concurrency.ch1.recipe2.PrimeGenerator;
import ru.klyubanov.java_modern_concurrency.ch1.recipe3.FileSearch;
import ru.klyubanov.java_modern_concurrency.ch1.recipe4.ConsoleClock;
import ru.klyubanov.java_modern_concurrency.ch1.recipe5.DataSourceLoader;
import ru.klyubanov.java_modern_concurrency.ch1.recipe6.CleanTask;
import ru.klyubanov.java_modern_concurrency.ch1.recipe6.Event;
import ru.klyubanov.java_modern_concurrency.ch1.recipe6.WriteTask;
import ru.klyubanov.java_modern_concurrency.ch1.recipe7.ExceptionHandler;

import java.time.LocalTime;
import java.util.concurrent.ConcurrentLinkedDeque;
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
        Thread thread = new Thread(searcher, "FileSearch");
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

    @Test
    public void testRecipe5() {
        Thread networkEmulationThread = new Thread(() -> {
            System.out.println("Beginning connecting to the network: " + LocalTime.now());

            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("connecting to the network has finished: " + LocalTime.now());
        });
        Thread dataSourceLoadingEmulationThread = new DataSourceLoader();

        dataSourceLoadingEmulationThread.start();
        networkEmulationThread.start();

        try {
            dataSourceLoadingEmulationThread.join();//4000
            networkEmulationThread.join();//1000
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: Configuration has been loaded: " + LocalTime.now());
    }

    /**
     * don't work properly as unit test, only work when started in the main method,
     * because in unit tests test method isn't waiting for execution of the daemons
     */
    @Ignore
    @Test
    public void testRecipe6() throws InterruptedException {
        ConcurrentLinkedDeque<Event> deque = new ConcurrentLinkedDeque<>();

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            WriteTask writeTask = new WriteTask(deque);
            writeTask.start();
        }
        CleanTask cleanTask = new CleanTask(deque);
        cleanTask.start();
        //
        cleanTask.join();
    }

    @Test
    public void testRecipe7() {
        class Task implements Runnable {
            @Override
            public void run() {
                int numero = Integer.parseInt("TTT");
            }
        }
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }


    private void status(Thread task) {
        System.out.printf("Main: Status of the Thread: %s\n",
                task.getState());
        System.out.printf("Main: isInterrupted: %s\n",
                task.isInterrupted());
        System.out.printf("Main: isAlive: %s\n", task.isAlive());
    }
}
