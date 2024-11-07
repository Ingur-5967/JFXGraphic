package ru.solomka.graphic.scene.item.tag;

import javafx.animation.Transition;

import java.util.function.Supplier;

public interface ItemAnimation {

    /**
     * The behavior of the animation depends on the state of the flag
     *
     * @param tag flag state
     */
    void setAnimationTag(boolean tag);

    /**
     * Returns state of tag
     * @return Returns state of tag
     */
    boolean hasTag();

    /**
     * Play animation with specified settings (It is possible if the tag is present).
     * After the animation played, the flag switched itself to the opposite state.
     * <p>
     * {@code element.animation(() -> RotateTransition anim = new RotateTransition(...); ...) }
     *
     * @param properties basic setting for {@code Animation}
     */
    default void animation(Supplier<? extends Transition> properties) {
        if (this.hasTag()) return;

        Transition animation = properties.get();
        animation.setOnFinished(_ -> this.setAnimationTag(false));

        animation.play();

        this.setAnimationTag(true);
    }
}