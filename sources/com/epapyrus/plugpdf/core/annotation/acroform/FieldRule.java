package com.epapyrus.plugpdf.core.annotation.acroform;

import android.util.SparseArray;
import com.epapyrus.plugpdf.core.annotation.acroform.BaseField;
import java.util.HashMap;
import java.util.Map;

public class FieldRule {
    private static FieldRule mFieldRule;
    private SparseArray<Map<String, BaseField.FieldState>> mFieldStates;
    private BaseField.FieldState mGlobalFieldState;

    public static FieldRule instance() {
        if (mFieldRule == null) {
            reset();
        }
        return mFieldRule;
    }

    public static void reset() {
        mFieldRule = new FieldRule();
    }

    private FieldRule() {
        this.mFieldStates = null;
        this.mFieldStates = new SparseArray<>();
        this.mGlobalFieldState = null;
    }

    public void registerFieldState(int i, String str, String str2, BaseField.FieldState fieldState) {
        if (!str.equals("")) {
            str2 = str + "|" + str2;
        }
        if (this.mFieldStates.get(i) != null) {
            this.mFieldStates.get(i).put(str2, fieldState);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(str2, fieldState);
        this.mFieldStates.put(i, hashMap);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.epapyrus.plugpdf.core.annotation.acroform.BaseField$FieldState} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void applyRule(com.epapyrus.plugpdf.core.annotation.acroform.BaseField r4) {
        /*
            r3 = this;
            com.epapyrus.plugpdf.core.annotation.acroform.BaseField$FieldState r0 = r3.mGlobalFieldState
            if (r0 == 0) goto L_0x0008
            r4.setFieldState(r0)
            goto L_0x0057
        L_0x0008:
            int r0 = r4.getPageIdx()
            android.util.SparseArray<java.util.Map<java.lang.String, com.epapyrus.plugpdf.core.annotation.acroform.BaseField$FieldState>> r1 = r3.mFieldStates
            java.lang.Object r0 = r1.get(r0)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L_0x0057
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r4.getTitle()
            r1.append(r2)
            java.lang.String r2 = "|"
            r1.append(r2)
            java.lang.String r2 = r4.getUID()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object r1 = r0.get(r1)
            com.epapyrus.plugpdf.core.annotation.acroform.BaseField$FieldState r1 = (com.epapyrus.plugpdf.core.annotation.acroform.BaseField.FieldState) r1
            if (r1 != 0) goto L_0x0045
            java.lang.String r1 = r4.getUID()
            java.lang.Object r1 = r0.get(r1)
            com.epapyrus.plugpdf.core.annotation.acroform.BaseField$FieldState r1 = (com.epapyrus.plugpdf.core.annotation.acroform.BaseField.FieldState) r1
        L_0x0045:
            if (r1 != 0) goto L_0x0052
            java.lang.String r1 = r4.getTitle()
            java.lang.Object r0 = r0.get(r1)
            r1 = r0
            com.epapyrus.plugpdf.core.annotation.acroform.BaseField$FieldState r1 = (com.epapyrus.plugpdf.core.annotation.acroform.BaseField.FieldState) r1
        L_0x0052:
            if (r1 == 0) goto L_0x0057
            r4.setFieldState(r1)
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.annotation.acroform.FieldRule.applyRule(com.epapyrus.plugpdf.core.annotation.acroform.BaseField):void");
    }

    public BaseField.FieldState getFieldState(int i, String str) {
        Map map;
        if (this.mFieldStates.get(i) == null || (map = this.mFieldStates.get(i)) == null) {
            return null;
        }
        return (BaseField.FieldState) map.get(str);
    }

    public BaseField.FieldState getFieldStateWithTitle(int i, String str, String str2) {
        Map map;
        if (this.mFieldStates.get(i) == null || (map = this.mFieldStates.get(i)) == null) {
            return null;
        }
        return (BaseField.FieldState) map.get(str + "|" + str2);
    }

    public void setGlobalFieldState(BaseField.FieldState fieldState) {
        this.mGlobalFieldState = fieldState;
    }

    public void resetGlobalFieldState() {
        this.mGlobalFieldState = null;
    }
}
