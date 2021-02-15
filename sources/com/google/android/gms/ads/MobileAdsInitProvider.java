package com.google.android.gms.ads;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import com.google.android.gms.internal.ads.zzzi;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class MobileAdsInitProvider extends ContentProvider {
    private final zzzi zzadl = new zzzi();

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        this.zzadl.attachInfo(context, providerInfo);
    }

    public boolean onCreate() {
        return this.zzadl.onCreate();
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return this.zzadl.query(uri, strArr, str, strArr2, str2);
    }

    public String getType(Uri uri) {
        return this.zzadl.getType(uri);
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return this.zzadl.insert(uri, contentValues);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return this.zzadl.delete(uri, str, strArr);
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return this.zzadl.update(uri, contentValues, str, strArr);
    }
}
