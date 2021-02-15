package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

class ParameterFactory {
    private final Format format;

    public ParameterFactory(Support support) {
        this.format = support.getFormat();
    }

    public Parameter getInstance(Constructor constructor, Annotation annotation, int i) throws Exception {
        return getInstance(constructor, annotation, (Annotation) null, i);
    }

    public Parameter getInstance(Constructor constructor, Annotation annotation, Annotation annotation2, int i) throws Exception {
        Constructor constructor2 = getConstructor(annotation);
        if (annotation2 != null) {
            return (Parameter) constructor2.newInstance(new Object[]{constructor, annotation, annotation2, this.format, Integer.valueOf(i)});
        }
        return (Parameter) constructor2.newInstance(new Object[]{constructor, annotation, this.format, Integer.valueOf(i)});
    }

    private Constructor getConstructor(Annotation annotation) throws Exception {
        Constructor constructor = getBuilder(annotation).getConstructor();
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor;
    }

    private ParameterBuilder getBuilder(Annotation annotation) throws Exception {
        if (annotation instanceof Element) {
            return new ParameterBuilder(ElementParameter.class, Element.class);
        }
        if (annotation instanceof ElementList) {
            return new ParameterBuilder(ElementListParameter.class, ElementList.class);
        }
        if (annotation instanceof ElementArray) {
            return new ParameterBuilder(ElementArrayParameter.class, ElementArray.class);
        }
        if (annotation instanceof ElementMapUnion) {
            return new ParameterBuilder(ElementMapUnionParameter.class, ElementMapUnion.class, ElementMap.class);
        }
        if (annotation instanceof ElementListUnion) {
            return new ParameterBuilder(ElementListUnionParameter.class, ElementListUnion.class, ElementList.class);
        }
        if (annotation instanceof ElementUnion) {
            return new ParameterBuilder(ElementUnionParameter.class, ElementUnion.class, Element.class);
        }
        if (annotation instanceof ElementMap) {
            return new ParameterBuilder(ElementMapParameter.class, ElementMap.class);
        }
        if (annotation instanceof Attribute) {
            return new ParameterBuilder(AttributeParameter.class, Attribute.class);
        }
        if (annotation instanceof Text) {
            return new ParameterBuilder(TextParameter.class, Text.class);
        }
        throw new PersistenceException("Annotation %s not supported", annotation);
    }

    private static class ParameterBuilder {
        private final Class entry;
        private final Class label;
        private final Class type;

        public ParameterBuilder(Class cls, Class cls2) {
            this(cls, cls2, (Class) null);
        }

        public ParameterBuilder(Class cls, Class cls2, Class cls3) {
            this.label = cls2;
            this.entry = cls3;
            this.type = cls;
        }

        public Constructor getConstructor() throws Exception {
            Class cls = this.entry;
            if (cls != null) {
                return getConstructor(this.label, cls);
            }
            return getConstructor(this.label);
        }

        public Constructor getConstructor(Class cls) throws Exception {
            return getConstructor(Constructor.class, cls, Format.class, Integer.TYPE);
        }

        public Constructor getConstructor(Class cls, Class cls2) throws Exception {
            return getConstructor(Constructor.class, cls, cls2, Format.class, Integer.TYPE);
        }

        private Constructor getConstructor(Class... clsArr) throws Exception {
            return this.type.getConstructor(clsArr);
        }
    }
}
