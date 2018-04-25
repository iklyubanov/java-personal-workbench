package ru.klyubanov.java_modern_concurrency.ch3.recipe18;

import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class Participant implements Runnable {
    private Videoconference conference;
    private String name;

    @Override
    public void run() {
        long duration=(long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //indicate the arrival of this participant
        conference.arrive(name);
    }
}
