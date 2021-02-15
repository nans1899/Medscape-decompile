package com.medscape.android.consult.postupdates.views;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.medscape.android.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"com/medscape/android/consult/postupdates/views/ConsultPostUpdateFragment$setUpListeners$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "", "p1", "", "p2", "p3", "onTextChanged", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultPostUpdateFragment.kt */
public final class ConsultPostUpdateFragment$setUpListeners$1 implements TextWatcher {
    final /* synthetic */ ConsultPostUpdateFragment this$0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    ConsultPostUpdateFragment$setUpListeners$1(ConsultPostUpdateFragment consultPostUpdateFragment) {
        this.this$0 = consultPostUpdateFragment;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        ConsultPostUpdateFragment.access$getViewModel$p(this.this$0).updatePostBody(String.valueOf(charSequence));
        TextView access$getCharacterCount$p = ConsultPostUpdateFragment.access$getCharacterCount$p(this.this$0);
        ConsultPostUpdateFragment consultPostUpdateFragment = this.this$0;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        String value = ConsultPostUpdateFragment.access$getViewModel$p(this.this$0).getPostUpdate().getValue();
        sb.append(String.valueOf(value != null ? Integer.valueOf(value.length()) : null));
        sb.append("");
        objArr[0] = sb.toString();
        access$getCharacterCount$p.setText(consultPostUpdateFragment.getString(R.string.consult_post_update_count, objArr));
    }
}
