package com.afollestad.materialdialogs;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/afollestad/materialdialogs/WhichButton;", "", "index", "", "(Ljava/lang/String;II)V", "getIndex", "()I", "POSITIVE", "NEGATIVE", "NEUTRAL", "Companion", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: WhichButton.kt */
public enum WhichButton {
    POSITIVE(0),
    NEGATIVE(1),
    NEUTRAL(2);
    
    public static final Companion Companion = null;
    private final int index;

    private WhichButton(int i) {
        this.index = i;
    }

    public final int getIndex() {
        return this.index;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/afollestad/materialdialogs/WhichButton$Companion;", "", "()V", "fromIndex", "Lcom/afollestad/materialdialogs/WhichButton;", "index", "", "core"}, k = 1, mv = {1, 1, 16})
    /* compiled from: WhichButton.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WhichButton fromIndex(int i) {
            if (i == 0) {
                return WhichButton.POSITIVE;
            }
            if (i == 1) {
                return WhichButton.NEGATIVE;
            }
            if (i == 2) {
                return WhichButton.NEUTRAL;
            }
            throw new IndexOutOfBoundsException(i + " is not an action button index.");
        }
    }
}
