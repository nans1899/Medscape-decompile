package com.wbmd.decisionpoint;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wbmd.decisionpoint.databinding.ActivityHubBindingImpl;
import com.wbmd.decisionpoint.databinding.ActivityHubWebViewBindingImpl;
import com.wbmd.decisionpoint.databinding.FragmentHubBindingImpl;
import com.wbmd.decisionpoint.databinding.RowAnswersBindingImpl;
import com.wbmd.decisionpoint.databinding.RowContributorItemBindingImpl;
import com.wbmd.decisionpoint.databinding.RowContributorTypeBindingImpl;
import com.wbmd.decisionpoint.databinding.RowDecisionPointBindingImpl;
import com.wbmd.decisionpoint.databinding.RowExpertsAnswersBindingImpl;
import com.wbmd.decisionpoint.databinding.RowHeaderBindingImpl;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYHUB = 1;
    private static final int LAYOUT_ACTIVITYHUBWEBVIEW = 2;
    private static final int LAYOUT_FRAGMENTHUB = 3;
    private static final int LAYOUT_ROWANSWERS = 4;
    private static final int LAYOUT_ROWCONTRIBUTORITEM = 5;
    private static final int LAYOUT_ROWCONTRIBUTORTYPE = 6;
    private static final int LAYOUT_ROWDECISIONPOINT = 7;
    private static final int LAYOUT_ROWEXPERTSANSWERS = 8;
    private static final int LAYOUT_ROWHEADER = 9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(9);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_hub, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_hub_web_view, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_hub, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.row_answers, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.row_contributor_item, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.row_contributor_type, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.row_decision_point, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.row_experts_answers, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.row_header, 9);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/activity_hub_0".equals(tag)) {
                        return new ActivityHubBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_hub is invalid. Received: " + tag);
                case 2:
                    if ("layout/activity_hub_web_view_0".equals(tag)) {
                        return new ActivityHubWebViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_hub_web_view is invalid. Received: " + tag);
                case 3:
                    if ("layout/fragment_hub_0".equals(tag)) {
                        return new FragmentHubBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_hub is invalid. Received: " + tag);
                case 4:
                    if ("layout/row_answers_0".equals(tag)) {
                        return new RowAnswersBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for row_answers is invalid. Received: " + tag);
                case 5:
                    if ("layout/row_contributor_item_0".equals(tag)) {
                        return new RowContributorItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for row_contributor_item is invalid. Received: " + tag);
                case 6:
                    if ("layout/row_contributor_type_0".equals(tag)) {
                        return new RowContributorTypeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for row_contributor_type is invalid. Received: " + tag);
                case 7:
                    if ("layout/row_decision_point_0".equals(tag)) {
                        return new RowDecisionPointBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for row_decision_point is invalid. Received: " + tag);
                case 8:
                    if ("layout/row_experts_answers_0".equals(tag)) {
                        return new RowExpertsAnswersBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for row_experts_answers is invalid. Received: " + tag);
                case 9:
                    if ("layout/row_header_0".equals(tag)) {
                        return new RowHeaderBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for row_header is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(6);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sKeys.put(1, "contributor");
            sKeys.put(2, ContentParser.ITEM);
            sKeys.put(3, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            sKeys.put(4, "title");
            sKeys.put(5, "viewmodel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(9);
            sKeys = hashMap;
            hashMap.put("layout/activity_hub_0", Integer.valueOf(R.layout.activity_hub));
            sKeys.put("layout/activity_hub_web_view_0", Integer.valueOf(R.layout.activity_hub_web_view));
            sKeys.put("layout/fragment_hub_0", Integer.valueOf(R.layout.fragment_hub));
            sKeys.put("layout/row_answers_0", Integer.valueOf(R.layout.row_answers));
            sKeys.put("layout/row_contributor_item_0", Integer.valueOf(R.layout.row_contributor_item));
            sKeys.put("layout/row_contributor_type_0", Integer.valueOf(R.layout.row_contributor_type));
            sKeys.put("layout/row_decision_point_0", Integer.valueOf(R.layout.row_decision_point));
            sKeys.put("layout/row_experts_answers_0", Integer.valueOf(R.layout.row_experts_answers));
            sKeys.put("layout/row_header_0", Integer.valueOf(R.layout.row_header));
        }
    }
}
