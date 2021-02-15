package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.text.Html;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage;
import com.wbmd.qxcalculator.model.contentItems.calculator.ChartLabel;
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ChartImageRowItem extends QxRecyclerViewRowItem {
    private ChartImage chartImage;
    private BitmapDrawable chartImageDrawable;
    private List<ChartImage> chartImages;
    private HashMap<ChartLabel, BitmapDrawable> chartLabelBitmapDrawableHashMap;
    private ContentItem contentItem;
    private boolean hasInitializedDrawables;
    public Result result;

    public ChartImageRowItem(ContentItem contentItem2, Result result2, final RecyclerView recyclerView) {
        this.result = result2;
        this.contentItem = contentItem2;
        List<ChartImage> arrayList = new ArrayList<>();
        JsonReader jsonReader = new JsonReader(new StringReader(result2.answerResult));
        jsonReader.setLenient(true);
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.nextName().equals("chart_options")) {
                    arrayList = ChartImage.convertJsonToChartImages(jsonReader);
                } else {
                    jsonReader.skipValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.chartImages = arrayList;
        if (recyclerView.getWidth() > 0) {
            findBestFitCharts(recyclerView.getContext(), recyclerView.getWidth());
        } else {
            recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ChartImageRowItem.this.findBestFitCharts(recyclerView.getContext(), recyclerView.getWidth());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void findBestFitCharts(Context context, int i) {
        ChartImage chartImage2 = null;
        View findViewById = LayoutInflater.from(context).inflate(getResourceId(), (ViewGroup) null).findViewById(R.id.chart_image_container);
        findViewById.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        int pxToDp = FileHelper.getInstance().pxToDp((findViewById.getMeasuredWidth() - findViewById.getPaddingStart()) - findViewById.getPaddingEnd());
        for (ChartImage next : this.chartImages) {
            if (chartImage2 != null) {
                double d = (double) pxToDp;
                if (Math.abs(next.preferredDipWidth.doubleValue() - d) >= Math.abs(chartImage2.preferredDipWidth.doubleValue() - d)) {
                }
            }
            chartImage2 = next;
        }
        this.chartImage = chartImage2;
        this.chartImageDrawable = FileHelper.getInstance().getScaledDrawable(this.contentItem, this.chartImage.imageName, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        this.chartLabelBitmapDrawableHashMap = new HashMap<>();
        for (ChartLabel next2 : this.chartImage.labels) {
            if (next2.imageName != null && !next2.imageName.isEmpty()) {
                this.chartLabelBitmapDrawableHashMap.put(next2, FileHelper.getInstance().getScaledDrawable(this.contentItem, next2.imageName, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
            }
        }
        this.hasInitializedDrawables = true;
    }

    public int getResourceId() {
        return R.layout.row_item_result_chart_image;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return ChartImageResultViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        View view;
        double d;
        int i2;
        FrameLayout frameLayout;
        int i3;
        int i4;
        long j;
        ChartImageRowItem chartImageRowItem = this;
        ChartImageResultViewHolder chartImageResultViewHolder = (ChartImageResultViewHolder) viewHolder;
        if (chartImageRowItem.hasInitializedDrawables && chartImageResultViewHolder.rowItem != chartImageRowItem) {
            chartImageResultViewHolder.rowItem = chartImageRowItem;
            FrameLayout frameLayout2 = chartImageResultViewHolder.chartLabelsContainerLayout;
            ImageView imageView = chartImageResultViewHolder.chartImageView;
            View view2 = chartImageResultViewHolder.container;
            Context context = frameLayout2.getContext();
            frameLayout2.removeAllViews();
            view2.measure(View.MeasureSpec.makeMeasureSpec(qxRecyclerViewAdapter.recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredWidth = (view2.getMeasuredWidth() - view2.getPaddingStart()) - view2.getPaddingEnd();
            BitmapDrawable bitmapDrawable = chartImageRowItem.chartImageDrawable;
            if (bitmapDrawable != null) {
                imageView.setImageDrawable(bitmapDrawable);
                if (chartImageRowItem.chartImage.title == null || chartImageRowItem.chartImage.title.isEmpty()) {
                    chartImageResultViewHolder.titleTextView.setVisibility(8);
                } else {
                    chartImageResultViewHolder.titleTextView.setVisibility(0);
                    chartImageResultViewHolder.titleTextView.setText(Html.fromHtml(chartImageRowItem.chartImage.title));
                }
                if (chartImageRowItem.chartImage.footer == null || chartImageRowItem.chartImage.footer.isEmpty()) {
                    chartImageResultViewHolder.footerTextView.setVisibility(8);
                } else {
                    chartImageResultViewHolder.footerTextView.setVisibility(0);
                    chartImageResultViewHolder.footerTextView.setText(Html.fromHtml(chartImageRowItem.chartImage.footer));
                }
                double width = ((double) measuredWidth) / ((double) chartImageRowItem.chartImageDrawable.getBitmap().getWidth());
                double d2 = (double) Resources.getSystem().getDisplayMetrics().density;
                double doubleValue = chartImageRowItem.chartImage.originLocationX.doubleValue() * d2;
                double doubleValue2 = chartImageRowItem.chartImage.originLocationY.doubleValue() * d2;
                double doubleValue3 = chartImageRowItem.chartImage.maxLocationX.doubleValue() * d2;
                double doubleValue4 = chartImageRowItem.chartImage.maxLocationY.doubleValue() * d2;
                Iterator<ChartLabel> it = chartImageRowItem.chartImage.labels.iterator();
                double d3 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                double d4 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                double d5 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                double d6 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                double d7 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                double d8 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                while (it.hasNext()) {
                    ChartLabel next = it.next();
                    if (next.getTitlePosition().equals(ChartLabel.LabelTitlePosition.TOP)) {
                        view = LayoutInflater.from(context).inflate(R.layout.view_chart_label_top, (ViewGroup) null);
                    } else {
                        view = LayoutInflater.from(context).inflate(R.layout.view_chart_label_bot, (ViewGroup) null);
                    }
                    ImageView imageView2 = (ImageView) view.findViewById(R.id.chart_label_image);
                    TextView textView = (TextView) view.findViewById(R.id.chart_label_title);
                    int i5 = AnonymousClass2.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$ChartImage$AxisScaleType[chartImageRowItem.chartImage.getAxisXScaleType().ordinal()];
                    Iterator<ChartLabel> it2 = it;
                    if (i5 == 1) {
                        d = (doubleValue3 - doubleValue) / (chartImageRowItem.chartImage.maxValueX.doubleValue() - chartImageRowItem.chartImage.originValueX.doubleValue());
                        d8 = next.coordX.doubleValue();
                        d4 = chartImageRowItem.chartImage.originValueX.doubleValue();
                    } else if (i5 == 2) {
                        d = (doubleValue3 - doubleValue) / (Math.log10(chartImageRowItem.chartImage.maxValueX.doubleValue()) - Math.log10(chartImageRowItem.chartImage.originValueX.doubleValue()));
                        d8 = Math.log10(next.coordX.doubleValue());
                        if (chartImageRowItem.chartImage.originValueX.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            d4 = Math.log10(chartImageRowItem.chartImage.originValueX.doubleValue());
                        }
                    } else if (i5 != 3) {
                        d = d6;
                    } else {
                        d = (doubleValue3 - doubleValue) / (Math.log(chartImageRowItem.chartImage.maxValueX.doubleValue()) - Math.log(chartImageRowItem.chartImage.originValueX.doubleValue()));
                        d8 = Math.log(next.coordX.doubleValue());
                        if (chartImageRowItem.chartImage.originValueX.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            d4 = Math.log(chartImageRowItem.chartImage.originValueX.doubleValue());
                        }
                    }
                    Context context2 = context;
                    int i6 = AnonymousClass2.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$ChartImage$AxisScaleType[chartImageRowItem.chartImage.getAxisYScaleType().ordinal()];
                    if (i6 == 1) {
                        d7 = (doubleValue2 - doubleValue4) / (chartImageRowItem.chartImage.maxValueY.doubleValue() - chartImageRowItem.chartImage.originValueY.doubleValue());
                        d3 = next.coordY.doubleValue();
                        d5 = chartImageRowItem.chartImage.originValueY.doubleValue();
                    } else if (i6 == 2) {
                        d7 = (doubleValue2 - doubleValue4) / (Math.log10(chartImageRowItem.chartImage.maxValueY.doubleValue()) - Math.log10(chartImageRowItem.chartImage.originValueY.doubleValue()));
                        if (next.coordY.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            d3 = Math.log10(next.coordY.doubleValue());
                        }
                        if (chartImageRowItem.chartImage.originValueY.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            d5 = Math.log10(chartImageRowItem.chartImage.originValueY.doubleValue());
                        }
                    } else if (i6 == 3) {
                        d7 = (doubleValue2 - doubleValue4) / (Math.log(chartImageRowItem.chartImage.maxValueY.doubleValue()) - Math.log(chartImageRowItem.chartImage.originValueY.doubleValue()));
                        if (next.coordY.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            d3 = Math.log(next.coordY.doubleValue());
                        }
                        if (chartImageRowItem.chartImage.originValueY.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            d5 = Math.log(chartImageRowItem.chartImage.originValueY.doubleValue());
                        }
                    }
                    if (next.title == null || next.title.isEmpty()) {
                        i2 = 8;
                        textView.setVisibility(8);
                    } else {
                        textView.setVisibility(0);
                        textView.setText(Html.fromHtml(next.title));
                        i2 = 8;
                    }
                    if (next.imageName == null || next.imageName.isEmpty()) {
                        frameLayout = frameLayout2;
                        imageView2.setVisibility(8);
                        i3 = 0;
                    } else {
                        BitmapDrawable bitmapDrawable2 = chartImageRowItem.chartLabelBitmapDrawableHashMap.get(next);
                        if (bitmapDrawable2 == null) {
                            imageView2.setVisibility(i2);
                            frameLayout = frameLayout2;
                            i3 = 0;
                        } else {
                            imageView2.setVisibility(0);
                            imageView2.setImageDrawable(bitmapDrawable2);
                            frameLayout = frameLayout2;
                            i3 = (int) Math.floor(((double) bitmapDrawable2.getBitmap().getHeight()) * width);
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView2.getLayoutParams();
                            layoutParams.width = (int) Math.floor(((double) bitmapDrawable2.getBitmap().getWidth()) * width);
                            layoutParams.height = i3;
                            imageView2.setLayoutParams(layoutParams);
                        }
                    }
                    view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                    ChartLabel chartLabel = next;
                    int round = ((int) Math.round((((d8 - d4) * d) + doubleValue) * width)) - ((int) Math.round(((double) view.getMeasuredWidth()) / 2.0d));
                    int round2 = (int) Math.round((doubleValue2 - ((d3 - d5) * d7)) * width);
                    if (i3 == 0) {
                        j = Math.round(((double) view.getMeasuredHeight()) / 2.0d);
                    } else if (chartLabel.getTitlePosition().equals(ChartLabel.LabelTitlePosition.BOTTOM)) {
                        j = Math.round(((double) i3) / 2.0d);
                    } else {
                        i4 = (round2 - view.getMeasuredHeight()) + ((int) Math.round(((double) i3) / 2.0d));
                        frameLayout.addView(view, new FrameLayout.LayoutParams(-2, -2));
                        view.setX((float) round);
                        view.setY((float) i4);
                        chartImageRowItem = this;
                        frameLayout2 = frameLayout;
                        context = context2;
                        it = it2;
                        d6 = d;
                    }
                    i4 = round2 - ((int) j);
                    frameLayout.addView(view, new FrameLayout.LayoutParams(-2, -2));
                    view.setX((float) round);
                    view.setY((float) i4);
                    chartImageRowItem = this;
                    frameLayout2 = frameLayout;
                    context = context2;
                    it = it2;
                    d6 = d;
                }
            }
        }
    }

    /* renamed from: com.wbmd.qxcalculator.model.rowItems.calculator.ChartImageRowItem$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$ChartImage$AxisScaleType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage$AxisScaleType[] r0 = com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage.AxisScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$ChartImage$AxisScaleType = r0
                com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage$AxisScaleType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage.AxisScaleType.LINEAR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$ChartImage$AxisScaleType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage$AxisScaleType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage.AxisScaleType.LOG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$ChartImage$AxisScaleType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage$AxisScaleType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.ChartImage.AxisScaleType.LN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.rowItems.calculator.ChartImageRowItem.AnonymousClass2.<clinit>():void");
        }
    }

    public static final class ChartImageResultViewHolder extends QxRecyclerRowItemViewHolder {
        ImageView chartImageView;
        FrameLayout chartLabelsContainerLayout;
        View container;
        TextView footerTextView;
        ChartImageRowItem rowItem;
        TextView titleTextView;

        public ChartImageResultViewHolder(View view) {
            super(view);
            this.container = view.findViewById(R.id.chart_image_container);
            this.chartLabelsContainerLayout = (FrameLayout) view.findViewById(R.id.chart_labels_container);
            this.chartImageView = (ImageView) view.findViewById(R.id.chart_image_view);
            this.titleTextView = (TextView) view.findViewById(R.id.label_title);
            this.footerTextView = (TextView) view.findViewById(R.id.label_footer);
        }
    }
}
