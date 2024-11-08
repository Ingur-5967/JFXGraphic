package ru.solomka.graphic.scene.item.impl.text;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.impl.base.BaseText;
import ru.solomka.graphic.tool.Pair;

import java.util.List;

public class SingleLabel extends BaseText {

    public SingleLabel(String content, Pair<Integer, String> setting) {
        super(content, setting.getFirst());

        AnchorPane parent = this.getElement();

        parent.setStyle("-fx-background-color: #fff");

        List<Label> elementData = (List<Label>) this.getElementContent();
        Label text = elementData.getFirst();
        text.setStyle(setting.getSecond());

        parent.setPrefSize((int) (text.getFont().getSize() / 1.5 * text.getText().length() / 1.5), setting.getFirst() + content.length() / 1.31);

        text.setLayoutX(0);
        text.setLayoutY((parent.getPrefHeight() / setting.getFirst() + 1) - text.getFont().getSize() / (text.getFont().getSize()) / 1.111);

        parent.getChildren().add(text);
    }
}