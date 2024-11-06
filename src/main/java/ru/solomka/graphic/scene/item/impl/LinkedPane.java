package ru.solomka.graphic.scene.item.impl;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.LazyComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.impl.base.BasePane;
import ru.solomka.graphic.scene.item.tag.Linked;
import ru.solomka.graphic.tool.OperationSupplier;
import ru.solomka.graphic.tool.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedPane extends BasePane implements LazyComponent<LinkedPane, AnchorPane>, Linked {

    private final List<SceneItem<?>> source;

    public LinkedPane(int width, int height, String id) {
        super(width, height, id);
        this.source = new ArrayList<>();
    }

    @Override
    public LinkedPane preInit(OperationSupplier<Pair<AnchorPane, List<SceneItem<?>>>> edit, SceneItem<?>... entries) {
        List<Node> remap = Arrays.stream(Arrays.stream(entries).map(SceneItem::getElement).toArray(Node[]::new)).toList();

        AnchorPane parent = this.getElement();

        source.addAll(List.of(entries));

        parent.getChildren().addAll(
                (edit != null ? edit.operate(new Pair<>(parent, Arrays.stream(entries).toList())).getSecond().stream().map(SceneItem::getElement).toList() : remap)
        );

        return this;
    }

    @Override
    public void addChildren(SceneItem<?> item) {
        this.source.add(item);
        this.getElement().getChildren().add(item.getElement());
    }

    @Override
    public LinkedPane preInit(SceneItem<?>... entries) {
        return this.preInit(null, entries);
    }

    @Override
    public List<SceneItem<?>> getSource() {
        return this.source;
    }

    @Override
    public SceneItem<?> get(String id) {
        return this.source.stream().filter(node -> node.getElement().getId() != null && node.getElement().getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public SceneItem<?> get(int position) {
        return this.source.get(position);
    }
}