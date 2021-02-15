package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

public class DateDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    static {
        Class[] clsArr = {Calendar.class, GregorianCalendar.class, Date.class, java.util.Date.class, Timestamp.class};
        for (int i = 0; i < 5; i++) {
            _classNames.add(clsArr[i].getName());
        }
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (!_classNames.contains(str)) {
            return null;
        }
        if (cls == Calendar.class) {
            return new CalendarDeserializer();
        }
        if (cls == java.util.Date.class) {
            return DateDeserializer.instance;
        }
        if (cls == Date.class) {
            return new SqlDateDeserializer();
        }
        if (cls == Timestamp.class) {
            return new TimestampDeserializer();
        }
        if (cls == GregorianCalendar.class) {
            return new CalendarDeserializer(GregorianCalendar.class);
        }
        return null;
    }

    protected static abstract class DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements ContextualDeserializer {
        protected final DateFormat _customFormat;
        protected final String _formatString;

        /* access modifiers changed from: protected */
        public abstract DateBasedDeserializer<T> withDateFormat(DateFormat dateFormat, String str);

        protected DateBasedDeserializer(Class<?> cls) {
            super(cls);
            this._customFormat = null;
            this._formatString = null;
        }

        protected DateBasedDeserializer(DateBasedDeserializer<T> dateBasedDeserializer, DateFormat dateFormat, String str) {
            super((Class<?>) dateBasedDeserializer._valueClass);
            this._customFormat = dateFormat;
            this._formatString = str;
        }

        public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            JsonFormat.Value findFormat;
            DateFormat dateFormat;
            if (!(beanProperty == null || (findFormat = deserializationContext.getAnnotationIntrospector().findFormat(beanProperty.getMember())) == null)) {
                TimeZone timeZone = findFormat.getTimeZone();
                if (findFormat.hasPattern()) {
                    String pattern = findFormat.getPattern();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, findFormat.hasLocale() ? findFormat.getLocale() : deserializationContext.getLocale());
                    if (timeZone == null) {
                        timeZone = deserializationContext.getTimeZone();
                    }
                    simpleDateFormat.setTimeZone(timeZone);
                    return withDateFormat(simpleDateFormat, pattern);
                } else if (timeZone != null) {
                    DateFormat dateFormat2 = deserializationContext.getConfig().getDateFormat();
                    if (dateFormat2.getClass() == StdDateFormat.class) {
                        dateFormat = ((StdDateFormat) dateFormat2).withTimeZone(timeZone).withLocale(findFormat.hasLocale() ? findFormat.getLocale() : deserializationContext.getLocale());
                    } else {
                        dateFormat = (DateFormat) dateFormat2.clone();
                        dateFormat.setTimeZone(timeZone);
                    }
                    return withDateFormat(dateFormat, this._formatString);
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public java.util.Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            java.util.Date parse;
            if (this._customFormat != null) {
                JsonToken currentToken = jsonParser.getCurrentToken();
                if (currentToken == JsonToken.VALUE_STRING) {
                    String trim = jsonParser.getText().trim();
                    if (trim.length() == 0) {
                        return (java.util.Date) getEmptyValue(deserializationContext);
                    }
                    synchronized (this._customFormat) {
                        try {
                            parse = this._customFormat.parse(trim);
                        } catch (ParseException e) {
                            throw new IllegalArgumentException("Failed to parse Date value '" + trim + "' (format: \"" + this._formatString + "\"): " + e.getMessage());
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return parse;
                } else if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                    jsonParser.nextToken();
                    java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext);
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        return _parseDate;
                    }
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'java.util.Date' value but there was more than a single value in the array");
                }
            }
            return super._parseDate(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class CalendarDeserializer extends DateBasedDeserializer<Calendar> {
        protected final Class<? extends Calendar> _calendarClass;

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        public CalendarDeserializer() {
            super(Calendar.class);
            this._calendarClass = null;
        }

        public CalendarDeserializer(Class<? extends Calendar> cls) {
            super(cls);
            this._calendarClass = cls;
        }

        public CalendarDeserializer(CalendarDeserializer calendarDeserializer, DateFormat dateFormat, String str) {
            super(calendarDeserializer, dateFormat, str);
            this._calendarClass = calendarDeserializer._calendarClass;
        }

        /* access modifiers changed from: protected */
        public CalendarDeserializer withDateFormat(DateFormat dateFormat, String str) {
            return new CalendarDeserializer(this, dateFormat, str);
        }

        public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            Class<? extends Calendar> cls = this._calendarClass;
            if (cls == null) {
                return deserializationContext.constructCalendar(_parseDate);
            }
            try {
                Calendar calendar = (Calendar) cls.newInstance();
                calendar.setTimeInMillis(_parseDate.getTime());
                TimeZone timeZone = deserializationContext.getTimeZone();
                if (timeZone != null) {
                    calendar.setTimeZone(timeZone);
                }
                return calendar;
            } catch (Exception e) {
                throw deserializationContext.instantiationException((Class<?>) this._calendarClass, (Throwable) e);
            }
        }
    }

    public static class DateDeserializer extends DateBasedDeserializer<java.util.Date> {
        public static final DateDeserializer instance = new DateDeserializer();

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        public DateDeserializer() {
            super(java.util.Date.class);
        }

        public DateDeserializer(DateDeserializer dateDeserializer, DateFormat dateFormat, String str) {
            super(dateDeserializer, dateFormat, str);
        }

        /* access modifiers changed from: protected */
        public DateDeserializer withDateFormat(DateFormat dateFormat, String str) {
            return new DateDeserializer(this, dateFormat, str);
        }

        public java.util.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return _parseDate(jsonParser, deserializationContext);
        }
    }

    public static class SqlDateDeserializer extends DateBasedDeserializer<Date> {
        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        public SqlDateDeserializer() {
            super(Date.class);
        }

        public SqlDateDeserializer(SqlDateDeserializer sqlDateDeserializer, DateFormat dateFormat, String str) {
            super(sqlDateDeserializer, dateFormat, str);
        }

        /* access modifiers changed from: protected */
        public SqlDateDeserializer withDateFormat(DateFormat dateFormat, String str) {
            return new SqlDateDeserializer(this, dateFormat, str);
        }

        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            return new Date(_parseDate.getTime());
        }
    }

    public static class TimestampDeserializer extends DateBasedDeserializer<Timestamp> {
        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        public TimestampDeserializer() {
            super(Timestamp.class);
        }

        public TimestampDeserializer(TimestampDeserializer timestampDeserializer, DateFormat dateFormat, String str) {
            super(timestampDeserializer, dateFormat, str);
        }

        /* access modifiers changed from: protected */
        public TimestampDeserializer withDateFormat(DateFormat dateFormat, String str) {
            return new TimestampDeserializer(this, dateFormat, str);
        }

        public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new Timestamp(_parseDate(jsonParser, deserializationContext).getTime());
        }
    }
}
