package ru.klyubanov.java8.algo.leetcode.bst;

public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        if (n == 0) return null;

        TreeNode root = new TreeNode(nums[n / 2]);
        TreeNode cur = root;
        for (int i = 0; i < n; i++) {
            if (i == n / 2) continue;
            while (cur != null) {
                if (nums[i] < cur.val) {
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        cur.left = new TreeNode(nums[i]);
                        System.out.println("new cur.left=" + nums[i] + " of cur=" + cur.val);
                        break;
                    }
                } else {
                    if (cur.right != null) {
                        cur = cur.right;
                    } else {
                        cur.right = new TreeNode(nums[i]);
                        System.out.println("new cur.right=" + nums[i] + " of cur=" + cur.val);
                        break;
                    }
                }
            }
        }
        return root;
    }

    //correct
    public TreeNode recursiveSortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right)
            return null;

        int middle = (left + right) / 2;
        int val = nums[middle];

        TreeNode root = new TreeNode(val);

        root.left = buildBST(nums, left, middle - 1);
        root.right = buildBST(nums, middle + 1, right);

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
