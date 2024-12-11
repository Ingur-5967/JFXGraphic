package ru.solomka.graphic.scene.item.tag.concept;

import java.util.function.Predicate;

public interface ObjectFilter<T> {
    void setFilterPrincipe(Predicate<T> criteria);

    boolean validateWithPrincipe(T object);
}
