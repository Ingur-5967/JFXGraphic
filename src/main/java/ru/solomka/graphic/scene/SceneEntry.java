package ru.solomka.graphic.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.SizeProperties;
import ru.solomka.graphic.scene.item.tag.Container;

import java.io.IOException;
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

    public Scene initScene() {
        try {
            Optional<FXMLLoader> loader = this.getFXMLLoader();

            if (loader.isEmpty())
                throw new NullPointerException("FXML loader is null");

            return new Scene(loader.get().load(), this.getSize().getWidth(), this.getSize().getHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<SceneItem<?>> findElement(Container container, Predicate<SceneItem<?>> filter) {
        return container.getSource().stream().filter(filter).findAny();
    }

    public void clear() {
        Pane viewed = (Pane) JFXGraphic.getPrimaryStage().getScene().getRoot();
        viewed.getChildren().clear();
        this.source.clear();
    }

    public @NonNull Optional<FXMLLoader> getFXMLLoader() {
        return Optional.of(new FXMLLoader(JFXGraphic.getGraphicInstance().getClass().getResource(linkedFXML)));
    }
}