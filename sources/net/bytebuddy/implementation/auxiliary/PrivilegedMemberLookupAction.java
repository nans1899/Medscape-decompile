package net.bytebuddy.implementation.auxiliary;

import java.lang.reflect.Type;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;

public enum PrivilegedMemberLookupAction implements AuxiliaryType {
    FOR_PUBLIC_METHOD("getMethod", "name", String.class, "parameters", Class[].class),
    FOR_DECLARED_METHOD("getDeclaredMethod", "name", String.class, "parameters", Class[].class),
    FOR_PUBLIC_CONSTRUCTOR("getConstructor", "parameters", Class[].class),
    FOR_DECLARED_CONSTRUCTOR(TypeProxy.SilentConstruction.Appender.GET_DECLARED_CONSTRUCTOR_METHOD_NAME, "parameters", Class[].class);
    
    private static final MethodDescription.InDefinedShape DEFAULT_CONSTRUCTOR = null;
    private static final String TYPE_FIELD = "type";
    private final Map<String, Class<?>> fields;
    private final MethodDescription.InDefinedShape methodDescription;

    static {
        DEFAULT_CONSTRUCTOR = (MethodDescription.InDefinedShape) ((MethodList) TypeDescription.OBJECT.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly();
    }

    private PrivilegedMemberLookupAction(String str, String str2, Class<?> cls) {
        try {
            this.methodDescription = new MethodDescription.ForLoadedMethod(Class.class.getMethod(str, new Class[]{cls}));
            this.fields = Collections.singletonMap(str2, cls);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate method: " + str, e);
        }
    }

    private PrivilegedMemberLookupAction(String str, String str2, Class<?> cls, String str3, Class<?> cls2) {
        try {
            this.methodDescription = new MethodDescription.ForLoadedMethod(Class.class.getMethod(str, new Class[]{cls, cls2}));
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.fields = linkedHashMap;
            linkedHashMap.put(str2, cls);
            this.fields.put(str3, cls2);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate method: " + str, e);
        }
    }

    public static AuxiliaryType of(MethodDescription methodDescription2) {
        if (methodDescription2.isConstructor()) {
            return methodDescription2.isPublic() ? FOR_PUBLIC_CONSTRUCTOR : FOR_DECLARED_CONSTRUCTOR;
        }
        if (methodDescription2.isMethod()) {
            return methodDescription2.isPublic() ? FOR_PUBLIC_METHOD : FOR_DECLARED_METHOD;
        }
        throw new IllegalStateException("Cannot load constant for type initializer: " + methodDescription2);
    }

    public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        Implementation.Composable andThen = MethodCall.invoke((MethodDescription) DEFAULT_CONSTRUCTOR).andThen(FieldAccessor.ofField("type").setsArgumentAt(0));
        int i = 1;
        for (String ofField : this.fields.keySet()) {
            andThen = andThen.andThen(FieldAccessor.ofField(ofField).setsArgumentAt(i));
            i++;
        }
        DynamicType.Builder<PrivilegedExceptionAction> modifiers = new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).subclass(PrivilegedExceptionAction.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER);
        ModifierContributor.ForMethod[] forMethodArr = {Visibility.PUBLIC};
        DynamicType.Builder.FieldDefinition.Optional.Valuable<T> defineField = modifiers.defineConstructor(forMethodArr).withParameters((List<? extends Type>) CompoundList.of(Class.class, new ArrayList(this.fields.values()))).intercept(andThen).method(ElementMatchers.named("run")).intercept(MethodCall.invoke((MethodDescription) this.methodDescription).onField("type").withField((String[]) this.fields.keySet().toArray(new String[this.fields.size()]))).defineField("type", (Type) Class.class, Visibility.PRIVATE);
        for (Map.Entry next : this.fields.entrySet()) {
            defineField = defineField.defineField((String) next.getKey(), (Type) next.getValue(), Visibility.PRIVATE);
        }
        return defineField.make();
    }
}
