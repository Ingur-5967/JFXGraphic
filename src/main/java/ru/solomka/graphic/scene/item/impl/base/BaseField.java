package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.ItemAnimation;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

public class BaseField implements ItemAnimation, BaseComponent<AnchorPane> {
    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssStyle... properties) {
        return null;
    }

    @Override
    public void setLocation(double x, double y) {

    }

    @Override
    public void setLocation(Padding padding) {

    }

    @Override
    public AnchorPane getElement() {
        return null;
    }

    @Override
    public void setAnimationTag(boolean tag) {

    }

    @Override
    public boolean hasTag() {
        return false;
    }
}
