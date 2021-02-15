package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzauo implements RewardedVideoAd {
    private final Object lock = new Object();
    private final zzaud zzdvx;
    private final zzaun zzdvy = new zzaun((RewardedVideoAdListener) null);
    private String zzdvz;
    private String zzdwa;
    private final Context zzvr;

    public zzauo(Context context, zzaud zzaud) {
        this.zzdvx = zzaud == null ? new zzaaa() : zzaud;
        this.zzvr = context.getApplicationContext();
    }

    private final void zza(String str, zzyw zzyw) {
        synchronized (this.lock) {
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.zza(zzvl.zza(this.zzvr, zzyw, str));
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void loadAd(String str, AdRequest adRequest) {
        zza(str, adRequest.zzdr());
    }

    public final void loadAd(String str, PublisherAdRequest publisherAdRequest) {
        zza(str, publisherAdRequest.zzdr());
    }

    public final void show() {
        synchronized (this.lock) {
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.show();
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.lock) {
            this.zzdvy.setRewardedVideoAdListener(rewardedVideoAdListener);
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.zza((zzaug) this.zzdvy);
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener) {
        synchronized (this.lock) {
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.zza((zzxj) new zzvh(adMetadataListener));
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final Bundle getAdMetadata() {
        synchronized (this.lock) {
            if (this.zzdvx != null) {
                try {
                    Bundle adMetadata = this.zzdvx.getAdMetadata();
                    return adMetadata;
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
            Bundle bundle = new Bundle();
            return bundle;
        }
    }

    public final void setUserId(String str) {
        synchronized (this.lock) {
            this.zzdvz = str;
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.setUserId(str);
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final boolean isLoaded() {
        synchronized (this.lock) {
            if (this.zzdvx == null) {
                return false;
            }
            try {
                boolean isLoaded = this.zzdvx.isLoaded();
                return isLoaded;
            } catch (RemoteException e) {
                zzbbq.zze("#007 Could not call remote method.", e);
                return false;
            }
        }
    }

    public final void pause() {
        pause((Context) null);
    }

    public final void pause(Context context) {
        synchronized (this.lock) {
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.zzj(ObjectWrapper.wrap(context));
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void resume() {
        resume((Context) null);
    }

    public final void resume(Context context) {
        synchronized (this.lock) {
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.zzk(ObjectWrapper.wrap(context));
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void destroy() {
        destroy((Context) null);
    }

    public final void destroy(Context context) {
        synchronized (this.lock) {
            this.zzdvy.setRewardedVideoAdListener((RewardedVideoAdListener) null);
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.zzl(ObjectWrapper.wrap(context));
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        RewardedVideoAdListener rewardedVideoAdListener;
        synchronized (this.lock) {
            rewardedVideoAdListener = this.zzdvy.getRewardedVideoAdListener();
        }
        return rewardedVideoAdListener;
    }

    public final String getUserId() {
        String str;
        synchronized (this.lock) {
            str = this.zzdvz;
        }
        return str;
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzdvx != null) {
                return this.zzdvx.getMediationAdapterClassName();
            }
            return null;
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final void setImmersiveMode(boolean z) {
        synchronized (this.lock) {
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.setImmersiveMode(z);
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void setCustomData(String str) {
        synchronized (this.lock) {
            if (this.zzdvx != null) {
                try {
                    this.zzdvx.setCustomData(str);
                    this.zzdwa = str;
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final String getCustomData() {
        String str;
        synchronized (this.lock) {
            str = this.zzdwa;
        }
        return str;
    }

    public final ResponseInfo getResponseInfo() {
        zzyn zzyn = null;
        try {
            if (this.zzdvx != null) {
                zzyn = this.zzdvx.zzki();
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
        return ResponseInfo.zza(zzyn);
    }
}
