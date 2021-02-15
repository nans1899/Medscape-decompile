package com.google.firebase.inappmessaging.model;

import android.text.TextUtils;
import com.google.firebase.inappmessaging.MessagesProto;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class Text {
    private final String hexColor;
    private final String text;

    public int hashCode() {
        String str = this.text;
        return str != null ? str.hashCode() + this.hexColor.hashCode() : this.hexColor.hashCode();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r1 = r4.text;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.firebase.inappmessaging.model.Text
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.firebase.inappmessaging.model.Text r5 = (com.google.firebase.inappmessaging.model.Text) r5
            int r1 = r4.hashCode()
            int r3 = r5.hashCode()
            if (r1 == r3) goto L_0x0017
            return r2
        L_0x0017:
            java.lang.String r1 = r4.text
            if (r1 != 0) goto L_0x001f
            java.lang.String r1 = r5.text
            if (r1 != 0) goto L_0x002b
        L_0x001f:
            java.lang.String r1 = r4.text
            if (r1 == 0) goto L_0x002c
            java.lang.String r3 = r5.text
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x002c
        L_0x002b:
            return r2
        L_0x002c:
            java.lang.String r1 = r4.hexColor
            java.lang.String r5 = r5.hexColor
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0037
            return r0
        L_0x0037:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.model.Text.equals(java.lang.Object):boolean");
    }

    private Text(String str, String str2) {
        this.text = str;
        this.hexColor = str2;
    }

    public String getText() {
        return this.text;
    }

    public String getHexColor() {
        return this.hexColor;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static class Builder {
        private String hexColor;
        private String text;

        public Builder setText(String str) {
            this.text = str;
            return this;
        }

        public Builder setText(MessagesProto.Text text2) {
            setText(text2.getText());
            setHexColor(text2.getHexColor());
            return this;
        }

        public Builder setHexColor(String str) {
            this.hexColor = str;
            return this;
        }

        public Text build() {
            if (!TextUtils.isEmpty(this.hexColor)) {
                return new Text(this.text, this.hexColor);
            }
            throw new IllegalArgumentException("Text model must have a color");
        }
    }
}
