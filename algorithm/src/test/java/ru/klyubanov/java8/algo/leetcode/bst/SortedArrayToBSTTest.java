package ru.klyubanov.java8.algo.leetcode.bst;

import org.junit.Assert;
import org.junit.Test;

public class SortedArrayToBSTTest {

    @Test
    public void testSortedArrayToBST() {
        int[] ar = {0,1,2,3,4,5,6,7,8,9};
        TreeNode treeNode = new SortedArrayToBST().sortedArrayToBST(ar);
        Assert.assertNotNull(treeNode);
    }

    @Test
    public void testRecursiveSortedArrayToBST() {
        int[] ar = {0,1,2,3,4,5,6,7,8,9};
        TreeNode treeNode = new SortedArrayToBST().recursiveSortedArrayToBST(ar);
        Assert.assertNotNull(treeNode);
    }
}