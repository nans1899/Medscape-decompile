package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class ConversationBuilder extends IndexableBuilder<ConversationBuilder> {
    ConversationBuilder() {
        super("Conversation");
    }

    public final ConversationBuilder setId(String str) {
        return (ConversationBuilder) put("id", str);
    }
}
