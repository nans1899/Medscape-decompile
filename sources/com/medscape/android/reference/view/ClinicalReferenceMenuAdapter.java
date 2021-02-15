package com.medscape.android.reference.view;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.medscape.android.R;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.interfaces.RefreshableAdapter;
import com.medscape.android.reference.model.ClinicalReferenceContent;
import com.medscape.android.reference.model.Sect1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClinicalReferenceMenuAdapter extends ArrayAdapter<String> implements RefreshableAdapter<String> {
    Context mContext;
    private boolean mIsNightMode;

    public void removeInlineAD() {
    }

    public void setInlineAD(NativeDFPAD nativeDFPAD) {
    }

    public ClinicalReferenceMenuAdapter(Context context, List<String> list, boolean z) {
        super(context, 0, list);
        this.mContext = context;
        this.mIsNightMode = z;
    }

    public void refreshList(List<String> list) {
        clear();
        if (Build.VERSION.SDK_INT >= 11) {
            super.addAll(list);
        } else {
            for (String add : list) {
                super.add(add);
            }
        }
        notifyDataSetChanged();
    }

    public void addToList(String str) {
        add(str);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ItemHolder itemHolder;
        if (view == null) {
            itemHolder = new ItemHolder();
            view2 = View.inflate(this.mContext, R.layout.dd_group_item, (ViewGroup) null);
            itemHolder.title = (TextView) view2.findViewById(R.id.textTitle);
            view2.setTag(itemHolder);
        } else {
            view2 = view;
            itemHolder = (ItemHolder) view.getTag();
        }
        itemHolder.title.setText((String) getItem(i));
        if (this.mIsNightMode) {
            itemHolder.title.setTextColor(-1);
        } else {
            itemHolder.title.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
        return view2;
    }

    public ClinicalReferenceMenuAdapter setData(ClinicalReferenceContent clinicalReferenceContent) {
        refreshList(new ArrayList());
        if (!(clinicalReferenceContent == null || clinicalReferenceContent.sections == null)) {
            Iterator<Sect1> it = clinicalReferenceContent.sections.iterator();
            while (it.hasNext()) {
                addToList(it.next().title);
            }
        }
        notifyDataSetChanged();
        return this;
    }

    private static class ItemHolder {
        TextView title;

        private ItemHolder() {
        }
    }

    public void setNightMode(boolean z) {
        this.mIsNightMode = z;
        notifyDataSetChanged();
    }
}
