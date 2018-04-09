package ru.klyubanov.java_modern_concurrency.recipe10;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name) {
        this.name = name;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r,name+"-Thread_"+counter);
        counter++;
        stats.add(String.format("Created thread %d with name %s on %s\n",
                t.getId(),t.getName(), LocalDateTime.now()));
        return t;
    }

    public String getStats() {
        return String.join("\n", stats);
    }
}
