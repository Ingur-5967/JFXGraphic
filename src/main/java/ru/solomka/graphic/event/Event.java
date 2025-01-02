package ru.solomka.graphic.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class Event {
    public static <C extends Listener, A> void call(Class<C> clazz, A eventInstance) {
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(EventHandler.class))
                .sorted(Comparator.comparing(annotation -> annotation.getAnnotation(EventHandler.class).priority().getOrder()))
                .toList().reversed();

        Object clazzObject;
        try {
            clazzObject = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        boolean cancelled = false;
        if (clazzObject instanceof Cancellable)
            cancelled = ((Cancellable) clazzObject).isCancelled();

        if (cancelled) return;

        methods.forEach(method -> {
            try {
                method.invoke(clazzObject, eventInstance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}