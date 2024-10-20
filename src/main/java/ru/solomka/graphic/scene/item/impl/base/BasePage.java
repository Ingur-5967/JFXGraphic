package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasePage extends BasePane implements Page {

    private int currentIndex;
    private final List<SceneItem<?>> content;

    public BasePage(int width, int height, String id) {
        super(width, height, id);
        this.currentIndex = 0;
        this.content = new ArrayList<>();
    }

    @Override
    public void setContent(SceneItem<?>... items) { this.content.addAll(Arrays.stream(items).toList()); }

    @Override
    public <T extends SceneItem<? extends Node>> T next() {
        if(!this.hasNext()) {
            return current();
        }
        return (T) this.content.get(++this.currentIndex);
    }

    @Override
    public <T extends SceneItem<? extends Node>> T previous() {
        if(this.currentIndex == 0) {
            return current();
        }
        return (T) this.content.get(--this.currentIndex);
    }

    @Override
    public <T extends SceneItem<? extends Node>> T current() { return (T) this.content.get(this.currentIndex); }

    @Override
    public boolean hasNext() { return !this.content.isEmpty() && this.currentIndex < this.content.size() - 1; }

    @Override
    public int getCurrentPage() { return this.currentIndex; }

    @Override
    public List<SceneItem<?>> getContent() { return this.content; }
}