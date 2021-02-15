package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class StringMatcher extends ElementMatcher.Junction.AbstractBase<String> {
    private final Mode mode;
    private final String value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StringMatcher stringMatcher = (StringMatcher) obj;
        return this.mode.equals(stringMatcher.mode) && this.value.equals(stringMatcher.value);
    }

    public int hashCode() {
        return ((527 + this.value.hashCode()) * 31) + this.mode.hashCode();
    }

    public StringMatcher(String str, Mode mode2) {
        this.value = str;
        this.mode = mode2;
    }

    public boolean matches(String str) {
        return this.mode.matches(this.value, str);
    }

    public String toString() {
        return this.mode.getDescription() + ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN + this.value + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }

    public enum Mode {
        EQUALS_FULLY("equals") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.equals(str);
            }
        },
        EQUALS_FULLY_IGNORE_CASE("equalsIgnoreCase") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.equalsIgnoreCase(str);
            }
        },
        STARTS_WITH("startsWith") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.startsWith(str);
            }
        },
        STARTS_WITH_IGNORE_CASE("startsWithIgnoreCase") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.toLowerCase().startsWith(str.toLowerCase());
            }
        },
        ENDS_WITH("endsWith") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.endsWith(str);
            }
        },
        ENDS_WITH_IGNORE_CASE("endsWithIgnoreCase") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.toLowerCase().endsWith(str.toLowerCase());
            }
        },
        CONTAINS("contains") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.contains(str);
            }
        },
        CONTAINS_IGNORE_CASE("containsIgnoreCase") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.toLowerCase().contains(str.toLowerCase());
            }
        },
        MATCHES("matches") {
            /* access modifiers changed from: protected */
            public boolean matches(String str, String str2) {
                return str2.matches(str);
            }
        };
        
        private final String description;

        /* access modifiers changed from: protected */
        public abstract boolean matches(String str, String str2);

        private Mode(String str) {
            this.description = str;
        }

        /* access modifiers changed from: protected */
        public String getDescription() {
            return this.description;
        }
    }
}
