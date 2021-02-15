package net.bytebuddy.description.type;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;

public interface PackageDescription extends NamedElement.WithRuntimeName, AnnotationSource {
    public static final String PACKAGE_CLASS_NAME = "package-info";
    public static final int PACKAGE_MODIFIERS = 5632;
    public static final PackageDescription UNDEFINED = null;

    boolean contains(TypeDescription typeDescription);

    public static abstract class AbstractBase implements PackageDescription {
        public String getInternalName() {
            return getName().replace('.', '/');
        }

        public String getActualName() {
            return getName();
        }

        public boolean contains(TypeDescription typeDescription) {
            return equals(typeDescription.getPackage());
        }

        public int hashCode() {
            return getName().hashCode();
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof PackageDescription) && getName().equals(((PackageDescription) obj).getName()));
        }

        public String toString() {
            return "package " + getName();
        }
    }

    public static class Simple extends AbstractBase {
        private final String name;

        public Simple(String str) {
            this.name = str;
        }

        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Empty();
        }

        public String getName() {
            return this.name;
        }
    }

    public static class ForLoadedPackage extends AbstractBase {
        private final Package aPackage;

        public ForLoadedPackage(Package packageR) {
            this.aPackage = packageR;
        }

        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.ForLoadedAnnotations(this.aPackage.getDeclaredAnnotations());
        }

        public String getName() {
            return this.aPackage.getName();
        }
    }
}
