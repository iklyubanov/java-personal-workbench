package ru.klyubanov.java8.algo.leetcode.convert;

/**
 * Runtime: 35 ms, faster than 99.16% of Java online submissions for Roman to Integer.
 * */
public class RomanToInteger {
    public static int romanToInt(String s) {
        char[] romans = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] digits = new int[]      { 1,   5,   10,  50, 100, 500, 1000};
        char prev = 0;

        int result = 0;

        for(int i=0; i < s.length(); i++) {
            char cur = s.charAt(i);
            for(int c=0; c < romans.length; c++) {
                if(cur == romans[c]) {
                    result += digits[c];
                    if(c > 0) for(int d=c-1; d >= 0 ; d--) {
                        if(d%2 == 0 && prev == romans[d]) {
                            result -= digits[d] * 2;
                            break;
                        }
                    }
                    prev = romans[c];
                    break;
                }
            }
        }

        return result;
    }
}
