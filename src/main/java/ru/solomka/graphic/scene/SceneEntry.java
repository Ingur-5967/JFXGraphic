package ru.solomka.graphic.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import ru.solomka.graphic.JFXGraphic;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.base.BasePane;
import ru.solomka.graphic.scene.item.tag.Container;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public final class SceneEntry {

    @Getter
    private final ItemSize size;

    @Setter
    private Scene scene;

    @Getter
    @Setter
    private Container mainLayout;

    public SceneEntry(Container mainLayout, ItemSize size) {
        this.size = size;
        this.scene = null;
        this.mainLayout = mainLayout;
    }

    public SceneEntry(Container parent) {
        this(parent, ((SceneItem) parent).getSize());
    }

    public static Scene getTransparentWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        return scene;
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
     * Initializing a scene with region {@code parent}
     *
     * @param parent Target region for scene
     * @return Initialized scene
     */
    public Scene initScene(Parent parent) {
        if (scene != null) return scene;
        this.setScene(new Scene(parent, this.getSize().getWidth(), this.getSize().getHeight()));
        return this.scene;
    }

    /**
     * Returns element that has passed the filter
     *
     * @param container Parent of nodes {@link BasePane}
     * @param filter Element filtering condition
     * @return Returns element that has passed the filter
     */
    public Optional<SceneItem> findElement(Container container, Predicate<SceneItem> filter) {
        return container.getSource().stream().filter(filter).findAny();
    }

    /**
     * Returns mapped all nodes on scene to {@code SceneItem<Node>}
     *
     * @return Returns mapped all nodes on scene to {@code SceneItem<Node>}
     * @throws IllegalStateException If scene is not initialized from {@link SceneEntry#getScene}
     */
    public List<SceneItem> getSceneContent() {
        Pane root = (Pane) this.getScene().getRoot();
        return root.getChildren()
                .stream()
                .map(SceneItem::fromSource)
                .toList();
    }

    /**
     * Returns initialized scene object
     *
     * @return Returns initialized scene object
     * @throws IllegalStateException If scene is not initialized
     */
    public Scene getScene() {
        if (scene == null)
            throw new IllegalStateException("Scene has not been initialized");

        return scene;
    }

    /**
     * Reset all items on scene
     */
    public void update() {
        List<SceneItem> cleanedItems = this.clear();
        cleanedItems.forEach(mainLayout::addChildren);
    }

    /**
     * Delete all components from current stage and returns itself items
     */
    public List<SceneItem> clear() {
        List<SceneItem> cache = this.getSceneContent();
        Pane root = (Pane) JFXGraphic.getPrimaryStage().getScene().getRoot();

        root.getChildren().removeIf(item -> item != ((BasePane) mainLayout).getNode());
        return cache;
    }
}