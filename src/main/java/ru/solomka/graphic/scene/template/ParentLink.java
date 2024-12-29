package ru.solomka.graphic.scene.template;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ParentLink<P, C> {
    private final P parent;
    private final C child;
}
