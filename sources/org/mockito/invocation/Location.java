package org.mockito.invocation;

import org.mockito.NotExtensible;

@NotExtensible
public interface Location {
    String getSourceFile();

    String toString();
}
