package org.mockito.stubbing;

import org.mockito.NotExtensible;

@NotExtensible
public interface LenientStubber extends BaseStubber {
    <T> OngoingStubbing<T> when(T t);
}
