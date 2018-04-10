package ru.klyubanov.java8.algo;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalLong;

public class AlgoTest {

    @Test
    public void testVeryBigSum() {
        int n = 5;
        long[] ar = {1000000001, 1000000002 ,1000000003, 1000000004, 1000000005};
        Assert.assertEquals(5000000015L, aVeryBigSum(n, ar));
        Assert.assertEquals(5000000015L, aVeryBigSum2(n, ar));
    }

    @Test
    public void testDiagonalDifference() {
        int[][] ar = {{11, 2, 4}, {4, 5, 6},{10, 8, -12}};
        Assert.assertEquals(15, SquareMatrixMission.diagonalDifference(ar));
    }

    @Test
    public void testPlusMinusFractions() {
        int[] ar = {-3, 0 ,9, -2, 0};
        PlusMinusFractions.Counter counter = PlusMinusFractions.plusMinus(ar);
        Assert.assertEquals(1, counter.getPCount());
        Assert.assertEquals(2, counter.getMCount());
        Assert.assertEquals(2, counter.getZCount());
        Assert.assertEquals(0.2, (double)counter.getPCount() / ar.length, 2);
        Assert.assertEquals(0.4, (double)counter.getMCount()/ ar.length, 2);
        Assert.assertEquals(0.4, (double)counter.getZCount()/ ar.length, 2);
    }

    static long aVeryBigSum(int n, long[] ar) {
        Optional<BigInteger> opInt = Arrays.stream(ar).mapToObj(i -> new BigInteger(String.valueOf(i))).reduce(BigInteger::add);
        return opInt.map(BigInteger::longValue).orElse(0L);
    }

    static long aVeryBigSum2(int n, long[] ar) {
        OptionalLong opInt = Arrays.stream(ar).reduce((a, b) -> a + b);
        return opInt.orElse(0L);
    }
}
