package com.wbmd.decisionpoint.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wbmd.decisionpoint.databinding.RowAnswersBinding;
import com.wbmd.decisionpoint.databinding.RowContributorItemBinding;
import com.wbmd.decisionpoint.databinding.RowContributorTypeBinding;
import com.wbmd.decisionpoint.databinding.RowDecisionPointBinding;
import com.wbmd.decisionpoint.databinding.RowExpertsAnswersBinding;
import com.wbmd.decisionpoint.databinding.RowHeaderBinding;
import com.wbmd.decisionpoint.domain.contributors.Contributor;
import com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoint;
import com.wbmd.decisionpoint.domain.interfaces.HubListener;
import com.wbmd.decisionpoint.ui.adapters.holders.AnswersViewHolder;
import com.wbmd.decisionpoint.ui.adapters.holders.ContributorItemViewHolder;
import com.wbmd.decisionpoint.ui.adapters.holders.ContributorTypeViewHolder;
import com.wbmd.decisionpoint.ui.adapters.holders.DecisionPointViewHolder;
import com.wbmd.decisionpoint.ui.adapters.holders.ExpertsAnswersViewHolder;
import com.wbmd.decisionpoint.ui.adapters.holders.HeaderViewHolder;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\b\u001b\u001c\u001d\u001e\u001f !\"B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u000f\u001a\u00020\u000b2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0018\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "listener", "Lcom/wbmd/decisionpoint/domain/interfaces/HubListener;", "(Lcom/wbmd/decisionpoint/domain/interfaces/HubListener;)V", "items", "Ljava/util/ArrayList;", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "Lkotlin/collections/ArrayList;", "addItem", "", "row", "toNotify", "", "addItems", "newItems", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "AnswersRow", "Companion", "ContributorItemRow", "ContributorTypeRow", "DecisionRow", "ExpertsAnswersRow", "HeaderRow", "Row", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HubAdapter.kt */
public final class HubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int TYPE_ANSWERS = 2;
    public static final int TYPE_CONTRIBUTOR_ITEM = 5;
    public static final int TYPE_CONTRIBUTOR_TYPE = 4;
    public static final int TYPE_DECISION_POINT = 1;
    public static final int TYPE_EXPERTS_ANSWERS = 3;
    public static final int TYPE_HEADER = 0;
    private ArrayList<Row> items = new ArrayList<>();
    private final HubListener listener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$AnswersRow;", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "()V", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubAdapter.kt */
    public static final class AnswersRow extends Row {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$ExpertsAnswersRow;", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "()V", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubAdapter.kt */
    public static final class ExpertsAnswersRow extends Row {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$HeaderRow;", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "()V", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubAdapter.kt */
    public static final class HeaderRow extends Row {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "", "()V", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubAdapter.kt */
    public static abstract class Row {
    }

    public HubAdapter(HubListener hubListener) {
        Intrinsics.checkNotNullParameter(hubListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = hubListener;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$DecisionRow;", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "decisionPoint", "Lcom/wbmd/decisionpoint/domain/decisionpoints/DecisionPoint;", "(Lcom/wbmd/decisionpoint/domain/decisionpoints/DecisionPoint;)V", "getDecisionPoint", "()Lcom/wbmd/decisionpoint/domain/decisionpoints/DecisionPoint;", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubAdapter.kt */
    public static final class DecisionRow extends Row {
        private final DecisionPoint decisionPoint;

        public DecisionRow(DecisionPoint decisionPoint2) {
            Intrinsics.checkNotNullParameter(decisionPoint2, "decisionPoint");
            this.decisionPoint = decisionPoint2;
        }

        public final DecisionPoint getDecisionPoint() {
            return this.decisionPoint;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$ContributorTypeRow;", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "type", "", "(Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubAdapter.kt */
    public static final class ContributorTypeRow extends Row {
        private final String type;

        public ContributorTypeRow(String str) {
            this.type = str;
        }

        public final String getType() {
            return this.type;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$ContributorItemRow;", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "contributor", "Lcom/wbmd/decisionpoint/domain/contributors/Contributor;", "(Lcom/wbmd/decisionpoint/domain/contributors/Contributor;)V", "getContributor", "()Lcom/wbmd/decisionpoint/domain/contributors/Contributor;", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubAdapter.kt */
    public static final class ContributorItemRow extends Row {
        private final Contributor contributor;

        public ContributorItemRow(Contributor contributor2) {
            Intrinsics.checkNotNullParameter(contributor2, "contributor");
            this.contributor = contributor2;
        }

        public final Contributor getContributor() {
            return this.contributor;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Companion;", "", "()V", "TYPE_ANSWERS", "", "TYPE_CONTRIBUTOR_ITEM", "TYPE_CONTRIBUTOR_TYPE", "TYPE_DECISION_POINT", "TYPE_EXPERTS_ANSWERS", "TYPE_HEADER", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HubAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            RowHeaderBinding inflate = RowHeaderBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "RowHeaderBinding.inflate…tInflater, parent, false)");
            return new HeaderViewHolder(inflate);
        } else if (i == 1) {
            RowDecisionPointBinding inflate2 = RowDecisionPointBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "RowDecisionPointBinding.…tInflater, parent, false)");
            return new DecisionPointViewHolder(inflate2, this.listener);
        } else if (i == 2) {
            RowAnswersBinding inflate3 = RowAnswersBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate3, "RowAnswersBinding.inflat…tInflater, parent, false)");
            return new AnswersViewHolder(inflate3);
        } else if (i == 3) {
            RowExpertsAnswersBinding inflate4 = RowExpertsAnswersBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate4, "RowExpertsAnswersBinding…tInflater, parent, false)");
            return new ExpertsAnswersViewHolder(inflate4);
        } else if (i == 4) {
            RowContributorTypeBinding inflate5 = RowContributorTypeBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate5, "RowContributorTypeBindin…tInflater, parent, false)");
            return new ContributorTypeViewHolder(inflate5);
        } else if (i == 5) {
            RowContributorItemBinding inflate6 = RowContributorItemBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate6, "RowContributorItemBindin…tInflater, parent, false)");
            return new ContributorItemViewHolder(inflate6);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Row row = this.items.get(i);
        Intrinsics.checkNotNullExpressionValue(row, "items[position]");
        Row row2 = row;
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType == 0) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
        } else if (itemViewType == 1) {
            DecisionPointViewHolder decisionPointViewHolder = (DecisionPointViewHolder) viewHolder;
            if (row2 != null) {
                decisionPointViewHolder.bindData(((DecisionRow) row2).getDecisionPoint());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.wbmd.decisionpoint.ui.adapters.HubAdapter.DecisionRow");
        } else if (itemViewType == 2) {
            AnswersViewHolder answersViewHolder = (AnswersViewHolder) viewHolder;
        } else if (itemViewType == 3) {
            ExpertsAnswersViewHolder expertsAnswersViewHolder = (ExpertsAnswersViewHolder) viewHolder;
        } else if (itemViewType == 4) {
            ContributorTypeViewHolder contributorTypeViewHolder = (ContributorTypeViewHolder) viewHolder;
            if (row2 != null) {
                contributorTypeViewHolder.bindData(((ContributorTypeRow) row2).getType());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.wbmd.decisionpoint.ui.adapters.HubAdapter.ContributorTypeRow");
        } else if (itemViewType == 5) {
            ContributorItemViewHolder contributorItemViewHolder = (ContributorItemViewHolder) viewHolder;
            if (row2 != null) {
                contributorItemViewHolder.bindData(((ContributorItemRow) row2).getContributor());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.wbmd.decisionpoint.ui.adapters.HubAdapter.ContributorItemRow");
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static /* synthetic */ void addItem$default(HubAdapter hubAdapter, Row row, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        hubAdapter.addItem(row, z);
    }

    public final void addItem(Row row, boolean z) {
        Intrinsics.checkNotNullParameter(row, "row");
        this.items.add(row);
        if (z) {
            notifyDataSetChanged();
        }
    }

    public final void addItems(ArrayList<Row> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "newItems");
        this.items.addAll(arrayList);
        notifyDataSetChanged();
    }

    public int getItemViewType(int i) {
        Row row = this.items.get(i);
        if (row instanceof HeaderRow) {
            return 0;
        }
        if (row instanceof DecisionRow) {
            return 1;
        }
        if (row instanceof AnswersRow) {
            return 2;
        }
        if (row instanceof ExpertsAnswersRow) {
            return 3;
        }
        if (row instanceof ContributorTypeRow) {
            return 4;
        }
        if (row instanceof ContributorItemRow) {
            return 5;
        }
        throw new IllegalArgumentException();
    }
}
