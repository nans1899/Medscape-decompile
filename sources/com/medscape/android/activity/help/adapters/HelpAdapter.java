package com.medscape.android.activity.help.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.help.ProfileMenuItems;
import com.medscape.android.activity.help.viewholders.HelpViewHolder;
import com.medscape.android.contentviewer.interfaces.IDataListItemClickListener;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\u0018\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0014H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/medscape/android/activity/help/adapters/HelpAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/medscape/android/activity/help/viewholders/HelpViewHolder;", "activity", "Landroid/app/Activity;", "listItems", "", "Lcom/medscape/android/activity/help/ProfileMenuItems;", "listener", "Lcom/medscape/android/contentviewer/interfaces/IDataListItemClickListener;", "(Landroid/app/Activity;Ljava/util/List;Lcom/medscape/android/contentviewer/interfaces/IDataListItemClickListener;)V", "getActivity", "()Landroid/app/Activity;", "getListItems", "()Ljava/util/List;", "getListener", "()Lcom/medscape/android/contentviewer/interfaces/IDataListItemClickListener;", "setListener", "(Lcom/medscape/android/contentviewer/interfaces/IDataListItemClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HelpAdapter.kt */
public final class HelpAdapter extends RecyclerView.Adapter<HelpViewHolder> {
    private final Activity activity;
    private final List<ProfileMenuItems> listItems;
    private IDataListItemClickListener listener;

    public HelpAdapter(Activity activity2, List<ProfileMenuItems> list, IDataListItemClickListener iDataListItemClickListener) {
        Intrinsics.checkNotNullParameter(activity2, "activity");
        Intrinsics.checkNotNullParameter(list, "listItems");
        this.activity = activity2;
        this.listItems = list;
        this.listener = iDataListItemClickListener;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final List<ProfileMenuItems> getListItems() {
        return this.listItems;
    }

    public final IDataListItemClickListener getListener() {
        return this.listener;
    }

    public final void setListener(IDataListItemClickListener iDataListItemClickListener) {
        this.listener = iDataListItemClickListener;
    }

    public HelpViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.help_line_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "view");
        return new HelpViewHolder(inflate);
    }

    public int getItemCount() {
        return this.listItems.size();
    }

    public void onBindViewHolder(HelpViewHolder helpViewHolder, int i) {
        Intrinsics.checkNotNullParameter(helpViewHolder, "holder");
        helpViewHolder.bind(this.activity, this.listItems.get(i));
        helpViewHolder.getRootView().setOnClickListener(new HelpAdapter$onBindViewHolder$1(this, i));
    }
}
