package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import lombok.Setter;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Stylizable;
import ru.solomka.graphic.style.CssStyle;

import java.util.function.Supplier;

public class BaseItem<E extends Node> implements SceneItem, Stylizable {

    private final Container parent;
    private final Location location;
    @Setter
    private E base;

    public BaseItem(E base, Container parent) {
        this.base = base;
        this.parent = parent;
        this.location = new Location(0, 0);

        if (parent != null)
            this.parent.addChildren(this.base);
    }

    public BaseItem(Supplier<E> initial, Supplier<Container> parent) {
        this(initial.get(), parent.get());
    }

    public BaseItem(Supplier<E> initial) {
        this(initial, () -> null);
    }

    @Override
    public void setLocation(double x, double y) {
        if (this.parent == null) {
            this.base.setLayoutX(x);
            this.base.setLayoutY(y);
        } else {
            BasePane basePane = (BasePane) this.parent;
            basePane.setLocation(x, y);
        }
        this.location.update(x, y);
    }

    @Override
    public void setSize(double width, double height) {
        Region layout = (Region) this.base;
        if (this.parent != null) {
            BasePane basePane = (BasePane) this.parent;
            basePane.setSize(width, height);
        }
        layout.setPrefSize(width, height);
    }

    @Override
    public ItemSize getSize() {
        if (this.parent == null) {
            Region layout = (Region) this.base;
            return new ItemSize(layout.getPrefWidth(), layout.getPrefHeight());
        }
        BasePane basePane = (BasePane) this.parent;
        return new ItemSize(basePane.getSize().getWidth(), basePane.getSize().getHeight());
    }

    @Override
    public <I extends SceneItem> I initStyle(CssStyle... properties) {
        this.base.setStyle(CssStyle.getCssString(properties));
        return (I) this;
    }

    @Override
    public <I extends SceneItem> I initParentStyle(CssStyle... properties) {
        if (this.parent == null) return this.initStyle(properties);

        BasePane root = (BasePane) this.parent;
        root.initStyle(properties);

        return (I) this;
    }

    @Override
    public <I extends SceneItem> I appendStyle(SceneItem item, CssStyle... newProperties) {
        String oldStyle = item.getNode().getStyle();
        String newStyle = CssStyle.getCssString(newProperties);
        item.getNode().setStyle(oldStyle.concat(newStyle));
        return (I) this;
    }

    @Override
    public <N extends Node> N getNode() {
        return (N) this.base;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public Container getRoot() {
        return this.parent == null ? Container.fromSource(LinkedPane.class, (Pane) this.base, null) : this.parent;
    }
}