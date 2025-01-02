package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Root;
import ru.solomka.graphic.style.CssStyle;

public class BaseText implements BaseComponent<AnchorPane>, Root {

    private final LinkedPane container;
    private final Location location;

    private final Label initialLabel;

    public BaseText(String content, int font) {
        this.container = new LinkedPane(0, 0);
        this.location = new Location(0.0, 0.0);

        this.initialLabel = this.initBaseObject(content, font);

        this.container.setSize((this.initialLabel.getFont().getSize() / 1.5 * this.initialLabel.getText().length() / 1.5), font + content.length() / 1.532);
        this.container.addChildren(this.initialLabel);
    }

    /**
     * Concat {@code content} to initial {@code Label} in {@code BaseText}.
     *
     * @param content Any valid text for object {@code Label}
     * @see Label
     */
    public void addTextToObject(String content, String separator) {
        String text = this.initialLabel.getText();

        this.initialLabel.setText(text.concat(separator.concat(content)));
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        parent.addChildren(this.container);
        this.setLocation(x, y);
    }

    private Label initBaseObject(String content, int font) {
        Label base = new Label(content);
        base.setFont(Font.font(font));
        base.setLayoutX(0);
        base.setLayoutY((this.container.getSize().getHeight() / font + 1));
        return base;
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
    public <N extends Node> N getNode() {
        return (N) this.initialLabel;
    }
}