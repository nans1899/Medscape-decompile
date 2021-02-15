package com.wbmd.qxcalculator.fragments.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.wbmd.omniture.utils.OmnitureHelper;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.FragmentContainerActivity;
import java.util.ArrayList;

public class WebViewFragment extends QxMDFragment {
    public static final String KEY_EXTRA_FROM_SECTION = "KEY_EXTRA_FROM_SECTION";
    public static final String KEY_URL = "WebViewFragment.KEY_URL";
    public static final String KEY_WEBVIEW_CONTENT_TYPE = "KEY_WEBVIEW_CONTENT_TYPE";
    private String sectionFrom;
    private String url;
    private WebView webView;
    private WebViewContentType webViewContentType;

    public enum WebViewContentType {
        TERMS_OF_USE,
        PRIVACY_POLICY,
        COOKIE_POLICY,
        DO_NOT_SELL_MY_INFO
    }

    public static WebViewFragment newInstance(String str) {
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_URL, str);
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.url = getArguments().getString(KEY_URL);
        this.webViewContentType = WebViewContentType.values()[getActivity().getIntent().getIntExtra(KEY_WEBVIEW_CONTENT_TYPE, 0)];
        this.sectionFrom = getActivity().getIntent().getStringExtra(KEY_EXTRA_FROM_SECTION);
        if (this.url == null) {
            getActivity().finish();
            return;
        }
        int i = AnonymousClass2.$SwitchMap$com$wbmd$qxcalculator$fragments$common$WebViewFragment$WebViewContentType[this.webViewContentType.ordinal()];
        if (i == 1) {
            setTitle(getString(R.string.nav_drawer_privacy_policy));
        } else if (i == 2) {
            setTitle(getString(R.string.nav_drawer_terms_of_use));
        } else if (i != 3) {
            setTitle(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        } else {
            setTitle(getString(R.string.do_not_sell_my_info));
        }
    }

    /* renamed from: com.wbmd.qxcalculator.fragments.common.WebViewFragment$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$fragments$common$WebViewFragment$WebViewContentType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.wbmd.qxcalculator.fragments.common.WebViewFragment$WebViewContentType[] r0 = com.wbmd.qxcalculator.fragments.common.WebViewFragment.WebViewContentType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$fragments$common$WebViewFragment$WebViewContentType = r0
                com.wbmd.qxcalculator.fragments.common.WebViewFragment$WebViewContentType r1 = com.wbmd.qxcalculator.fragments.common.WebViewFragment.WebViewContentType.PRIVACY_POLICY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$fragments$common$WebViewFragment$WebViewContentType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.fragments.common.WebViewFragment$WebViewContentType r1 = com.wbmd.qxcalculator.fragments.common.WebViewFragment.WebViewContentType.TERMS_OF_USE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$fragments$common$WebViewFragment$WebViewContentType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.fragments.common.WebViewFragment$WebViewContentType r1 = com.wbmd.qxcalculator.fragments.common.WebViewFragment.WebViewContentType.DO_NOT_SELL_MY_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$fragments$common$WebViewFragment$WebViewContentType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.fragments.common.WebViewFragment$WebViewContentType r1 = com.wbmd.qxcalculator.fragments.common.WebViewFragment.WebViewContentType.COOKIE_POLICY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.fragments.common.WebViewFragment.AnonymousClass2.<clinit>():void");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_web_view, viewGroup, false);
        WebView webView2 = (WebView) inflate.findViewById(R.id.web_view);
        this.webView = webView2;
        setContentView(webView2);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.setWebViewClient(new WebViewClient());
        this.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (WebViewFragment.this.getActivity() instanceof FragmentContainerActivity) {
                    ProgressBar progressBar = ((FragmentContainerActivity) WebViewFragment.this.getActivity()).webLoadingProgressBar;
                    if (i < 15) {
                        progressBar.setProgress(15);
                    } else {
                        progressBar.setProgress(i);
                    }
                    if (i == 100) {
                        progressBar.setVisibility(8);
                    } else {
                        progressBar.setVisibility(0);
                    }
                }
            }
        });
        this.webView.loadUrl(this.url);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        int i = AnonymousClass2.$SwitchMap$com$wbmd$qxcalculator$fragments$common$WebViewFragment$WebViewContentType[this.webViewContentType.ordinal()];
        if (i == 1) {
            ArrayList arrayList = new ArrayList();
            if (this.sectionFrom.equalsIgnoreCase(getString(R.string.welcome))) {
                arrayList.add(getString(R.string.welcome));
                arrayList.add(getString(R.string.privacy));
            } else {
                arrayList.add(getString(R.string.settings));
                arrayList.add(getString(R.string.privacy));
            }
            OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList);
        } else if (i == 2) {
            ArrayList arrayList2 = new ArrayList();
            if (this.sectionFrom.equalsIgnoreCase(getString(R.string.welcome))) {
                arrayList2.add(getString(R.string.welcome));
                arrayList2.add(getString(R.string.terms));
            } else {
                arrayList2.add(getString(R.string.settings));
                arrayList2.add(getString(R.string.terms));
            }
            OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList2);
        } else if (i == 3) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(getString(R.string.settings));
            arrayList3.add(getString(R.string.do_not_sell_my_info));
            OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList3);
        } else if (i == 4) {
            ArrayList arrayList4 = new ArrayList();
            if (this.sectionFrom.equalsIgnoreCase(getString(R.string.welcome))) {
                arrayList4.add(getString(R.string.welcome));
                arrayList4.add(getString(R.string.cookie));
            }
            OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList4);
        }
    }

    public boolean onBackButtonPressed() {
        if (!this.webView.canGoBack()) {
            return true;
        }
        this.webView.goBack();
        return false;
    }
}
