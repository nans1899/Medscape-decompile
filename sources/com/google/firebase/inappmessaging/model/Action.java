package com.google.firebase.inappmessaging.model;

import android.text.TextUtils;
import com.google.firebase.inappmessaging.MessagesProto;
import com.google.firebase.inappmessaging.model.Button;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class Action {
    private final String actionUrl;
    private final Button button;

    public int hashCode() {
        String str = this.actionUrl;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        Button button2 = this.button;
        if (button2 != null) {
            i = button2.hashCode();
        }
        return hashCode + i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
        r1 = r4.button;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.firebase.inappmessaging.model.Action
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.firebase.inappmessaging.model.Action r5 = (com.google.firebase.inappmessaging.model.Action) r5
            int r1 = r4.hashCode()
            int r3 = r5.hashCode()
            if (r1 == r3) goto L_0x0017
            return r2
        L_0x0017:
            java.lang.String r1 = r4.actionUrl
            if (r1 != 0) goto L_0x001f
            java.lang.String r1 = r5.actionUrl
            if (r1 != 0) goto L_0x002b
        L_0x001f:
            java.lang.String r1 = r4.actionUrl
            if (r1 == 0) goto L_0x002c
            java.lang.String r3 = r5.actionUrl
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x002c
        L_0x002b:
            return r2
        L_0x002c:
            com.google.firebase.inappmessaging.model.Button r1 = r4.button
            if (r1 != 0) goto L_0x0034
            com.google.firebase.inappmessaging.model.Button r1 = r5.button
            if (r1 == 0) goto L_0x0040
        L_0x0034:
            com.google.firebase.inappmessaging.model.Button r1 = r4.button
            if (r1 == 0) goto L_0x0041
            com.google.firebase.inappmessaging.model.Button r5 = r5.button
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0041
        L_0x0040:
            return r0
        L_0x0041:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.model.Action.equals(java.lang.Object):boolean");
    }

    private Action(String str, Button button2) {
        this.actionUrl = str;
        this.button = button2;
    }

    public String getActionUrl() {
        return this.actionUrl;
    }

    public Button getButton() {
        return this.button;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static class Builder {
        private String actionUrl;
        private Button button;

        public Builder setActionUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.actionUrl = str;
            }
            return this;
        }

        public Builder setButton(Button button2) {
            this.button = button2;
            return this;
        }

        public Builder setButton(MessagesProto.Button button2) {
            Button.Builder builder = new Button.Builder();
            builder.setButtonHexColor(button2.getButtonHexColor());
            builder.setText(button2.getText());
            return this;
        }

        public Action build() {
            return new Action(this.actionUrl, this.button);
        }
    }
}
