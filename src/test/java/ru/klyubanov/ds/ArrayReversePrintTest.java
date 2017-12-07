package ru.klyubanov.ds;

import org.junit.Test;

import java.util.Arrays;

public class ArrayReversePrintTest {

    private static Integer i;
    static {
        i = 1;
    }
    private static Integer i2 = i + 1;

    @Test
    public void test() {
        int[] array = {1, 5, 6, 7, 2, 4, 6, 3};
        for(int i=array.length-1; i>=0; i--) {
            System.out.print(array[i] + " ");
        }
    }
}
