package ru.solomka.graphic.scene.item;

import javafx.scene.Node;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

public interface SceneItem<T extends Node> {

    @SuppressWarnings("unchecked")
    static <N extends Node> SceneItem<N> fromSource(N source) {
        return new SceneItem<>() {
            @Override
            public <I extends SceneItem<N>> I initStyle(CssStyle... properties) {
                Class<I> clazz = (Class<I>) source.getClass();

                Object instance;

                try {
                    instance = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                source.setStyle(CssStyle.getCssString(properties));

                return (I) instance;
            }

            @Override
            public void setLocation(double x, double y) {
                source.setLayoutX(x);
                source.setLayoutY(y);
            }

            @Override
            public void setLocation(Padding padding) {
                source.setLayoutX(source.getLayoutX() + padding.getLeft() + padding.getRight());
                source.setLayoutY(source.getLayoutY() + padding.getTop() + padding.getBottom());
            }

            @Override
            public N getItem() {
                return source;
            }
        };
    }

    <I extends SceneItem<T>> I initStyle(CssStyle... properties);

    void setLocation(double x, double y);

    void setLocation(Padding padding);

    T getItem();
}