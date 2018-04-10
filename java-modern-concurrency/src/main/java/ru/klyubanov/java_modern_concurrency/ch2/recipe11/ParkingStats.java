package ru.klyubanov.java_modern_concurrency.ch2.recipe11;

import lombok.Data;

@Data
public class ParkingStats {
    protected long numberCars;
    protected long numberMotorcycles;
    protected ParkingCash cash;

    public ParkingStats(ParkingCash cash) {
        this.cash = cash;
    }

    public void carComeIn() {
        numberCars++;
    }

    public void carGoOut() {
        numberCars--;
        cash.vehiclePay();
    }

    public void motoComeIn() {
        numberMotorcycles++;
    }

    public void motoGoOut() {
        numberMotorcycles--;
        cash.vehiclePay();
    }
}
