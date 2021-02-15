package org.mockito.stubbing;

import org.mockito.Incubating;

@Incubating
public interface Answer2<T, A0, A1> {
    T answer(A0 a0, A1 a1) throws Throwable;
}
