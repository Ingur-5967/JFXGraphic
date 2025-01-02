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
import ru.solomka.graphic.scene.item.tag.Content;
import ru.solomka.graphic.scene.item.tag.Root;
import ru.solomka.graphic.scene.item.tag.enums.ItemAlignment;
import ru.solomka.graphic.style.CssStyle;

public class BaseImage implements BaseComponent<AnchorPane>, Root, Content {

    private final LinkedPane container;
    private final ImageView viewer;
    @Getter
    private final ItemAlignment orientation;

    private final Location location;

    public BaseImage(Image source, ItemAlignment orientation) {
        this.container = new LinkedPane(0, 0);
        this.orientation = orientation;
        this.location = new Location(0.0, 0.0);

        this.viewer = new ImageView(source);
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        this.setLocation(x, y);
        parent.addChildren(this.container);
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssStyle... properties) {
        this.container.initStyle(properties);
        return (I) this;
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLocation(x, y);
        this.location.update(x, y);
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
    public Location getLocation() {
        return this.location;
    }

    @Override
    public Container getRoot() {
        return this.container;
    }

    @Override
    public <C> C getItemContent() {
        return (C) this.viewer;
    }
}