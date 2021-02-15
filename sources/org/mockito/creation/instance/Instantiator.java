package org.mockito.creation.instance;

public interface Instantiator {
    <T> T newInstance(Class<T> cls) throws InstantiationException;
}
