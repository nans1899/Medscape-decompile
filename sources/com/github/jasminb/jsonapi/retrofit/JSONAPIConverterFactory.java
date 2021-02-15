package com.github.jasminb.jsonapi.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class JSONAPIConverterFactory extends Converter.Factory {
    private Converter.Factory alternativeFactory;
    private ResourceConverter deserializer;
    private ResourceConverter serializer;

    public JSONAPIConverterFactory(ResourceConverter resourceConverter) {
        this.deserializer = resourceConverter;
        this.serializer = resourceConverter;
    }

    public JSONAPIConverterFactory(ResourceConverter resourceConverter, ResourceConverter resourceConverter2) {
        this.deserializer = resourceConverter;
        this.serializer = resourceConverter2;
    }

    public JSONAPIConverterFactory(ObjectMapper objectMapper, Class<?>... clsArr) {
        ResourceConverter resourceConverter = new ResourceConverter(objectMapper, clsArr);
        this.deserializer = resourceConverter;
        this.serializer = resourceConverter;
    }

    public void setAlternativeFactory(Converter.Factory factory) {
        this.alternativeFactory = factory;
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        RetrofitType retrofitType = new RetrofitType(type);
        if (!retrofitType.isValid() || !this.deserializer.isRegisteredType(retrofitType.getType())) {
            Converter.Factory factory = this.alternativeFactory;
            if (factory != null) {
                return factory.responseBodyConverter(type, annotationArr, retrofit);
            }
            return null;
        } else if (retrofitType.isJSONAPIDocumentType()) {
            return new JSONAPIDocumentResponseBodyConverter(this.deserializer, retrofitType.getType(), retrofitType.isCollection());
        } else {
            return new JSONAPIResponseBodyConverter(this.deserializer, retrofitType.getType(), retrofitType.isCollection());
        }
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        RetrofitType retrofitType = new RetrofitType(type);
        if (retrofitType.isValid() && this.serializer.isRegisteredType(retrofitType.getType())) {
            return new JSONAPIRequestBodyConverter(this.serializer);
        }
        Converter.Factory factory = this.alternativeFactory;
        if (factory != null) {
            return factory.requestBodyConverter(type, annotationArr, annotationArr2, retrofit);
        }
        return null;
    }
}
