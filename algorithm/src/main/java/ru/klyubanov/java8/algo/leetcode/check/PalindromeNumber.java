package ru.klyubanov.java8.algo.leetcode.check;

public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if(x < 0) return false;
        if(x/10 == 0) return true;

        long div = 100;
        int left = 2;
        int right = 1;
        while(x/div > 0) {
            div*=10; left++;
        }
        while(left > right) {

            long rightDig = x % (long)Math.pow(10, right);
            if(right>1) rightDig = rightDig / (long)Math.pow(10, right-1) ;
            long leftDig = x % (long)Math.pow(10, left) / (long)Math.pow(10, left-1);
            if(rightDig!=leftDig) return false;
            left--; right++;
        }

        return true;
    }

    public static boolean isPalindromeReverse(int x) {
        if(x < 0) return false;
        int rev=0;
        int n = x;
        while(n>0){
            rev = rev*10 + n%10;
            n = n/10;
        }
        return x == rev;
    }
}
