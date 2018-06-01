package ru.klyubanov.java_modern_concurrency.ch3.recipe19;

/**
 * This class will store, in an array, the
 * number of occurrences of the searched number in each row of the matrix
 * */
public class Results {
    private final int data[];

    public Results(int size){
        data=new int[size];
    }

    public void setData(int position, int value){
        data[position]=value;
    }

    public int[] getData(){
        return data;
    }
}
