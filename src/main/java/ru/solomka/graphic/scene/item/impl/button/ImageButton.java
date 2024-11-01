package ru.solomka.graphic.scene.item.impl.button;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.item.impl.ImageViewer;
import ru.solomka.graphic.scene.item.tag.Interact;
import ru.solomka.graphic.scene.item.tag.ItemAnimation;
import ru.solomka.graphic.scene.item.tag.enums.ItemAlignment;
import ru.solomka.graphic.tool.Pair;

import java.util.function.BiConsumer;

public class ImageButton extends ImageViewer implements Interact, ItemAnimation {

    private boolean animation;

    public ImageButton(int width, int height, ItemAlignment orientation, Image source) {
        super(width, height, orientation, source);
        this.animation = false;
    }

    @Override
    public <E extends Event> void setup(BiConsumer<E, Pair<Stage, Node>> interact) {
        this.getItem().setOnMouseClicked(e -> interact.accept((E) e, new Pair<>(JFXGraphic.getPrimaryStage(), this.getItem())));
    }

    @Override
    public void setAnimationTag(boolean tag) {
        this.animation = tag;
    }

    @Override
    public boolean hasTag() {
        return this.animation;
    }
}