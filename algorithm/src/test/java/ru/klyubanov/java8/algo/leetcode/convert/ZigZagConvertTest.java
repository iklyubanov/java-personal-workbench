package ru.klyubanov.java8.algo.leetcode.convert;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZigZagConvertTest {

    @Test
    public void testConvert() {
        Assert.assertEquals("PAYPALISHIRING", ZigZagConvert.convert("PAYPALISHIRING", 1));
        Assert.assertEquals("PHASIYIRPLIGAN", ZigZagConvert.convert("PAYPALISHIRING", 5));
    }
}