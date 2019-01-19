package ru.klyubanov.java8.algo.leetcode.sort;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ParityTwoSortArrayTest {

    private static int[] bigArray;

    @BeforeClass
    public static void prepareBigArray() throws IOException, URISyntaxException {
        String content = readFile();
        String[] strArray = content.split(",");
        bigArray = Arrays.stream(strArray).mapToInt(Integer::parseInt).toArray();
    }

    private static String readFile() throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("odd_n_even.txt").toURI())), "UTF-8");
    }

    @Test
    public void testSort() {
        int[] A = {4, 2, 5, 7, 1, 3, 8, 10};
        int[] sorted = ParityTwoSortArray.sort(A);
        for (int i = 0; i < sorted.length; i += 2) {
            Assert.assertFalse(ParityTwoSortArray.isOdd(sorted[i]));
            Assert.assertTrue(ParityTwoSortArray.isOdd(sorted[i + 1]));
        }
    }

    @Test
    public void testSortArrayByParityII() {
        int[] A = {4, 2, 5, 7, 1, 3, 8, 10};
        int[] expected = {4, 5, 2, 7, 8, 1, 10, 3};
        int[] sorted = ParityTwoSortArray.sortArrayByParityII(A);
        Assert.assertArrayEquals(expected, sorted);
    }

    @Test
    public void testSortLong() {
        int[] sorted = ParityTwoSortArray.sort(bigArray);
        for (int i = 0; i < sorted.length; i += 2) {
            Assert.assertFalse(ParityTwoSortArray.isOdd(sorted[i]));
            Assert.assertTrue(ParityTwoSortArray.isOdd(sorted[i + 1]));
        }
    }

    @Test
    public void testSortArrayByParityIILong() {
        int[] sorted = ParityTwoSortArray.sort(bigArray);
        for (int i = 0; i < sorted.length; i += 2) {
            Assert.assertFalse(ParityTwoSortArray.isOdd(sorted[i]));
            Assert.assertTrue(ParityTwoSortArray.isOdd(sorted[i + 1]));
        }
    }
}