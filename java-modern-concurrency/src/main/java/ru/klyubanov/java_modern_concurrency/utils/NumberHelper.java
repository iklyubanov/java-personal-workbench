package ru.klyubanov.java_modern_concurrency.utils;

public class NumberHelper {
    /**
     * Is it prime number?
     * */
    public static boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }
}
