package ru.solomka.graphic.scene.item.tag;

import javafx.event.Event;
import javafx.event.EventType;

import java.util.function.Consumer;

public interface Interact {

    /**
     * Sets the handler for a specific event
     *
     * @param event Action before a call event
     * @param <E> Type of target event
     */

    <E extends Event> void setup(EventType<E> eventType, Consumer<E> event);
}