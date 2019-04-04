package ru.klyubanov.java8.algo.leetcode.bst;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeLevelOrderTraversalTest {

    @Test
    public void testLevelOrderBottom() {
        int[] ar = {0,1,2,3,4,5,6,7,8,9};
        TreeNode treeNode = new SortedArrayToBST().sortedArrayToBST(ar);

        List<List<Integer>> dlist = BinaryTreeLevelOrderTraversal.levelOrderBottom(treeNode);
        Assert.assertEquals(10, dlist.size());
    }
}