package ru.klyubanov.java8.algo;

import lombok.Data;

import java.util.Arrays;

public class PlusMinusFractions {

    @Data
    public static class Counter {
        private int pCount = 0, mCount = 0, zCount = 0;

        public int getPCount() {
            return pCount;
        }

        public int getMCount() {
            return mCount;
        }

        public int getZCount() {
            return zCount;
        }
    }

    static Counter plusMinus(int[] arr) {

        final Counter counter = new Counter();

        Arrays.stream(arr).forEach(e -> {
            if(e > 0) counter.pCount++;
            else if(e < 0) counter.mCount++;
            else counter.zCount++;
        });

        System.out.println((double)counter.pCount / arr.length);
        System.out.println((double)counter.mCount / arr.length);
        System.out.println((double)counter.zCount / arr.length);
        return counter;
    }
}
