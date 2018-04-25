package ru.klyubanov.java_modern_concurrency.ch3.recipe18;

import java.util.concurrent.CountDownLatch;

public class Videoconference implements Runnable {
    private final CountDownLatch controller;

    public Videoconference(int number) {
        controller = new CountDownLatch(number);
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
        try {
            controller.wait();
            System.out.println("VideoConference: All the participants have come");
            System.out.println("VideoConference: Let's start...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will be called each time a
     * participant arrives for the video conference.
     */
    public void arrive(String name) {
        System.out.println(name + " has arrived.");
        controller.countDown();

        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }
}
