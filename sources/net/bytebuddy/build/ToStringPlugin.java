package net.bytebuddy.build;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.ToStringMethod;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

@HashCodeAndEqualsPlugin.Enhance
public class ToStringPlugin implements Plugin, Plugin.Factory {

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Exclude {
    }

    public void close() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    public int hashCode() {
        return 17;
    }

    public Plugin make() {
        return this;
    }

    public boolean matches(TypeDescription typeDescription) {
        return typeDescription.getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Enhance.class);
    }

    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        ElementMatcher.Junction junction;
        Enhance loadSilent = typeDescription.getDeclaredAnnotations().ofType(Enhance.class).loadSilent();
        if (!((MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.isToString())).isEmpty()) {
            return builder;
        }
        DynamicType.Builder.MethodDefinition.ImplementationDefinition<?> method = builder.method(ElementMatchers.isToString());
        ToStringMethod prefixedBy = ToStringMethod.prefixedBy((ToStringMethod.PrefixResolver) loadSilent.prefix().getPrefixResolver());
        if (loadSilent.includeSyntheticFields()) {
            junction = ElementMatchers.none();
        } else {
            junction = ElementMatchers.isSynthetic();
        }
        return method.intercept(prefixedBy.withIgnoredFields(junction).withIgnoredFields(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Exclude.class)));
    }

    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Enhance {
        boolean includeSyntheticFields() default false;

        Prefix prefix() default Prefix.SIMPLE;

        public enum Prefix {
            FULLY_QUALIFIED(ToStringMethod.PrefixResolver.Default.FULLY_QUALIFIED_CLASS_NAME),
            CANONICAL(ToStringMethod.PrefixResolver.Default.CANONICAL_CLASS_NAME),
            SIMPLE(ToStringMethod.PrefixResolver.Default.SIMPLE_CLASS_NAME);
            
            private final ToStringMethod.PrefixResolver.Default prefixResolver;

            private Prefix(ToStringMethod.PrefixResolver.Default defaultR) {
                this.prefixResolver = defaultR;
            }

            /* access modifiers changed from: protected */
            public ToStringMethod.PrefixResolver.Default getPrefixResolver() {
                return this.prefixResolver;
            }
        }
    }
}
