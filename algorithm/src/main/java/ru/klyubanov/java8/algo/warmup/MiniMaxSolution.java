package ru.klyubanov.java8.algo.warmup;

import java.util.Arrays;

public class MiniMaxSolution {

    public static long[] miniMaxSum(int[] arr) {
        long[] sums = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int finalI = i;
            sums[i] = Arrays.stream(arr).mapToLong(e -> e).sum() - arr[i];
        }

        System.out.print(Arrays.stream(sums).min().getAsLong() + " ");
        System.out.print(Arrays.stream(sums).max().getAsLong());

        return sums;
    }
}
