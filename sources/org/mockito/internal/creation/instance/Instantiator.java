package org.mockito.internal.creation.instance;

@Deprecated
public interface Instantiator {
    <T> T newInstance(Class<T> cls) throws InstantiationException;
}