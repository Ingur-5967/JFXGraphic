package ru.solomka.graphic.scene.item.impl.base;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import ru.solomka.graphic.scene.item.ItemSize;
import ru.solomka.graphic.scene.item.impl.LinkedPane;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Interact;
import ru.solomka.graphic.scene.item.tag.Root;

import java.util.function.Consumer;

public class BaseButton extends BaseItem<Button> implements Interact, Root {

    public BaseButton(ItemSize size, String content, int font) {
        super(() -> {
            if (font <= 0)
                throw new IllegalArgumentException("Font size must be greater than 0");

            Button initialButton = new Button(content);
            initialButton.setText(content);
            initialButton.setFont(Font.font(font));
            initialButton.setCursor(Cursor.HAND);
            initialButton.setStyle("-fx-background-color: transparent; -fx-border-color: #cccccc");
            return initialButton;
        }, () -> new LinkedPane(0, 0));

        this.setSize(size.getWidth(), size.getHeight());
    }

    @Override
    public <E extends Event> void setup(EventType<E> eventType, Consumer<E> event) {
        this.getNode().addEventHandler(eventType, event::accept);
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        parent.addChildren(((BasePane) this.getRoot()));
        this.setLocation(x, y);
    }
}