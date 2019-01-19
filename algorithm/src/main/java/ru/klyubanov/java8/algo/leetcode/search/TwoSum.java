package ru.klyubanov.java8.algo.leetcode.search;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> hashtable = new HashMap<>(n);
        for(int i=0; i<n; i++) hashtable.put(nums[i], i);

        int[] result = new int[2];
        for(int i=0; i<n; i++) {
            Integer second = hashtable.get(target - nums[i]);

            if(second != null && i != second) {
                result[0] = i;
                result[1] = second;
                break;
            }
        }

        return result;
    }

    public static int[] fasterTwoSum(int[] nums, int target) {
        int n = nums.length;
        if(n <= 1) return new int[]{-1, -1};
        Map<Integer, Integer> hashtable = new HashMap<>(n);
        for(int i=0; i<n; i++) hashtable.put(nums[i], i);

        int[] result = new int[2];
        for(int i=0; i<n; i+=2) {
            Integer second = hashtable.get(target - nums[i]);

            if(second != null && i != second) {
                result[0] = i;
                result[1] = second;
                break;
            } else {
                second = hashtable.get(target - nums[i+1]);
                if(second != null && i+1 != second) {
                    result[0] = i+1;
                    result[1] = second;
                    break;
                }
            }
        }

        return result;
    }
}
