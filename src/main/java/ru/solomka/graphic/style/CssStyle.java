package ru.solomka.graphic.style;


import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CssStyle {

    String css;

    /**
     * Concatenate all strings into a single string to correctly pass the style for the object.
     * <p>
     * {@code new CssString(CssStyle.Properties.TEXT_FILL_COLOR.getProperty("red"))}
     * </p>
     *
     * @param styles Collection of CssStyle with Properties
     * @return A single line with styles specified
     */
    public static @NonNull String getCssString(CssStyle... styles) {
        return String.join(";", Arrays.stream(styles).map(CssStyle::getCss).toList());
    }

    public static CssStyle[] create(String... properties) {
        return Arrays.stream(properties).map(CssStyle::new).toArray(CssStyle[]::new);
    }

    public static CssStyle empty() {
        return new CssStyle("");
    }

    public static Scene getTransparentWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    public enum Properties {

        TEXT_FILL_COLOR("-fx-text-fill: %s"),
        PROMPT_TEXT_FILL_COLOR("-fx-prompt-text-fill: %s"),
        BACKGROUND_COLOR("-fx-background-color: %s"),
        TEXT_BOX_BORDER("-fx-text-box-border: %s"),
        BORDER_COLOR("-fx-border-color: %s"),
        FONT_SIZE("-fx-font-size: %s"),
        OPACITY("-fx-opacity: %s"),
        FOCUS_COLOR("-fx-focus-color: %s"),
        PADDING("-fx-padding: %s"),
        BORDER_RADIUS("-fx-border-radius: %s"),
        FONT_FAMILY("-fx-font-family: %s"),
        BACKGROUND_RADIUS("-fx-background-radius: %s"),
        OTHER("%s");

        private final String property;

        Properties(String property) {
            this.property = property;
        }

        public String getProperty(Object value) {
            return String.format(property, value);
        }
    }
}