package ru.solomka.graphic.scene.item.impl.base;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.Interact;
import ru.solomka.graphic.scene.item.tag.ItemAnimation;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;
import ru.solomka.graphic.tool.Pair;

import java.util.function.BiConsumer;

public abstract class BaseButton implements BaseComponent<AnchorPane>, ItemAnimation, Interact {

    private final AnchorPane container;
    private boolean animation;

    public BaseButton(String id) {
        this.container = new AnchorPane();
        this.container.setId(id);
        this.animation = false;
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssStyle... properties) {
        this.container.setStyle(CssStyle.getCssString(properties));
        return (I) this;
    }

    @Override
    public <E extends Event> void setup(BiConsumer<E, Pair<Stage, Node>> interact) {
        this.container.setOnMouseClicked(e -> interact.accept((E) e, new Pair<>(JFXGraphic.getPrimaryStage(), this.getItem())));
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
    public void setAnimationTag(boolean tag) {
        this.animation = tag;
    }

    @Override
    public boolean hasTag() {
        return this.animation;
    }

    @Override
    public AnchorPane getItem() {
        return this.container;
    }
}