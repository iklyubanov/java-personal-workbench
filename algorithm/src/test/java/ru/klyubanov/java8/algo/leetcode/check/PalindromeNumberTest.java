package ru.klyubanov.java8.algo.leetcode.check;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeNumberTest {

    @Test
    public void testIsPalindrome() {
        assertTrue(PalindromeNumber.isPalindrome(121));
        assertFalse(PalindromeNumber.isPalindrome(-121));
        assertTrue(PalindromeNumber.isPalindrome(1));
        assertTrue(PalindromeNumber.isPalindrome(1410110141));
        assertFalse(PalindromeNumber.isPalindrome(1410150141));
    }

    @Test
    public void testIsPalindromeReverse() {
        assertTrue(PalindromeNumber.isPalindromeReverse(121));
        assertFalse(PalindromeNumber.isPalindromeReverse(-121));
        assertTrue(PalindromeNumber.isPalindromeReverse(1));
        assertTrue(PalindromeNumber.isPalindromeReverse(1410110141));
        assertFalse(PalindromeNumber.isPalindromeReverse(1410150141));
    }
}