package ru.klyubanov.java8.algo.leetcode.convert;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RomanToIntegerTest {

    @Test
    public void testRomanToInt() {
        Assert.assertEquals(140, RomanToInteger.romanToInt("CXL"));
        Assert.assertEquals(1002, RomanToInteger.romanToInt("MII"));
    }
}