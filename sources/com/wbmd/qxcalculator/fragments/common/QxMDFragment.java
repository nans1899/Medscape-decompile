package com.wbmd.qxcalculator.fragments.common;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.FragmentContainerActivity;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.fragments.common.WebViewFragment;
import com.wbmd.qxcalculator.managers.QxFirebaseEventManager;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.CrashLogger;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.List;

public class QxMDFragment extends Fragment {
    private static final String KEY_ERROR_STRING = "KEY_ERROR_STRING";
    private static final String KEY_HAS_MADE_EDITS = "KEY_HAS_MADE_EDITS";
    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_VIEW_MODE_ORDINAL = "KEY_VIEW_MODE_ORDINAL";
    private final String KEY_TERMS_TYPE = "terms_type";
    private View contentView;
    private String errorString;
    private TextView errorTextView;
    private View errorView;
    protected boolean hasMadeEdits;
    protected boolean isFinishing;
    protected boolean isSaving;
    private View loadingView;
    private boolean promptOnUnsavedEdits;
    private View savingOverlayView;
    private boolean showDoneButton;
    private String title;
    protected ViewMode viewMode = ViewMode.IDLE;

    protected enum ViewMode {
        NOT_SET,
        IDLE,
        LOADING,
        SAVING,
        ERROR
    }

    /* access modifiers changed from: protected */
    public void errorViewTapped() {
    }

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onDoneButtonPressed() {
    }

    /* access modifiers changed from: protected */
    public boolean shouldAutoTrackScreenViewAnalyticsOnResume() {
        return true;
    }

    public void toolbarSearchEnterred(String str) {
    }

