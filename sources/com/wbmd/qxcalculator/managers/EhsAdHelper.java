package com.wbmd.qxcalculator.managers;

import com.appboy.Constants;
import com.facebook.appevents.UserDataStore;

public class EhsAdHelper {
    public static String getProfessionTag() {
        Long identifier = UserManager.getInstance().getDbUser().getProfession().getIdentifier();
        if (identifier == null) {
            return null;
        }
        if (identifier.equals(4L)) {
            return Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID;
        }
        if (identifier.equals(22L)) {
            return "np";
        }
        if (identifier.equals(5L)) {
            return "ot";
        }
        if (identifier.equals(6L)) {
            return UserDataStore.PHONE;
        }
        if (identifier.equals(7L)) {
            return "md";
        }
        if (identifier.equals(17L)) {
            return "pa";
        }
        if (identifier.equals(9L)) {
            return "md";
        }
        return null;
    }

    public static String getSpecialtyTag() {
        Long identifier = UserManager.getInstance().getDbUser().getSpecialty().getIdentifier();
        if (identifier == null) {
            return null;
        }
        if (identifier.equals(16L)) {
            return "a1";
        }
        if (identifier.equals(17L)) {
            return "a2";
        }
        if (identifier.equals(18L)) {
            return "c3";
        }
        if (identifier.equals(19L)) {
            return "c4";
        }
        if (identifier.equals(20L)) {
            return "d5";
        }
        if (identifier.equals(22L)) {
            return "e6";
        }
        if (identifier.equals(23L)) {
            return "e7";
        }
        if (identifier.equals(24L)) {
            return "f9";
        }
        if (identifier.equals(25L)) {
            return "g10";
        }
        if (identifier.equals(26L)) {
            return "g12";
        }
        if (identifier.equals(27L)) {
            return "h13";
        }
        if (identifier.equals(28L)) {
            return "i18";
        }
        if (identifier.equals(29L)) {
            return "i19";
        }
        if (identifier.equals(30L)) {
            return "n21";
        }
        if (identifier.equals(31L)) {
            return "n23";
        }
        if (identifier.equals(32L)) {
            return "n22";
        }
        if (identifier.equals(33L)) {
            return "o24";
        }
        if (identifier.equals(35L)) {
            return "o26";
        }
        if (identifier.equals(36L)) {
            return "o27";
        }
        if (identifier.equals(38L)) {
            return "o28";
        }
        if (identifier.equals(39L)) {
            return "p29";
        }
        if (identifier.equals(40L)) {
            return "p30";
        }
        if (identifier.equals(41L)) {
            return "p31";
        }
        if (identifier.equals(42L)) {
            return "p36";
        }
        if (identifier.equals(43L)) {
            return "p38";
        }
        if (identifier.equals(44L)) {
            return "r41";
        }
        if (identifier.equals(46L)) {
            return "r40";
        }
        if (identifier.equals(47L)) {
            return "r43";
        }
        if (identifier.equals(48L) || identifier.equals(49L) || identifier.equals(50L)) {
            return "s45";
        }
        if (identifier.equals(51L)) {
            return "p34";
        }
        if (identifier.equals(52L)) {
            return "o25";
        }
        if (identifier.equals(53L)) {
            return "v48";
        }
        if (identifier.equals(54L)) {
            return "s45";
        }
        if (identifier.equals(55L)) {
            return "u47";
        }
        return null;
    }
}
