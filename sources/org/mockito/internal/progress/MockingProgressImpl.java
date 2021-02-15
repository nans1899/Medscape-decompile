package org.mockito.internal.progress;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import org.mockito.internal.configuration.GlobalConfiguration;
import org.mockito.internal.debugging.Localized;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.listeners.AutoCleanableListener;
import org.mockito.invocation.Location;
import org.mockito.listeners.MockCreationListener;
import org.mockito.listeners.MockitoListener;
import org.mockito.listeners.VerificationListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationStrategy;

public class MockingProgressImpl implements MockingProgress {
    private final ArgumentMatcherStorage argumentMatcherStorage = new ArgumentMatcherStorageImpl();
    private final Set<MockitoListener> listeners = new LinkedHashSet();
    private OngoingStubbing<?> ongoingStubbing;
    private Location stubbingInProgress = null;
    private Localized<VerificationMode> verificationMode;
    private VerificationStrategy verificationStrategy = getDefaultVerificationStrategy();

    public static VerificationStrategy getDefaultVerificationStrategy() {
        return new VerificationStrategy() {
            public VerificationMode maybeVerifyLazily(VerificationMode verificationMode) {
                return verificationMode;
            }
        };
    }

    public void reportOngoingStubbing(OngoingStubbing ongoingStubbing2) {
        this.ongoingStubbing = ongoingStubbing2;
    }

    public OngoingStubbing<?> pullOngoingStubbing() {
        OngoingStubbing<?> ongoingStubbing2 = this.ongoingStubbing;
        this.ongoingStubbing = null;
        return ongoingStubbing2;
    }

    public Set<VerificationListener> verificationListeners() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (MockitoListener next : this.listeners) {
            if (next instanceof VerificationListener) {
                linkedHashSet.add((VerificationListener) next);
            }
        }
        return linkedHashSet;
    }

    public void verificationStarted(VerificationMode verificationMode2) {
        validateState();
        resetOngoingStubbing();
        this.verificationMode = new Localized<>(verificationMode2);
    }

    public void resetOngoingStubbing() {
        this.ongoingStubbing = null;
    }

    public VerificationMode pullVerificationMode() {
        Localized<VerificationMode> localized = this.verificationMode;
        if (localized == null) {
            return null;
        }
        VerificationMode object = localized.getObject();
        this.verificationMode = null;
        return object;
    }

    public void stubbingStarted() {
        validateState();
        this.stubbingInProgress = new LocationImpl();
    }

    public void validateState() {
        validateMostStuff();
        Location location = this.stubbingInProgress;
        if (location != null) {
            this.stubbingInProgress = null;
            throw Reporter.unfinishedStubbing(location);
        }
    }

    private void validateMostStuff() {
        GlobalConfiguration.validate();
        Localized<VerificationMode> localized = this.verificationMode;
        if (localized == null) {
            getArgumentMatcherStorage().validateState();
            return;
        }
        Location location = localized.getLocation();
        this.verificationMode = null;
        throw Reporter.unfinishedVerificationException(location);
    }

    public void stubbingCompleted() {
        this.stubbingInProgress = null;
    }

    public String toString() {
        return "ongoingStubbing: " + this.ongoingStubbing + ", verificationMode: " + this.verificationMode + ", stubbingInProgress: " + this.stubbingInProgress;
    }

    public void reset() {
        this.stubbingInProgress = null;
        this.verificationMode = null;
        getArgumentMatcherStorage().reset();
    }

    public ArgumentMatcherStorage getArgumentMatcherStorage() {
        return this.argumentMatcherStorage;
    }

    public void mockingStarted(Object obj, MockCreationSettings mockCreationSettings) {
        for (MockitoListener next : this.listeners) {
            if (next instanceof MockCreationListener) {
                ((MockCreationListener) next).onMockCreated(obj, mockCreationSettings);
            }
        }
        validateMostStuff();
    }

    public void addListener(MockitoListener mockitoListener) {
        addListener(mockitoListener, this.listeners);
    }

    static void addListener(MockitoListener mockitoListener, Set<MockitoListener> set) {
        LinkedList<MockitoListener> linkedList = new LinkedList<>();
        for (MockitoListener next : set) {
            if (next.getClass().equals(mockitoListener.getClass())) {
                if (!(next instanceof AutoCleanableListener) || !((AutoCleanableListener) next).isListenerDirty()) {
                    Reporter.redundantMockitoListener(mockitoListener.getClass().getSimpleName());
                } else {
                    linkedList.add(next);
                }
            }
        }
        for (MockitoListener remove : linkedList) {
            set.remove(remove);
        }
        set.add(mockitoListener);
    }

    public void removeListener(MockitoListener mockitoListener) {
        this.listeners.remove(mockitoListener);
    }

    public void setVerificationStrategy(VerificationStrategy verificationStrategy2) {
        this.verificationStrategy = verificationStrategy2;
    }

    public VerificationMode maybeVerifyLazily(VerificationMode verificationMode2) {
        return this.verificationStrategy.maybeVerifyLazily(verificationMode2);
    }

    public void clearListeners() {
        this.listeners.clear();
    }
}
