package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzby;
import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzdy extends zzdv {
    private static final String TAG = zzdy.class.getSimpleName();
    private static long startTime = 0;
    /* access modifiers changed from: private */
    public static zzdp zzxe;
    private static ExecutorService zzxf;
    /* access modifiers changed from: private */
    public static zzdtc zzxg;
    private static final Object zzxh = new Object();
    private static boolean zzxi = false;
    protected boolean zzxj = false;
    private String zzxk;
    private boolean zzxl = false;
    private boolean zzxm = false;
    private zzfi zzxn;
    private int zzxo = zzcw.zznj;

    protected static synchronized void zza(Context context, boolean z) {
        synchronized (zzdy.class) {
            if (!zzxi) {
                startTime = System.currentTimeMillis() / 1000;
                zzwg = zzb(context, z);
                zzxi = true;
            }
        }
    }

    static synchronized void zza(String str, Context context, boolean z, int i) {
        synchronized (zzdy.class) {
            if (zzxe == null) {
                if (zzq(i)) {
                    zzdtd zzavw = zzdtd.zzavv().zzhb(str).zzbq(z).zzavw();
                    zzdtc zza = zzdtc.zza(context, Executors.newFixedThreadPool(1));
                    zzxg = zza;
                    zzxe = zzdp.zza(context, zza, zzavw);
                    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
                    zzxf = newFixedThreadPool;
                    newFixedThreadPool.execute(new zzdx());
                }
            }
        }
    }

    private static boolean zzq(int i) {
        try {
            if (i == zzcw.zznj) {
                if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqo)).booleanValue()) {
                    return true;
                }
            }
        } catch (IllegalStateException unused) {
        }
        return false;
    }

    protected zzdy(Context context, String str, boolean z, int i) {
        super(context);
        this.zzxk = str;
        this.zzxj = z;
        this.zzxo = i;
    }

    private static zzex zzb(Context context, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (zzwg == null) {
            synchronized (zzxh) {
                if (zzwg == null) {
                    zzex zza = zzex.zza(context, "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", z);
                    if (zza.isInitialized()) {
                        try {
                            z2 = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcrc)).booleanValue();
                        } catch (IllegalStateException unused) {
                            z2 = false;
                        }
                        if (z2) {
                            zza.zza("TkuK+8ZKbIcxeUe4msY7eeifKf/tICuqqRvwzwQUhsKM0HemvJhBrPQYp0qpvgcE", "eNJuSXkridnHpFkTgNBQFH0ivDH801goaJfT5bONEac=", new Class[0]);
                        }
                        zza.zza("mLW4WfBtN0b1ZboDT/Xcg0iQ140V7G6lHXVBVeBNgLy2jqsT86h2d5npN9bwHugA", "7PTXHfesCwrygeE6a5SpFPYapA+6N5AjzCxH/Yeev9s=", Context.class);
                        zza.zza("6MSHSlSyck9tPP3AhA1TvP6GMucaxnzE6fuqtUKNFpo3t/1gZkhYdWZ3T7TqgVQ+", "ceQUDMmIspNePIQJbm5sD+0WYMcJxKiy+KS8DogRZko=", Context.class);
                        zza.zza("wEdejOGW/+9ddzi8bGvNCSmyvuHB7o3duM27YuSCELw0rSGojxjd9OjpDd/bvfjH", "6tksJC1oiOeEiy2PbP6Xt59/bZLk2jiIwJLpwcxJtmo=", Context.class);
                        zza.zza("H1GG/b4ANwBSbE3B+1W5cQLMERlgJYdZPa/XpdTdLAyX/s5/erDdYeSN9tVo6jKC", "F/EU4ZcvKrJZHhJGs54afTSYBM9roD2BTsVzFmIfQmM=", Context.class);
                        zza.zza("UQKiPRoyS+CnmUD46E4EQsdx5KAVcG8QUHzjpjKV7eOLJZ8IiejnQxha3R+ewm0b", "Q2/dQoYza3Uuw12qqlI5Okt59+FCPCwuUEpf8JYT3zQ=", Context.class);
                        zza.zza("yc8yVBGvbM+lDFTeqeGtXc4ZNvehxfgG+5lUS0qb9Du8+QB2SPf9wsrUE/z4yk8S", "Cr3Y6+GncptpU6DnnTxAUgghcXzA5hROF2y+XKP1eRU=", Context.class, Boolean.TYPE);
                        zza.zza("PodJLO62QvFjTRyT1s1j7Q9gO2vYuekX/f9fSujDgK0lEz9+ovbaOYnK8KkglxI5", "YNaCscR40XE3jUfiuSQHOi4SzYzHuKIdiPgG8VTOtns=", Context.class);
                        zza.zza("ZYG1jdm5AM7mUcEoXCq3rK65rJCTC1sw09mQRjZNz08G/w3QyVfe+O2dWBpXFfYE", "rSYhJJHF5kuUSeVTNPNw2nZQeRBUWQY9GRiatfzsnBI=", Context.class);
                        zza.zza("xbfft456rqtoEjzflxINvm3jB0UueLr4QkvjRWQER1VwL7sPWXVbi0ERv76eXFyQ", "ECBqiWBAFfHVW9c0fNISGmIVHjwqX6w+ErcYZElUmEc=", MotionEvent.class, DisplayMetrics.class);
                        zza.zza("JvvFzwwo66S0VRYmvytx5jLGWNK4QTG9DsWMC8EHPsa+dy60MhFDXxhSCFeMdBUA", "3iWeLGlh18NsDExlN2QIzTmA4vWzzS1+BPse+PPBjp4=", MotionEvent.class, DisplayMetrics.class);
                        zza.zza("Gi2YikSW4mz4yLeV51PuRFzLB4uKpJt5dlUqD2L9JzjHJ007dtZdVfKWEzHFdZMW", "2pYopzTvTKz5lKmw9xOg8KoJpRi+qonTMAPEuw8ei1o=", new Class[0]);
                        zza.zza("/ZhcQgKQVlzIHmNpZ4IN9grEYbbg6E0hXqW4cjuJbw/80ZDsNEcjCfIny6aSwVeJ", "VmyCEaBbgXUge3crX5DhhnNRVJcJLKw2o+4M6cwIJJA=", new Class[0]);
                        zza.zza("r4nN9klO+X23v//w8edSrlCcSaSvl9cUSUvfgTVlWHRgctE1mC8fesBDmN2+aF+P", "Dx4xeVYRCwxQri2CLTw8ZWQkMm8yQmUN/kGv/owJAr0=", new Class[0]);
                        zza.zza("XLHOfrBefh/XuKTLTjyhlPIaCxluS3pTQi+gEZfTBluRJuWX3xNYXE2jLxpQRzgB", "BgRtXwp/TdPjOMTtxgPOZvXLI0QBLAqNFbcCQtGyZIw=", new Class[0]);
                        zza.zza("Z7KH49fR2DjGspeuHX8BcHTD0uvOOHknJOx30FGv58BpyVtvGyvjuMhyW8cRn2RI", "D8c6NAmywhfnShC87wKLOPWI667JyTy6/R+sj2OrkcE=", new Class[0]);
                        zza.zza("mI4AvGY+nQt22tJsUNFln/OBC0y4peiX+clO3RuuvHbZxKoMDV9bJ0uZQRoWlvUW", "DL06yVystRGRjM8EyvmOgS0+0UCTDIf3AO1BdC6S2Xc=", new Class[0]);
                        zza.zza("Cd/6jk7/gs+AjGxTa4IWq6IxvgNYe5PwNFP6CgcViOvfWnCCQZHIXOGmxAUENuIM", "oGPxyK0MwPjhYamik95TRAfpfH6vWsbKtfhXi+EQnuc=", Context.class, Boolean.TYPE, Boolean.TYPE);
                        zza.zza("zX3km1qPLrYiG9n7dUoZFQC+zmTqD3abHbpkWV6m20n4Hps2FMxcbSPgd5Hww3wz", "jgvEncvxob+pdE8d4JYFj2otUJMxUVgOSjZFi7SPhb8=", StackTraceElement[].class);
                        zza.zza("0LbfErERsnzVecZdFdN1r+gkwDj0UWKblMs3MLLnL2Xbg6jOw+rQN6l6e1wPHG33", "1bR3VLwyKPqduFBz9kXnGy9UPty9HeyYL7t+HjE4ync=", View.class, DisplayMetrics.class, Boolean.TYPE);
                        zza.zza("Mg7hpNILFKkI7hAkw7A/iVut8RIgxPSTSSiW9E7s4cWD5OqGx03LJQgW7i+QM0lp", "X0m24tw9RfpfSH/8tn2SLvPJTtxlpwlibbKYTkjQXto=", Context.class, Boolean.TYPE);
                        zza.zza("80fAoAQlY3by/vtVyujDVOjPNRWX+VCcNQBmSADeqXB4cNvb/YoTw9GLAGSi0xdE", "26Ohttc1YMDS/slW8vGpdK8iFLV040F3RgiqDCo8vCY=", View.class, Activity.class, Boolean.TYPE);
                        zza.zza("JRUkDJhW1HFHNphIghrQ/GpgsHAYhKZrP+QjqJGAwmK1uoDv5DksWYPGE3CIg8Wl", "+XEeokV1QMMQbXE7HokZ3OcTbXy9hxPE5v4fwFjSqmg=", Long.TYPE);
                        try {
                            z3 = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcrh)).booleanValue();
                        } catch (IllegalStateException unused2) {
                            z3 = false;
                        }
                        if (z3) {
                            zza.zza("S7j7LD+X97hW9j19WIw8PL4uee7GXfPlwR/necYXNzsTAuZoEKTwM2kjDqHm05Xn", "zr0B6w2ARZzNLj2nzDGif7orJvzwcPV/ZAvZIkxUu58=", Context.class);
                        }
                        try {
                            z4 = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcrj)).booleanValue();
                        } catch (IllegalStateException unused3) {
                            z4 = false;
                        }
                        if (z4) {
                            zza.zza("KPLQ0fePjwRZEMYpyhf3z9wME5WAXo6nyi3l+jJDepzY4MR9ieVKu+2i7JuNsveg", "17DwGTsvrSwrOOIos7QWdg74ixLWLGA2Yzsqu+WYLrw=", Context.class);
                        }
                        try {
                            z5 = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcrk)).booleanValue();
                        } catch (IllegalStateException unused4) {
                            z5 = false;
                        }
                        if (z5) {
                            zza.zza("fJGzXKpU2C8iDl+Y7ANdP7v6dQ4TyTGpRfe+tJE9nXBQ6AkONmMJiKZGUd7krHwa", "ReoS3B5WMCMFdJKmPyF5hDrYSI+H3suOGmd1TWj29uY=", Context.class);
                        }
                    }
                    zzwg = zza;
                }
            }
        }
        return zzwg;
    }

    private static zzfd zza(zzex zzex, MotionEvent motionEvent, DisplayMetrics displayMetrics) throws zzeu {
        Method zza = zzex.zza("xbfft456rqtoEjzflxINvm3jB0UueLr4QkvjRWQER1VwL7sPWXVbi0ERv76eXFyQ", "ECBqiWBAFfHVW9c0fNISGmIVHjwqX6w+ErcYZElUmEc=");
        if (zza == null || motionEvent == null) {
            throw new zzeu();
        }
        try {
            return new zzfd((String) zza.invoke((Object) null, new Object[]{motionEvent, displayMetrics}));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzeu(e);
        }
    }

    /* access modifiers changed from: protected */
    public final zzcf.zza.C0009zza zza(Context context, zzby.zza zza) {
        zzcf.zza.C0009zza zzaq = zzcf.zza.zzaq();
        if (!TextUtils.isEmpty(this.zzxk)) {
            zzaq.zzv(this.zzxk);
        }
        zzex zzb = zzb(context, this.zzxj);
        if (zzb.zzcg() != null) {
            zza(zza(zzb, context, zzaq, (zzby.zza) null));
        }
        return zzaq;
    }

    public final String zzb(Context context) {
        if (zzq(this.zzxo)) {
            zzxf.execute(new zzea(this, context));
        }
        return super.zzb(context);
    }

    public final String zza(Context context, View view, Activity activity) {
        if (zzq(this.zzxo)) {
            zzxf.execute(new zzdz(this, context, view, activity));
        }
        return super.zza(context, view, activity);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        if (zzq(this.zzxo)) {
            zzxf.execute(new zzec(this, context, str, view, activity));
        }
        return super.zza(context, str, view, activity);
    }

    public final void zza(MotionEvent motionEvent) {
        if (zzq(this.zzxo)) {
            zzxf.execute(new zzeb(this, motionEvent));
        }
        super.zza(motionEvent);
    }

    public final void zza(int i, int i2, int i3) {
        if (zzq(this.zzxo)) {
            zzxf.execute(new zzee(this, i3, i, i2));
        }
        super.zza(i, i2, i3);
    }

    /* access modifiers changed from: protected */
    public final zzcf.zza.C0009zza zzc(Context context, View view, Activity activity) {
        zzcf.zza.C0009zza zzv = zzcf.zza.zzaq().zzv(this.zzxk);
        zza(zzb(context, this.zzxj), zzv, view, activity, false);
        return zzv;
    }

    /* access modifiers changed from: protected */
    public final zzcf.zza.C0009zza zzb(Context context, View view, Activity activity) {
        zzcf.zza.C0009zza zzaq = zzcf.zza.zzaq();
        if (!TextUtils.isEmpty(this.zzxk)) {
            zzaq.zzv(this.zzxk);
        }
        zza(zzb(context, this.zzxj), zzaq, view, activity, true);
        return zzaq;
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zza(zzex zzex, Context context, zzcf.zza.C0009zza zza, zzby.zza zza2) {
        int zzbu = zzex.zzbu();
        ArrayList arrayList = new ArrayList();
        if (!zzex.isInitialized()) {
            zza.zzn((long) zzcf.zza.zzd.PSN_INITIALIZATION_FAIL.zzv());
            return arrayList;
        }
        zzcf.zza.C0009zza zza3 = zza;
        zzex zzex2 = zzex;
        zzcf.zza.C0009zza zza4 = zza;
        arrayList.add(new zzfo(zzex2, "Cd/6jk7/gs+AjGxTa4IWq6IxvgNYe5PwNFP6CgcViOvfWnCCQZHIXOGmxAUENuIM", "oGPxyK0MwPjhYamik95TRAfpfH6vWsbKtfhXi+EQnuc=", zza4, zzbu, 27, context, zza2));
        arrayList.add(new zzfs(zzex2, "Gi2YikSW4mz4yLeV51PuRFzLB4uKpJt5dlUqD2L9JzjHJ007dtZdVfKWEzHFdZMW", "2pYopzTvTKz5lKmw9xOg8KoJpRi+qonTMAPEuw8ei1o=", zza4, startTime, zzbu, 25));
        int i = zzbu;
        arrayList.add(new zzfx(zzex2, "r4nN9klO+X23v//w8edSrlCcSaSvl9cUSUvfgTVlWHRgctE1mC8fesBDmN2+aF+P", "Dx4xeVYRCwxQri2CLTw8ZWQkMm8yQmUN/kGv/owJAr0=", zza4, i, 1));
        arrayList.add(new zzgc(zzex2, "wEdejOGW/+9ddzi8bGvNCSmyvuHB7o3duM27YuSCELw0rSGojxjd9OjpDd/bvfjH", "6tksJC1oiOeEiy2PbP6Xt59/bZLk2jiIwJLpwcxJtmo=", zza4, i, 31));
        arrayList.add(new zzgf(zzex2, "/ZhcQgKQVlzIHmNpZ4IN9grEYbbg6E0hXqW4cjuJbw/80ZDsNEcjCfIny6aSwVeJ", "VmyCEaBbgXUge3crX5DhhnNRVJcJLKw2o+4M6cwIJJA=", zza4, i, 33));
        arrayList.add(new zzfl(zzex2, "6MSHSlSyck9tPP3AhA1TvP6GMucaxnzE6fuqtUKNFpo3t/1gZkhYdWZ3T7TqgVQ+", "ceQUDMmIspNePIQJbm5sD+0WYMcJxKiy+KS8DogRZko=", zza4, i, 29, context));
        arrayList.add(new zzfq(zzex2, "H1GG/b4ANwBSbE3B+1W5cQLMERlgJYdZPa/XpdTdLAyX/s5/erDdYeSN9tVo6jKC", "F/EU4ZcvKrJZHhJGs54afTSYBM9roD2BTsVzFmIfQmM=", zza4, i, 5));
        arrayList.add(new zzfy(zzex2, "UQKiPRoyS+CnmUD46E4EQsdx5KAVcG8QUHzjpjKV7eOLJZ8IiejnQxha3R+ewm0b", "Q2/dQoYza3Uuw12qqlI5Okt59+FCPCwuUEpf8JYT3zQ=", zza4, i, 12));
        arrayList.add(new zzga(zzex2, "yc8yVBGvbM+lDFTeqeGtXc4ZNvehxfgG+5lUS0qb9Du8+QB2SPf9wsrUE/z4yk8S", "Cr3Y6+GncptpU6DnnTxAUgghcXzA5hROF2y+XKP1eRU=", zza4, i, 3));
        arrayList.add(new zzfp(zzex2, "XLHOfrBefh/XuKTLTjyhlPIaCxluS3pTQi+gEZfTBluRJuWX3xNYXE2jLxpQRzgB", "BgRtXwp/TdPjOMTtxgPOZvXLI0QBLAqNFbcCQtGyZIw=", zza4, i, 44));
        arrayList.add(new zzft(zzex2, "Z7KH49fR2DjGspeuHX8BcHTD0uvOOHknJOx30FGv58BpyVtvGyvjuMhyW8cRn2RI", "D8c6NAmywhfnShC87wKLOPWI667JyTy6/R+sj2OrkcE=", zza4, i, 22));
        arrayList.add(new zzgi(zzex2, "PodJLO62QvFjTRyT1s1j7Q9gO2vYuekX/f9fSujDgK0lEz9+ovbaOYnK8KkglxI5", "YNaCscR40XE3jUfiuSQHOi4SzYzHuKIdiPgG8VTOtns=", zza4, i, 48));
        arrayList.add(new zzfm(zzex2, "ZYG1jdm5AM7mUcEoXCq3rK65rJCTC1sw09mQRjZNz08G/w3QyVfe+O2dWBpXFfYE", "rSYhJJHF5kuUSeVTNPNw2nZQeRBUWQY9GRiatfzsnBI=", zza4, i, 49));
        arrayList.add(new zzgd(zzex2, "mI4AvGY+nQt22tJsUNFln/OBC0y4peiX+clO3RuuvHbZxKoMDV9bJ0uZQRoWlvUW", "DL06yVystRGRjM8EyvmOgS0+0UCTDIf3AO1BdC6S2Xc=", zza4, i, 51));
        arrayList.add(new zzgb(zzex2, "Mg7hpNILFKkI7hAkw7A/iVut8RIgxPSTSSiW9E7s4cWD5OqGx03LJQgW7i+QM0lp", "X0m24tw9RfpfSH/8tn2SLvPJTtxlpwlibbKYTkjQXto=", zza4, i, 61));
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcrk)).booleanValue()) {
            arrayList.add(new zzfv(zzex, "fJGzXKpU2C8iDl+Y7ANdP7v6dQ4TyTGpRfe+tJE9nXBQ6AkONmMJiKZGUd7krHwa", "ReoS3B5WMCMFdJKmPyF5hDrYSI+H3suOGmd1TWj29uY=", zza, zzbu, 11));
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcrh)).booleanValue()) {
            arrayList.add(new zzfz(zzex, "S7j7LD+X97hW9j19WIw8PL4uee7GXfPlwR/necYXNzsTAuZoEKTwM2kjDqHm05Xn", "zr0B6w2ARZzNLj2nzDGif7orJvzwcPV/ZAvZIkxUu58=", zza, zzbu, 73));
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcrj)).booleanValue()) {
            arrayList.add(new zzfw(zzex, "KPLQ0fePjwRZEMYpyhf3z9wME5WAXo6nyi3l+jJDepzY4MR9ieVKu+2i7JuNsveg", "17DwGTsvrSwrOOIos7QWdg74ixLWLGA2Yzsqu+WYLrw=", zza, zzbu, 76));
        }
        return arrayList;
    }

    private final void zza(zzex zzex, zzcf.zza.C0009zza zza, View view, Activity activity, boolean z) {
        List list;
        zzex zzex2 = zzex;
        zzcf.zza.C0009zza zza2 = zza;
        if (!zzex.isInitialized()) {
            zza2.zzn((long) zzcf.zza.zzd.PSN_INITIALIZATION_FAIL.zzv());
            list = Arrays.asList(new Callable[]{new zzfu(zzex2, zza2)});
        } else {
            try {
                zzfd zza3 = zza(zzex2, this.zzwl, this.zzxd);
                if (zza3.zzzp != null) {
                    zza2.zzj(zza3.zzzp.longValue());
                }
                if (zza3.zzzq != null) {
                    zza2.zzk(zza3.zzzq.longValue());
                }
                if (zza3.zzzr != null) {
                    zza2.zzl(zza3.zzzr.longValue());
                }
                if (this.zzxc) {
                    if (zza3.zzzs != null) {
                        zza2.zzu(zza3.zzzs.longValue());
                    }
                    if (zza3.zzzt != null) {
                        zza2.zzv(zza3.zzzt.longValue());
                    }
                }
            } catch (zzeu unused) {
            }
            zzcf.zza.zze.C0010zza zzav = zzcf.zza.zze.zzav();
            if (this.zzwn > 0 && zzfg.zza(this.zzxd)) {
                zzav.zzcc(zzfg.zza(this.zzwu, this.zzxd));
                zzav.zzcd(zzfg.zza((double) (this.zzwz - this.zzwx), this.zzxd)).zzce(zzfg.zza((double) (this.zzxa - this.zzwy), this.zzxd)).zzch(zzfg.zza((double) this.zzwx, this.zzxd)).zzci(zzfg.zza((double) this.zzwy, this.zzxd));
                if (this.zzxc && this.zzwl != null) {
                    long zza4 = zzfg.zza((double) (((this.zzwx - this.zzwz) + this.zzwl.getRawX()) - this.zzwl.getX()), this.zzxd);
                    if (zza4 != 0) {
                        zzav.zzcf(zza4);
                    }
                    long zza5 = zzfg.zza((double) (((this.zzwy - this.zzxa) + this.zzwl.getRawY()) - this.zzwl.getY()), this.zzxd);
                    if (zza5 != 0) {
                        zzav.zzcg(zza5);
                    }
                }
            }
            try {
                zzfd zzb = zzb(this.zzwl);
                if (zzb.zzzp != null) {
                    zzav.zzbs(zzb.zzzp.longValue());
                }
                if (zzb.zzzq != null) {
                    zzav.zzbt(zzb.zzzq.longValue());
                }
                zzav.zzby(zzb.zzzr.longValue());
                if (this.zzxc) {
                    if (zzb.zzzt != null) {
                        zzav.zzbu(zzb.zzzt.longValue());
                    }
                    if (zzb.zzzs != null) {
                        zzav.zzbw(zzb.zzzs.longValue());
                    }
                    if (zzb.zzzu != null) {
                        zzav.zzk(zzb.zzzu.longValue() != 0 ? zzcn.ENUM_TRUE : zzcn.ENUM_FALSE);
                    }
                    if (this.zzwo > 0) {
                        Long valueOf = zzfg.zza(this.zzxd) ? Long.valueOf(Math.round(((double) this.zzwt) / ((double) this.zzwo))) : null;
                        if (valueOf != null) {
                            zzav.zzbv(valueOf.longValue());
                        } else {
                            zzav.zzat();
                        }
                        zzav.zzbx(Math.round(((double) this.zzws) / ((double) this.zzwo)));
                    }
                    if (zzb.zzzx != null) {
                        zzav.zzca(zzb.zzzx.longValue());
                    }
                    if (zzb.zzzy != null) {
                        zzav.zzbz(zzb.zzzy.longValue());
                    }
                    if (zzb.zzzz != null) {
                        zzav.zzl(zzb.zzzz.longValue() != 0 ? zzcn.ENUM_TRUE : zzcn.ENUM_FALSE);
                    }
                }
            } catch (zzeu unused2) {
            }
            if (this.zzwr > 0) {
                zzav.zzcb(this.zzwr);
            }
            zza2.zza((zzcf.zza.zze) ((zzejz) zzav.zzbgt()));
            if (this.zzwn > 0) {
                zza2.zzy(this.zzwn);
            }
            if (this.zzwo > 0) {
                zza2.zzx(this.zzwo);
            }
            if (this.zzwp > 0) {
                zza2.zzw(this.zzwp);
            }
            if (this.zzwq > 0) {
                zza2.zzz(this.zzwq);
            }
            try {
                int size = this.zzwm.size() - 1;
                if (size > 0) {
                    zza.zzai();
                    for (int i = 0; i < size; i++) {
                        zzfd zza6 = zza(zzwg, (MotionEvent) this.zzwm.get(i), this.zzxd);
                        zza2.zzb((zzcf.zza.zze) ((zzejz) zzcf.zza.zze.zzav().zzbs(zza6.zzzp.longValue()).zzbt(zza6.zzzq.longValue()).zzbgt()));
                    }
                }
            } catch (zzeu unused3) {
                zza.zzai();
            }
            ArrayList arrayList = new ArrayList();
            if (zzex.zzcg() != null) {
                int zzbu = zzex.zzbu();
                arrayList.add(new zzfu(zzex2, zza2));
                zzex zzex3 = zzex;
                zzcf.zza.C0009zza zza7 = zza;
                arrayList.add(new zzfx(zzex3, "r4nN9klO+X23v//w8edSrlCcSaSvl9cUSUvfgTVlWHRgctE1mC8fesBDmN2+aF+P", "Dx4xeVYRCwxQri2CLTw8ZWQkMm8yQmUN/kGv/owJAr0=", zza7, zzbu, 1));
                arrayList.add(new zzfs(zzex3, "Gi2YikSW4mz4yLeV51PuRFzLB4uKpJt5dlUqD2L9JzjHJ007dtZdVfKWEzHFdZMW", "2pYopzTvTKz5lKmw9xOg8KoJpRi+qonTMAPEuw8ei1o=", zza7, startTime, zzbu, 25));
                int i2 = zzbu;
                arrayList.add(new zzfp(zzex3, "XLHOfrBefh/XuKTLTjyhlPIaCxluS3pTQi+gEZfTBluRJuWX3xNYXE2jLxpQRzgB", "BgRtXwp/TdPjOMTtxgPOZvXLI0QBLAqNFbcCQtGyZIw=", zza7, i2, 44));
                arrayList.add(new zzfy(zzex3, "UQKiPRoyS+CnmUD46E4EQsdx5KAVcG8QUHzjpjKV7eOLJZ8IiejnQxha3R+ewm0b", "Q2/dQoYza3Uuw12qqlI5Okt59+FCPCwuUEpf8JYT3zQ=", zza7, i2, 12));
                arrayList.add(new zzga(zzex3, "yc8yVBGvbM+lDFTeqeGtXc4ZNvehxfgG+5lUS0qb9Du8+QB2SPf9wsrUE/z4yk8S", "Cr3Y6+GncptpU6DnnTxAUgghcXzA5hROF2y+XKP1eRU=", zza7, i2, 3));
                arrayList.add(new zzft(zzex3, "Z7KH49fR2DjGspeuHX8BcHTD0uvOOHknJOx30FGv58BpyVtvGyvjuMhyW8cRn2RI", "D8c6NAmywhfnShC87wKLOPWI667JyTy6/R+sj2OrkcE=", zza7, i2, 22));
                arrayList.add(new zzfq(zzex3, "H1GG/b4ANwBSbE3B+1W5cQLMERlgJYdZPa/XpdTdLAyX/s5/erDdYeSN9tVo6jKC", "F/EU4ZcvKrJZHhJGs54afTSYBM9roD2BTsVzFmIfQmM=", zza7, i2, 5));
                arrayList.add(new zzgi(zzex3, "PodJLO62QvFjTRyT1s1j7Q9gO2vYuekX/f9fSujDgK0lEz9+ovbaOYnK8KkglxI5", "YNaCscR40XE3jUfiuSQHOi4SzYzHuKIdiPgG8VTOtns=", zza7, i2, 48));
                arrayList.add(new zzfm(zzex3, "ZYG1jdm5AM7mUcEoXCq3rK65rJCTC1sw09mQRjZNz08G/w3QyVfe+O2dWBpXFfYE", "rSYhJJHF5kuUSeVTNPNw2nZQeRBUWQY9GRiatfzsnBI=", zza7, i2, 49));
                arrayList.add(new zzgd(zzex3, "mI4AvGY+nQt22tJsUNFln/OBC0y4peiX+clO3RuuvHbZxKoMDV9bJ0uZQRoWlvUW", "DL06yVystRGRjM8EyvmOgS0+0UCTDIf3AO1BdC6S2Xc=", zza7, i2, 51));
                arrayList.add(new zzge(zzex3, "zX3km1qPLrYiG9n7dUoZFQC+zmTqD3abHbpkWV6m20n4Hps2FMxcbSPgd5Hww3wz", "jgvEncvxob+pdE8d4JYFj2otUJMxUVgOSjZFi7SPhb8=", zza7, i2, 45, new Throwable().getStackTrace()));
                arrayList.add(new zzgh(zzex3, "0LbfErERsnzVecZdFdN1r+gkwDj0UWKblMs3MLLnL2Xbg6jOw+rQN6l6e1wPHG33", "1bR3VLwyKPqduFBz9kXnGy9UPty9HeyYL7t+HjE4ync=", zza7, i2, 57, view));
                arrayList.add(new zzgb(zzex3, "Mg7hpNILFKkI7hAkw7A/iVut8RIgxPSTSSiW9E7s4cWD5OqGx03LJQgW7i+QM0lp", "X0m24tw9RfpfSH/8tn2SLvPJTtxlpwlibbKYTkjQXto=", zza7, i2, 61));
                if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqv)).booleanValue()) {
                    arrayList.add(new zzfj(zzex, "80fAoAQlY3by/vtVyujDVOjPNRWX+VCcNQBmSADeqXB4cNvb/YoTw9GLAGSi0xdE", "26Ohttc1YMDS/slW8vGpdK8iFLV040F3RgiqDCo8vCY=", zza, zzbu, 62, view, activity));
                }
                if (z) {
                    if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqx)).booleanValue()) {
                        arrayList.add(new zzgg(zzex, "JRUkDJhW1HFHNphIghrQ/GpgsHAYhKZrP+QjqJGAwmK1uoDv5DksWYPGE3CIg8Wl", "+XEeokV1QMMQbXE7HokZ3OcTbXy9hxPE5v4fwFjSqmg=", zza, zzbu, 53, this.zzxn));
                    }
                }
            }
            list = arrayList;
        }
        zza((List<Callable<Void>>) list);
    }

    private static void zza(List<Callable<Void>> list) {
        ExecutorService zzcg;
        if (zzwg != null && (zzcg = zzwg.zzcg()) != null && !list.isEmpty()) {
            try {
                zzcg.invokeAll(list, ((Long) zzwm.zzpx().zzd(zzabb.zzcqu)).longValue(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.d(TAG, String.format("class methods got exception: %s", new Object[]{zzfg.zza((Throwable) e)}));
            }
        }
    }

    /* access modifiers changed from: protected */
    public final zzfd zzb(MotionEvent motionEvent) throws zzeu {
        Method zza = zzwg.zza("JvvFzwwo66S0VRYmvytx5jLGWNK4QTG9DsWMC8EHPsa+dy60MhFDXxhSCFeMdBUA", "3iWeLGlh18NsDExlN2QIzTmA4vWzzS1+BPse+PPBjp4=");
        if (zza == null || motionEvent == null) {
            throw new zzeu();
        }
        try {
            return new zzfd((String) zza.invoke((Object) null, new Object[]{motionEvent, this.zzxd}));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzeu(e);
        }
    }

    /* access modifiers changed from: protected */
    public final long zza(StackTraceElement[] stackTraceElementArr) throws zzeu {
        Method zza = zzwg.zza("zX3km1qPLrYiG9n7dUoZFQC+zmTqD3abHbpkWV6m20n4Hps2FMxcbSPgd5Hww3wz", "jgvEncvxob+pdE8d4JYFj2otUJMxUVgOSjZFi7SPhb8=");
        if (zza == null || stackTraceElementArr == null) {
            throw new zzeu();
        }
        try {
            return new zzet((String) zza.invoke((Object) null, new Object[]{stackTraceElementArr})).zzyn.longValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzeu(e);
        }
    }

    public final void zzb(View view) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqx)).booleanValue()) {
            if (this.zzxn == null) {
                zzex zzex = zzwg;
                this.zzxn = new zzfi(zzex.zzvr, zzex.zzcn());
            }
            this.zzxn.zze(view);
        }
    }
}
