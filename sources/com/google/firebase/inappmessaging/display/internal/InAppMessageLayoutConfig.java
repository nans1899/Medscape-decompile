package com.google.firebase.inappmessaging.display.internal;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class InAppMessageLayoutConfig {
    /* access modifiers changed from: private */
    public Boolean animate;
    /* access modifiers changed from: private */
    public Boolean autoDismiss;
    /* access modifiers changed from: private */
    public Boolean backgroundEnabled;
    /* access modifiers changed from: private */
    public Float maxBodyHeightWeight;
    /* access modifiers changed from: private */
    public Float maxBodyWidthWeight;
    /* access modifiers changed from: private */
    public Integer maxDialogHeightPx;
    /* access modifiers changed from: private */
    public Integer maxDialogWidthPx;
    /* access modifiers changed from: private */
    public Float maxImageHeightWeight;
    /* access modifiers changed from: private */
    public Float maxImageWidthWeight;
    /* access modifiers changed from: private */
    public Integer viewWindowGravity;
    /* access modifiers changed from: private */
    public Integer windowFlag;
    /* access modifiers changed from: private */
    public Integer windowHeight;
    /* access modifiers changed from: private */
    public Integer windowWidth;

    public static Builder builder() {
        return new Builder();
    }

    public Float maxImageHeightWeight() {
        return this.maxImageHeightWeight;
    }

    public Float maxImageWidthWeight() {
        return this.maxImageWidthWeight;
    }

    public Float maxBodyHeightWeight() {
        return this.maxBodyHeightWeight;
    }

    public Float maxBodyWidthWeight() {
        return this.maxBodyWidthWeight;
    }

    public Integer maxDialogHeightPx() {
        return this.maxDialogHeightPx;
    }

    public Integer maxDialogWidthPx() {
        return this.maxDialogWidthPx;
    }

    public Integer windowFlag() {
        return this.windowFlag;
    }

    public Integer viewWindowGravity() {
        return this.viewWindowGravity;
    }

    public Integer windowWidth() {
        return this.windowWidth;
    }

    public Integer windowHeight() {
        return this.windowHeight;
    }

    public Boolean backgroundEnabled() {
        return this.backgroundEnabled;
    }

    public Boolean animate() {
        return this.animate;
    }

    public Boolean autoDismiss() {
        return this.autoDismiss;
    }

    public int getMaxImageHeight() {
        return (int) (maxImageHeightWeight().floatValue() * ((float) maxDialogHeightPx().intValue()));
    }

    public int getMaxImageWidth() {
        return (int) (maxImageWidthWeight().floatValue() * ((float) maxDialogWidthPx().intValue()));
    }

    public int getMaxBodyHeight() {
        return (int) (maxBodyHeightWeight().floatValue() * ((float) maxDialogHeightPx().intValue()));
    }

    public int getMaxBodyWidth() {
        return (int) (maxBodyWidthWeight().floatValue() * ((float) maxDialogWidthPx().intValue()));
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    public static class Builder {
        private final InAppMessageLayoutConfig config = new InAppMessageLayoutConfig();

        public Builder setMaxImageHeightWeight(Float f) {
            Float unused = this.config.maxImageHeightWeight = f;
            return this;
        }

        public Builder setMaxImageWidthWeight(Float f) {
            Float unused = this.config.maxImageWidthWeight = f;
            return this;
        }

        public Builder setMaxBodyHeightWeight(Float f) {
            Float unused = this.config.maxBodyHeightWeight = f;
            return this;
        }

        public Builder setMaxBodyWidthWeight(Float f) {
            Float unused = this.config.maxBodyWidthWeight = f;
            return this;
        }

        public Builder setMaxDialogHeightPx(Integer num) {
            Integer unused = this.config.maxDialogHeightPx = num;
            return this;
        }

        public Builder setMaxDialogWidthPx(Integer num) {
            Integer unused = this.config.maxDialogWidthPx = num;
            return this;
        }

        public Builder setViewWindowGravity(Integer num) {
            Integer unused = this.config.viewWindowGravity = num;
            return this;
        }

        public Builder setWindowFlag(Integer num) {
            Integer unused = this.config.windowFlag = num;
            return this;
        }

        public Builder setWindowWidth(Integer num) {
            Integer unused = this.config.windowWidth = num;
            return this;
        }

        public Builder setWindowHeight(Integer num) {
            Integer unused = this.config.windowHeight = num;
            return this;
        }

        public Builder setBackgroundEnabled(Boolean bool) {
            Boolean unused = this.config.backgroundEnabled = bool;
            return this;
        }

        public Builder setAnimate(Boolean bool) {
            Boolean unused = this.config.animate = bool;
            return this;
        }

        public Builder setAutoDismiss(Boolean bool) {
            Boolean unused = this.config.autoDismiss = bool;
            return this;
        }

        public InAppMessageLayoutConfig build() {
            return this.config;
        }
    }
}
