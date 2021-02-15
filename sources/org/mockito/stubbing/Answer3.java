package org.mockito.stubbing;

import org.mockito.Incubating;

@Incubating
public interface Answer3<T, A0, A1, A2> {
    T answer(A0 a0, A1 a1, A2 a2) throws Throwable;
}
