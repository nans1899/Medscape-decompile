package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.media.Image;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.vision.zzs;
import com.google.android.gms.internal.vision.zzu;
import com.google.android.gms.internal.vision.zzw;
import com.google.android.gms.vision.face.Contour;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class zzb extends zzs<zzh> {
    private final zzf zzdg;

    public zzb(Context context, zzf zzf) {
        super(context, "FaceNativeHandle", "face");
        this.zzdg = zzf;
        zzr();
    }

    public final Face[] zzb(ByteBuffer byteBuffer, zzu zzu) {
        if (!isOperational()) {
            return new Face[0];
        }
        try {
            FaceParcel[] zzc = ((zzh) zzr()).zzc(ObjectWrapper.wrap(byteBuffer), zzu);
            Face[] faceArr = new Face[zzc.length];
            for (int i = 0; i < zzc.length; i++) {
                faceArr[i] = zza(zzc[i]);
            }
            return faceArr;
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new Face[0];
        }
    }

    public final Face[] zza(Image.Plane[] planeArr, zzu zzu) {
        Image.Plane[] planeArr2 = planeArr;
        if (!isOperational()) {
            Log.e("FaceNativeHandle", "Native handle is not ready to be used.");
            return new Face[0];
        } else if (planeArr2 == null || planeArr2.length == 3) {
            try {
                IObjectWrapper wrap = ObjectWrapper.wrap(planeArr2[0].getBuffer());
                IObjectWrapper wrap2 = ObjectWrapper.wrap(planeArr2[1].getBuffer());
                IObjectWrapper wrap3 = ObjectWrapper.wrap(planeArr2[2].getBuffer());
                int pixelStride = planeArr2[0].getPixelStride();
                int pixelStride2 = planeArr2[1].getPixelStride();
                int pixelStride3 = planeArr2[2].getPixelStride();
                int rowStride = planeArr2[0].getRowStride();
                FaceParcel[] zza = ((zzh) zzr()).zza(wrap, wrap2, wrap3, pixelStride, pixelStride2, pixelStride3, rowStride, planeArr2[1].getRowStride(), planeArr2[2].getRowStride(), zzu);
                Face[] faceArr = new Face[zza.length];
                for (int i = 0; i < zza.length; i++) {
                    faceArr[i] = zza(zza[i]);
                }
                return faceArr;
            } catch (RemoteException e) {
                Log.e("FaceNativeHandle", "Could not call native face detector", e);
                return new Face[0];
            }
        } else {
            throw new IllegalArgumentException("Only android.graphics.ImageFormat#YUV_420_888 is supported which should have 3 planes.");
        }
    }

    public final boolean zzd(int i) {
        if (!isOperational()) {
            return false;
        }
        try {
            return ((zzh) zzr()).zzd(i);
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzp() throws RemoteException {
        ((zzh) zzr()).zzo();
    }

    private static Face zza(FaceParcel faceParcel) {
        Landmark[] landmarkArr;
        Contour[] contourArr;
        FaceParcel faceParcel2 = faceParcel;
        int i = faceParcel2.id;
        PointF pointF = new PointF(faceParcel2.centerX, faceParcel2.centerY);
        float f = faceParcel2.width;
        float f2 = faceParcel2.height;
        float f3 = faceParcel2.zzdh;
        float f4 = faceParcel2.zzdi;
        float f5 = faceParcel2.zzdj;
        LandmarkParcel[] landmarkParcelArr = faceParcel2.zzdk;
        if (landmarkParcelArr == null) {
            landmarkArr = new Landmark[0];
        } else {
            Landmark[] landmarkArr2 = new Landmark[landmarkParcelArr.length];
            int i2 = 0;
            while (i2 < landmarkParcelArr.length) {
                LandmarkParcel landmarkParcel = landmarkParcelArr[i2];
                landmarkArr2[i2] = new Landmark(new PointF(landmarkParcel.x, landmarkParcel.y), landmarkParcel.type);
                i2++;
                landmarkParcelArr = landmarkParcelArr;
            }
            landmarkArr = landmarkArr2;
        }
        zza[] zzaArr = faceParcel2.zzdl;
        if (zzaArr == null) {
            contourArr = new Contour[0];
        } else {
            Contour[] contourArr2 = new Contour[zzaArr.length];
            for (int i3 = 0; i3 < zzaArr.length; i3++) {
                zza zza = zzaArr[i3];
                contourArr2[i3] = new Contour(zza.zzdf, zza.type);
            }
            contourArr = contourArr2;
        }
        return new Face(i, pointF, f, f2, f3, f4, f5, landmarkArr, contourArr, faceParcel2.zzcm, faceParcel2.zzcn, faceParcel2.zzco, faceParcel2.zzcp);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzi zzi;
        if (zzw.zza(context, "com.google.android.gms.vision.dynamite.face")) {
            zzi = zzl.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.NativeFaceDetectorV2Creator"));
        } else {
            zzi = zzl.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
        }
        if (zzi == null) {
            return null;
        }
        return zzi.newFaceDetector(ObjectWrapper.wrap(context), this.zzdg);
    }
}
