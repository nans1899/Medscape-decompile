package com.medscape.android.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.Scopes;
import com.medscape.android.Constants;
import com.medscape.android.activity.calc.CalculatorLandingActivity;
import com.medscape.android.activity.calc.MedscapeCalculatorActivity;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.activity.cme.views.CMELandingActivity;
import com.medscape.android.activity.directory.DirectorySearchActivity;
import com.medscape.android.activity.formulary.IndexedDrugFormularyListActivity;
import com.medscape.android.activity.interactions.DrugInteractionActivity;
import com.medscape.android.activity.rss.views.NewsLandingActivity;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.analytics.remoteconfig.reference.ReferenceArticleTOCEnabledManager;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.homescreen.views.HomeScreenActivity;
import com.medscape.android.parser.model.Article;
import com.medscape.android.pillid.PillIdentifierActivity;
import com.medscape.android.reference.ClinicalReferenceArticleLandingActivity;
import com.medscape.android.util.constants.DeepLinkConstants;
import com.medscape.android.webmdrx.activity.IndexedRxDrugListActivity;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedirectHandler implements LaunchQxCallback {
    private static final String TAG = RedirectHandler.class.getSimpleName();
    private Context context;
    private boolean isFromPush;

    public RedirectHandler(boolean z) {
        this.isFromPush = z;
    }

    public static Bundle getRedirectBundle(Intent intent) {
        Bundle bundle = new Bundle();
        if (!(intent == null || intent.getData() == null || intent.getData().getScheme() == null || (!intent.getData().getScheme().equalsIgnoreCase("consult") && !intent.getData().getScheme().equalsIgnoreCase("ckb") && !intent.getData().getScheme().equalsIgnoreCase("drug") && !intent.getData().getScheme().equalsIgnoreCase("webmdrx")))) {
            String dataString = intent.getDataString();
            if (StringUtil.isNotEmpty(dataString)) {
                bundle.putString(Constants.EXTRA_REDIRECT, dataString);
            }
        }
        return bundle;
    }

    public void handleRedirect(Context context2, String str, boolean z) {
        if (context2 != null) {
            Uri parse = Uri.parse(str);
            if (z) {
                Intent intent = new Intent(context2, HomeScreenActivity.class);
                intent.setFlags(335577088);
                intent.setAction("android.intent.action.MAIN");
                intent.putExtra(Constants.EXTRA_DEEPLINK_PAYLOAD, str);
                context2.startActivity(intent);
                new Handler().postDelayed(new Runnable(parse, context2) {
                    public final /* synthetic */ Uri f$1;
                    public final /* synthetic */ Context f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        RedirectHandler.this.lambda$handleRedirect$0$RedirectHandler(this.f$1, this.f$2);
                    }
                }, 50);
                return;
            }
            lambda$handleRedirect$0$RedirectHandler(parse, context2);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: handleOpeningOfUrl */
    public void lambda$handleRedirect$0$RedirectHandler(Uri uri, Context context2) {
        if (uri != null && uri.getScheme() != null) {
            if (uri.getScheme().equals("medscape")) {
                Matcher matcher = Pattern.compile("\\B//\\B|\\bhome\\b|\\bnews\\b|\\beducation\\b|\\breference\\b").matcher(uri.toString());
                boolean find = matcher.find();
                UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
                urlQuerySanitizer.setAllowUnregisteredParamaters(true);
                if (uri.toString().contains("path")) {
                    urlQuerySanitizer.parseUrl(uri.toString());
                    WebviewUtil.Companion.launchNews(context2, WebviewUtil.Companion.getNewsUrl(urlQuerySanitizer.getValue("path").substring(urlQuerySanitizer.getValue("path").indexOf(47) + 1)), "", "wv-launch-push", "news", "push", "", "", "");
                } else if (uri.toString().contains("url")) {
                    Uri parse = Uri.parse(uri.toString());
                    String queryParameter = parse.getQueryParameter("url");
                    String fragment = parse.getFragment();
                    if (parse.getPathSegments().contains("education")) {
                        context2.startActivity(new Intent(context2, CMELandingActivity.class).setFlags(67108864));
                    }
                    if (StringUtil.isNotEmpty(queryParameter)) {
                        if (StringUtil.isNotEmpty(fragment)) {
                            queryParameter = queryParameter + "#" + fragment;
                        }
                        handleUrlPart(context2, queryParameter);
                    }
                } else if (uri.toString().contains("articleId")) {
                    urlQuerySanitizer.parseUrl(uri.toString());
                    openCMEArticle(context2, urlQuerySanitizer.getValue("articleId"));
                } else if (find) {
                    String substring = uri.toString().substring(matcher.start(), matcher.end());
                    char c = 65535;
                    int hashCode = substring.hashCode();
                    if (hashCode != -290756696) {
                        if (hashCode == 3377875 && substring.equals("news")) {
                            c = 0;
                        }
                    } else if (substring.equals("education")) {
                        c = 1;
                    }
                    if (c == 0) {
                        context2.startActivity(new Intent(context2, NewsLandingActivity.class).setFlags(67108864));
                    } else if (c != 1) {
                        context2.startActivity(new Intent(context2, HomeScreenActivity.class).setFlags(67108864));
                    } else {
                        context2.startActivity(new Intent(context2, CMELandingActivity.class).setFlags(67108864));
                    }
                }
            } else if (uri.getScheme().equalsIgnoreCase("consult") || uri.getScheme().equalsIgnoreCase("ckb") || uri.getScheme().equalsIgnoreCase("drug") || uri.getScheme().equalsIgnoreCase("webmdrx")) {
                String uri2 = uri.toString();
                if (StringUtil.isNotEmpty(uri2)) {
                    Log.i(TAG, "handleRedirect: starts with" + uri2);
                    handleUrlPart(context2, uri2);
                }
            } else if (uri.getHost() != null && uri.getHost().equalsIgnoreCase("www.medscape.com") && uri.getPath() != null && uri.getPath().contains("consult")) {
                handleUrlPart(context2, transferUniversalLinkIntoDeepLink(uri));
            }
        }
    }

    private void handleUrlPart(Context context2, String str) {
        if (str != null && context2 != null) {
            this.context = context2;
            if (str.startsWith("consult://") && CapabilitiesManager.getInstance(context2).isConsultFeatureAvailable()) {
                CapabilitiesManager.getInstance(context2).startConsultActivity(str);
            } else if (str.startsWith("ckb://") || str.startsWith("drug://")) {
                if (!str.startsWith("ckb") || !new ReferenceArticleTOCEnabledManager().getRefTOCData()) {
                    context2.startActivity(new Intent().addFlags(268435456).setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse(str)).putExtra(Constants.EXTRA_PUSH_LAUNCH, true));
                } else {
                    context2.startActivity(new Intent(context2, ClinicalReferenceArticleLandingActivity.class).addFlags(268435456).setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse(str)).putExtra(Constants.EXTRA_PUSH_LAUNCH, true));
                }
            } else if (str.equalsIgnoreCase("webmdrx://")) {
                if (Util.isUSuser(context2)) {
                    context2.startActivity(new Intent(context2, IndexedRxDrugListActivity.class).addFlags(268435456));
                }
            } else if (str.startsWith(DeepLinkConstants.PILL_ID)) {
                Intent intent = new Intent(context2, PillIdentifierActivity.class);
                intent.putExtra(DeepLinkConstants.EXTRA_PILL_ID_DEEPLINK, str);
                context2.startActivity(intent);
            } else if (str.startsWith(DeepLinkConstants.INTERACTION)) {
                Intent intent2 = new Intent(context2, DrugInteractionActivity.class);
                intent2.putExtra(DeepLinkConstants.EXTRA_INTERACTION_DEEPLINK, str);
                context2.startActivity(intent2);
            } else if (str.startsWith(DeepLinkConstants.CME_TRACKER)) {
                CMEHelper.launchCMETracker(context2, this.isFromPush);
            } else if (str.startsWith(DeepLinkConstants.CALCULATOR)) {
                if (str.contains("home")) {
                    openCalculatorHomeScreen(context2);
                } else if (!Util.findMatchingQxCalcForMedscapeCalc(context2, str.substring(str.indexOf(DeepLinkConstants.CALCULATOR) + 7), this)) {
                    openCalculatorHomeScreen(context2);
                }
            } else if (str.startsWith(DeepLinkConstants.FORMULARY)) {
                context2.startActivity(new Intent(context2, IndexedDrugFormularyListActivity.class));
            } else if (str.startsWith(DeepLinkConstants.HEALTH_DIRECTORY)) {
                context2.startActivity(new Intent(context2, DirectorySearchActivity.class));
            } else if (str.startsWith(DeepLinkConstants.CME)) {
                openCMEArticle(context2, str.substring(str.indexOf(DeepLinkConstants.CME) + 6));
            }
        }
    }

    public static String transferUniversalLinkIntoDeepLink(Uri uri) {
        if (uri == null) {
            return "consult://timeline";
        }
        if (uri.toString().contains("post?")) {
            String queryParameter = uri.getQueryParameter("id");
            if (!StringUtil.isNotEmpty(queryParameter)) {
                return "consult://timeline";
            }
            return "consult://post/" + queryParameter;
        } else if (uri.toString().contains("filterby")) {
            String queryParameter2 = uri.getQueryParameter("filterby");
            if (!StringUtil.isNotEmpty(queryParameter2)) {
                return "consult://timeline";
            }
            if (queryParameter2.equals(com.wbmd.wbmddrugscommons.constants.Constants.WBMDDrugKeyTag)) {
                String queryParameter3 = uri.getQueryParameter(ContentParser.TAGS);
                if (StringUtil.isNullOrEmpty(queryParameter3)) {
                    return "consult://timeline";
                }
                return "consult://timeline/specialty/" + queryParameter3;
            }
            return "consult://timeline/" + queryParameter2;
        } else if (!uri.toString().contains(Scopes.PROFILE)) {
            return "consult://timeline";
        } else {
            String queryParameter4 = uri.getQueryParameter("uid");
            if (StringUtil.isNullOrEmpty(queryParameter4)) {
                return "consult://timeline";
            }
            return "consult://user/" + queryParameter4;
        }
    }

    public void onQxItemClicked(DBContentItem dBContentItem, Bundle bundle) {
        Intent intent = new Intent(this.context, MedscapeCalculatorActivity.class);
        intent.putExtras(bundle);
        intent.putExtra(Constants.EXTRA_PUSH_LAUNCH, this.isFromPush);
        this.context.startActivity(intent);
    }

    private void openCalculatorHomeScreen(Context context2) {
        Intent intent = new Intent(context2, CalculatorLandingActivity.class);
        intent.putExtra(Constants.EXTRA_PUSH_LAUNCH, this.isFromPush);
        context2.startActivity(intent);
    }

    private void openCMEArticle(Context context2, String str) {
        Article article = new Article();
        article.mArticleId = str;
        if (context2 != null) {
            CMEHelper.launchCMEArticle(context2, article, this.isFromPush, false);
        }
    }
}
