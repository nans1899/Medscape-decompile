package com.google.firebase.inappmessaging.model;

import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ImageOnlyMessage extends InAppMessage {
    @Nullable
    private Action action;
    private ImageData imageData;

    public int hashCode() {
        Action action2 = this.action;
        return this.imageData.hashCode() + (action2 != null ? action2.hashCode() : 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r1 = r4.action;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.firebase.inappmessaging.model.ImageOnlyMessage
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.firebase.inappmessaging.model.ImageOnlyMessage r5 = (com.google.firebase.inappmessaging.model.ImageOnlyMessage) r5
            int r1 = r4.hashCode()
            int r3 = r5.hashCode()
            if (r1 == r3) goto L_0x0017
            return r2
        L_0x0017:
            com.google.firebase.inappmessaging.model.Action r1 = r4.action
            if (r1 != 0) goto L_0x001f
            com.google.firebase.inappmessaging.model.Action r1 = r5.action
            if (r1 != 0) goto L_0x002b
        L_0x001f:
            com.google.firebase.inappmessaging.model.Action r1 = r4.action
            if (r1 == 0) goto L_0x002c
            com.google.firebase.inappmessaging.model.Action r3 = r5.action
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x002c
        L_0x002b:
            return r2
        L_0x002c:
            com.google.firebase.inappmessaging.model.ImageData r1 = r4.imageData
            com.google.firebase.inappmessaging.model.ImageData r5 = r5.imageData
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0037
            return r0
        L_0x0037:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.model.ImageOnlyMessage.equals(java.lang.Object):boolean");
    }

    private ImageOnlyMessage(CampaignMetadata campaignMetadata, ImageData imageData2, @Nullable Action action2, @Nullable Map<String, String> map) {
        super(campaignMetadata, MessageType.IMAGE_ONLY, map);
        this.imageData = imageData2;
        this.action = action2;
    }

    public ImageData getImageData() {
        return this.imageData;
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
        ImageData imageData;

        public Builder setImageData(@Nullable ImageData imageData2) {
            this.imageData = imageData2;
            return this;
        }

        public Builder setAction(@Nullable Action action2) {
            this.action = action2;
            return this;
        }

        public ImageOnlyMessage build(CampaignMetadata campaignMetadata, @Nullable Map<String, String> map) {
            ImageData imageData2 = this.imageData;
            if (imageData2 != null) {
                return new ImageOnlyMessage(campaignMetadata, imageData2, this.action, map);
            }
            throw new IllegalArgumentException("ImageOnly model must have image data");
        }
    }
}
