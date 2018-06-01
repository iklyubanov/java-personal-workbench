package ru.klyubanov.java_modern_concurrency.ch3.recipe19;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * the class that calculates the total number of occurrences of the
 number in the matrix
 * */
@RequiredArgsConstructor
public class Grouper implements Runnable {
    private final Results results;

    @Override
    public void run() {
        System.out.println("Grouper: Processing results...");
        int finalResult = Arrays.stream(results.getData()).sum();
        System.out.printf("Grouper: Total result: %d.\n", finalResult);
    }
}
