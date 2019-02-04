package ru.klyubanov.java8.algo.leetcode.sort;

public class MergeTwoSortedLists {
    public static class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
 
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = null;
        if(l1 == null && l2 == null) return null;
        if(l1 == null && l2 != null) {
            root = l2;
        } else if(l1 != null && l2 == null) {
            root = l1;
        }
        else if(l1 != null && l2 != null && l1.val < l2.val) {
            root = l1;
            root.next = mergeTwoLists(root.next, l2);
        }
        else {
            root = l2;
            root.next = mergeTwoLists(root.next, l1);
        }

        return root;
    }
}
