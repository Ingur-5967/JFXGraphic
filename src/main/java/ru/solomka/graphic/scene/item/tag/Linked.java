package ru.solomka.graphic.scene.item.tag;

import ru.solomka.graphic.scene.item.SceneItem;

public interface Linked {
    SceneItem<?> get(String id);

    SceneItem<?> get(int position);
}
