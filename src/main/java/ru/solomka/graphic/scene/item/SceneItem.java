package ru.solomka.graphic.scene.item;

import javafx.scene.Node;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

public interface SceneItem<T extends Node> {

    /**
     * Sets element styles
     *
     * @param properties Styles for element (See {@code CssStyle})
     * @param <I>        Child of SceneElement (For example: {@code BaseButton})
     * @return SceneElement with accepted styles
     */

    <I extends SceneItem<T>> I initStyle(CssStyle... properties);

    /**
     * Sets new location on scene
     *
     * @param x Coordinate X
     * @param y Coordinate Y
     */

    void setLocation(double x, double y);

    /**
     * Changes the location by adding indents to the coordinates
     *
     * @param padding Indents on all sides
     */

    void setLocation(Padding padding);

    default void setInnerLocation(SceneItem<T> element, double x, double y) {
    }

    /**
     * Returns current element
     *
     * @return Returns current element
     */

    T getItem();

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

}