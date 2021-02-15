package com.medscape.android.ads.proclivity;

import android.content.Context;
import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.EnvironmentManager;

public class ProclivityUrls {
    public static String getProclivityUrl(Context context) {
        return "https://api" + getUrlPrefix(context) + ".medscape.com/adpredictionservice/transform";
    }

    private static String getUrlPrefix(Context context) {
        if (context == null) {
            return "";
        }
        String environmentWithDefault = new EnvironmentManager().getEnvironmentWithDefault(context, EnvironmentConstants.MODULE_SERVICE);
        char c = 65535;
        int hashCode = environmentWithDefault.hashCode();
        if (hashCode != 446124970) {
            if (hashCode != 568937877) {
                switch (hashCode) {
                    case 568961724:
                        if (environmentWithDefault.equals(EnvironmentConstants.QA00)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 568961725:
                        if (environmentWithDefault.equals(EnvironmentConstants.QA01)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 568961726:
                        if (environmentWithDefault.equals(EnvironmentConstants.QA02)) {
                            c = 2;
                            break;
                        }
                        break;
                }
            } else if (environmentWithDefault.equals(EnvironmentConstants.PERF)) {
                c = 4;
            }
        } else if (environmentWithDefault.equals(EnvironmentConstants.DEV01)) {
            c = 3;
        }
        if (c == 0) {
            return ".qa00";
        }
        if (c == 1) {
            return ".qa01";
        }
        if (c == 2) {
            return ".qa02";
        }
        if (c == 3) {
            return ".dev01";
        }
        if (c != 4) {
            return "";
        }
        return ".perf";
    }
}
