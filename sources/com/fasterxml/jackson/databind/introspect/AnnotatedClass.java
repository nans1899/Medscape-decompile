package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class AnnotatedClass extends Annotated implements TypeResolutionContext {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final TypeBindings _bindings;
    protected final Class<?> _class;
    protected AnnotationMap _classAnnotations;
    protected List<AnnotatedConstructor> _constructors;
    protected List<AnnotatedMethod> _creatorMethods;
    protected boolean _creatorsResolved = false;
    protected AnnotatedConstructor _defaultConstructor;
    protected List<AnnotatedField> _fields;
    protected AnnotatedMethodMap _memberMethods;
    protected final ClassIntrospector.MixInResolver _mixInResolver;
    protected final Class<?> _primaryMixIn;
    protected final List<JavaType> _superTypes;
    protected final JavaType _type;
    protected final TypeFactory _typeFactory;

    private AnnotatedClass(JavaType javaType, Class<?> cls, TypeBindings typeBindings, List<JavaType> list, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver, TypeFactory typeFactory, AnnotationMap annotationMap) {
        Class<?> cls2;
        this._type = javaType;
        this._class = cls;
        this._bindings = typeBindings;
        this._superTypes = list;
        this._annotationIntrospector = annotationIntrospector;
        this._typeFactory = typeFactory;
        this._mixInResolver = mixInResolver;
        if (mixInResolver == null) {
            cls2 = null;
        } else {
            cls2 = mixInResolver.findMixInClassFor(cls);
        }
        this._primaryMixIn = cls2;
        this._classAnnotations = annotationMap;
    }

    public AnnotatedClass withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedClass(this._type, this._class, this._bindings, this._superTypes, this._annotationIntrospector, this._mixInResolver, this._typeFactory, annotationMap);
    }

    public static AnnotatedClass construct(JavaType javaType, MapperConfig<?> mapperConfig) {
        return new AnnotatedClass(javaType, javaType.getRawClass(), javaType.getBindings(), ClassUtil.findSuperTypes(javaType, (Class<?>) null, false), mapperConfig.isAnnotationProcessingEnabled() ? mapperConfig.getAnnotationIntrospector() : null, mapperConfig, mapperConfig.getTypeFactory(), (AnnotationMap) null);
    }

    public static AnnotatedClass construct(JavaType javaType, MapperConfig<?> mapperConfig, ClassIntrospector.MixInResolver mixInResolver) {
        return new AnnotatedClass(javaType, javaType.getRawClass(), javaType.getBindings(), ClassUtil.findSuperTypes(javaType, (Class<?>) null, false), mapperConfig.isAnnotationProcessingEnabled() ? mapperConfig.getAnnotationIntrospector() : null, mixInResolver, mapperConfig.getTypeFactory(), (AnnotationMap) null);
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, MapperConfig<?> mapperConfig) {
        if (mapperConfig == null) {
            return new AnnotatedClass((JavaType) null, cls, TypeBindings.emptyBindings(), Collections.emptyList(), (AnnotationIntrospector) null, (ClassIntrospector.MixInResolver) null, (TypeFactory) null, (AnnotationMap) null);
        }
        return new AnnotatedClass((JavaType) null, cls, TypeBindings.emptyBindings(), Collections.emptyList(), mapperConfig.isAnnotationProcessingEnabled() ? mapperConfig.getAnnotationIntrospector() : null, mapperConfig, mapperConfig.getTypeFactory(), (AnnotationMap) null);
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, MapperConfig<?> mapperConfig, ClassIntrospector.MixInResolver mixInResolver) {
        if (mapperConfig == null) {
            return new AnnotatedClass((JavaType) null, cls, TypeBindings.emptyBindings(), Collections.emptyList(), (AnnotationIntrospector) null, (ClassIntrospector.MixInResolver) null, (TypeFactory) null, (AnnotationMap) null);
        }
        return new AnnotatedClass((JavaType) null, cls, TypeBindings.emptyBindings(), Collections.emptyList(), mapperConfig.isAnnotationProcessingEnabled() ? mapperConfig.getAnnotationIntrospector() : null, mixInResolver, mapperConfig.getTypeFactory(), (AnnotationMap) null);
    }

    public JavaType resolveType(Type type) {
        return this._typeFactory.constructType(type, this._bindings);
    }

    public Class<?> getAnnotated() {
        return this._class;
    }

    public int getModifiers() {
        return this._class.getModifiers();
    }

    public String getName() {
        return this._class.getName();
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return _classAnnotations().get(cls);
    }

    public boolean hasAnnotation(Class<?> cls) {
        return _classAnnotations().has(cls);
    }

    public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
        return _classAnnotations().hasOneOf(clsArr);
    }

    public Class<?> getRawType() {
        return this._class;
    }

    public Iterable<Annotation> annotations() {
        return _classAnnotations().annotations();
    }

    /* access modifiers changed from: protected */
    public AnnotationMap getAllAnnotations() {
        return _classAnnotations();
    }

    public JavaType getType() {
        return this._type;
    }

    public Annotations getAnnotations() {
        return _classAnnotations();
    }

    public boolean hasAnnotations() {
        return _classAnnotations().size() > 0;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._defaultConstructor;
    }

    public List<AnnotatedConstructor> getConstructors() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._constructors;
    }

    public List<AnnotatedMethod> getStaticMethods() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._creatorMethods;
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods;
    }

    public int getMemberMethodCount() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.size();
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.find(str, clsArr);
    }

    public int getFieldCount() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields.size();
    }

    public Iterable<AnnotatedField> fields() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields;
    }

    private AnnotationMap _classAnnotations() {
        AnnotationMap annotationMap = this._classAnnotations;
        if (annotationMap == null) {
            synchronized (this) {
                annotationMap = this._classAnnotations;
                if (annotationMap == null) {
                    annotationMap = _resolveClassAnnotations();
                    this._classAnnotations = annotationMap;
                }
            }
        }
        return annotationMap;
    }

    private AnnotationMap _resolveClassAnnotations() {
        AnnotationMap annotationMap = new AnnotationMap();
        if (this._annotationIntrospector != null) {
            Class<?> cls = this._primaryMixIn;
            if (cls != null) {
                _addClassMixIns(annotationMap, this._class, cls);
            }
            _addAnnotationsIfNotPresent(annotationMap, ClassUtil.findClassAnnotations(this._class));
            for (JavaType next : this._superTypes) {
                _addClassMixIns(annotationMap, next);
                _addAnnotationsIfNotPresent(annotationMap, ClassUtil.findClassAnnotations(next.getRawClass()));
            }
            _addClassMixIns(annotationMap, (Class<?>) Object.class);
        }
        return annotationMap;
    }

    private void resolveCreators() {
        ClassUtil.Ctor[] constructors = ClassUtil.getConstructors(this._class);
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (ClassUtil.Ctor ctor : constructors) {
            if (_isIncludableConstructor(ctor.getConstructor())) {
                if (ctor.getParamCount() == 0) {
                    this._defaultConstructor = _constructDefaultConstructor(ctor, this);
                } else {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(Math.max(10, constructors.length));
                    }
                    arrayList2.add(_constructNonDefaultConstructor(ctor, this));
                }
            }
        }
        if (arrayList2 == null) {
            this._constructors = Collections.emptyList();
        } else {
            this._constructors = arrayList2;
        }
        if (this._primaryMixIn != null && (this._defaultConstructor != null || !this._constructors.isEmpty())) {
            _addConstructorMixIns(this._primaryMixIn);
        }
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            AnnotatedConstructor annotatedConstructor = this._defaultConstructor;
            if (annotatedConstructor != null && annotationIntrospector.hasIgnoreMarker(annotatedConstructor)) {
                this._defaultConstructor = null;
            }
            List<AnnotatedConstructor> list = this._constructors;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker(this._constructors.get(size))) {
                        this._constructors.remove(size);
                    }
                }
            }
        }
        for (Method method : _findClassMethods(this._class)) {
            if (Modifier.isStatic(method.getModifiers())) {
                if (arrayList == null) {
                    arrayList = new ArrayList(8);
                }
                arrayList.add(_constructCreatorMethod(method, this));
            }
        }
        if (arrayList == null) {
            this._creatorMethods = Collections.emptyList();
        } else {
            this._creatorMethods = arrayList;
            Class<?> cls = this._primaryMixIn;
            if (cls != null) {
                _addFactoryMixIns(cls);
            }
            if (this._annotationIntrospector != null) {
                int size2 = this._creatorMethods.size();
                while (true) {
                    size2--;
                    if (size2 < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker(this._creatorMethods.get(size2))) {
                        this._creatorMethods.remove(size2);
                    }
                }
            }
        }
        this._creatorsResolved = true;
    }

    private void resolveMemberMethods() {
        Class<?> findMixInClassFor;
        this._memberMethods = new AnnotatedMethodMap();
        AnnotatedMethodMap annotatedMethodMap = new AnnotatedMethodMap();
        _addMemberMethods(this._class, this, this._memberMethods, this._primaryMixIn, annotatedMethodMap);
        for (JavaType next : this._superTypes) {
            ClassIntrospector.MixInResolver mixInResolver = this._mixInResolver;
            Class<?> findMixInClassFor2 = mixInResolver == null ? null : mixInResolver.findMixInClassFor(next.getRawClass());
            _addMemberMethods(next.getRawClass(), new TypeResolutionContext.Basic(this._typeFactory, next.getBindings()), this._memberMethods, findMixInClassFor2, annotatedMethodMap);
        }
        ClassIntrospector.MixInResolver mixInResolver2 = this._mixInResolver;
        if (!(mixInResolver2 == null || (findMixInClassFor = mixInResolver2.findMixInClassFor(Object.class)) == null)) {
            _addMethodMixIns(this._class, this._memberMethods, findMixInClassFor, annotatedMethodMap);
        }
        if (this._annotationIntrospector != null && !annotatedMethodMap.isEmpty()) {
            Iterator<AnnotatedMethod> it = annotatedMethodMap.iterator();
            while (it.hasNext()) {
                AnnotatedMethod next2 = it.next();
                try {
                    Method declaredMethod = Object.class.getDeclaredMethod(next2.getName(), next2.getRawParameterTypes());
                    if (declaredMethod != null) {
                        AnnotatedMethod _constructMethod = _constructMethod(declaredMethod, this);
                        _addMixOvers(next2.getAnnotated(), _constructMethod, false);
                        this._memberMethods.add(_constructMethod);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    private void resolveFields() {
        Map<String, AnnotatedField> _findFields = _findFields(this._type, this, (Map<String, AnnotatedField>) null);
        if (_findFields == null || _findFields.size() == 0) {
            this._fields = Collections.emptyList();
            return;
        }
        ArrayList arrayList = new ArrayList(_findFields.size());
        this._fields = arrayList;
        arrayList.addAll(_findFields.values());
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, JavaType javaType) {
        if (this._mixInResolver != null) {
            Class<?> rawClass = javaType.getRawClass();
            _addClassMixIns(annotationMap, rawClass, this._mixInResolver.findMixInClassFor(rawClass));
        }
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls) {
        ClassIntrospector.MixInResolver mixInResolver = this._mixInResolver;
        if (mixInResolver != null) {
            _addClassMixIns(annotationMap, cls, mixInResolver.findMixInClassFor(cls));
        }
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            _addAnnotationsIfNotPresent(annotationMap, ClassUtil.findClassAnnotations(cls2));
            for (Class<?> findClassAnnotations : ClassUtil.findSuperClasses(cls2, cls, false)) {
                _addAnnotationsIfNotPresent(annotationMap, ClassUtil.findClassAnnotations(findClassAnnotations));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addConstructorMixIns(Class<?> cls) {
        List<AnnotatedConstructor> list = this._constructors;
        int size = list == null ? 0 : list.size();
        MemberKey[] memberKeyArr = null;
        for (ClassUtil.Ctor constructor : ClassUtil.getConstructors(cls)) {
            Constructor<?> constructor2 = constructor.getConstructor();
            if (constructor2.getParameterTypes().length != 0) {
                if (memberKeyArr == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr[i] = new MemberKey((Constructor<?>) this._constructors.get(i).getAnnotated());
                    }
                }
                MemberKey memberKey = new MemberKey(constructor2);
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        if (memberKey.equals(memberKeyArr[i2])) {
                            _addMixOvers(constructor2, this._constructors.get(i2), true);
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            } else {
                AnnotatedConstructor annotatedConstructor = this._defaultConstructor;
                if (annotatedConstructor != null) {
                    _addMixOvers(constructor2, annotatedConstructor, false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addFactoryMixIns(Class<?> cls) {
        int size = this._creatorMethods.size();
        MemberKey[] memberKeyArr = null;
        for (Method method : ClassUtil.getDeclaredMethods(cls)) {
            if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length != 0) {
                if (memberKeyArr == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr[i] = new MemberKey(this._creatorMethods.get(i).getAnnotated());
                    }
                }
                MemberKey memberKey = new MemberKey(method);
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        if (memberKey.equals(memberKeyArr[i2])) {
                            _addMixOvers(method, this._creatorMethods.get(i2), true);
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMemberMethods(Class<?> cls, TypeResolutionContext typeResolutionContext, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        if (cls2 != null) {
            _addMethodMixIns(cls, annotatedMethodMap, cls2, annotatedMethodMap2);
        }
        if (cls != null) {
            for (Method method : _findClassMethods(cls)) {
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find == null) {
                        AnnotatedMethod _constructMethod = _constructMethod(method, typeResolutionContext);
                        annotatedMethodMap.add(_constructMethod);
                        AnnotatedMethod remove = annotatedMethodMap2.remove(method);
                        if (remove != null) {
                            _addMixOvers(remove.getAnnotated(), _constructMethod, false);
                        }
                    } else {
                        _addMixUnders(method, find);
                        if (find.getDeclaringClass().isInterface() && !method.getDeclaringClass().isInterface()) {
                            annotatedMethodMap.add(find.withMethod(method));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMethodMixIns(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        for (Class<?> declaredMethods : ClassUtil.findRawSuperTypes(cls2, cls, true)) {
            for (Method method : ClassUtil.getDeclaredMethods(declaredMethods)) {
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find != null) {
                        _addMixUnders(method, find);
                    } else {
                        AnnotatedMethod find2 = annotatedMethodMap2.find(method);
                        if (find2 != null) {
                            _addMixUnders(method, find2);
                        } else {
                            annotatedMethodMap2.add(_constructMethod(method, this));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, AnnotatedField> _findFields(JavaType javaType, TypeResolutionContext typeResolutionContext, Map<String, AnnotatedField> map) {
        Class<?> findMixInClassFor;
        JavaType superClass = javaType.getSuperClass();
        if (superClass != null) {
            Class<?> rawClass = javaType.getRawClass();
            map = _findFields(superClass, new TypeResolutionContext.Basic(this._typeFactory, superClass.getBindings()), map);
            for (Field field : ClassUtil.getDeclaredFields(rawClass)) {
                if (_isIncludableField(field)) {
                    if (map == null) {
                        map = new LinkedHashMap<>();
                    }
                    map.put(field.getName(), _constructField(field, typeResolutionContext));
                }
            }
            ClassIntrospector.MixInResolver mixInResolver = this._mixInResolver;
            if (!(mixInResolver == null || (findMixInClassFor = mixInResolver.findMixInClassFor(rawClass)) == null)) {
                _addFieldMixIns(findMixInClassFor, rawClass, map);
            }
        }
        return map;
    }

    /* access modifiers changed from: protected */
    public void _addFieldMixIns(Class<?> cls, Class<?> cls2, Map<String, AnnotatedField> map) {
        AnnotatedField annotatedField;
        for (Class<?> declaredFields : ClassUtil.findSuperClasses(cls, cls2, true)) {
            for (Field field : ClassUtil.getDeclaredFields(declaredFields)) {
                if (_isIncludableField(field) && (annotatedField = map.get(field.getName())) != null) {
                    _addOrOverrideAnnotations(annotatedField, field.getDeclaredAnnotations());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructMethod(Method method, TypeResolutionContext typeResolutionContext) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(typeResolutionContext, method, _emptyAnnotationMap(), (AnnotationMap[]) null);
        }
        return new AnnotatedMethod(typeResolutionContext, method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), (AnnotationMap[]) null);
    }

    /* access modifiers changed from: protected */
    public AnnotatedConstructor _constructDefaultConstructor(ClassUtil.Ctor ctor, TypeResolutionContext typeResolutionContext) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedConstructor(typeResolutionContext, ctor.getConstructor(), _emptyAnnotationMap(), NO_ANNOTATION_MAPS);
        }
        return new AnnotatedConstructor(typeResolutionContext, ctor.getConstructor(), _collectRelevantAnnotations(ctor.getDeclaredAnnotations()), NO_ANNOTATION_MAPS);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.introspect.AnnotatedConstructor _constructNonDefaultConstructor(com.fasterxml.jackson.databind.util.ClassUtil.Ctor r9, com.fasterxml.jackson.databind.introspect.TypeResolutionContext r10) {
        /*
            r8 = this;
            int r0 = r9.getParamCount()
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r8._annotationIntrospector
            if (r1 != 0) goto L_0x001a
            com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r1 = new com.fasterxml.jackson.databind.introspect.AnnotatedConstructor
            java.lang.reflect.Constructor r9 = r9.getConstructor()
            com.fasterxml.jackson.databind.introspect.AnnotationMap r2 = r8._emptyAnnotationMap()
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r0 = r8._emptyAnnotationMaps(r0)
            r1.<init>(r10, r9, r2, r0)
            return r1
        L_0x001a:
            if (r0 != 0) goto L_0x0030
            com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r0 = new com.fasterxml.jackson.databind.introspect.AnnotatedConstructor
            java.lang.reflect.Constructor r1 = r9.getConstructor()
            java.lang.annotation.Annotation[] r9 = r9.getDeclaredAnnotations()
            com.fasterxml.jackson.databind.introspect.AnnotationMap r9 = r8._collectRelevantAnnotations((java.lang.annotation.Annotation[]) r9)
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r2 = NO_ANNOTATION_MAPS
            r0.<init>(r10, r1, r9, r2)
            return r0
        L_0x0030:
            java.lang.annotation.Annotation[][] r1 = r9.getParameterAnnotations()
            int r2 = r1.length
            if (r0 == r2) goto L_0x00a8
            r2 = 0
            java.lang.Class r3 = r9.getDeclaringClass()
            boolean r4 = r3.isEnum()
            r5 = 0
            if (r4 == 0) goto L_0x0058
            int r4 = r1.length
            r6 = 2
            int r4 = r4 + r6
            if (r0 != r4) goto L_0x0058
            int r2 = r1.length
            int r2 = r2 + r6
            java.lang.annotation.Annotation[][] r2 = new java.lang.annotation.Annotation[r2][]
            int r3 = r1.length
            java.lang.System.arraycopy(r1, r5, r2, r6, r3)
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r1 = r8._collectRelevantAnnotations((java.lang.annotation.Annotation[][]) r2)
        L_0x0054:
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x0070
        L_0x0058:
            boolean r3 = r3.isMemberClass()
            if (r3 == 0) goto L_0x0070
            int r3 = r1.length
            r4 = 1
            int r3 = r3 + r4
            if (r0 != r3) goto L_0x0070
            int r2 = r1.length
            int r2 = r2 + r4
            java.lang.annotation.Annotation[][] r2 = new java.lang.annotation.Annotation[r2][]
            int r3 = r1.length
            java.lang.System.arraycopy(r1, r5, r2, r4, r3)
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r1 = r8._collectRelevantAnnotations((java.lang.annotation.Annotation[][]) r2)
            goto L_0x0054
        L_0x0070:
            if (r2 == 0) goto L_0x0073
            goto L_0x00ac
        L_0x0073:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Internal error: constructor for "
            r2.append(r3)
            java.lang.Class r9 = r9.getDeclaringClass()
            java.lang.String r9 = r9.getName()
            r2.append(r9)
            java.lang.String r9 = " has mismatch: "
            r2.append(r9)
            r2.append(r0)
            java.lang.String r9 = " parameters; "
            r2.append(r9)
            int r9 = r1.length
            r2.append(r9)
            java.lang.String r9 = " sets of annotations"
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r10.<init>(r9)
            throw r10
        L_0x00a8:
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r2 = r8._collectRelevantAnnotations((java.lang.annotation.Annotation[][]) r1)
        L_0x00ac:
            com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r0 = new com.fasterxml.jackson.databind.introspect.AnnotatedConstructor
            java.lang.reflect.Constructor r1 = r9.getConstructor()
            java.lang.annotation.Annotation[] r9 = r9.getDeclaredAnnotations()
            com.fasterxml.jackson.databind.introspect.AnnotationMap r9 = r8._collectRelevantAnnotations((java.lang.annotation.Annotation[]) r9)
            r0.<init>(r10, r1, r9, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotatedClass._constructNonDefaultConstructor(com.fasterxml.jackson.databind.util.ClassUtil$Ctor, com.fasterxml.jackson.databind.introspect.TypeResolutionContext):com.fasterxml.jackson.databind.introspect.AnnotatedConstructor");
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructCreatorMethod(Method method, TypeResolutionContext typeResolutionContext) {
        int length = method.getParameterTypes().length;
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(typeResolutionContext, method, _emptyAnnotationMap(), _emptyAnnotationMaps(length));
        }
        if (length == 0) {
            return new AnnotatedMethod(typeResolutionContext, method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), NO_ANNOTATION_MAPS);
        }
        return new AnnotatedMethod(typeResolutionContext, method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), _collectRelevantAnnotations(method.getParameterAnnotations()));
    }

    /* access modifiers changed from: protected */
    public AnnotatedField _constructField(Field field, TypeResolutionContext typeResolutionContext) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedField(typeResolutionContext, field, _emptyAnnotationMap());
        }
        return new AnnotatedField(typeResolutionContext, field, _collectRelevantAnnotations(field.getDeclaredAnnotations()));
    }

    private AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    private AnnotationMap[] _emptyAnnotationMaps(int i) {
        if (i == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] annotationMapArr = new AnnotationMap[i];
        for (int i2 = 0; i2 < i; i2++) {
            annotationMapArr[i2] = _emptyAnnotationMap();
        }
        return annotationMapArr;
    }

    /* access modifiers changed from: protected */
    public boolean _isIncludableMemberMethod(Method method) {
        if (!Modifier.isStatic(method.getModifiers()) && !method.isSynthetic() && !method.isBridge() && method.getParameterTypes().length <= 2) {
            return true;
        }
        return false;
    }

    private boolean _isIncludableField(Field field) {
        if (!field.isSynthetic() && !Modifier.isStatic(field.getModifiers())) {
            return true;
        }
        return false;
    }

    private boolean _isIncludableConstructor(Constructor<?> constructor) {
        return !constructor.isSynthetic();
    }

    /* access modifiers changed from: protected */
    public AnnotationMap[] _collectRelevantAnnotations(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        AnnotationMap[] annotationMapArr = new AnnotationMap[length];
        for (int i = 0; i < length; i++) {
            annotationMapArr[i] = _collectRelevantAnnotations(annotationArr[i]);
        }
        return annotationMapArr;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap _collectRelevantAnnotations(Annotation[] annotationArr) {
        return _addAnnotationsIfNotPresent(new AnnotationMap(), annotationArr);
    }

    private AnnotationMap _addAnnotationsIfNotPresent(AnnotationMap annotationMap, Annotation[] annotationArr) {
        if (annotationArr != null) {
            List<Annotation> list = null;
            for (Annotation annotation : annotationArr) {
                if (annotationMap.addIfNotPresent(annotation) && _isAnnotationBundle(annotation)) {
                    list = _addFromBundle(annotation, list);
                }
            }
            if (list != null) {
                _addAnnotationsIfNotPresent(annotationMap, (Annotation[]) list.toArray(new Annotation[list.size()]));
            }
        }
        return annotationMap;
    }

    private List<Annotation> _addFromBundle(Annotation annotation, List<Annotation> list) {
        for (Annotation annotation2 : ClassUtil.findClassAnnotations(annotation.annotationType())) {
            if (!(annotation2 instanceof Target) && !(annotation2 instanceof Retention)) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(annotation2);
            }
        }
        return list;
    }

    private void _addAnnotationsIfNotPresent(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        if (annotationArr != null) {
            List<Annotation> list = null;
            for (Annotation annotation : annotationArr) {
                if (annotatedMember.addIfNotPresent(annotation) && _isAnnotationBundle(annotation)) {
                    list = _addFromBundle(annotation, list);
                }
            }
            if (list != null) {
                _addAnnotationsIfNotPresent(annotatedMember, (Annotation[]) list.toArray(new Annotation[list.size()]));
            }
        }
    }

    private void _addOrOverrideAnnotations(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        if (annotationArr != null) {
            List<Annotation> list = null;
            for (Annotation annotation : annotationArr) {
                if (annotatedMember.addOrOverride(annotation) && _isAnnotationBundle(annotation)) {
                    list = _addFromBundle(annotation, list);
                }
            }
            if (list != null) {
                _addOrOverrideAnnotations(annotatedMember, (Annotation[]) list.toArray(new Annotation[list.size()]));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Constructor<?> constructor, AnnotatedConstructor annotatedConstructor, boolean z) {
        _addOrOverrideAnnotations(annotatedConstructor, constructor.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation addOrOverrideParam : parameterAnnotations[i]) {
                    annotatedConstructor.addOrOverrideParam(i, addOrOverrideParam);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Method method, AnnotatedMethod annotatedMethod, boolean z) {
        _addOrOverrideAnnotations(annotatedMethod, method.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation addOrOverrideParam : parameterAnnotations[i]) {
                    annotatedMethod.addOrOverrideParam(i, addOrOverrideParam);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixUnders(Method method, AnnotatedMethod annotatedMethod) {
        _addAnnotationsIfNotPresent((AnnotatedMember) annotatedMethod, method.getDeclaredAnnotations());
    }

    private final boolean _isAnnotationBundle(Annotation annotation) {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        return annotationIntrospector != null && annotationIntrospector.isAnnotationBundle(annotation);
    }

    /* access modifiers changed from: protected */
    public Method[] _findClassMethods(Class<?> cls) {
        try {
            return ClassUtil.getDeclaredMethods(cls);
        } catch (NoClassDefFoundError e) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                try {
                    return contextClassLoader.loadClass(cls.getName()).getDeclaredMethods();
                } catch (ClassNotFoundException unused) {
                    throw e;
                }
            } else {
                throw e;
            }
        }
    }

    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }

    public int hashCode() {
        return this._class.getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass() && ((AnnotatedClass) obj)._class == this._class) {
            return true;
        }
        return false;
    }
}
