package ru.solomka.graphic.scene.item.tag;

import javafx.scene.Node;
import ru.solomka.graphic.scene.item.SceneItem;

import java.util.List;

public interface Page {

    List<SceneItem<?>> getData();

    void setData(SceneItem<?>... items);

    <T extends SceneItem<? extends Node>> T current();

    <T extends SceneItem<? extends Node>> T next();

    <T extends SceneItem<? extends Node>> T previous();

    boolean hasNext();

    int getCurrentPage();
}