    public void setLoadingView(View view) {
        this.loadingView = view;
        View findViewById = view.findViewById(R.id.refreshing_text_view);
        if (findViewById != null && (findViewById instanceof TextView)) {
            ((TextView) findViewById).setText(textForLoadingView());
        }
        View findViewById2 = view.findViewById(R.id.loading_progress_bar);
        if (findViewById2 != null && (findViewById2 instanceof ProgressBar)) {
            ((ProgressBar) findViewById2).getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.progress_bar), PorterDuff.Mode.SRC_IN);
        }
    }

    public void setErrorView(View view) {
        this.errorView = view;
        TextView textView = (TextView) view.findViewById(R.id.error_text_view);
        if (textView != null && (textView instanceof TextView)) {
            this.errorTextView = textView;
            textView.setText(textForErrorView((String) null));
        }
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QxMDFragment.this.errorViewTapped();
            }
        });
    }

    /* access modifiers changed from: protected */
    public String textForErrorView(String str) {
        return getString(R.string.general_error_message);
    }

    /* access modifiers changed from: protected */
    public void showErrorSavingDialog(List<QxError> list) {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.dialog_error_saving_changes_title).setMessage(getString(R.string.dialog_error_saving_changes_message, QxError.concatenateErrorsIntoString(list))).setPositiveButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
    }

    /* access modifiers changed from: protected */
    public void showErrorDialog(List<QxError> list) {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.dialog_error_general_title).setMessage(getString(R.string.dialog_error_general_message, QxError.concatenateErrorsIntoString(list))).setPositiveButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
    }

    /* access modifiers changed from: protected */
    public String textForLoadingView() {
        return getString(R.string.loading);
    }

    public void setContentView(View view) {
        this.contentView = view;
    }

    public void setSavingOverlayView(View view) {
        this.savingOverlayView = view;
    }

    public void setTitle(String str) {
        this.title = str;
        getActivity().setTitle(str);
    }

    /* access modifiers changed from: protected */
    public void setViewMode(ViewMode viewMode2) {
        setViewMode(viewMode2, false);
    }

    /* access modifiers changed from: protected */
    public void setViewMode(ViewMode viewMode2, boolean z) {
        setViewMode(viewMode2, z, (String) null);
    }

    /* access modifiers changed from: protected */
    public void setViewMode(ViewMode viewMode2, String str) {
        setViewMode(viewMode2, false, str);
    }

    /* access modifiers changed from: protected */
    public void setViewMode(ViewMode viewMode2, boolean z, String str) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        ProgressBar progressBar3;
        ProgressBar progressBar4;
        if (this.viewMode != viewMode2 || z) {
            ViewMode viewMode3 = this.viewMode;
            this.viewMode = viewMode2;
            int i = AnonymousClass5.$SwitchMap$com$wbmd$qxcalculator$fragments$common$QxMDFragment$ViewMode[viewMode2.ordinal()];
            if (i == 1) {
                View view = this.contentView;
                if (view != null) {
                    view.setVisibility(0);
                }
                View view2 = this.loadingView;
                if (view2 != null) {
                    view2.setVisibility(8);
                }
                View view3 = this.errorView;
                if (view3 != null) {
                    view3.setVisibility(8);
                }
                View view4 = this.savingOverlayView;
                if (view4 != null) {
                    view4.setVisibility(8);
                }
                this.isSaving = false;
                if (getActivity() != null) {
                    if ((getActivity() instanceof QxMDActivity) && (progressBar = ((QxMDActivity) getActivity()).toolbarProgressBar) != null) {
                        progressBar.setVisibility(8);
                    }
                    if (getActivity() instanceof QxMDActivity) {
                        if (viewMode3 == ViewMode.SAVING) {
                            ((QxMDActivity) getActivity()).setUserInteractionEnabled(true);
                        }
                        if (this.title != null && viewMode3 == ViewMode.SAVING) {
                            setTitle(this.title);
                        }
                        getActivity().invalidateOptionsMenu();
                    }
                }
            } else if (i == 2) {
                View view5 = this.contentView;
                if (view5 != null) {
                    view5.setVisibility(8);
                }
                View view6 = this.loadingView;
                if (view6 != null) {
                    view6.setVisibility(0);
                }
                View view7 = this.errorView;
                if (view7 != null) {
                    view7.setVisibility(8);
                }
                View view8 = this.savingOverlayView;
                if (view8 != null) {
                    view8.setVisibility(8);
                }
                this.isSaving = false;
                if (getActivity() != null) {
                    if ((getActivity() instanceof QxMDActivity) && (progressBar2 = ((QxMDActivity) getActivity()).toolbarProgressBar) != null) {
                        progressBar2.setVisibility(8);
                    }
                    if (getActivity() instanceof QxMDActivity) {
                        if (viewMode3 == ViewMode.SAVING) {
                            ((QxMDActivity) getActivity()).setUserInteractionEnabled(true);
                        }
                        if (this.title != null && viewMode3 == ViewMode.SAVING) {
                            setTitle(this.title);
                        }
                        getActivity().invalidateOptionsMenu();
                    }
                }
            } else if (i == 3) {
                this.errorString = str;
                View view9 = this.contentView;
                if (view9 != null) {
                    view9.setVisibility(8);
                }
                View view10 = this.loadingView;
                if (view10 != null) {
                    view10.setVisibility(8);
                }
                View view11 = this.errorView;
                if (view11 != null) {
                    view11.setVisibility(0);
                    TextView textView = this.errorTextView;
                    if (textView != null) {
                        textView.setText(textForErrorView(str));
                    }
                }
                View view12 = this.savingOverlayView;
                if (view12 != null) {
                    view12.setVisibility(8);
                }
                this.isSaving = false;
                if (getActivity() != null) {
                    if ((getActivity() instanceof QxMDActivity) && (progressBar3 = ((QxMDActivity) getActivity()).toolbarProgressBar) != null) {
                        progressBar3.setVisibility(8);
                    }
                    if (getActivity() instanceof QxMDActivity) {
                        if (viewMode3 == ViewMode.SAVING) {
                            ((QxMDActivity) getActivity()).setUserInteractionEnabled(true);
                        }
                        if (this.title != null && viewMode3 == ViewMode.SAVING) {
                            setTitle(this.title);
                        }
                        getActivity().invalidateOptionsMenu();
                    }
                }
                this.isSaving = false;
            } else if (i == 4) {
                View view13 = this.contentView;
                if (view13 != null) {
                    view13.setVisibility(0);
                }
                View view14 = this.loadingView;
                if (view14 != null) {
                    view14.setVisibility(8);
                }
                View view15 = this.errorView;
                if (view15 != null) {
                    view15.setVisibility(8);
                }
                View view16 = this.savingOverlayView;
                if (view16 != null) {
                    view16.setVisibility(0);
                }
                this.isSaving = true;
                if (getActivity() != null) {
                    View currentFocus = getActivity().getCurrentFocus();
                    if (currentFocus != null) {
                        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    }
                    if ((getActivity() instanceof QxMDActivity) && (progressBar4 = ((QxMDActivity) getActivity()).toolbarProgressBar) != null) {
                        progressBar4.setVisibility(0);
                    }
                    if (getActivity() instanceof QxMDActivity) {
                        ((QxMDActivity) getActivity()).setUserInteractionEnabled(false);
                        ((QxMDActivity) getActivity()).setTitle(getStringForSavingTitle());
                        getActivity().invalidateOptionsMenu();
                    }
                }
            }
        }
    }

    /* renamed from: com.wbmd.qxcalculator.fragments.common.QxMDFragment$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$fragments$common$QxMDFragment$ViewMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.wbmd.qxcalculator.fragments.common.QxMDFragment$ViewMode[] r0 = com.wbmd.qxcalculator.fragments.common.QxMDFragment.ViewMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$fragments$common$QxMDFragment$ViewMode = r0
                com.wbmd.qxcalculator.fragments.common.QxMDFragment$ViewMode r1 = com.wbmd.qxcalculator.fragments.common.QxMDFragment.ViewMode.IDLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$fragments$common$QxMDFragment$ViewMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.fragments.common.QxMDFragment$ViewMode r1 = com.wbmd.qxcalculator.fragments.common.QxMDFragment.ViewMode.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$fragments$common$QxMDFragment$ViewMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.fragments.common.QxMDFragment$ViewMode r1 = com.wbmd.qxcalculator.fragments.common.QxMDFragment.ViewMode.ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$fragments$common$QxMDFragment$ViewMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.fragments.common.QxMDFragment$ViewMode r1 = com.wbmd.qxcalculator.fragments.common.QxMDFragment.ViewMode.SAVING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.fragments.common.QxMDFragment.AnonymousClass5.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public String getStringForSavingTitle() {
        return getString(R.string.saving);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            CrashLogger instance = CrashLogger.getInstance();
            instance.leaveBreadcrumb("onCreate (fresh)" + getClass().getSimpleName());
        } else {
            CrashLogger instance2 = CrashLogger.getInstance();
            instance2.leaveBreadcrumb("onCreate (restore)" + getClass().getSimpleName());
        }
        if (bundle != null) {
            this.hasMadeEdits = bundle.getBoolean(KEY_HAS_MADE_EDITS, false);
            this.viewMode = ViewMode.values()[bundle.getInt(KEY_VIEW_MODE_ORDINAL, 0)];
            String string = bundle.getString("KEY_TITLE");
            if (string != null) {
                setTitle(string);
            }
            this.errorString = bundle.getString(KEY_ERROR_STRING);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setViewMode(this.viewMode, true, this.errorString);
        this.title = getActivity().getTitle().toString();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_HAS_MADE_EDITS, this.hasMadeEdits);
        bundle.putInt(KEY_VIEW_MODE_ORDINAL, this.viewMode.ordinal());
        bundle.putString("KEY_TITLE", this.title);
        bundle.putString(KEY_ERROR_STRING, this.errorString);
    }

    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && getAnalyticsScreenName() != null && shouldAutoTrackScreenViewAnalyticsOnResume()) {
            trackScreenViewAnalytics();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        CrashLogger instance = CrashLogger.getInstance();
        instance.leaveBreadcrumb("destroy " + getClass().getSimpleName());
    }

    /* access modifiers changed from: protected */
    public void trackScreenViewAnalytics() {
        if (getAnalyticsScreenName() != null) {
            AnalyticsHandler.getInstance().trackPageView(getActivity(), getAnalyticsScreenName());
        }
    }

    public boolean onBackButtonPressed() {
        if (this.isSaving) {
            return false;
        }
        if (!this.hasMadeEdits || !this.promptOnUnsavedEdits) {
            return true;
        }
        new AlertDialog.Builder(getActivity()).setTitle(R.string.changes_not_saved_title).setMessage(R.string.changes_not_saved_message).setNegativeButton(R.string.changes_not_saved_do_not_save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((QxMDActivity) QxMDFragment.this.getActivity()).finishWithResults(0, (Intent) null);
            }
        }).setPositiveButton(R.string.changes_not_saved_save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                QxMDFragment.this.onDoneButtonPressed();
            }
        }).setNeutralButton(R.string.cancel, (DialogInterface.OnClickListener) null).create().show();
        return false;
    }

    public void willBeFinished() {
        this.isFinishing = true;
    }

    /* access modifiers changed from: protected */
    public void setShowDoneButton(boolean z, boolean z2) {
        this.showDoneButton = z;
        this.promptOnUnsavedEdits = z2;
        setHasOptionsMenu(z);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        if (this.showDoneButton && !this.isSaving) {
            createDoneButton(menu, menuInflater);
        }
    }

    /* access modifiers changed from: protected */
    public void createDoneButton(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_done, menu);
        MenuItem findItem = menu.findItem(R.id.menu_done);
        findItem.getIcon().setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.toolbar_tint), PorterDuff.Mode.SRC_IN));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_done) {
            return super.onOptionsItemSelected(menuItem);
        }
        onDoneButtonPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void setTextViewHTML(TextView textView, String str) {
        Spanned fromHtml = Html.fromHtml(str);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fromHtml);
        for (URLSpan makeLinkClickable : (URLSpan[]) spannableStringBuilder.getSpans(0, fromHtml.length(), URLSpan.class)) {
            makeLinkClickable(spannableStringBuilder, makeLinkClickable);
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: protected */
    public void makeLinkClickable(SpannableStringBuilder spannableStringBuilder, final URLSpan uRLSpan) {
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                String url = uRLSpan.getURL();
                QxMDFragment.this.sendEventsForClickedUrl(url);
                Intent intent = new Intent(QxMDFragment.this.getActivity(), FragmentContainerActivity.class);
                intent.putExtra("EXTRA_FRAGMENT_TYPE", FragmentContainerActivity.EXTRA_FRAGMENT_TYPE_WEB_VIEW);
                if (url.contains(QxMDFragment.this.getString(R.string.terms))) {
                    intent.putExtra(WebViewFragment.KEY_WEBVIEW_CONTENT_TYPE, WebViewFragment.WebViewContentType.TERMS_OF_USE.ordinal());
                    intent.putExtra(WebViewFragment.KEY_EXTRA_FROM_SECTION, QxMDFragment.this.getString(R.string.welcome));
                } else if (url.contains(QxMDFragment.this.getString(R.string.privacy))) {
                    intent.putExtra(WebViewFragment.KEY_WEBVIEW_CONTENT_TYPE, WebViewFragment.WebViewContentType.PRIVACY_POLICY.ordinal());
                    intent.putExtra(WebViewFragment.KEY_EXTRA_FROM_SECTION, QxMDFragment.this.getString(R.string.welcome));
                } else if (url.contains(QxMDFragment.this.getString(R.string.cookie))) {
                    intent.putExtra(WebViewFragment.KEY_WEBVIEW_CONTENT_TYPE, WebViewFragment.WebViewContentType.COOKIE_POLICY.ordinal());
                    intent.putExtra(WebViewFragment.KEY_EXTRA_FROM_SECTION, QxMDFragment.this.getString(R.string.welcome));
                }
                intent.putExtra(WebViewFragment.KEY_URL, url);
                QxMDFragment.this.startActivity(intent);
            }
        }, spannableStringBuilder.getSpanStart(uRLSpan), spannableStringBuilder.getSpanEnd(uRLSpan), spannableStringBuilder.getSpanFlags(uRLSpan));
        spannableStringBuilder.removeSpan(uRLSpan);
    }

    /* access modifiers changed from: private */
    public void sendEventsForClickedUrl(String str) {
        Bundle bundle = new Bundle();
        if (str.contains(Constants.TERMSOFUSE_VERSION)) {
            bundle.putString("terms_type", "terms_of_use");
        } else if (str.contains("privacy")) {
            bundle.putString("terms_type", "privacy_policy");
        } else if (str.contains("cookies")) {
            bundle.putString("terms_type", "cookie_policy");
        }
        QxFirebaseEventManager.getInstance(getActivity()).sendEventName("clicked_terms", bundle);
    }
}
