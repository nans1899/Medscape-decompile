package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class StickerBuilder extends IndexableBuilder<StickerBuilder> {
    StickerBuilder() {
        super("Sticker");
    }

    public final StickerBuilder setIsPartOf(StickerPackBuilder stickerPackBuilder) {
        return (StickerBuilder) put("isPartOf", (S[]) new StickerPackBuilder[]{stickerPackBuilder});
    }
}
