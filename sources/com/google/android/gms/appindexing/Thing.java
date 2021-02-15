package com.google.android.gms.appindexing;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

@Deprecated
/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class Thing {
    final Bundle zzay;

    Thing(Bundle bundle) {
        this.zzay = bundle;
    }

    @Deprecated
    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static class Builder {
        final Bundle zzax = new Bundle();

        public Builder setName(String str) {
            Preconditions.checkNotNull(str);
            put("name", str);
            return this;
        }

        public Builder setUrl(Uri uri) {
            Preconditions.checkNotNull(uri);
            put("url", uri.toString());
            return this;
        }

        public Builder setId(String str) {
            if (str != null) {
                put("id", str);
            }
            return this;
        }

        public Builder setType(String str) {
            put("type", str);
            return this;
        }

        public Builder setDescription(String str) {
            put("description", str);
            return this;
        }

        public Builder put(String str, String str2) {
            Preconditions.checkNotNull(str);
            if (str2 != null) {
                this.zzax.putString(str, str2);
            }
            return this;
        }

        public Builder put(String str, String[] strArr) {
            Preconditions.checkNotNull(str);
            if (strArr != null) {
                this.zzax.putStringArray(str, strArr);
            }
            return this;
        }

        public Builder put(String str, Thing thing) {
            Preconditions.checkNotNull(str);
            if (thing != null) {
                this.zzax.putParcelable(str, thing.zzay);
            }
            return this;
        }

        public Builder put(String str, Thing[] thingArr) {
            Preconditions.checkNotNull(str);
            if (thingArr != null) {
                ArrayList arrayList = new ArrayList();
                for (Thing thing : thingArr) {
                    if (thing != null) {
                        arrayList.add(thing.zzay);
                    }
                }
                this.zzax.putParcelableArray(str, (Parcelable[]) arrayList.toArray(new Bundle[arrayList.size()]));
            }
            return this;
        }

        public Builder put(String str, boolean z) {
            Preconditions.checkNotNull(str);
            this.zzax.putBoolean(str, z);
            return this;
        }

        public Thing build() {
            return new Thing(this.zzax);
        }
    }

    public final Bundle zze() {
        return this.zzay;
    }
}
