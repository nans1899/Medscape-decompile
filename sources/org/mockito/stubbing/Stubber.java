package org.mockito.stubbing;

import org.mockito.NotExtensible;

@NotExtensible
public interface Stubber extends BaseStubber {
    <T> T when(T t);
}
