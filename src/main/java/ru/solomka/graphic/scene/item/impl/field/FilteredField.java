package ru.solomka.graphic.scene.item.impl.field;

import javafx.scene.control.TextField;
import ru.solomka.graphic.scene.item.impl.base.BaseInput;

import java.util.Optional;

public class FilteredField extends BaseInput {

    public FilteredField() {
    }

    /**
     * Returns any value after validate with {@code Predicate<String>}
     * <br>
     * Value can be {@code null}, if criteria is not passed
     *
     * @return Returns any value after validate with {@code Predicate<String>}
     */
    public Object getContentAfterCriteriaTest() {
        return Optional.ofNullable((TextField) this.getElementContent())
                .filter(field -> this.validateWithPrincipe(field.getText()))
                .map(TextField::getText)
                .orElse(null);
    }
}