package org.mockito.internal.debugging;

import org.mockito.invocation.Location;

public class Localized<T> {
    private final Location location = new LocationImpl();
    private final T object;

    public Localized(T t) {
        this.object = t;
    }

    public T getObject() {
        return this.object;
    }

    public Location getLocation() {
        return this.location;
    }
}
