package org.jui.util.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Handlers<T extends Event> {
    private final List<Consumer<T>> handlers;

    public Handlers() {
        handlers = new ArrayList<>();
    }

    public void add(Consumer<T> apply) {
        handlers.add(apply);
    }
    public void invokeAll(T event) {
        handlers.forEach(c -> c.accept(event));
    }
}
