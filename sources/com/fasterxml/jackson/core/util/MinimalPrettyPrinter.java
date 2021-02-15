package com.fasterxml.jackson.core.util;

import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import java.io.IOException;
import java.io.Serializable;
import net.bytebuddy.pool.TypePool;

public class MinimalPrettyPrinter implements PrettyPrinter, Serializable {
    public static final String DEFAULT_ROOT_VALUE_SEPARATOR = " ";
    private static final long serialVersionUID = 1;
    protected String _rootValueSeparator;

    public void beforeArrayValues(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
    }

    public void beforeObjectEntries(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
    }

    public MinimalPrettyPrinter() {
        this(DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public MinimalPrettyPrinter(String str) {
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._rootValueSeparator = str;
    }

    public void setRootValueSeparator(String str) {
        this._rootValueSeparator = str;
    }

    public void writeRootValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        String str = this._rootValueSeparator;
        if (str != null) {
            jsonGenerator.writeRaw(str);
        }
    }

    public void writeStartObject(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw((char) ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN);
    }

    public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw((char) ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
    }

    public void writeObjectEntrySeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw((char) ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
    }

    public void writeEndObject(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw((char) ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
    }

    public void writeStartArray(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
    }

    public void writeArrayValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw((char) ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
    }

    public void writeEndArray(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(']');
    }
}
