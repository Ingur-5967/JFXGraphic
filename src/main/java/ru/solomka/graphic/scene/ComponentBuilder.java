package ru.solomka.graphic.scene;

import javafx.scene.Node;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.style.CssStyle;


public class ComponentBuilder<T extends Node> {

    private final T source;

    private ComponentBuilder(T source) {
        this.source = source;
    }

    public static <S extends Node> ComponentBuilder<S> of(S source) {
        return new ComponentBuilder<>(source);
    }

    public ComponentBuilder<T> css(CssStyle... css) {
        this.source.setStyle(CssStyle.getCssString(css));
        return this;
    }

    public ComponentBuilder<T> id(String id) {
        this.source.setId(id);
        return this;
    }

    public Node get() {
        return this.source;
    }

    public SceneItem<? extends Node> toSceneItem() {
        return SceneItem.fromSource(this.source);
    }
}