package ru.solomka.graphic.scene.template.group;

import ru.solomka.graphic.scene.SceneEntry;
import ru.solomka.graphic.scene.template.Template;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultGroup implements TemplateGroup {

    private final List<Template> cache;

    public DefaultGroup(Template... templates) {
        this.cache = Arrays.stream(templates).collect(Collectors.toList());
    }

    @Override
    public void add(Template template) {
        this.cache.add(template);
    }

    @Override
    public void remove(String templateId) {
        this.cache.removeIf(template -> template.getTemplateId().equals(templateId));
    }

    @Override
    public void loadTemplates(SceneEntry entry) {
        this.cache.forEach(template -> template.load(entry));
    }

    @Override
    public Template getTemplateByFilter(Predicate<Template> filter) {
        return this.cache.stream().filter(filter)
                .findAny()
                .orElse(null);
    }
}