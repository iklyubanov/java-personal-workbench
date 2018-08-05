package ru.klyubanov.java8.patterns.structural.flyweight;

import java.util.stream.IntStream;

public class ExecutionFlow {
    private static final String colors[] = {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {
        IntStream.range(0, 20).forEach(i -> {
            Circle circle = CircleFactory.getInstance().getShape(getRandomColor());
            circle.setX(getRandomCoordinate());
            circle.setY(getRandomCoordinate());
            circle.setRadius(100);
            circle.draw();
        });
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomCoordinate() {
        return (int) (Math.random() * 100);
    }
}
