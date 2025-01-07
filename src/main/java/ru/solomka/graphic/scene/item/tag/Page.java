package ru.solomka.graphic.scene.item.tag;

import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.base.BasePage;

import java.util.List;

public interface Page {

    /**
     * Returns current content the container
     *
     * @return Returns current content the container
     */
    List<SceneItem> getData();

    /**
     * Sets new elements to the container
     *
     * @param items Target elements to change
     */
    void setData(SceneItem... items);

    /**
     * Returns current element by index
     *
     * @param <T> Child of SceneItem
     * @return Returns current element by {@code currentIndex} in list
     * @see SceneItem
     * @see BasePage
     */
    <T extends SceneItem> T current();

    /**
     * Returns current element by index
     *
     * @param <T> Child of SceneItem
     * @return Returns element by {@code (currentIndex + 1)} in list
     * @see SceneItem
     * @see BasePage
     */
    <T extends SceneItem> T next();

    /**
     * Returns current element by index
     *
     * @param <T> Child of SceneItem
     * @return Returns element by {@code (currentIndex - 1)} in list
     * @see SceneItem
     * @see BasePage
     */
    <T extends SceneItem> T previous();

    /**
     * Returns the result of checking for the presence of the next element in the list by index
     * <p>
     * {@code currentIndex + 1}
     * </p>
     *
     * @return Returns the result of checking to the next element
     */
    boolean hasNext();


    /**
     * Returns the current index
     *
     * @return Returns the current index
     */
    int getCurrentPage();
}