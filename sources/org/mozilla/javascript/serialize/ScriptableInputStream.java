package org.mozilla.javascript.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.UniqueTag;
import org.mozilla.javascript.serialize.ScriptableOutputStream;

public class ScriptableInputStream extends ObjectInputStream {
    private ClassLoader classLoader;
    private Scriptable scope;

    public ScriptableInputStream(InputStream inputStream, Scriptable scriptable) throws IOException {
        super(inputStream);
        this.scope = scriptable;
        enableResolveObject(true);
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null) {
            this.classLoader = currentContext.getApplicationClassLoader();
        }
    }

    /* access modifiers changed from: protected */
    public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        String name = objectStreamClass.getName();
        ClassLoader classLoader2 = this.classLoader;
        if (classLoader2 != null) {
            try {
                return classLoader2.loadClass(name);
            } catch (ClassNotFoundException unused) {
            }
        }
        return super.resolveClass(objectStreamClass);
    }

    /* access modifiers changed from: protected */
    public Object resolveObject(Object obj) throws IOException {
        if (obj instanceof ScriptableOutputStream.PendingLookup) {
            String name = ((ScriptableOutputStream.PendingLookup) obj).getName();
            Object lookupQualifiedName = ScriptableOutputStream.lookupQualifiedName(this.scope, name);
            if (lookupQualifiedName != Scriptable.NOT_FOUND) {
                return lookupQualifiedName;
            }
            throw new IOException("Object " + name + " not found upon " + "deserialization.");
        } else if (obj instanceof UniqueTag) {
            return ((UniqueTag) obj).readResolve();
        } else {
            return obj instanceof Undefined ? ((Undefined) obj).readResolve() : obj;
        }
    }
}
