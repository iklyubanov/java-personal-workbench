package ru.klyubanov.java8.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class CircleFactory implements ShapeFactory {
    private Map<String, Circle> circlesCache = new HashMap<>();
    private static CircleFactory instance;

    private CircleFactory() {
    }

    public static CircleFactory getInstance() {
        if (instance == null) {
            instance = new CircleFactory();
        }
        return instance;
    }

    public Circle getShape(String color) {
        Circle circle = circlesCache.get(color);
        if (circle == null) {
            circle = new Circle(color);
            circlesCache.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
