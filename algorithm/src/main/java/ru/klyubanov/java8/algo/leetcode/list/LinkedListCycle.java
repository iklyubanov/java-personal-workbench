package ru.klyubanov.java8.algo.leetcode.list;


import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static boolean hasCycle(ListNode head) {
        return hasCycleWithExtraSpace(head);
    }

    public static boolean hasCycleWithExtraSpace(ListNode head) {
        Set<Integer> visited = new HashSet<>();
        while(head != null) {
            System.out.println("new="+ head.val);
            System.out.println("head="+ head);
            System.out.println("head.next="+ head.next);
            if(!visited.contains(head.val)) {visited.add(head.val); }
            else return true;
            head = head.next;
        }
        return false;
    }
}
