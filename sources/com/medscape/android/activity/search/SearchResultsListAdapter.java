package com.medscape.android.activity.search;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import androidx.core.content.ContextCompat;
import com.medscape.android.R;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.interfaces.RefreshableAdapter;
import java.util.List;

public class SearchResultsListAdapter extends BaseAdapter implements RefreshableAdapter<CRData> {
    private final Activity activity;
    private CRData adElement;
    private int blackColor;
    private List<CRData> elements;
    private NativeDFPAD inLineADView;
    private LayoutInflater inflater;

    public long getItemId(int i) {
        return (long) i;
    }

    public SearchResultsListAdapter(Activity activity2, List<CRData> list) {
        this.activity = activity2;
        this.elements = list;
        this.inflater = LayoutInflater.from(activity2);
        this.blackColor = ContextCompat.getColor(activity2, R.color.black);
    }

    public int getCount() {
        return this.elements.size();
    }

    public Object getItem(int i) {
        return this.elements.get(i);
    }

    /* JADX WARNING: type inference failed for: r8v6, types: [android.view.View] */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x006d, code lost:
        r7 = r5.inflater.inflate(com.medscape.android.R.layout.auto_complete_item, r8, false);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            java.util.List<com.medscape.android.activity.search.model.CRData> r0 = r5.elements
            java.lang.Object r6 = r0.get(r6)
            com.medscape.android.activity.search.model.CRData r6 = (com.medscape.android.activity.search.model.CRData) r6
            boolean r0 = r6.isInlineAD()
            r1 = 0
            if (r0 == 0) goto L_0x0034
            r5.adElement = r6
            android.view.LayoutInflater r6 = r5.inflater
            r7 = 2131559004(0x7f0d025c, float:1.874334E38)
            android.view.View r6 = r6.inflate(r7, r8, r1)
            com.medscape.android.ads.ShareThroughNativeADViewHolder r7 = new com.medscape.android.ads.ShareThroughNativeADViewHolder
            r7.<init>(r6)
            android.content.Context r8 = r6.getContext()
            r0 = 16
            float r8 = com.medscape.android.util.Util.dpToPixel(r8, r0)
            r7.applyPadding(r8)
            com.medscape.android.ads.NativeDFPAD r8 = r5.inLineADView
            r0 = 1
            r7.onBind(r8, r0)
            goto L_0x00d8
        L_0x0034:
            boolean r0 = r6.isExternalSearchDriver()
            if (r0 == 0) goto L_0x0045
            android.view.LayoutInflater r6 = r5.inflater
            r7 = 2131558990(0x7f0d024e, float:1.8743311E38)
            android.view.View r6 = r6.inflate(r7, r8, r1)
            goto L_0x00d8
        L_0x0045:
            com.medscape.android.activity.search.model.SearchSuggestionMsg r0 = r6.getSearchSuggestionMsg()
            if (r0 != 0) goto L_0x00ba
            r0 = 2131558505(0x7f0d0069, float:1.8742328E38)
            if (r7 != 0) goto L_0x0056
            android.view.LayoutInflater r7 = r5.inflater
            android.view.View r7 = r7.inflate(r0, r8, r1)
        L_0x0056:
            java.lang.String r2 = r6.getTitle()
            java.lang.String r3 = "''"
            java.lang.String r4 = "'"
            java.lang.String r2 = r2.replaceAll(r3, r4)
            r3 = 2131363066(0x7f0a04fa, float:1.834593E38)
            android.view.View r4 = r7.findViewById(r3)
            android.widget.TextView r4 = (android.widget.TextView) r4
            if (r4 != 0) goto L_0x007d
            android.view.LayoutInflater r7 = r5.inflater
            android.view.View r7 = r7.inflate(r0, r8, r1)
            android.view.View r8 = r7.findViewById(r3)
            r4 = r8
            android.widget.TextView r4 = (android.widget.TextView) r4
            if (r4 != 0) goto L_0x007d
            return r7
        L_0x007d:
            r8 = 2131362625(0x7f0a0341, float:1.8345036E38)
            android.view.View r8 = r7.findViewById(r8)
            r0 = 2131362918(0x7f0a0466, float:1.834563E38)
            android.view.View r0 = r7.findViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            if (r8 == 0) goto L_0x0092
            r8.setVisibility(r1)
        L_0x0092:
            r4.setText(r2)
            int r8 = r5.blackColor
            r4.setTextColor(r8)
            java.lang.String r8 = r6.getType()
            int r6 = r6.getCrType()
            int r6 = getResourceId(r8, r6)
            if (r6 <= 0) goto L_0x00b1
            if (r0 == 0) goto L_0x00b1
            r0.setVisibility(r1)
            r0.setImageResource(r6)
            goto L_0x00b8
        L_0x00b1:
            if (r0 == 0) goto L_0x00b8
            r6 = 8
            r0.setVisibility(r6)
        L_0x00b8:
            r6 = r7
            goto L_0x00d8
        L_0x00ba:
            android.view.LayoutInflater r7 = r5.inflater
            r0 = 2131558996(0x7f0d0254, float:1.8743324E38)
            android.view.View r7 = r7.inflate(r0, r8, r1)
            com.medscape.android.search.SearchMsgViewHolder r8 = new com.medscape.android.search.SearchMsgViewHolder
            com.medscape.android.activity.search.model.SearchSuggestionMsg r6 = r6.getSearchSuggestionMsg()
            android.app.Activity r0 = r5.activity
            com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_REFERENCE
            int r1 = r1.getId()
            r8.<init>(r7, r6, r0, r1)
            android.view.View r6 = r8.bindView()
        L_0x00d8:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchResultsListAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public void refreshList(List<CRData> list) {
        this.elements = list;
        notifyDataSetChanged();
    }

    public void addToList(CRData cRData) {
        this.elements.add(cRData);
    }

    public void removeInlineAD() {
        CRData cRData = this.adElement;
        if (cRData != null) {
            this.elements.remove(cRData);
            notifyDataSetChanged();
        }
    }

    public void setInlineAD(NativeDFPAD nativeDFPAD) {
        this.inLineADView = nativeDFPAD;
        notifyDataSetChanged();
    }

    private static int getResourceId(String str, int i) {
        if (SearchHelper.TYPE_DRUG.equals(str)) {
            return R.drawable.drugs3x;
        }
        if (SearchHelper.TYPE_CALCULATOR.equals(str)) {
            return R.drawable.calculator;
        }
        if ("T".equals(str)) {
            return i == 0 ? R.drawable.conditions3x : R.drawable.scissors_icon;
        }
        if (SearchHelper.TYPE_DRUG_CLASS.equals(str) || SearchHelper.TYPE_CALCULATOR_FOLDER.equals(str)) {
            return R.drawable.search_icon_class_blue;
        }
        boolean equals = "NR".equals(str);
        return 0;
    }
}
