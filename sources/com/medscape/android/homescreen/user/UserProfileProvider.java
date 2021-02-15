package com.medscape.android.homescreen.user;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.Scopes;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.Settings;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.helper.CryptoHelper;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.parser.model.MetricsUserProfile;
import com.medscape.android.parser.model.UserProfile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0010\u0010\u000b\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0018\u0010\r\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/medscape/android/homescreen/user/UserProfileProvider;", "", "()V", "metricsUserProfile", "Lcom/medscape/android/parser/model/MetricsUserProfile;", "getMetricsUserProfile", "()Lcom/medscape/android/parser/model/MetricsUserProfile;", "setMetricsUserProfile", "(Lcom/medscape/android/parser/model/MetricsUserProfile;)V", "userProfile", "Lcom/medscape/android/parser/model/UserProfile;", "getUserProfile", "()Lcom/medscape/android/parser/model/UserProfile;", "setUserProfile", "(Lcom/medscape/android/parser/model/UserProfile;)V", "context", "Landroid/content/Context;", "getUserSpecialityIDKey", "", "removeMetricUserProfileFromFile", "", "saveMetricUserProfileToFile", "mProfile", "profile", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: UserProfileProvider.kt */
public final class UserProfileProvider {
    public static final UserProfileProvider INSTANCE = new UserProfileProvider();
    private static MetricsUserProfile metricsUserProfile;
    private static UserProfile userProfile;

    private UserProfileProvider() {
    }

    public final UserProfile getUserProfile() {
        return userProfile;
    }

    public final void setUserProfile(UserProfile userProfile2) {
        userProfile = userProfile2;
    }

    public final MetricsUserProfile getMetricsUserProfile() {
        return metricsUserProfile;
    }

    public final void setMetricsUserProfile(MetricsUserProfile metricsUserProfile2) {
        metricsUserProfile = metricsUserProfile2;
    }

    public final UserProfile getUserProfile(Context context) {
        UserProfile userProfile2 = userProfile;
        if (userProfile2 != null) {
            return userProfile2;
        }
        UserProfile userProfile3 = new UserProfile();
        userProfile = userProfile3;
        if (context != null) {
            if (userProfile3 != null) {
                userProfile3.setProfessionId(Settings.singleton(context).getSetting("wbmd_professionId", ""));
            }
        } else if (userProfile3 != null) {
            userProfile3.setProfessionId("");
        }
        UserProfile userProfile4 = userProfile;
        Intrinsics.checkNotNull(userProfile4);
        return userProfile4;
    }

    public final void setUserProfile(UserProfile userProfile2, Context context) {
        Intrinsics.checkNotNullParameter(userProfile2, Scopes.PROFILE);
        userProfile = userProfile2;
        MetricsUserProfile metricsUserProfile2 = new MetricsUserProfile(userProfile2);
        metricsUserProfile = metricsUserProfile2;
        Intrinsics.checkNotNull(metricsUserProfile2);
        saveMetricUserProfileToFile(metricsUserProfile2, context);
        if (context != null) {
            context.sendBroadcast(new Intent(Constants.HOMESCREEN_BROADCAST_UPDATE));
        }
    }

