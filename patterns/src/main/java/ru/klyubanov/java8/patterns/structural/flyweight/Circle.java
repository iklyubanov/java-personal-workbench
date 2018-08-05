package ru.klyubanov.java8.patterns.structural.flyweight;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Circle implements Shape {
    private int x;
    private int y;
    private double radius;
    private String color;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}
