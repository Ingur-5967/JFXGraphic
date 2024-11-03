package ru.solomka.graphic;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.NonNull;

public class WindowCalcHelper {

    public static double calculateTopPosition(Pane node, Node regular) {
        return getPositiveCentre(node, regular)[1] - (node.getBoundsInLocal().getHeight() / 6.9 * regular.getBoundsInLocal().getHeight() / 2) / 1.5;
    }

    public static double calculateDownPosition(Pane node, Node regular) {
        return getPositiveCentre(node, regular)[1] + (node.getBoundsInLocal().getHeight() / 6.9 * regular.getBoundsInLocal().getHeight() / 2) / 4.2;
    }

    public static double calculateRightPosition(@NonNull Node node) {
        return (node.getLayoutBounds().getCenterX() - node.getLayoutBounds().getCenterX() / 2) + node.getBoundsInLocal().getWidth();
    }

    public static double[] getNegativeCentre(@NonNull Pane node, @NonNull Node regular) {
        return new double[]{
                (node.getPrefWidth() / 2 - regular.getBoundsInLocal().getWidth() / 2),
                (node.getPrefHeight() / 2 - regular.getBoundsInLocal().getHeight() / 2)
        };
    }

    public static double[] getNegativeCentreOfLabel(@NonNull Pane node, @NonNull Label regular) {

        node.getPrefWidth();
        return new double[]{
                (node.getPrefWidth() / 2 - (regular.getText().length() * regular.getFont().getSize()) / 4),
                (node.getPrefHeight() / 2 - regular.getFont().getSize()) - 2,
        };

    }

    public static double[] getPositiveCentre(@NonNull Pane node, @NonNull Node regular) {
        return new double[]{
                (node.getPrefWidth() / 2 + regular.getBoundsInLocal().getWidth() / 2),
                (node.getPrefHeight() / 2 + regular.getBoundsInLocal().getHeight() / 2)
        };
    }

    public static double[] getPositiveCentreOfLabel(@NonNull Pane node, @NonNull Label regular) {
        return new double[]{
                (node.getPrefWidth() / 2 - regular.getText().length()),
                (node.getPrefHeight() / 2 - regular.getFont().getSize()) + 1,
        };
    }

    public static double[] getSizeComponent(@NonNull Node node) {
        return new double[]{node.getBoundsInParent().getWidth(), node.getBoundsInParent().getHeight()};
    }

    public static double[] getSizePane(@NonNull AnchorPane pane) {
        return new double[]{pane.getMaxWidth(), pane.getMaxHeight()};
    }

    public static double[] getCornerComponent(@NonNull Node node) {
        return new double[]{node.getBoundsInParent().getMinX(), node.getBoundsInParent().getMinY()};
    }
}