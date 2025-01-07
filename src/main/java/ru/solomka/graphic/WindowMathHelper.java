package ru.solomka.graphic;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.NonNull;
import ru.solomka.graphic.scene.item.SceneItem;

public class WindowMathHelper {

    /**
     * Returns centre X coordinate in local parent
     *
     * @param pane    local parent
     * @param regular target node
     * @return Returns centre X coordinate in local parent
     */
    public static double getCentreX(@NonNull Pane pane, @NonNull AnchorPane regular) {
        if (pane.getPrefWidth() <= 0 || pane.getPrefHeight() <= 0)
            throw new IllegalArgumentException("Pane width and height must be greater than 0");

        if (regular.getPrefWidth() <= 0 || regular.getPrefHeight() <= 0)
            throw new IllegalArgumentException("Regular node width and height must be greater than 0");

        return pane.getPrefWidth() / 2 - regular.getPrefWidth() / 1.957;
    }

    public static double getDistance(Node posOne, Node posTwo) {
        return Math.sqrt(Math.pow(posOne.getLayoutX() - posTwo.getLayoutX(), 2) + Math.pow(posOne.getLayoutY() - posTwo.getLayoutY(), 2));
    }

    /**
     * Returns centre Y coordinate in local parent
     *
     * @param pane    local parent
     * @param regular target node
     * @return Returns centre Y coordinate in local parent
     */
    public static double getCentreY(@NonNull Pane pane, @NonNull AnchorPane regular) {
        if (pane.getPrefWidth() <= 0 || pane.getPrefHeight() <= 0)
            throw new IllegalArgumentException("Parent width and height must be greater than 0");

        if (regular.getPrefWidth() <= 0 || regular.getPrefHeight() <= 0)
            throw new IllegalArgumentException("Regular node width and height must be greater than 0");

        return pane.getPrefHeight() / 2 - regular.getPrefHeight() / 1.8479;
    }

    public static double getTopBorder(SceneItem source, SceneItem target) {
        return source.getLocation().getY() - target.getSize().getHeight();
    }

    public static double getRightBorder(SceneItem source) {
        return source.getSize().getWidth() + source.getLocation().getX();
    }

    public static double getLeftBorder(SceneItem source, SceneItem target) {
        return source.getNode().localToScene(source.getLocation().getX() - target.getSize().getWidth(), 0).getX();
    }
}