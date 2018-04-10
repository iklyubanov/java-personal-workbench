package ru.klyubanov.java_modern_concurrency.ch2.recipe11;

public class ThreadSafeParkingCash extends ParkingCash{

    @Override
    public synchronized void vehiclePay() {
        cash+=cost;
    }

    @Override
    public void close() {
        System.out.print("Closing accounting");
        long totalAmmount;
        synchronized (this) {
            totalAmmount = cash;
            cash = 0;
        }
        System.out.println("The total amount is : " + totalAmmount);
    }
}
