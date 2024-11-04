module ru.solomka.graphic {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.net.http;
    requires java.sql;
    requires java.naming;
    requires lombok;

    opens ru.solomka.graphic.event.device to javafx.fxml;
    exports ru.solomka.graphic.event.device;

    opens ru.solomka.graphic.scene.item.tag.enums to javafx.fxml;
    exports ru.solomka.graphic.scene.item.tag.enums;

    opens ru.solomka.graphic.event.impl to javafx.fxml;
    exports ru.solomka.graphic.event.impl;

    opens ru.solomka.graphic.scene to javafx.fxml;
    exports ru.solomka.graphic.scene;

    opens ru.solomka.graphic.scene.item to javafx.fxml;
    exports ru.solomka.graphic.scene.item;

    opens ru.solomka.graphic.scene.item.impl to javafx.fxml;
    exports ru.solomka.graphic.scene.item.impl;

    opens ru.solomka.graphic.scene.item.impl.base to javafx.fxml;
    exports ru.solomka.graphic.scene.item.impl.base;

    opens ru.solomka.graphic.scene.item.impl.button to javafx.fxml;
    exports ru.solomka.graphic.scene.item.impl.button;

    opens ru.solomka.graphic.scene.item.impl.shape to javafx.fxml;
    exports ru.solomka.graphic.scene.item.impl.shape;

    opens ru.solomka.graphic.scene.item.impl.text to javafx.fxml;
    exports ru.solomka.graphic.scene.item.impl.text;

    opens ru.solomka.graphic.scene.item.tag to javafx.fxml;
    exports ru.solomka.graphic.scene.item.tag;

    opens ru.solomka.graphic.style to javafx.fxml;
    exports ru.solomka.graphic.style;

    opens ru.solomka.graphic.tool to javafx.fxml;
    exports ru.solomka.graphic.tool;

    opens ru.solomka.graphic to javafx.fxml;
    exports ru.solomka.graphic;
}