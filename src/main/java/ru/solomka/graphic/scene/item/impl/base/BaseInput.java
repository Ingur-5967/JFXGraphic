package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Root;
import ru.solomka.graphic.style.CssStyle;

public class BaseInput implements BaseComponent<AnchorPane>, Root {

    private final AnchorPane container;
    private final TextField initialField;
    private final Location location;

    public BaseInput() {
        this.container = new AnchorPane();
        this.initialField = new TextField();
        this.location = new Location(0.0, 0.0);
        this.container.getChildren().add(initialField);
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssStyle... properties) {
        this.initialField.setStyle(CssStyle.getCssString(properties));
        return (I) this;
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(SceneItem<?> item, CssStyle... properties) {
        item.initStyle(properties);
        return (I) this;
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(Node node, CssStyle... properties) {
        node.setStyle(CssStyle.getCssString(properties));
        return (I) this;
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
        this.location.update(x, y);
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        this.setLocation(x, y);
        parent.addChildren(this.container);
    }

    @Override
    public Object getElementContent() {
        return this.initialField;
    }

    @Override
    public Container getRoot() {
        return Container.fromSource(LinkedPane.class, this.container, null);
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public ItemSize getSize() {
        return new ItemSize(this.container.getPrefWidth(), this.container.getPrefHeight());
    }
}