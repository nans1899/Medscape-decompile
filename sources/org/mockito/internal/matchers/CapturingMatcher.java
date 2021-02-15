package org.mockito.internal.matchers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.exceptions.Reporter;

public class CapturingMatcher<T> implements ArgumentMatcher<T>, CapturesArguments, VarargMatcher, Serializable {
    private final List<Object> arguments = new ArrayList();
    private final ReadWriteLock lock;
    private final Lock readLock;
    private final Lock writeLock;

    public boolean matches(Object obj) {
        return true;
    }

    public String toString() {
        return "<Capturing argument>";
    }

    public CapturingMatcher() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.lock = reentrantReadWriteLock;
        this.readLock = reentrantReadWriteLock.readLock();
        this.writeLock = this.lock.writeLock();
    }

    public T getLastValue() {
        this.readLock.lock();
        try {
            if (!this.arguments.isEmpty()) {
                return this.arguments.get(this.arguments.size() - 1);
            }
            throw Reporter.noArgumentValueWasCaptured();
        } finally {
            this.readLock.unlock();
        }
    }

    public List<T> getAllValues() {
        this.readLock.lock();
        try {
            return new ArrayList(this.arguments);
        } finally {
            this.readLock.unlock();
        }
    }

    public void captureFrom(Object obj) {
        this.writeLock.lock();
        try {
            this.arguments.add(obj);
        } finally {
            this.writeLock.unlock();
        }
    }
}
