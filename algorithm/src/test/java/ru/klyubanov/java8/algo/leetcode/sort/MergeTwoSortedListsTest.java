package ru.klyubanov.java8.algo.leetcode.sort;

import org.junit.Assert;
import org.junit.Test;


public class MergeTwoSortedListsTest {

    @Test
    public void testMergeTwoLists() {
        MergeTwoSortedLists.ListNode listNode = new MergeTwoSortedLists.ListNode(1);
        listNode.next =new MergeTwoSortedLists.ListNode(2);
        MergeTwoSortedLists.ListNode another = new MergeTwoSortedLists.ListNode(2);
        Assert.assertEquals(1, MergeTwoSortedLists.mergeTwoLists(listNode, another).val);
    }
}