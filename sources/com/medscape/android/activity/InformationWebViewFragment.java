package com.medscape.android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.contentviewer.interfaces.ILoadNextListener;
import com.medscape.android.drugs.details.views.DrugDetailsActivity;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import java.util.HashMap;

public class InformationWebViewFragment extends Fragment {
    protected static final int GET_NEXT_AD = 102;
    private static final int START_TIMER = 101;
    private static final String TAG = "InformationWebViewAcitivity";
    protected long autohide;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 51) {
                InformationWebViewFragment.this.mHandler.postDelayed(InformationWebViewFragment.this.mAutohideTimer, InformationWebViewFragment.this.autohide * 1000);
                return true;
            } else if (i != 101) {
                return true;
            } else {
                InformationWebViewFragment.this.mHandler.postDelayed(InformationWebViewFragment.this.mTimer, InformationWebViewFragment.this.rotate * 1000);
                return true;
            }
        }
    });
    private String header = "";
    boolean isResetDone;
    Runnable mAutohideTimer = new Runnable() {
        public void run() {
            InformationWebViewFragment.this.h.sendEmptyMessage(52);
        }
    };
    private String mChronicleId;
    private int mContentId;
    /* access modifiers changed from: private */
    public View mErrorView;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    ILoadNextListener mLoadMoreListener;
    private String mNextSectionName;
    private View mRootView;
    Runnable mTimer = new Runnable() {
        public void run() {
            InformationWebViewFragment.this.h.sendEmptyMessage(102);
        }
    };
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    protected long rotate;
    String uri;
    private WebView webView;

    public static Fragment newInstance(ILoadNextListener iLoadNextListener) {
        InformationWebViewFragment informationWebViewFragment = new InformationWebViewFragment();
        informationWebViewFragment.mLoadMoreListener = iLoadNextListener;
        return informationWebViewFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.drug_pricing_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        if (activity instanceof DrugDetailsActivity) {
            ((DrugDetailsActivity) activity).getAd();
        }
        View view = getView();
        view.findViewById(R.id.ad).setVisibility(8);
        this.mRootView = view.findViewById(R.id.root_layout);
        this.mErrorView = view.findViewById(R.id.noNetworkView);
        Bundle arguments = getArguments();
        this.mNextSectionName = arguments.getString("DIVIDER_NEXT") == null ? "" : arguments.getString("DIVIDER_NEXT");
        if (arguments != null) {
            this.mContentId = arguments.getInt("contentId", -1);
            this.mChronicleId = arguments.getString("chronicleId", (String) null);
            arguments.getString("contentType");
            this.uri = ((Uri) arguments.getParcelable("uri")).toString();
            this.header = arguments.getString("header");
        }
        String str = this.header;
        if (str != null) {
            activity.setTitle(str);
        } else {
            activity.setTitle("");
        }
        Util.setCookie(activity);
        ProgressBar progressBar2 = (ProgressBar) view.findViewById(R.id.progress);
        this.progressBar = progressBar2;
        progressBar2.setVisibility(0);
        WebView webView2 = (WebView) view.findViewById(R.id.webView);
        this.webView = webView2;
        webView2.getSettings().setUserAgentString(Util.addUserAgent(this.webView, getActivity()));
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.setScrollBarStyle(33554432);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new InsideWebViewClient());
        loadUrl();
    }

    private void sendBI() {
        HashMap hashMap;
        if (!this.mChronicleId.isEmpty()) {
            hashMap = new HashMap();
            hashMap.put("wapp.asset", hashMap);
        } else {
            hashMap = null;
        }
        HashMap hashMap2 = hashMap;
        OmnitureManager omnitureManager = OmnitureManager.get();
        FragmentActivity activity = getActivity();
        ((BaseActivity) getActivity()).setCurrentPvid(omnitureManager.trackPageView(activity, Constants.OMNITURE_CHANNEL_REFERENCE, "drug", "view", "" + this.mContentId, "9-images", hashMap2));
    }

    /* access modifiers changed from: private */
    public void loadUrl() {
        if (this.uri.startsWith("file")) {
            this.webView.loadUrl(this.uri);
        } else if (Util.isOnline(getActivity())) {
            this.webView.loadUrl(this.uri);
        } else {
            ((Button) this.mErrorView.findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    InformationWebViewFragment.this.mErrorView.setVisibility(8);
                    InformationWebViewFragment.this.isResetDone = false;
                    InformationWebViewFragment.this.loadUrl();
                }
            });
            this.progressBar.setVisibility(8);
        }
    }

    final class InsideWebViewClient extends WebViewClient {
        public void onReceivedError(WebView webView, int i, String str, String str2) {
        }

        InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            if (str.startsWith("tel:")) {
                Uri.parse(str);
                InformationWebViewFragment.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("geo")) {
                Uri.parse(str);
                InformationWebViewFragment.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("mail")) {
                Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                FragmentActivity activity = InformationWebViewFragment.this.getActivity();
                if (Util.isEmailConfigured(activity, intent)) {
                    InformationWebViewFragment.this.getActivity().startActivity(intent);
                } else if (!activity.isFinishing()) {
                    DialogUtil.showAlertDialog(24, (String) null, activity.getString(R.string.alert_show_email_configure_message), activity).show();
                }
                return true;
            } else {
                webView.loadUrl(str);
                return false;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            InformationWebViewFragment.this.progressBar.setVisibility(8);
            webView.loadUrl("javascript:alert(document.cookie);");
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            InformationWebViewFragment.this.progressBar.setVisibility(0);
        }
    }

    public void clearFocus() {
        View view = this.mRootView;
        if (view != null) {
            view.clearFocus();
        }
    }
}
