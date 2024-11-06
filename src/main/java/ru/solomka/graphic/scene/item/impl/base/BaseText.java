package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import lombok.Getter;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.item.BaseComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

import java.util.ArrayList;
import java.util.List;

public class BaseText implements BaseComponent<AnchorPane> {

    private final AnchorPane container;

    @Getter
    private final List<Label> textData;

    public BaseText(String mainContent, int font) {
        this.container = new AnchorPane();
        this.textData = new ArrayList<>();
        Label base = new Label(mainContent);
        base.setFont(Font.font(font));
        this.textData.add(base);
    }

    /**
     * Concat {@code content} to exists object {@code Label} in data list {@code BaseText}.
     * If list is empty, then the {@code content} element becomes the first element in the data list.
     *
     * @param content   Any valid text for object {@code Label}
     * @param lineIndex Index to get the object {@code Label} in data
     * @see Label
     */

    public void addText(String content, int lineIndex) {
        Label element;

        if (this.textData.size() < lineIndex)
            throw new IllegalArgumentException("Line index out of bounds");

        if (this.textData.isEmpty()) {
            element = new Label(content);
            element.setFont(Font.font(this.textData.getLast().getFont().getSize()));
            this.textData.add(new Label(content));
            this.addText(content);
        }

        element = this.textData.get(lineIndex);
        element.setText(element.getText().concat(content));
    }

    /**
     * Concat {@code content} to exists object {@code Label} in data list {@code BaseText}.
     * If list is empty, then the {@code content} element becomes the first element in the data list.
     *
     * @param content Any valid text for object {@code Label}
     * @see Label
     */

    public void addText(String content) {
        this.addText(content, 0);
    }

    /**
     * Delete all instances from {@code textData} on {@code Scene}
     *
     * @see javafx.scene.Scene
     */

    public void clearAll() {
        if (JFXGraphic.getPrimaryStage() == null)
            throw new NullPointerException("JFXGraphic is not attached to the primary stage (Stage is NULL)");

        this.textData.forEach(component -> {
            Parent root = JFXGraphic.getPrimaryStage().getScene().getRoot();
            Pane regionItems = (Pane) root;

            regionItems.getChildren().remove(component);
        });

        this.textData.clear();
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
    public AnchorPane getElement() {
        return this.container;
    }

    @Override
    public Object getElementContent() {
        return this.textData;
    }
}