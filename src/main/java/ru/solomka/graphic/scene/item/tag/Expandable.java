package ru.solomka.graphic.scene.item.tag;

import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.enums.ItemAlignment;

public interface Expandable<T extends SceneItem<?>> {
    void addChild(T element, ItemAlignment alignment);
}