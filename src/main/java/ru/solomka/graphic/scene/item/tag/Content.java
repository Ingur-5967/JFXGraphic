package ru.solomka.graphic.scene.item.tag;

public interface Content {

    /**
     * Returns content of root element
     *
     * @return Returns content of root element
     */
    <C> C getItemContent();
}