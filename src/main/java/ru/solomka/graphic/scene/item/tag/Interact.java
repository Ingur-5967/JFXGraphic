package ru.solomka.graphic.scene.item.tag;

import javafx.scene.Node;
import javafx.stage.Stage;
import ru.solomka.graphic.event.Event;
import ru.solomka.graphic.tool.Pair;

import java.util.function.BiConsumer;

public interface Interact {

    /**
     * Sets the handler for a specific event
     *
     * @param interact Pair with a primary stage and target node
     * @param <E>      Type of target event
     */

    <E extends Event> void setup(BiConsumer<E, Pair<Stage, Node>> interact);
}