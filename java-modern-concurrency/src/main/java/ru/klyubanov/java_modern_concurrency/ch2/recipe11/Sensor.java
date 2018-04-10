package ru.klyubanov.java_modern_concurrency.ch2.recipe11;

import com.rainerhahnekamp.sneakythrow.Sneaky;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class Sensor implements Runnable{
    private final ParkingStats stats;


    @Override
    public void run() {
        for (int i = 0; i< 10; i++) {
            stats.carComeIn();
            stats.carComeIn();
            Sneaky.sneaked(() -> TimeUnit.MILLISECONDS.sleep(50));
            stats.motoComeIn();
            Sneaky.sneaked(() -> TimeUnit.MILLISECONDS.sleep(50));
            stats.motoGoOut();
            stats.carGoOut();
            stats.carGoOut();
        }
    }
}
