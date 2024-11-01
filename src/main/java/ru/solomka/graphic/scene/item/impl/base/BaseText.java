package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.DataContext;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

public class BaseText implements BaseComponent<AnchorPane>, DataContext {

    private final AnchorPane container;
    private final Label text;

    public BaseText(String mainContent, int font) {
        this.container = new AnchorPane();
        this.text = new Label(mainContent);
        text.setFont(Font.font(font));
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
    }

    @Override
    public void setLocation(Padding padding) {
        this.container.setLayoutX(this.container.getLayoutX() + padding.getLeft() + padding.getRight());
        this.container.setLayoutY(this.container.getLayoutY() + padding.getTop() + padding.getBottom());
    }

    @Override
    public AnchorPane getItem() {
        return this.container;
    }

    @Override
    public Object getData() {
        return this.text;
    }
}