    public final void saveMetricUserProfileToFile(MetricsUserProfile metricsUserProfile2, Context context) {
        Intrinsics.checkNotNullParameter(metricsUserProfile2, "mProfile");
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(metricsUserProfile2);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byte[] bytes = "aBmvzkEbiaduEGse".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            byte[] encrypt = CryptoHelper.encrypt(bytes, byteArray);
            if (context == null) {
                context = MedscapeApplication.get();
            }
            File file = new File(FileHelper.getDataDirectory(context) + "/Medscape/usProf.txt");
            file.getParentFile().mkdirs();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(encrypt);
            fileOutputStream.close();
        } catch (Exception unused) {
            Log.d("MedscapeMain", "Failed to save");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:22|(2:26|27)|28|29) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0081 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e A[SYNTHETIC, Splitter:B:26:0x007e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.medscape.android.parser.model.MetricsUserProfile getMetricsUserProfile(android.content.Context r6) {
        /*
            r5 = this;
            r6 = 0
            r0 = r6
            com.medscape.android.parser.model.MetricsUserProfile r0 = (com.medscape.android.parser.model.MetricsUserProfile) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0082 }
            r1.<init>()     // Catch:{ Exception -> 0x0082 }
            com.medscape.android.MedscapeApplication r2 = com.medscape.android.MedscapeApplication.get()     // Catch:{ Exception -> 0x0082 }
            android.content.Context r2 = (android.content.Context) r2     // Catch:{ Exception -> 0x0082 }
            java.lang.String r2 = com.medscape.android.helper.FileHelper.getDataDirectory(r2)     // Catch:{ Exception -> 0x0082 }
            r1.append(r2)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r2 = "/Medscape/usProf.txt"
            r1.append(r2)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0082 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0082 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0082 }
            long r3 = r2.length()     // Catch:{ Exception -> 0x0082 }
            int r1 = (int) r3     // Catch:{ Exception -> 0x0082 }
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0082 }
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch:{ Exception -> 0x0082 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0079 }
            r3.<init>(r2)     // Catch:{ all -> 0x0079 }
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ all -> 0x0079 }
            int r6 = r3.read(r1)     // Catch:{ all -> 0x0077 }
            r2 = -1
            if (r6 == r2) goto L_0x006d
            r3.close()     // Catch:{ IOException -> 0x003e }
        L_0x003e:
            java.lang.String r6 = "aBmvzkEbiaduEGse"
            java.nio.charset.Charset r2 = kotlin.text.Charsets.UTF_8     // Catch:{ Exception -> 0x0082 }
            byte[] r6 = r6.getBytes(r2)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r2 = "(this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)     // Catch:{ Exception -> 0x0082 }
            byte[] r6 = com.medscape.android.helper.CryptoHelper.decrypt(r6, r1)     // Catch:{ Exception -> 0x0082 }
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0082 }
            r1.<init>(r6)     // Catch:{ Exception -> 0x0082 }
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0082 }
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ Exception -> 0x0082 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r6 = r6.readObject()     // Catch:{ Exception -> 0x0082 }
            if (r6 == 0) goto L_0x0065
            com.medscape.android.parser.model.MetricsUserProfile r6 = (com.medscape.android.parser.model.MetricsUserProfile) r6     // Catch:{ Exception -> 0x0082 }
            r0 = r6
            goto L_0x008d
        L_0x0065:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = "null cannot be cast to non-null type com.medscape.android.parser.model.MetricsUserProfile"
            r6.<init>(r1)     // Catch:{ Exception -> 0x0082 }
            throw r6     // Catch:{ Exception -> 0x0082 }
        L_0x006d:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "EOF reached while trying to read the whole file"
            r6.<init>(r1)     // Catch:{ all -> 0x0077 }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x0077 }
            throw r6     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r6 = move-exception
            goto L_0x007c
        L_0x0079:
            r1 = move-exception
            r3 = r6
            r6 = r1
        L_0x007c:
            if (r3 == 0) goto L_0x0081
            r3.close()     // Catch:{ IOException -> 0x0081 }
        L_0x0081:
            throw r6     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r6 = move-exception
            r6.printStackTrace()
            java.lang.String r6 = "MedscapeMain"
            java.lang.String r1 = "Failed to read"
            android.util.Log.d(r6, r1)
        L_0x008d:
            metricsUserProfile = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.homescreen.user.UserProfileProvider.getMetricsUserProfile(android.content.Context):com.medscape.android.parser.model.MetricsUserProfile");
    }

    public final void removeMetricUserProfileFromFile() {
        metricsUserProfile = null;
        boolean delete = new File(FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/usProf.txt").delete();
        Log.i("MedscapeMain", "Deleted file:" + delete);
    }

    public final String getUserSpecialityIDKey(Context context) {
        if (context == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("wbmd.medscape.user_selected_specialty.id");
        AuthenticationManager instance = AuthenticationManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.getInstance(context)");
        sb.append(instance.getMaskedGuid());
        return sb.toString();
    }
}
