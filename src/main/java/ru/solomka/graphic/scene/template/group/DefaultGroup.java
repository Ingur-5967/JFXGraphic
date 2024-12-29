package ru.solomka.graphic.scene.template.group;

import ru.solomka.graphic.scene.template.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class DefaultGroup implements TemplateGroup {

    private final List<Template> cache;

    public DefaultGroup() {
        this.cache = new ArrayList<>();
    }

    public DefaultGroup(Template... templates) {
        this.cache = Arrays.stream(templates).toList();
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
    public Template getTemplateByFilter(Predicate<Template> filter) {
        return this.cache.stream().filter(filter)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<? extends Template> getTemplates() {
        return this.cache;
    }
}