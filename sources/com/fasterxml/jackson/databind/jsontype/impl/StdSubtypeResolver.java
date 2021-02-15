package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StdSubtypeResolver extends SubtypeResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected LinkedHashSet<NamedType> _registeredSubtypes;

    public void registerSubtypes(NamedType... namedTypeArr) {
        if (this._registeredSubtypes == null) {
            this._registeredSubtypes = new LinkedHashSet<>();
        }
        for (NamedType add : namedTypeArr) {
            this._registeredSubtypes.add(add);
        }
    }

    public void registerSubtypes(Class<?>... clsArr) {
        NamedType[] namedTypeArr = new NamedType[clsArr.length];
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            namedTypeArr[i] = new NamedType(clsArr[i]);
        }
        registerSubtypes(namedTypeArr);
    }

    public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Class<?> rawType = javaType == null ? annotatedMember.getRawType() : javaType.getRawClass();
        HashMap hashMap = new HashMap();
        LinkedHashSet<NamedType> linkedHashSet = this._registeredSubtypes;
        if (linkedHashSet != null) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                NamedType namedType = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), mapperConfig), namedType, mapperConfig, annotationIntrospector, hashMap);
                }
            }
        }
        List<NamedType> findSubtypes = annotationIntrospector.findSubtypes(annotatedMember);
        if (findSubtypes != null) {
            for (NamedType next : findSubtypes) {
                _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(next.getType(), mapperConfig), next, mapperConfig, annotationIntrospector, hashMap);
            }
        }
        _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(rawType, mapperConfig), new NamedType(rawType, (String) null), mapperConfig, annotationIntrospector, hashMap);
        return new ArrayList(hashMap.values());
    }

    public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass) {
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        HashMap hashMap = new HashMap();
        if (this._registeredSubtypes != null) {
            Class<?> rawType = annotatedClass.getRawType();
            Iterator it = this._registeredSubtypes.iterator();
            while (it.hasNext()) {
                NamedType namedType = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), mapperConfig), namedType, mapperConfig, annotationIntrospector, hashMap);
                }
            }
        }
        _collectAndResolve(annotatedClass, new NamedType(annotatedClass.getRawType(), (String) null), mapperConfig, annotationIntrospector, hashMap);
        return new ArrayList(hashMap.values());
    }

    public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Class<?> rawType = javaType == null ? annotatedMember.getRawType() : javaType.getRawClass();
        HashSet hashSet = new HashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(rawType, mapperConfig), new NamedType(rawType, (String) null), mapperConfig, hashSet, linkedHashMap);
        List<NamedType> findSubtypes = annotationIntrospector.findSubtypes(annotatedMember);
        if (findSubtypes != null) {
            for (NamedType next : findSubtypes) {
                _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(next.getType(), mapperConfig), next, mapperConfig, hashSet, linkedHashMap);
            }
        }
        LinkedHashSet<NamedType> linkedHashSet = this._registeredSubtypes;
        if (linkedHashSet != null) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                NamedType namedType = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType.getType())) {
                    _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), mapperConfig), namedType, mapperConfig, hashSet, linkedHashMap);
                }
            }
        }
        return _combineNamedAndUnnamed(hashSet, linkedHashMap);
    }

    public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass) {
        HashSet hashSet = new HashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        _collectAndResolveByTypeId(annotatedClass, new NamedType(annotatedClass.getRawType(), (String) null), mapperConfig, hashSet, linkedHashMap);
        if (this._registeredSubtypes != null) {
            Class<?> rawType = annotatedClass.getRawType();
            Iterator it = this._registeredSubtypes.iterator();
            while (it.hasNext()) {
                NamedType namedType = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType.getType())) {
                    _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), mapperConfig), namedType, mapperConfig, hashSet, linkedHashMap);
                }
            }
        }
        return _combineNamedAndUnnamed(hashSet, linkedHashMap);
    }

    @Deprecated
    public Collection<NamedType> collectAndResolveSubtypes(AnnotatedMember annotatedMember, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector, JavaType javaType) {
        return collectAndResolveSubtypesByClass(mapperConfig, annotatedMember, javaType);
    }

    @Deprecated
    public Collection<NamedType> collectAndResolveSubtypes(AnnotatedClass annotatedClass, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector) {
        return collectAndResolveSubtypesByClass(mapperConfig, annotatedClass);
    }

    /* access modifiers changed from: protected */
    public void _collectAndResolve(AnnotatedClass annotatedClass, NamedType namedType, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector, HashMap<NamedType, NamedType> hashMap) {
        String findTypeName;
        if (!namedType.hasName() && (findTypeName = annotationIntrospector.findTypeName(annotatedClass)) != null) {
            namedType = new NamedType(namedType.getType(), findTypeName);
        }
        if (!hashMap.containsKey(namedType)) {
            hashMap.put(namedType, namedType);
            List<NamedType> findSubtypes = annotationIntrospector.findSubtypes(annotatedClass);
            if (findSubtypes != null && !findSubtypes.isEmpty()) {
                for (NamedType next : findSubtypes) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(next.getType(), mapperConfig), next, mapperConfig, annotationIntrospector, hashMap);
                }
            }
        } else if (namedType.hasName() && !hashMap.get(namedType).hasName()) {
            hashMap.put(namedType, namedType);
        }
    }

    /* access modifiers changed from: protected */
    public void _collectAndResolveByTypeId(AnnotatedClass annotatedClass, NamedType namedType, MapperConfig<?> mapperConfig, Set<Class<?>> set, Map<String, NamedType> map) {
        List<NamedType> findSubtypes;
        String findTypeName;
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        if (!namedType.hasName() && (findTypeName = annotationIntrospector.findTypeName(annotatedClass)) != null) {
            namedType = new NamedType(namedType.getType(), findTypeName);
        }
        if (namedType.hasName()) {
            map.put(namedType.getName(), namedType);
        }
        if (set.add(namedType.getType()) && (findSubtypes = annotationIntrospector.findSubtypes(annotatedClass)) != null && !findSubtypes.isEmpty()) {
            for (NamedType next : findSubtypes) {
                _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(next.getType(), mapperConfig), next, mapperConfig, set, map);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Collection<NamedType> _combineNamedAndUnnamed(Set<Class<?>> set, Map<String, NamedType> map) {
        ArrayList arrayList = new ArrayList(map.values());
        for (NamedType type : map.values()) {
            set.remove(type.getType());
        }
        for (Class<?> namedType : set) {
            arrayList.add(new NamedType(namedType));
        }
        return arrayList;
    }
}
