package ru.solomka.graphic.event.device;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.awt.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Mouse {

    Location location;

    public void setLocation(double x, double y) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove((int) x, (int) y);
    }

    @Data
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Location {
        double x, y;
    }
}