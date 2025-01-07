package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Root;

public class BaseText extends BaseItem<Label> implements Root {

    private final Label initialLabel;

    public BaseText(String content, int font) {
        super(() -> {
            if (font <= 0)
                throw new IllegalArgumentException("Font size must be greater than 0");

            Label initial = new Label(content);
            initial.setFont(Font.font(font));
            return initial;
        }, () -> new LinkedPane(0, 0));

        this.initialLabel = this.getNode();
        this.initialLabel.setLayoutY((((BasePane) this.getRoot()).getSize().getHeight() / font + 1));

        double fixedWidth = (this.initialLabel.getFont().getSize() / 1.5 * this.initialLabel.getText().length() / 1.5);
        double fixedHeight = font + content.length() / 1.532;

        this.setSize(fixedWidth, fixedHeight);
        this.setBase(this.initialLabel);
    }

    @Override
    public void setSize(double width, double height) {
        BasePane root = (BasePane) this.getRoot();
        root.setSize(width, height);
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
        parent.addChildren(((BasePane) this.getRoot()));
        this.setLocation(x, y);
    }
}