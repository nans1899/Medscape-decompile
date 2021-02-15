package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class PostalAddressBuilder extends IndexableBuilder<PostalAddressBuilder> {
    PostalAddressBuilder() {
        super("PostalAddress");
    }

    public final PostalAddressBuilder setAddressCountry(String str) {
        return (PostalAddressBuilder) put("addressCountry", str);
    }

    public final PostalAddressBuilder setAddressLocality(String str) {
        return (PostalAddressBuilder) put("addressLocality", str);
    }

    public final PostalAddressBuilder setPostalCode(String str) {
        return (PostalAddressBuilder) put("postalCode", str);
    }

    public final PostalAddressBuilder setStreetAddress(String str) {
        return (PostalAddressBuilder) put("streetAddress", str);
    }
}
