package ru.solomka.graphic;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.NonNull;

public class WindowCalcHelper {

    /**
     * @param pane    local parent
     * @param regular target node
     * @return Centre X coordinate in local parent
     */

    public static double getCentreX(@NonNull Pane pane, @NonNull AnchorPane regular) {
        return pane.getPrefWidth() / 2 - regular.getPrefWidth()/2;
    }

    /**
     * @param pane local parent
     * @param regular target node
     * @return Centre Y coordinate in local parent
     */

    public static double getCentreY(@NonNull Pane pane, @NonNull AnchorPane regular) {
        return pane.getPrefHeight() / 2 - regular.getPrefHeight()/2;
    }

    /**
     * @param node target node
     * @return Size node (Width, Height)
     */

    public static double[] getSizeComponent(@NonNull Node node) {
        return new double[]{node.getBoundsInParent().getWidth(), node.getBoundsInParent().getHeight()};
    }
}