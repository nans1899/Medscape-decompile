package org.mockito.internal.listeners;

import java.util.Collection;
import java.util.List;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.invocation.Invocation;
import org.mockito.listeners.StubbingLookupEvent;
import org.mockito.listeners.StubbingLookupListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.Stubbing;

public class StubbingLookupNotifier {
    public static void notifyStubbedAnswerLookup(Invocation invocation, Stubbing stubbing, Collection<Stubbing> collection, CreationSettings creationSettings) {
        List<StubbingLookupListener> stubbingLookupListeners = creationSettings.getStubbingLookupListeners();
        if (!stubbingLookupListeners.isEmpty()) {
            Event event = new Event(invocation, stubbing, collection, creationSettings);
            for (StubbingLookupListener onStubbingLookup : stubbingLookupListeners) {
                onStubbingLookup.onStubbingLookup(event);
            }
        }
    }

    static class Event implements StubbingLookupEvent {
        private final Collection<Stubbing> allStubbings;
        private final Invocation invocation;
        private final MockCreationSettings mockSettings;
        private final Stubbing stubbing;

        public Event(Invocation invocation2, Stubbing stubbing2, Collection<Stubbing> collection, MockCreationSettings mockCreationSettings) {
            this.invocation = invocation2;
            this.stubbing = stubbing2;
            this.allStubbings = collection;
            this.mockSettings = mockCreationSettings;
        }

        public Invocation getInvocation() {
            return this.invocation;
        }

        public Stubbing getStubbingFound() {
            return this.stubbing;
        }

        public Collection<Stubbing> getAllStubbings() {
            return this.allStubbings;
        }

        public MockCreationSettings getMockSettings() {
            return this.mockSettings;
        }
    }
}
