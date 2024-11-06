package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.SizeProperties;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

import java.util.List;

public class BasePane implements Container, SceneItem<AnchorPane> {

    private final AnchorPane container;

    public BasePane(int width, int height, String id) {
        this.container = new AnchorPane();
        this.container.setPrefSize(width, height);
        this.container.setId(id);
    }

    @Override
    public void addChildren(Node item) {
        this.container.getChildren().add(item);
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssStyle... properties) {
        this.container.setStyle(CssStyle.getCssString(properties));
        return (I) this;
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
    }

    @Override
    public void setLocation(Padding padding) {
        this.container.setLayoutX(this.container.getLayoutX() + padding.getLeft() + padding.getRight());
        this.container.setLayoutY(this.container.getLayoutY() + padding.getTop() + padding.getBottom());
    }

    @Override
    public AnchorPane getElement() {
        return this.container;
    }

    @Override
    public List<Node> getChildren() {
        return this.container.getChildren();
    }

    @Override
    public SizeProperties getBounds() {
        return new SizeProperties(this.container.getPrefWidth(), this.container.getPrefHeight());
    }
}