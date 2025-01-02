package ru.solomka.graphic;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.solomka.graphic.device.Mouse;
import ru.solomka.graphic.scene.SceneEntry;
import ru.solomka.graphic.scene.item.Location;
import ru.solomka.graphic.scene.template.TemplateContainer;


public abstract class JFXGraphic extends Application {

    @Setter
    @Getter
    private static JFXGraphic instance;

    @Getter
    private static TemplateContainer templateContainer;

    @Setter
    @Getter
    private static Stage primaryStage;

    @Getter
    @Setter
    private SceneEntry sceneEntry;

    @Getter
    private final Mouse mouse = new Mouse(new Location(0, 0), null);

    @Override
    public void start(Stage stage) {
        instance = this;
        templateContainer = TemplateContainer.create();

        this.onEnable(stage);

        if (sceneEntry == null)
            throw new NullPointerException("SceneEntry is not initialized");

        primaryStage = stage;

        stage.show();
    }

    @Override
    public void stop() {
        this.onDisable();
        instance = null;
    }


    public abstract void onEnable(Stage stage);
    public abstract void onDisable();
}