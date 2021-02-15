package com.medscape.android.forceup;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.appevents.UserDataStore;
import com.ib.foreceup.ForceUpdateManager;
import com.ib.foreceup.model.UserConditions;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J*\u0010\u0007\u001a\u001e\u0012\b\u0012\u00060\tR\u00020\n\u0018\u00010\bj\u000e\u0012\b\u0012\u00060\tR\u00020\n\u0018\u0001`\u000b2\u0006\u0010\u0005\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/medscape/android/forceup/ForceUpManager;", "", "()V", "initializeForceup", "", "context", "Landroidx/appcompat/app/AppCompatActivity;", "prepareUserForceupCriteria", "Ljava/util/ArrayList;", "Lcom/ib/foreceup/model/UserConditions$UserSentCriteria;", "Lcom/ib/foreceup/model/UserConditions;", "Lkotlin/collections/ArrayList;", "Landroid/content/Context;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ForceUpManager.kt */
public final class ForceUpManager {
    public final void initializeForceup(AppCompatActivity appCompatActivity) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "context");
        ArrayList<UserConditions.UserSentCriteria> prepareUserForceupCriteria = prepareUserForceupCriteria(appCompatActivity);
        Context applicationContext = appCompatActivity.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        new ForceUpdateManager(appCompatActivity, R.xml.remote_config_defaults, applicationContext.getPackageName(), (ForceUpdateManager.UpdateListener) null).checkRemoteConfigWithReceiver(MedscapeApplication.get(), prepareUserForceupCriteria);
    }

    public final ArrayList<UserConditions.UserSentCriteria> prepareUserForceupCriteria(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!AccountProvider.isUserLoggedIn(context)) {
            return null;
        }
        ArrayList<UserConditions.UserSentCriteria> arrayList = new ArrayList<>();
        UserConditions.UserSentCriteria userSentCriteria = new UserConditions.UserSentCriteria();
        userSentCriteria.setKey("registrationInfo");
        userSentCriteria.setCriteria((HashMap) MapsKt.mapOf(TuplesKt.to("specialty_id", Settings.singleton(context).getSetting("wbmd.medscape.specialty.id", "")), TuplesKt.to(UserDataStore.COUNTRY, Settings.singleton(context).getSetting((String) Constants.USER_COUNTRY_CODE, "")), TuplesKt.to("profession_id", Settings.singleton(context).getSetting("wbmd_professionId", "")), TuplesKt.to("profession", Settings.singleton(context).getSetting((String) Constants.PROFESSION, ""))));
        arrayList.add(userSentCriteria);
        return arrayList;
    }
}
