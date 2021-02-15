package com.webmd.wbmdcmepulse.adapters;

import android.content.Context;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.customviews.AnimatedExpandableListView;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import java.util.HashMap;
import java.util.List;

public class ArticleInfoContributorDisclosureAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private Context mContext;
    private HashMap<String, List<Object>> mExpandibleListDetail;
    private List<String> mExpandibleListTitle;

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    public ArticleInfoContributorDisclosureAdapter(Context context, List<String> list, HashMap<String, List<Object>> hashMap) {
        this.mContext = context;
        this.mExpandibleListTitle = list;
        this.mExpandibleListDetail = hashMap;
    }

    public int getGroupCount() {
        return this.mExpandibleListTitle.size();
    }

    public Object getGroup(int i) {
        return this.mExpandibleListTitle.get(i);
    }

    public Object getChild(int i, int i2) {
        return this.mExpandibleListDetail.get(this.mExpandibleListTitle.get(i)).get(i2);
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        String str = (String) getGroup(i);
        if (view == null) {
            view = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.article_info_expandible_titlerow, (ViewGroup) null);
        }
        ArticleCopyTextView articleCopyTextView = (ArticleCopyTextView) view.findViewById(R.id.listTitle);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon_arrow);
        if (z) {
            imageView.setImageResource(R.drawable.arrow_up);
        } else {
            imageView.setImageResource(R.drawable.arrow_down);
        }
        if (articleCopyTextView != null) {
            articleCopyTextView.setText(str);
            if (str.equals("Divider")) {
                view.setVisibility(4);
                articleCopyTextView.setTextSize(1.0f);
                imageView.setVisibility(8);
                articleCopyTextView.setPadding(0, 0, 0, 2);
            } else {
                view.setVisibility(0);
                articleCopyTextView.setTextSize(17.0f);
                imageView.setVisibility(0);
                articleCopyTextView.setPadding(0, 20, 0, 10);
            }
        }
        return view;
    }

    public View getRealChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        Object child = getChild(i, i2);
        if (view == null) {
            view = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.article_info_expandible_detail, (ViewGroup) null);
        }
        ArticleCopyTextView articleCopyTextView = (ArticleCopyTextView) view.findViewById(R.id.expandedListItem);
        if (child instanceof String) {
            String str = (String) child;
            if (!Extensions.isStringNullOrEmpty(str)) {
                articleCopyTextView.setText(str);
            }
        } else if (child instanceof Spanned) {
            Spanned spanned = (Spanned) child;
            if (!Extensions.isStringNullOrEmpty(spanned.toString())) {
                articleCopyTextView.setText(spanned);
            }
        }
        return view;
    }

    public int getRealChildrenCount(int i) {
        return this.mExpandibleListDetail.get(this.mExpandibleListTitle.get(i)).size();
    }
}
