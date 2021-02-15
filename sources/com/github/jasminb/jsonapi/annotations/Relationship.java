package com.github.jasminb.jsonapi.annotations;

import com.github.jasminb.jsonapi.RelType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Relationship {
    String path() default "";

    RelType relType() default RelType.SELF;

    String relatedPath() default "";

    boolean resolve() default false;

    boolean serialise() default true;

    String value();
}
