package ru.klyubanov.java_modern_concurrency.ch2.recipe11;

public class ParkingCash {
    protected static final int cost=2;
    protected long cash;

    public void vehiclePay() {
        cash+=cost;
    }

    public void close() {
        System.out.print("Closing accounting");
        long totalAmmount=cash;
        cash=0;
        System.out.println("The total amount is : " + totalAmmount);
    }
}
