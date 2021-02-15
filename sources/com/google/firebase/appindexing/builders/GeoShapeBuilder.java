package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class GeoShapeBuilder extends IndexableBuilder<GeoShapeBuilder> {
    GeoShapeBuilder() {
        super("GeoShape");
    }

    @Deprecated
    public final GeoShapeBuilder setBox(String str) {
        return (GeoShapeBuilder) put("box", str);
    }

    public final GeoShapeBuilder setBox(String... strArr) {
        return (GeoShapeBuilder) put("box", strArr);
    }
}
