package ru.solomka.graphic.scene.item.impl.base;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Interact;
import ru.solomka.graphic.scene.item.tag.Root;
import ru.solomka.graphic.style.CssStyle;

import java.util.function.Consumer;

public abstract class BaseButton implements BaseComponent<AnchorPane>, Interact, Root {

    private final Button button;
    private final Location location;

    public BaseButton(ItemSize size, String content, int font) {
        this.location = new Location(0.0, 0.0);

        this.button = this.initBaseObject(content, font);
        this.button.setPrefSize(size.getWidth(), size.getHeight());
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssStyle... properties) {
        this.button.setStyle(CssStyle.getCssString(properties));
        return (I) this;
    }

    @Override
    public <E extends Event> void setup(EventType<E> eventType, Consumer<E> event) {
        this.button.addEventHandler(eventType, event::accept);
    }

    @Override
    public void setLocation(double x, double y) {
        this.button.setLayoutX(x);
        this.button.setLayoutY(y);
        this.location.update(x, y);
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        this.setLocation(x, y);
        parent.addChildren(this.button);
    }

    @Override
    public ItemSize getSize() {
        return new ItemSize(this.button.getPrefWidth(), this.button.getPrefHeight());
    }

    @Override
    public void setSize(double width, double height) {
        this.button.setPrefSize(width, height);
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public Container getRoot() {
        return Container.fromSource(LinkedPane.class, button.getParent(), null);
    }

    @Override
    public <N extends Node> N getNode() {
        return (N) this.button;
    }

    private Button initBaseObject(String content, int font) {
        if (content.isEmpty() || font <= 0)
            return new Button();

        Button base = new Button(content);
        base.setFont(Font.font(font));

        return base;
    }
}