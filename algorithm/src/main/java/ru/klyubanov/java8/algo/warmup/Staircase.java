package ru.klyubanov.java8.algo.warmup;

import java.util.StringJoiner;

public class Staircase {

    public static String printStaircase(int n) {
        StringJoiner result = new StringJoiner("\n");
        StringBuilder stairs = new StringBuilder();
        StringBuilder emptySpaces = new StringBuilder();

        for (int i = 0; i < n; i++) {
            emptySpaces.append(" ");
        }

        for (int i = 1; i <= n; i++) {
            stairs.append("#");
            emptySpaces.deleteCharAt(0);
            result.add(emptySpaces + stairs.toString());
            System.out.println(emptySpaces + stairs.toString());
        }

        return result.toString();
    }
}
