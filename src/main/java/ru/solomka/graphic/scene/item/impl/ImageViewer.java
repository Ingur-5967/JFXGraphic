package ru.solomka.graphic.scene.item.impl;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.impl.base.BaseImage;
import ru.solomka.graphic.scene.item.tag.enums.ItemAlignment;

public class ImageViewer extends BaseImage {

    public ImageViewer(int width, int height, ItemAlignment orientation, Image source) {
        super(source, orientation);

        ImageView viewer = (ImageView) this.getElementContent();
        AnchorPane parent = this.getRoot().getBaseRegion();

        parent.setPrefSize(width, height);

        viewer.setFitWidth(width);
        viewer.setFitHeight(height);

        parent.getChildren().add(viewer);
    }
}