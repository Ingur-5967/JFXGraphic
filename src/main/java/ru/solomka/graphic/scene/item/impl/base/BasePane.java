package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

import java.util.List;

public class BasePane implements Container, SceneItem<AnchorPane> {

    private final AnchorPane container;
    private final Location location;

    public BasePane(double width, double height) {
        this.container = new AnchorPane();
        this.location = new Location(0.0, 0.0);
        this.container.setPrefSize(width, height);
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        this.setLocation(x, y);
        parent.addChildren(this.container);
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
        this.location.update(x, y);
    }

    @Override
    public ItemSize getSize() {
        return new ItemSize(this.container.getPrefWidth(), this.container.getPrefHeight());
    }

    @Override
    public Container getRoot() {
        return this;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(Padding padding) {
        this.container.setLayoutX(this.container.getLayoutX() + padding.getLeft() + padding.getRight());
        this.container.setLayoutY(this.container.getLayoutY() + padding.getTop() + padding.getBottom());
        this.location.update(this.container.getLayoutX(), this.container.getLayoutY());
    }

    @Override
    public List<Node> getChildren() {
        return this.container.getChildren();
    }

    @Override
    public <P extends Pane> P getBaseRegion() {
        return (P) this.container;
    }
}