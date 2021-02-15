package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class StickerPackBuilder extends IndexableBuilder<StickerPackBuilder> {
    StickerPackBuilder() {
        super("StickerPack");
    }

    public final StickerPackBuilder setHasSticker(StickerBuilder... stickerBuilderArr) {
        return (StickerPackBuilder) put("hasSticker", (S[]) stickerBuilderArr);
    }
}
