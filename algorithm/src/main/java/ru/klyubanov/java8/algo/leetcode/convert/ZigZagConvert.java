package ru.klyubanov.java8.algo.leetcode.convert;

import java.util.*;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * */
public class ZigZagConvert {

    public static String convert(String s, int numRows) {
        int n = s.length();

        List<Deque<Character>> queues = prepareQueues(s, numRows);
        StringBuilder sb = new StringBuilder(n);
        for(int i=0; i < queues.size(); i++) {
            Iterator<Character> it = queues.get(i).iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
        }

        return sb.toString();
    }

    private static List<Deque<Character>> prepareQueues(String s, int numRows) {
        int n = s.length();
        List<Deque<Character>> queues = new ArrayList<>(numRows);

        for(int i=0; i < numRows; i++) {
            queues.add(new ArrayDeque<>());
        }

        boolean increment = true;
        int index = 0;
        for(int i=0; i < n; i++) {
            queues.get(index).add(s.charAt(i));
            if(numRows != 1) {
                if(index == numRows-1) increment = false;
                else if(index == 0) increment = true;

                if(increment) index++;
                else index--;
            }

        }
        return queues;
    }
}
