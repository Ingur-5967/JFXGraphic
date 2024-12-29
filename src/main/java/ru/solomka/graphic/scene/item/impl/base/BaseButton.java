package ru.solomka.graphic.scene.item.impl.base;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Interact;
import ru.solomka.graphic.scene.item.tag.ItemAnimation;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

import java.util.function.Consumer;

public abstract class BaseButton implements BaseComponent<AnchorPane>, ItemAnimation, Interact {

    private final AnchorPane container;

    private boolean animation;
    private final Location location;

    public BaseButton(String content, int font) {
        this.container = new AnchorPane();
        this.animation = false;
        this.location = new Location(0.0, 0.0);

        Button base = this.initBaseObject(content, font);

        if (!content.isEmpty() && font > 0)
            this.container.setPrefSize((int) (base.getFont().getSize() / 1.5 * base.getText().length() / 1.5), font + content.length() / 1.532);

        this.container.getChildren().add(base);
    }

    public void setGraphicContent(Image resource) {
        Button target = (Button) this.container.getChildren().getFirst();

        ImageView viewer = new ImageView(resource);
        viewer.setFitWidth(target.getPrefWidth());
        viewer.setFitHeight(target.getPrefHeight());

        target.setGraphic(new ImageView(resource));
    }

    private Button initBaseObject(String content, int font) {
        if (content.isEmpty() || font <= 0)
            return new Button();

        Button base = new Button(content);

        base.setFont(Font.font(font));

        base.setLayoutX(0);
        base.setLayoutY((this.container.getPrefHeight() / font + 1));

        return base;
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssStyle... properties) {
        this.container.setStyle(CssStyle.getCssString(properties));
        return (I) this;
    }

    @Override
    public <E extends Event> void setup(EventType<E> eventType, Consumer<E> event) {
        this.container.addEventHandler(eventType, event::accept);
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
    public void setAnimationTag(boolean tag) {
        this.animation = tag;
    }

    @Override
    public boolean hasTag() {
        return this.animation;
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
    public Container getRoot() {
        return Container.fromSource(LinkedPane.class, this.container, new Object[]{this.container.getPrefWidth(), this.container.getPrefHeight()});
    }
}