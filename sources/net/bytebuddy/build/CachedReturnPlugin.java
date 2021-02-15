package net.bytebuddy.build;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.FieldPersistence;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.SyntheticState;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.RandomString;

@HashCodeAndEqualsPlugin.Enhance
public class CachedReturnPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
    private static final String ADVICE_INFIX = "$";
    private static final String NAME_INFIX = "_";
    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
    private final Map<TypeDescription, TypeDescription> adviceByType;
    private final ClassFileLocator classFileLocator;
    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
    private final RandomString randomString = new RandomString();

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface CacheField {
    }

    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Enhance {
        String value() default "";
    }

    public void close() {
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.classFileLocator.equals(((CachedReturnPlugin) obj).classFileLocator);
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.classFileLocator.hashCode();
    }

    public Plugin make() {
        return this;
    }

    class Object {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static Object enter(@CacheField Object obj) {
            return obj;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object obj, @CacheField Object obj2) {
        }

        private Object() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* renamed from: net.bytebuddy.build.CachedReturnPlugin$boolean  reason: invalid class name */
    class Cboolean {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static boolean enter(@CacheField boolean z) {
            return z;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false) boolean z, @CacheField boolean z2) {
        }

        private Cboolean() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* renamed from: net.bytebuddy.build.CachedReturnPlugin$byte  reason: invalid class name */
    class Cbyte {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static byte enter(@CacheField byte b) {
            return b;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false) byte b, @CacheField byte b2) {
        }

        private Cbyte() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* renamed from: net.bytebuddy.build.CachedReturnPlugin$char  reason: invalid class name */
    class Cchar {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static char enter(@CacheField char c) {
            return c;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false) char c, @CacheField char c2) {
        }

        private Cchar() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* renamed from: net.bytebuddy.build.CachedReturnPlugin$double  reason: invalid class name */
    class Cdouble {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static double enter(@CacheField double d) {
            return d;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false) double d, @CacheField double d2) {
            int i = (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0 : -1));
        }

        private Cdouble() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* renamed from: net.bytebuddy.build.CachedReturnPlugin$float  reason: invalid class name */
    class Cfloat {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static float enter(@CacheField float f) {
            return f;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false) float f, @CacheField float f2) {
            int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        }

        private Cfloat() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* renamed from: net.bytebuddy.build.CachedReturnPlugin$int  reason: invalid class name */
    class Cint {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static int enter(@CacheField int i) {
            return i;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false) int i, @CacheField int i2) {
        }

        private Cint() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* renamed from: net.bytebuddy.build.CachedReturnPlugin$long  reason: invalid class name */
    class Clong {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static long enter(@CacheField long j) {
            return j;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false) long j, @CacheField long j2) {
            int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        }

        private Clong() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* renamed from: net.bytebuddy.build.CachedReturnPlugin$short  reason: invalid class name */
    class Cshort {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        protected static short enter(@CacheField short s) {
            return s;
        }

        @Advice.OnMethodExit
        protected static void exit(@Advice.Return(readOnly = false) short s, @CacheField short s2) {
        }

        private Cshort() {
            throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CachedReturnPlugin() {
        super(ElementMatchers.declaresMethod(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Enhance.class)));
        Class<CachedReturnPlugin> cls = CachedReturnPlugin.class;
        ClassFileLocator of = ClassFileLocator.ForClassLoader.of(cls.getClassLoader());
        this.classFileLocator = of;
        TypePool of2 = TypePool.Default.of(of);
        this.adviceByType = new HashMap();
        Class[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class};
        for (int i = 0; i < 9; i++) {
            Class cls2 = clsArr[i];
            Map<TypeDescription, TypeDescription> map = this.adviceByType;
            TypeDescription of3 = TypeDescription.ForLoadedType.of(cls2);
            map.put(of3, of2.describe(cls.getName() + ADVICE_INFIX + cls2.getSimpleName()).resolve());
        }
    }

    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator2) {
        DynamicType.Builder<T> builder2 = builder;
        for (MethodDescription.InDefinedShape inDefinedShape : (MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.not(ElementMatchers.isBridge()).and(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Enhance.class)))) {
            if (inDefinedShape.isAbstract()) {
                throw new IllegalStateException("Cannot cache the value of an abstract method: " + inDefinedShape);
            } else if (!inDefinedShape.getParameters().isEmpty()) {
                throw new IllegalStateException("Cannot cache the value of a method with parameters: " + inDefinedShape);
            } else if (!inDefinedShape.getReturnType().represents(Void.TYPE)) {
                String value = inDefinedShape.getDeclaredAnnotations().ofType(Enhance.class).loadSilent().value();
                if (value.length() == 0) {
                    value = inDefinedShape.getName() + NAME_INFIX + this.randomString.nextString();
                }
                TypeDescription asErasure = inDefinedShape.getReturnType().asErasure();
                ModifierContributor.ForField[] forFieldArr = new ModifierContributor.ForField[4];
                forFieldArr[0] = inDefinedShape.isStatic() ? Ownership.STATIC : Ownership.MEMBER;
                forFieldArr[1] = Visibility.PRIVATE;
                forFieldArr[2] = SyntheticState.SYNTHETIC;
                forFieldArr[3] = FieldPersistence.TRANSIENT;
                builder2 = builder2.defineField(value, (TypeDefinition) asErasure, forFieldArr).visit(Advice.withCustomMapping().bind(CacheField.class, (Advice.OffsetMapping) new CacheFieldOffsetMapping(value)).to(this.adviceByType.get(inDefinedShape.getReturnType().isPrimitive() ? inDefinedShape.getReturnType().asErasure() : TypeDescription.OBJECT), this.classFileLocator).on(ElementMatchers.is(inDefinedShape)));
            } else {
                throw new IllegalStateException("Cannot cache void result for " + inDefinedShape);
            }
        }
        return builder2;
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class CacheFieldOffsetMapping implements Advice.OffsetMapping {
        private final String name;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.name.equals(((CacheFieldOffsetMapping) obj).name);
        }

        public int hashCode() {
            return 527 + this.name.hashCode();
        }

        protected CacheFieldOffsetMapping(String str) {
            this.name = str;
        }

        public Advice.OffsetMapping.Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Advice.ArgumentHandler argumentHandler, Advice.OffsetMapping.Sort sort) {
            return new Advice.OffsetMapping.Target.ForField.ReadWrite((FieldDescription) ((FieldList) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.name))).getOnly());
        }
    }
}
