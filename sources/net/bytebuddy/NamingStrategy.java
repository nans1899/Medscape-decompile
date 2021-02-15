package net.bytebuddy;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.utility.RandomString;

public interface NamingStrategy {
    String rebase(TypeDescription typeDescription);

    String redefine(TypeDescription typeDescription);

    String subclass(TypeDescription.Generic generic);

    public static abstract class AbstractBase implements NamingStrategy {
        /* access modifiers changed from: protected */
        public abstract String name(TypeDescription typeDescription);

        public String subclass(TypeDescription.Generic generic) {
            return name(generic.asErasure());
        }

        public String redefine(TypeDescription typeDescription) {
            return typeDescription.getName();
        }

        public String rebase(TypeDescription typeDescription) {
            return typeDescription.getName();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class SuffixingRandom extends AbstractBase {
        public static final String BYTE_BUDDY_RENAME_PACKAGE = "net.bytebuddy.renamed";
        private static final String JAVA_PACKAGE = "java.";
        public static final String NO_PREFIX = "";
        private final BaseNameResolver baseNameResolver;
        private final String javaLangPackagePrefix;
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
        private final RandomString randomString;
        private final String suffix;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SuffixingRandom suffixingRandom = (SuffixingRandom) obj;
            return this.suffix.equals(suffixingRandom.suffix) && this.javaLangPackagePrefix.equals(suffixingRandom.javaLangPackagePrefix) && this.baseNameResolver.equals(suffixingRandom.baseNameResolver);
        }

        public int hashCode() {
            return ((((527 + this.suffix.hashCode()) * 31) + this.javaLangPackagePrefix.hashCode()) * 31) + this.baseNameResolver.hashCode();
        }

        public SuffixingRandom(String str) {
            this(str, (BaseNameResolver) BaseNameResolver.ForUnnamedType.INSTANCE);
        }

        public SuffixingRandom(String str, String str2) {
            this(str, BaseNameResolver.ForUnnamedType.INSTANCE, str2);
        }

        public SuffixingRandom(String str, BaseNameResolver baseNameResolver2) {
            this(str, baseNameResolver2, BYTE_BUDDY_RENAME_PACKAGE);
        }

        public SuffixingRandom(String str, BaseNameResolver baseNameResolver2, String str2) {
            this.suffix = str;
            this.baseNameResolver = baseNameResolver2;
            this.javaLangPackagePrefix = str2;
            this.randomString = new RandomString();
        }

        /* access modifiers changed from: protected */
        public String name(TypeDescription typeDescription) {
            String resolve = this.baseNameResolver.resolve(typeDescription);
            if (resolve.startsWith(JAVA_PACKAGE) && !this.javaLangPackagePrefix.equals("")) {
                resolve = this.javaLangPackagePrefix + "." + resolve;
            }
            return resolve + "$" + this.suffix + "$" + this.randomString.nextString();
        }

        public interface BaseNameResolver {
            String resolve(TypeDescription typeDescription);

            public enum ForUnnamedType implements BaseNameResolver {
                INSTANCE;

                public String resolve(TypeDescription typeDescription) {
                    return typeDescription.getName();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForGivenType implements BaseNameResolver {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForGivenType) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                public ForGivenType(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public String resolve(TypeDescription typeDescription2) {
                    return this.typeDescription.getName();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForFixedValue implements BaseNameResolver {
                private final String name;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.name.equals(((ForFixedValue) obj).name);
                }

                public int hashCode() {
                    return 527 + this.name.hashCode();
                }

                public ForFixedValue(String str) {
                    this.name = str;
                }

                public String resolve(TypeDescription typeDescription) {
                    return this.name;
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class PrefixingRandom extends AbstractBase {
        private final String prefix;
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
        private final RandomString randomString = new RandomString();

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.prefix.equals(((PrefixingRandom) obj).prefix);
        }

        public int hashCode() {
            return 527 + this.prefix.hashCode();
        }

        public PrefixingRandom(String str) {
            this.prefix = str;
        }

        /* access modifiers changed from: protected */
        public String name(TypeDescription typeDescription) {
            return this.prefix + "." + typeDescription.getName() + "$" + this.randomString.nextString();
        }
    }
}
