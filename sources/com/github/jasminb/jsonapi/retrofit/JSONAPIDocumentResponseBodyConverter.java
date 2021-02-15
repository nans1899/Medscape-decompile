package com.github.jasminb.jsonapi.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class JSONAPIDocumentResponseBodyConverter<T> implements Converter<ResponseBody, JSONAPIDocument<T>> {
    private final Class<?> clazz;
    private final Boolean isCollection;
    private final ResourceConverter parser;

    public JSONAPIDocumentResponseBodyConverter(ResourceConverter resourceConverter, Class<?> cls, boolean z) {
        this.clazz = cls;
        this.isCollection = Boolean.valueOf(z);
        this.parser = resourceConverter;
    }

    public JSONAPIDocument<T> convert(ResponseBody responseBody) throws IOException {
        if (this.isCollection.booleanValue()) {
            return this.parser.readDocumentCollection(responseBody.byteStream(), this.clazz);
        }
        return this.parser.readDocument(responseBody.byteStream(), this.clazz);
    }
}
