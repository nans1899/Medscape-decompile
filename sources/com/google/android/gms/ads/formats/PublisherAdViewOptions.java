package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.ads.zzaai;
import com.google.android.gms.internal.ads.zzafx;
import com.google.android.gms.internal.ads.zzafy;
import com.google.android.gms.internal.ads.zzvt;
import com.google.android.gms.internal.ads.zzxk;
import com.google.android.gms.internal.ads.zzxn;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class PublisherAdViewOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PublisherAdViewOptions> CREATOR = new zzc();
    private final boolean zzbny;
    private final zzxk zzbnz;
    private AppEventListener zzboa;
    private final IBinder zzbob;

    private PublisherAdViewOptions(Builder builder) {
        this.zzbny = builder.zzbny;
        AppEventListener zzb = builder.zzboa;
        this.zzboa = zzb;
        zzaai zzaai = null;
        this.zzbnz = zzb != null ? new zzvt(this.zzboa) : null;
        this.zzbob = builder.zzboc != null ? new zzaai(builder.zzboc) : zzaai;
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzbny = false;
        /* access modifiers changed from: private */
        public AppEventListener zzboa;
        /* access modifiers changed from: private */
        public ShouldDelayBannerRenderingListener zzboc;

        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zzbny = z;
            return this;
        }

        public final Builder setAppEventListener(AppEventListener appEventListener) {
            this.zzboa = appEventListener;
            return this;
        }

        public final Builder setShouldDelayBannerRenderingListener(ShouldDelayBannerRenderingListener shouldDelayBannerRenderingListener) {
            this.zzboc = shouldDelayBannerRenderingListener;
            return this;
        }

        public final PublisherAdViewOptions build() {
            return new PublisherAdViewOptions(this);
        }
    }

    PublisherAdViewOptions(boolean z, IBinder iBinder, IBinder iBinder2) {
        this.zzbny = z;
        this.zzbnz = iBinder != null ? zzxn.zze(iBinder) : null;
        this.zzbob = iBinder2;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzboa;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzbny;
    }

    public final zzxk zzjv() {
        return this.zzbnz;
    }

    public final zzafy zzjw() {
        return zzafx.zzy(this.zzbob);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, getManualImpressionsEnabled());
        zzxk zzxk = this.zzbnz;
        SafeParcelWriter.writeIBinder(parcel, 2, zzxk == null ? null : zzxk.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzbob, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
