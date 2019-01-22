package ru.klyubanov.java8.algo.leetcode;

public class ReverseInteger {
    //–2,147,483,648 to 2,147,483,647.
    public static int reverse(int x) {
        int divider = 10;
        int full = 0;
        while(true) {
            if(Math.abs(x / divider) > 0) {
                int current = x % divider - x % (divider/10);
                int newfull = full * 10 + current/ (divider/10);
                if((x > 0 && newfull < full) || (x < 0 && newfull > full)) {
                    full = 0;
                    break;
                } else {
                    full = newfull;
                }
                if(divider * 10 / 10 == divider)
                    divider *= 10;
                else if(((full * 10 + x/divider) - x/divider) / 10 != full) {
                    full = 0;
                    break;
                } else {
                    full = full * 10 + x / divider;
                    break;
                }


            } else {
                int current = x % divider - x % (divider/10);
                int newfull = full * 10 + current/ (divider/10);
                if((x > 0 && newfull < full) || (x < 0 && newfull > full)) {
                    full = 0;
                } else {
                    full = newfull;
                }
                break;
            }
        }

        return full;
    }
}
