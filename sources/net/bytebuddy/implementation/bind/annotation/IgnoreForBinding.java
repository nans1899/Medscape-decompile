package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.description.method.MethodDescription;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreForBinding {

    public static final class Verifier {
        private Verifier() {
            throw new UnsupportedOperationException();
        }

        public static boolean check(MethodDescription methodDescription) {
            return methodDescription.getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) IgnoreForBinding.class);
        }
    }
}
