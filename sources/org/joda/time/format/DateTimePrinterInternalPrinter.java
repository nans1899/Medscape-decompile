package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimePrinterInternalPrinter implements InternalPrinter {
    private final DateTimePrinter underlying;

    static InternalPrinter of(DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter instanceof InternalPrinterDateTimePrinter) {
            return (InternalPrinter) dateTimePrinter;
        }
        if (dateTimePrinter == null) {
            return null;
        }
        return new DateTimePrinterInternalPrinter(dateTimePrinter);
    }

    private DateTimePrinterInternalPrinter(DateTimePrinter dateTimePrinter) {
        this.underlying = dateTimePrinter;
    }

    /* access modifiers changed from: package-private */
    public DateTimePrinter getUnderlying() {
        return this.underlying;
    }

    public int estimatePrintedLength() {
        return this.underlying.estimatePrintedLength();
    }

    public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
        Appendable appendable2 = appendable;
        if (appendable2 instanceof StringBuffer) {
            this.underlying.printTo((StringBuffer) appendable2, j, chronology, i, dateTimeZone, locale);
        } else if (appendable2 instanceof Writer) {
            this.underlying.printTo((Writer) appendable2, j, chronology, i, dateTimeZone, locale);
        } else {
            StringBuffer stringBuffer = new StringBuffer(estimatePrintedLength());
            this.underlying.printTo(stringBuffer, j, chronology, i, dateTimeZone, locale);
            appendable.append(stringBuffer);
        }
    }

    public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        if (appendable instanceof StringBuffer) {
            this.underlying.printTo((StringBuffer) appendable, readablePartial, locale);
        } else if (appendable instanceof Writer) {
            this.underlying.printTo((Writer) appendable, readablePartial, locale);
        } else {
            StringBuffer stringBuffer = new StringBuffer(estimatePrintedLength());
            this.underlying.printTo(stringBuffer, readablePartial, locale);
            appendable.append(stringBuffer);
        }
    }
}
