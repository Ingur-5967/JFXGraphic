package ru.solomka.graphic.event.impl;

import javafx.scene.input.MouseButton;
import lombok.Getter;
import ru.solomka.graphic.event.Event;
import ru.solomka.graphic.event.device.Mouse;
import ru.solomka.graphic.scene.SceneEntry;

import java.awt.*;

@Getter
public class MouseMoveEvent implements Event {

    private final SceneEntry scene;
    private final Mouse userMouse;
    private final MouseButton pressedButton;

    public MouseMoveEvent(SceneEntry scene, MouseButton pressedButton, Mouse userMouse) {
        this.scene = scene;
        this.pressedButton = pressedButton;
        this.userMouse = userMouse;
    }

    public void setNewLocation(double x, double y) {
        try {
            userMouse.setLocation(x, y);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}