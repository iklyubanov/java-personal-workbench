package ru.klyubanov.java8.algo.leetcode.sort;

import java.util.HashSet;
import java.util.Set;

public class ParityTwoSortArray {

    public static int[] sort(int[] A) {
        int front = 0, back = A.length -1;
        int[] ans = new int[A.length];

        for (int aA : A) {
            if (aA % 2 == 0) {
                ans[front] = aA;
                front = front + 2;
            } else {
                ans[back] = aA;
                back = back - 2;
            }
        }
        return ans;
    }

    public static int[] sortArrayByParityII(int[] A) {
        int[] result = new int[A.length];
        Set<Integer> mapped = new HashSet<>();
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A.length; j++) {
                if((!isOdd(A[j]) && !isOdd(i) && !mapped.contains(j)) || (isOdd(A[j]) && isOdd(i) && !mapped.contains(j))) {
                    mapped.add(j);
                    result[i] = A[j];
                    break;
                }
            }
        }
        return result;
    }

    static boolean isOdd(int e) {
        return e % 2 == 1;
    }
}
