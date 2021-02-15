package com.wbmd.qxcalculator.model.contentItems.common;

import android.util.Log;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.LongIdHandler;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import java.io.InputStream;
import java.util.ArrayList;

@Type("password_reset")
public class PasswordReset {
    public String email;
    @Id(LongIdHandler.class)
    public Long identifier;

    public String convertToJsonApiString() {
        try {
            return new String(new ResourceConverter(new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true), (Class<?>[]) new Class[]{PasswordReset.class}).writeDocument(new JSONAPIDocument(this)));
        } catch (Exception e) {
            Log.d("chan", "error " + e);
            return null;
        }
    }

    public static ArrayList<PasswordReset> convertJsonInputStreamToPasswordResets(InputStream inputStream) {
        Class<PasswordReset> cls = PasswordReset.class;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setConfig(objectMapper.getDeserializationConfig().without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        ResourceConverter resourceConverter = new ResourceConverter(objectMapper, (Class<?>[]) new Class[]{cls});
        resourceConverter.enableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        return new ArrayList<>(resourceConverter.readDocumentCollection(inputStream, cls).get());
    }
}
