package ru.solomka.graphic.scene.item;

import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;

public interface SceneItem {

    /**
     * Sets new location on scene
     *
     * @param x Coordinate X
     * @param y Coordinate Y
     */
    void setLocation(double x, double y);

    @SuppressWarnings("unchecked")
    static <N extends Node> SceneItem fromSource(N source) {
        return new SceneItem() {
            @Override
            public void setLocation(double x, double y) {
                source.setLayoutX(x);
                source.setLayoutY(y);
            }

            @Override
            public Node getNode() {
                return source;
            }

            @Override
            public Location getLocation() {
                return new Location(source.getLayoutX(), source.getLayoutY());
            }

            @Override
            public Container getRoot() {
                if (source instanceof ButtonBase)
                    return new LinkedPane(((ButtonBase) source).getPrefWidth(), ((ButtonBase) source).getPrefHeight());

                Pane region = source.getParent() == null ? (Pane) source : (Pane) source.getParent();
                return Container.fromSource(LinkedPane.class, region, null);
            }

            @Override
            public ItemSize getSize() {
                Region region = (Region) source;
                return new ItemSize(region.getPrefWidth(), region.getPrefHeight());
            }

            @Override
            public void setSize(double width, double height) {
                source.prefWidth(width);
                source.prefHeight(height);
            }
        };
    }

    /**
     * Returns base element of root
     *
     * @return Returns base element of root
     */
    <N extends Node> N getNode();

    /**
     * Returns current position element
     *
     * @return Returns current position element
     */
    Location getLocation();

    /**
     * Returns root element
     *
     * @return Returns root element
     */
    Container getRoot();

    /**
     * Returns size of root object
     *
     * @return Returns size of root object
     */
    ItemSize getSize();

    void setSize(double width, double height);
}