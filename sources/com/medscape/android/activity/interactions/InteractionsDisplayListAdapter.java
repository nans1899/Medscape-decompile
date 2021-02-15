package com.medscape.android.activity.interactions;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.medscape.android.db.model.Interaction;
import de.halfbit.pinnedsection.PinnedSectionListView;
import java.util.List;

public class InteractionsDisplayListAdapter extends ArrayAdapter<Object> implements PinnedSectionListView.PinnedSectionListAdapter {
    private static final int VIEW_TYPES_COUNT = 3;
    private static final int VIEW_TYPE_HEADER = 2;
    private static final int VIEW_TYPE_INTERACTION = 1;
    private Context mContext;
    private List<Object> mItems;

    public boolean areAllItemsEnabled() {
        return false;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public boolean isItemViewTypePinned(int i) {
        return i == 2;
    }

    public InteractionsDisplayListAdapter(Context context, int i, List<Object> list) {
        super(context, i, list);
        this.mItems = list;
        this.mContext = context;
    }

    public int getCount() {
        return this.mItems.size();
    }

    public int getItemViewType(int i) {
        return getItem(i) instanceof Interaction ? 1 : 2;
    }

    public Object getItem(int i) {
        return this.mItems.get(i);
    }

    public boolean isEnabled(int i) {
        return !(this.mItems.get(i) instanceof String);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: com.medscape.android.db.model.Interaction} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r6 = this;
            java.lang.Object r8 = r6.getItem(r7)
            int r0 = r6.getItemViewType(r7)
            r1 = 2
            r2 = 0
            if (r0 == r1) goto L_0x004c
            java.lang.Object r8 = r6.getItem(r7)
            r2 = r8
            com.medscape.android.db.model.Interaction r2 = (com.medscape.android.db.model.Interaction) r2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r3 = "<b><font color=\"#83adca\">"
            r8.append(r3)
            java.lang.String r3 = r2.getSubjectName()
            r8.append(r3)
            java.lang.String r3 = " + "
            r8.append(r3)
            java.lang.String r3 = r2.getObjectName()
            r8.append(r3)
            java.lang.String r3 = "</font></b>"
            r8.append(r3)
            java.lang.String r3 = "<br>"
            r8.append(r3)
            android.content.Context r3 = r6.mContext
            java.lang.String r3 = r2.getContents(r3)
            java.lang.String r3 = android.text.TextUtils.htmlEncode(r3)
            r8.append(r3)
            java.lang.String r8 = r8.toString()
            goto L_0x006d
        L_0x004c:
            boolean r3 = r8 instanceof java.lang.String
            if (r3 == 0) goto L_0x006b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "<b>"
            r3.append(r4)
            java.lang.String r8 = r8.toString()
            r3.append(r8)
            java.lang.String r8 = "</b>"
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            goto L_0x006d
        L_0x006b:
            java.lang.String r8 = ""
        L_0x006d:
            android.content.Context r3 = r6.getContext()
            java.lang.String r4 = "layout_inflater"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.view.LayoutInflater r3 = (android.view.LayoutInflater) r3
            r4 = 0
            if (r0 != r1) goto L_0x0096
            r5 = 2131559008(0x7f0d0260, float:1.8743348E38)
            android.view.View r9 = r3.inflate(r5, r9, r4)
            android.content.Context r3 = r6.getContext()
            android.content.res.Resources r3 = r3.getResources()
            r4 = 2131099921(0x7f060111, float:1.7812209E38)
            int r3 = r3.getColor(r4)
            r9.setBackgroundColor(r3)
            goto L_0x009d
        L_0x0096:
            r5 = 2131559009(0x7f0d0261, float:1.874335E38)
            android.view.View r9 = r3.inflate(r5, r9, r4)
        L_0x009d:
            com.medscape.android.activity.interactions.InteractionsDisplaySectionViewHolder r3 = new com.medscape.android.activity.interactions.InteractionsDisplaySectionViewHolder
            r3.<init>()
            r3.position = r7
            r7 = 2131363510(0x7f0a06b6, float:1.834683E38)
            android.view.View r7 = r9.findViewById(r7)
            android.widget.TextView r7 = (android.widget.TextView) r7
            r3.title = r7
            if (r0 == r1) goto L_0x00c8
            android.widget.TextView r7 = r3.title
            android.text.Spanned r8 = android.text.Html.fromHtml(r8)
            r7.setText(r8)
            if (r2 == 0) goto L_0x00dd
            android.widget.TextView r7 = r3.title
            android.content.Context r8 = r6.mContext
            java.lang.String r8 = r2.getContents(r8)
            r7.setContentDescription(r8)
            goto L_0x00dd
        L_0x00c8:
            r7 = 2131362579(0x7f0a0313, float:1.8344943E38)
            android.view.View r7 = r9.findViewById(r7)
            r0 = 8
            r7.setVisibility(r0)
            android.widget.TextView r7 = r3.title
            android.text.Spanned r8 = android.text.Html.fromHtml(r8)
            r7.setText(r8)
        L_0x00dd:
            r9.setTag(r3)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.interactions.InteractionsDisplayListAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }
}
