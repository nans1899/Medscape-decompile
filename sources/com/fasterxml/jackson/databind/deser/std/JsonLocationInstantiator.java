package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;

public class JsonLocationInstantiator extends ValueInstantiator {
    public boolean canCreateFromObjectWith() {
        return true;
    }

    public String getValueTypeDesc() {
        return JsonLocation.class.getName();
    }

    public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig deserializationConfig) {
        JavaType constructType = deserializationConfig.constructType((Class<?>) Integer.TYPE);
        JavaType constructType2 = deserializationConfig.constructType((Class<?>) Long.TYPE);
        return new SettableBeanProperty[]{creatorProp("sourceRef", deserializationConfig.constructType((Class<?>) Object.class), 0), creatorProp("byteOffset", constructType2, 1), creatorProp("charOffset", constructType2, 2), creatorProp("lineNr", constructType, 3), creatorProp("columnNr", constructType, 4)};
    }

    private static CreatorProperty creatorProp(String str, JavaType javaType, int i) {
        return new CreatorProperty(PropertyName.construct(str), javaType, (PropertyName) null, (TypeDeserializer) null, (Annotations) null, (AnnotatedParameter) null, i, (Object) null, PropertyMetadata.STD_REQUIRED);
    }

    public Object createFromObjectWith(DeserializationContext deserializationContext, Object[] objArr) {
        return new JsonLocation(objArr[0], _long(objArr[1]), _long(objArr[2]), _int(objArr[3]), _int(objArr[4]));
    }

    private static final long _long(Object obj) {
        if (obj == null) {
            return 0;
        }
        return ((Number) obj).longValue();
    }

    private static final int _int(Object obj) {
        if (obj == null) {
            return 0;
        }
        return ((Number) obj).intValue();
    }
}
