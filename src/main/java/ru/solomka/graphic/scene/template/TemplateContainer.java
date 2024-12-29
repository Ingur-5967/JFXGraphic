package ru.solomka.graphic.scene.template;

import ru.solomka.graphic.scene.template.group.TemplateGroup;

import java.util.HashMap;
import java.util.Map;

public class TemplateContainer {

    private final Map<String, TemplateGroup> templates;

    private TemplateContainer() {
        this.templates = new HashMap<>();
    }

    public static TemplateContainer create() {
        return new TemplateContainer();
    }

    public final <G extends TemplateGroup> void add(String id, G group) {
        this.templates.put(id, group);
    }

    public <G extends TemplateGroup> G get(String id) {
        return (G) this.templates.get(id);
    }

    public <G extends TemplateGroup> G remove(String id) {
        return (G) this.templates.remove(id);
    }
}