package ru.klyubanov.java8.algo.impl;

public class StudentsGradeRounder {

    public static int[] gradingStudents(int[] grades) {
        int[] rounded = new int[grades.length];

        for (int i = 0; i < grades.length; i++) {
            if(grades[i] >= 38 && grades[i] % 5 >= 3) {
                rounded[i] = grades[i] + (5 - grades[i] % 5);
            } else {
                rounded[i] = grades[i];
            }
        }

        return rounded;
    }
}
