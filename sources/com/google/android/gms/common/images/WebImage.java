package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new zae();
    private final int zaa;
    private final Uri zab;
    private final int zac;
    private final int zad;

    WebImage(int i, Uri uri, int i2, int i3) {
        this.zaa = i;
        this.zab = uri;
        this.zac = i2;
        this.zad = i3;
    }

    public WebImage(Uri uri, int i, int i2) throws IllegalArgumentException {
        this(1, uri, i, i2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }

    public WebImage(JSONObject jSONObject) throws IllegalArgumentException {
        this(zaa(jSONObject), jSONObject.optInt(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH, 0), jSONObject.optInt(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, 0));
    }

    private static Uri zaa(JSONObject jSONObject) {
        Uri uri = Uri.EMPTY;
        if (!jSONObject.has("url")) {
            return uri;
        }
        try {
            return Uri.parse(jSONObject.getString("url"));
        } catch (JSONException unused) {
            return uri;
        }
    }

    public final Uri getUrl() {
        return this.zab;
    }

    public final int getWidth() {
        return this.zac;
    }

    public final int getHeight() {
        return this.zad;
    }

    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", new Object[]{Integer.valueOf(this.zac), Integer.valueOf(this.zad), this.zab.toString()});
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.zab.toString());
            jSONObject.put(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH, this.zac);
            jSONObject.put(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, this.zad);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof WebImage)) {
            WebImage webImage = (WebImage) obj;
            return Objects.equal(this.zab, webImage.zab) && this.zac == webImage.zac && this.zad == webImage.zad;
        }
    }

    public final int hashCode() {
        return Objects.hashCode(this.zab, Integer.valueOf(this.zac), Integer.valueOf(this.zad));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel, 2, getUrl(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
