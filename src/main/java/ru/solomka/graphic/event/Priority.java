package ru.solomka.graphic.event;

import lombok.Getter;

@Getter
public enum Priority {
    MONITOR(4),
    HIGH(3),
    NORMAL(2),
    LOW(1);

    private final int order;

    Priority(int order) {
        this.order = order;
    }
}