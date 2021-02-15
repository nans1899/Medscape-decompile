package com.medscape.android.search;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.search.model.SearchSuggestionMsg;
import java.util.Iterator;
import org.apache.commons.io.IOUtils;

public class SearchMsgViewHolder {
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public int mSearchMode;
    private SearchSuggestionMsg mSearchSuggestionMsg;
    private View mView;

    public SearchMsgViewHolder(View view, SearchSuggestionMsg searchSuggestionMsg, Activity activity, int i) {
        this.mView = view;
        this.mSearchSuggestionMsg = searchSuggestionMsg;
        this.mActivity = activity;
        this.mSearchMode = i;
    }

    public View bindView() {
        TextView textView = (TextView) this.mView.findViewById(R.id.no_results_msg);
        TextView textView2 = (TextView) this.mView.findViewById(R.id.user_auto_msg_action);
        TextView textView3 = (TextView) this.mView.findViewById(R.id.user_option_msg_action);
        LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.search_tips_layout);
        LinearLayout linearLayout2 = (LinearLayout) this.mView.findViewById(R.id.no_results_layout);
        View findViewById = this.mView.findViewById(R.id.divider);
        if (textView == null || textView2 == null || textView3 == null || linearLayout == null) {
            return this.mView;
        }
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        if (this.mSearchSuggestionMsg.getSuggestionMode() == 1) {
            linearLayout2.setVisibility(0);
            textView.setVisibility(0);
            textView3.setVisibility(8);
            textView2.setVisibility(8);
            findViewById.setVisibility(8);
            linearLayout.setVisibility(0);
            textView.setText(getNoResultsFormattedText());
            textView.setFocusable(true);
            buildSearchTips(linearLayout);
        } else if (this.mSearchSuggestionMsg.getSuggestionMode() == 3) {
            linearLayout2.setVisibility(8);
            textView2.setVisibility(0);
            findViewById.setVisibility(0);
            String string = this.mActivity.getResources().getString(R.string.show_results_for);
            String string2 = this.mActivity.getResources().getString(R.string.search_instead_for);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(string);
            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append(this.mSearchSuggestionMsg.getAutoCorrectedQuery());
            int length2 = spannableStringBuilder.length();
            spannableStringBuilder.setSpan(new StyleSpan(1), length, length2, 17);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mActivity, R.color.greyish_brown)), length, length2, 17);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.1f), 0, spannableStringBuilder.length(), 17);
            spannableStringBuilder.append(IOUtils.LINE_SEPARATOR_UNIX);
            spannableStringBuilder.append(string2);
            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            appendClickableQuery(spannableStringBuilder, this.mSearchSuggestionMsg.getUserQuery(), 4);
            textView2.setText(spannableStringBuilder);
            if (this.mSearchMode == Constants.SEARCH_REFERENCE) {
                textView2.setPadding(25, 0, 15, 0);
            }
        } else if (this.mSearchSuggestionMsg.getSuggestionMode() == 4) {
            linearLayout2.setVisibility(8);
            textView2.setVisibility(0);
            findViewById.setVisibility(0);
            textView2.setText(buildDidyouMeanMessage());
            if (this.mSearchMode == Constants.SEARCH_REFERENCE) {
                textView2.setPadding(25, 0, 15, 0);
            }
        } else if (this.mSearchSuggestionMsg.getSuggestionMode() == 2) {
            textView2.setVisibility(8);
            findViewById.setVisibility(8);
            linearLayout2.setVisibility(0);
            textView.setVisibility(0);
            textView3.setVisibility(0);
            linearLayout.setVisibility(0);
            textView.setText(getNoResultsFormattedText());
            textView3.setText(buildDidyouMeanMessage());
            buildSearchTips(linearLayout);
        }
        return this.mView;
    }

    private void buildSearchTips(LinearLayout linearLayout) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams((int) (((double) displayMetrics.widthPixels) * 0.7d), -2));
        View inflate = this.mActivity.getLayoutInflater().inflate(R.layout.search_tip_item, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.pointer)).setText("");
        TextView textView = (TextView) inflate.findViewById(R.id.tip_msg);
        textView.setTypeface((Typeface) null, 1);
        textView.setText(this.mActivity.getString(R.string.search_tips));
        textView.setTextColor(ContextCompat.getColor(this.mActivity, R.color.medscape_blue));
        linearLayout.addView(inflate);
        linearLayout.addView(getSearchTipItem(this.mActivity.getString(R.string.search_tip1)));
        linearLayout.addView(getSearchTipItem(this.mActivity.getString(R.string.search_tip2)));
        linearLayout.addView(getSearchTipItem(this.mActivity.getString(R.string.search_tip3)));
    }

    private SpannableStringBuilder appendClickableQuery(SpannableStringBuilder spannableStringBuilder, final String str, final int i) {
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(str);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (SearchMsgViewHolder.this.mActivity != null && (SearchMsgViewHolder.this.mActivity instanceof MedscapeSearchActivity)) {
                    String str = ((MedscapeSearchActivity) SearchMsgViewHolder.this.mActivity).mQuery;
                    ((MedscapeSearchActivity) SearchMsgViewHolder.this.mActivity).mSearchView.setQuery(str, false);
                    ((MedscapeSearchActivity) SearchMsgViewHolder.this.mActivity).mQuery = str;
                    ((MedscapeSearchActivity) SearchMsgViewHolder.this.mActivity).searchSuggestionType = i;
                    ((MedscapeSearchActivity) SearchMsgViewHolder.this.mActivity).searchWithQueryAndSubmit(SearchMsgViewHolder.this.mSearchMode, i);
                    SearchMsgViewHolder.this.sendOmniturePageView(str.equals(str), str);
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                if (SearchMsgViewHolder.this.mActivity != null) {
                    textPaint.setColor(ContextCompat.getColor(SearchMsgViewHolder.this.mActivity, R.color.link_blue));
                }
                textPaint.setUnderlineText(false);
            }
        }, length, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    private SpannableString getNoResultsFormattedText() {
        String string = this.mActivity.getResources().getString(R.string.no_results, new Object[]{this.mSearchSuggestionMsg.getUserQuery()});
        int length = this.mActivity.getResources().getString(R.string.no_results_start).length();
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new StyleSpan(1), length, this.mSearchSuggestionMsg.getUserQuery().length() + length, 17);
        return spannableString;
    }

    private View getSearchTipItem(String str) {
        View inflate = this.mActivity.getLayoutInflater().inflate(R.layout.search_tip_item, (ViewGroup) null);
        inflate.findViewById(R.id.pointer).setVisibility(0);
        ((TextView) inflate.findViewById(R.id.tip_msg)).setText(str);
        return inflate;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendOmniturePageView(boolean r12, java.lang.String r13) {
        /*
            r11 = this;
            android.app.Activity r0 = r11.mActivity
            if (r0 == 0) goto L_0x006c
            com.medscape.android.BI.omniture.OmnitureManager r0 = com.medscape.android.BI.omniture.OmnitureManager.get()
            java.lang.String r3 = r0.mSearchChannel
            if (r12 == 0) goto L_0x000f
            java.lang.String r0 = "search-original"
            goto L_0x0011
        L_0x000f:
            java.lang.String r0 = "search-spellcheck"
        L_0x0011:
            int r1 = r11.mSearchMode
            int r2 = com.medscape.android.Constants.SEARCH_REFERENCE
            java.lang.String r4 = "cme"
            java.lang.String r5 = "news"
            java.lang.String r6 = ""
            if (r1 != r2) goto L_0x0023
            java.lang.String r4 = "drgs"
            java.lang.String r1 = "drugs"
        L_0x0021:
            r5 = r1
            goto L_0x003f
        L_0x0023:
            int r1 = r11.mSearchMode
            int r2 = com.medscape.android.Constants.SEARCH_NEWS
            if (r1 != r2) goto L_0x002b
            r4 = r5
            goto L_0x003f
        L_0x002b:
            int r1 = r11.mSearchMode
            int r2 = com.medscape.android.Constants.SEARCH_EDUCATION
            if (r1 != r2) goto L_0x0032
            goto L_0x003e
        L_0x0032:
            int r1 = r11.mSearchMode
            int r2 = com.medscape.android.Constants.SEARCH_MEDLINE
            if (r1 != r2) goto L_0x003d
            java.lang.String r4 = "med"
            java.lang.String r1 = "medline"
            goto L_0x0021
        L_0x003d:
            r4 = r6
        L_0x003e:
            r5 = r4
        L_0x003f:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            if (r12 != 0) goto L_0x004b
            java.lang.String r12 = "wapp.querytext"
            r1.put(r12, r13)
        L_0x004b:
            com.medscape.android.BI.omniture.OmnitureManager r12 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r12.markModule(r0, r4, r1)
            com.medscape.android.BI.omniture.OmnitureManager r1 = com.medscape.android.BI.omniture.OmnitureManager.get()
            android.app.Activity r2 = r11.mActivity
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r4 = "search"
            java.lang.String r6 = "results"
            java.lang.String r10 = ""
            java.lang.String r12 = r1.trackPageView(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            android.app.Activity r13 = r11.mActivity
            com.medscape.android.base.BaseActivity r13 = (com.medscape.android.base.BaseActivity) r13
            r13.setCurrentPvid(r12)
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.search.SearchMsgViewHolder.sendOmniturePageView(boolean, java.lang.String):void");
    }

    private SpannableStringBuilder buildDidyouMeanMessage() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(this.mActivity.getString(R.string.search_did_you_mean));
        spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        SearchSuggestionMsg searchSuggestionMsg = this.mSearchSuggestionMsg;
        if (searchSuggestionMsg == null || searchSuggestionMsg.getSuggestions() == null || this.mSearchSuggestionMsg.getSuggestions().size() <= 0) {
            return new SpannableStringBuilder();
        }
        Iterator<String> it = this.mSearchSuggestionMsg.getSuggestions().iterator();
        boolean z = true;
        while (it.hasNext()) {
            String next = it.next();
            if (!z) {
                spannableStringBuilder.append(", ");
            }
            spannableStringBuilder = appendClickableQuery(spannableStringBuilder, next, 0);
            z = false;
        }
        return spannableStringBuilder;
    }
}
