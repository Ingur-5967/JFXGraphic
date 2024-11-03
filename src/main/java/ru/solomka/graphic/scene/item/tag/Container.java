package ru.solomka.graphic.scene.item.tag;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import ru.solomka.graphic.scene.item.LazyComponent;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.scene.item.SizeProperties;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Container {

    @SuppressWarnings("unchecked")
    static <I extends Container> Container fromSource(Class<I> instance, Parent region, Object[] properties) {
        I loader;
        try {
            loader = (I) instance.getDeclaredConstructors()[0].newInstance(properties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        List<SceneItem<?>> items = region.getChildrenUnmodifiable().stream().filter(c -> c instanceof AnchorPane).map(SceneItem::fromSource).collect(Collectors.toList());

        return items.isEmpty() ? loader : ((LazyComponent<? extends Container, ? extends Node>) loader).preInit(items.toArray(SceneItem[]::new));
    }

    void addChildren(Node item);

    List<Node> getChildren();

    SizeProperties getBounds();

    default void addChildren(SceneItem<?> item) {
    }

    default List<SceneItem<?>> getSource() {
        return Collections.emptyList();
    }
}