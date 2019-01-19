package ru.klyubanov.java8.algo.coursera.algorithm1.week1.q1;

public class SocialNetwork {
    private SocialNetwork instance;
    private SocialNetwork() {
    }

    public SocialNetwork getInstance(int n, int m) {
        if(instance == null) {
            instance = new SocialNetwork();
            instance.init(n, m);
        }
        return instance;
    }

    private void init(int n, int m) {

    }
}
