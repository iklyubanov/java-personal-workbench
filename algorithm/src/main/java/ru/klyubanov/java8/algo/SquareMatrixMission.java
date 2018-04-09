package ru.klyubanov.java8.algo;

public class SquareMatrixMission {
    static int diagonalDifference(int[][] a) {
        int leftDiagonalSum = 0;
        int rightDiagonalSum = 0;
        for(int i=0; i<a.length; i++) {
            leftDiagonalSum += a[i][i];
            rightDiagonalSum += a[i][a.length-1-i];
        }
        return Math.abs(leftDiagonalSum - rightDiagonalSum);
    }
}
