package ru.klyubanov.java_modern_concurrency.ch3.recipe18;

public class Main {
    public static void main(String[] args) {
        Videoconference videoconference = new Videoconference(10);
        Thread threadConference=new Thread(videoconference);
        threadConference.start();
        for (int i=0; i<10; i++){
            Participant p=new Participant(videoconference, "Participant "+i);
            Thread t=new Thread(p);
            t.start();
        }
    }
}
