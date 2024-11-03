package ru.solomka.graphic.scene.item.tag;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;
import ru.solomka.graphic.tool.Pair;

import java.util.function.BiConsumer;

public interface Interact {
    <E extends Event> void setup(BiConsumer<E, Pair<Stage, Node>> interact);
}