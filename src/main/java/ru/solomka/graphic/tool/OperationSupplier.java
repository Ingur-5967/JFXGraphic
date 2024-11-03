package ru.solomka.graphic.tool;

@FunctionalInterface
public interface OperationSupplier<T> {
    T operate(T join);
}