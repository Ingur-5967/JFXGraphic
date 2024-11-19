package ru.solomka.graphic.scene.item.impl.text;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import ru.solomka.graphic.scene.item.impl.base.BaseText;
import ru.solomka.graphic.scene.item.tag.Interact;

import java.awt.*;
import java.net.URI;
import java.util.function.Consumer;


@Getter
public class HyperLabel extends BaseText implements Interact {

    private final String url;

    public HyperLabel(String content, int font, String url) {
        super(content, font, 1);
        this.getRoot().getChildren().add(this.getLineObject(0));
        this.url = url;

        this.setup(MouseEvent.MOUSE_CLICKED, _ -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(this.url));
                } catch (Exception _) {
                }
            }
        });
    }

    @Override
    public <E extends Event> void setup(EventType<E> eventType, Consumer<E> event) {
        this.getLineObject(0).addEventHandler(eventType, event::accept);
    }
}