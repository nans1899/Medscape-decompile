package com.wbmd.qxcalculator.activities.contentItems;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItemWrapper;
import com.wbmd.omniture.utils.OmnitureHelper;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSection;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.rowItems.headers.DefaultHeaderNoTitleRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.DefaultHeaderRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.InvisibleHeaderRowItem;
import com.wbmd.qxcalculator.model.rowItems.referencebook.ReferenceBookGroupRowItem;
import com.wbmd.qxcalculator.model.rowItems.referencebook.ReferenceBookItemRowItem;
import com.wbmd.qxcalculator.model.rowItems.referencebook.ReferenceBookSectionFooterRowItem;
import com.wbmd.qxcalculator.model.rowItems.referencebook.ReferenceBookTopLevelItemRowItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.ColorHelper;
import com.wbmd.qxcalculator.views.ArrowView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReferenceBookActivity extends ContentItemActivity implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener, QxRecyclerViewAdapter.OnRecyclerViewRowItemExpandCollapseListener {
    private static final String KEY_EXPANDED_IDS = "KEY_EXPANDED_IDS";
    private static final String KEY_HAS_SHOWN_SPLASH = "ReferenceBookActivity.KEY_HAS_SHOWN_SPLASH";
    public static final String KEY_REFERENCE_BOOK = "ReferenceBookActivity.KEY_REFERENCE_BOOK";
    public static final String KEY_REFERENCE_BOOK_SECTION_ITEM = "ReferenceBookActivity.KEY_REFERENCE_BOOK_SECTION_ITEM";
    private QxRecyclerViewAdapter adapter;
    private boolean hasHiddenSplashPage;
    private boolean hasShownSplashPage;
    private QxRecyclerView listView;
    /* access modifiers changed from: private */
    public ImageView splashImageView;
    private int titlePageBackgroundColor = 0;

    public int getStatusBarColor() {
        return getResources().getColor(R.color.status_bar_color_default);
    }

    public int getStatusBarColorForSplashPage() {
        if (this.titlePageBackgroundColor == 0) {
            return getStatusBarColor();
        }
        return ColorHelper.getInstance().addColors(this.titlePageBackgroundColor, Color.parseColor("#26000000"));
    }

    public int getActionBarColor() {
        return getResources().getColor(R.color.action_bar_color_default);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_reference_book;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.contentItem != null || !requiresContentItem()) {
            if (bundle != null) {
                this.hasShownSplashPage = bundle.getBoolean(KEY_HAS_SHOWN_SPLASH, false);
            }
            if (this.adapter == null) {
                QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
                this.adapter = qxRecyclerViewAdapter;
                qxRecyclerViewAdapter.setHasStableIds(false);
                this.adapter.setOnClickListener(this);
                this.adapter.setOnExpandCollapseListener(this);
            }
            QxRecyclerView qxRecyclerView = (QxRecyclerView) findViewById(R.id.recycler_view);
            this.listView = qxRecyclerView;
            qxRecyclerView.setAdapter(this.adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(1);
            this.listView.setLayoutManager(linearLayoutManager);
            if (bundle != null) {
                rebuildList(bundle.getStringArrayList(KEY_EXPANDED_IDS));
            }
            final ReferenceBook referenceBook = this.contentItem.referenceBook;
            this.splashImageView = (ImageView) findViewById(R.id.splash_image_view);
            if (this.hasShownSplashPage || referenceBook.titlePageSource == null || referenceBook.titlePageSource.isEmpty()) {
                this.splashImageView.setVisibility(8);
                return;
            }
            this.hasShownSplashPage = true;
            if (referenceBook.titlePageBackgroundColor != null && !referenceBook.titlePageBackgroundColor.isEmpty()) {
                try {
                    int parseColor = Color.parseColor(referenceBook.titlePageBackgroundColor);
                    this.titlePageBackgroundColor = parseColor;
                    this.splashImageView.setBackgroundColor(parseColor);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            this.splashImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    Log.d("API", "onGlobalLayout");
                    ReferenceBookActivity.this.splashImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    BitmapDrawable scaledDrawable = FileHelper.getInstance().getScaledDrawable(ReferenceBookActivity.this.contentItem, referenceBook.titlePageSource, ((double) ReferenceBookActivity.this.splashImageView.getMeasuredWidth()) / ((double) ReferenceBookActivity.this.splashImageView.getMeasuredHeight()));
                    if (scaledDrawable != null) {
                        ReferenceBookActivity referenceBookActivity = ReferenceBookActivity.this;
                        referenceBookActivity.setBarColors(referenceBookActivity.getStatusBarColorForSplashPage(), ReferenceBookActivity.this.getActionBarColor());
                        ReferenceBookActivity.this.splashImageView.setVisibility(0);
                        ReferenceBookActivity.this.splashImageView.setImageDrawable(scaledDrawable);
                        ReferenceBookActivity.this.splashImageView.postDelayed(new Runnable() {
                            public void run() {
                                ReferenceBookActivity.this.hideSplashscreen();
                            }
                        }, 1500);
                        ReferenceBookActivity.this.splashImageView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                ReferenceBookActivity.this.hideSplashscreen();
                            }
                        });
                    }
                }
            });
            return;
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void hideSplashscreen() {
        if (!this.hasHiddenSplashPage) {
            setBarColors(getStatusBarColor(), getActionBarColor());
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.splashImageView, "alpha", new float[]{1.0f, 0.0f});
            ImageView imageView = this.splashImageView;
            animatorSet.playTogether(new Animator[]{ofFloat, ObjectAnimator.ofFloat(imageView, "translationY", new float[]{0.0f, (float) (-imageView.getHeight())})});
            animatorSet.setDuration(400);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ReferenceBookActivity.this.splashImageView.setVisibility(8);
                }
            });
            animatorSet.start();
            this.hasHiddenSplashPage = true;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ArrayList arrayList = new ArrayList();
        for (QxRecyclerViewRowItem next : this.adapter.getRowItems()) {
            if (next.isExpanded) {
                arrayList.add(next.getId());
            }
        }
        bundle.putStringArrayList(KEY_EXPANDED_IDS, arrayList);
        bundle.putBoolean(KEY_HAS_SHOWN_SPLASH, this.hasShownSplashPage);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onResume() {
        super.onResume();
        Log.d("API", "CalculatorActivity onResume");
        AnalyticsHandler.getInstance().trackPageView(this, "referenceBookTOC");
        if (!this.adapter.getHasBeenInitialized()) {
            rebuildList((List<String>) null);
        }
        if (this.contentItem != null && this.contentItem.footer != null && this.contentItem.footer.equalsIgnoreCase(getString(R.string.sponsored)) && this.contentItem.identifier.contains(getString(R.string.reference_book))) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getString(R.string.guide));
            arrayList.add(getString(R.string.sponsored));
            arrayList.add(OmnitureHelper.INSTANCE.getCalcPageName(this.contentItem.name));
            OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList);
        }
    }

    private void rebuildList(List<String> list) {
        QxRecyclerViewRowItem qxRecyclerViewRowItem;
        this.adapter.reset();
        List<List<QxRecyclerViewRowItem>> buildRowItems = buildRowItems();
        ReferenceBook referenceBook = this.contentItem.referenceBook;
        int i = 0;
        while (i < referenceBook.sections.size()) {
            ReferenceBookSection referenceBookSection = referenceBook.sections.get(i);
            String str = referenceBookSection.title;
            if (str == null || str.isEmpty()) {
                qxRecyclerViewRowItem = i == 0 ? new InvisibleHeaderRowItem() : new DefaultHeaderNoTitleRowItem();
            } else {
                qxRecyclerViewRowItem = new DefaultHeaderRowItem(str, i == 0);
            }
            if (referenceBookSection.footer != null && !referenceBookSection.footer.isEmpty()) {
                buildRowItems.get(i).add(new ReferenceBookSectionFooterRowItem(referenceBookSection));
            }
            this.adapter.addSectionWithHeaderItem(qxRecyclerViewRowItem, buildRowItems.get(i));
            i++;
        }
        this.adapter.expandChildrenContainedInExpandedIds((List<QxRecyclerViewRowItemWrapper>) null, list);
        this.adapter.notifyDataSetChanged();
    }

    private List<List<QxRecyclerViewRowItem>> buildRowItems() {
        ReferenceBook referenceBook = this.contentItem.referenceBook;
        boolean z = false;
        for (ReferenceBookSection referenceBookSection : referenceBook.sections) {
            Iterator<ReferenceBookSectionItem> it = referenceBookSection.sectionItems.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ReferenceBookSectionItem next = it.next();
                if (next.subSectionItems != null && !next.subSectionItems.isEmpty()) {
                    z = true;
                    continue;
                    break;
                }
            }
            if (z) {
                break;
            }
        }
        ArrayList arrayList = new ArrayList(referenceBook.sections.size());
        for (ReferenceBookSection next2 : referenceBook.sections) {
            ArrayList arrayList2 = new ArrayList(next2.sectionItems.size());
            for (ReferenceBookSectionItem next3 : next2.sectionItems) {
                if (next3.subSectionItems != null && !next3.subSectionItems.isEmpty()) {
                    ReferenceBookGroupRowItem referenceBookGroupRowItem = new ReferenceBookGroupRowItem(next3, this);
                    arrayList2.add(referenceBookGroupRowItem);
                    buildChildrenRowItems(referenceBookGroupRowItem, next3);
                } else if (z) {
                    arrayList2.add(new ReferenceBookTopLevelItemRowItem(next3, false, this));
                } else {
                    arrayList2.add(new ReferenceBookItemRowItem(next3, this));
                }
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private void buildChildrenRowItems(QxRecyclerViewRowItem qxRecyclerViewRowItem, ReferenceBookSectionItem referenceBookSectionItem) {
        for (ReferenceBookSectionItem next : referenceBookSectionItem.subSectionItems) {
            if (next.subSectionItems == null || next.subSectionItems.isEmpty()) {
                qxRecyclerViewRowItem.addChild(new ReferenceBookItemRowItem(next, this));
            } else {
                ReferenceBookGroupRowItem referenceBookGroupRowItem = new ReferenceBookGroupRowItem(next, this);
                qxRecyclerViewRowItem.addChild(referenceBookGroupRowItem);
                buildChildrenRowItems(referenceBookGroupRowItem, next);
            }
        }
    }

    public void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ReferenceBookSectionItem referenceBookSectionItem;
        if (qxRecyclerViewRowItem instanceof ReferenceBookItemRowItem) {
            referenceBookSectionItem = ((ReferenceBookItemRowItem) qxRecyclerViewRowItem).sectionItem;
        } else {
            referenceBookSectionItem = qxRecyclerViewRowItem instanceof ReferenceBookTopLevelItemRowItem ? ((ReferenceBookTopLevelItemRowItem) qxRecyclerViewRowItem).sectionItem : null;
        }
        if (referenceBookSectionItem == null) {
            return;
        }
        if (referenceBookSectionItem.sourceId == null || referenceBookSectionItem.sourceId.isEmpty()) {
            openReferenceBookItem(referenceBookSectionItem);
            return;
        }
        DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(referenceBookSectionItem.sourceId);
        Intent intent = new Intent();
        intent.putExtra(CalculatorActivity.KEY_EXTRA_CALC_FROM_SECTION, getString(R.string.search_firebase_action));
        ContentItemLaunchManager.getInstance().launchContentItem(contentItemForIdentifier, (FragmentActivity) this, intent);
    }

    public void onRecyclerViewRowItemExpanded(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ArrowView arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
        if (arrowView != null) {
            arrowView.expandArrow(true, true);
        }
    }

    public void onRecyclerViewRowItemCollapsed(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ArrowView arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
        if (arrowView != null) {
            arrowView.expandArrow(false, true);
        }
    }

    private void openReferenceBookItem(ReferenceBookSectionItem referenceBookSectionItem) {
        Intent intent = new Intent(this, ReferenceBookDetailActivity.class);
        intent.putExtra(KEY_REFERENCE_BOOK_SECTION_ITEM, referenceBookSectionItem);
        intent.putExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM, this.contentItem);
        startActivity(intent);
    }
}
