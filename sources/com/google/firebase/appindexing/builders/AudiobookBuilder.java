package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class AudiobookBuilder extends IndexableBuilder<AudiobookBuilder> {
    AudiobookBuilder() {
        super("Audiobook");
    }

    public AudiobookBuilder setAuthor(PersonBuilder... personBuilderArr) {
        return (AudiobookBuilder) put("author", (S[]) personBuilderArr);
    }

    public AudiobookBuilder setReadBy(PersonBuilder... personBuilderArr) {
        return (AudiobookBuilder) put("readBy", (S[]) personBuilderArr);
    }
}
