package ru.klyubanov.java8.algo.leetcode.ds;

import org.junit.Assert;
import org.junit.Test;

public class AddTwoNumbersTest {

    @Test
    public void testAddTwoNumbers() {
        AddTwoNumbers numbers = new AddTwoNumbers();
        AddTwoNumbers.ListNode l1 = numbers.new ListNode(5);
        AddTwoNumbers.ListNode l2 = numbers.new ListNode(5);
        AddTwoNumbers.ListNode result = numbers.addTwoNumbers(l1, l2);
        Assert.assertTrue(result.val == 0 && result.next.val == 1);
    }
}