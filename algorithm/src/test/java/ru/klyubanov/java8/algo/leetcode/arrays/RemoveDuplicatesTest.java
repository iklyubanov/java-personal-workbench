package ru.klyubanov.java8.algo.leetcode.arrays;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveDuplicatesTest {

    @Test
    public void testRemoveElement() {
        int[] ar = {3, 2, 2, 3};
        int[] exp = {2,2,3,3};
        int ns = RemoveDuplicates.removeElement(ar, 3);
        Assert.assertArrayEquals(exp, ar);
        Assert.assertEquals(2, ns);

    }
}