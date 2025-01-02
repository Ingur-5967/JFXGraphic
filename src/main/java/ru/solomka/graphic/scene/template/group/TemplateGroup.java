package ru.solomka.graphic.scene.template.group;

import ru.solomka.graphic.scene.SceneEntry;
import ru.solomka.graphic.scene.template.Template;

import java.util.function.Predicate;

public interface TemplateGroup {
    /**
     * Adds template to cache
     *
     * @param template Template for scene
     */
    void add(Template template);

    /**
     * Remove template by id
     *
     * @param templateId Template id
     */
    void remove(String templateId);

    /**
     * Load template from cache
     *
     * @param entry SceneEntry where the objects will be placed
     */
    void loadTemplates(SceneEntry entry);

    /**
     * Returns template if template id identical
     *
     * @return Returns template if template id identical
     */
    Template getTemplateByFilter(Predicate<Template> filter);
}