package ru.solomka.graphic.event;


import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;

public class EventProvider {

    private static final LinkedList<Event> EVENTS = new LinkedList<>();

    public static void register(Event event) {
        EVENTS.add(event);
    }

    public static void unregister(Event event) {
        EVENTS.remove(event);
    }

    public static void call(Event event, Priority... priorities) throws Exception {
        for (Event registeredEvent : EVENTS) {

            if (!EVENTS.contains(event))
                throw new Exception("Event is not registered");

            if (registeredEvent != event) continue;

            Class<? extends Event> instance = registeredEvent.getClass();

            Arrays.stream(instance.getDeclaredMethods()).forEach(method -> {
                if (method.getAnnotation(EventHandler.class) == null) return;

                EventHandler annotation = method.getAnnotation(EventHandler.class);

                if (!Arrays.stream(priorities).toList().contains(annotation.priority())) return;

                try {
                    method.invoke(method.getClass().newInstance(), event);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}