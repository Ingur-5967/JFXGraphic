package ru.solomka.graphic.scene.item.impl.text;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.impl.base.BaseText;
import ru.solomka.graphic.tool.Pair;

public class DefaultLabel extends BaseText {

    public DefaultLabel(String content, Pair<Integer, String> setting) {
        super(content, setting.getFirst());

        AnchorPane parent = this.getItem();

        Label text = (Label) this.getData();
        text.setStyle(setting.getSecond());

        parent.setPrefSize((int) (text.getFont().getSize() / 2 + text.getText().length()), setting.getFirst());

        text.setLayoutX(0);
        text.setLayoutY((parent.getPrefHeight() / setting.getFirst() + 1) - text.getFont().getSize() / (text.getFont().getSize() / 2));

        parent.getChildren().add(text);
    }
}