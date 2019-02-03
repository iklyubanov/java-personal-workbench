package ru.klyubanov.java8.algo.leetcode.check;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {

    public static boolean isValid(String s) {
        char[] open = new char[] {'(', '{', '['};
        char[] close = new char[] {')', '}', ']'};
        int n = s.length();
        Deque<Character> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {//push pop peek
            char cur = s.charAt(i);
            Character prev = deque.peek();
            if(cur != open[0] && cur != open[1] && cur != open[2] && cur != close[0] && cur != close[1] && cur != close[2])
                continue;
            if(cur == open[0] || cur == open[1] || cur == open[2])
                deque.push(cur);
            else if(prev != null && ((prev == open[0] && cur == close[0]) || (prev == open[1] && cur == close[1]) || (prev == open[2] && cur == close[2]))) {
                deque.pop();
            }
            else return false;
        }
        if(!deque.isEmpty()) return false;
        return true;
    }
}
