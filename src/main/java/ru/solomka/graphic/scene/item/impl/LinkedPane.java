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

        if (this.getRoot() == null)
            throw new NullPointerException("Root region cannot be null");
    }

    /**
     * Resize root element by the specified element
     *
     * @param element target element for which there will be a resizing
     */
    public void setAdaptiveSize(SceneItem<?> element) {
        if (element.getSize().getWidth() <= 0 || element.getSize().getHeight() <= 0)
            throw new IllegalArgumentException("Size cannot be zero or negative.");

        this.getRoot().getBaseRegion().setPrefSize(element.getSize().getWidth(), element.getSize().getHeight());
    }

    @Override
    public LinkedPane preInit(SceneItem<?>... entries) {
        List<SceneItem<?>> nodes = Arrays.stream(Arrays.stream(entries)
                .map(SceneItem::getRoot)
                .map(Container::getChildren)
                .toArray(SceneItem<?>[]::new)).toList();

        AnchorPane parent = this.getRoot().getBaseRegion();

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
    public SceneItem<?> get(String id) {
        return this.getSource().stream()
                .filter(node -> node.getRoot().getBaseRegion().getId() != null && node.getRoot().getBaseRegion().getId().equals(id))
                .findAny().orElse(null);
    }

    @Override
    public SceneItem<?> get(int position) {
        return this.getSource().get(position);
    }

    @Override
    public Container getRoot() {
        return super.getRoot();
    }

    @Override
    public <E extends Event> void setup(EventType<E> eventType, Consumer<E> event) {
        this.getBaseRegion().addEventHandler(eventType, event::accept);
    }
}
