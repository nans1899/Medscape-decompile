package com.medscape.android.view.nav;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.FeedMaster;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class NavMenu {
    private ArrayList<NavMenuItem> mNavMenuItems;
    private LinkedHashMap<NavMenuPosition, NavMenuItem> mNavMenuItemsMap;

    public enum NavMenuPosition {
        HOME(0),
        NEWS(1),
        EDUCATION(2),
        SPECIALTIES(3),
        MYINVITATIONS(4),
        USERINFO(5),
        SAVED(6),
        DATAUPDATES(7),
        HELP(8),
        NOTIFICATIONS(9);
        
        private final int position;

        private NavMenuPosition(int i) {
            this.position = i;
        }

        public static NavMenuPosition get(int i) {
            switch (i) {
                case 0:
                    return HOME;
                case 1:
                    return NEWS;
                case 2:
                    return EDUCATION;
                case 3:
                    return SPECIALTIES;
                case 4:
                    return MYINVITATIONS;
                case 5:
                    return USERINFO;
                case 6:
                    return SAVED;
                case 7:
                    return DATAUPDATES;
                case 8:
                    return HELP;
                case 9:
                    return NOTIFICATIONS;
                default:
                    return HOME;
            }
        }

        public int getPosition() {
            return this.position;
        }
    }

    public NavMenu(Context context) {
        ArrayList<NavMenuItem> arrayList = new ArrayList<>();
        this.mNavMenuItems = arrayList;
        Context context2 = context;
        arrayList.add(new NavMenuItem(context2, NavMenuPosition.HOME, R.string.drawer_title_home, 7, (String) null, false));
        this.mNavMenuItems.add(new NavMenuItem(context2, NavMenuPosition.NEWS, R.string.drawer_title_news, 3, (String) null, false));
        this.mNavMenuItems.add(new NavMenuItem(context2, NavMenuPosition.EDUCATION, R.string.drawer_title_education, 4, (String) null, true));
        this.mNavMenuItems.add(new NavMenuItem(context2, NavMenuPosition.SPECIALTIES, R.string.drawer_title_specialties, 13, getUserSelectedHomepageSpecialty(), false));
        this.mNavMenuItems.add(new NavMenuItem(context2, NavMenuPosition.MYINVITATIONS, R.string.drawer_title_invitations, 19, (String) null, true));
        this.mNavMenuItems.add(new NavMenuItem(NavMenuPosition.USERINFO, getUserDisplayName(), -999, getUserLoggedInState(), false));
        Context context3 = context;
        this.mNavMenuItems.add(new NavMenuItem(context3, NavMenuPosition.SAVED, R.string.drawer_title_saved, 11, (String) null, false));
        this.mNavMenuItems.add(new NavMenuItem(context3, NavMenuPosition.DATAUPDATES, R.string.drawer_title_data, 10, (String) null, false));
        this.mNavMenuItems.add(new NavMenuItem(context3, NavMenuPosition.HELP, R.string.drawer_title_help, 9, (String) null, false));
        this.mNavMenuItems.add(new NavMenuItem(context3, NavMenuPosition.NOTIFICATIONS, R.string.drawer_title_notifications, 20, (String) null, false));
        LinkedHashMap<NavMenuPosition, NavMenuItem> linkedHashMap = new LinkedHashMap<>();
        this.mNavMenuItemsMap = linkedHashMap;
        Context context4 = context;
        linkedHashMap.put(NavMenuPosition.HOME, new NavMenuItem(context4, NavMenuPosition.HOME, R.string.drawer_title_home, 7, (String) null, false));
        this.mNavMenuItemsMap.put(NavMenuPosition.NEWS, new NavMenuItem(context4, NavMenuPosition.NEWS, R.string.drawer_title_news, 3, (String) null, false));
        this.mNavMenuItemsMap.put(NavMenuPosition.EDUCATION, new NavMenuItem(context4, NavMenuPosition.EDUCATION, R.string.drawer_title_education, 4, (String) null, true));
        this.mNavMenuItemsMap.put(NavMenuPosition.SPECIALTIES, new NavMenuItem(context4, NavMenuPosition.SPECIALTIES, R.string.drawer_title_specialties, 13, getUserSelectedHomepageSpecialty(), false));
        this.mNavMenuItemsMap.put(NavMenuPosition.MYINVITATIONS, new NavMenuItem(context4, NavMenuPosition.MYINVITATIONS, R.string.drawer_title_invitations, 19, (String) null, true));
        this.mNavMenuItemsMap.put(NavMenuPosition.USERINFO, new NavMenuItem(NavMenuPosition.USERINFO, getUserDisplayName(), -999, getUserLoggedInState(), false));
        Context context5 = context;
        this.mNavMenuItemsMap.put(NavMenuPosition.SAVED, new NavMenuItem(context5, NavMenuPosition.SAVED, R.string.drawer_title_saved, 11, (String) null, false));
        this.mNavMenuItemsMap.put(NavMenuPosition.DATAUPDATES, new NavMenuItem(context5, NavMenuPosition.DATAUPDATES, R.string.drawer_title_data, 10, (String) null, false));
        this.mNavMenuItemsMap.put(NavMenuPosition.HELP, new NavMenuItem(context5, NavMenuPosition.HELP, R.string.drawer_title_help, 9, (String) null, false));
        this.mNavMenuItemsMap.put(NavMenuPosition.NOTIFICATIONS, new NavMenuItem(context5, NavMenuPosition.HELP, R.string.drawer_title_notifications, 20, (String) null, false));
    }

    public ArrayList<NavMenuItem> getArray() {
        return this.mNavMenuItems;
    }

    public NavMenuItem getItem(int i) {
        return this.mNavMenuItemsMap.get(NavMenuPosition.get(i));
    }

    public void updateLoginState() {
        this.mNavMenuItems.set(NavMenuPosition.SPECIALTIES.getPosition(), new NavMenuItem(NavMenuPosition.SPECIALTIES, "Specialties", -1, getUserSelectedHomepageSpecialty(), false));
        this.mNavMenuItems.set(NavMenuPosition.USERINFO.getPosition(), new NavMenuItem(NavMenuPosition.USERINFO, getUserDisplayName(), -1, getUserLoggedInState(), false));
    }

    public void confirmLogOut() {
        this.mNavMenuItems.set(NavMenuPosition.USERINFO.getPosition(), new NavMenuItem(NavMenuPosition.USERINFO, MedscapeApplication.get().getString(R.string.logout_alert_option_message), -1, getUserLoggedInState(), false));
        this.mNavMenuItemsMap.put(NavMenuPosition.USERINFO, new NavMenuItem(NavMenuPosition.USERINFO, MedscapeApplication.get().getString(R.string.logout_alert_option_message), -1, getUserLoggedInState(), false));
    }

    public void resetLogOut() {
        this.mNavMenuItems.set(NavMenuPosition.USERINFO.getPosition(), new NavMenuItem(NavMenuPosition.USERINFO, getUserDisplayName(), -1, getUserLoggedInState(), false));
        this.mNavMenuItemsMap.put(NavMenuPosition.USERINFO, new NavMenuItem(NavMenuPosition.USERINFO, getUserDisplayName(), -1, getUserLoggedInState(), false));
    }

    public void updateSpecialityState() {
        this.mNavMenuItems.set(NavMenuPosition.SPECIALTIES.getPosition(), new NavMenuItem(NavMenuPosition.SPECIALTIES, "Specialties", -1, getUserSelectedHomepageSpecialty(), false));
        this.mNavMenuItemsMap.put(NavMenuPosition.SPECIALTIES, new NavMenuItem(NavMenuPosition.SPECIALTIES, "Specialties", -1, getUserSelectedHomepageSpecialty(), false));
    }

    public String getUserDisplayName() {
        return Settings.singleton(MedscapeApplication.get()).getSetting(Constants.USER_DISPLAYNAME, "Log In");
    }

    public String getUserLoggedInState() {
        if (Settings.singleton(MedscapeApplication.get()).getSetting(Constants.USER_DISPLAYNAME, (String) null) != null) {
            return "Log out";
        }
        return null;
    }

    public String getUserSelectedHomepageSpecialty() {
        if (!AccountProvider.isUserLoggedIn(MedscapeApplication.get())) {
            return "";
        }
        return FeedMaster.getSpecialtyNameOrDefaultBySpecialtyId(new DatabaseHelper(MedscapeApplication.get()), Settings.singleton(MedscapeApplication.get()).getSetting(UserProfileProvider.INSTANCE.getUserSpecialityIDKey(MedscapeApplication.get()), ""), ExifInterface.GPS_MEASUREMENT_2D);
    }

    public int getMenuAction(int i) {
        return this.mNavMenuItems.get(i).mMenuAction;
    }

    public int getMenuPosition(int i) {
        Iterator<NavMenuItem> it = this.mNavMenuItems.iterator();
        while (it.hasNext()) {
            NavMenuItem next = it.next();
            if (next.mMenuAction == i) {
                return next.mPosition;
            }
        }
        return -1;
    }
}
