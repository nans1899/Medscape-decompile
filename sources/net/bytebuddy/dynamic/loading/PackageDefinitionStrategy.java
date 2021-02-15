package net.bytebuddy.dynamic.loading;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

public interface PackageDefinitionStrategy {
    Definition define(ClassLoader classLoader, String str, String str2);

    public enum NoOp implements PackageDefinitionStrategy {
        INSTANCE;

        public Definition define(ClassLoader classLoader, String str, String str2) {
            return Definition.Undefined.INSTANCE;
        }
    }

    public enum Trivial implements PackageDefinitionStrategy {
        INSTANCE;

        public Definition define(ClassLoader classLoader, String str, String str2) {
            return Definition.Trivial.INSTANCE;
        }
    }

    public interface Definition {
        String getImplementationTitle();

        String getImplementationVendor();

        String getImplementationVersion();

        URL getSealBase();

        String getSpecificationTitle();

        String getSpecificationVendor();

        String getSpecificationVersion();

        boolean isCompatibleTo(Package packageR);

        boolean isDefined();

        public enum Undefined implements Definition {
            INSTANCE;

            public boolean isDefined() {
                return false;
            }

            public String getSpecificationTitle() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            public String getSpecificationVersion() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            public String getSpecificationVendor() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            public String getImplementationTitle() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            public String getImplementationVersion() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            public String getImplementationVendor() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            public URL getSealBase() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            public boolean isCompatibleTo(Package packageR) {
                throw new IllegalStateException("Cannot check compatibility to undefined package");
            }
        }

        public enum Trivial implements Definition {
            INSTANCE;
            
            private static final URL NOT_SEALED = null;
            private static final String NO_VALUE = null;

            public boolean isCompatibleTo(Package packageR) {
                return true;
            }

            public boolean isDefined() {
                return true;
            }

            static {
                NO_VALUE = null;
                NOT_SEALED = null;
            }

            public String getSpecificationTitle() {
                return NO_VALUE;
            }

            public String getSpecificationVersion() {
                return NO_VALUE;
            }

            public String getSpecificationVendor() {
                return NO_VALUE;
            }

            public String getImplementationTitle() {
                return NO_VALUE;
            }

            public String getImplementationVersion() {
                return NO_VALUE;
            }

            public String getImplementationVendor() {
                return NO_VALUE;
            }

            public URL getSealBase() {
                return NOT_SEALED;
            }
        }

        public static class Simple implements Definition {
            private final String implementationTitle;
            private final String implementationVendor;
            private final String implementationVersion;
            protected final URL sealBase;
            private final String specificationTitle;
            private final String specificationVendor;
            private final String specificationVersion;

            public boolean isDefined() {
                return true;
            }

            public Simple(String str, String str2, String str3, String str4, String str5, String str6, URL url) {
                this.specificationTitle = str;
                this.specificationVersion = str2;
                this.specificationVendor = str3;
                this.implementationTitle = str4;
                this.implementationVersion = str5;
                this.implementationVendor = str6;
                this.sealBase = url;
            }

            public String getSpecificationTitle() {
                return this.specificationTitle;
            }

            public String getSpecificationVersion() {
                return this.specificationVersion;
            }

            public String getSpecificationVendor() {
                return this.specificationVendor;
            }

            public String getImplementationTitle() {
                return this.implementationTitle;
            }

            public String getImplementationVersion() {
                return this.implementationVersion;
            }

            public String getImplementationVendor() {
                return this.implementationVendor;
            }

            public URL getSealBase() {
                return this.sealBase;
            }

            public boolean isCompatibleTo(Package packageR) {
                URL url = this.sealBase;
                if (url == null) {
                    return !packageR.isSealed();
                }
                return packageR.isSealed(url);
            }

