package ru.solomka.graphic.scene.item.impl.text;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.graphic.scene.item.impl.base.BaseText;
import ru.solomka.graphic.tool.Pair;

import java.util.stream.Stream;

public class MultiLabel extends BaseText {

    private final Pair<Label, Label> content;

    public MultiLabel(@NotNull Label mainContent, Label subContent, int verticalSpace) {
        super(mainContent.getText(), (int) mainContent.getFont().getSize());

        this.content = new Pair<>(mainContent, subContent);

        AnchorPane parent = this.getItem();

        subContent.setLayoutX(mainContent.getLayoutX());
        subContent.setLayoutY(mainContent.getLayoutY() + verticalSpace);

        int maxWidth = Stream.of(mainContent, subContent).mapToInt(c -> (int) (c.getFont().getSize() + c.getText().length())).max().getAsInt();

        parent.setPrefSize(maxWidth, (verticalSpace + mainContent.getFont().getSize() + subContent.getFont().getSize())/1.35);

        parent.getChildren().add(mainContent);
        parent.getChildren().add(subContent);
    }

    @Override
    public Object getData() {
        return this.content;
    }
}