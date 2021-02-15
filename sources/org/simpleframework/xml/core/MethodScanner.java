package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.Transient;
import org.simpleframework.xml.Version;

class MethodScanner extends ContactList {
    private final Detail detail;
    private final MethodPartFactory factory;
    private final PartMap read = new PartMap();
    private final Support support;
    private final PartMap write = new PartMap();

    public MethodScanner(Detail detail2, Support support2) throws Exception {
        this.factory = new MethodPartFactory(detail2, support2);
        this.support = support2;
        this.detail = detail2;
        scan(detail2);
    }

    private void scan(Detail detail2) throws Exception {
        DefaultType override = detail2.getOverride();
        DefaultType access = detail2.getAccess();
        Class cls = detail2.getSuper();
        if (cls != null) {
            extend(cls, override);
        }
        extract(detail2, access);
        extract(detail2);
        build();
        validate();
    }

    private void extend(Class cls, DefaultType defaultType) throws Exception {
        Iterator it = this.support.getMethods(cls, defaultType).iterator();
        while (it.hasNext()) {
            process((MethodContact) ((Contact) it.next()));
        }
    }

    private void extract(Detail detail2) throws Exception {
        for (MethodDetail next : detail2.getMethods()) {
            Annotation[] annotations = next.getAnnotations();
            Method method = next.getMethod();
            for (Annotation scan : annotations) {
                scan(method, scan, annotations);
            }
        }
    }

    private void extract(Detail detail2, DefaultType defaultType) throws Exception {
        List<MethodDetail> methods = detail2.getMethods();
        if (defaultType == DefaultType.PROPERTY) {
            for (MethodDetail next : methods) {
                Annotation[] annotations = next.getAnnotations();
                Method method = next.getMethod();
                if (this.factory.getType(method) != null) {
                    process(method, annotations);
                }
            }
        }
    }

    private void scan(Method method, Annotation annotation, Annotation[] annotationArr) throws Exception {
        if (annotation instanceof Attribute) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementUnion) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementListUnion) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementMapUnion) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementList) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementArray) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementMap) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof Element) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof Version) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof Text) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof Transient) {
            remove(method, annotation, annotationArr);
        }
    }

    private void process(Method method, Annotation annotation, Annotation[] annotationArr) throws Exception {
        MethodPart instance = this.factory.getInstance(method, annotation, annotationArr);
        MethodType methodType = instance.getMethodType();
        if (methodType == MethodType.GET) {
            process(instance, this.read);
        }
        if (methodType == MethodType.IS) {
            process(instance, this.read);
        }
        if (methodType == MethodType.SET) {
            process(instance, this.write);
        }
    }

    private void process(Method method, Annotation[] annotationArr) throws Exception {
        MethodPart instance = this.factory.getInstance(method, annotationArr);
        MethodType methodType = instance.getMethodType();
        if (methodType == MethodType.GET) {
            process(instance, this.read);
        }
        if (methodType == MethodType.IS) {
            process(instance, this.read);
        }
        if (methodType == MethodType.SET) {
            process(instance, this.write);
        }
    }

    private void process(MethodPart methodPart, PartMap partMap) {
        String name = methodPart.getName();
        if (name != null) {
            partMap.put(name, methodPart);
        }
    }

    private void process(MethodContact methodContact) {
        MethodPart read2 = methodContact.getRead();
        MethodPart write2 = methodContact.getWrite();
        if (write2 != null) {
            insert(write2, this.write);
        }
        insert(read2, this.read);
    }

    private void insert(MethodPart methodPart, PartMap partMap) {
        String name = methodPart.getName();
        MethodPart methodPart2 = (MethodPart) partMap.remove(name);
        if (methodPart2 != null && isText(methodPart)) {
            methodPart = methodPart2;
        }
        partMap.put(name, methodPart);
    }

    private boolean isText(MethodPart methodPart) {
        return methodPart.getAnnotation() instanceof Text;
    }

    private void remove(Method method, Annotation annotation, Annotation[] annotationArr) throws Exception {
        MethodPart instance = this.factory.getInstance(method, annotation, annotationArr);
        MethodType methodType = instance.getMethodType();
        if (methodType == MethodType.GET) {
            remove(instance, this.read);
        }
        if (methodType == MethodType.IS) {
            remove(instance, this.read);
        }
        if (methodType == MethodType.SET) {
            remove(instance, this.write);
        }
    }

    private void remove(MethodPart methodPart, PartMap partMap) throws Exception {
        String name = methodPart.getName();
        if (name != null) {
            partMap.remove(name);
        }
    }

    private void build() throws Exception {
        Iterator<String> it = this.read.iterator();
        while (it.hasNext()) {
            String next = it.next();
            MethodPart methodPart = (MethodPart) this.read.get(next);
            if (methodPart != null) {
                build(methodPart, next);
            }
        }
    }

    private void build(MethodPart methodPart, String str) throws Exception {
        MethodPart take = this.write.take(str);
        if (take != null) {
            build(methodPart, take);
        } else {
            build(methodPart);
        }
    }

    private void build(MethodPart methodPart) throws Exception {
        add(new MethodContact(methodPart));
    }

    private void build(MethodPart methodPart, MethodPart methodPart2) throws Exception {
        Annotation annotation = methodPart.getAnnotation();
        String name = methodPart.getName();
        if (methodPart2.getAnnotation().equals(annotation)) {
            Class type = methodPart.getType();
            if (type == methodPart2.getType()) {
                add(new MethodContact(methodPart, methodPart2));
            } else {
                throw new MethodException("Method types do not match for %s in %s", name, type);
            }
        } else {
            throw new MethodException("Annotations do not match for '%s' in %s", name, this.detail);
        }
    }

    private void validate() throws Exception {
        Iterator<String> it = this.write.iterator();
        while (it.hasNext()) {
            String next = it.next();
            MethodPart methodPart = (MethodPart) this.write.get(next);
            if (methodPart != null) {
                validate(methodPart, next);
            }
        }
    }

    private void validate(MethodPart methodPart, String str) throws Exception {
        MethodPart take = this.read.take(str);
        Method method = methodPart.getMethod();
        if (take == null) {
            throw new MethodException("No matching get method for %s in %s", method, this.detail);
        }
    }

    private static class PartMap extends LinkedHashMap<String, MethodPart> implements Iterable<String> {
        private PartMap() {
        }

        public Iterator<String> iterator() {
            return keySet().iterator();
        }

        public MethodPart take(String str) {
            return (MethodPart) remove(str);
        }
    }
}
