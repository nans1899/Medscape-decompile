package org.mozilla.javascript.xmlimpl;

import com.medscape.android.updater.UpdateManager;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

class XMLCtor extends IdFunctionObject {
    private static final int Id_defaultSettings = 1;
    private static final int Id_ignoreComments = 1;
    private static final int Id_ignoreProcessingInstructions = 2;
    private static final int Id_ignoreWhitespace = 3;
    private static final int Id_prettyIndent = 4;
    private static final int Id_prettyPrinting = 5;
    private static final int Id_setSettings = 3;
    private static final int Id_settings = 2;
    private static final int MAX_FUNCTION_ID = 3;
    private static final int MAX_INSTANCE_ID = 5;
    private static final Object XMLCTOR_TAG = "XMLCtor";
    static final long serialVersionUID = -8708195078359817341L;
    private XmlProcessor options;

    XMLCtor(XML xml, Object obj, int i, int i2) {
        super(xml, obj, i, i2);
        this.options = xml.getProcessor();
        activatePrototypeMap(3);
    }

    private void writeSetting(Scriptable scriptable) {
        for (int i = 1; i <= 5; i++) {
            int maxInstanceId = super.getMaxInstanceId() + i;
            ScriptableObject.putProperty(scriptable, getInstanceIdName(maxInstanceId), getInstanceIdValue(maxInstanceId));
        }
    }

    private void readSettings(Scriptable scriptable) {
        for (int i = 1; i <= 5; i++) {
            int maxInstanceId = super.getMaxInstanceId() + i;
            Object property = ScriptableObject.getProperty(scriptable, getInstanceIdName(maxInstanceId));
            if (property != Scriptable.NOT_FOUND) {
                if (!(i == 1 || i == 2 || i == 3)) {
                    if (i == 4) {
                        if (!(property instanceof Number)) {
                        }
                        setInstanceIdValue(maxInstanceId, property);
                    } else if (i != 5) {
                        throw new IllegalStateException();
                    }
                }
                if (!(property instanceof Boolean)) {
                }
                setInstanceIdValue(maxInstanceId, property);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxInstanceId() {
        return super.getMaxInstanceId() + 5;
    }

    /* access modifiers changed from: protected */
    public int findInstanceIdInfo(String str) {
        int i;
        String str2;
        int length = str.length();
        int i2 = 0;
        if (length != 12) {
            if (length == 14) {
                char charAt = str.charAt(0);
                if (charAt == 'i') {
                    str2 = "ignoreComments";
                    i = 1;
                } else if (charAt == 'p') {
                    str2 = "prettyPrinting";
                    i = 5;
                }
            } else if (length == 16) {
                str2 = "ignoreWhitespace";
                i = 3;
            } else if (length == 28) {
                str2 = "ignoreProcessingInstructions";
                i = 2;
            }
            str2 = null;
            i = 0;
        } else {
            str2 = "prettyIndent";
            i = 4;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            i2 = i;
        }
        if (i2 == 0) {
            return super.findInstanceIdInfo(str);
        }
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) {
            return instanceIdInfo(6, super.getMaxInstanceId() + i2);
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: protected */
    public String getInstanceIdName(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (maxInstanceId == 1) {
            return "ignoreComments";
        }
        if (maxInstanceId == 2) {
            return "ignoreProcessingInstructions";
        }
        if (maxInstanceId == 3) {
            return "ignoreWhitespace";
        }
        if (maxInstanceId != 4) {
            return maxInstanceId != 5 ? super.getInstanceIdName(i) : "prettyPrinting";
        }
        return "prettyIndent";
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (maxInstanceId == 1) {
            return ScriptRuntime.wrapBoolean(this.options.isIgnoreComments());
        }
        if (maxInstanceId == 2) {
            return ScriptRuntime.wrapBoolean(this.options.isIgnoreProcessingInstructions());
        }
        if (maxInstanceId == 3) {
            return ScriptRuntime.wrapBoolean(this.options.isIgnoreWhitespace());
        }
        if (maxInstanceId == 4) {
            return ScriptRuntime.wrapInt(this.options.getPrettyIndent());
        }
        if (maxInstanceId != 5) {
            return super.getInstanceIdValue(i);
        }
        return ScriptRuntime.wrapBoolean(this.options.isPrettyPrinting());
    }

    /* access modifiers changed from: protected */
    public void setInstanceIdValue(int i, Object obj) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (maxInstanceId == 1) {
            this.options.setIgnoreComments(ScriptRuntime.toBoolean(obj));
        } else if (maxInstanceId == 2) {
            this.options.setIgnoreProcessingInstructions(ScriptRuntime.toBoolean(obj));
        } else if (maxInstanceId == 3) {
            this.options.setIgnoreWhitespace(ScriptRuntime.toBoolean(obj));
        } else if (maxInstanceId == 4) {
            this.options.setPrettyIndent(ScriptRuntime.toInt32(obj));
        } else if (maxInstanceId != 5) {
            super.setInstanceIdValue(i, obj);
        } else {
            this.options.setPrettyPrinting(ScriptRuntime.toBoolean(obj));
        }
    }

    /* access modifiers changed from: protected */
    public int findPrototypeId(String str) {
        String str2;
        int i;
        int length = str.length();
        if (length == 8) {
            i = 2;
            str2 = UpdateManager.SETTINGS_PREFS;
        } else if (length == 11) {
            i = 3;
            str2 = "setSettings";
        } else if (length == 15) {
            i = 1;
            str2 = "defaultSettings";
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 1;
        if (i == 1) {
            str2 = "defaultSettings";
        } else if (i == 2) {
            str2 = UpdateManager.SETTINGS_PREFS;
        } else if (i == 3) {
            str = "setSettings";
            initPrototypeMethod(XMLCTOR_TAG, i, str, i2);
        } else {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        str = str2;
        i2 = 0;
        initPrototypeMethod(XMLCTOR_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(XMLCTOR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            this.options.setDefault();
            Scriptable newObject = context.newObject(scriptable);
            writeSetting(newObject);
            return newObject;
        } else if (methodId == 2) {
            Scriptable newObject2 = context.newObject(scriptable);
            writeSetting(newObject2);
            return newObject2;
        } else if (methodId == 3) {
            if (objArr.length == 0 || objArr[0] == null || objArr[0] == Undefined.instance) {
                this.options.setDefault();
            } else if (objArr[0] instanceof Scriptable) {
                readSettings(objArr[0]);
            }
            return Undefined.instance;
        } else {
            throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    public boolean hasInstance(Scriptable scriptable) {
        return (scriptable instanceof XML) || (scriptable instanceof XMLList);
    }
}
