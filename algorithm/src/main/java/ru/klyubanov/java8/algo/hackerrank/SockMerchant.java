package ru.klyubanov.java8.algo.hackerrank;

import java.util.HashMap;
import java.util.Map;

public class SockMerchant {

    // Complete the sockMerchant function below.
    static int sockMerchant(int[] ar) {
        int counter = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < ar.length; i++) {
            Integer val = map.get(ar[i]);
            val = val != null? val + 1 : 1;
            map.put(ar[i], val);
            if(val > 0 && val % 2 == 0) counter++;
        }
        return counter;
    }
}
