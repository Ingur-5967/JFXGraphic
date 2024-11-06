package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasePage extends BasePane implements Page {

    private final List<SceneItem<?>> data;
    private int currentIndex;

    public BasePage(int width, int height, String id) {
        super(width, height, id);
        this.currentIndex = 0;
        this.data = new ArrayList<>();
    }

    @Override
    public <T extends SceneItem<? extends Node>> T next() {
        if (!this.hasNext()) {
            return current();
        }
        return (T) this.data.get(++this.currentIndex);
    }

    @Override
    public <T extends SceneItem<? extends Node>> T previous() {
        if (this.currentIndex == 0) {
            return current();
        }
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