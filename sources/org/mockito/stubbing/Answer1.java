package org.mockito.stubbing;

import org.mockito.Incubating;

@Incubating
public interface Answer1<T, A0> {
    T answer(A0 a0) throws Throwable;
}
