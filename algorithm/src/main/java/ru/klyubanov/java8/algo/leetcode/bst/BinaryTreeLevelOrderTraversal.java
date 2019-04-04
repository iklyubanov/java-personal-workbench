package ru.klyubanov.java8.algo.leetcode.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> dlist = new ArrayList<>();
        int depth = 0;
        traverse(root, dlist, depth);
        Collections.reverse(dlist);
        return dlist;
    }

    static void traverse(TreeNode node, List<List<Integer>> dlist, int depth) {
        if(node == null) return;
        if(dlist.size()-1 < depth) {
            dlist.add(depth, new LinkedList<>());
        }
        dlist.get(depth).add(node.val);
        depth++;
        traverse(node.left, dlist, depth);
        traverse(node.right, dlist, depth);
    }
}
