package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class AggregateRatingBuilder extends IndexableBuilder<AggregateRatingBuilder> {
    AggregateRatingBuilder() {
        super("AggregateRating");
    }

    public final AggregateRatingBuilder setRatingCount(long j) {
        return (AggregateRatingBuilder) put("ratingCount", j);
    }

    public final AggregateRatingBuilder setRatingValue(String str) {
        return (AggregateRatingBuilder) put("ratingValue", str);
    }
}
