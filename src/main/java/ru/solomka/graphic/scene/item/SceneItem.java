package ru.solomka.graphic.scene.item;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.impl.base.BaseButton;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

public interface SceneItem<T extends Node> {

    /**
     * Sets element styles
     *
     * @param properties Styles for element (See {@link CssStyle})
     * @param <I> Child of SceneElement (See: {@link BaseButton})
     * @return SceneElement with accepted styles
     */
    <I extends SceneItem<T>> I initStyle(CssStyle... properties);

    default <I extends SceneItem<T>> I initStyle(SceneItem<?> item, CssStyle... properties) {
        return null;
    }

    default <I extends SceneItem<T>> I initStyle(Node node, CssStyle... properties) {
        return null;
    }

    /**
     * Sets new location on scene
     *
     * @param x Coordinate X
     * @param y Coordinate Y
     */
    void setLocation(double x, double y);

    @SuppressWarnings("unchecked")
    static <N extends Node> SceneItem<N> fromSource(N source) {
        return new SceneItem<>() {
            @Override
            public <I extends SceneItem<N>> I initStyle(CssStyle... properties) {
                Class<I> clazz = (Class<I>) source.getClass();

                Object instance;

                try {
                    instance = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                source.setStyle(CssStyle.getCssString(properties));

                return (I) instance;
            }

            @Override
            public void setLocation(double x, double y) {
                source.setLayoutX(x);
                source.setLayoutY(y);
            }

            @Override
            public Location getLocation() {
                return new Location(source.getLayoutX(), source.getLayoutY());
            }

            @Override
            public void setLocation(Padding padding) {
                source.setLayoutX(source.getLayoutX() + padding.getLeft() + padding.getRight());
                source.setLayoutY(source.getLayoutY() + padding.getTop() + padding.getBottom());
            }

            @Override
            public Container getRoot() {
                Pane region = source.getParent() == null ? (Pane) source : (Pane) source.getParent();
                return Container.fromSource(LinkedPane.class, region, new Object[]{region.getPrefWidth(), region.getPrefHeight()});
            }

            @Override
            public ItemSize getSize() {
                return new ItemSize(source.getBoundsInParent().getWidth(), source.getBoundsInParent().getHeight());
            }
        };
    }

    /**
     * Changes the location by adding indents to the coordinates
     *
     * @param padding Indents on all sides
     */
    default void setLocation(Padding padding) {}

    /**
     * Returns current position element
     *
     * @return Returns current position element
     */
    Location getLocation();

    /**
     * Returns content of root element
     *
     * @return Returns content of root element
     */
    default Object getElementContent() {
        return null;
    }

    /**
     * Returns root element
     *
     * @return Returns root element
     */
    Container getRoot();

    default void setInnerLocation(SceneItem<?> item, double x, double y) {
    }

    default void setRootElement(Container parent, double x, double y) {
    }

    /**
     * Returns size of root object
     *
     * @return Returns size of root object
     */
    ItemSize getSize();
}