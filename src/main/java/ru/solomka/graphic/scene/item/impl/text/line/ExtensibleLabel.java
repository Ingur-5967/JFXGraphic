package ru.solomka.graphic.scene.item.impl.text.line;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.impl.base.BasePane;
import ru.solomka.graphic.scene.item.impl.base.BaseText;
import ru.solomka.graphic.style.CssStyle;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExtensibleLabel extends BaseText {

    private final List<Label> textData;

    @Setter
    private int maxLines;

    public ExtensibleLabel(String initialContent, int font, int maxLines) {
        super(initialContent, font);

        this.textData = new ArrayList<>();
        this.maxLines = maxLines;

        this.textData.add((Label) this.getRoot().getChildren().getFirst());
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

        LinkedPane root = (LinkedPane) this.getRoot();

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
        root.setSize(root.getSize().getWidth(), root.getSize().getHeight() + (padding + newLabel.getFont().getSize()) / 2);

        root.addChildren(newLabel);

        return true;
    }

    /**
     * Adds a new line to existing text
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
            BasePane root = (BasePane) JFXGraphic.getInstance().getSceneEntry().getMainLayout();
            Pane container = root.getNode();
            container.getChildren().remove(component);
        });

        this.textData.clear();
    }

    /**
     * Returns an existing item to the list data
     *
     * @param index Desired object index
     * @return Returns an existing item to the list data
     * @throws IndexOutOfBoundsException {@code index < 0} or <p>{@code index >= BaseText#getTextData().size()}</p>
     */
    public Label getLineObject(int index) {
        if (index < 0 || index >= this.textData.size())
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (this.textData.size() - 1));

        return this.textData.get(index);
    }

    public void addTextToObjectByIndex(int index, String text, String separator) {
        String content = this.getLineObject(index).getText();
        this.textData.get(index).setText(content.concat(separator.concat(text)));
    }

    public void setDataSize(int newSize) {
        this.maxLines = newSize;
    }
}
