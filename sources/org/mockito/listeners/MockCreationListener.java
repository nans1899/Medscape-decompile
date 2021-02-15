package org.mockito.listeners;

import org.mockito.mock.MockCreationSettings;

public interface MockCreationListener extends MockitoListener {
    void onMockCreated(Object obj, MockCreationSettings mockCreationSettings);
}
