package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

public abstract class ConcreteBeanPropertyBase implements BeanProperty, Serializable {
    private static final long serialVersionUID = 1;
    protected transient JsonFormat.Value _format;
    protected final PropertyMetadata _metadata;

    public boolean isVirtual() {
        return false;
    }

    protected ConcreteBeanPropertyBase(PropertyMetadata propertyMetadata) {
        this._metadata = propertyMetadata == null ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : propertyMetadata;
    }

    protected ConcreteBeanPropertyBase(ConcreteBeanPropertyBase concreteBeanPropertyBase) {
        this._metadata = concreteBeanPropertyBase._metadata;
        this._format = concreteBeanPropertyBase._format;
    }

    public boolean isRequired() {
        return this._metadata.isRequired();
    }

    public PropertyMetadata getMetadata() {
        return this._metadata;
    }

    @Deprecated
    public final JsonFormat.Value findFormatOverrides(AnnotationIntrospector annotationIntrospector) {
        AnnotatedMember member;
        JsonFormat.Value value = this._format;
        if (value != null) {
            return value;
        }
        if (!(annotationIntrospector == null || (member = getMember()) == null)) {
            value = annotationIntrospector.findFormat(member);
        }
        return value == null ? EMPTY_FORMAT : value;
    }

    public JsonFormat.Value findPropertyFormat(MapperConfig<?> mapperConfig, Class<?> cls) {
        JsonFormat.Value findFormat;
        JsonFormat.Value defaultPropertyFormat = mapperConfig.getDefaultPropertyFormat(cls);
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        AnnotatedMember member = getMember();
        if (annotationIntrospector == null || member == null || (findFormat = annotationIntrospector.findFormat(member)) == null) {
            return defaultPropertyFormat;
        }
        return defaultPropertyFormat.withOverrides(findFormat);
    }

    public JsonInclude.Value findPropertyInclusion(MapperConfig<?> mapperConfig, Class<?> cls) {
        JsonInclude.Value findPropertyInclusion;
        JsonInclude.Value defaultPropertyInclusion = mapperConfig.getDefaultPropertyInclusion(cls);
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        AnnotatedMember member = getMember();
        if (annotationIntrospector == null || member == null || (findPropertyInclusion = annotationIntrospector.findPropertyInclusion(member)) == null) {
            return defaultPropertyInclusion;
        }
        return defaultPropertyInclusion.withOverrides(findPropertyInclusion);
    }
}
