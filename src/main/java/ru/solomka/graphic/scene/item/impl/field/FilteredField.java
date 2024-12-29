package ru.solomka.graphic.scene.item.impl.field;

import javafx.scene.control.TextField;
import ru.solomka.graphic.scene.item.impl.base.BaseInput;

import java.util.Optional;

public class FilteredField extends BaseInput {

    public FilteredField() {
    }

    /**
     * Returns any value after validate by {@code Predicate<String>}
     * <br>
     * If test is not passed, returns Optional[null]
     *
     * @return Returns any value after validate by {@code Predicate<String>}
     */
    public Optional<String> getContentAfterCriteriaTest() {
        TextField box = (TextField) this.getElementContent();
        return Optional.ofNullable(box).stream()
                .map(TextField::getText)
                .filter(this::validateWithPrincipe)
                .findFirst();
    }
}