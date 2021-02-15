package com.facebook.places.model;

public class CurrentPlaceFeedbackRequestParams {
    private final String placeId;
    private final String tracking;
    private final Boolean wasHere;

    private CurrentPlaceFeedbackRequestParams(Builder builder) {
        this.tracking = builder.tracking;
        this.placeId = builder.placeId;
        this.wasHere = builder.wasHere;
    }

    public String getTracking() {
        return this.tracking;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public Boolean wasHere() {
        return this.wasHere;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String placeId;
        /* access modifiers changed from: private */
        public String tracking;
        /* access modifiers changed from: private */
        public Boolean wasHere;

        public Builder setWasHere(boolean z) {
            this.wasHere = Boolean.valueOf(z);
            return this;
        }

        public Builder setPlaceId(String str) {
            this.placeId = str;
            return this;
        }

        public Builder setTracking(String str) {
            this.tracking = str;
            return this;
        }

        public CurrentPlaceFeedbackRequestParams build() {
            return new CurrentPlaceFeedbackRequestParams(this);
        }
    }
}
