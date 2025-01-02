package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Content;
import ru.solomka.graphic.scene.item.tag.Root;
import ru.solomka.graphic.style.CssStyle;

public class BaseInput implements BaseComponent<AnchorPane>, Root, Content {

    private final LinkedPane container;
    private final TextField initialField;
    private final Location location;

    public BaseInput() {
        this.container = new LinkedPane(0, 0);
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
    public void setLocation(double x, double y) {
        this.container.setLocation(x, y);
        this.location.update(x, y);
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        this.setLocation(x, y);
        parent.addChildren(this.container);
    }

    @Override
    public Container getRoot() {
        return this.container;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public ItemSize getSize() {
        return this.container.getSize();
    }

    @Override
    public void setSize(double width, double height) {
        this.container.setSize(width, height);
    }

    @Override
    public <C> C getItemContent() {
        return (C) this.initialField;
    }
}