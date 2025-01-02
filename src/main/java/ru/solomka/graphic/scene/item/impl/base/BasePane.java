package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Root;
import ru.solomka.graphic.style.CssStyle;

import java.util.List;
import java.util.stream.Collectors;

public class BasePane implements Container, SceneItem<AnchorPane>, Root {

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
    public void addChildren(SceneItem<?> item) {
        this.addChildren((Pane) item.getRoot().getBaseRegion());
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
    public void setSize(double width, double height) {
        this.container.setPrefSize(width, height);
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
    public List<Node> getChildren() {
        return this.container.getChildren();
    }

    @Override
    public List<SceneItem<?>> getSource() {
        return this.getChildren().stream().map(SceneItem::fromSource).collect(Collectors.toList());
    }

    @Override
    public <P extends Pane> P getBaseRegion() {
        return (P) this.container;
    }
}