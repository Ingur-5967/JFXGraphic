module ru.solomka.graphic {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.net.http;
    requires java.sql;
    requires lombok;
    requires java.naming;
    requires org.jetbrains.annotations;

    opens ru.solomka.graphic to javafx.fxml;
    exports ru.solomka.graphic;

    opens ru.solomka.graphic.scene to javafx.fxml;
    exports ru.solomka.graphic.scene;

    opens ru.solomka.graphic.tool to javafx.fxml;
    exports ru.solomka.graphic.tool;
    exports ru.solomka.graphic.event;
    opens ru.solomka.graphic.event to javafx.fxml;
    exports ru.solomka.graphic.event.device;
    opens ru.solomka.graphic.event.device to javafx.fxml;
}