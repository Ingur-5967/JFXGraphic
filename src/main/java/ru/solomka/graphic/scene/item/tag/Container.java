package ru.solomka.graphic.scene.item.tag;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ru.solomka.graphic.scene.item.LazyComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.base.BasePane;
import ru.solomka.graphic.style.CssStyle;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Container {

    /**
     * Adds {@code item} non-abstract element to the container
     *
     * @param item Target element to add to local container
     * @see BasePane
     */
    void addChildren(Node item);

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
    default void addChildren(SceneItem<?> item) {
    }

    /**
     * Returns abstract content the container
     *
     * @return Returns abstract content the container
     * @see SceneItem
     * @see BasePane
     */
    default List<SceneItem<?>> getSource() {
        return Collections.emptyList();
    }

    <P extends Pane> P getBaseRegion();

    @SuppressWarnings("unchecked")
    static <I extends Container> Container fromSource(Class<I> instance, Parent region, Object[] properties) {

        if (!(region instanceof Pane root))
            throw new IllegalArgumentException("Pane support only nodes which are inherited from Pane");

        if (properties == null)
            properties = new Object[]{root.getPrefWidth(), root.getPrefHeight()};

        I loader;
        try {
            loader = (I) instance.getDeclaredConstructors()[0].newInstance(properties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        BasePane layout = (BasePane) loader;
        layout.setLocation(region.getLayoutX(), region.getLayoutY());
        layout.initStyle(CssStyle.create(region.getStyle()));

        List<SceneItem<?>> items = layout.getChildren().stream().filter(c -> c instanceof AnchorPane).map(SceneItem::fromSource).collect(Collectors.toList());

        return items.isEmpty() ? layout : ((LazyComponent<? extends Container, ? extends Node>) layout).preInit(items.toArray(SceneItem<?>[]::new));
    }
}