package ru.solomka.graphic.tool;

import ru.solomka.graphic.JFXGraphic;

import java.io.InputStream;
import java.net.URL;

public class ResourceUtils {
    public static InputStream getFileAsStream(String absolutePath) {
        return JFXGraphic.getGraphicInstance().getClass().getClassLoader().getResourceAsStream(absolutePath);
    }

    public static URL getFileAsResource(String absolutePath) {
        return JFXGraphic.getGraphicInstance().getClass().getClassLoader().getResource(absolutePath);
    }
}