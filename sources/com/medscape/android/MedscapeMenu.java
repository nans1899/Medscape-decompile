package com.medscape.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.MenuItem;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.Scopes;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.activity.DebugSettingsActivity;
import com.medscape.android.activity.calc.CalcArticleActivity;
import com.medscape.android.activity.calc.calcsettings.views.CalcSettingsActivity;
import com.medscape.android.activity.cme.views.CMELandingActivity;
import com.medscape.android.activity.events.LiveEventExploreActivity;
import com.medscape.android.activity.help.HelpMainActivity;
import com.medscape.android.activity.interactions.DrugInteractionActivity;
import com.medscape.android.activity.rss.views.NewsLandingActivity;
import com.medscape.android.activity.saved.views.SaveActivity;
import com.medscape.android.activity.speciality.SpecialityListActivity;
import com.medscape.android.activity.update.UpdateReferenceMainActivity;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.consult.activity.NotificationSettingsActivity;
import com.medscape.android.custom.CustomMenu;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.homescreen.views.HomeScreenActivity;
import com.medscape.android.myinvites.MyInvitationsActivity;
import com.medscape.android.parser.model.Article;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MedscapeMenu {
    public static boolean ISREDIRECT = false;
    public static final int MENU_CALCULATOR = 23;
    public static final int MENU_CME = 4;
    public static final int MENU_DEBUG_SETTINGS = 22;
    public static final int MENU_DRUG_INTERACTIONS = 2;
    public static final int MENU_EMAIL = 15;
    public static final int MENU_EVENTS = 25;
    public static final int MENU_HELP = 9;
    public static final int MENU_HOME = 7;
    public static final int MENU_LANGUAGE = 21;
    public static final int MENU_MORE_NEWS = 13;
    public static final int MENU_MYINVITES = 19;
    public static final int MENU_NEWS = 3;
    public static final int MENU_NOTIFICATIONS = 20;
    public static final int MENU_PERSONAL_INFORMATION = 24;
    public static final int MENU_REFERENCE = 7;
    public static final int MENU_SAVED = 11;
    public static final int MENU_UPDATE = 10;
    private static final String TAG = "MedscapeMenu";
    public static String emailBody = null;
    public static String emailSubject = null;
    public static boolean hasInteractions = false;

    public static boolean onOptionsItemSelected(final Context context, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 7) {
            Intent intent = new Intent(context, HomeScreenActivity.class);
            intent.setFlags(67108864);
            context.startActivity(intent);
            return true;
        } else if (itemId != 15) {
            return false;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Share").setCancelable(true).setSingleChoiceItems(new String[]{"Email", "Facebook"}, 0, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        MedscapeMenu.sendAnEmail(context);
                    }
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                }
            });
            builder.show();
            return true;
        }
    }

    public static void sendAnEmail(Context context) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/html");
        setEmailContent(context);
        intent.putExtra("android.intent.extra.SUBJECT", emailSubject);
        intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(emailBody));
        if (Util.isEmailConfigured(context, intent)) {
            context.startActivity(Intent.createChooser(intent, "Send mail..."));
        } else if (!((Activity) context).isFinishing()) {
            DialogUtil.showAlertDialog(24, (String) null, context.getString(R.string.alert_show_email_configure_message), context).show();
        }
    }

    public static void sendSaveBIPings(Context context, String str, String str2) {
        OmnitureManager.get().trackModule(context, str, "save", str2, (Map<String, Object>) null);
    }

    public static void setEmailContent(Context context) {
        Article article;
        if (context instanceof DrugMonographMainActivity) {
            article = ((DrugMonographMainActivity) context).getArticleForEmail();
        } else {
            article = context instanceof ClinicalReferenceArticleActivity ? ((ClinicalReferenceArticleActivity) context).getArticleForEmail() : null;
        }
        if (article != null) {
            ellipsis(article.mTitle, 5);
            emailSubject = "Interesting article on Medscape";
            emailBody = "I thought you would be interested in the following article:<BR><BR><a href =\"" + article.mLink + "\">" + article.mTitle + "</a><BR><BR>" + article.mDescription + "<BR><BR>Interested in downloading Medscape’s free app for iPhone®, Android™, iPad®, or Kindle Fire®?<a href = \"http://www.medscape.com/mobileapp\">Learn More ></a><BR><BR>Sent using the Medscape App for Android™<BR><BR>";
        }
    }

    public static String ellipsis(String str, int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        String str2 = "";
        int i2 = 1;
        while (stringTokenizer.hasMoreTokens()) {
            i2++;
            str2 = str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + stringTokenizer.nextToken();
            if (i2 > i) {
                break;
            }
        }
        return str2 + "...";
    }

    public static boolean onItemSelected(Activity activity, int i) {
        if (i == 3) {
            Intent intent = new Intent(activity, NewsLandingActivity.class);
            intent.addFlags(537001984);
            activity.startActivity(intent);
            activity.overridePendingTransition(0, 0);
            return true;
        } else if (i == 4) {
            Intent intent2 = new Intent(activity, CMELandingActivity.class);
            intent2.addFlags(537001984);
            activity.startActivity(intent2);
            activity.overridePendingTransition(0, 0);
            return true;
        } else if (i == 7) {
            Intent intent3 = new Intent(activity, HomeScreenActivity.class);
            intent3.putExtra(Constants.HOME_CLICKED_FROM_MENU, true);
            intent3.addFlags(537001984);
            activity.startActivity(intent3);
            activity.overridePendingTransition(0, 0);
            return true;
        } else if (i == 13) {
            OmnitureManager.get().trackPageView(activity, "other", Scopes.PROFILE, "content-preferences", (String) null, (String) null, (Map<String, Object>) null);
            Intent intent4 = new Intent(activity, SpecialityListActivity.class);
            intent4.putExtra("feedType", "NEWS");
            activity.startActivityForResult(intent4, 9);
            return true;
        } else if (i == 19) {
            OmnitureManager.get().trackPageView(activity, "other", Scopes.PROFILE, "myinvitations", (String) null, (String) null, (Map<String, Object>) null);
            activity.startActivity(new Intent(activity, MyInvitationsActivity.class));
            return true;
        } else if (i != 20) {
            switch (i) {
                case 9:
                    activity.startActivity(new Intent(activity, HelpMainActivity.class));
                    return true;
                case 10:
                    OmnitureManager.get().trackPageView(activity, "other", Scopes.PROFILE, "data-updates", (String) null, (String) null, (Map<String, Object>) null);
                    activity.startActivity(new Intent(activity, UpdateReferenceMainActivity.class));
                    return true;
                case 11:
                    activity.startActivity(new Intent(activity, SaveActivity.class));
                    activity.overridePendingTransition(0, 0);
                    return true;
                default:
                    switch (i) {
                        case 22:
                            activity.startActivity(new Intent(activity, DebugSettingsActivity.class));
                            return true;
                        case 23:
                            OmnitureManager.get().trackPageView(activity, "other", Scopes.PROFILE, Constants.OMNITURE_MLINK_CALC, (String) null, (String) null, (Map<String, Object>) null);
                            activity.startActivity(new Intent(activity, CalcSettingsActivity.class));
                            return true;
                        case 24:
                            OmnitureManager.get().trackPageView(activity, "other", Scopes.PROFILE, "do-not-sell", (String) null, (String) null, (Map<String, Object>) null);
                            WebviewUtil.Companion.launchPlainWebView(activity, Constants.URL_PERSONAL_INFORMATION, activity.getString(R.string.drawer_title_personal_information), "", "", "other", "", 1, true);
                            return true;
                        case 25:
                            Intent intent5 = new Intent(activity, LiveEventExploreActivity.class);
                            intent5.addFlags(537001984);
                            activity.startActivity(intent5);
                            activity.overridePendingTransition(0, 0);
                            return true;
                        default:
                            return false;
                    }
            }
        } else {
            OmnitureManager.get().trackPageView(activity, "other", Scopes.PROFILE, "notifications", (String) null, (String) null, (Map<String, Object>) null);
            activity.startActivity(new Intent(activity, NotificationSettingsActivity.class));
            return true;
        }
    }

    public static HashMap<Integer, String> getMenuItem(int i, String str) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(Integer.valueOf(i), str);
        return hashMap;
    }

    public static boolean onCustomMenuSelected(final Activity activity, HashMap<Integer, HashMap<Integer, String>> hashMap) {
        CustomMenu.show(activity, hashMap, new CustomMenu.OnMenuItemSelectedListener() {
            public void MenuItemSelectedEvent(Integer num) {
                new Intent();
                int intValue = num.intValue();
                if (intValue == 2) {
                    OmnitureManager.get().trackModule(activity, Constants.OMNITURE_CHANNEL_REFERENCE, "bartool-add", "ic", (Map<String, Object>) null);
                    Intent intent = new Intent(activity, DrugInteractionActivity.class);
                    intent.putExtra("drug", ((DrugMonographMainActivity) activity).getDrugForInteraction());
                    activity.startActivity(intent);
                } else if (intValue != 15) {
                    switch (intValue) {
                        case 9:
                            activity.startActivity(new Intent(activity, HelpMainActivity.class));
                            return;
                        case 10:
                            activity.startActivity(new Intent(activity, UpdateReferenceMainActivity.class));
                            return;
                        case 11:
                            MedscapeMenu.save(activity);
                            return;
                        default:
                            return;
                    }
                } else {
                    Intent intent2 = new Intent("android.intent.action.SEND");
                    intent2.setType("text/html");
                    MedscapeMenu.setEmailContent(activity);
                    intent2.putExtra("android.intent.extra.SUBJECT", MedscapeMenu.emailSubject);
                    intent2.putExtra("android.intent.extra.TEXT", Html.fromHtml(MedscapeMenu.emailBody));
                    if (Util.isEmailConfigured(activity, intent2)) {
                        activity.startActivity(Intent.createChooser(intent2, "Send mail..."));
                    } else if (!activity.isFinishing()) {
                        DialogUtil.showAlertDialog(24, (String) null, activity.getString(R.string.alert_show_email_configure_message), activity).show();
                    }
                    OmnitureManager.get().trackModule(activity, "other", "bartool-share", "email", (Map<String, Object>) null);
                }
            }
        });
        return false;
    }

    protected static void save(Context context) {
        if (context instanceof DrugMonographMainActivity) {
            ((DrugMonographMainActivity) context).saveContent();
        } else if (context instanceof ClinicalReferenceArticleActivity) {
            ((ClinicalReferenceArticleActivity) context).saveContent();
        } else if (context instanceof CalcArticleActivity) {
            ((CalcArticleActivity) context).saveContent();
        }
    }
}
