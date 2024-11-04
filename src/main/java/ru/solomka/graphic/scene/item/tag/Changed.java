package ru.solomka.graphic.scene.item.tag;

public interface Changed<T> {
    void change(T newState);
    T getState();
}
