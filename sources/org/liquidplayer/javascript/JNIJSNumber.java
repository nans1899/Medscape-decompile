package org.liquidplayer.javascript;

import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

class JNIJSNumber extends JNIJSPrimitive {
    private static final DecimalFormat df;
    private final double value;

    /* access modifiers changed from: package-private */
    public JNIJSValue createJSONString() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isNumber() {
        return true;
    }

    JNIJSNumber(long j) {
        super(j);
        this.value = Double.longBitsToDouble(j);
    }

    /* access modifiers changed from: protected */
    public boolean primitiveEqual(JNIJSPrimitive jNIJSPrimitive) {
        if (!(jNIJSPrimitive instanceof JNIJSNumber) || ((JNIJSNumber) jNIJSPrimitive).value != this.value) {
            if (jNIJSPrimitive instanceof JNIJSBoolean) {
                JNIJSBoolean jNIJSBoolean = (JNIJSBoolean) jNIJSPrimitive;
                if ((jNIJSBoolean.reference != 14 || !toBoolean()) && (jNIJSBoolean.reference != 10 || toBoolean())) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean primitiveStrictEqual(JNIJSPrimitive jNIJSPrimitive) {
        return (jNIJSPrimitive instanceof JNIJSNumber) && ((JNIJSNumber) jNIJSPrimitive).value == this.value;
    }

    /* access modifiers changed from: package-private */
    public boolean toBoolean() {
        return this.value != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    /* access modifiers changed from: package-private */
    public double toNumber() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public String toStringCopy() {
        return df.format(this.value);
    }

    static {
        DecimalFormat decimalFormat = new DecimalFormat(AppEventsConstants.EVENT_PARAM_VALUE_NO, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df = decimalFormat;
        decimalFormat.setMaximumFractionDigits(340);
    }
}
