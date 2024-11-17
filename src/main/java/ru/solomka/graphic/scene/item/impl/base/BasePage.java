package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.SizeProperties;
import ru.solomka.graphic.scene.item.tag.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasePage extends BasePane implements Page {

    private final List<SceneItem<?>> data;
    private int currentIndex;

    public BasePage(int width, int height) {
        super(width, height);
        this.currentIndex = 0;
        this.data = new ArrayList<>();
    }

    @Override
    public <T extends SceneItem<? extends Node>> T next() {
        if (!this.hasNext())
            return current();

        return (T) this.data.get(++this.currentIndex);
    }

    @Override
    public <T extends SceneItem<? extends Node>> T previous() {
        if (this.currentIndex == 0)
            return current();

        return (T) this.data.get(--this.currentIndex);
    }

    @Override
    public <T extends SceneItem<? extends Node>> T current() {
        return (T) this.data.get(this.currentIndex);
    }

    @Override
    public boolean hasNext() {
        return !this.data.isEmpty() && this.currentIndex < this.data.size() - 1;
    }

    @Override
    public SizeProperties getSize() {
        if (this.data.isEmpty())
            return new SizeProperties(0, 0);

        double width = this.data.stream().mapToDouble(c -> ((AnchorPane) c.getElement()).getPrefWidth()).sum();
        double height = this.data.stream().mapToDouble(c -> ((AnchorPane) c.getElement()).getPrefHeight()).max().orElse(-1);

        if (height == -1)
            throw new RuntimeException("Data of page cannot get a max height of element");

        return new SizeProperties(width, height);
    }

    @Override
    public int getCurrentPage() {
        return this.currentIndex;
    }

    @Override
    public List<SceneItem<?>> getData() {
        return this.data;
    }

    @Override
    public void setData(SceneItem<?>... items) {
        this.data.addAll(Arrays.stream(items).toList());
    }

}