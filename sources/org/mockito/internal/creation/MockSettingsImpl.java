package org.mockito.internal.creation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mockito.MockSettings;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.debugging.VerboseMockInvocationLogger;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.Checks;
import org.mockito.internal.util.MockCreationValidator;
import org.mockito.internal.util.MockNameImpl;
import org.mockito.internal.util.collections.Sets;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.StubbingLookupListener;
import org.mockito.listeners.VerificationStartedListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.mock.MockName;
import org.mockito.mock.SerializableMode;
import org.mockito.stubbing.Answer;

public class MockSettingsImpl<T> extends CreationSettings<T> implements MockSettings, MockCreationSettings<T> {
    private static final long serialVersionUID = 4475297236197939569L;
    private Object[] constructorArgs;
    private Object outerClassInstance;
    private boolean useConstructor;

    public MockSettings serializable() {
        return serializable(SerializableMode.BASIC);
    }

    public MockSettings serializable(SerializableMode serializableMode) {
        this.serializableMode = serializableMode;
        return this;
    }

    public MockSettings extraInterfaces(Class<?>... clsArr) {
        if (clsArr == null || clsArr.length == 0) {
            throw Reporter.extraInterfacesRequiresAtLeastOneInterface();
        }
        int length = clsArr.length;
        int i = 0;
        while (i < length) {
            Class<?> cls = clsArr[i];
            if (cls == null) {
                throw Reporter.extraInterfacesDoesNotAcceptNullParameters();
            } else if (cls.isInterface()) {
                i++;
            } else {
                throw Reporter.extraInterfacesAcceptsOnlyInterfaces(cls);
            }
        }
        this.extraInterfaces = Sets.newSet(clsArr);
        return this;
    }

    public MockName getMockName() {
        return this.mockName;
    }

    public Set<Class<?>> getExtraInterfaces() {
        return this.extraInterfaces;
    }

    public Object getSpiedInstance() {
        return this.spiedInstance;
    }

    public MockSettings name(String str) {
        this.name = str;
        return this;
    }

    public MockSettings spiedInstance(Object obj) {
        this.spiedInstance = obj;
        return this;
    }

    public MockSettings defaultAnswer(Answer answer) {
        this.defaultAnswer = answer;
        if (answer != null) {
            return this;
        }
        throw Reporter.defaultAnswerDoesNotAcceptNullParameter();
    }

    public Answer<Object> getDefaultAnswer() {
        return this.defaultAnswer;
    }

    public MockSettingsImpl<T> stubOnly() {
        this.stubOnly = true;
        return this;
    }

    public MockSettings useConstructor(Object... objArr) {
        Checks.checkNotNull(objArr, "constructorArgs", "If you need to pass null, please cast it to the right type, e.g.: useConstructor((String) null)");
        this.useConstructor = true;
        this.constructorArgs = objArr;
        return this;
    }

    public MockSettings outerInstance(Object obj) {
        this.outerClassInstance = obj;
        return this;
    }

    public MockSettings withoutAnnotations() {
        this.stripAnnotations = true;
        return this;
    }

    public boolean isUsingConstructor() {
        return this.useConstructor;
    }

    public Object getOuterClassInstance() {
        return this.outerClassInstance;
    }

    public Object[] getConstructorArgs() {
        if (this.outerClassInstance == null) {
            return this.constructorArgs;
        }
        ArrayList arrayList = new ArrayList(this.constructorArgs.length + 1);
        arrayList.add(this.outerClassInstance);
        arrayList.addAll(Arrays.asList(this.constructorArgs));
        return arrayList.toArray(new Object[(this.constructorArgs.length + 1)]);
    }

    public boolean isStubOnly() {
        return this.stubOnly;
    }

    public MockSettings verboseLogging() {
        if (!invocationListenersContainsType(VerboseMockInvocationLogger.class)) {
            invocationListeners(new VerboseMockInvocationLogger());
        }
        return this;
    }

    public MockSettings invocationListeners(InvocationListener... invocationListenerArr) {
        addListeners(invocationListenerArr, this.invocationListeners, "invocationListeners");
        return this;
    }

    public MockSettings stubbingLookupListeners(StubbingLookupListener... stubbingLookupListenerArr) {
        addListeners(stubbingLookupListenerArr, this.stubbingLookupListeners, "stubbingLookupListeners");
        return this;
    }

    static <T> void addListeners(T[] tArr, List<T> list, String str) {
        if (tArr == null) {
            throw Reporter.methodDoesNotAcceptParameter(str, "null vararg array.");
        } else if (tArr.length != 0) {
            int length = tArr.length;
            int i = 0;
            while (i < length) {
                T t = tArr[i];
                if (t != null) {
                    list.add(t);
                    i++;
                } else {
                    throw Reporter.methodDoesNotAcceptParameter(str, "null listeners.");
                }
            }
        } else {
            throw Reporter.requiresAtLeastOneListener(str);
        }
    }

    public MockSettings verificationStartedListeners(VerificationStartedListener... verificationStartedListenerArr) {
        addListeners(verificationStartedListenerArr, this.verificationStartedListeners, "verificationStartedListeners");
        return this;
    }

    private boolean invocationListenersContainsType(Class<?> cls) {
        for (InvocationListener invocationListener : this.invocationListeners) {
            if (invocationListener.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasInvocationListeners() {
        return !getInvocationListeners().isEmpty();
    }

    public Class<T> getTypeToMock() {
        return this.typeToMock;
    }

    public <T> MockCreationSettings<T> build(Class<T> cls) {
        return validatedSettings(cls, this);
    }

    public MockSettings lenient() {
        this.lenient = true;
        return this;
    }

    private static <T> CreationSettings<T> validatedSettings(Class<T> cls, CreationSettings<T> creationSettings) {
        MockCreationValidator mockCreationValidator = new MockCreationValidator();
        mockCreationValidator.validateType(cls);
        mockCreationValidator.validateExtraInterfaces(cls, creationSettings.getExtraInterfaces());
        mockCreationValidator.validateMockedType(cls, creationSettings.getSpiedInstance());
        mockCreationValidator.validateConstructorUse(creationSettings.isUsingConstructor(), creationSettings.getSerializableMode());
        CreationSettings<T> creationSettings2 = new CreationSettings<>(creationSettings);
        creationSettings2.setMockName(new MockNameImpl(creationSettings.getName(), cls));
        creationSettings2.setTypeToMock(cls);
        creationSettings2.setExtraInterfaces(prepareExtraInterfaces(creationSettings));
        return creationSettings2;
    }

    private static Set<Class<?>> prepareExtraInterfaces(CreationSettings creationSettings) {
        HashSet hashSet = new HashSet(creationSettings.getExtraInterfaces());
        if (creationSettings.isSerializable()) {
            hashSet.add(Serializable.class);
        }
        return hashSet;
    }
}
