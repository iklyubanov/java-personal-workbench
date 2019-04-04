package ru.klyubanov.java8.algo.leetcode.bst;

public class BalancedBinaryTree {
    public static boolean isBalanced(TreeNode root) {
        int res = countDepth(root);
        return res != -1;
    }

    private static int countDepth(TreeNode n) {
        if(n == null) return 0;
        int l = countDepth(n.left);
        if(l == -1) return -1;
        int r = countDepth(n.right);
        if(r == -1) return -1;
        if(Math.abs(r-l)>1) return -1;
        return Math.max(r, l) + 1;
    }
}
