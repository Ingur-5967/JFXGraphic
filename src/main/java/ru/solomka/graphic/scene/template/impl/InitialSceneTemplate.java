package ru.solomka.graphic.scene.template.impl;

import ru.solomka.graphic.scene.SceneEntry;
import ru.solomka.graphic.scene.template.BaseTemplate;
import ru.solomka.graphic.scene.template.Template;

public abstract class InitialSceneTemplate extends BaseTemplate {

    private final boolean loaded;

    public InitialSceneTemplate(String id) {
        super(id);
        this.loaded = false;
    }

    @Override
    public Template unload(SceneEntry entry) {
        return super.unload(entry);
    }

    @Override
    public boolean isLoaded() {
        return this.loaded;
    }
}