package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Root;

public class BaseInput extends BaseItem<TextInputControl> implements Root {

    public BaseInput(ItemSize size) {
        super(() -> {
            TextField field = new TextField();
            field.setPrefSize(size.getWidth(), size.getHeight());
            return field;
        }, () -> new LinkedPane(size.getWidth(), size.getHeight()));

        this.setSize(size.getWidth(), size.getHeight());
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        parent.addChildren((BasePane) this.getRoot());
        this.setLocation(x, y);
    }
}