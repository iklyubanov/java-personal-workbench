package ru.klyubanov.java8.algo.leetcode.arrays;

/**
 * Runtime: 6 ms, faster than 97.49% of Java online submissions for Remove Duplicates from Sorted Array.
 * Memory Usage: 30.5 MB, less than 33.13% of Java online submissions for
 * Remove Duplicates from Sorted Array.
 * */
public class SortedArrayDuplicationRemove {

    public static int removeDuplicates(int[] nums) {
        int ni = 0;

        for(int i=0; i<nums.length; i++) {
            if(ni>0 && nums[i] == nums[ni-1]) continue;
            nums[ni] = nums[i];
            ni++;
        }
        return ni;
    }
}
