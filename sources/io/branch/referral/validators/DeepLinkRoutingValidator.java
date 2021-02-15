package io.branch.referral.validators;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

public class DeepLinkRoutingValidator {
    private static final String BRANCH_VALIDATE_TEST_KEY = "_branch_validate";
    private static final int BRANCH_VALIDATE_TEST_VALUE = 60514;
    private static final int LAUNCH_TEST_TEMPLATE_DELAY = 500;
    private static final String URI_REDIRECT_KEY = "$uri_redirect_mode";
    private static final String URI_REDIRECT_MODE = "2";
    private static final String VALIDATE_LINK_PARAM_KEY = "validate";
    private static final String VALIDATE_SDK_LINK_PARAM_KEY = "bnc_validate";
    private static WeakReference<Activity> current_activity_reference;

    public static void validate(WeakReference<Activity> weakReference) {
        current_activity_reference = weakReference;
        if (!TextUtils.isEmpty(getLatestReferringLink()) && weakReference != null) {
            final JSONObject latestReferringParams = Branch.getInstance().getLatestReferringParams();
            if (latestReferringParams.optInt(BRANCH_VALIDATE_TEST_KEY) == BRANCH_VALIDATE_TEST_VALUE) {
                if (latestReferringParams.optBoolean(Defines.Jsonkey.Clicked_Branch_Link.getKey())) {
                    validateDeeplinkRouting(latestReferringParams);
                } else {
                    displayErrorMessage();
                }
            } else if (latestReferringParams.optBoolean(VALIDATE_SDK_LINK_PARAM_KEY)) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        DeepLinkRoutingValidator.launchTestTemplate(DeepLinkRoutingValidator.getUpdatedLinkWithTestStat(latestReferringParams, ""));
                    }
                }, 500);
            }
        }
    }

    private static void validateDeeplinkRouting(final JSONObject jSONObject) {
        AlertDialog.Builder builder;
        if (current_activity_reference.get() != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                builder = new AlertDialog.Builder((Context) current_activity_reference.get(), 16974374);
            } else {
                builder = new AlertDialog.Builder((Context) current_activity_reference.get());
            }
            builder.setTitle("Branch Deeplinking Routing").setMessage("Good news - we got link data. Now a question for you, astute developer: did the app deep link to the specific piece of content you expected to see?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DeepLinkRoutingValidator.launchTestTemplate(DeepLinkRoutingValidator.getUpdatedLinkWithTestStat(jSONObject, "g"));
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DeepLinkRoutingValidator.launchTestTemplate(DeepLinkRoutingValidator.getUpdatedLinkWithTestStat(jSONObject, "r"));
                }
            }).setNeutralButton(17039360, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).setCancelable(false).setIcon(17301651).show();
        }
    }

    /* access modifiers changed from: private */
    public static void launchTestTemplate(String str) {
        if (current_activity_reference.get() != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str).buildUpon().appendQueryParameter(URI_REDIRECT_KEY, "2").build());
            intent.addFlags(268435456);
            intent.setPackage("com.android.chrome");
            ((Activity) current_activity_reference.get()).getPackageManager().queryIntentActivities(intent, 0);
            try {
                ((Activity) current_activity_reference.get()).startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                intent.setPackage((String) null);
                ((Activity) current_activity_reference.get()).startActivity(intent);
            }
        }
    }

    /* access modifiers changed from: private */
    public static String getUpdatedLinkWithTestStat(JSONObject jSONObject, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = "";
        try {
            str7 = jSONObject.getString("~" + Defines.Jsonkey.ReferringLink.getKey()).split("\\?")[0];
        } catch (Exception unused) {
            Log.e("BRANCH SDK", "Failed to get referring link");
        }
        String str8 = str7 + "?validate=true";
        if (!TextUtils.isEmpty(str)) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(str8);
                if (jSONObject.getString("ct").equals("t1")) {
                    str2 = "&t1=" + str;
                } else {
                    str2 = "&t1=" + jSONObject.getString("t1");
                }
                sb.append(str2);
                str8 = sb.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str8);
                if (jSONObject.getString("ct").equals("t2")) {
                    str3 = "&t2=" + str;
                } else {
                    str3 = "&t2=" + jSONObject.getString("t2");
                }
                sb2.append(str3);
                str8 = sb2.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str8);
                if (jSONObject.getString("ct").equals("t3")) {
                    str4 = "&t3=" + str;
                } else {
                    str4 = "&t3=" + jSONObject.getString("t3");
                }
                sb3.append(str4);
                str8 = sb3.toString();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str8);
                if (jSONObject.getString("ct").equals("t4")) {
                    str5 = "&t4=" + str;
                } else {
                    str5 = "&t4=" + jSONObject.getString("t4");
                }
                sb4.append(str5);
                str8 = sb4.toString();
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str8);
                if (jSONObject.getString("ct").equals("t5")) {
                    str6 = "&t5=" + str;
                } else {
                    str6 = "&t5=" + jSONObject.getString("t5");
                }
                sb5.append(str6);
                str8 = sb5.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str8 + "&os=android";
    }

    private static void displayErrorMessage() {
        AlertDialog.Builder builder;
        if (current_activity_reference.get() != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                builder = new AlertDialog.Builder((Context) current_activity_reference.get(), 16974374);
            } else {
                builder = new AlertDialog.Builder((Context) current_activity_reference.get());
            }
            builder.setTitle("Branch Deeplink Routing Support").setMessage("Bummer. It seems like +clicked_branch_link is false - we didn't deep link.  Double check that the link you're clicking has the same branch_key that is being used in your Manifest file. Return to Chrome when you're ready to test again.").setNeutralButton("Got it", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).setCancelable(false).setIcon(17301651).show();
        }
    }

    private static String getLatestReferringLink() {
        if (Branch.getInstance() == null || Branch.getInstance().getLatestReferringParams() == null) {
            return "";
        }
        JSONObject latestReferringParams = Branch.getInstance().getLatestReferringParams();
        return latestReferringParams.optString("~" + Defines.Jsonkey.ReferringLink.getKey());
    }
}
