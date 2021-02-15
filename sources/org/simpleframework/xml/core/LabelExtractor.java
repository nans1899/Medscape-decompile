package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
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
import org.simpleframework.xml.Version;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class LabelExtractor {
    private final Cache<LabelGroup> cache = new ConcurrentCache();
    private final Format format;

    public LabelExtractor(Format format2) {
        this.format = format2;
    }

    public Label getLabel(Contact contact, Annotation annotation) throws Exception {
        LabelGroup group = getGroup(contact, annotation, getKey(contact, annotation));
        if (group != null) {
            return group.getPrimary();
        }
        return null;
    }

    public List<Label> getList(Contact contact, Annotation annotation) throws Exception {
        LabelGroup group = getGroup(contact, annotation, getKey(contact, annotation));
        if (group != null) {
            return group.getList();
        }
        return Collections.emptyList();
    }

    private LabelGroup getGroup(Contact contact, Annotation annotation, Object obj) throws Exception {
        LabelGroup fetch = this.cache.fetch(obj);
        if (fetch != null) {
            return fetch;
        }
        LabelGroup labels = getLabels(contact, annotation);
        if (labels != null) {
            this.cache.cache(obj, labels);
        }
        return labels;
    }

    private LabelGroup getLabels(Contact contact, Annotation annotation) throws Exception {
        if (annotation instanceof ElementUnion) {
            return getUnion(contact, annotation);
        }
        if (annotation instanceof ElementListUnion) {
            return getUnion(contact, annotation);
        }
        if (annotation instanceof ElementMapUnion) {
            return getUnion(contact, annotation);
        }
        return getSingle(contact, annotation);
    }

    private LabelGroup getSingle(Contact contact, Annotation annotation) throws Exception {
        Label label = getLabel(contact, annotation, (Annotation) null);
        if (label != null) {
            label = new CacheLabel(label);
        }
        return new LabelGroup(label);
    }

    private LabelGroup getUnion(Contact contact, Annotation annotation) throws Exception {
        Annotation[] annotations = getAnnotations(annotation);
        if (annotations.length <= 0) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        for (Annotation label : annotations) {
            Label label2 = getLabel(contact, annotation, label);
            if (label2 != null) {
                label2 = new CacheLabel(label2);
            }
            linkedList.add(label2);
        }
        return new LabelGroup((List<Label>) linkedList);
    }

    private Annotation[] getAnnotations(Annotation annotation) throws Exception {
        Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
        return declaredMethods.length > 0 ? (Annotation[]) declaredMethods[0].invoke(annotation, new Object[0]) : new Annotation[0];
    }

    private Label getLabel(Contact contact, Annotation annotation, Annotation annotation2) throws Exception {
        Constructor constructor = getConstructor(annotation);
        if (annotation2 != null) {
            return (Label) constructor.newInstance(new Object[]{contact, annotation, annotation2, this.format});
        }
        return (Label) constructor.newInstance(new Object[]{contact, annotation, this.format});
    }

    private Object getKey(Contact contact, Annotation annotation) {
        return new LabelKey(contact, annotation);
    }

    private Constructor getConstructor(Annotation annotation) throws Exception {
        Constructor constructor = getBuilder(annotation).getConstructor();
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor;
    }

    private LabelBuilder getBuilder(Annotation annotation) throws Exception {
        if (annotation instanceof Element) {
            return new LabelBuilder(ElementLabel.class, Element.class);
        }
        if (annotation instanceof ElementList) {
            return new LabelBuilder(ElementListLabel.class, ElementList.class);
        }
        if (annotation instanceof ElementArray) {
            return new LabelBuilder(ElementArrayLabel.class, ElementArray.class);
        }
        if (annotation instanceof ElementMap) {
            return new LabelBuilder(ElementMapLabel.class, ElementMap.class);
        }
        if (annotation instanceof ElementUnion) {
            return new LabelBuilder(ElementUnionLabel.class, ElementUnion.class, Element.class);
        }
        if (annotation instanceof ElementListUnion) {
            return new LabelBuilder(ElementListUnionLabel.class, ElementListUnion.class, ElementList.class);
        }
        if (annotation instanceof ElementMapUnion) {
            return new LabelBuilder(ElementMapUnionLabel.class, ElementMapUnion.class, ElementMap.class);
        }
        if (annotation instanceof Attribute) {
            return new LabelBuilder(AttributeLabel.class, Attribute.class);
        }
        if (annotation instanceof Version) {
            return new LabelBuilder(VersionLabel.class, Version.class);
        }
        if (annotation instanceof Text) {
            return new LabelBuilder(TextLabel.class, Text.class);
        }
        throw new PersistenceException("Annotation %s not supported", annotation);
    }

    private static class LabelBuilder {
        private final Class entry;
        private final Class label;
        private final Class type;

        public LabelBuilder(Class cls, Class cls2) {
            this(cls, cls2, (Class) null);
        }

        public LabelBuilder(Class cls, Class cls2, Class cls3) {
            this.entry = cls3;
            this.label = cls2;
            this.type = cls;
        }

        public Constructor getConstructor() throws Exception {
            Class cls = this.entry;
            if (cls != null) {
                return getConstructor(this.label, cls);
            }
            return getConstructor(this.label);
        }

        private Constructor getConstructor(Class cls) throws Exception {
            return this.type.getConstructor(new Class[]{Contact.class, cls, Format.class});
        }

        private Constructor getConstructor(Class cls, Class cls2) throws Exception {
            return this.type.getConstructor(new Class[]{Contact.class, cls, cls2, Format.class});
        }
    }
}
