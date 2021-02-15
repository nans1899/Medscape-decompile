package com.appboy.events;

public interface IEventSubscriber<T> {
    void trigger(T t);
}
