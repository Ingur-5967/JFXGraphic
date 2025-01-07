package ru.solomka.graphic.scene.item.impl.base;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.tag.Container;
import ru.solomka.graphic.scene.item.tag.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasePane extends BaseItem<AnchorPane> implements Container, Root {

    private final AnchorPane parent;

    public BasePane(double width, double height) {
        super(() -> {
            AnchorPane initial = new AnchorPane();
            initial.setPrefSize(width, height);
            return initial;
        });

        this.parent = this.getNode();
    }

    /**
     * Resize root element by the specified element
     *
     * @param element target element for which there will be a resizing
     */
    public void setAdaptiveSize(SceneItem element) {
        if (element.getSize().getWidth() <= 0 || element.getSize().getHeight() <= 0)
            throw new IllegalArgumentException("Size cannot be zero or negative.");

        AnchorPane pane = this.getNode();

        pane.setPrefSize(element.getSize().getWidth(), element.getSize().getHeight());
    }

    @Override
    public void setRootElement(Container parent, double x, double y) {
        parent.addChildren(this);
        this.setLocation(x, y);
    }

    @Override
    public void addChildren(Node item) {
        this.parent.getChildren().add(item);
    }

    @Override
    public void addChildren(SceneItem item) {
        this.addChildren((Node) item.getNode());
    }

    @Override
    public void removeChildren(Node item) {
        this.parent.getChildren().remove(item);
    }

    @Override
    public List<Node> getChildren() {
        return new ArrayList<>(this.parent.getChildren());
    }

    @Override
    public List<SceneItem> getSource() {
        return this.getChildren().stream().map(SceneItem::fromSource).collect(Collectors.toList());
    }
}