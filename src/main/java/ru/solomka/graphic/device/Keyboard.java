package ru.solomka.graphic.device;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//todo

public class Keyboard {

    private final Map<KeyCode, Consumer<Object>> BIND = new HashMap<>();

    public void bind(KeyCode key, Consumer<Object> consumer) {
        BIND.put(key, consumer);
    }

    public void unbind(KeyCode key) {
        BIND.remove(key);
    }
}