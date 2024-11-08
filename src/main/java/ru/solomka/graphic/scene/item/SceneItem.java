package ru.solomka.graphic.scene.item;

import javafx.scene.Node;
import ru.solomka.graphic.scene.item.impl.base.BaseButton;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;

public interface SceneItem<T extends Node> {

    /**
     * Sets element styles
     *
     * @param properties Styles for element (See {@link CssStyle})
     * @param <I> Child of SceneElement (See: {@link BaseButton})
     * @return SceneElement with accepted styles
     */
    <I extends SceneItem<T>> I initStyle(CssStyle... properties);

    default <I extends SceneItem<T>> I initStyle(SceneItem<?> item, CssStyle... properties) {
        return null;
    }

    default <I extends SceneItem<T>> I initStyle(Node node, CssStyle... properties) {
        return null;
    }

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
     * Returns root element
     *
     * @return Returns root element
     */
    T getElement();

    /**
     * Returns content of root element
     *
     * @return Returns content of root element
     */
    default Object getElementContent() {
        return null;
    }


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
            public N getElement() {
                return source;
            }
        };
    }
}