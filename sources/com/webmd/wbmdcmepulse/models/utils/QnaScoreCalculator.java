package com.webmd.wbmdcmepulse.models.utils;

import com.webmd.wbmdcmepulse.models.articles.Eligibility;
import com.webmd.wbmdproffesionalauthentication.model.UserProfession;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.util.List;

public class QnaScoreCalculator {
    public static double getCurrentScore(int i, int i2) {
        if (i == 0) {
            return 100.0d;
        }
        return (1.0d - (((double) i) / ((double) i2))) * 100.0d;
    }

    public static double getPassingScore(List<Eligibility> list, UserProfile userProfile) {
        if (userProfile == null) {
            return 75.0d;
        }
        UserProfession professionProfile = userProfile.getProfessionProfile();
        int eligibilityTypeFromProfessionId = getEligibilityTypeFromProfessionId(professionProfile.getProfessionId(), professionProfile.getOccupation());
        for (Eligibility next : list) {
            if (eligibilityTypeFromProfessionId == next.type) {
                return next.passingScore;
            }
        }
        return 75.0d;
    }

    public static int getEligibilityTypeFromProfessionId(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        if (str.equals(UserProfile.PHYSICIAN_ID)) {
            return 1;
        }
        if (str.equals(UserProfile.PHYS_ASST_ID)) {
            return 8;
        }
        if (str.equals(UserProfile.NURSE_ID) && str2.equals(UserProfile.NURSE_PRACTITIONER_ID)) {
            return 7;
        }
        if (str.equals(UserProfile.NURSE_ID) && !str2.equals(UserProfile.STUDENT_ID) && !str2.equals(UserProfile.MIDWIFE_ID)) {
            return 2;
        }
        if (str.equals(UserProfile.PHARMACIST_ID)) {
            return 3;
        }
        if (str2.equals(UserProfile.PSYCHOLOGIST_ID)) {
            return 4;
        }
        return str2.equals(UserProfile.MEDICAL_LABORATORY_ID) ? 5 : 6;
    }
}
