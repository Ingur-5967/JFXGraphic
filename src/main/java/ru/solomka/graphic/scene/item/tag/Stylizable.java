package ru.solomka.graphic.scene.item.tag;

import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.base.BaseButton;
import ru.solomka.graphic.style.CssStyle;

public interface Stylizable {
    /**
     * Sets base element styles
     *
     * @param properties Styles for element (See {@link CssStyle})
     * @param <I>        Child of SceneElement (See: {@link BaseButton})
     * @return SceneElement with accepted styles
     */
    <I extends SceneItem> I initStyle(CssStyle... properties);

    /**
     * Sets root element styles
     *
     * @param properties Styles for element (See {@link CssStyle})
     * @param <I>        Child of SceneElement (See: {@link BaseButton})
     * @return SceneElement with accepted styles
     */
    <I extends SceneItem> I initParentStyle(CssStyle... properties);

    /**
     * Append element styles
     *
     * @param newProperties Styles for element (See {@link CssStyle})
     * @param <I>           Child of SceneElement (See: {@link BaseButton})
     * @return SceneElement with accepted styles
     */
    <I extends SceneItem> I appendStyle(SceneItem item, CssStyle... newProperties);
}