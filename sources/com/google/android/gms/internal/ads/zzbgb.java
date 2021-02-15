package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbgb extends WebChromeClient {
    private final zzbgj zzdgy;

    public zzbgb(zzbgj zzbgj) {
        this.zzdgy = zzbgj;
    }

    private final boolean zza(Context context, String str, String str2, String str3, String str4, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        zza zzabb;
        try {
            if (this.zzdgy == null || this.zzdgy.zzabw() == null || this.zzdgy.zzabw().zzabb() == null || (zzabb = this.zzdgy.zzabw().zzabb()) == null || zzabb.zzjz()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(str2);
                if (z) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(1);
                    TextView textView = new TextView(context);
                    textView.setText(str3);
                    EditText editText = new EditText(context);
                    editText.setText(str4);
                    linearLayout.addView(textView);
                    linearLayout.addView(editText);
                    builder.setView(linearLayout).setPositiveButton(17039370, new zzbgh(jsPromptResult, editText)).setNegativeButton(17039360, new zzbge(jsPromptResult)).setOnCancelListener(new zzbgf(jsPromptResult)).create().show();
                } else {
                    builder.setMessage(str3).setPositiveButton(17039370, new zzbgc(jsResult)).setNegativeButton(17039360, new zzbgd(jsResult)).setOnCancelListener(new zzbga(jsResult)).create().show();
                }
                return true;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(str3).length());
            sb.append("window.");
            sb.append(str);
            sb.append("('");
            sb.append(str3);
            sb.append("')");
            zzabb.zzbo(sb.toString());
            return false;
        } catch (WindowManager.BadTokenException e) {
            zzayp.zzd("Fail to display Dialog.", e);
        }
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebView.WebViewTransport webViewTransport = (WebView.WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        if (this.zzdgy.zzabx() != null) {
            webView2.setWebViewClient(this.zzdgy.zzabx());
        }
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    public final void onCloseWindow(WebView webView) {
        if (!(webView instanceof zzbgj)) {
            zzayp.zzfe("Tried to close a WebView that wasn't an AdWebView.");
            return;
        }
        zze zzabs = ((zzbgj) webView).zzabs();
        if (zzabs == null) {
            zzayp.zzfe("Tried to close an AdWebView not associated with an overlay.");
        } else {
            zzabs.close();
        }
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String message = consoleMessage.message();
        String sourceId = consoleMessage.sourceId();
        int lineNumber = consoleMessage.lineNumber();
        StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 19 + String.valueOf(sourceId).length());
        sb.append("JS: ");
        sb.append(message);
        sb.append(" (");
        sb.append(sourceId);
        sb.append(":");
        sb.append(lineNumber);
        sb.append(")");
        String sb2 = sb.toString();
        if (sb2.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        int i = zzbgg.zzemo[consoleMessage.messageLevel().ordinal()];
        if (i == 1) {
            zzayp.zzfc(sb2);
        } else if (i == 2) {
            zzayp.zzfe(sb2);
        } else if (i == 3 || i == 4) {
            zzayp.zzfd(sb2);
        } else if (i != 5) {
            zzayp.zzfd(sb2);
        } else {
            zzayp.zzef(sb2);
        }
        return super.onConsoleMessage(consoleMessage);
    }

    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        long j4 = 5242880 - j3;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j != 0) {
            if (j2 == 0) {
                j = Math.min(j + Math.min(PlaybackStateCompat.ACTION_PREPARE_FROM_URI, j4), 1048576);
            } else if (j2 <= Math.min(1048576 - j, j4)) {
                j += j2;
            }
            j2 = j;
        } else if (j2 > j4 || j2 > 1048576) {
            j2 = 0;
        }
        quotaUpdater.updateQuota(j2);
    }

    public final void onHideCustomView() {
        zze zzabs = this.zzdgy.zzabs();
        if (zzabs == null) {
            zzayp.zzfe("Could not get ad overlay when hiding custom view.");
        } else {
            zzabs.zzur();
        }
    }

    private static Context zza(WebView webView) {
        if (!(webView instanceof zzbgj)) {
            return webView.getContext();
        }
        zzbgj zzbgj = (zzbgj) webView;
        Activity zzzu = zzbgj.zzzu();
        if (zzzu != null) {
            return zzzu;
        }
        return zzbgj.getContext();
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "alert", str, str2, (String) null, jsResult, (JsPromptResult) null, false);
    }

    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "onBeforeUnload", str, str2, (String) null, jsResult, (JsPromptResult) null, false);
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "confirm", str, str2, (String) null, jsResult, (JsPromptResult) null, false);
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zza(webView), "prompt", str, str2, str3, (JsResult) null, jsPromptResult, true);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        long j3 = j + PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        if (5242880 - j2 < j3) {
            quotaUpdater.updateQuota(0);
        } else {
            quotaUpdater.updateQuota(j3);
        }
    }

    public final void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        onShowCustomView(view, -1, customViewCallback);
    }

    @Deprecated
    public final void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        zze zzabs = this.zzdgy.zzabs();
        if (zzabs == null) {
            zzayp.zzfe("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
            return;
        }
        zzabs.zza(view, customViewCallback);
        zzabs.setRequestedOrientation(i);
    }

    public final void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        boolean z;
        if (callback != null) {
            zzp.zzkr();
            if (!zzayu.zzr(this.zzdgy.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                zzp.zzkr();
                if (!zzayu.zzr(this.zzdgy.getContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
                    z = false;
                    callback.invoke(str, z, true);
                }
            }
            z = true;
            callback.invoke(str, z, true);
        }
    }

    public final void onPermissionRequest(PermissionRequest permissionRequest) {
        if (!PlatformVersion.isAtLeastLollipop() || zzadh.zzdcs.get().booleanValue()) {
            super.onPermissionRequest(permissionRequest);
            return;
        }
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj == null || zzbgj.zzabw() == null || this.zzdgy.zzabw().zzabo() == null) {
            super.onPermissionRequest(permissionRequest);
            return;
        }
        String[] zza = this.zzdgy.zzabw().zzabo().zza(permissionRequest.getResources());
        if (zza.length > 0) {
            permissionRequest.grant(zza);
        } else {
            permissionRequest.deny();
        }
    }
}
