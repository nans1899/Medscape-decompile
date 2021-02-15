package at.rags.morpheus;

import at.rags.morpheus.Exceptions.NotExtendingResourceException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Deserializer {
    private static HashMap<String, Class> registeredClasses = new HashMap<>();

    public static void registerResourceClass(String str, Class cls) {
        registeredClasses.put(str, cls);
    }

    public Resource createObjectFromString(String str) throws InstantiationException, IllegalAccessException, NotExtendingResourceException {
        Class cls = registeredClasses.get(str);
        try {
            return (Resource) cls.newInstance();
        } catch (InstantiationException e) {
            throw e;
        } catch (IllegalAccessException e2) {
            throw e2;
        } catch (ClassCastException unused) {
            throw new NotExtendingResourceException(cls + " is not inheriting Resource");
        }
    }

    public Resource setField(Resource resource, String str, Object obj) {
        Field field = null;
        try {
            field = resource.getClass().getDeclaredField(str);
            field.setAccessible(true);
            field.set(resource, obj);
        } catch (NoSuchFieldException unused) {
            Logger.debug("Field " + str + " not found.");
        } catch (IllegalAccessException unused2) {
            Logger.debug("Could not access " + field.getName() + " field");
        }
        return resource;
    }

    public Resource setIdField(Resource resource, Object obj) throws NotExtendingResourceException {
        try {
            try {
                Field declaredField = getMorpheusResourceSuperClass(resource).getDeclaredField("Id");
                declaredField.setAccessible(true);
                if (obj instanceof String) {
                    declaredField.set(resource, obj);
                } else {
                    declaredField.set(resource, String.valueOf(obj));
                }
            } catch (NoSuchFieldException unused) {
                Logger.debug("No field Id found. That should not happened.");
            } catch (IllegalAccessException unused2) {
                Logger.debug("Could not access field Id");
            }
            return resource;
        } catch (NotExtendingResourceException e) {
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0008 A[LOOP:0: B:1:0x0008->B:4:0x0011, LOOP_START, PHI: r0 
      PHI: (r0v2 java.lang.Class<at.rags.morpheus.Resource>) = (r0v1 java.lang.Class<at.rags.morpheus.Resource>), (r0v5 java.lang.Class<? super at.rags.morpheus.Resource>) binds: [B:0:0x0000, B:4:0x0011] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Class getMorpheusResourceSuperClass(at.rags.morpheus.Resource r3) throws at.rags.morpheus.Exceptions.NotExtendingResourceException {
        /*
            r2 = this;
            java.lang.Class r0 = r3.getClass()
            java.lang.Class r0 = r0.getSuperclass()
        L_0x0008:
            java.lang.Class<at.rags.morpheus.Resource> r1 = at.rags.morpheus.Resource.class
            if (r0 != r1) goto L_0x000d
            goto L_0x0013
        L_0x000d:
            java.lang.Class r0 = r0.getSuperclass()
            if (r0 != 0) goto L_0x0008
        L_0x0013:
            if (r0 == 0) goto L_0x0016
            return r0
        L_0x0016:
            at.rags.morpheus.Exceptions.NotExtendingResourceException r0 = new at.rags.morpheus.Exceptions.NotExtendingResourceException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.Class r3 = r3.getClass()
            r1.append(r3)
            java.lang.String r3 = " is not inheriting Resource"
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: at.rags.morpheus.Deserializer.getMorpheusResourceSuperClass(at.rags.morpheus.Resource):java.lang.Class");
    }

    public static HashMap<String, Class> getRegisteredClasses() {
        return registeredClasses;
    }

    public static void setRegisteredClasses(HashMap<String, Class> hashMap) {
        registeredClasses = hashMap;
    }
}
