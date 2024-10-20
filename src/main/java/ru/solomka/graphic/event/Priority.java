package ru.solomka.graphic.event;

import lombok.Getter;

@Getter
public enum Priority {
    VERY_HIGH(1),
    HIGH(2),
    NORMAL(3),
    LOW(4);

    private final int priority;

    Priority(int priority) {
        this.priority = priority;
    }
}