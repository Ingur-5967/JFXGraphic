package ru.solomka.graphic.scene.item.impl.field;

import javafx.scene.control.TextField;
import lombok.Getter;
import ru.solomka.graphic.scene.item.impl.base.BaseInput;
import ru.solomka.graphic.scene.item.tag.filter.ObjectFilter;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
public final class FilteredField extends BaseInput implements ObjectFilter<String> {

    private Predicate<String> criteria;

    public FilteredField() {
        this.criteria = _ -> true;
    }

    @Override
    public boolean validateWithPrincipe(String object) {
        return criteria.test(object);
    }

    @Override
    public void setFilterPrincipe(Predicate<String> criteria) {
        this.criteria = criteria;
    }

    /**
     * Returns any value after validate by {@code Predicate<String>}
     * <br>
     * If test is not passed, returns Optional[null]
     *
     * @return Returns any value after validate by {@code Predicate<String>}
     */
    public Optional<String> getContentAfterCriteriaTest() {
        TextField box = this.getNode();
        return Optional.ofNullable(box).stream()
                .map(TextField::getText)
                .filter(this::validateWithPrincipe)
                .findFirst();
    }
}