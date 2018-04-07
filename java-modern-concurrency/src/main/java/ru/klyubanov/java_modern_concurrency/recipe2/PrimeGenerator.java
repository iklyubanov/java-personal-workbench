package ru.klyubanov.java_modern_concurrency.recipe2;

import static ru.klyubanov.java_modern_concurrency.utils.NumberHelper.isPrime;

public class PrimeGenerator extends Thread {
    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }
            if (isInterrupted()) {
                System.out.println("The Prime Generator has been interrupted");
                return;
            }
            number++;
        }
    }
}
