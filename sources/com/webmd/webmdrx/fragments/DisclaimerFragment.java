package com.webmd.webmdrx.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.webmdrx.R;
import java.util.Map;

public class DisclaimerFragment extends Fragment {
    /* access modifiers changed from: private */
    public OnFAQSelectedListener faqListener;
    private boolean isCreated;
    private View mainView;
    Toolbar toolbar;

    public interface OnFAQSelectedListener {
        void onFAQSelected();
    }

    public void setFaqListener(OnFAQSelectedListener onFAQSelectedListener) {
        this.faqListener = onFAQSelectedListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mainView = layoutInflater.inflate(R.layout.fragment_disclaimer, viewGroup, false);
        AnonymousClass1 r6 = new ClickableSpan() {
            public void onClick(View view) {
                if (DisclaimerFragment.this.faqListener != null) {
                    DisclaimerFragment.this.faqListener.onFAQSelected();
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        };
        SpannableString spannableString = new SpannableString(getString(R.string.disclaimer_text_part1));
        String string = getString(R.string.disclaimer_text_part2);
        String string2 = this.mainView.getResources().getString(R.string.disclaimer_faq_page);
        int indexOf = string.indexOf(string2);
        int indexOf2 = string.indexOf(string2) + string2.length();
        SpannableString spannableString2 = new SpannableString(string);
        spannableString2.setSpan(r6, indexOf, indexOf2, 33);
        spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#1682C5")), indexOf, indexOf2, 33);
        TextView textView = (TextView) this.mainView.findViewById(R.id.f_disclaimer_text_view);
        textView.setText(TextUtils.concat(new CharSequence[]{spannableString, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, spannableString2}));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(0);
        return this.mainView;
    }

    public void onResume() {
        super.onResume();
        this.isCreated = false;
        setUpToolbar();
        WBMDOmnitureManager.sendPageView("settings/disclaimer", (Map<String, String>) null, new WBMDOmnitureModule((String) null, (String) null, WBMDOmnitureManager.shared.getLastSentPage()));
    }

    public void setUpToolbar() {
        Toolbar toolbar2 = (Toolbar) this.mainView.findViewById(R.id.disclaimer_toolbar);
        this.toolbar = toolbar2;
        toolbar2.setTitle((CharSequence) "Disclaimer");
        this.toolbar.setTitleTextColor((int) ViewCompat.MEASURED_STATE_MASK);
        ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DisclaimerFragment.this.getActivity().onBackPressed();
            }
        });
    }

    public void setCreated(boolean z) {
        this.isCreated = z;
    }

    public boolean getIsCreated() {
        return this.isCreated;
    }
}
