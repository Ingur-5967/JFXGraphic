package ru.solomka.graphic.scene.template;

import ru.solomka.graphic.scene.SceneEntry;

public interface Template {
    void load(SceneEntry entry);

    Template unload(SceneEntry entry);

    String getTemplateId();

    boolean isLoaded();

    void setLoaded(boolean loaded);
}