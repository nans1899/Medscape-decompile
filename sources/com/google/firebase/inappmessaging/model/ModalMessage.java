package com.google.firebase.inappmessaging.model;

import android.text.TextUtils;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ModalMessage extends InAppMessage {
    @Nullable
    private final Action action;
    private final String backgroundHexColor;
    @Nullable
    private final Text body;
    @Nullable
    private final ImageData imageData;
    private final Text title;

    public int hashCode() {
        Text text = this.body;
        int i = 0;
        int hashCode = text != null ? text.hashCode() : 0;
        Action action2 = this.action;
        int hashCode2 = action2 != null ? action2.hashCode() : 0;
        ImageData imageData2 = this.imageData;
        if (imageData2 != null) {
            i = imageData2.hashCode();
        }
        return this.title.hashCode() + hashCode + this.backgroundHexColor.hashCode() + hashCode2 + i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0049, code lost:
        r1 = r4.imageData;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.firebase.inappmessaging.model.ModalMessage
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.firebase.inappmessaging.model.ModalMessage r5 = (com.google.firebase.inappmessaging.model.ModalMessage) r5
            int r1 = r4.hashCode()
            int r3 = r5.hashCode()
            if (r1 == r3) goto L_0x0017
            return r2
        L_0x0017:
            com.google.firebase.inappmessaging.model.Text r1 = r4.body
            if (r1 != 0) goto L_0x001f
            com.google.firebase.inappmessaging.model.Text r1 = r5.body
            if (r1 != 0) goto L_0x002b
        L_0x001f:
            com.google.firebase.inappmessaging.model.Text r1 = r4.body
            if (r1 == 0) goto L_0x002c
            com.google.firebase.inappmessaging.model.Text r3 = r5.body
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x002c
        L_0x002b:
            return r2
        L_0x002c:
            com.google.firebase.inappmessaging.model.Action r1 = r4.action
            if (r1 != 0) goto L_0x0034
            com.google.firebase.inappmessaging.model.Action r1 = r5.action
            if (r1 != 0) goto L_0x0040
        L_0x0034:
            com.google.firebase.inappmessaging.model.Action r1 = r4.action
            if (r1 == 0) goto L_0x0041
            com.google.firebase.inappmessaging.model.Action r3 = r5.action
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0041
        L_0x0040:
            return r2
        L_0x0041:
            com.google.firebase.inappmessaging.model.ImageData r1 = r4.imageData
            if (r1 != 0) goto L_0x0049
            com.google.firebase.inappmessaging.model.ImageData r1 = r5.imageData
            if (r1 != 0) goto L_0x0055
        L_0x0049:
            com.google.firebase.inappmessaging.model.ImageData r1 = r4.imageData
            if (r1 == 0) goto L_0x0056
            com.google.firebase.inappmessaging.model.ImageData r3 = r5.imageData
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0056
        L_0x0055:
            return r2
        L_0x0056:
            com.google.firebase.inappmessaging.model.Text r1 = r4.title
            com.google.firebase.inappmessaging.model.Text r3 = r5.title
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0061
            return r2
        L_0x0061:
            java.lang.String r1 = r4.backgroundHexColor
            java.lang.String r5 = r5.backgroundHexColor
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x006c
            return r0
        L_0x006c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.model.ModalMessage.equals(java.lang.Object):boolean");
    }

    private ModalMessage(CampaignMetadata campaignMetadata, Text text, @Nullable Text text2, @Nullable ImageData imageData2, @Nullable Action action2, String str, @Nullable Map<String, String> map) {
        super(campaignMetadata, MessageType.MODAL, map);
        this.title = text;
        this.body = text2;
        this.imageData = imageData2;
        this.action = action2;
        this.backgroundHexColor = str;
    }

    public Text getTitle() {
        return this.title;
    }

    @Nullable
    public Text getBody() {
        return this.body;
    }

    @Nullable
    public ImageData getImageData() {
        return this.imageData;
    }

    public String getBackgroundHexColor() {
        return this.backgroundHexColor;
    }

    @Nullable
    public Action getAction() {
        return this.action;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static class Builder {
        @Nullable
        Action action;
        @Nullable
        String backgroundHexColor;
        @Nullable
        Text body;
        @Nullable
        ImageData imageData;
        @Nullable
        Text title;

        public Builder setTitle(@Nullable Text text) {
            this.title = text;
            return this;
        }

        public Builder setBody(@Nullable Text text) {
            this.body = text;
            return this;
        }

        public Builder setImageData(@Nullable ImageData imageData2) {
            this.imageData = imageData2;
            return this;
        }

        public Builder setAction(@Nullable Action action2) {
            this.action = action2;
            return this;
        }

        public Builder setBackgroundHexColor(@Nullable String str) {
            this.backgroundHexColor = str;
            return this;
        }

        public ModalMessage build(CampaignMetadata campaignMetadata, @Nullable Map<String, String> map) {
            if (this.title != null) {
                Action action2 = this.action;
                if (action2 != null && action2.getButton() == null) {
                    throw new IllegalArgumentException("Modal model action must be null or have a button");
                } else if (!TextUtils.isEmpty(this.backgroundHexColor)) {
                    return new ModalMessage(campaignMetadata, this.title, this.body, this.imageData, this.action, this.backgroundHexColor, map);
                } else {
                    throw new IllegalArgumentException("Modal model must have a background color");
                }
            } else {
                throw new IllegalArgumentException("Modal model must have a title");
            }
        }
    }
}
