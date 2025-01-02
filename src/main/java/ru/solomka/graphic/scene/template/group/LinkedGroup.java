package ru.solomka.graphic.scene.template.group;

import ru.solomka.graphic.scene.SceneEntry;
import ru.solomka.graphic.scene.template.ParentLink;
import ru.solomka.graphic.scene.template.Template;
import ru.solomka.graphic.scene.template.impl.OrderlySceneTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LinkedGroup implements TemplateGroup {

    private final List<ParentLink<? extends Template, ? extends Template>> cache;

    public LinkedGroup(Template... templates) {
        this.cache = new ArrayList<>();

        boolean findInvalidObjects = Arrays.stream(templates).anyMatch(template -> !(template instanceof OrderlySceneTemplate));

        if (findInvalidObjects)
            throw new RuntimeException("LinkedGroup support only OrderlySceneTemplate objects");

        for (Template template : templates)
            this.cache.add(new ParentLink<>(template, ((((OrderlySceneTemplate) template).getLoadBefore()))));
    }

    @Override
    public void add(Template template) {
        if (!(template instanceof OrderlySceneTemplate))
            throw new RuntimeException(String.format("LinkedGroup don't support %s", template.getClass().getName()));

        this.cache.add(new ParentLink<>(template, ((OrderlySceneTemplate) template).getLoadBefore()));
    }

    @Override
    public void remove(String templateId) {
        this.cache.stream().map(ParentLink::getChild)
                .findFirst()
                .ifPresent(this.cache::remove);
    }

    /**
     * Load filtered list of {@link OrderlySceneTemplate} which are valid for uploading
     */
    public void loadTemplates(SceneEntry entry) {
        List<OrderlySceneTemplate> templates = cache.stream().map(ParentLink::getParent)
                .filter(template -> template instanceof OrderlySceneTemplate)
                .map(template -> (OrderlySceneTemplate) template)
                .filter(template -> !template.isLoaded() && template.getLoadBefore() != null)
                .toList();

        templates.forEach(template -> template.load(entry));
    }

    @Override
    public Template getTemplateByFilter(Predicate<Template> filter) {
        return this.cache.stream().map(ParentLink::getParent)
                .filter(filter)
                .findAny()
                .orElse(null);
    }

    public List<ParentLink<? extends Template, ? extends Template>> getaTemplateCache() {
        return this.cache;
    }
}