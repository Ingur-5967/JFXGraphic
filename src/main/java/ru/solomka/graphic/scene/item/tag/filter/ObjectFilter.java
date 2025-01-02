package ru.solomka.graphic.scene.item.tag.filter;

import java.util.function.Predicate;

public interface ObjectFilter<T> {
    void setFilterPrincipe(Predicate<T> criteria);
    boolean validateWithPrincipe(T object);
}
