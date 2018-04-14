package ru.klyubanov.java_modern_concurrency.ch1.recipe6;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Event {
    private LocalTime time;
    private String type;
}
