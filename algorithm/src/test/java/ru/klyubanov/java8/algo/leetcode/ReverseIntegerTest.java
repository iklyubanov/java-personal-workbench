package ru.klyubanov.java8.algo.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseIntegerTest {

    @Test
    public void testReverse() {
        Assert.assertEquals(321, ReverseInteger.reverse(123));
        Assert.assertEquals(0, ReverseInteger.reverse(Integer.MAX_VALUE));
    }
}