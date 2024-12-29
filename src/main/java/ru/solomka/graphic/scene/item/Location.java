package ru.solomka.graphic.scene.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private double x;
    private double y;

    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }
}