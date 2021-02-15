package io.branch.referral;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import io.branch.referral.Defines;
import java.net.URI;
import java.net.URISyntaxException;
import org.json.JSONObject;

public class BranchViewHandler {
    public static final int BRANCH_VIEW_ERR_ALREADY_SHOWING = -200;
    public static final int BRANCH_VIEW_ERR_INVALID_VIEW = -201;
    public static final int BRANCH_VIEW_ERR_REACHED_LIMIT = -203;
    public static final int BRANCH_VIEW_ERR_TEMP_UNAVAILABLE = -202;
    private static final String BRANCH_VIEW_REDIRECT_ACTION_ACCEPT = "accept";
    private static final String BRANCH_VIEW_REDIRECT_ACTION_CANCEL = "cancel";
    private static final String BRANCH_VIEW_REDIRECT_SCHEME = "branch-cta";
    private static BranchViewHandler thisInstance_;
    /* access modifiers changed from: private */
    public Dialog branchViewDialog_;
    /* access modifiers changed from: private */
    public boolean isBranchViewAccepted_;
    /* access modifiers changed from: private */
    public boolean isBranchViewDialogShowing_;
    /* access modifiers changed from: private */
    public boolean loadingHtmlInBackGround_ = false;
    private BranchView openOrInstallPendingBranchView_ = null;
    private String parentActivityClassName_;
    /* access modifiers changed from: private */
    public boolean webViewLoadError_;

    public interface IBranchViewEvents {
        void onBranchViewAccepted(String str, String str2);

        void onBranchViewCancelled(String str, String str2);

        void onBranchViewError(int i, String str, String str2);

        void onBranchViewVisible(String str, String str2);
    }

    private BranchViewHandler() {
    }

    public static BranchViewHandler getInstance() {
        if (thisInstance_ == null) {
            thisInstance_ = new BranchViewHandler();
        }
        return thisInstance_;
    }

    public boolean showPendingBranchView(Context context) {
        boolean showBranchView = showBranchView(this.openOrInstallPendingBranchView_, context, (IBranchViewEvents) null);
        if (showBranchView) {
            this.openOrInstallPendingBranchView_ = null;
        }
        return showBranchView;
    }

    public boolean showBranchView(JSONObject jSONObject, String str, Context context, IBranchViewEvents iBranchViewEvents) {
        return showBranchView(new BranchView(jSONObject, str), context, iBranchViewEvents);
    }

