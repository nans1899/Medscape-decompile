package com.medscape.android.consult.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.util.StringUtil;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsultUtils {
    public static String getTimeSince(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        double abs = (double) Math.abs((Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis()) / 1000);
        double d = abs / 60.0d;
        if (abs < 60.0d) {
            return String.format("%ss", new Object[]{Integer.valueOf((int) Math.floor(abs))});
        } else if (d < 60.0d) {
            return String.format("%sm", new Object[]{Integer.valueOf((int) Math.floor(d))});
        } else if (d < 1440.0d) {
            return String.format("%sh", new Object[]{Integer.valueOf((int) Math.floor(d / 60.0d))});
        } else if (d < 10080.0d) {
            return String.format("%sd", new Object[]{Integer.valueOf((int) Math.floor(d / 1440.0d))});
        } else if (d < 44640.0d) {
            return String.format("%sw", new Object[]{Integer.valueOf((int) Math.floor(d / 10080.0d))});
        } else if (d < 525960.0d) {
            return String.format("%smo", new Object[]{Integer.valueOf((int) Math.floor(d / 43200.0d))});
        } else {
            return String.format("%syr", new Object[]{Integer.valueOf((int) Math.floor(d / 525600.0d))});
        }
    }

    public static String getVoteLabelWithCount(String str, int i) {
        if (i <= 0) {
            return str;
        }
        return ((str + " (") + getFormattedConsultCount(i)) + ")";
    }

    public static String getFormattedConsultCount(int i) {
        if (i < 1000) {
            return "" + String.valueOf(i);
        } else if (i < 1000000) {
            return ("" + String.valueOf(i / 1000)) + "k";
        } else if (i < 1000000000) {
            return ("" + String.valueOf(i / 1000000)) + "m";
        } else {
            return "" + "1b";
        }
    }

    public static boolean isUrlInString(String str) {
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("http://") || lowerCase.contains("https://") || lowerCase.contains("www.");
    }

    public static double[] getImageSizeOfAsset(String str) {
        double[] dArr = new double[2];
        if (StringUtil.isNotEmpty(str)) {
            Matcher matcher = Pattern.compile("size(\\d+)x(\\d+)x").matcher(str);
            if (matcher.find()) {
                String group = matcher.group(0);
                if (StringUtil.isNotEmpty(group) && group.contains("size")) {
                    try {
                        dArr[0] = (double) Integer.parseInt(group.substring(4, group.indexOf("x")));
                        dArr[1] = (double) Integer.parseInt(group.substring(group.indexOf("x") + 1, group.lastIndexOf("x")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dArr;
    }

    public static double getImageViewHeight(Context context, double[] dArr) {
        if (dArr == null) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        double d = (double) displayMetrics.density;
        double d2 = dArr[1];
        double d3 = dArr[0];
        double width = (((double) defaultDisplay.getWidth()) / d) - 10.0d;
        double d4 = 1.0d;
        if (dArr[1] > width) {
            d4 = width / d2;
        }
        return d3 * d4 * d;
    }

    public static boolean isMedstudent(ConsultUser consultUser) {
        if (consultUser == null) {
            return false;
        }
        String professionID = consultUser.getProfessionID();
        return StringUtil.isNotEmpty(professionID) && professionID.equals(UserProfile.MEDICAL_LABORATORY_ID);
    }

    public static boolean isshowSponsoredLabel(ConsultUser consultUser) {
        return consultUser != null && consultUser.canShowSponsoredLabel() && isSponsoredUser(consultUser);
    }

    public static boolean isSponsoredUser(ConsultUser consultUser) {
        return consultUser != null && StringUtil.isNotEmpty(consultUser.getUserType()) && consultUser.getUserType().equalsIgnoreCase("Sponsor");
    }

    public static boolean isPartnerPost(ConsultPost consultPost) {
        return (consultPost == null || consultPost.getPoster() == null || consultPost.getPoster().getUserType() == null || !consultPost.getPoster().getUserType().equalsIgnoreCase("Partner")) ? false : true;
    }
}
