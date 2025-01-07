package ru.solomka.graphic.scene.item.impl;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.LazyComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.base.BasePane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Interact;
import ru.solomka.graphic.scene.item.tag.Linked;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public final class LinkedPane extends BasePane implements LazyComponent<LinkedPane, AnchorPane>, Linked, Interact {

    public LinkedPane(double width, double height) {
        super(width, height);
    }

    @Override
    public LinkedPane preInit(SceneItem... entries) {
        List<SceneItem> nodes = Arrays.stream(Arrays.stream(entries)
                .map(SceneItem::getRoot)
                .map(Container::getChildren)
                .toArray(SceneItem[]::new)).toList();

        AnchorPane parent = this.getNode();

        parent.getChildren().addAll(
                nodes.stream()
                        .map(SceneItem::getRoot)
                        .map(Container::getChildren)
                        .map(element -> (Node) element)
                        .toList()
        );

        return this;
    }

    @Override
    public SceneItem get(String id) {
        return this.getSource().stream()
                .filter(item -> item.getNode().getId() != null && item.getNode().getId().equals(id))
                .findAny().orElse(null);
    }

    @Override
    public SceneItem get(int position) {
        return this.getSource().get(position);
    }

    @Override
    public <E extends Event> void setup(EventType<E> eventType, Consumer<E> event) {
        this.getNode().addEventHandler(eventType, event::accept);
    }
}
