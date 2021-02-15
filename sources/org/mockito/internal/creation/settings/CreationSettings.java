package org.mockito.internal.creation.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.StubbingLookupListener;
import org.mockito.listeners.VerificationStartedListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.mock.MockName;
import org.mockito.mock.SerializableMode;
import org.mockito.stubbing.Answer;

public class CreationSettings<T> implements MockCreationSettings<T>, Serializable {
    private static final long serialVersionUID = -6789800638070123629L;
    private Object[] constructorArgs;
    protected Answer<Object> defaultAnswer;
    protected Set<Class<?>> extraInterfaces = new LinkedHashSet();
    protected List<InvocationListener> invocationListeners = new ArrayList();
    protected boolean lenient;
    protected MockName mockName;
    protected String name;
    private Object outerClassInstance;
    protected SerializableMode serializableMode = SerializableMode.NONE;
    protected Object spiedInstance;
    protected boolean stripAnnotations;
    protected boolean stubOnly;
    protected List<StubbingLookupListener> stubbingLookupListeners = new CopyOnWriteArrayList();
    protected Class<T> typeToMock;
    private boolean useConstructor;
    protected List<VerificationStartedListener> verificationStartedListeners = new LinkedList();

    public CreationSettings() {
    }

    public CreationSettings(CreationSettings creationSettings) {
        this.typeToMock = creationSettings.typeToMock;
        this.extraInterfaces = creationSettings.extraInterfaces;
        this.name = creationSettings.name;
        this.spiedInstance = creationSettings.spiedInstance;
        this.defaultAnswer = creationSettings.defaultAnswer;
        this.mockName = creationSettings.mockName;
        this.serializableMode = creationSettings.serializableMode;
        this.invocationListeners = creationSettings.invocationListeners;
        this.stubbingLookupListeners = creationSettings.stubbingLookupListeners;
        this.verificationStartedListeners = creationSettings.verificationStartedListeners;
        this.stubOnly = creationSettings.stubOnly;
        this.useConstructor = creationSettings.isUsingConstructor();
        this.outerClassInstance = creationSettings.getOuterClassInstance();
        this.constructorArgs = creationSettings.getConstructorArgs();
        this.lenient = creationSettings.lenient;
        this.stripAnnotations = creationSettings.stripAnnotations;
    }

    public Class<T> getTypeToMock() {
        return this.typeToMock;
    }

    public CreationSettings<T> setTypeToMock(Class<T> cls) {
        this.typeToMock = cls;
        return this;
    }

    public Set<Class<?>> getExtraInterfaces() {
        return this.extraInterfaces;
    }

    public CreationSettings<T> setExtraInterfaces(Set<Class<?>> set) {
        this.extraInterfaces = set;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Object getSpiedInstance() {
        return this.spiedInstance;
    }

    public Answer<Object> getDefaultAnswer() {
        return this.defaultAnswer;
    }

    public MockName getMockName() {
        return this.mockName;
    }

    public CreationSettings<T> setMockName(MockName mockName2) {
        this.mockName = mockName2;
        return this;
    }

    public boolean isSerializable() {
        return this.serializableMode != SerializableMode.NONE;
    }

    public CreationSettings<T> setSerializableMode(SerializableMode serializableMode2) {
        this.serializableMode = serializableMode2;
        return this;
    }

    public SerializableMode getSerializableMode() {
        return this.serializableMode;
    }

    public List<InvocationListener> getInvocationListeners() {
        return this.invocationListeners;
    }

    public List<VerificationStartedListener> getVerificationStartedListeners() {
        return this.verificationStartedListeners;
    }

    public List<StubbingLookupListener> getStubbingLookupListeners() {
        return this.stubbingLookupListeners;
    }

    public boolean isUsingConstructor() {
        return this.useConstructor;
    }

    public boolean isStripAnnotations() {
        return this.stripAnnotations;
    }

    public Object[] getConstructorArgs() {
        return this.constructorArgs;
    }

    public Object getOuterClassInstance() {
        return this.outerClassInstance;
    }

    public boolean isStubOnly() {
        return this.stubOnly;
    }

    public boolean isLenient() {
        return this.lenient;
    }
}