    private boolean showBranchView(BranchView branchView, Context context, IBranchViewEvents iBranchViewEvents) {
        if (this.isBranchViewDialogShowing_ || this.loadingHtmlInBackGround_) {
            if (iBranchViewEvents != null) {
                iBranchViewEvents.onBranchViewError(BRANCH_VIEW_ERR_ALREADY_SHOWING, "Unable to create a Branch view. A Branch view is already showing", branchView.branchViewAction_);
            }
            return false;
        }
        this.isBranchViewDialogShowing_ = false;
        this.isBranchViewAccepted_ = false;
        if (!(context == null || branchView == null)) {
            if (branchView.isAvailable(context)) {
                if (!TextUtils.isEmpty(branchView.webViewHtml_)) {
                    createAndShowBranchView(branchView, context, iBranchViewEvents);
                } else {
                    this.loadingHtmlInBackGround_ = true;
                    new loadBranchViewTask(branchView, context, iBranchViewEvents).execute(new Void[0]);
                }
                return true;
            } else if (iBranchViewEvents != null) {
                iBranchViewEvents.onBranchViewError(BRANCH_VIEW_ERR_REACHED_LIMIT, "Unable to create this Branch view. Reached maximum usage limit ", branchView.branchViewAction_);
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void createAndShowBranchView(final BranchView branchView, Context context, final IBranchViewEvents iBranchViewEvents) {
        if (context != null && branchView != null) {
            final WebView webView = new WebView(context);
            webView.getSettings().setJavaScriptEnabled(true);
            if (Build.VERSION.SDK_INT >= 19) {
                webView.setLayerType(2, (Paint) null);
            }
            this.webViewLoadError_ = false;
            if (!TextUtils.isEmpty(branchView.webViewHtml_)) {
                webView.loadDataWithBaseURL((String) null, branchView.webViewHtml_, "text/html", "utf-8", (String) null);
                webView.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        boolean access$400 = BranchViewHandler.this.handleUserActionRedirect(str);
                        if (!access$400) {
                            webView.loadUrl(str);
                        } else if (BranchViewHandler.this.branchViewDialog_ != null) {
                            BranchViewHandler.this.branchViewDialog_.dismiss();
                        }
                        return access$400;
                    }

                    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                        super.onPageStarted(webView, str, bitmap);
                    }

                    public void onReceivedError(WebView webView, int i, String str, String str2) {
                        super.onReceivedError(webView, i, str, str2);
                        boolean unused = BranchViewHandler.this.webViewLoadError_ = true;
                    }

                    public void onPageFinished(WebView webView, String str) {
                        super.onPageFinished(webView, str);
                        BranchViewHandler.this.openBranchViewDialog(branchView, iBranchViewEvents, webView);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void openBranchViewDialog(final BranchView branchView, final IBranchViewEvents iBranchViewEvents, WebView webView) {
        if (this.webViewLoadError_ || Branch.getInstance() == null || Branch.getInstance().currentActivityReference_ == null) {
            this.isBranchViewDialogShowing_ = false;
            if (iBranchViewEvents != null) {
                iBranchViewEvents.onBranchViewError(BRANCH_VIEW_ERR_TEMP_UNAVAILABLE, "Unable to create a Branch view due to a temporary network error", branchView.branchViewAction_);
                return;
            }
            return;
        }
        Activity activity = (Activity) Branch.getInstance().currentActivityReference_.get();
        if (activity != null) {
            branchView.updateUsageCount(activity.getApplicationContext(), branchView.branchViewID_);
            this.parentActivityClassName_ = activity.getClass().getName();
            RelativeLayout relativeLayout = new RelativeLayout(activity);
            relativeLayout.setVisibility(8);
            relativeLayout.addView(webView, new RelativeLayout.LayoutParams(-1, -1));
            relativeLayout.setBackgroundColor(0);
            Dialog dialog = this.branchViewDialog_;
            if (dialog == null || !dialog.isShowing()) {
                Dialog dialog2 = new Dialog(activity, 16973834);
                this.branchViewDialog_ = dialog2;
                dialog2.setContentView(relativeLayout);
                relativeLayout.setVisibility(0);
                webView.setVisibility(0);
                this.branchViewDialog_.show();
                showViewWithAlphaAnimation(relativeLayout);
                showViewWithAlphaAnimation(webView);
                this.isBranchViewDialogShowing_ = true;
                if (iBranchViewEvents != null) {
                    iBranchViewEvents.onBranchViewVisible(branchView.branchViewAction_, branchView.branchViewID_);
                }
                this.branchViewDialog_.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        boolean unused = BranchViewHandler.this.isBranchViewDialogShowing_ = false;
                        Dialog unused2 = BranchViewHandler.this.branchViewDialog_ = null;
                        if (iBranchViewEvents == null) {
                            return;
                        }
                        if (BranchViewHandler.this.isBranchViewAccepted_) {
                            iBranchViewEvents.onBranchViewAccepted(branchView.branchViewAction_, branchView.branchViewID_);
                        } else {
                            iBranchViewEvents.onBranchViewCancelled(branchView.branchViewAction_, branchView.branchViewID_);
                        }
                    }
                });
            } else if (iBranchViewEvents != null) {
                iBranchViewEvents.onBranchViewError(BRANCH_VIEW_ERR_ALREADY_SHOWING, "Unable to create a Branch view. A Branch view is already showing", branchView.branchViewAction_);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean handleUserActionRedirect(String str) {
        try {
            URI uri = new URI(str);
            if (!uri.getScheme().equalsIgnoreCase(BRANCH_VIEW_REDIRECT_SCHEME)) {
                return false;
            }
            if (uri.getHost().equalsIgnoreCase(BRANCH_VIEW_REDIRECT_ACTION_ACCEPT)) {
                this.isBranchViewAccepted_ = true;
            } else if (!uri.getHost().equalsIgnoreCase(BRANCH_VIEW_REDIRECT_ACTION_CANCEL)) {
                return false;
            } else {
                this.isBranchViewAccepted_ = false;
            }
            return true;
        } catch (URISyntaxException unused) {
            return false;
        }
    }

    private void showViewWithAlphaAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setStartOffset(10);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setFillAfter(true);
        view.setVisibility(0);
        view.startAnimation(alphaAnimation);
    }

    private void hideViewWithAlphaAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setStartOffset(10);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setFillAfter(true);
        view.setVisibility(8);
        view.startAnimation(alphaAnimation);
    }

    public boolean markInstallOrOpenBranchViewPending(JSONObject jSONObject, String str) {
        Activity activity;
        BranchView branchView = new BranchView(jSONObject, str);
        if (Branch.getInstance().currentActivityReference_ == null || (activity = (Activity) Branch.getInstance().currentActivityReference_.get()) == null || !branchView.isAvailable(activity)) {
            return false;
        }
        this.openOrInstallPendingBranchView_ = new BranchView(jSONObject, str);
        return true;
    }

    public boolean isInstallOrOpenBranchViewPending(Context context) {
        BranchView branchView = this.openOrInstallPendingBranchView_;
        return branchView != null && branchView.isAvailable(context);
    }

    private class loadBranchViewTask extends AsyncTask<Void, Void, Boolean> {
        private final BranchView branchView;
        private final IBranchViewEvents callback;
        private final Context context;
        private String htmlString;

        public loadBranchViewTask(BranchView branchView2, Context context2, IBranchViewEvents iBranchViewEvents) {
            this.branchView = branchView2;
            this.context = context2;
            this.callback = iBranchViewEvents;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0050  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Boolean doInBackground(java.lang.Void... r8) {
            /*
                r7 = this;
                r8 = 0
                r0 = 200(0xc8, float:2.8E-43)
                r1 = -1
                java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x004d }
                io.branch.referral.BranchViewHandler$BranchView r3 = r7.branchView     // Catch:{ Exception -> 0x004d }
                java.lang.String r3 = r3.webViewUrl_     // Catch:{ Exception -> 0x004d }
                r2.<init>(r3)     // Catch:{ Exception -> 0x004d }
                java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x004d }
                java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x004d }
                java.lang.String r3 = "GET"
                r2.setRequestMethod(r3)     // Catch:{ Exception -> 0x004d }
                r2.connect()     // Catch:{ Exception -> 0x004d }
                int r3 = r2.getResponseCode()     // Catch:{ Exception -> 0x004d }
                if (r3 != r0) goto L_0x004e
                java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x004c }
                r4.<init>()     // Catch:{ Exception -> 0x004c }
                java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x004c }
                r5 = 1024(0x400, float:1.435E-42)
                byte[] r5 = new byte[r5]     // Catch:{ Exception -> 0x004c }
            L_0x0030:
                int r6 = r2.read(r5)     // Catch:{ Exception -> 0x004c }
                if (r6 == r1) goto L_0x003a
                r4.write(r5, r8, r6)     // Catch:{ Exception -> 0x004c }
                goto L_0x0030
            L_0x003a:
                io.branch.referral.BranchViewHandler$BranchView r1 = r7.branchView     // Catch:{ Exception -> 0x004c }
                java.lang.String r5 = "UTF-8"
                java.lang.String r5 = r4.toString(r5)     // Catch:{ Exception -> 0x004c }
                java.lang.String unused = r1.webViewHtml_ = r5     // Catch:{ Exception -> 0x004c }
                r4.close()     // Catch:{ Exception -> 0x004c }
                r2.close()     // Catch:{ Exception -> 0x004c }
                goto L_0x004e
            L_0x004c:
                r1 = r3
            L_0x004d:
                r3 = r1
            L_0x004e:
                if (r3 != r0) goto L_0x0051
                r8 = 1
            L_0x0051:
                java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.BranchViewHandler.loadBranchViewTask.doInBackground(java.lang.Void[]):java.lang.Boolean");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue()) {
                BranchViewHandler.this.createAndShowBranchView(this.branchView, this.context, this.callback);
            } else {
                IBranchViewEvents iBranchViewEvents = this.callback;
                if (iBranchViewEvents != null) {
                    iBranchViewEvents.onBranchViewError(BranchViewHandler.BRANCH_VIEW_ERR_TEMP_UNAVAILABLE, "Unable to create a Branch view due to a temporary network error", this.branchView.branchViewAction_);
                }
            }
            boolean unused = BranchViewHandler.this.loadingHtmlInBackGround_ = false;
        }
    }

    private class BranchView {
        private static final int USAGE_UNLIMITED = -1;
        /* access modifiers changed from: private */
        public String branchViewAction_;
        /* access modifiers changed from: private */
        public String branchViewID_;
        private int num_of_use_;
        /* access modifiers changed from: private */
        public String webViewHtml_;
        /* access modifiers changed from: private */
        public String webViewUrl_;

        private BranchView(JSONObject jSONObject, String str) {
            this.branchViewID_ = "";
            this.branchViewAction_ = "";
            this.num_of_use_ = 1;
            this.webViewUrl_ = "";
            this.webViewHtml_ = "";
            try {
                this.branchViewAction_ = str;
                if (jSONObject.has(Defines.Jsonkey.BranchViewID.getKey())) {
                    this.branchViewID_ = jSONObject.getString(Defines.Jsonkey.BranchViewID.getKey());
                }
                if (jSONObject.has(Defines.Jsonkey.BranchViewNumOfUse.getKey())) {
                    this.num_of_use_ = jSONObject.getInt(Defines.Jsonkey.BranchViewNumOfUse.getKey());
                }
                if (jSONObject.has(Defines.Jsonkey.BranchViewUrl.getKey())) {
                    this.webViewUrl_ = jSONObject.getString(Defines.Jsonkey.BranchViewUrl.getKey());
                }
                if (jSONObject.has(Defines.Jsonkey.BranchViewHtml.getKey())) {
                    this.webViewHtml_ = jSONObject.getString(Defines.Jsonkey.BranchViewHtml.getKey());
                }
            } catch (Exception unused) {
            }
        }

        /* access modifiers changed from: private */
        public boolean isAvailable(Context context) {
            int branchViewUsageCount = PrefHelper.getInstance(context).getBranchViewUsageCount(this.branchViewID_);
            int i = this.num_of_use_;
            return i > branchViewUsageCount || i == -1;
        }

        public void updateUsageCount(Context context, String str) {
            PrefHelper.getInstance(context).updateBranchViewUsageCount(str);
        }
    }

    public void onCurrentActivityDestroyed(Activity activity) {
        String str = this.parentActivityClassName_;
        if (str != null && str.equalsIgnoreCase(activity.getClass().getName())) {
            this.isBranchViewDialogShowing_ = false;
        }
    }
}
