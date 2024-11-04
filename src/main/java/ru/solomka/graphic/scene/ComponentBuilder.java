package ru.solomka.graphic.scene;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.solomka.graphic.scene.item.SceneItem;
import ru.solomka.graphic.style.CssStyle;
import ru.solomka.graphic.style.Padding;


public class ComponentBuilder<T extends Node> {

    private final T source;

    private ComponentBuilder(T source) {
        this.source = source;
    }

    public static ImageWrapper wrapper(Image image) {
        return new ImageWrapper(image);
    }

    public static <S extends Node> ComponentBuilder<S> of(S source) {
        return new ComponentBuilder<>(source);
    }

    public ComponentBuilder<T> css(CssStyle... css) {
        source.setStyle(CssStyle.getCssString(css));
        return this;
    }

    public ComponentBuilder<T> image(String url) {
        if (!(source instanceof ImageView))
            throw new IllegalArgumentException("Component must have a image");

        ((ImageView) source).setImage(new Image(url));

        return this;
    }

    public ComponentBuilder<T> text(String content) {
        if (!(source instanceof Label))
            throw new IllegalArgumentException("Component must have a label");

        ((Label) source).setText(content);

        return this;
    }

    public ComponentBuilder<T> padding(Padding padding) {
        this.source.setLayoutX(this.source.getLayoutX() + padding.getLeft() + padding.getRight());
        this.source.setLayoutY(this.source.getLayoutY() + padding.getTop() + padding.getBottom());
        return this;
    }

    public ImageWrapper wrapper() {
        if (!(source instanceof ImageView))
            throw new IllegalArgumentException("Component must have a image");

        return new ImageWrapper(((ImageView) source).getImage());
    }

    public SceneItem<T> create() {
        return SceneItem.fromSource(source);
    }

    public static class ImageWrapper {

        private final ImageView view;

        public ImageWrapper(Image view) {
            this.view = new ImageView(view);
        }

        public ImageWrapper size(int width, int height) {
            view.setFitWidth(width);
            view.setFitHeight(height);
            return this;
        }

        public ImageWrapper clip(int width, int height) {
            Rectangle clip = new Rectangle(view.getFitWidth(), view.getFitHeight());

            clip.setArcWidth(width);
            clip.setArcHeight(height);

            view.setClip(clip);

            return this;
        }

        public ImageWrapper snapshot() {
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = view.snapshot(parameters, null);

            view.setEffect(new DropShadow(5, Color.BLACK));
            view.setImage(image);

            return this;

        }

        public SceneItem<ImageView> get() {
            return SceneItem.fromSource(this.view);
        }
    }
}