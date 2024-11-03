package ru.solomka.graphic;

import java.io.InputStream;
import java.net.URL;

public class Resource {

    public static InputStream getFileAsStream(String absolutePath) {
        return JFXGraphic.getGraphicInstance().getClass().getResourceAsStream(absolutePath);
    }

    public static URL getFileAsResource(String absolutePath) {
        return JFXGraphic.getGraphicInstance().getClass().getResource(absolutePath);
    }
}