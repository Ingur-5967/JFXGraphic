package ru.solomka.graphic.event;

public interface Cancellable {
    void setCancelled(boolean cancelled);
    boolean isCancelled();
}