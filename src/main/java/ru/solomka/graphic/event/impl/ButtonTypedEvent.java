package ru.solomka.graphic.event.impl;

import lombok.Getter;
import ru.solomka.graphic.event.Event;
import ru.solomka.graphic.event.device.Keyboard;
import ru.solomka.graphic.scene.item.impl.base.BaseField;

@Getter
public class ButtonTypedEvent implements Event {

    private final BaseField field;
    private final Keyboard keyboard;

    public ButtonTypedEvent(BaseField field, Keyboard keyboard) {
        this.field = field;
        this.keyboard = keyboard;
    }
}