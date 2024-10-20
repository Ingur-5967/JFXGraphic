package ru.solomka.graphic.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.Resource;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.Container;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data @FieldDefaults(level = AccessLevel.PRIVATE)
public final class SceneEntry {

    final String linkedFXML;
    @Setter List<SceneItem<?>> source;

    public SceneEntry(String fxml) {
        this.linkedFXML = fxml;
        this.source = new ArrayList<>();
    }

    public Scene initScene(int width, int height) {
        try {
            return new Scene(getFXMLLoader().load(), width, height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SceneItem<?> findElement(@NotNull Container container, Predicate<SceneItem<?>> filter) {
        return container.getSource().stream().filter(filter).findAny().orElse(null);
    }

    public void clear() {
        Pane viewed = (Pane) JFXGraphic.getPrimaryStage().getScene().getRoot();
        viewed.getChildren().clear();
        this.source.clear();
    }

    public FXMLLoader getFXMLLoader() {
        return new FXMLLoader(Resource.getFileAsResource(linkedFXML));
    }
}