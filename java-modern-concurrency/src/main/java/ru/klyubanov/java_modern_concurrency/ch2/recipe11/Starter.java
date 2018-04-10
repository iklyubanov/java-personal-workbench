package ru.klyubanov.java_modern_concurrency.ch2.recipe11;

public class Starter {


    public static void main(String[] args) {
        ParkingCash cash = new ThreadSafeParkingCash();//new ParkingCash();
        ParkingStats stats = new ThreadSaveParkingStats(cash);//ParkingStats(cash);
        execute(cash, stats);
    }

    /**
     * Using that aproach we had race conditions, and the different shared variables accessed by all the threads gave
     incorrect results.
     * */
    private static void execute(ParkingCash cash, ParkingStats stats) {

        System.out.print("Parking Simulator");

        int numberSensors=2 * Runtime.getRuntime()
                .availableProcessors();
        Thread threads[]=new Thread[numberSensors];
        for (int i = 0; i<numberSensors; i++) {
            Sensor sensor=new Sensor(stats);
            Thread thread=new Thread(sensor);
            thread.start();
            threads[i]=thread;
        }
//Then wait for the finalization of the threads using the join() method
        for (int i=0; i<numberSensors; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Number of cars: %d\n",
                stats.getNumberCars());
        System.out.printf("Number of motorcycles: %d\n",
                stats.getNumberMotorcycles());
        cash.close();
    }
}
