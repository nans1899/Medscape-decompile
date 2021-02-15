package com.webmd.medscape.live.explorelivevents.data;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/Error;", "", "showError", "", "message", "", "errorImage", "Landroid/graphics/drawable/Drawable;", "(ZLjava/lang/String;Landroid/graphics/drawable/Drawable;)V", "getErrorImage", "()Landroid/graphics/drawable/Drawable;", "getMessage", "()Ljava/lang/String;", "getShowError", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: UiState.kt */
public final class Error {
    private final Drawable errorImage;
    private final String message;
    private final boolean showError;

    public static /* synthetic */ Error copy$default(Error error, boolean z, String str, Drawable drawable, int i, Object obj) {
        if ((i & 1) != 0) {
            z = error.showError;
        }
        if ((i & 2) != 0) {
            str = error.message;
        }
        if ((i & 4) != 0) {
            drawable = error.errorImage;
        }
        return error.copy(z, str, drawable);
    }

    public final boolean component1() {
        return this.showError;
    }

    public final String component2() {
        return this.message;
    }

    public final Drawable component3() {
        return this.errorImage;
    }

    public final Error copy(boolean z, String str, Drawable drawable) {
        return new Error(z, str, drawable);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Error)) {
            return false;
        }
        Error error = (Error) obj;
        return this.showError == error.showError && Intrinsics.areEqual((Object) this.message, (Object) error.message) && Intrinsics.areEqual((Object) this.errorImage, (Object) error.errorImage);
    }

    public int hashCode() {
        boolean z = this.showError;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        String str = this.message;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        Drawable drawable = this.errorImage;
        if (drawable != null) {
            i2 = drawable.hashCode();
        }
        return hashCode + i2;
    }

    public String toString() {
        return "Error(showError=" + this.showError + ", message=" + this.message + ", errorImage=" + this.errorImage + ")";
    }

    public Error(boolean z, String str, Drawable drawable) {
        this.showError = z;
        this.message = str;
        this.errorImage = drawable;
    }

    public final Drawable getErrorImage() {
        return this.errorImage;
    }

    public final String getMessage() {
        return this.message;
    }

    public final boolean getShowError() {
        return this.showError;
    }
}
