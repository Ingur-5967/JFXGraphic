package ru.solomka.graphic.event;

public interface Cancellable {
    boolean isCancelled();
    void setCancelled(boolean cancelled);
}