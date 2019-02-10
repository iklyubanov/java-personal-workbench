package ru.klyubanov.java8.algo.leetcode.arrays;

public class RemoveDuplicates {
    public static int removeElement(int[] nums, int val) {
        int n = nums.length;
        int newLen = n;
        if(n==0) return 0;
        if(n==1) {
            if(nums[0]==val) return 0;
        }

        for(int i=0; i<newLen; i++) {
            if(nums[i] == val) {
                for(int j=i+1; j<newLen; j++) {
                    nums[j-1] = nums[j];
                }

                newLen--;
                if(nums[i] == val) i--;
            }
        }
        return newLen;
    }
}
