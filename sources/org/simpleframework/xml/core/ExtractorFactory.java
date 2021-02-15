package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.stream.Format;

class ExtractorFactory {
    private final Contact contact;
    private final Format format;
    private final Annotation label;

    public ExtractorFactory(Contact contact2, Annotation annotation, Format format2) {
        this.contact = contact2;
        this.format = format2;
        this.label = annotation;
    }

    public Extractor getInstance() throws Exception {
        return (Extractor) getInstance(this.label);
    }

    private Object getInstance(Annotation annotation) throws Exception {
        Constructor access$000 = getBuilder(annotation).getConstructor();
        if (!access$000.isAccessible()) {
            access$000.setAccessible(true);
        }
        return access$000.newInstance(new Object[]{this.contact, annotation, this.format});
    }

    private ExtractorBuilder getBuilder(Annotation annotation) throws Exception {
        if (annotation instanceof ElementUnion) {
            return new ExtractorBuilder(ElementUnion.class, ElementExtractor.class);
        }
        if (annotation instanceof ElementListUnion) {
            return new ExtractorBuilder(ElementListUnion.class, ElementListExtractor.class);
        }
        if (annotation instanceof ElementMapUnion) {
            return new ExtractorBuilder(ElementMapUnion.class, ElementMapExtractor.class);
        }
        throw new PersistenceException("Annotation %s is not a union", annotation);
    }

    private static class ExtractorBuilder {
        private final Class label;
        private final Class type;

        public ExtractorBuilder(Class cls, Class cls2) {
            this.label = cls;
            this.type = cls2;
        }

        /* access modifiers changed from: private */
        public Constructor getConstructor() throws Exception {
            return this.type.getConstructor(new Class[]{Contact.class, this.label, Format.class});
        }
    }

    private static class ElementExtractor implements Extractor<Element> {
        private final Contact contact;
        private final Format format;
        private final ElementUnion union;

        public ElementExtractor(Contact contact2, ElementUnion elementUnion, Format format2) throws Exception {
            this.contact = contact2;
            this.format = format2;
            this.union = elementUnion;
        }

        public Element[] getAnnotations() {
            return this.union.value();
        }

        public Label getLabel(Element element) {
            return new ElementLabel(this.contact, element, this.format);
        }

        public Class getType(Element element) {
            Class type = element.type();
            return type == Void.TYPE ? this.contact.getType() : type;
        }
    }

    private static class ElementListExtractor implements Extractor<ElementList> {
        private final Contact contact;
        private final Format format;
        private final ElementListUnion union;

        public ElementListExtractor(Contact contact2, ElementListUnion elementListUnion, Format format2) throws Exception {
            this.contact = contact2;
            this.format = format2;
            this.union = elementListUnion;
        }

        public ElementList[] getAnnotations() {
            return this.union.value();
        }

        public Label getLabel(ElementList elementList) {
            return new ElementListLabel(this.contact, elementList, this.format);
        }

        public Class getType(ElementList elementList) {
            return elementList.type();
        }
    }

    private static class ElementMapExtractor implements Extractor<ElementMap> {
        private final Contact contact;
        private final Format format;
        private final ElementMapUnion union;

        public ElementMapExtractor(Contact contact2, ElementMapUnion elementMapUnion, Format format2) throws Exception {
            this.contact = contact2;
            this.format = format2;
            this.union = elementMapUnion;
        }

        public ElementMap[] getAnnotations() {
            return this.union.value();
        }

        public Label getLabel(ElementMap elementMap) {
            return new ElementMapLabel(this.contact, elementMap, this.format);
        }

        public Class getType(ElementMap elementMap) {
            return elementMap.valueType();
        }
    }
}
