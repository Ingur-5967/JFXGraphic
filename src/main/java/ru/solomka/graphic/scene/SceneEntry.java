package ru.solomka.graphic.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.SizeProperties;
import ru.solomka.graphic.scene.item.tag.Container;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class SceneEntry {

    final String linkedFXML;
    @Getter
    final SizeProperties size;

    @Setter
    List<SceneItem<?>> source;

    public SceneEntry(String fxml, SizeProperties size) {
        this.linkedFXML = fxml;
        this.size = size;
        this.source = new ArrayList<>();
    }

    public Scene initScene(URL linkLoader) {
        try {
            FXMLLoader loader = new FXMLLoader(linkLoader);

            return new Scene(loader.load(), this.getSize().getWidth(), this.getSize().getHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<SceneItem<?>> findElement(Container container, Predicate<SceneItem<?>> filter) {
        return container.getSource().stream().filter(filter).findAny();
    }

    public void clear() {
        Pane root = (Pane) JFXGraphic.getPrimaryStage().getScene().getRoot();
        root.getChildren().clear();
        this.source.clear();
    }
}