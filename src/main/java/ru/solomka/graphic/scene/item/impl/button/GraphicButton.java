package ru.solomka.graphic.scene.item.impl.button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.impl.base.BaseButton;

public class GraphicButton extends BaseButton {

    public GraphicButton(ItemSize size, String icon) {
        super(size, "", 0);

        Image image = new Image(icon);

        ImageView view = new ImageView(image);
        view.setFitHeight(size.getHeight());
        view.setFitWidth(size.getWidth());

        Button button = this.getNode();
        button.setGraphic(view);

        button.setPadding(Insets.EMPTY);
    }
}