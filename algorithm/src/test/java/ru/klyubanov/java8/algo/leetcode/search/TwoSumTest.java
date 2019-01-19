package ru.klyubanov.java8.algo.leetcode.search;

import org.junit.Assert;
import org.junit.Test;

public class TwoSumTest {

    @Test
    public void testTwoSum() {
        int[] nums = {3, 2, 4};
        int[] result = TwoSum.twoSum(nums, 6);
        int[] exp = {1, 2};
        Assert.assertArrayEquals(exp, result);
    }


}