package com.webmd.wbmdproffesionalauthentication.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.common.Scopes;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.fragments.SignUpEducationFragment;
import com.webmd.wbmdproffesionalauthentication.fragments.SignUpProfessionFragment;
import com.webmd.wbmdproffesionalauthentication.fragments.SignUpProfileFragment;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.utilities.AuthComponentConstants;

public class RegistrationActivity extends BaseActivity {
    private FragmentManager mFragmentManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleEmpty();
        setContentView(R.layout.registration_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.register_toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        this.mFragmentManager = getSupportFragmentManager();
        loadNextFragment(new SignUpProfessionFragment(), "profession");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        FragmentManager fragmentManager = this.mFragmentManager;
        String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        Fragment findFragmentById = this.mFragmentManager.findFragmentById(R.id.container);
        boolean z = false;
        if (findFragmentById != null) {
            if (findFragmentById instanceof SignUpProfileFragment) {
                z = ((SignUpProfileFragment) findFragmentById).isRegisterInProgress();
            }
            if (findFragmentById instanceof SignUpEducationFragment) {
                z = ((SignUpEducationFragment) findFragmentById).isRegisterInProgress();
            }
        }
        if (!z) {
            if (name != null) {
                if (name.contains("education")) {
                    UserProfile.getInstance().clearEducation();
                }
                if (name.contains(Scopes.PROFILE)) {
                    UserProfile.getInstance().clearBasicInfo();
                }
                if (name.contains("profession")) {
                    UserProfile.getInstance().clearProfessionProfile();
                }
            }
            if (this.mFragmentManager.getBackStackEntryCount() > 1) {
                try {
                    this.mFragmentManager.popBackStackImmediate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                UserProfile.getInstance().clearAll();
                finish();
            }
        }
    }

    public void loadNextFragment(Fragment fragment, String str) {
        if (fragment != null) {
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            beginTransaction.replace(R.id.container, fragment);
            beginTransaction.addToBackStack(str);
            beginTransaction.commit();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        UserProfile.getInstance().clearAll();
        super.onDestroy();
    }

    public void setSpannableTextForTerms(TextView textView) {
        AnonymousClass1 r0 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this.getBaseContext(), WebViewActivity.class);
                intent.putExtra(AuthComponentConstants.EXTRA_LINK, RegistrationActivity.this.getString(R.string.terms_and_conditions_link));
                intent.putExtra(AuthComponentConstants.EXTRA_TITLE, RegistrationActivity.this.getString(R.string.signup_profile_terms_of_use));
                RegistrationActivity.this.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        };
        AnonymousClass2 r1 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this.getBaseContext(), WebViewActivity.class);
                intent.putExtra(AuthComponentConstants.EXTRA_LINK, RegistrationActivity.this.getString(R.string.privacy_policy_link));
                intent.putExtra(AuthComponentConstants.EXTRA_TITLE, RegistrationActivity.this.getString(R.string.signup_profile_privacy_policy));
                RegistrationActivity.this.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        };
        String string = getString(R.string.signup_profile_disclaimer);
        String string2 = getString(R.string.signup_profile_terms_of_use);
        String string3 = getString(R.string.signup_profile_privacy_policy);
        int indexOf = string.indexOf(string2);
        int length = string2.length() + indexOf;
        int indexOf2 = string.indexOf(string3);
        int length2 = string3.length() + indexOf2;
        SpannableString spannableString = new SpannableString(string);
        int color = ResourcesCompat.getColor(getResources(), R.color.app_accent_color, (Resources.Theme) null);
        spannableString.setSpan(r0, indexOf, length, 33);
        spannableString.setSpan(new ForegroundColorSpan(color), indexOf, length, 33);
        spannableString.setSpan(r1, indexOf2, length2, 33);
        spannableString.setSpan(new ForegroundColorSpan(color), indexOf2, length2, 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(0);
    }
}
