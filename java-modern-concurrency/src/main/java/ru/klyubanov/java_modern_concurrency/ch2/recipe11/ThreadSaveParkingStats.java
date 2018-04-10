package ru.klyubanov.java_modern_concurrency.ch2.recipe11;

import lombok.Data;

@Data
public class ThreadSaveParkingStats extends ParkingStats{
    private final Object controlCars, controlMotorcycles;


    public ThreadSaveParkingStats(ParkingCash cash) {
        super(cash);
        controlCars = new Object();
        controlMotorcycles = new Object();
    }

    @Override
    public void carComeIn() {
        synchronized (controlCars) {
            numberCars++;
        }
    }

    @Override
    public void carGoOut() {
        synchronized (controlCars) {
            numberCars--;
        }

        cash.vehiclePay();
    }

    @Override
    public void motoComeIn() {
        synchronized (controlMotorcycles) {
            numberMotorcycles++;
        }
    }

    @Override
    public void motoGoOut() {
        synchronized (controlMotorcycles) {
            numberMotorcycles--;
        }
        cash.vehiclePay();
    }
}
