package ru.solomka.graphic.scene.template.impl;

import lombok.Getter;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.SceneEntry;
import ru.solomka.graphic.scene.template.BaseTemplate;
import ru.solomka.graphic.scene.template.Template;
import ru.solomka.graphic.scene.template.TemplateParent;
import ru.solomka.graphic.scene.template.group.LinkedGroup;

public abstract class OrderlySceneTemplate extends BaseTemplate implements TemplateParent {

    @Getter
    private final Template loadBefore;

    private boolean loaded = false;

    public OrderlySceneTemplate(String id, String groupId, Template loadBefore) {
        super(id);

        this.loadBefore = loadBefore;

        JFXGraphic.getTemplateContainer().add(
                groupId,
                JFXGraphic.getTemplateContainer().getOrDefault(groupId, new LinkedGroup(this))
        );
    }

    @Override
    public void loadTemplateParent(SceneEntry entry) {
        if (loadBefore == null) return;

        if (!loadBefore.isLoaded()) {
            loadBefore.load(entry);
            loadBefore.setLoaded(true);
        }
    }

    @Override
    public boolean isLoaded() {
        return this.loaded;
    }

    @Override
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}