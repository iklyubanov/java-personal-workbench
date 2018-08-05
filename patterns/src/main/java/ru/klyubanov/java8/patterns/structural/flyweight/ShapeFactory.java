package ru.klyubanov.java8.patterns.structural.flyweight;

public interface ShapeFactory {
    Shape getShape(String color);
}
