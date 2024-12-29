package ru.solomka.graphic;

import java.io.InputStream;
import java.net.URL;

public class SceneFileResource {
    public static InputStream getFileAsStream(String absolutePath) {
        return JFXGraphic.getInstance().getClass().getClassLoader().getResourceAsStream(absolutePath);
    }

    public static URL getFileAsResource(String absolutePath) {
        return JFXGraphic.getInstance().getClass().getClassLoader().getResource(absolutePath);
    }
}