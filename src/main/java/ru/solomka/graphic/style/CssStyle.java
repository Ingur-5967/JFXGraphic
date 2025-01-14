package ru.solomka.graphic.style;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class CssStyle {

    private final String css;

    /**
     * Concatenate all strings into a single string to correctly pass the style for the object.
     *
     * @see Properties
     * @param properties Collection of CssStyle with Properties
     * @return A single line with styles specified
     */
    public static @NonNull String getCssString(CssStyle... properties) {
        return String.join(";", Arrays.stream(properties).map(CssStyle::getCss).toList());
    }

    public static CssStyle[] create(String... properties) {
        return Arrays.stream(properties).map(CssStyle::new).toArray(CssStyle[]::new);
    }

    public static CssStyle empty() {
        return new CssStyle("");
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