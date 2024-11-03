package ru.solomka.graphic.scene.item.tag;

import javafx.animation.Transition;

import java.util.function.Supplier;

public interface ItemAnimation {

    void setAnimationTag(boolean tag);

    boolean hasTag();

    default void animation(Supplier<? extends Transition> properties) {
        if (this.hasTag()) return;

        Transition animation = properties.get();
        animation.setOnFinished(type -> this.setAnimationTag(false));

        animation.play();

        this.setAnimationTag(true);
    }
}