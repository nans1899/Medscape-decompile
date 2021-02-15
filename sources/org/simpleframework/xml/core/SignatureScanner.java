package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Text;

class SignatureScanner {
    private final SignatureBuilder builder;
    private final Constructor constructor;
    private final ParameterFactory factory;
    private final ParameterMap registry;
    private final Class type;

    public SignatureScanner(Constructor constructor2, ParameterMap parameterMap, Support support) throws Exception {
        this.builder = new SignatureBuilder(constructor2);
        this.factory = new ParameterFactory(support);
        Class declaringClass = constructor2.getDeclaringClass();
        this.type = declaringClass;
        this.constructor = constructor2;
        this.registry = parameterMap;
        scan(declaringClass);
    }

    public boolean isValid() {
        return this.builder.isValid();
    }

    public List<Signature> getSignatures() throws Exception {
        return this.builder.build();
    }

    private void scan(Class cls) throws Exception {
        Class[] parameterTypes = this.constructor.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            scan(parameterTypes[i], i);
        }
    }

    private void scan(Class cls, int i) throws Exception {
        Annotation[][] parameterAnnotations = this.constructor.getParameterAnnotations();
        for (Annotation process : parameterAnnotations[i]) {
            for (Parameter insert : process(process, i)) {
                this.builder.insert(insert, i);
            }
        }
    }

    private List<Parameter> process(Annotation annotation, int i) throws Exception {
        if (annotation instanceof Attribute) {
            return create(annotation, i);
        }
        if (annotation instanceof Element) {
            return create(annotation, i);
        }
        if (annotation instanceof ElementList) {
            return create(annotation, i);
        }
        if (annotation instanceof ElementArray) {
            return create(annotation, i);
        }
        if (annotation instanceof ElementMap) {
            return create(annotation, i);
        }
        if (annotation instanceof ElementListUnion) {
            return union(annotation, i);
        }
        if (annotation instanceof ElementMapUnion) {
            return union(annotation, i);
        }
        if (annotation instanceof ElementUnion) {
            return union(annotation, i);
        }
        if (annotation instanceof Text) {
            return create(annotation, i);
        }
        return Collections.emptyList();
    }

    private List<Parameter> union(Annotation annotation, int i) throws Exception {
        Signature signature = new Signature(this.constructor);
        Annotation[] extract = extract(annotation);
        int length = extract.length;
        int i2 = 0;
        while (i2 < length) {
            Parameter instance = this.factory.getInstance(this.constructor, annotation, extract[i2], i);
            String path = instance.getPath();
            if (!signature.contains(path)) {
                signature.set(path, instance);
                register(instance);
                i2++;
            } else {
                throw new UnionException("Annotation name '%s' used more than once in %s for %s", path, annotation, this.type);
            }
        }
        return signature.getAll();
    }

    private List<Parameter> create(Annotation annotation, int i) throws Exception {
        Parameter instance = this.factory.getInstance(this.constructor, annotation, i);
        if (instance != null) {
            register(instance);
        }
        return Collections.singletonList(instance);
    }

    private Annotation[] extract(Annotation annotation) throws Exception {
        Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
        if (declaredMethods.length == 1) {
            return (Annotation[]) declaredMethods[0].invoke(annotation, new Object[0]);
        }
        throw new UnionException("Annotation '%s' is not a valid union for %s", annotation, this.type);
    }

    private void register(Parameter parameter) throws Exception {
        String path = parameter.getPath();
        Object key = parameter.getKey();
        if (this.registry.containsKey(key)) {
            validate(parameter, key);
        }
        if (this.registry.containsKey(path)) {
            validate(parameter, path);
        }
        this.registry.put(path, parameter);
        this.registry.put(key, parameter);
    }

    private void validate(Parameter parameter, Object obj) throws Exception {
        Parameter parameter2 = (Parameter) this.registry.get(obj);
        if (parameter.isText() != parameter2.isText()) {
            Annotation annotation = parameter.getAnnotation();
            Annotation annotation2 = parameter2.getAnnotation();
            String path = parameter.getPath();
            if (!annotation.equals(annotation2)) {
                throw new ConstructorException("Annotations do not match for '%s' in %s", path, this.type);
            } else if (parameter2.getType() != parameter.getType()) {
                throw new ConstructorException("Parameter types do not match for '%s' in %s", path, this.type);
            }
        }
    }
}
