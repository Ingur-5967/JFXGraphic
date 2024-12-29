package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.ItemAnimation;
import ru.solomka.graphic.scene.item.tag.enums.ItemAlignment;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

public class BaseImage implements BaseComponent<AnchorPane>, ItemAnimation {

    private final AnchorPane container;
    private final ImageView viewer;
    @Getter
    private final ItemAlignment orientation;

    private boolean animation;
    private final Location location;

    public BaseImage(Image source, ItemAlignment orientation) {
        this.container = new AnchorPane();
        this.orientation = orientation;
        this.location = new Location(0.0, 0.0);

        this.animation = false;

        this.viewer = new ImageView(source);
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        this.setLocation(x, y);
        parent.addChildren(this.container);
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
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void setAnimationTag(boolean tag) {
        this.animation = tag;
    }

    @Override
    public boolean hasTag() {
        return this.animation;
    }

    @Override
    public Container getRoot() {
        return Container.fromSource(LinkedPane.class, this.container, new Object[]{this.container.getPrefWidth(), this.container.getPrefHeight()});
    }

    @Override
    public void setLocation(Padding padding) {
        this.container.setLayoutX(this.container.getLayoutX() + padding.getLeft() + padding.getRight());
        this.container.setLayoutY(this.container.getLayoutY() + padding.getTop() + padding.getBottom());
        this.location.update(this.container.getLayoutX(), this.container.getLayoutY());
    }

    @Override
    public Object getElementContent() {
        return this.viewer;
    }
}