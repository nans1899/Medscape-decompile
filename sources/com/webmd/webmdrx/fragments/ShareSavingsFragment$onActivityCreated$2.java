package com.webmd.webmdrx.fragments;

import android.view.View;
import android.widget.EditText;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ShareSavingsFragment.kt */
final class ShareSavingsFragment$onActivityCreated$2 implements View.OnClickListener {
    final /* synthetic */ ShareSavingsFragment this$0;

    ShareSavingsFragment$onActivityCreated$2(ShareSavingsFragment shareSavingsFragment) {
        this.this$0 = shareSavingsFragment;
    }

    public final void onClick(View view) {
        this.this$0.hideKeyboard();
        this.this$0.showLoadingSpinner();
        EditText access$getMContactInfoEditText$p = this.this$0.mContactInfoEditText;
        Intrinsics.checkNotNull(access$getMContactInfoEditText$p);
        String obj = access$getMContactInfoEditText$p.getText().toString();
        if (!this.this$0.mIsShareviaEmail) {
            String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(obj, "(", "", false, 4, (Object) null), ")", "", false, 4, (Object) null), "-", "", false, 4, (Object) null), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "", false, 4, (Object) null);
            if (Util.isValidUSMobile(replace$default)) {
                ShareSavingsFragment shareSavingsFragment = this.this$0;
                shareSavingsFragment.shareContactInfo(replace$default, shareSavingsFragment.mIsShareviaEmail);
            } else {
                ShareSavingsFragment shareSavingsFragment2 = this.this$0;
                String string = shareSavingsFragment2.getString(R.string.error_valid_phone_no_message);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error_valid_phone_no_message)");
                shareSavingsFragment2.showSimpleDialog(string);
            }
        } else if (Util.isValidEmail(obj)) {
            ShareSavingsFragment shareSavingsFragment3 = this.this$0;
            shareSavingsFragment3.shareContactInfo(obj, shareSavingsFragment3.mIsShareviaEmail);
        } else {
            ShareSavingsFragment shareSavingsFragment4 = this.this$0;
            String string2 = shareSavingsFragment4.getString(R.string.error_valid_email_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.error_valid_email_message)");
            shareSavingsFragment4.showSimpleDialog(string2);
        }
        WBMDOmnitureManager wBMDOmnitureManager = WBMDOmnitureManager.shared;
        Intrinsics.checkNotNullExpressionValue(wBMDOmnitureManager, "WBMDOmnitureManager.shared");
        WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule("wrx-share", "", wBMDOmnitureManager.getLastSentPage()));
    }
}
