package ru.solomka.graphic.scene.template.impl;

import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.SceneEntry;
import ru.solomka.graphic.scene.template.BaseTemplate;
import ru.solomka.graphic.scene.template.Template;
import ru.solomka.graphic.scene.template.group.DefaultGroup;

public abstract class InitialSceneTemplate extends BaseTemplate {

    private final boolean loaded;

    public InitialSceneTemplate(String templateId, String groupId) {
        super(templateId);
        this.loaded = false;

        JFXGraphic.getTemplateContainer().add(
                groupId,
                JFXGraphic.getTemplateContainer().getOrDefault(groupId, new DefaultGroup(this))
        );

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