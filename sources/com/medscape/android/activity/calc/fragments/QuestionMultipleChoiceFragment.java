package com.medscape.android.activity.calc.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.activity.calc.QuestionPagerActivity;
import com.medscape.android.activity.calc.UtilCalc;
import com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter;
import com.medscape.android.activity.calc.ads.InlineAdLoaded;
import com.medscape.android.activity.calc.managers.ContentItemLaunchManager;
import com.medscape.android.ads.InlineAdTouchHelper;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.wbmd.adlibrary.utilities.AdScrollHandler;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.fragments.calculator.QuestionPagerFragment;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.model.contentItems.calculator.LinkedCalculatorItem;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.wbmdcommons.callbacks.IScrollEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J&\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0006H\u0016J\b\u0010\u001b\u001a\u00020\u0006H\u0016J\b\u0010\u001c\u001a\u00020\u0006H\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0016J\b\u0010\u001e\u001a\u00020\u0006H\u0016¨\u0006\u001f"}, d2 = {"Lcom/medscape/android/activity/calc/fragments/QuestionMultipleChoiceFragment;", "Lcom/wbmd/qxcalculator/fragments/calculator/QuestionMultipleChoiceFragment;", "Lcom/wbmd/wbmdcommons/callbacks/IScrollEvent;", "Lcom/medscape/android/activity/calc/ads/InlineAdLoaded;", "()V", "launchLinkedContentItem", "", "dbContentItem", "Lcom/wbmd/qxcalculator/model/db/DBContentItem;", "linkedCalculatorItemIndex", "", "linkedCalculatorRowTapped", "fromView", "Landroid/view/View;", "newInstance", "question", "Lcom/wbmd/qxcalculator/model/contentItems/calculator/Question;", "contentItem", "Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onInlineAdLoaded", "onPreCacheEvent", "onScrollThresholdReached", "setupAdapter", "setupList", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuestionMultipleChoiceFragment.kt */
public final class QuestionMultipleChoiceFragment extends com.wbmd.qxcalculator.fragments.calculator.QuestionMultipleChoiceFragment implements IScrollEvent, InlineAdLoaded {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onPreCacheEvent() {
    }

    public final QuestionMultipleChoiceFragment newInstance(Question question, ContentItem contentItem) {
        Intrinsics.checkNotNullParameter(question, "question");
        Intrinsics.checkNotNullParameter(contentItem, "contentItem");
        QuestionMultipleChoiceFragment questionMultipleChoiceFragment = new QuestionMultipleChoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("QuestionFragment.KEY_ARG_QUESTION", question);
        bundle.putParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM", contentItem);
        questionMultipleChoiceFragment.setArguments(bundle);
        return questionMultipleChoiceFragment;
    }

