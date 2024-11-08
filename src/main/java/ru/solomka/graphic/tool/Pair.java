package ru.solomka.graphic.tool;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Pair<F, S> {
    F first;
    S second;

    public static <F, S> Pair<F, S> create(F first, S second) {
        return new Pair<>(first, second);
    }
}