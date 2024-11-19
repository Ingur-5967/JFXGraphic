package ru.solomka.graphic.scene.item.impl.text;

import ru.solomka.graphic.scene.item.impl.base.BaseText;

public class SingleLabel extends BaseText {
    public SingleLabel(String content, int font) {
        super(content, font);
        this.getRoot().getChildren().add(this.getLineObject(0));
    }
}