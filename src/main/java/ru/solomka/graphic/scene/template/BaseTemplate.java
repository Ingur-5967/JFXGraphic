package ru.solomka.graphic.scene.template;

import ru.solomka.graphic.scene.SceneEntry;

public class BaseTemplate implements Template {

    private final String id;

    public BaseTemplate(String id) {
        if (id.isEmpty())
            throw new IllegalArgumentException("Template ID cannot be empty or null!");

        this.id = id;
    }

    @Override
    public void load(SceneEntry entry) {
    }

    @Override
    public Template unload(SceneEntry entry) {
        return null;
    }

    @Override
    public String getTemplateId() {
        return this.id;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public void setLoaded(boolean loaded) {
    }
}
