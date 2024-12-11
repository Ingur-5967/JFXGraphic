package ru.solomka.graphic.scene.item.impl.text.line;

import ru.solomka.graphic.scene.item.impl.base.BaseText;
import ru.solomka.graphic.style.CssStyle;

public class ExtensibleLabel extends BaseText {

    public ExtensibleLabel(String content, int font, int initialCountLines) {
        super(content, font, initialCountLines);
        this.getRoot().getChildren().add(this.getLineObject(0));
    }

    @Override
    public boolean addLine(String content, int font, int padding, CssStyle... styles) {
        if (this.getMaxLines() < this.getTextData().size())
            this.setMaxLines(this.getMaxLines() + 1);

        return super.addLine(content, font, padding, styles);
    }
}