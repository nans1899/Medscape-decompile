package com.github.jasminb.jsonapi.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class JSONAPIRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private final ResourceConverter converter;

    public JSONAPIRequestBodyConverter(ResourceConverter resourceConverter) {
        this.converter = resourceConverter;
    }

    public RequestBody convert(T t) throws IOException {
        JSONAPIDocument jSONAPIDocument;
        boolean z;
        try {
            MediaType parse = MediaType.parse("application/vnd.api+json");
            if (t instanceof JSONAPIDocument) {
                jSONAPIDocument = (JSONAPIDocument) t;
                z = Iterable.class.isAssignableFrom(jSONAPIDocument.get().getClass());
            } else {
                JSONAPIDocument jSONAPIDocument2 = new JSONAPIDocument(t);
                z = Iterable.class.isAssignableFrom(t.getClass());
                jSONAPIDocument = jSONAPIDocument2;
            }
            if (z) {
                return RequestBody.create(parse, this.converter.writeDocumentCollection(jSONAPIDocument));
            }
            return RequestBody.create(parse, this.converter.writeDocument(jSONAPIDocument));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
