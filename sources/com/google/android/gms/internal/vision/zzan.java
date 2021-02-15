package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class zzan extends zzs<zzad> {
    private final zzam zzem;

    public zzan(Context context, zzam zzam) {
        super(context, "TextNativeHandle", "ocr");
        this.zzem = zzam;
        zzr();
    }

    public final zzah[] zza(Bitmap bitmap, zzu zzu, zzaj zzaj) {
        if (!isOperational()) {
            return new zzah[0];
        }
        try {
            return ((zzad) zzr()).zza(ObjectWrapper.wrap(bitmap), zzu, zzaj);
        } catch (RemoteException e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new zzah[0];
        }
    }

    /* access modifiers changed from: protected */
    public final void zzp() throws RemoteException {
        ((zzad) zzr()).zzs();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzaf zzaf;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        if (instantiate == null) {
            zzaf = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            if (queryLocalInterface instanceof zzaf) {
                zzaf = (zzaf) queryLocalInterface;
            } else {
                zzaf = new zzae(instantiate);
            }
        }
        if (zzaf == null) {
            return null;
        }
        return zzaf.zza(ObjectWrapper.wrap(context), this.zzem);
    }
}
