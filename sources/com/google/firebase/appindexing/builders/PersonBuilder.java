package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class PersonBuilder extends IndexableBuilder<PersonBuilder> {
    PersonBuilder() {
        super("Person");
    }

    public final PersonBuilder setEmail(String str) {
        return (PersonBuilder) put("email", str);
    }

    public final PersonBuilder setTelephone(String str) {
        return (PersonBuilder) put("telephone", str);
    }

    public final PersonBuilder setIsSelf(boolean z) {
        return (PersonBuilder) put("isSelf", z);
    }
}