    public void setupAdapter() {
        QxRecyclerViewAdapter qxRecyclerViewAdapter;
        if (this.adapter == null) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                Intrinsics.checkNotNullExpressionValue(activity, "it");
                ContentItem contentItem = this.contentItem;
                Intrinsics.checkNotNullExpressionValue(contentItem, "contentItem");
                qxRecyclerViewAdapter = new QxRecyclerViewAdapter(activity, contentItem, this);
            } else {
                qxRecyclerViewAdapter = null;
            }
            this.adapter = qxRecyclerViewAdapter;
            this.adapter.setOnClickListener(this);
            this.adapter.shouldDelaySelection = false;
        }
    }

    public void setupList() {
        QxRecyclerView qxRecyclerView = this.listView;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerView, "listView");
        qxRecyclerView.setAdapter(this.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        QxRecyclerView qxRecyclerView2 = this.listView;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerView2, "listView");
        qxRecyclerView2.setLayoutManager(linearLayoutManager);
        new AdScrollHandler(this.listView, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, this);
        InlineAdTouchHelper inlineAdTouchHelper = new InlineAdTouchHelper();
        QxRecyclerView qxRecyclerView3 = this.listView;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerView3, "listView");
        InlineAdTouchHelper.applyTouchListener$default(inlineAdTouchHelper, qxRecyclerView3, false, 2, (Object) null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        if (this.adapter != null) {
            com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
            if (qxRecyclerViewAdapter != null) {
                ((QxRecyclerViewAdapter) qxRecyclerViewAdapter).resetAdHolder();
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter");
            }
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    /* access modifiers changed from: protected */
    public void linkedCalculatorRowTapped(View view) {
        Intrinsics.checkNotNullParameter(view, "fromView");
        if (this.question.linkedCalculatorItems != null && !this.question.linkedCalculatorItems.isEmpty()) {
            if (this.question.linkedCalculatorItems.size() == 1) {
                ContentDataManager instance = ContentDataManager.getInstance();
                DBContentItem contentItemForIdentifier = instance.getContentItemForIdentifier("calculator_" + this.question.linkedCalculatorItems.get(0).calculatorIdentifier);
                Intrinsics.checkNotNullExpressionValue(contentItemForIdentifier, "dbContentItem");
                launchLinkedContentItem(contentItemForIdentifier, 0);
                return;
            }
            FragmentActivity activity = getActivity();
            this.linkedCalculatorPopupMenu = activity != null ? new PopupMenu(activity, view) : null;
            List arrayList = new ArrayList(this.question.linkedCalculatorItems.size());
            for (LinkedCalculatorItem linkedCalculatorItem : this.question.linkedCalculatorItems) {
                String str = linkedCalculatorItem.calculatorIdentifier;
                Intrinsics.checkNotNullExpressionValue(str, "calculatorItem.calculatorIdentifier");
                arrayList.add(str);
            }
            List<DBContentItem> contentItemsForIdentifiers = ContentDataManager.getInstance().getContentItemsForIdentifiers(arrayList);
            Intrinsics.checkNotNullExpressionValue(contentItemsForIdentifiers, "contentItems");
            int size = contentItemsForIdentifiers.size();
            for (int i = 0; i < size; i++) {
                PopupMenu popupMenu = this.linkedCalculatorPopupMenu;
                Intrinsics.checkNotNullExpressionValue(popupMenu, "linkedCalculatorPopupMenu");
                Menu menu = popupMenu.getMenu();
                DBContentItem dBContentItem = contentItemsForIdentifiers.get(i);
                Intrinsics.checkNotNullExpressionValue(dBContentItem, "contentItems[i]");
                menu.add(0, i, i, dBContentItem.getName());
            }
            this.linkedCalculatorPopupMenu.setOnMenuItemClickListener(new QuestionMultipleChoiceFragment$linkedCalculatorRowTapped$2(this, contentItemsForIdentifiers));
        }
    }

    /* access modifiers changed from: private */
    public final void launchLinkedContentItem(DBContentItem dBContentItem, int i) {
        if (dBContentItem != null) {
            QuestionPagerFragment questionPagerFragment = (QuestionPagerFragment) getParentFragment();
            if (questionPagerFragment != null) {
                questionPagerFragment.notifyLeavingCalculator();
            }
            Intent intent = new Intent();
            intent.putExtra(CalculatorActivity.KEY_EXTRA_IS_SUB_CALC, true);
            intent.putExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION, this.question);
            intent.putExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION_LINKED_CALC_INDEX, i);
            new ContentItemLaunchManager().launchContentItem(dBContentItem, getActivity(), intent, 1, true);
        }
    }

    public void onScrollThresholdReached() {
        if (getActivity() instanceof QuestionPagerActivity) {
            UtilCalc utilCalc = new UtilCalc();
            com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
            Intrinsics.checkNotNullExpressionValue(qxRecyclerViewAdapter, "adapter");
            RecyclerView.Adapter adapter = qxRecyclerViewAdapter;
            QxRecyclerView qxRecyclerView = this.listView;
            Intrinsics.checkNotNullExpressionValue(qxRecyclerView, "listView");
            RecyclerView recyclerView = qxRecyclerView;
            FragmentActivity activity = getActivity();
            if (activity != null) {
                View adLayout = ((QuestionPagerActivity) activity).getAdLayout();
                com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter2 = this.adapter;
                if (qxRecyclerViewAdapter2 != null) {
                    utilCalc.setBannerAdVisibility(adapter, recyclerView, adLayout, ((QxRecyclerViewAdapter) qxRecyclerViewAdapter2).isInlineADcallComplete());
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter");
            }
            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.QuestionPagerActivity");
        }
    }

    public void onInlineAdLoaded() {
        if (getActivity() instanceof QuestionPagerActivity) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                QuestionPagerActivity questionPagerActivity = (QuestionPagerActivity) activity;
                com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
                if (qxRecyclerViewAdapter != null) {
                    QxRecyclerViewAdapter qxRecyclerViewAdapter2 = (QxRecyclerViewAdapter) qxRecyclerViewAdapter;
                    QxRecyclerView qxRecyclerView = this.listView;
                    Intrinsics.checkNotNullExpressionValue(qxRecyclerView, "listView");
                    RecyclerView recyclerView = qxRecyclerView;
                    com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter3 = this.adapter;
                    if (qxRecyclerViewAdapter3 != null) {
                        questionPagerActivity.setListValuesForVisibilityCheck(qxRecyclerViewAdapter2, recyclerView, ((QxRecyclerViewAdapter) qxRecyclerViewAdapter3).isInlineADcallComplete());
                        UtilCalc utilCalc = new UtilCalc();
                        com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter4 = this.adapter;
                        Intrinsics.checkNotNullExpressionValue(qxRecyclerViewAdapter4, "adapter");
                        RecyclerView.Adapter adapter = qxRecyclerViewAdapter4;
                        QxRecyclerView qxRecyclerView2 = this.listView;
                        Intrinsics.checkNotNullExpressionValue(qxRecyclerView2, "listView");
                        RecyclerView recyclerView2 = qxRecyclerView2;
                        FragmentActivity activity2 = getActivity();
                        if (activity2 != null) {
                            View adLayout = ((QuestionPagerActivity) activity2).getAdLayout();
                            com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter5 = this.adapter;
                            if (qxRecyclerViewAdapter5 != null) {
                                utilCalc.setBannerAdVisibility(adapter, recyclerView2, adLayout, ((QxRecyclerViewAdapter) qxRecyclerViewAdapter5).isInlineADcallComplete());
                                return;
                            }
                            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter");
                        }
                        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.QuestionPagerActivity");
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter");
                }
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter");
            }
            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.QuestionPagerActivity");
        }
    }
}
