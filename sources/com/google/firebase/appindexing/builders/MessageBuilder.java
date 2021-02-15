package com.google.firebase.appindexing.builders;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Date;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class MessageBuilder extends IndexableBuilder<MessageBuilder> {
    MessageBuilder() {
        super("Message");
    }

    MessageBuilder(String str) {
        super(str);
    }

    public final MessageBuilder setText(String str) {
        return (MessageBuilder) put("text", str);
    }

    public final MessageBuilder setDateSent(Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateSent", date.getTime());
    }

    public final MessageBuilder setDateReceived(Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateReceived", date.getTime());
    }

    public final MessageBuilder setDateRead(Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateRead", date.getTime());
    }

    public final MessageBuilder setSender(PersonBuilder personBuilder) {
        return (MessageBuilder) put("sender", (S[]) new PersonBuilder[]{personBuilder});
    }

    public final MessageBuilder setRecipient(PersonBuilder... personBuilderArr) {
        return (MessageBuilder) put("recipient", (S[]) personBuilderArr);
    }

    public final MessageBuilder setMessageAttachment(IndexableBuilder<?>... indexableBuilderArr) {
        return (MessageBuilder) put("messageAttachment", (S[]) indexableBuilderArr);
    }

    public final MessageBuilder setIsPartOf(ConversationBuilder... conversationBuilderArr) {
        return (MessageBuilder) put("isPartOf", (S[]) conversationBuilderArr);
    }
}
