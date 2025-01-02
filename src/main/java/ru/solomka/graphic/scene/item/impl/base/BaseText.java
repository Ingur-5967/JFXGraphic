package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Root;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

import java.util.ArrayList;
import java.util.List;

public class BaseText implements BaseComponent<AnchorPane>, Root {

    private final AnchorPane container;
    private final Location location;

    @Getter
    private final List<Label> textData;
    @Getter
    @Setter
    private int maxLines;

    public BaseText(String content, int font, int maxLines) {
        this.container = new AnchorPane();
        this.textData = new ArrayList<>();
        this.location = new Location(0.0, 0.0);
        this.maxLines = maxLines;

        Label base = this.initBaseObject(content, font);

        this.container.setPrefSize((int) (base.getFont().getSize() / 1.5 * base.getText().length() / 1.5), font + content.length() / 1.532);
        this.textData.add(base);
    }

    public BaseText(String content, int font) {
        this(content, font, 1);
    }

    /**
     * Concat {@code content} to exists object {@code Label} in data list {@code BaseText}.
     * If list is empty, then the {@code content} element becomes the first element in the data list.
     *
     * @param content Any valid text for object {@code Label}
     * @param lineIndex Index to get the object {@code Label} in data
     * @see Label
     * @throws IllegalArgumentException {@code lineIndex} < 0
     */
    public boolean addTextToObject(String content, String separator, int lineIndex) {
        Label element;

        if (lineIndex < 0)
            throw new IllegalArgumentException("Line index must be greater than 0");

        if (this.textData.size() < lineIndex)
            return false;

        if (this.textData.isEmpty()) {
            element = new Label(content);
            element.setFont(Font.font(this.textData.getLast().getFont().getSize()));
            this.textData.add(new Label(content));
            this.addTextToObject(content, separator);
        }

        element = this.textData.get(lineIndex);
        element.setText(element.getText().concat(separator.concat(content)));

        this.container.setPrefWidth((element.getFont().getSize() / 1.47 * element.getText().length() / 1.43));

        return true;
    }

    /**
     * Concat {@code content} to exists object {@code Label} in data list {@code BaseText}.
     * If list is empty, then the {@code content} element becomes the first element in the data list.
     * <p>
     * Root method: {@link BaseText#addTextToObject(String, String, int)}
     * </p>
     * 
     * @param content Any valid text for object {@code Label}
     * @see Label
     */
    public boolean addTextToObject(String content, String separator) {
        return this.addTextToObject(content, separator, 0);
    }

    /**
     * Adds a new line to existing text
     *
     * @param content Any valid text for object {@code Label}
     * @param font    Text size ({@link Font})
     * @param padding Spacing between the previous line
     * @param styles  Style for text
     * @return Result/Success of adding a new line
     * @throws IllegalArgumentException {@code padding} < 0 or {@code font} <= 0
     * @see Label
     */
    public boolean addLine(String content, int font, int padding, CssStyle... styles) {
        if (padding < 0 || font <= 0)
            throw new IllegalArgumentException("Padding must be greater than 0");

        if (this.maxLines < this.textData.size())
            return false;

        Label coordinator = this.textData.getLast();
        Label newLabel = new Label(content);

        newLabel.setFont(Font.font(font));
        newLabel.setStyle((CssStyle.getCssString(styles).isEmpty() ? "" : CssStyle.getCssString(styles)));
        newLabel.setLayoutX(coordinator.getLayoutX());
        newLabel.setLayoutY((coordinator.getLayoutY() + coordinator.getFont().getSize() / 1.5) + padding);

        this.textData.add(newLabel);
        this.container.setPrefHeight(this.container.getPrefHeight() + (padding + newLabel.getFont().getSize()) / 2);
        this.container.getChildren().add(newLabel);

        return true;
    }

    /**
     * Adds a new line to existing text
     * <p>
     * Root method: {@link BaseText#addLine(String, int, int, CssStyle...)}
     * </p>
     *
     * @param content Any valid text for object {@code Label}
     * @param font    New text size
     * @param padding Spacing between the previous line
     * @return Result/Success of adding a new line
     * @throws IllegalArgumentException {@code padding} < 0 or {@code font} <= 0
     * @see Label
     */
    public boolean addLine(String content, int font, int padding) {
        return this.addLine(content, font, padding, CssStyle.empty());
    }

    /**
     * Delete all instances from {@code textData} on {@code Scene}
     *
     * @see Scene
     */
    public void clearAll() {
        this.textData.forEach(component -> {
            Parent root = JFXGraphic.getPrimaryStage().getScene().getRoot();
            Pane regionItems = (Pane) root;

            regionItems.getChildren().remove(component);
        });

        this.textData.clear();
    }

    /**
     * Returns an existing item to the list date {@code BaseText#getTextData()}
     *
     * @param index Desired object index
     * @return Returns an existing item to the list date
     * @throws IndexOutOfBoundsException {@code index < 0} or <p>{@code index >= BaseText#getTextData().size()}</p>
     */
    public Label getLineObject(int index) {
        if (index < 0 || index >= this.textData.size())
            throw new IllegalArgumentException("Index must be between 0 and " + (this.textData.size() - 1));

        return this.textData.get(index);
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        if (this.textData.isEmpty())
            throw new NullPointerException("TextData is empty");

        this.textData.forEach(component -> {
            component.setLayoutX(x);
            component.setLayoutY(y);
        });

        this.setLocation(x, y);

        this.textData.forEach(_ -> parent.getBaseRegion().getChildren().addAll(this.textData));
    }

    private Label initBaseObject(String content, int font) {
        Label base = new Label(content);
        base.setFont(Font.font(font));
        base.setLayoutX(0);
        base.setLayoutY((this.container.getPrefHeight() / font + 1));
        return base;
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssStyle... properties) {
        return this.initStyle(this.container, properties);
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(Node node, CssStyle... properties) {
        node.setStyle(CssStyle.getCssString(properties));
        return (I) this;
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(SceneItem<?> item, CssStyle... properties) {
        return this.initStyle((Pane) item.getRoot().getBaseRegion(), properties);
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
    public Container getRoot() {
        return Container.fromSource(LinkedPane.class, this.container, null);
    }

    @Override
    public void setLocation(Padding padding) {
        this.container.setLayoutX(this.container.getLayoutX() + padding.getLeft() + padding.getRight());
        this.container.setLayoutY(this.container.getLayoutY() + padding.getTop() + padding.getBottom());
        this.location.update(this.container.getLayoutX(), this.container.getLayoutY());
    }

    @Override
    public Object getElementContent() {
        return this.textData;
    }
}