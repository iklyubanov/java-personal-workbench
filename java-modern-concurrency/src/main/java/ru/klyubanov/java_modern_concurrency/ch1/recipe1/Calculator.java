package ru.klyubanov.java_modern_concurrency.ch1.recipe1;

import ru.klyubanov.java_modern_concurrency.utils.NumberHelper;

public class Calculator implements Runnable {
    @Override
    public void run() {
        long current = 1L;
        long max = 20000L;
        long numPrimes = 0L;

        System.out.printf("Thread '%s': START\n",
                Thread.currentThread().getName());
        while (current <= max) {
            if (NumberHelper.isPrime(current)) {
                numPrimes++;
            }
            current++;
        }
        System.out.printf("Thread '%s': END. Number of Primes: %d\n",
                Thread.currentThread().getName(), numPrimes);
    }

}
