package ru.solomka.graphic;

import javafx.application.Application;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.solomka.graphic.device.Mouse;
import ru.solomka.graphic.scene.SceneEntry;


public abstract class JFXGraphic extends Application {

    @Setter
    @Getter
    private static JFXGraphic graphicInstance;

    @Setter
    @Getter
    private static Stage primaryStage;

    @Getter
    private Mouse mouseInstance;

    @Getter
    @Setter
    private SceneEntry scene;

    @Override
    public void start(Stage stage) {
        this.onEnable(stage);

        if (scene == null)
            throw new NullPointerException("SceneEntry is not initialized");

        if (primaryStage == null)
            throw new NullPointerException("Stage is not initialized");

        this.mouseInstance = new Mouse(new Mouse.Location(0, 0), MouseButton.NONE);

        Pane region = (Pane) primaryStage.getScene().getRoot();

        region.setOnMouseMoved(mouse -> this.mouseInstance.setLocation(mouse.getSceneX(), mouse.getSceneY()));
        region.setOnMouseClicked(mouse -> this.mouseInstance.setLastButtonClicked(mouse.getButton()));

        stage.show();
    }

    @Override
    public void stop() {
        this.onDisable();
        primaryStage.close();
        primaryStage = null;
        graphicInstance = null;
        this.scene = null;
        this.mouseInstance = null;
    }

    public abstract void onEnable(Stage stage);

    public abstract void onDisable();
}