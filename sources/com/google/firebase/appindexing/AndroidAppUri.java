package com.google.firebase.appindexing;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class AndroidAppUri {
    private final Uri uri;

    private AndroidAppUri(Uri uri2) {
        this.uri = uri2;
    }

    public static AndroidAppUri newAndroidAppUri(Uri uri2) {
        AndroidAppUri androidAppUri = new AndroidAppUri(uri2);
        if (!"android-app".equals(androidAppUri.uri.getScheme())) {
            throw new IllegalArgumentException("android-app scheme is required.");
        } else if (!TextUtils.isEmpty(androidAppUri.getPackageName())) {
            return androidAppUri;
        } else {
            throw new IllegalArgumentException("Package name is empty.");
        }
    }

    public final String getPackageName() {
        return this.uri.getAuthority();
    }

    public final Uri getDeepLinkUri() {
        List<String> pathSegments = this.uri.getPathSegments();
        if (pathSegments.size() <= 0) {
            return null;
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(pathSegments.get(0));
        if (pathSegments.size() > 1) {
            builder.authority(pathSegments.get(1));
            for (int i = 2; i < pathSegments.size(); i++) {
                builder.appendPath(pathSegments.get(i));
            }
        }
        builder.encodedQuery(this.uri.getEncodedQuery());
        builder.encodedFragment(this.uri.getEncodedFragment());
        return builder.build();
    }

    public final String toString() {
        return this.uri.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof AndroidAppUri) {
            return this.uri.equals(((AndroidAppUri) obj).uri);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.uri);
    }
}
