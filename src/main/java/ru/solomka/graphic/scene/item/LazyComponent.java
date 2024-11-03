package ru.solomka.graphic.scene.item;

import javafx.scene.Node;
import ru.solomka.graphic.tool.OperationSupplier;
import ru.solomka.graphic.tool.Pair;

import java.util.List;

public interface LazyComponent<C extends LazyComponent<?, T>, T extends Node> extends SceneItem<T> {
    C preInit(OperationSupplier<Pair<T, List<SceneItem<?>>>> edit, SceneItem<?>... entries);

    C preInit(SceneItem<?>... entries);
}