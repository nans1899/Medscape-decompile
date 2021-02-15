package com.github.jasminb.jsonapi;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Links;
import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.RelationshipLinks;
import com.github.jasminb.jsonapi.annotations.RelationshipMeta;
import com.github.jasminb.jsonapi.annotations.Type;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverterConfiguration {
    private final Map<Field, Relationship> fieldRelationshipMap = new HashMap();
    private final Map<Field, RelationshipMeta> fieldRelationshipMetaMap = new HashMap();
    private final Map<Class<?>, ResourceIdHandler> idHandlerMap = new HashMap();
    private final Map<Class<?>, Field> idMap = new HashMap();
    private final Map<Class<?>, Field> linkFieldMap = new HashMap();
    private final Map<Class<?>, Field> metaFieldMap = new HashMap();
    private final Map<Class<?>, Class<?>> metaTypeMap = new HashMap();
    private final Map<Class<?>, Map<String, Field>> relationshipFieldMap = new HashMap();
    private final Map<Class<?>, Map<String, Field>> relationshipLinksFieldMap = new HashMap();
    private final Map<Class<?>, List<Field>> relationshipMap = new HashMap();
    private final Map<Class<?>, Map<String, Field>> relationshipMetaFieldMap = new HashMap();
    private final Map<Class<?>, Map<String, Class<?>>> relationshipMetaTypeMap = new HashMap();
    private final Map<Class<?>, Map<String, Class<?>>> relationshipTypeMap = new HashMap();
    private final Map<Class<?>, Type> typeAnnotations = new HashMap();
    private final Map<String, Class<?>> typeToClassMapping = new HashMap();

    public ConverterConfiguration(Class<?>... clsArr) {
        for (Class<?> registerType : clsArr) {
            registerType(registerType);
        }
    }

    private void processClass(Class<?> cls) {
        if (cls.isAnnotationPresent(Type.class)) {
            Type type = (Type) cls.getAnnotation(Type.class);
            this.typeToClassMapping.put(type.value(), cls);
            this.typeAnnotations.put(cls, type);
            this.relationshipTypeMap.put(cls, new HashMap());
            this.relationshipFieldMap.put(cls, new HashMap());
            this.relationshipMetaFieldMap.put(cls, new HashMap());
            this.relationshipMetaTypeMap.put(cls, new HashMap());
            this.relationshipLinksFieldMap.put(cls, new HashMap());
            List<Field> annotatedFields = ReflectionUtils.getAnnotatedFields(cls, Relationship.class, true);
            for (Field next : annotatedFields) {
                next.setAccessible(true);
                Relationship relationship = (Relationship) next.getAnnotation(Relationship.class);
                Class<?> fieldType = ReflectionUtils.getFieldType(next);
                this.relationshipTypeMap.get(cls).put(relationship.value(), fieldType);
                this.relationshipFieldMap.get(cls).put(relationship.value(), next);
                this.fieldRelationshipMap.put(next, relationship);
                if (!relationship.resolve() || relationship.relType() != null) {
                    registerType(fieldType);
                } else {
                    throw new IllegalArgumentException("@Relationship on " + cls.getName() + "#" + next.getName() + " with 'resolve = true' must have a relType attribute set.");
                }
            }
            this.relationshipMap.put(cls, annotatedFields);
            for (Field next2 : ReflectionUtils.getAnnotatedFields(cls, RelationshipMeta.class, true)) {
                next2.setAccessible(true);
                RelationshipMeta relationshipMeta = (RelationshipMeta) next2.getAnnotation(RelationshipMeta.class);
                this.relationshipMetaTypeMap.get(cls).put(relationshipMeta.value(), ReflectionUtils.getFieldType(next2));
                this.fieldRelationshipMetaMap.put(next2, relationshipMeta);
                this.relationshipMetaFieldMap.get(cls).put(relationshipMeta.value(), next2);
            }
            for (Field next3 : ReflectionUtils.getAnnotatedFields(cls, RelationshipLinks.class, true)) {
                next3.setAccessible(true);
                this.relationshipLinksFieldMap.get(cls).put(((RelationshipLinks) next3.getAnnotation(RelationshipLinks.class)).value(), next3);
            }
            List<Field> annotatedFields2 = ReflectionUtils.getAnnotatedFields(cls, Id.class, true);
            if (!annotatedFields2.isEmpty() && annotatedFields2.size() == 1) {
                Field field = annotatedFields2.get(0);
                field.setAccessible(true);
                this.idMap.put(cls, field);
                try {
                    this.idHandlerMap.put(cls, ((Id) field.getAnnotation(Id.class)).value().newInstance());
                    List<Field> annotatedFields3 = ReflectionUtils.getAnnotatedFields(cls, Meta.class, true);
                    if (annotatedFields3.size() == 1) {
                        Field field2 = annotatedFields3.get(0);
                        field2.setAccessible(true);
                        this.metaTypeMap.put(cls, ReflectionUtils.getFieldType(field2));
                        this.metaFieldMap.put(cls, field2);
                    } else if (annotatedFields3.size() > 1) {
                        throw new IllegalArgumentException(String.format("Only one meta field is allowed for type '%s'", new Object[]{cls.getCanonicalName()}));
                    }
                    List<Field> annotatedFields4 = ReflectionUtils.getAnnotatedFields(cls, Links.class, true);
                    if (annotatedFields4.size() == 1) {
                        Field field3 = annotatedFields4.get(0);
                        field3.setAccessible(true);
                        Class<?> fieldType2 = ReflectionUtils.getFieldType(field3);
                        if (Links.class.isAssignableFrom(fieldType2)) {
                            this.linkFieldMap.put(cls, field3);
                        } else {
                            throw new IllegalArgumentException(String.format("%s is not allowed to be used as @Links attribute. Only com.github.jasminb.jsonapi.Links or its derivatives can be annotated as @Links", new Object[]{fieldType2.getCanonicalName()}));
                        }
                    } else if (annotatedFields4.size() > 1) {
                        throw new IllegalArgumentException(String.format("Only one links field is allowed for type '%s'", new Object[]{cls.getCanonicalName()}));
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new IllegalArgumentException("Unable to construct handler instance by using no-arg constructor", e);
                }
            } else if (annotatedFields2.isEmpty()) {
                throw new IllegalArgumentException("All resource classes must have a field annotated with the @Id annotation");
            } else {
                throw new IllegalArgumentException("Only single @Id annotation is allowed per defined type!");
            }
        } else {
            throw new IllegalArgumentException("Class " + cls.getName() + " don't have Type annotation. All resource classes must be annotated with Type annotation!");
        }
    }

    public Field getMetaField(Class<?> cls) {
        return this.metaFieldMap.get(cls);
    }

    public Class<?> getMetaType(Class<?> cls) {
        return this.metaTypeMap.get(cls);
    }

    public Field getLinksField(Class<?> cls) {
        return this.linkFieldMap.get(cls);
    }

    public Class<?> getTypeClass(String str) {
        return this.typeToClassMapping.get(str);
    }

    public Field getIdField(Class<?> cls) {
        return this.idMap.get(cls);
    }

    public ResourceIdHandler getIdHandler(Class<?> cls) {
        return this.idHandlerMap.get(cls);
    }

    public Field getRelationshipField(Class<?> cls, String str) {
        return (Field) this.relationshipFieldMap.get(cls).get(str);
    }

    public Class<?> getRelationshipType(Class<?> cls, String str) {
        return (Class) this.relationshipTypeMap.get(cls).get(str);
    }

    public Relationship getFieldRelationship(Field field) {
        return this.fieldRelationshipMap.get(field);
    }

    public List<Field> getRelationshipFields(Class<?> cls) {
        return this.relationshipMap.get(cls);
    }

    public boolean isRegisteredType(Class<?> cls) {
        return this.typeAnnotations.containsKey(cls);
    }

    public String getTypeName(Class<?> cls) {
        Type type = this.typeAnnotations.get(cls);
        if (type != null) {
            return type.value();
        }
        return null;
    }

    public Type getType(Class<?> cls) {
        return this.typeAnnotations.get(cls);
    }

    public synchronized boolean registerType(Class<?> cls) {
        if (isRegisteredType(cls)) {
            return false;
        }
        processClass(cls);
        return true;
    }

    public static boolean isEligibleType(Class<?> cls) {
        if (!cls.isAnnotationPresent(Type.class) || ReflectionUtils.getAnnotatedFields(cls, Id.class, true).isEmpty()) {
            return false;
        }
        return true;
    }

    public Field getRelationshipMetaField(Class<?> cls, String str) {
        return (Field) this.relationshipMetaFieldMap.get(cls).get(str);
    }

    public Class<?> getRelationshipMetaType(Class<?> cls, String str) {
        return (Class) this.relationshipMetaTypeMap.get(cls).get(str);
    }

    public Field getRelationshipLinksField(Class<?> cls, String str) {
        return (Field) this.relationshipLinksFieldMap.get(cls).get(str);
    }
}
