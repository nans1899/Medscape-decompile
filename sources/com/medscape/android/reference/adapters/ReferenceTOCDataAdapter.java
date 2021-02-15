package com.medscape.android.reference.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.reference.interfaces.ISectionItemClickListener;
import com.medscape.android.reference.model.Sect1;
import com.medscape.android.reference.viewholders.TOCItemViewHolder;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u001bH\u0016J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001bH\u0016J\u001e\u0010%\u001a\u00020\u001e2\u0016\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015J\u000e\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0011J\u000e\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001bR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R*\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/medscape/android/reference/adapters/ReferenceTOCDataAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "clickListener", "Lcom/medscape/android/reference/interfaces/ISectionItemClickListener;", "(Landroid/content/Context;Lcom/medscape/android/reference/interfaces/ISectionItemClickListener;)V", "getClickListener", "()Lcom/medscape/android/reference/interfaces/ISectionItemClickListener;", "setClickListener", "(Lcom/medscape/android/reference/interfaces/ISectionItemClickListener;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "isNightMode", "", "sections", "Ljava/util/ArrayList;", "Lcom/medscape/android/reference/model/Sect1;", "Lkotlin/collections/ArrayList;", "getSections", "()Ljava/util/ArrayList;", "setSections", "(Ljava/util/ArrayList;)V", "textSize", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "data", "setNightMode", "nightMode", "setTextSizeIndex", "textSizeIndex", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ReferenceTOCDataAdapter.kt */
public final class ReferenceTOCDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ISectionItemClickListener clickListener;
    private Context context;
    private boolean isNightMode;
    private ArrayList<Sect1> sections = new ArrayList<>();
    private int textSize = -1;

    public ReferenceTOCDataAdapter(Context context2, ISectionItemClickListener iSectionItemClickListener) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(iSectionItemClickListener, "clickListener");
        this.context = context2;
        this.clickListener = iSectionItemClickListener;
    }

    public final ISectionItemClickListener getClickListener() {
        return this.clickListener;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setClickListener(ISectionItemClickListener iSectionItemClickListener) {
        Intrinsics.checkNotNullParameter(iSectionItemClickListener, "<set-?>");
        this.clickListener = iSectionItemClickListener;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public final ArrayList<Sect1> getSections() {
        return this.sections;
    }

    public final void setSections(ArrayList<Sect1> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.sections = arrayList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.toc_adapter_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "LayoutInflater.from(pare…pter_item, parent, false)");
        return new TOCItemViewHolder(inflate);
    }

    public int getItemCount() {
        return this.sections.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Sect1 sect1 = this.sections.get(i);
        Intrinsics.checkNotNullExpressionValue(sect1, "sections[position]");
        ((TOCItemViewHolder) viewHolder).bindData(sect1, this.clickListener, this.textSize, this.isNightMode, i, this.sections.size());
    }

    public final void setData(ArrayList<Sect1> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "data");
        this.sections.clear();
        this.sections.addAll(arrayList);
        notifyDataSetChanged();
    }

    public final void setTextSizeIndex(int i) {
        this.textSize = i;
        notifyDataSetChanged();
    }

    public final void setNightMode(boolean z) {
        this.isNightMode = z;
        notifyDataSetChanged();
    }
}