            public int hashCode() {
                String str = this.specificationTitle;
                int i = 0;
                int hashCode = (str != null ? str.hashCode() : 0) * 31;
                String str2 = this.specificationVersion;
                int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
                String str3 = this.specificationVendor;
                int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
                String str4 = this.implementationTitle;
                int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
                String str5 = this.implementationVersion;
                int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
                String str6 = this.implementationVendor;
                int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
                URL url = this.sealBase;
                if (url != null) {
                    i = url.hashCode();
                }
                return hashCode6 + i;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Simple simple = (Simple) obj;
                String str = this.specificationTitle;
                if (str == null ? simple.specificationTitle == null : str.equals(simple.specificationTitle)) {
                    String str2 = this.specificationVersion;
                    if (str2 == null ? simple.specificationVersion == null : str2.equals(simple.specificationVersion)) {
                        String str3 = this.specificationVendor;
                        if (str3 == null ? simple.specificationVendor == null : str3.equals(simple.specificationVendor)) {
                            String str4 = this.implementationTitle;
                            if (str4 == null ? simple.implementationTitle == null : str4.equals(simple.implementationTitle)) {
                                String str5 = this.implementationVersion;
                                if (str5 == null ? simple.implementationVersion == null : str5.equals(simple.implementationVersion)) {
                                    String str6 = this.implementationVendor;
                                    if (str6 == null ? simple.implementationVendor == null : str6.equals(simple.implementationVendor)) {
                                        URL url = this.sealBase;
                                        if (url != null) {
                                            if (!url.equals(simple.sealBase)) {
                                                return false;
                                            }
                                            return true;
                                        } else if (simple.sealBase == null) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ManifestReading implements PackageDefinitionStrategy {
        private static final Attributes.Name[] ATTRIBUTE_NAMES = {Attributes.Name.SPECIFICATION_TITLE, Attributes.Name.SPECIFICATION_VERSION, Attributes.Name.SPECIFICATION_VENDOR, Attributes.Name.IMPLEMENTATION_TITLE, Attributes.Name.IMPLEMENTATION_VERSION, Attributes.Name.IMPLEMENTATION_VENDOR, Attributes.Name.SEALED};
        /* access modifiers changed from: private */
        public static final URL NOT_SEALED = null;
        private final SealBaseLocator sealBaseLocator;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sealBaseLocator.equals(((ManifestReading) obj).sealBaseLocator);
        }

        public int hashCode() {
            return 527 + this.sealBaseLocator.hashCode();
        }

        public ManifestReading() {
            this(new SealBaseLocator.ForTypeResourceUrl());
        }

        public ManifestReading(SealBaseLocator sealBaseLocator2) {
            this.sealBaseLocator = sealBaseLocator2;
        }

        public Definition define(ClassLoader classLoader, String str, String str2) {
            InputStream resourceAsStream = classLoader.getResourceAsStream("META-INF/MANIFEST.MF");
            if (resourceAsStream == null) {
                return Definition.Trivial.INSTANCE;
            }
            try {
                Manifest manifest = new Manifest(resourceAsStream);
                HashMap hashMap = new HashMap();
                Attributes mainAttributes = manifest.getMainAttributes();
                if (mainAttributes != null) {
                    for (Attributes.Name name : ATTRIBUTE_NAMES) {
                        hashMap.put(name, mainAttributes.getValue(name));
                    }
                }
                Attributes attributes = manifest.getAttributes(str.replace('.', '/').concat("/"));
                if (attributes != null) {
                    for (Attributes.Name name2 : ATTRIBUTE_NAMES) {
                        String value = attributes.getValue(name2);
                        if (value != null) {
                            hashMap.put(name2, value);
                        }
                    }
                }
                Definition.Simple simple = new Definition.Simple((String) hashMap.get(Attributes.Name.SPECIFICATION_TITLE), (String) hashMap.get(Attributes.Name.SPECIFICATION_VERSION), (String) hashMap.get(Attributes.Name.SPECIFICATION_VENDOR), (String) hashMap.get(Attributes.Name.IMPLEMENTATION_TITLE), (String) hashMap.get(Attributes.Name.IMPLEMENTATION_VERSION), (String) hashMap.get(Attributes.Name.IMPLEMENTATION_VENDOR), Boolean.parseBoolean((String) hashMap.get(Attributes.Name.SEALED)) ? this.sealBaseLocator.findSealBase(classLoader, str2) : NOT_SEALED);
                resourceAsStream.close();
                return simple;
            } catch (IOException e) {
                throw new IllegalStateException("Error while reading manifest file", e);
            } catch (Throwable th) {
                resourceAsStream.close();
                throw th;
            }
        }

        public interface SealBaseLocator {
            URL findSealBase(ClassLoader classLoader, String str);

            public enum NonSealing implements SealBaseLocator {
                INSTANCE;

                public URL findSealBase(ClassLoader classLoader, String str) {
                    return ManifestReading.NOT_SEALED;
                }
            }

            public static class ForFixedValue implements SealBaseLocator {
                private final URL sealBase;

                public ForFixedValue(URL url) {
                    this.sealBase = url;
                }

                public URL findSealBase(ClassLoader classLoader, String str) {
                    return this.sealBase;
                }

                public int hashCode() {
                    return this.sealBase.hashCode();
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    return this.sealBase.equals(((ForFixedValue) obj).sealBase);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForTypeResourceUrl implements SealBaseLocator {
                private static final String CLASS_FILE_EXTENSION = ".class";
                private static final int EXCLUDE_INITIAL_SLASH = 1;
                private static final String FILE_SYSTEM = "file";
                private static final String JAR_FILE = "jar";
                private static final String RUNTIME_IMAGE = "jrt";
                private final SealBaseLocator fallback;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fallback.equals(((ForTypeResourceUrl) obj).fallback);
                }

                public int hashCode() {
                    return 527 + this.fallback.hashCode();
                }

                public ForTypeResourceUrl() {
                    this(NonSealing.INSTANCE);
                }

                public ForTypeResourceUrl(SealBaseLocator sealBaseLocator) {
                    this.fallback = sealBaseLocator;
                }

                public URL findSealBase(ClassLoader classLoader, String str) {
                    URL resource = classLoader.getResource(str.replace('.', '/') + ".class");
                    if (resource != null) {
                        try {
                            if (resource.getProtocol().equals(JAR_FILE)) {
                                return new URL(resource.getPath().substring(0, resource.getPath().indexOf(33)));
                            }
                            if (resource.getProtocol().equals(FILE_SYSTEM)) {
                                return resource;
                            }
                            if (resource.getProtocol().equals(RUNTIME_IMAGE)) {
                                String path = resource.getPath();
                                int indexOf = path.indexOf(47, 1);
                                if (indexOf == -1) {
                                    return resource;
                                }
                                return new URL("jrt:" + path.substring(0, indexOf));
                            }
                        } catch (MalformedURLException e) {
                            throw new IllegalStateException("Unexpected URL: " + resource, e);
                        }
                    }
                    return this.fallback.findSealBase(classLoader, str);
                }
            }
        }
    }
}
