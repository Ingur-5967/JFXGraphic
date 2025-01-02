package ru.solomka.graphic.scene.item.impl;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.solomka.graphic.scene.item.impl.base.BaseImage;
import ru.solomka.graphic.scene.item.impl.base.BasePane;
import ru.solomka.graphic.scene.item.tag.enums.ItemAlignment;

public final class ImageViewer extends BaseImage {

    public ImageViewer(int width, int height, ItemAlignment orientation, Image source) {
        super(source, orientation);

        ImageView viewer = this.getNode();
        BasePane parent = (BasePane) this.getRoot();

        parent.setSize(width, height);

        viewer.setFitWidth(width);
        viewer.setFitHeight(height);

        parent.getChildren().add(viewer);
    }
}