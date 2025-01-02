package ru.solomka.graphic.device;

import javafx.scene.input.MouseButton;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.solomka.graphic.scene.item.Location;

import java.awt.*;

@Getter
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Mouse {

    Location location;
    MouseButton lastButtonClicked;

    public void move(double x, double y) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove((int) x, (int) y);
    }

    public void setLocation(double x, double y) {
        this.location = new Location(x, y);
    }

}