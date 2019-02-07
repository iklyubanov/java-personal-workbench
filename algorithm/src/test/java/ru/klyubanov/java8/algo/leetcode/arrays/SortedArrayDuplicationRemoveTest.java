package ru.klyubanov.java8.algo.leetcode.arrays;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedArrayDuplicationRemoveTest {

    @Test
    public void testRemoveDuplicates() {
        int[] exp = {1, 2, 3, 4, 4};
        int[] ar = {1, 1, 2, 3, 4};
        int lenght = SortedArrayDuplicationRemove.removeDuplicates(ar);
        Assert.assertArrayEquals(exp, ar);
        Assert.assertEquals(4, lenght);
    }
}