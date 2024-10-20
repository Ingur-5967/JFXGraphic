package ru.solomka.graphic;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class WindowCalcHelper {

    public static double calculateTopPosition(@NotNull Pane node, Node regular) {
        return getPositiveCentre(node, regular)[1] - (node.getBoundsInLocal().getHeight()/6.9*regular.getBoundsInLocal().getHeight()/2)/1.5;
    }

    public static double calculateDownPosition(@NotNull Pane node, Node regular) {
        return getPositiveCentre(node, regular)[1] + (node.getBoundsInLocal().getHeight()/6.9*regular.getBoundsInLocal().getHeight()/2)/4.2;
    }

    public static double calculateRightPosition(@NotNull Node node) { return (node.getLayoutBounds().getCenterX() - node.getLayoutBounds().getCenterX()/2) + node.getBoundsInLocal().getWidth(); }

    @NotNull @Contract("_, _ -> new")
    public static double[] getNegativeCentre(@NotNull Pane node, Node regular) {
        return new double[] {
                (node.getPrefWidth()/2 - regular.getBoundsInLocal().getWidth()/2),
                (node.getPrefHeight()/2 - regular.getBoundsInLocal().getHeight()/2)
        };
    }

    @NotNull @Contract("_, _ -> new")
    public static double[] getNegativeCentreOfLabel(Pane node, Label regular) {
        return new double[] {
                (node.getPrefWidth()/2 - (regular.getText().length() * regular.getFont().getSize())/4),
                (node.getPrefHeight()/2 - regular.getFont().getSize()) - 2 ,
        };
    }
    @NotNull @Contract("_, _ -> new")
    public static double[] getPositiveCentre(@NotNull Pane node, Node regular) {
        return new double[] {
                (node.getPrefWidth()/2 + regular.getBoundsInLocal().getWidth()/2),
                (node.getPrefHeight()/2 + regular.getBoundsInLocal().getHeight()/2)
        };
    }

    @NotNull @Contract("_, _ -> new")
    public static double[] getPositiveCentreOfLabel(Pane node, Label regular) {
        return new double[] {
                (node.getPrefWidth()/2 - regular.getText().length()),
                (node.getPrefHeight()/2 - regular.getFont().getSize()) + 1,
        };
    }

    @NotNull @Contract("_ -> new")
    public static double[] getSizeComponent(@NotNull Node node) { return new double[] {node.getBoundsInParent().getWidth(), node.getBoundsInParent().getHeight()}; }

    @NotNull @Contract("_ -> new")
    public static double[] getSizePane(@NotNull AnchorPane pane) { return new double[]{pane.getMaxWidth(), pane.getMaxHeight()};}

    @NotNull @Contract("_ -> new")
    public static double[] getCornerComponent(@NotNull Node node) { return new double[]{node.getBoundsInParent().getMinX(), node.getBoundsInParent().getMinY()}; }
}