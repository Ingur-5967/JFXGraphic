package ru.solomka.graphic;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.NonNull;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.enums.ItemAlignment;

public class WindowCalcHelper {

    /**
     * Returns centre X coordinate in local parent
     *
     * @param pane local parent
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

    /**
     * Returns centre Y coordinate in local parent
     *
     * @param pane local parent
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

    /**
     * Returns size node
     *
     * @param node target node
     * @return Returns size node
     */
    public static ItemSize getSizeComponent(@NonNull Node node) {
        return new ItemSize(node.getBoundsInParent().getWidth(), node.getBoundsInParent().getHeight());
    }

    public static double getPositionBorderByElement(Node node, Node regular, ItemAlignment alignment) {
        double distanceToPoint = Math.sqrt(Math.pow(regular.getLayoutX() - node.getLayoutX(), 2) + Math.pow(regular.getLayoutY() - node.getLayoutY(), 2));

        switch (alignment) {
            case TOP -> {
                return Math.abs(distanceToPoint - node.getLayoutY());
            }
            case DOWN -> {
                return (distanceToPoint - node.getLayoutY()) + ((AnchorPane) node).getPrefHeight() * 1.5;
            }
            default -> throw new IllegalArgumentException("Invalid alignment");
        }
    }

    /**
     * Returns X or Y coordinate
     *
     * @param pane      local parent
     * @param regular   target element for calculated fixed position (DOWN position)
     * @param alignment orientation for result
     * @return Returns X or Y coordinate
     */
    public static double getPositionBorder(Pane pane, SceneItem<AnchorPane> regular, ItemAlignment alignment) {
        switch (alignment) {
            case LEFT, TOP -> {
                return 0.0;
            }
            case RIGHT -> {
                return pane.getPrefWidth();
            }
            case DOWN -> {
                if (regular == null)
                    throw new IllegalArgumentException("Regular node must not be null for calculate DOWN position");

                return pane.getPrefHeight() - ((Pane) regular.getNode()).getPrefHeight();
            }
            default -> throw new IllegalArgumentException("Invalid alignment");
        }
    }

    /**
     * Returns X or Y coordinate
     * <p>
     * Root method: {@link WindowCalcHelper#getPositionBorder(Pane, SceneItem, ItemAlignment) }
     * </p>
     *
     * @param pane      local parent
     * @param alignment orientation for result
     * @return Returns X or Y coordinate
     */
    public static double getPositionBorder(Pane pane, ItemAlignment alignment) {
        return getPositionBorder(pane, null, alignment);
    }
}