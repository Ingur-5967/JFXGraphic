package ru.solomka.graphic.scene.template.group;

import ru.solomka.graphic.scene.template.Template;

import java.util.List;
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
     * Returns template if template id identical
     *
     * @return Returns template if template id identical
     */
    Template getTemplateByFilter(Predicate<Template> filter);

    /**
     * Return templates
     *
     * @return Returns templates
     */
    List<? extends Template> getTemplates();
}