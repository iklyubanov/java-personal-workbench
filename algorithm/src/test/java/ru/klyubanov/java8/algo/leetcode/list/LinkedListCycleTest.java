package ru.klyubanov.java8.algo.leetcode.list;

import org.junit.Assert;
import org.junit.Test;
import ru.klyubanov.java8.algo.leetcode.list.LinkedListCycle.ListNode;

public class LinkedListCycleTest {

    @Test
    public void testHasCycle() {
    }

    @Test
    public void testHasCycleWithExtraSpace() {
        ListNode head = initList();
        boolean result = LinkedListCycle.hasCycleWithExtraSpace(head);
        Assert.assertTrue(result);
    }

    private ListNode initList() {
        int[] ar = new int[]{10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5};
        ListNode head = new ListNode(-21);
        ListNode next = head;
        for(int e : ar) {
            next.next = new ListNode(e);
        }
        return head;
    }
}