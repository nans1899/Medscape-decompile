package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class BookBuilder extends IndexableBuilder<BookBuilder> {
    BookBuilder() {
        super("Book");
    }

    public BookBuilder setAuthor(PersonBuilder... personBuilderArr) {
        return (BookBuilder) put("author", (S[]) personBuilderArr);
    }
}
