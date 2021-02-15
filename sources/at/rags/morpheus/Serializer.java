package at.rags.morpheus;

import at.rags.morpheus.Annotations.Relationship;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Serializer {
    public HashMap<String, Object> getFieldsAsDictionary(Resource resource) {
        Object obj;
        String str;
        HashMap<String, Object> hashMap = null;
        for (Field field : resource.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Relationship.class)) {
                try {
                    field.setAccessible(true);
                    obj = field.get(resource);
                    if (obj == null) {
                    }
                } catch (IllegalAccessException unused) {
                    Logger.debug("Cannot access field: " + null + ".");
                    obj = null;
                }
                if (field.isAnnotationPresent(SerializedName.class)) {
                    str = ((SerializedName) field.getAnnotation(SerializedName.class)).value();
                } else {
                    str = field.getName();
                }
                if (hashMap == null) {
                    hashMap = new HashMap<>();
                }
                hashMap.put(str, obj);
            }
        }
        return hashMap;
    }

    public HashMap<String, Object> getRelationships(Resource resource) {
        HashMap<String, Object> hashMap = new HashMap<>();
        for (Field field : resource.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Relationship.class)) {
                Relationship relationship = (Relationship) field.getAnnotation(Relationship.class);
                field.setAccessible(true);
                try {
                    hashMap.put(relationship.value(), field.get(resource));
                } catch (IllegalAccessException unused) {
                    Logger.debug("Cannot access field: " + field.getName() + ".");
                }
            }
        }
        return hashMap;
    }
}
