package ru.solomka.graphic.scene.item.tag;

import ru.solomka.graphic.scene.item.SceneItem;

public interface Linked {

    /**
     * @param id string id the target node
     * @return Returns SceneItem that has the same string id
     */

    SceneItem<?> get(String id);

    /**
     * @param position string id the target node
     * @return Returns the SceneItem that is at the specified position
     */

    SceneItem<?> get(int position);
}
