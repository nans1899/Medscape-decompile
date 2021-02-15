package org.mockito;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {
    Answers answer() default Answers.RETURNS_DEFAULTS;

    Class<?>[] extraInterfaces() default {};

    boolean lenient() default false;

    String name() default "";

    boolean serializable() default false;

    boolean stubOnly() default false;
}
