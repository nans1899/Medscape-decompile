package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.TimeZone;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormat {
    public static final String DEFAULT_LOCALE = "##default";
    public static final String DEFAULT_TIMEZONE = "##default";

    public enum Feature {
        ACCEPT_SINGLE_VALUE_AS_ARRAY,
        WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
        WRITE_DATES_WITH_ZONE_ID,
        WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED,
        WRITE_SORTED_MAP_ENTRIES
    }

    String locale() default "##default";

    String pattern() default "";

    Shape shape() default Shape.ANY;

    String timezone() default "##default";

    Feature[] with() default {};

    Feature[] without() default {};

    public enum Shape {
        ANY,
        SCALAR,
        ARRAY,
        OBJECT,
        NUMBER,
        NUMBER_FLOAT,
        NUMBER_INT,
        STRING,
        BOOLEAN;

        public boolean isNumeric() {
            return this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT;
        }

        public boolean isStructured() {
            return this == OBJECT || this == ARRAY;
        }
    }

    public static class Features {
        private static final Features EMPTY = new Features(0, 0);
        private final int _disabled;
        private final int _enabled;

        private Features(int i, int i2) {
            this._enabled = i;
            this._disabled = i2;
        }

        public static Features empty() {
            return EMPTY;
        }

        public static Features construct(JsonFormat jsonFormat) {
            return construct(jsonFormat.with(), jsonFormat.without());
        }

        public static Features construct(Feature[] featureArr, Feature[] featureArr2) {
            int i = 0;
            for (Feature ordinal : featureArr) {
                i |= 1 << ordinal.ordinal();
            }
            int i2 = 0;
            for (Feature ordinal2 : featureArr2) {
                i2 |= 1 << ordinal2.ordinal();
            }
            return new Features(i, i2);
        }

        public Features withOverrides(Features features) {
            if (features == null) {
                return this;
            }
            int i = features._disabled;
            int i2 = features._enabled;
            if (i == 0 && i2 == 0) {
                return this;
            }
            if (this._enabled == 0 && this._disabled == 0) {
                return features;
            }
            int i3 = this._enabled;
            int i4 = ((~i) & i3) | i2;
            int i5 = this._disabled;
            int i6 = i | ((~i2) & i5);
            if (i4 == i3 && i6 == i5) {
                return this;
            }
            return new Features(i4, i6);
        }

        public Features with(Feature... featureArr) {
            int i = this._enabled;
            for (Feature ordinal : featureArr) {
                i |= 1 << ordinal.ordinal();
            }
            return i == this._enabled ? this : new Features(i, this._disabled);
        }

        public Features without(Feature... featureArr) {
            int i = this._disabled;
            for (Feature ordinal : featureArr) {
                i |= 1 << ordinal.ordinal();
            }
            return i == this._disabled ? this : new Features(this._enabled, i);
        }

        public Boolean get(Feature feature) {
            int ordinal = 1 << feature.ordinal();
            if ((this._disabled & ordinal) != 0) {
                return Boolean.FALSE;
            }
            if ((ordinal & this._enabled) != 0) {
                return Boolean.TRUE;
            }
            return null;
        }

        public int hashCode() {
            return this._disabled + this._enabled;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            Features features = (Features) obj;
            if (features._enabled == this._enabled && features._disabled == this._disabled) {
                return true;
            }
            return false;
        }
    }

    public static class Value implements JacksonAnnotationValue<JsonFormat>, Serializable {
        private static final Value EMPTY = new Value();
        private static final long serialVersionUID = 1;
        private final Features _features;
        private final Locale _locale;
        private final String _pattern;
        private final Shape _shape;
        private transient TimeZone _timezone;
        private final String _timezoneStr;

        public Value() {
            this("", Shape.ANY, "", "", Features.empty());
        }

        public Value(JsonFormat jsonFormat) {
            this(jsonFormat.pattern(), jsonFormat.shape(), jsonFormat.locale(), jsonFormat.timezone(), Features.construct(jsonFormat));
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Value(String str, Shape shape, String str2, String str3, Features features) {
            this(str, shape, (str2 == null || str2.length() == 0 || "##default".equals(str2)) ? null : new Locale(str2), (str3 == null || str3.length() == 0 || "##default".equals(str3)) ? null : str3, (TimeZone) null, features);
        }

        public Value(String str, Shape shape, Locale locale, TimeZone timeZone, Features features) {
            this._pattern = str;
            this._shape = shape == null ? Shape.ANY : shape;
            this._locale = locale;
            this._timezone = timeZone;
            this._timezoneStr = null;
            this._features = features == null ? Features.empty() : features;
        }

        public Value(String str, Shape shape, Locale locale, String str2, TimeZone timeZone, Features features) {
            this._pattern = str;
            this._shape = shape == null ? Shape.ANY : shape;
            this._locale = locale;
            this._timezone = timeZone;
            this._timezoneStr = str2;
            this._features = features == null ? Features.empty() : features;
        }

        @Deprecated
        public Value(String str, Shape shape, Locale locale, TimeZone timeZone) {
            this(str, shape, locale, timeZone, Features.empty());
        }

        @Deprecated
        public Value(String str, Shape shape, String str2, String str3) {
            this(str, shape, str2, str3, Features.empty());
        }

        @Deprecated
        public Value(String str, Shape shape, Locale locale, String str2, TimeZone timeZone) {
            this(str, shape, locale, str2, timeZone, Features.empty());
        }

        public static final Value empty() {
            return EMPTY;
        }

        public static final Value from(JsonFormat jsonFormat) {
            if (jsonFormat == null) {
                return null;
            }
            return new Value(jsonFormat);
        }

        public final Value withOverrides(Value value) {
            Value value2;
            Features features;
            TimeZone timeZone;
            String str;
            if (value == null || value == (value2 = EMPTY)) {
                return this;
            }
            if (this == value2) {
                return value;
            }
            String str2 = value._pattern;
            if (str2 == null || str2.isEmpty()) {
                str2 = this._pattern;
            }
            String str3 = str2;
            Shape shape = value._shape;
            if (shape == Shape.ANY) {
                shape = this._shape;
            }
            Shape shape2 = shape;
            Locale locale = value._locale;
            if (locale == null) {
                locale = this._locale;
            }
            Locale locale2 = locale;
            Features features2 = this._features;
            if (features2 == null) {
                features = value._features;
            } else {
                features = features2.withOverrides(value._features);
            }
            Features features3 = features;
            String str4 = value._timezoneStr;
            if (str4 == null || str4.isEmpty()) {
                str = this._timezoneStr;
                timeZone = this._timezone;
            } else {
                timeZone = value._timezone;
                str = str4;
            }
            return new Value(str3, shape2, locale2, str, timeZone, features3);
        }

        public static Value forPattern(String str) {
            return new Value(str, (Shape) null, (Locale) null, (String) null, (TimeZone) null, Features.empty());
        }

        public static Value forShape(Shape shape) {
            return new Value((String) null, shape, (Locale) null, (String) null, (TimeZone) null, Features.empty());
        }

        public Value withPattern(String str) {
            return new Value(str, this._shape, this._locale, this._timezoneStr, this._timezone, this._features);
        }

        public Value withShape(Shape shape) {
            return new Value(this._pattern, shape, this._locale, this._timezoneStr, this._timezone, this._features);
        }

        public Value withLocale(Locale locale) {
            return new Value(this._pattern, this._shape, locale, this._timezoneStr, this._timezone, this._features);
        }

        public Value withTimeZone(TimeZone timeZone) {
            return new Value(this._pattern, this._shape, this._locale, (String) null, timeZone, this._features);
        }

        public Value withFeature(Feature feature) {
            Features with = this._features.with(feature);
            return with == this._features ? this : new Value(this._pattern, this._shape, this._locale, this._timezoneStr, this._timezone, with);
        }

        public Value withoutFeature(Feature feature) {
            Features without = this._features.without(feature);
            return without == this._features ? this : new Value(this._pattern, this._shape, this._locale, this._timezoneStr, this._timezone, without);
        }

        public Class<JsonFormat> valueFor() {
            return JsonFormat.class;
        }

        public String getPattern() {
            return this._pattern;
        }

        public Shape getShape() {
            return this._shape;
        }

        public Locale getLocale() {
            return this._locale;
        }

        public String timeZoneAsString() {
            TimeZone timeZone = this._timezone;
            if (timeZone != null) {
                return timeZone.getID();
            }
            return this._timezoneStr;
        }

        public TimeZone getTimeZone() {
            TimeZone timeZone = this._timezone;
            if (timeZone != null) {
                return timeZone;
            }
            String str = this._timezoneStr;
            if (str == null) {
                return null;
            }
            TimeZone timeZone2 = TimeZone.getTimeZone(str);
            this._timezone = timeZone2;
            return timeZone2;
        }

        public boolean hasShape() {
            return this._shape != Shape.ANY;
        }

        public boolean hasPattern() {
            String str = this._pattern;
            return str != null && str.length() > 0;
        }

        public boolean hasLocale() {
            return this._locale != null;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r0 = r1._timezoneStr;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean hasTimeZone() {
            /*
                r1 = this;
                java.util.TimeZone r0 = r1._timezone
                if (r0 != 0) goto L_0x0011
                java.lang.String r0 = r1._timezoneStr
                if (r0 == 0) goto L_0x000f
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x000f
                goto L_0x0011
            L_0x000f:
                r0 = 0
                goto L_0x0012
            L_0x0011:
                r0 = 1
            L_0x0012:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.annotation.JsonFormat.Value.hasTimeZone():boolean");
        }

        public Boolean getFeature(Feature feature) {
            return this._features.get(feature);
        }

        public String toString() {
            return String.format("[pattern=%s,shape=%s,locale=%s,timezone=%s]", new Object[]{this._pattern, this._shape, this._locale, this._timezoneStr});
        }

        public int hashCode() {
            String str = this._timezoneStr;
            int hashCode = str == null ? 1 : str.hashCode();
            String str2 = this._pattern;
            if (str2 != null) {
                hashCode ^= str2.hashCode();
            }
            int hashCode2 = hashCode + this._shape.hashCode();
            Locale locale = this._locale;
            if (locale != null) {
                hashCode2 ^= locale.hashCode();
            }
            return hashCode2 + this._features.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            Value value = (Value) obj;
            if (this._shape != value._shape || !this._features.equals(value._features) || !_equal(this._timezoneStr, value._timezoneStr) || !_equal(this._pattern, value._pattern) || !_equal(this._timezone, value._timezone) || !_equal(this._locale, value._locale)) {
                return false;
            }
            return true;
        }

        private static <T> boolean _equal(T t, T t2) {
            if (t == null) {
                return t2 == null;
            }
            if (t2 == null) {
                return false;
            }
            return t.equals(t2);
        }
    }
}
