package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection != null) {
            return androidIdViaReflection;
        }
        AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
        return androidIdViaService == null ? new AttributionIdentifiers() : androidIdViaService;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        Object invokeMethodQuietly;
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
                if (methodQuietly == null) {
                    return null;
                }
                Object invokeMethodQuietly2 = Utility.invokeMethodQuietly((Object) null, methodQuietly, context);
                if (invokeMethodQuietly2 instanceof Integer) {
                    if (((Integer) invokeMethodQuietly2).intValue() == 0) {
                        Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
                        if (methodQuietly2 == null || (invokeMethodQuietly = Utility.invokeMethodQuietly((Object) null, methodQuietly2, context)) == null) {
                            return null;
                        }
                        Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly.getClass(), "getId", (Class<?>[]) new Class[0]);
                        Method methodQuietly4 = Utility.getMethodQuietly(invokeMethodQuietly.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
                        if (methodQuietly3 != null) {
                            if (methodQuietly4 != null) {
                                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                                attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly3, new Object[0]);
                                attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly4, new Object[0])).booleanValue();
                                return attributionIdentifiers;
                            }
                        }
                    }
                }
                return null;
            }
            throw new FacebookException("getAndroidId cannot be called on the main thread.");
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, googleAdServiceConnection, 1)) {
            try {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            } catch (Exception e) {
                Utility.logd("android_id", e);
            } finally {
                context.unbindService(googleAdServiceConnection);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0064 A[Catch:{ Exception -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0068 A[Catch:{ Exception -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006d A[Catch:{ Exception -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r12) {
        /*
            java.lang.String r0 = "limit_tracking"
            java.lang.String r1 = "androidid"
            java.lang.String r2 = "aid"
            android.os.Looper r3 = android.os.Looper.myLooper()
            android.os.Looper r4 = android.os.Looper.getMainLooper()
            if (r3 != r4) goto L_0x0017
            java.lang.String r3 = TAG
            java.lang.String r4 = "getAttributionIdentifiers should not be called from the main thread"
            android.util.Log.e(r3, r4)
        L_0x0017:
            com.facebook.internal.AttributionIdentifiers r3 = recentlyFetchedIdentifiers
            if (r3 == 0) goto L_0x002c
            long r3 = java.lang.System.currentTimeMillis()
            com.facebook.internal.AttributionIdentifiers r5 = recentlyFetchedIdentifiers
            long r6 = r5.fetchTime
            long r3 = r3 - r6
            r6 = 3600000(0x36ee80, double:1.7786363E-317)
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x002c
            return r5
        L_0x002c:
            com.facebook.internal.AttributionIdentifiers r3 = getAndroidId(r12)
            r4 = 0
            java.lang.String[] r7 = new java.lang.String[]{r2, r1, r0}     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            android.content.pm.PackageManager r5 = r12.getPackageManager()     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            java.lang.String r6 = "com.facebook.katana.provider.AttributionIdProvider"
            r8 = 0
            android.content.pm.ProviderInfo r5 = r5.resolveContentProvider(r6, r8)     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            if (r5 == 0) goto L_0x004a
            java.lang.String r5 = "content://com.facebook.katana.provider.AttributionIdProvider"
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
        L_0x0048:
            r6 = r5
            goto L_0x005e
        L_0x004a:
            android.content.pm.PackageManager r5 = r12.getPackageManager()     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            java.lang.String r6 = "com.facebook.wakizashi.provider.AttributionIdProvider"
            android.content.pm.ProviderInfo r5 = r5.resolveContentProvider(r6, r8)     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            if (r5 == 0) goto L_0x005d
            java.lang.String r5 = "content://com.facebook.wakizashi.provider.AttributionIdProvider"
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            goto L_0x0048
        L_0x005d:
            r6 = r4
        L_0x005e:
            java.lang.String r5 = getInstallerPackageName(r12)     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            if (r5 == 0) goto L_0x0066
            r3.androidInstallerPackage = r5     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
        L_0x0066:
            if (r6 != 0) goto L_0x006d
            com.facebook.internal.AttributionIdentifiers r12 = cacheAndReturnIdentifiers(r3)     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            return r12
        L_0x006d:
            android.content.ContentResolver r5 = r12.getContentResolver()     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r12 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00cc, all -> 0x00ca }
            if (r12 == 0) goto L_0x00b7
            boolean r5 = r12.moveToFirst()     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            if (r5 != 0) goto L_0x0081
            goto L_0x00b7
        L_0x0081:
            int r2 = r12.getColumnIndex(r2)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            int r1 = r12.getColumnIndex(r1)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            int r0 = r12.getColumnIndex(r0)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            java.lang.String r2 = r12.getString(r2)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            r3.attributionId = r2     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            if (r1 <= 0) goto L_0x00ad
            if (r0 <= 0) goto L_0x00ad
            java.lang.String r2 = r3.getAndroidAdvertiserId()     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            if (r2 != 0) goto L_0x00ad
            java.lang.String r1 = r12.getString(r1)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            r3.androidAdvertiserId = r1     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            java.lang.String r0 = r12.getString(r0)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            r3.limitTracking = r0     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
        L_0x00ad:
            if (r12 == 0) goto L_0x00b2
            r12.close()
        L_0x00b2:
            com.facebook.internal.AttributionIdentifiers r12 = cacheAndReturnIdentifiers(r3)
            return r12
        L_0x00b7:
            com.facebook.internal.AttributionIdentifiers r0 = cacheAndReturnIdentifiers(r3)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            if (r12 == 0) goto L_0x00c0
            r12.close()
        L_0x00c0:
            return r0
        L_0x00c1:
            r0 = move-exception
            r4 = r12
            r12 = r0
            goto L_0x00f0
        L_0x00c5:
            r0 = move-exception
            r11 = r0
            r0 = r12
            r12 = r11
            goto L_0x00ce
        L_0x00ca:
            r12 = move-exception
            goto L_0x00f0
        L_0x00cc:
            r12 = move-exception
            r0 = r4
        L_0x00ce:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x00ee }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ee }
            r2.<init>()     // Catch:{ all -> 0x00ee }
            java.lang.String r3 = "Caught unexpected exception in getAttributionId(): "
            r2.append(r3)     // Catch:{ all -> 0x00ee }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x00ee }
            r2.append(r12)     // Catch:{ all -> 0x00ee }
            java.lang.String r12 = r2.toString()     // Catch:{ all -> 0x00ee }
            com.facebook.internal.Utility.logd((java.lang.String) r1, (java.lang.String) r12)     // Catch:{ all -> 0x00ee }
            if (r0 == 0) goto L_0x00ed
            r0.close()
        L_0x00ed:
            return r4
        L_0x00ee:
            r12 = move-exception
            r4 = r0
        L_0x00f0:
            if (r4 == 0) goto L_0x00f5
            r4.close()
        L_0x00f5:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    public static AttributionIdentifiers getCachedIdentifiers() {
        return recentlyFetchedIdentifiers;
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        if (!FacebookSdk.isInitialized() || !FacebookSdk.getAdvertiserIDCollectionEnabled()) {
            return null;
        }
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.queue.put(iBinder);
                } catch (InterruptedException unused) {
                }
            }
        }

        public IBinder getBinder() throws InterruptedException {
            if (!this.consumed.compareAndSet(true, true)) {
                return this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
