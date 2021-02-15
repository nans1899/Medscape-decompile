package io.branch.indexing;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Indexables;
import io.branch.referral.PrefHelper;
import io.branch.referral.util.LinkProperties;
import java.lang.reflect.Method;
import org.apache.commons.io.IOUtils;

class AppIndexingHelper {
    /* access modifiers changed from: private */
    public static final LinkProperties DEF_LINK_PROPERTIES = new LinkProperties().setChannel("google_search");
    /* access modifiers changed from: private */
    public static FirebaseUserActions firebaseUserActionsInstance;

    AppIndexingHelper() {
    }

    static void addToAppIndex(final Context context, final BranchUniversalObject branchUniversalObject, final LinkProperties linkProperties) {
        new Thread(new Runnable() {
            public void run() {
                String str;
                try {
                    FirebaseUserActions unused = AppIndexingHelper.firebaseUserActionsInstance = FirebaseUserActions.getInstance();
                } catch (NoClassDefFoundError unused2) {
                    PrefHelper.Debug("BranchSDK", "Firebase app indexing is not available. Please consider enabling Firebase app indexing for your app for better indexing experience with Google.");
                } catch (Throwable unused3) {
                    PrefHelper.Debug("BranchSDK", "Failed to index your contents using Firebase. Please make sure Firebase  is enabled and initialised in your app");
                }
                LinkProperties linkProperties = linkProperties;
                if (linkProperties == null) {
                    str = branchUniversalObject.getShortUrl(context, AppIndexingHelper.DEF_LINK_PROPERTIES);
                } else {
                    str = branchUniversalObject.getShortUrl(context, linkProperties);
                }
                PrefHelper.Debug("BranchSDK", "Indexing BranchUniversalObject with Google using URL " + str);
                if (!TextUtils.isEmpty(str)) {
                    try {
                        if (AppIndexingHelper.firebaseUserActionsInstance != null) {
                            AppIndexingHelper.addToAppIndexUsingFirebase(str, branchUniversalObject);
                        } else {
                            AppIndexingHelper.listOnGoogleSearch(str, context, branchUniversalObject);
                        }
                    } catch (Throwable unused4) {
                        PrefHelper.Debug("BranchSDK", "Branch Warning: Unable to list your content in Google search. Please make sure you have added latest Firebase app indexing SDK to your project dependencies.");
                    }
                }
            }
        }).start();
    }

    static void removeFromFirebaseLocalIndex(final Context context, final BranchUniversalObject branchUniversalObject, final LinkProperties linkProperties) {
        new Thread(new Runnable() {
            public void run() {
                String str;
                try {
                    if (linkProperties == null) {
                        str = branchUniversalObject.getShortUrl(context, AppIndexingHelper.DEF_LINK_PROPERTIES);
                    } else {
                        str = branchUniversalObject.getShortUrl(context, linkProperties);
                    }
                    PrefHelper.Debug("BranchSDK", "Removing indexed BranchUniversalObject with link " + str);
                    FirebaseAppIndex.getInstance().remove(str);
                } catch (NoClassDefFoundError unused) {
                    PrefHelper.Debug("BranchSDK", "Failed to remove the BranchUniversalObject from Firebase local indexing. Please make sure Firebase is enabled and initialised in your app");
                } catch (Throwable unused2) {
                    PrefHelper.Debug("BranchSDK", "Failed to index your contents using Firebase. Please make sure Firebase is enabled and initialised in your app");
                }
            }
        }).run();
    }

    /* access modifiers changed from: private */
    public static void addToAppIndexUsingFirebase(String str, BranchUniversalObject branchUniversalObject) {
        String str2 = branchUniversalObject.getTitle() + IOUtils.LINE_SEPARATOR_UNIX + branchUniversalObject.getDescription();
        if (branchUniversalObject.isLocallyIndexable()) {
            FirebaseAppIndex.getInstance().update(Indexables.newSimple(str2, str));
        }
        firebaseUserActionsInstance.end(new Action.Builder(Action.Builder.VIEW_ACTION).setObject(str2, str).setMetadata(new Action.Metadata.Builder().setUpload(branchUniversalObject.isPublicallyIndexable())).build());
    }

    /* access modifiers changed from: private */
    public static void listOnGoogleSearch(String str, Context context, BranchUniversalObject branchUniversalObject) throws Exception {
        Class<?> cls = Class.forName("com.google.android.gms.appindexing.Thing");
        Class<?> cls2 = Class.forName("com.google.android.gms.appindexing.Thing$Builder");
        Object newInstance = cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
        Method method = cls2.getMethod("setName", new Class[]{String.class});
        Method method2 = cls2.getMethod("setDescription", new Class[]{String.class});
        Method method3 = cls2.getMethod("setUrl", new Class[]{Uri.class});
        Method method4 = cls2.getMethod("build", new Class[0]);
        method.invoke(newInstance, new Object[]{branchUniversalObject.getTitle()});
        method2.invoke(newInstance, new Object[]{branchUniversalObject.getDescription()});
        method3.invoke(newInstance, new Object[]{Uri.parse(str)});
        Object invoke = method4.invoke(newInstance, new Object[0]);
        Class<?> cls3 = Class.forName("com.google.android.gms.appindexing.Action");
        Class<?> cls4 = Class.forName("com.google.android.gms.appindexing.Action$Builder");
        Object newInstance2 = cls4.getConstructor(new Class[]{String.class}).newInstance(new Object[]{(String) cls3.getDeclaredField("TYPE_VIEW").get((Object) null)});
        Method method5 = cls4.getMethod("setObject", new Class[]{cls});
        Method method6 = cls4.getMethod("setActionStatus", new Class[]{String.class});
        Method method7 = cls4.getMethod("build", new Class[0]);
        method5.invoke(newInstance2, new Object[]{invoke});
        method6.invoke(newInstance2, new Object[]{(String) cls3.getDeclaredField("STATUS_TYPE_COMPLETED").get((Object) null)});
        Object invoke2 = method7.invoke(newInstance2, new Object[0]);
        Class<?> cls5 = Class.forName("com.google.android.gms.appindexing.AppIndex");
        Class<?> cls6 = Class.forName("com.google.android.gms.common.api.Api");
        Class<?> cls7 = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
        Class<?> cls8 = Class.forName("com.google.android.gms.common.api.GoogleApiClient$Builder");
        Object newInstance3 = cls8.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
        Method method8 = cls8.getMethod("addApi", new Class[]{cls6});
        Method method9 = cls8.getMethod("build", new Class[0]);
        Method method10 = cls7.getMethod("connect", new Class[0]);
        Method method11 = cls7.getMethod("disconnect", new Class[0]);
        method8.invoke(newInstance3, new Object[]{cls6.cast(cls5.getDeclaredField("API").get((Object) null))});
        Object invoke3 = method9.invoke(newInstance3, new Object[0]);
        method10.invoke(invoke3, new Object[0]);
        Class<?> cls9 = Class.forName("com.google.android.gms.appindexing.AppIndexApi");
        Object obj = cls5.getDeclaredField("AppIndexApi").get((Object) null);
        cls9.getMethod("start", new Class[]{cls7, cls3}).invoke(obj, new Object[]{invoke3, invoke2});
        method11.invoke(invoke3, new Object[0]);
    }
}
