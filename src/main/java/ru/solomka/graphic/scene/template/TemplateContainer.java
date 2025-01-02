package ru.solomka.graphic.scene.template;

import ru.solomka.graphic.scene.template.group.TemplateGroup;

import java.util.LinkedHashMap;
import java.util.Optional;

public class TemplateContainer {

    private final LinkedHashMap<String, TemplateGroup> templates;

    private TemplateContainer() {
        this.templates = new LinkedHashMap<>();
    }

    public static TemplateContainer create() {
        return new TemplateContainer();
    }

    public final <G extends TemplateGroup> void add(String id, G group) {
        this.templates.put(id, group);
    }

    public <G extends TemplateGroup> G getOrDefault(String id, G initialGroup) {
        return Optional.ofNullable((G) this.templates.get(id)).orElse(initialGroup);
    }

    public <G extends TemplateGroup> Optional<G> remove(String id) {
        return Optional.ofNullable((G) this.templates.remove(id));
    }
}