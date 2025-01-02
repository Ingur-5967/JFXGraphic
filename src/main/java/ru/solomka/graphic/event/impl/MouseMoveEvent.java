package ru.solomka.graphic.event.impl;

import lombok.Getter;
import ru.solomka.graphic.event.Event;
import ru.solomka.graphic.scene.item.Location;

@Getter
public class MouseMoveEvent extends Event {

    private final Location location;

    public MouseMoveEvent(Location location) {
        this.location = location;
    }
}