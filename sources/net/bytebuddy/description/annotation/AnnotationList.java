package net.bytebuddy.description.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.FilterableList;

public interface AnnotationList extends FilterableList<AnnotationDescription, AnnotationList> {
    TypeList asTypeList();

    AnnotationList inherited(Set<? extends TypeDescription> set);

    boolean isAnnotationPresent(Class<? extends Annotation> cls);

    boolean isAnnotationPresent(TypeDescription typeDescription);

    <T extends Annotation> AnnotationDescription.Loadable<T> ofType(Class<T> cls);

    AnnotationDescription ofType(TypeDescription typeDescription);

    AnnotationList visibility(ElementMatcher<? super RetentionPolicy> elementMatcher);

    public static abstract class AbstractBase extends FilterableList.AbstractBase<AnnotationDescription, AnnotationList> implements AnnotationList {
        public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
            Iterator it = iterator();
            while (it.hasNext()) {
                if (((AnnotationDescription) it.next()).getAnnotationType().represents(cls)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isAnnotationPresent(TypeDescription typeDescription) {
            Iterator it = iterator();
            while (it.hasNext()) {
                if (((AnnotationDescription) it.next()).getAnnotationType().equals(typeDescription)) {
                    return true;
                }
            }
            return false;
        }

        public <T extends Annotation> AnnotationDescription.Loadable<T> ofType(Class<T> cls) {
            Iterator it = iterator();
            while (it.hasNext()) {
                AnnotationDescription annotationDescription = (AnnotationDescription) it.next();
                if (annotationDescription.getAnnotationType().represents(cls)) {
                    return annotationDescription.prepare(cls);
                }
            }
            return AnnotationDescription.UNDEFINED;
        }

        public AnnotationDescription ofType(TypeDescription typeDescription) {
            Iterator it = iterator();
            while (it.hasNext()) {
                AnnotationDescription annotationDescription = (AnnotationDescription) it.next();
                if (annotationDescription.getAnnotationType().equals(typeDescription)) {
                    return annotationDescription;
                }
            }
            return AnnotationDescription.UNDEFINED;
        }

        public AnnotationList inherited(Set<? extends TypeDescription> set) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                AnnotationDescription annotationDescription = (AnnotationDescription) it.next();
                if (!set.contains(annotationDescription.getAnnotationType()) && annotationDescription.isInherited()) {
                    arrayList.add(annotationDescription);
                }
            }
            return wrap((List<AnnotationDescription>) arrayList);
        }

        public AnnotationList visibility(ElementMatcher<? super RetentionPolicy> elementMatcher) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                AnnotationDescription annotationDescription = (AnnotationDescription) it.next();
                if (elementMatcher.matches(annotationDescription.getRetention())) {
                    arrayList.add(annotationDescription);
                }
            }
            return wrap((List<AnnotationDescription>) arrayList);
        }

        public TypeList asTypeList() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((AnnotationDescription) it.next()).getAnnotationType());
            }
            return new TypeList.Explicit((List<? extends TypeDescription>) arrayList);
        }

        /* access modifiers changed from: protected */
        public AnnotationList wrap(List<AnnotationDescription> list) {
            return new Explicit((List<? extends AnnotationDescription>) list);
        }
    }

    public static class ForLoadedAnnotations extends AbstractBase {
        private final List<? extends Annotation> annotations;

        public ForLoadedAnnotations(Annotation... annotationArr) {
            this((List<? extends Annotation>) Arrays.asList(annotationArr));
        }

        public ForLoadedAnnotations(List<? extends Annotation> list) {
            this.annotations = list;
        }

        public static List<AnnotationList> asList(Annotation[][] annotationArr) {
            ArrayList arrayList = new ArrayList(annotationArr.length);
            for (Annotation[] forLoadedAnnotations : annotationArr) {
                arrayList.add(new ForLoadedAnnotations(forLoadedAnnotations));
            }
            return arrayList;
        }

        public AnnotationDescription get(int i) {
            return AnnotationDescription.ForLoadedAnnotation.of((Annotation) this.annotations.get(i));
        }

        public int size() {
            return this.annotations.size();
        }
    }

    public static class Explicit extends AbstractBase {
        private final List<? extends AnnotationDescription> annotationDescriptions;

        public Explicit(AnnotationDescription... annotationDescriptionArr) {
            this((List<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
        }

        public Explicit(List<? extends AnnotationDescription> list) {
            this.annotationDescriptions = list;
        }

        public static List<AnnotationList> asList(List<? extends List<? extends AnnotationDescription>> list) {
            ArrayList arrayList = new ArrayList(list.size());
            for (List explicit : list) {
                arrayList.add(new Explicit((List<? extends AnnotationDescription>) explicit));
            }
            return arrayList;
        }

        public AnnotationDescription get(int i) {
            return (AnnotationDescription) this.annotationDescriptions.get(i);
        }

        public int size() {
            return this.annotationDescriptions.size();
        }
    }

    public static class Empty extends FilterableList.Empty<AnnotationDescription, AnnotationList> implements AnnotationList {
        public AnnotationList inherited(Set<? extends TypeDescription> set) {
            return this;
        }

        public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
            return false;
        }

        public boolean isAnnotationPresent(TypeDescription typeDescription) {
            return false;
        }

        public AnnotationList visibility(ElementMatcher<? super RetentionPolicy> elementMatcher) {
            return this;
        }

        public static List<AnnotationList> asList(int i) {
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(new Empty());
            }
            return arrayList;
        }

        public <T extends Annotation> AnnotationDescription.Loadable<T> ofType(Class<T> cls) {
            return AnnotationDescription.UNDEFINED;
        }

        public AnnotationDescription ofType(TypeDescription typeDescription) {
            return AnnotationDescription.UNDEFINED;
        }

        public TypeList asTypeList() {
            return new TypeList.Empty();
        }
    }
}
