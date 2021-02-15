package org.mozilla.javascript.serialize;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.UniqueTag;

public class ScriptableOutputStream extends ObjectOutputStream {
    private Scriptable scope;
    private Map<Object, String> table;

    public ScriptableOutputStream(OutputStream outputStream, Scriptable scriptable) throws IOException {
        super(outputStream);
        this.scope = scriptable;
        HashMap hashMap = new HashMap();
        this.table = hashMap;
        hashMap.put(scriptable, "");
        enableReplaceObject(true);
        excludeStandardObjectNames();
    }

    public void excludeAllIds(Object[] objArr) {
        for (String str : objArr) {
            if (str instanceof String) {
                Scriptable scriptable = this.scope;
                String str2 = str;
                if (scriptable.get(str2, scriptable) instanceof Scriptable) {
                    addExcludedName(str2);
                }
            }
        }
    }

    public void addOptionalExcludedName(String str) {
        Object lookupQualifiedName = lookupQualifiedName(this.scope, str);
        if (lookupQualifiedName != null && lookupQualifiedName != UniqueTag.NOT_FOUND) {
            if (lookupQualifiedName instanceof Scriptable) {
                this.table.put(lookupQualifiedName, str);
                return;
            }
            throw new IllegalArgumentException("Object for excluded name " + str + " is not a Scriptable, it is " + lookupQualifiedName.getClass().getName());
        }
    }

    public void addExcludedName(String str) {
        Object lookupQualifiedName = lookupQualifiedName(this.scope, str);
        if (lookupQualifiedName instanceof Scriptable) {
            this.table.put(lookupQualifiedName, str);
            return;
        }
        throw new IllegalArgumentException("Object for excluded name " + str + " not found.");
    }

    public boolean hasExcludedName(String str) {
        return this.table.get(str) != null;
    }

    public void removeExcludedName(String str) {
        this.table.remove(str);
    }

    public void excludeStandardObjectNames() {
        String[] strArr = {"Object", "Object.prototype", "Function", "Function.prototype", "String", "String.prototype", "Math", "Array", "Array.prototype", "Error", "Error.prototype", "Number", "Number.prototype", HttpHeaders.DATE, "Date.prototype", "RegExp", "RegExp.prototype", "Script", "Script.prototype", "Continuation", "Continuation.prototype"};
        for (int i = 0; i < 21; i++) {
            addExcludedName(strArr[i]);
        }
        String[] strArr2 = {"XML", "XML.prototype", "XMLList", "XMLList.prototype"};
        for (int i2 = 0; i2 < 4; i2++) {
            addOptionalExcludedName(strArr2[i2]);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        r2 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object lookupQualifiedName(org.mozilla.javascript.Scriptable r2, java.lang.String r3) {
        /*
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            java.lang.String r1 = "."
            r0.<init>(r3, r1)
        L_0x0007:
            boolean r3 = r0.hasMoreTokens()
            if (r3 == 0) goto L_0x001d
            java.lang.String r3 = r0.nextToken()
            org.mozilla.javascript.Scriptable r2 = (org.mozilla.javascript.Scriptable) r2
            java.lang.Object r2 = org.mozilla.javascript.ScriptableObject.getProperty((org.mozilla.javascript.Scriptable) r2, (java.lang.String) r3)
            if (r2 == 0) goto L_0x001d
            boolean r3 = r2 instanceof org.mozilla.javascript.Scriptable
            if (r3 != 0) goto L_0x0007
        L_0x001d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.serialize.ScriptableOutputStream.lookupQualifiedName(org.mozilla.javascript.Scriptable, java.lang.String):java.lang.Object");
    }

    static class PendingLookup implements Serializable {
        static final long serialVersionUID = -2692990309789917727L;
        private String name;

        PendingLookup(String str) {
            this.name = str;
        }

        /* access modifiers changed from: package-private */
        public String getName() {
            return this.name;
        }
    }

    /* access modifiers changed from: protected */
    public Object replaceObject(Object obj) throws IOException {
        String str = this.table.get(obj);
        if (str == null) {
            return obj;
        }
        return new PendingLookup(str);
    }
}
