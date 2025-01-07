package ru.solomka.graphic.scene.item.tag;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import ru.solomka.graphic.scene.item.LazyComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.base.BasePane;
import ru.solomka.graphic.style.CssStyle;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Container {

    /**
     * Adds {@code item} non-abstract element to the container
     *
     * @param item Target element to add to local container
     * @see BasePane
     */
    void addChildren(Node item);

    @SuppressWarnings("unchecked")
    static <I extends Container> Container fromSource(Class<I> instance, Pane region, Object[] properties) {

        if (region == null)
            throw new NullPointerException("Parent cannot be null");


        if (properties == null)
            properties = new Object[]{region.getPrefWidth(), region.getPrefHeight()};

        I loader;
        try {
            loader = (I) instance.getDeclaredConstructors()[0].newInstance(properties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        BasePane layout = (BasePane) loader;
        layout.setLocation(region.getLayoutX(), region.getLayoutY());
        layout.initStyle(CssStyle.create(region.getStyle()));

        List<SceneItem> items = layout.getChildren().stream().map(SceneItem::fromSource).toList();

        return items.isEmpty() ? layout : ((LazyComponent<? extends Container, ? extends Node>) layout).preInit(items.toArray(SceneItem[]::new));
    }

    void removeChildren(Node item);

    /**
     * Returns non-abstract content the container
     *
     * @return Returns non-abstract content the container
     */
    List<Node> getChildren();

    /**
     * Adds new child to abstract container
     *
     * @param item Target item for add
     * @see BasePane
     */
    void addChildren(SceneItem item);

    /**
     * Returns abstract content the container
     *
     * @return Returns abstract content the container
     * @see SceneItem
     * @see BasePane
     */
    List<SceneItem> getSource();
}