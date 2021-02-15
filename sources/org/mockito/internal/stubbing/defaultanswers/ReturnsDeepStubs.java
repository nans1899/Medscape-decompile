package org.mockito.internal.stubbing.defaultanswers;

import java.io.IOException;
import java.io.Serializable;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.internal.MockitoCore;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.stubbing.StubbedInvocationMatcher;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.mock.MockCreationSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubbing;

public class ReturnsDeepStubs implements Answer<Object>, Serializable {
    private static final long serialVersionUID = -7105341425736035847L;

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        GenericMetadataSupport resolveGenericReturnType = actualParameterizedType(invocationOnMock.getMock()).resolveGenericReturnType(invocationOnMock.getMethod());
        Class<?> rawType = resolveGenericReturnType.rawType();
        if (!mockitoCore().isTypeMockable(rawType)) {
            return delegate().returnValueFor(rawType);
        }
        if (!rawType.equals(Object.class) || resolveGenericReturnType.hasRawExtraInterfaces()) {
            return deepStub(invocationOnMock, resolveGenericReturnType);
        }
        return null;
    }

    private Object deepStub(InvocationOnMock invocationOnMock, GenericMetadataSupport genericMetadataSupport) throws Throwable {
        InvocationContainerImpl invocationContainer = MockUtil.getInvocationContainer(invocationOnMock.getMock());
        for (Stubbing next : invocationContainer.getStubbingsDescending()) {
            if (invocationContainer.getInvocationForStubbing().matches(next.getInvocation())) {
                return next.answer(invocationOnMock);
            }
        }
        StubbedInvocationMatcher recordDeepStubAnswer = recordDeepStubAnswer(newDeepStubMock(genericMetadataSupport, invocationOnMock.getMock()), invocationContainer);
        recordDeepStubAnswer.markStubUsed(recordDeepStubAnswer.getInvocation());
        return recordDeepStubAnswer.answer(invocationOnMock);
    }

    private Object newDeepStubMock(GenericMetadataSupport genericMetadataSupport, Object obj) {
        return mockitoCore().mock(genericMetadataSupport.rawType(), withSettingsUsing(genericMetadataSupport, MockUtil.getMockSettings(obj)));
    }

    private MockSettings withSettingsUsing(GenericMetadataSupport genericMetadataSupport, MockCreationSettings mockCreationSettings) {
        MockSettings mockSettings;
        if (genericMetadataSupport.hasRawExtraInterfaces()) {
            mockSettings = Mockito.withSettings().extraInterfaces(genericMetadataSupport.rawExtraInterfaces());
        } else {
            mockSettings = Mockito.withSettings();
        }
        return propagateSerializationSettings(mockSettings, mockCreationSettings).defaultAnswer(returnsDeepStubsAnswerUsing(genericMetadataSupport));
    }

    private MockSettings propagateSerializationSettings(MockSettings mockSettings, MockCreationSettings mockCreationSettings) {
        return mockSettings.serializable(mockCreationSettings.getSerializableMode());
    }

    private ReturnsDeepStubs returnsDeepStubsAnswerUsing(GenericMetadataSupport genericMetadataSupport) {
        return new ReturnsDeepStubsSerializationFallback(genericMetadataSupport);
    }

    private StubbedInvocationMatcher recordDeepStubAnswer(Object obj, InvocationContainerImpl invocationContainerImpl) {
        return invocationContainerImpl.addAnswer(new DeeplyStubbedAnswer(obj), false, (Strictness) null);
    }

    /* access modifiers changed from: protected */
    public GenericMetadataSupport actualParameterizedType(Object obj) {
        return GenericMetadataSupport.inferFrom(((CreationSettings) MockUtil.getMockHandler(obj).getMockSettings()).getTypeToMock());
    }

    private static class ReturnsDeepStubsSerializationFallback extends ReturnsDeepStubs implements Serializable {
        private final GenericMetadataSupport returnTypeGenericMetadata;

        public ReturnsDeepStubsSerializationFallback(GenericMetadataSupport genericMetadataSupport) {
            this.returnTypeGenericMetadata = genericMetadataSupport;
        }

        /* access modifiers changed from: protected */
        public GenericMetadataSupport actualParameterizedType(Object obj) {
            return this.returnTypeGenericMetadata;
        }

        private Object writeReplace() throws IOException {
            return Mockito.RETURNS_DEEP_STUBS;
        }
    }

    private static class DeeplyStubbedAnswer implements Answer<Object>, Serializable {
        private final Object mock;

        DeeplyStubbedAnswer(Object obj) {
            this.mock = obj;
        }

        public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
            return this.mock;
        }
    }

    private static MockitoCore mockitoCore() {
        return LazyHolder.MOCKITO_CORE;
    }

    private static ReturnsEmptyValues delegate() {
        return LazyHolder.DELEGATE;
    }

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final ReturnsEmptyValues DELEGATE = new ReturnsEmptyValues();
        /* access modifiers changed from: private */
        public static final MockitoCore MOCKITO_CORE = new MockitoCore();

        private LazyHolder() {
        }
    }
}
