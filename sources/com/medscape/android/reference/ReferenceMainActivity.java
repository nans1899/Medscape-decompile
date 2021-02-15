package com.medscape.android.reference;

import android.os.Bundle;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;

public class ReferenceMainActivity extends AbstractBreadcrumbNavigableActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!AccountProvider.isUserLoggedIn(this)) {
            finish();
        }
    }
}
