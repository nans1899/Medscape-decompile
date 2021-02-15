package org.mozilla.javascript;

import org.mozilla.javascript.PolicySecurityController;

public class RhinoSecurityManager extends SecurityManager {
    /* access modifiers changed from: protected */
    public Class getCurrentScriptClass() {
        for (Class<InterpretedFunction> cls : getClassContext()) {
            if ((cls != InterpretedFunction.class && NativeFunction.class.isAssignableFrom(cls)) || PolicySecurityController.SecureCaller.class.isAssignableFrom(cls)) {
                return cls;
            }
        }
        return null;
    }
}
