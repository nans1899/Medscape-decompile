package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;

public abstract class SecurityProxy extends SecurityController {
    /* access modifiers changed from: protected */
    public abstract void callProcessFileSecure(Context context, Scriptable scriptable, String str);
}
