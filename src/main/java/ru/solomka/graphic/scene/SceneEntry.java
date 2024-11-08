package ru.solomka.graphic.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import ru.solomka.graphic.scene.item.impl.LinkedPane;
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

    @Getter
    final SizeProperties size;

    @Setter
    List<SceneItem<?>> source;

    public SceneEntry(SizeProperties size) {
        this.size = size;
        this.source = new ArrayList<>();
    }

    /**
     * Initializing a scene with region {@code parent}
     *
     * @param parent Target region for scene
     * @return Initialized scene
     */
    public Scene initScene(Parent parent) {
        return new Scene(parent, this.getSize().getWidth(), this.getSize().getHeight());
    }

    /**
     * Initializing a scene with source the {@code *.fxml} file
     * <p>
     * See: {@link SceneEntry#initScene(Parent)}
     * </p>
     *
     * @param linkLoader Path to the file *.fxml
     * @return Initialized scene
     */
    public Scene initScene(URL linkLoader) {
        try {
            FXMLLoader loader = new FXMLLoader(linkLoader);
            return this.initScene((Parent) loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns element that has passed the filter
     *
     * @param container Parent of nodes (For example: {@link LinkedPane})
     * @param filter Element filtering condition
     * @return Returns element that has passed the filter
     */
    public Optional<SceneItem<?>> findElement(Container container, Predicate<SceneItem<?>> filter) {
        return container.getSource().stream().filter(filter).findAny();
    }

    /**
     * Delete all components from current stage
     */
    public void clear() {
        Pane root = (Pane) JFXGraphic.getPrimaryStage().getScene().getRoot();
        root.getChildren().clear();
        this.source.clear();
    }
}