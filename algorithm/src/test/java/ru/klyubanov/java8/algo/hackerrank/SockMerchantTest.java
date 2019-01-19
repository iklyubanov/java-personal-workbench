package ru.klyubanov.java8.algo.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class SockMerchantTest {

    @Test
    public void testSockMerchant() {
        int[] ar = new int[]{10, 1, 2, 2, 10,7, 15, 7};
        Assert.assertEquals(3, SockMerchant.sockMerchant(ar));
    }

    @Test
    public void testSockMerchant50() {
        int[] ar = new int[]{42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42};
        Assert.assertEquals(50, SockMerchant.sockMerchant(ar));
    }


}