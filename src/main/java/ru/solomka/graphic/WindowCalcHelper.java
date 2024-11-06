package ru.solomka.graphic;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.NonNull;
import ru.solomka.graphic.scene.item.SizeProperties;

public class WindowCalcHelper {

    /**
     * Returns centre X coordinate in local parent
     *
     * @param pane local parent
     * @param regular target node
     * @return Returns centre X coordinate in local parent
     */

    public static double getCentreX(@NonNull Pane pane, @NonNull AnchorPane regular) {
        return pane.getPrefWidth() / 2 - regular.getPrefWidth()/2;
    }

    /**
     * Returns centre Y coordinate in local parent
     *
     * @param pane local parent
     * @param regular target node
     * @return Returns centre Y coordinate in local parent
     */

    public static double getCentreY(@NonNull Pane pane, @NonNull AnchorPane regular) {
        return pane.getPrefHeight() / 2 - regular.getPrefHeight()/2;
    }

    /**
     * Returns size node
     *
     * @param node target node
     * @return Returns size node
     */

    public static SizeProperties getSizeComponent(@NonNull Node node) {
        return new SizeProperties(node.getBoundsInParent().getWidth(), node.getBoundsInParent().getHeight());
    }
}