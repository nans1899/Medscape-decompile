package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeType {

    public static final class Verifier {
        private Verifier() {
            throw new UnsupportedOperationException();
        }

        public static Assigner.Typing check(AnnotationSource annotationSource) {
            return Assigner.Typing.of(annotationSource.getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) RuntimeType.class));
        }
    }
}
