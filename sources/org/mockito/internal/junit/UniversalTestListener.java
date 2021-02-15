package org.mockito.internal.junit;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.listeners.AutoCleanableListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockitoLogger;
import org.mockito.quality.Strictness;

public class UniversalTestListener implements MockitoTestListener, AutoCleanableListener {
    private Strictness currentStrictness;
    private boolean listenerDirty;
    private final MockitoLogger logger;
    private Map<Object, MockCreationSettings> mocks = new IdentityHashMap();
    private DefaultStubbingLookupListener stubbingLookupListener;

    public UniversalTestListener(Strictness strictness, MockitoLogger mockitoLogger) {
        this.currentStrictness = strictness;
        this.logger = mockitoLogger;
        this.stubbingLookupListener = new DefaultStubbingLookupListener(strictness);
    }

    public void testFinished(TestFinishedEvent testFinishedEvent) {
        Set<Object> keySet = this.mocks.keySet();
        this.mocks = new IdentityHashMap();
        int i = AnonymousClass1.$SwitchMap$org$mockito$quality$Strictness[this.currentStrictness.ordinal()];
        if (i == 1) {
            emitWarnings(this.logger, testFinishedEvent, keySet);
        } else if (i == 2) {
            reportUnusedStubs(testFinishedEvent, keySet);
        } else if (i != 3) {
            throw new IllegalStateException("Unknown strictness: " + this.currentStrictness);
        }
    }

    /* renamed from: org.mockito.internal.junit.UniversalTestListener$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$mockito$quality$Strictness;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.mockito.quality.Strictness[] r0 = org.mockito.quality.Strictness.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$mockito$quality$Strictness = r0
                org.mockito.quality.Strictness r1 = org.mockito.quality.Strictness.WARN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$mockito$quality$Strictness     // Catch:{ NoSuchFieldError -> 0x001d }
                org.mockito.quality.Strictness r1 = org.mockito.quality.Strictness.STRICT_STUBS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$mockito$quality$Strictness     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.mockito.quality.Strictness r1 = org.mockito.quality.Strictness.LENIENT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.junit.UniversalTestListener.AnonymousClass1.<clinit>():void");
        }
    }

    private void reportUnusedStubs(TestFinishedEvent testFinishedEvent, Collection<Object> collection) {
        if (testFinishedEvent.getFailure() == null && !this.stubbingLookupListener.isMismatchesReported()) {
            new UnusedStubbingsFinder().getUnusedStubbings(collection).reportUnused();
        }
    }

    private static void emitWarnings(MockitoLogger mockitoLogger, TestFinishedEvent testFinishedEvent, Collection<Object> collection) {
        if (testFinishedEvent.getFailure() != null) {
            new ArgMismatchFinder().getStubbingArgMismatches(collection).format(testFinishedEvent.getTestName(), mockitoLogger);
        } else {
            new UnusedStubbingsFinder().getUnusedStubbings(collection).format(testFinishedEvent.getTestName(), mockitoLogger);
        }
    }

    public void onMockCreated(Object obj, MockCreationSettings mockCreationSettings) {
        this.mocks.put(obj, mockCreationSettings);
        ((CreationSettings) mockCreationSettings).getStubbingLookupListeners().add(this.stubbingLookupListener);
    }

    public void setStrictness(Strictness strictness) {
        this.currentStrictness = strictness;
        this.stubbingLookupListener.setCurrentStrictness(strictness);
    }

    public boolean isListenerDirty() {
        return this.listenerDirty;
    }

    public void setListenerDirty() {
        this.listenerDirty = true;
    }
}
