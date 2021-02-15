package com.medscape.android.consult.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.medscape.android.R;
import com.medscape.android.consult.models.ConsultTimeLineFilterItem;
import java.util.ArrayList;
import java.util.List;

public class ConsultTimelineFilterAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private List<ConsultTimeLineFilterItem> mFilterItems = new ArrayList();

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public ConsultTimelineFilterAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<ConsultTimeLineFilterItem> list) {
        if (list != null) {
            this.mFilterItems = list;
        }
    }

    public List<ConsultTimeLineFilterItem> getFilterItems() {
        return this.mFilterItems;
    }

    public String getChild(int i, int i2) {
        if (this.mFilterItems.get(i) == null || this.mFilterItems.get(i).getItemType() != 3032 || this.mFilterItems.get(i).getChildFilters() == null || this.mFilterItems.get(i).getChildFilters().get(i2) == null) {
            return null;
        }
        return this.mFilterItems.get(i).getChildFilters().get(i2).getTitle();
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        ChildHolder childHolder;
        if (view == null) {
            childHolder = new ChildHolder();
            view = this.inflater.inflate(R.layout.dd_list_item, viewGroup, false);
            childHolder.title = (TextView) view.findViewById(R.id.textTitle);
            childHolder.hint = (TextView) view.findViewById(R.id.textHint);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) view.getTag();
        }
        childHolder.hint.setVisibility(8);
        String child = getChild(i, i2);
        if (child != null) {
            childHolder.title.setText(child);
        }
        return view;
    }

    public int getChildrenCount(int i) {
        if (this.mFilterItems.get(i) == null || this.mFilterItems.get(i).getItemType() != 3032) {
            return 0;
        }
        return this.mFilterItems.get(i).getChildFilters().size();
    }

    public ConsultTimeLineFilterItem getGroup(int i) {
        return this.mFilterItems.get(i);
    }

    public int getGroupCount() {
        return this.mFilterItems.size();
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        View view2;
        GroupHolder groupHolder;
        if (view == null) {
            groupHolder = new GroupHolder();
            if (getChildrenCount(i) > 0) {
                view2 = this.inflater.inflate(R.layout.dd_group_item, viewGroup, false);
            } else {
                view2 = this.inflater.inflate(R.layout.dd_group_no_children_item, viewGroup, false);
            }
            groupHolder.title = (TextView) view2.findViewById(R.id.textTitle);
            groupHolder.title.setTypeface((Typeface) null, 0);
            groupHolder.groupIndicator = (ImageView) view2.findViewById(R.id.group_indicator);
            view2.setTag(groupHolder);
        } else {
            view2 = view;
            groupHolder = (GroupHolder) view.getTag();
        }
        ConsultTimeLineFilterItem group = getGroup(i);
        if (group != null) {
            groupHolder.title.setText(group.getTitle());
            if (getChildrenCount(i) > 0) {
                groupHolder.groupIndicator.setVisibility(0);
            } else {
                groupHolder.groupIndicator.setVisibility(8);
            }
            if (z) {
                view2.setBackgroundResource(R.drawable.color_dddddd);
                groupHolder.groupIndicator.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
            } else {
                view2.setBackgroundResource(R.drawable.white_background_blue_ripple);
                groupHolder.groupIndicator.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            }
        }
        return view2;
    }

    private static class ChildHolder {
        TextView hint;
        TextView title;

        private ChildHolder() {
        }
    }

    private static class GroupHolder {
        ImageView groupIndicator;
        TextView title;

        private GroupHolder() {
        }
    }
}
