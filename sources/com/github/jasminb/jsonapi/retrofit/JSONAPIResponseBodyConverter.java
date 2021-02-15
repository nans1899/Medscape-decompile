package com.github.jasminb.jsonapi.retrofit;

import com.github.jasminb.jsonapi.ResourceConverter;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class JSONAPIResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Class<?> clazz;
    private final Boolean isCollection;
    private final ResourceConverter parser;

    public JSONAPIResponseBodyConverter(ResourceConverter resourceConverter, Class<?> cls, boolean z) {
        this.clazz = cls;
        this.isCollection = Boolean.valueOf(z);
        this.parser = resourceConverter;
    }

    public T convert(ResponseBody responseBody) throws IOException {
        if (this.isCollection.booleanValue()) {
            return this.parser.readDocumentCollection(responseBody.byteStream(), this.clazz).get();
        }
        return this.parser.readDocument(responseBody.byteStream(), this.clazz).get();
    }
}
