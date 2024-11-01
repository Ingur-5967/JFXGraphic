package ru.solomka.graphic;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.solomka.graphic.event.EventProvider;
import ru.solomka.graphic.event.Priority;
import ru.solomka.graphic.event.device.Mouse;
import ru.solomka.graphic.event.impl.ButtonTypedEvent;
import ru.solomka.graphic.event.impl.MouseMoveEvent;
import ru.solomka.graphic.scene.SceneEntry;

public abstract class JFXGraphic extends Application {

    @Getter @Setter
    protected static JFXGraphic graphicInstance;

    @Getter @Setter
    private static Stage primaryStage;

    @Getter
    private Mouse mouseInstance;

    @Getter @Setter
    private SceneEntry scene;

    @Override
    public void start(Stage stage) {
        this.onEnable(stage);

        if(scene == null)
            throw new NullPointerException("SceneEntry is not initialized");

        this.mouseInstance = new Mouse(new Mouse.Location(0, 0));

        Pane region = (Pane) primaryStage.getScene().getRoot();

        region.setOnMouseMoved(mouse -> {
            try {
                EventProvider.call(new MouseMoveEvent(scene, mouse.getButton(), this.mouseInstance), Priority.values());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        region.setOnKeyTyped(_ -> {
            try {
                EventProvider.call(new ButtonTypedEvent(null, null));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void stop() {
        this.onDisable();
        primaryStage.close();
        primaryStage = null;
        this.mouseInstance = null;
    }

    public abstract void onEnable(Stage stage);
    public abstract void onDisable();
}