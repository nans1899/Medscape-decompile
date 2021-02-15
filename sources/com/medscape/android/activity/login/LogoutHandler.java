package com.medscape.android.activity.login;

import android.app.Activity;
import android.content.Context;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.consult.util.ConsultConstants;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.db.DBLocation;
import com.wbmd.qxcalculator.model.db.DBProfession;
import com.wbmd.qxcalculator.model.db.DBSpecialty;
import com.wbmd.qxcalculator.model.db.DBUser;

public class LogoutHandler {
    public void handleLogout(Activity activity) {
        handleLogoutWithRedirect(activity, (String) null);
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [com.medscape.android.MedscapeApplication] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleLogoutWithRedirect(android.app.Activity r3, java.lang.String r4) {
        /*
            r2 = this;
            com.medscape.android.homescreen.user.UserProfileProvider r0 = com.medscape.android.homescreen.user.UserProfileProvider.INSTANCE
            r0.removeMetricUserProfileFromFile()
            com.webmd.wbmdproffesionalauthentication.providers.AccountProvider.logOut(r3)
            com.medscape.android.auth.AuthenticationManager r0 = com.medscape.android.auth.AuthenticationManager.getInstance(r3)
            com.medscape.android.consult.managers.ConsultDataManager r1 = com.medscape.android.consult.managers.ConsultDataManager.getInstance(r3, r3)
            r1.logOut()
            if (r0 == 0) goto L_0x0018
            r0.resetAuthenticationStatus()
        L_0x0018:
            com.medscape.android.Settings r0 = com.medscape.android.Settings.singleton(r3)
            com.medscape.android.homescreen.user.UserProfileProvider r1 = com.medscape.android.homescreen.user.UserProfileProvider.INSTANCE
            java.lang.String r1 = r1.getUserSpecialityIDKey(r3)
            r0.removeSetting(r1)
            r2.clearUserAuthData(r3)
            r2.clearQxUserMappedValues()
            com.medscape.android.capabilities.CapabilitiesManager r0 = com.medscape.android.capabilities.CapabilitiesManager.getInstance(r3)
            r0.clearCachedCapabilities()
            com.wbmd.wbmdcommons.caching.ICacheProvider r0 = com.medscape.android.MedscapeApplication.getCacheProvider()
            if (r0 == 0) goto L_0x003b
            r0.clear()
        L_0x003b:
            com.medscape.android.analytics.NotificationAnalyticsHandler r0 = com.medscape.android.analytics.NotificationAnalyticsHandler.INSTANCE
            r0.clearAllTags(r3)
            r2.clearCookies(r3)
            r2.delateCachedFile()
            r2.clearOtherFlags(r3)
            com.medscape.android.activity.login.LoginManager.resetGUID(r3)
            com.medscape.android.myinvites.MyInvitationsManager$Companion r0 = com.medscape.android.myinvites.MyInvitationsManager.Companion
            com.medscape.android.myinvites.MyInvitationsManager r0 = r0.get(r3)
            r1 = 0
            r0.updateOpenInvitations(r1)
            com.medscape.android.BI.omniture.OmnitureManager r0 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r0.setOmniturePrivacyStatus(r1, r3)
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.medscape.android.welcome.WelcomeActivity> r1 = com.medscape.android.welcome.WelcomeActivity.class
            r0.<init>(r3, r1)
            android.content.Intent r1 = r3.getIntent()
            android.net.Uri r1 = r1.getData()
            r0.setData(r1)
            if (r4 == 0) goto L_0x007c
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L_0x007c
            java.lang.String r1 = com.medscape.android.util.RedirectConstants.REDIRECT_BUNDLE_KEY
            r0.putExtra(r1, r4)
        L_0x007c:
            java.lang.String r4 = "extra_force_up"
            r1 = 1
            r0.putExtra(r4, r1)
            java.lang.String r4 = "com.medscape.android.EXTRA_GO_TO_LOGIN"
            r0.putExtra(r4, r1)
            r4 = 335577088(0x14008000, float:6.487592E-27)
            r0.setFlags(r4)
            r3.finish()
            com.medscape.android.MedscapeApplication r4 = com.medscape.android.MedscapeApplication.get()
            if (r4 != 0) goto L_0x0097
            goto L_0x0098
        L_0x0097:
            r3 = r4
        L_0x0098:
            if (r3 == 0) goto L_0x009d
            r3.startActivity(r0)
        L_0x009d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.login.LogoutHandler.handleLogoutWithRedirect(android.app.Activity, java.lang.String):void");
    }

    private void clearCookies(Context context) {
        Settings.singleton(context).saveSetting(DFPAdAction.NG_USERID, "");
        Settings.singleton(context).saveSetting(Constants.PREF_COOKIE_STRING, "");
    }

    private boolean delateCachedFile() {
        return FileHelper.deleteFile(FileHelper.getRssFilepath());
    }

    private void clearOtherFlags(Context context) {
        Settings.singleton(context).removeSetting(ClinicalReferenceArticleActivity.CONSULT_HINT_SHOWN);
    }

    private void clearUserAuthData(Context context) {
        Settings.singleton(context).removeSetting(Constants.USER_STATE_ID);
        Settings.singleton(context).removeSetting(Constants.HOME_PAGE_ID);
        Settings.singleton(context).removeSetting("wbmd.medscape.specialty.id");
        Settings.singleton(context).removeSetting(Constants.USER_DISPLAYNAME);
        Settings.singleton(context).removeSetting("wbmd_professionId");
        Settings.singleton(context).removeSetting(Constants.PROFESSION);
        Settings.singleton(context).removeSetting(Constants.OCCUPATION_ID);
        Settings.singleton(context).removeSetting(Constants.USER_COUNTRY_CODE);
        Settings.singleton(context).removeSetting(Constants.USER_EMAIL);
        Settings.singleton(context).removeSetting(ConsultConstants.MED_STUDENTS_FILTER_SHOW);
        Settings.singleton(context).removeSetting(ConsultConstants.MED_STUDENTS_FILTER_NOTIFICATION);
        Settings.singleton(context).removeSetting(ConsultConstants.GLOBAL_POSTS_SHOW);
        Settings.singleton(context).removeSetting(ConsultConstants.GLOBAL_POSTS_SHOW);
    }

    private void clearQxUserMappedValues() {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        dbUser.setLocation((DBLocation) null);
        dbUser.setProfession((DBProfession) null);
        dbUser.setSpecialty((DBSpecialty) null);
    }
}
