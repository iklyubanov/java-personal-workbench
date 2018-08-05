package ru.klyubanov.java8.algo;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.klyubanov.java8.algo.impl.StudentsGradeRounder;
import ru.klyubanov.java8.algo.warmup.MiniMaxSolution;
import ru.klyubanov.java8.algo.warmup.Staircase;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalLong;

public class AlgoTest {

    @Test
    public void testVeryBigSum() {
        int n = 5;
        long[] ar = {1000000001, 1000000002 ,1000000003, 1000000004, 1000000005};
        Arrays.asList(ar).sort();
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

    @Test
    public void testStaircase() {
        String expected = "     #\n" +
                "    ##\n" +
                "   ###\n" +
                "  ####\n" +
                " #####\n" +
                "######";

        Assert.assertEquals(expected, Staircase.printStaircase(6));
    }

    @Test
    public void testGradedStudents() {
        int[] ar = {73, 67 ,38, 33};
        Assert.assertArrayEquals(new int[]{75, 67 ,40, 33}, StudentsGradeRounder.gradingStudents(ar));
    }

    @Test
    public void testMiniMaxSum() {
        int[] ar = {1, 2 , 3, 4, 5};
        long[] sums = MiniMaxSolution.miniMaxSum(ar);
        Assert.assertEquals(10, Arrays.stream(sums).min().getAsLong());
        Assert.assertEquals(14, Arrays.stream(sums).max().getAsLong());
    }

    @Test
    public void testMiniMaxSum2() {
        int[] ar = {1, 2 , 3, 4, 0, 5};
        long[] sums = MiniMaxSolution.miniMaxSum(ar);
        Assert.assertEquals(10, Arrays.stream(sums).min().getAsLong());
        Assert.assertEquals(15, Arrays.stream(sums).max().getAsLong());
    }

    @Ignore
    @Test
    public void testMiniMaxSum3() {
        int[] ar = {1, 2 , 3, 4, 5, 5};
        long[] sums = MiniMaxSolution.miniMaxSum(ar);
        Assert.assertEquals(10, Arrays.stream(sums).min().getAsLong());
        Assert.assertEquals(19, Arrays.stream(sums).max().getAsLong());
    }

    @Test
    public void testMiniMaxSum4() {
        int[] ar = {256741038, 623958417, 467905213, 714532089, 938071625};
        long[] sums = MiniMaxSolution.miniMaxSum(ar);
        //
        Assert.assertEquals(2063136757, Arrays.stream(sums).min().getAsLong());
        Assert.assertEquals(2744467344L, Arrays.stream(sums).max().getAsLong());
    }

    @Test
    public void testMiniMaxSum5() {
        int[] ar = {5, 5 , 5, 5, 5};
        long[] sums = MiniMaxSolution.miniMaxSum(ar);
        Assert.assertEquals(20, Arrays.stream(sums).min().getAsLong());
        Assert.assertEquals(20, Arrays.stream(sums).max().getAsLong());
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
