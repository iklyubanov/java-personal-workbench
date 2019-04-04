package ru.klyubanov.java8.algo.leetcode.bst;

import org.junit.Assert;
import org.junit.Test;

public class BalancedBinaryTreeTest {

    @Test
    public void testIsBalanced() {
        TreeNode root = new TreeNode(10);
        TreeNode r = new TreeNode(12);
        root.right = r;
        r.right = new TreeNode(14);
        boolean res = BalancedBinaryTree.isBalanced(root);
        Assert.assertFalse(res);
    }

    @Test
    public void testIsBalanced2() {
        TreeNode root = new TreeNode(10);
        TreeNode r = new TreeNode(12);
        root.right = r;
        TreeNode l = new TreeNode(2);
        root.left = l;
        r.right = new TreeNode(14);
        r.left = new TreeNode(11);
        boolean res = BalancedBinaryTree.isBalanced(root);
        Assert.assertTrue(res);
    }
}