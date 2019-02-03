package ru.klyubanov.java8.algo.leetcode.check;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidParenthesesTest {

    @Test
    public void testIsValid() {
        Assert.assertTrue(ValidParentheses.isValid("{}[](({()}))"));
    }
}