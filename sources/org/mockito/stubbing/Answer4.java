package org.mockito.stubbing;

import org.mockito.Incubating;

@Incubating
public interface Answer4<T, A0, A1, A2, A3> {
    T answer(A0 a0, A1 a1, A2 a2, A3 a3) throws Throwable;
}
