package com.medscape.android.myinvites;

import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.myinvites.specific.Invitation;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000120\u0010\u0002\u001a,\u0012\u0004\u0012\u00020\u0004 \u0006*\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00050\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Ljava/util/ArrayList;", "Lcom/medscape/android/myinvites/specific/Invitation;", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: MyInvitationsActivity.kt */
final class MyInvitationsActivity$observeViewModel$2<T> implements Observer<ArrayList<Invitation>> {
    final /* synthetic */ MyInvitationsActivity this$0;

    MyInvitationsActivity$observeViewModel$2(MyInvitationsActivity myInvitationsActivity) {
        this.this$0 = myInvitationsActivity;
    }

    public final void onChanged(ArrayList<Invitation> arrayList) {
        Collection collection = arrayList;
        if (collection == null || collection.isEmpty()) {
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.text_no_data);
            Intrinsics.checkNotNullExpressionValue(textView, "text_no_data");
            textView.setVisibility(0);
            RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.recycler_view);
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recycler_view");
            recyclerView.setVisibility(8);
            return;
        }
        MyInvitationsActivity.access$getAdapter$p(this.this$0).addItems(arrayList);
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.text_no_data);
        Intrinsics.checkNotNullExpressionValue(textView2, "text_no_data");
        textView2.setVisibility(8);
        RecyclerView recyclerView2 = (RecyclerView) this.this$0._$_findCachedViewById(R.id.recycler_view);
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "recycler_view");
        recyclerView2.setVisibility(0);
    }
}
