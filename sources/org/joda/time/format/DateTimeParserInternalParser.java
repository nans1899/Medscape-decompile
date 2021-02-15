package org.joda.time.format;

class DateTimeParserInternalParser implements InternalParser {
    private final DateTimeParser underlying;

    static InternalParser of(DateTimeParser dateTimeParser) {
        if (dateTimeParser instanceof InternalParserDateTimeParser) {
            return (InternalParser) dateTimeParser;
        }
        if (dateTimeParser == null) {
            return null;
        }
        return new DateTimeParserInternalParser(dateTimeParser);
    }

    private DateTimeParserInternalParser(DateTimeParser dateTimeParser) {
        this.underlying = dateTimeParser;
    }

    /* access modifiers changed from: package-private */
    public DateTimeParser getUnderlying() {
        return this.underlying;
    }

    public int estimateParsedLength() {
        return this.underlying.estimateParsedLength();
    }

    public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
        return this.underlying.parseInto(dateTimeParserBucket, charSequence.toString(), i);
    }
}
