package ru.solomka.graphic.scene.item;

import javafx.scene.Node;

public interface LazyComponent<C extends LazyComponent<?, T>, T extends Node> extends SceneItem {
    C preInit(SceneItem... entries);
}