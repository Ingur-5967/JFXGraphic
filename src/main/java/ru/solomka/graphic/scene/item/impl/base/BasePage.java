package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasePage implements Page {

    private final List<SceneItem> data;

    private int currentIndex;

    public BasePage() {
        this.currentIndex = 0;
        this.data = new ArrayList<>();
    }

    @Override
    public <T extends SceneItem> T next() {
        if (!this.hasNext())
            return current();

        return (T) this.data.get(++this.currentIndex);
    }

    @Override
    public <T extends SceneItem> T previous() {
        if (this.currentIndex == 0)
            return current();

        return (T) this.data.get(--this.currentIndex);
    }

    @Override
    public <T extends SceneItem> T current() {
        return (T) this.data.get(this.currentIndex);
    }

    @Override
    public boolean hasNext() {
        return !this.data.isEmpty() && this.currentIndex < this.data.size() - 1;
    }

    public ItemSize getPageContentSize() {
        if (this.data.isEmpty())
            return new ItemSize(0, 0);

        double width = this.data.stream().mapToDouble(component -> ((AnchorPane) component.getRoot()).getPrefWidth()).sum();

        double height = this.data.stream().mapToDouble(component -> ((AnchorPane) component.getRoot()).getPrefHeight()).max()
                .orElseThrow(() -> new RuntimeException("Data of page cannot get a max height of element"));

        return new ItemSize(width, height);
    }

    @Override
    public int getCurrentPage() {
        return this.currentIndex;
    }

    @Override
    public List<SceneItem> getData() {
        return this.data;
    }

    @Override
    public void setData(SceneItem... items) {
        this.data.addAll(Arrays.stream(items).toList());
    }

}