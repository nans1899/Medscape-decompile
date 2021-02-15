package com.medscape.android.reference;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdSize;
import com.medscape.android.R;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.ads.ShareThroughNativeADViewHolder;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.contentviewer.CaptionRowLineItem;
import com.medscape.android.contentviewer.ContentSectionDataAdapter;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.FigureLineItem;
import com.medscape.android.contentviewer.InlineAdLineItem;
import com.medscape.android.contentviewer.LineItem;
import com.medscape.android.contentviewer.NextSectionLineItem;
import com.medscape.android.contentviewer.TableRowLineItem;
import com.medscape.android.contentviewer.model.ReferenceFindPosition;
import com.medscape.android.contentviewer.model.SharethroughInlineAd;
import com.medscape.android.contentviewer.model.TableRowDataViewHolder;
import com.medscape.android.contentviewer.view_holders.CaptionViewHolder;
import com.medscape.android.contentviewer.view_holders.ContentHeaderViewHolder;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.contentviewer.view_holders.FigureDataViewHolder;
import com.medscape.android.contentviewer.view_holders.NextSectionViewHolder;
import com.medscape.android.contentviewer.view_holders.PreCachedNativeDfpInlineAdViewHolder;
import com.medscape.android.reference.interfaces.EmbeddableNewlineSpan;
import com.medscape.android.reference.model.ClinicalReferenceContent;
import com.medscape.android.reference.model.Figure;
import com.medscape.android.reference.model.Para;
import com.medscape.android.reference.model.Table;
import com.medscape.android.reference.style.NativeArticleFigureSpan;
import com.medscape.android.reference.style.NativeArticleListSpan;
import com.medscape.android.reference.style.NativeArticleSideHeaderSpan;
import com.medscape.android.reference.style.NativeArticleTableSpan;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSLM;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ClinicalReferenceArticleContentDataAdapter extends ContentSectionDataAdapter implements FigureDataViewHolder.DataUpdatedListener {
    private static final int VIEW_TYPE_AD = 5;
    private static final int VIEW_TYPE_CAPTION = 9;
    private static final int VIEW_TYPE_CONTRIBUTOR = 16;
    private static final int VIEW_TYPE_FIGURE = 4;
    private static final int VIEW_TYPE_NEXT_SECTION = 6;
    private static final int VIEW_TYPE_POINTS_LIST = 7;
    private static final int VIEW_TYPE_SHARETHROUGH_AD = 17;
    private static final int VIEW_TYPE_SIDE_HEADER = 8;
    private static final int VIEW_TYPE_TABLE_ROW = 3;
    private int SECTION_FIRST_POSITION = 0;
    private boolean isNightMode = false;
    private boolean isSharethroughLoading;
    int itemsADCounter;
    DFPAdAction mAdAction;
    private int mAdsRequested = 0;
    private ClinicalReferenceContent mContent;
    Context mContext;
    public ReferenceFindPosition mCurrentFindItem;
    public int mCurrentFindPos;
    private int mCurrentSection = 0;
    public ArrayList<ReferenceFindPosition> mFindPositions;
    public String mFindQuery = "";
    private ArrayList<Integer> mImageLoads;
    /* access modifiers changed from: private */
    public Set<NativeDFPAD> mInlineAdViewSet = new HashSet();
    private int mItemsCounter = 0;
    private LayoutManager mLayoutManager;
    private int mTextSizeIndex = -1;
    private boolean mTocEnabled;
    private int mTotalInlineAds = 0;
    String nextPageName;
    int previousSectionFirstPosition;
    int previousViewType;
    private NativeDFPAD topAdView;

    public ClinicalReferenceArticleContentDataAdapter(Context context, boolean z) {
        this.mContext = context;
        this.mTocEnabled = z;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            view = from.inflate(R.layout.clinical_content_section_header_item, viewGroup, false);
        } else if (i == 2) {
            view = from.inflate(R.layout.clinical_content_section_footer_item, viewGroup, false);
        } else if (i == 16) {
            return new ContentHeaderViewHolder(from.inflate(R.layout.clinical_content_section_header_w_byline_item, viewGroup, false), this.mContext, this.mDataListClickListener);
        } else {
            if (i == 3) {
                return new TableRowDataViewHolder(from.inflate(R.layout.clinical_content_section_table_row_item, viewGroup, false), this.mDataListClickListener);
            }
            if (i == 9) {
                return new CaptionViewHolder(from.inflate(R.layout.clinical_content_section_caption_item, viewGroup, false));
            }
            if (i == 4) {
                return new FigureDataViewHolder(from.inflate(R.layout.clinical_content_section_image_item, viewGroup, false), this.mDataListClickListener, this, this.mContext);
            }
            if (i == 5) {
                return new PreCachedNativeDfpInlineAdViewHolder(from.inflate(R.layout.combined_ad_layout, viewGroup, false));
            }
            if (i == 6) {
                return new NextSectionViewHolder(from.inflate(R.layout.clinical_next_section_item, viewGroup, false), this.mDataListClickListener);
            }
            if (i == 17) {
                View inflate = from.inflate(R.layout.sharethrough_reference_inline_ad, viewGroup, false);
                ShareThroughNativeADViewHolder shareThroughNativeADViewHolder = new ShareThroughNativeADViewHolder(inflate);
                shareThroughNativeADViewHolder.applyPadding(Util.dpToPixel(inflate.getContext(), 16));
                return shareThroughNativeADViewHolder;
            }
            view = from.inflate(R.layout.clinical_text_line_item, viewGroup, false);
        }
        return new DataViewHolder(view, this.mDataListClickListener, this.mTextSizeIndex, this.isNightMode);
    }

    public int getItemViewType(int i) {
        if (this.mItems.get(i) instanceof InlineAdLineItem) {
            return 5;
        }
        if (this.mItems.get(i) instanceof TableRowLineItem) {
            return 3;
        }
        if (this.mItems.get(i) instanceof FigureLineItem) {
            return 4;
        }
        if (this.mItems.get(i) instanceof NextSectionLineItem) {
            return 6;
        }
        if (this.mItems.get(i) instanceof CaptionRowLineItem) {
            return 9;
        }
        if (((LineItem) this.mItems.get(i)).isHeader) {
            return 1;
        }
        if (((LineItem) this.mItems.get(i)).isSubsection) {
            return 2;
        }
        if (((LineItem) this.mItems.get(i)).isContributor) {
            return 16;
        }
        return this.mItems.get(i) instanceof SharethroughInlineAd ? 17 : 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002c A[Catch:{ ContentNotFoundException -> 0x0097 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ea  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.medscape.android.contentviewer.LineItem> getContentItems() {
        /*
            r15 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.medscape.android.reference.model.ClinicalReferenceContent r1 = r15.mContent
            if (r1 == 0) goto L_0x010c
            r15.resetADPlacement()
            r1 = 0
            r15.mTotalInlineAds = r1
            r9 = 1
            r10 = 1
            com.medscape.android.reference.model.ClinicalReferenceContent r2 = r15.mContent     // Catch:{ ContentNotFoundException -> 0x0097 }
            int r3 = r15.mCurrentSection     // Catch:{ ContentNotFoundException -> 0x0097 }
            com.medscape.android.reference.model.Sect1 r2 = r2.getSection(r3)     // Catch:{ ContentNotFoundException -> 0x0097 }
            if (r2 != 0) goto L_0x001d
        L_0x001b:
            r11 = 0
            goto L_0x0029
        L_0x001d:
            java.util.ArrayList<com.medscape.android.reference.model.Sect2> r3 = r2.subsections     // Catch:{ ContentNotFoundException -> 0x0097 }
            if (r3 != 0) goto L_0x0022
            goto L_0x001b
        L_0x0022:
            java.util.ArrayList<com.medscape.android.reference.model.Sect2> r2 = r2.subsections     // Catch:{ ContentNotFoundException -> 0x0097 }
            int r2 = r2.size()     // Catch:{ ContentNotFoundException -> 0x0097 }
            r11 = r2
        L_0x0029:
            r12 = 0
        L_0x002a:
            if (r12 >= r11) goto L_0x00a1
            com.medscape.android.reference.model.ClinicalReferenceContent r2 = r15.mContent     // Catch:{ ContentNotFoundException -> 0x0097 }
            int r3 = r15.mCurrentSection     // Catch:{ ContentNotFoundException -> 0x0097 }
            com.medscape.android.reference.model.Sect2 r2 = r2.getSubsection(r3, r12)     // Catch:{ ContentNotFoundException -> 0x0097 }
            java.util.ArrayList<com.medscape.android.reference.model.Para> r13 = r2.paras     // Catch:{ ContentNotFoundException -> 0x0097 }
            com.medscape.android.reference.model.ClinicalReferenceContent r2 = r15.mContent     // Catch:{ ContentNotFoundException -> 0x0097 }
            int r3 = r15.mCurrentSection     // Catch:{ ContentNotFoundException -> 0x0097 }
            java.lang.String r4 = r2.getSubsectionTitle(r3, r12)     // Catch:{ ContentNotFoundException -> 0x0097 }
            boolean r2 = com.medscape.android.util.StringUtil.isNotEmpty(r4)     // Catch:{ ContentNotFoundException -> 0x0097 }
            if (r2 == 0) goto L_0x0074
            int r2 = r0.size()     // Catch:{ ContentNotFoundException -> 0x0097 }
            r15.SECTION_FIRST_POSITION = r2     // Catch:{ ContentNotFoundException -> 0x0097 }
            if (r2 != 0) goto L_0x0064
            java.util.ArrayList r0 = r15.addTopAdView(r0)     // Catch:{ ContentNotFoundException -> 0x0097 }
            int r2 = r0.size()     // Catch:{ ContentNotFoundException -> 0x0097 }
            r15.SECTION_FIRST_POSITION = r2     // Catch:{ ContentNotFoundException -> 0x0097 }
            boolean r2 = r15.mTocEnabled     // Catch:{ ContentNotFoundException -> 0x0097 }
            if (r2 != 0) goto L_0x005e
            java.util.ArrayList r0 = r15.addContributorToItems(r0)     // Catch:{ ContentNotFoundException -> 0x0097 }
        L_0x005e:
            int r2 = r0.size()     // Catch:{ ContentNotFoundException -> 0x0097 }
            r15.SECTION_FIRST_POSITION = r2     // Catch:{ ContentNotFoundException -> 0x0097 }
        L_0x0064:
            com.medscape.android.contentviewer.LineItem r14 = new com.medscape.android.contentviewer.LineItem     // Catch:{ ContentNotFoundException -> 0x0097 }
            r3 = 0
            int r5 = r15.SECTION_FIRST_POSITION     // Catch:{ ContentNotFoundException -> 0x0097 }
            r7 = 0
            r8 = 0
            r2 = r14
            r6 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)     // Catch:{ ContentNotFoundException -> 0x0097 }
            java.util.ArrayList r0 = r15.adContentItemToList(r0, r14, r10, r1)     // Catch:{ ContentNotFoundException -> 0x0097 }
        L_0x0074:
            if (r13 == 0) goto L_0x0094
            int r2 = r13.size()     // Catch:{ ContentNotFoundException -> 0x0097 }
            if (r2 <= 0) goto L_0x0094
            java.util.Iterator r2 = r13.iterator()     // Catch:{ ContentNotFoundException -> 0x0097 }
        L_0x0080:
            boolean r3 = r2.hasNext()     // Catch:{ ContentNotFoundException -> 0x0097 }
            if (r3 == 0) goto L_0x0094
            java.lang.Object r3 = r2.next()     // Catch:{ ContentNotFoundException -> 0x0097 }
            com.medscape.android.reference.model.Para r3 = (com.medscape.android.reference.model.Para) r3     // Catch:{ ContentNotFoundException -> 0x0097 }
            java.util.ArrayList r3 = r15.splitBySpan(r3)     // Catch:{ ContentNotFoundException -> 0x0097 }
            r0.addAll(r3)     // Catch:{ ContentNotFoundException -> 0x0097 }
            goto L_0x0080
        L_0x0094:
            int r12 = r12 + 1
            goto L_0x002a
        L_0x0097:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            java.lang.String r2 = "ContentValues"
            com.wbmd.wbmdcommons.logging.Trace.e(r2, r1)
        L_0x00a1:
            java.lang.String r1 = r15.nextPageName
            boolean r1 = com.medscape.android.util.StringUtil.isNotEmpty(r1)
            if (r1 == 0) goto L_0x00ba
            com.medscape.android.contentviewer.NextSectionLineItem r1 = new com.medscape.android.contentviewer.NextSectionLineItem
            r3 = 0
            java.lang.String r4 = r15.nextPageName
            int r5 = r15.SECTION_FIRST_POSITION
            r6 = 0
            r7 = 0
            r8 = 0
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r0.add(r1)
        L_0x00ba:
            com.medscape.android.reference.model.ClinicalReferenceContent r1 = r15.mContent
            java.util.LinkedHashMap<java.lang.String, com.medscape.android.reference.model.ReferenceItem> r1 = r1.references
            if (r1 == 0) goto L_0x00e2
            com.medscape.android.contentviewer.LineItem r1 = new com.medscape.android.contentviewer.LineItem
            com.medscape.android.contentviewer.CrossLink r3 = new com.medscape.android.contentviewer.CrossLink
            com.medscape.android.contentviewer.CrossLink$Type r2 = com.medscape.android.contentviewer.CrossLink.Type.REFERENCES
            java.lang.String r4 = "References"
            r3.<init>((com.medscape.android.contentviewer.CrossLink.Type) r2, (java.lang.String) r4)
            com.medscape.android.MedscapeApplication r2 = com.medscape.android.MedscapeApplication.get()
            r4 = 2131951916(0x7f13012c, float:1.954026E38)
            java.lang.String r4 = r2.getString(r4)
            int r5 = r15.SECTION_FIRST_POSITION
            r6 = 0
            r7 = 1
            r8 = 0
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r0.add(r1)
        L_0x00e2:
            com.medscape.android.reference.model.ClinicalReferenceContent r1 = r15.mContent
            boolean r1 = r1.hasMedia(r10)
            if (r1 == 0) goto L_0x010c
            com.medscape.android.contentviewer.LineItem r1 = new com.medscape.android.contentviewer.LineItem
            com.medscape.android.contentviewer.CrossLink r3 = new com.medscape.android.contentviewer.CrossLink
            com.medscape.android.contentviewer.CrossLink$Type r2 = com.medscape.android.contentviewer.CrossLink.Type.MEDIAGALLERY
            java.lang.String r4 = "Media Gallery"
            r3.<init>((com.medscape.android.contentviewer.CrossLink.Type) r2, (java.lang.String) r4)
            com.medscape.android.MedscapeApplication r2 = com.medscape.android.MedscapeApplication.get()
            r4 = 2131951915(0x7f13012b, float:1.9540258E38)
            java.lang.String r4 = r2.getString(r4)
            int r5 = r15.SECTION_FIRST_POSITION
            r6 = 0
            r7 = 1
            r8 = 0
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r0.add(r1)
        L_0x010c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.reference.ClinicalReferenceArticleContentDataAdapter.getContentItems():java.util.ArrayList");
    }

    private ArrayList<LineItem> addContributorToItems(ArrayList<LineItem> arrayList) {
        return this.mContent.contributors != null ? adContentItemToList(arrayList, new LineItem((CrossLink) null, (CharSequence) null, this.SECTION_FIRST_POSITION, false, false, false, true), 16, true) : arrayList;
    }

    private ArrayList<LineItem> addTopAdView(ArrayList<LineItem> arrayList) {
        if (CapabilitiesManager.getInstance(this.mContext).isSharethroughFeatureAvailable()) {
            arrayList.add(new SharethroughInlineAd(this.SECTION_FIRST_POSITION));
            this.mItemsCounter++;
        }
        return arrayList;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = ((LineItem) this.mItems.get(i)).sectionFirstPosition;
        boolean z = viewHolder instanceof DataViewHolder;
        if (z) {
            DataViewHolder dataViewHolder = (DataViewHolder) viewHolder;
            dataViewHolder.setNightModeOn(this.isNightMode);
            dataViewHolder.setTextSizeIndex(this.mTextSizeIndex);
        }
        if (viewHolder instanceof TableRowDataViewHolder) {
            ((TableRowDataViewHolder) viewHolder).bindTableRow((TableRowLineItem) this.mItems.get(i));
            View view = viewHolder.itemView;
            GridSLM.LayoutParams from = GridSLM.LayoutParams.from(view.getLayoutParams());
            from.setSlm(LinearSLM.ID);
            from.setFirstPosition(i2);
            view.setLayoutParams(from);
        } else if (viewHolder instanceof CaptionViewHolder) {
            ((CaptionViewHolder) viewHolder).bindCaption((CaptionRowLineItem) this.mItems.get(i));
            View view2 = viewHolder.itemView;
            GridSLM.LayoutParams from2 = GridSLM.LayoutParams.from(view2.getLayoutParams());
            from2.setSlm(LinearSLM.ID);
            from2.setFirstPosition(i2);
            view2.setLayoutParams(from2);
        } else if (viewHolder instanceof FigureDataViewHolder) {
            ((FigureDataViewHolder) viewHolder).bindFigure(this.mContent.mediaGalleryMap.get(((FigureLineItem) this.mItems.get(i)).figureId), i);
            View view3 = viewHolder.itemView;
            GridSLM.LayoutParams from3 = GridSLM.LayoutParams.from(view3.getLayoutParams());
            from3.setSlm(LinearSLM.ID);
            from3.setFirstPosition(i2);
            view3.setLayoutParams(from3);
        } else if (viewHolder instanceof PreCachedNativeDfpInlineAdViewHolder) {
            InlineAdLineItem inlineAdLineItem = (InlineAdLineItem) this.mItems.get(i);
            if (inlineAdLineItem.getObject() != null) {
                ((PreCachedNativeDfpInlineAdViewHolder) viewHolder).bind((NativeDFPAD) inlineAdLineItem.getObject());
            } else {
                Set<NativeDFPAD> set = this.mInlineAdViewSet;
                if (set == null || !set.iterator().hasNext()) {
                    ((PreCachedNativeDfpInlineAdViewHolder) viewHolder).bind((NativeDFPAD) null);
                    requestAdView(i);
                } else {
                    NativeDFPAD next = this.mInlineAdViewSet.iterator().next();
                    ((LineItem) this.mItems.get(i)).setObject(next);
                    this.mInlineAdViewSet.remove(next);
                    ((PreCachedNativeDfpInlineAdViewHolder) viewHolder).bind(next);
                }
            }
            View view4 = viewHolder.itemView;
            GridSLM.LayoutParams from4 = GridSLM.LayoutParams.from(view4.getLayoutParams());
            from4.setSlm(LinearSLM.ID);
            from4.setFirstPosition(i2);
            view4.setLayoutParams(from4);
        } else if (viewHolder instanceof NextSectionViewHolder) {
            ((NextSectionViewHolder) viewHolder).bindItem(((NextSectionLineItem) this.mItems.get(i)).nextSectionTitle);
            View view5 = viewHolder.itemView;
            GridSLM.LayoutParams from5 = GridSLM.LayoutParams.from(view5.getLayoutParams());
            from5.setSlm(LinearSLM.ID);
            from5.setFirstPosition(i2);
            view5.setLayoutParams(from5);
        } else if (viewHolder instanceof ContentHeaderViewHolder) {
            ((ContentHeaderViewHolder) viewHolder).bindItem(this.mContent);
            View view6 = viewHolder.itemView;
            GridSLM.LayoutParams from6 = GridSLM.LayoutParams.from(view6.getLayoutParams());
            from6.setSlm(LinearSLM.ID);
            from6.setFirstPosition(i2);
            view6.setLayoutParams(from6);
        } else if (viewHolder instanceof ShareThroughNativeADViewHolder) {
            ((ShareThroughNativeADViewHolder) viewHolder).onBind(this.topAdView, true, this.isSharethroughLoading);
            View view7 = viewHolder.itemView;
            GridSLM.LayoutParams from7 = GridSLM.LayoutParams.from(view7.getLayoutParams());
            from7.setSlm(LinearSLM.ID);
            from7.setFirstPosition(0);
            view7.setLayoutParams(from7);
        } else if (z) {
            ((DataViewHolder) viewHolder).bindItem((LineItem) this.mItems.get(i));
            View view8 = viewHolder.itemView;
            GridSLM.LayoutParams from8 = GridSLM.LayoutParams.from(view8.getLayoutParams());
            from8.setSlm(LinearSLM.ID);
            from8.setFirstPosition(i2);
            view8.setLayoutParams(from8);
        }
    }

    public void setData(Object obj) {
        if (obj != null && (obj instanceof ClinicalReferenceContent)) {
            this.mContent = (ClinicalReferenceContent) obj;
            resetADLoad();
            this.mImageLoads = new ArrayList<>();
        }
    }

    public void setCurrentItem(int i) {
        this.mFindQuery = "";
        this.mCurrentSection = i;
        resetADLoad();
        this.mItems = getContentItems();
        this.mImageLoads = new ArrayList<>();
    }

    public void setFindQuery(String str) {
        resetTableFindHighlights();
        this.mFindQuery = str;
        ArrayList<ReferenceFindPosition> arrayList = new ArrayList<>();
        this.mFindPositions = arrayList;
        arrayList.clear();
        this.mCurrentFindPos = 0;
        resetADLoad();
        this.mItems = getContentItems();
        notifyDataSetChanged();
        Context context = this.mContext;
        if (context instanceof ClinicalReferenceArticleActivity) {
            ((ClinicalReferenceArticleActivity) context).showCurrentFindPosition(this.mCurrentFindPos, this.mFindPositions.size());
        }
    }

    public ArrayList<LineItem> splitBySpan(Para para) {
        Figure figure;
        int i;
        final Para para2 = para;
        ArrayList<LineItem> arrayList = new ArrayList<>();
        EmbeddableNewlineSpan[] embeddableNewlineSpanArr = (EmbeddableNewlineSpan[]) para2.getSpans(0, para.length(), EmbeddableNewlineSpan.class);
        if (embeddableNewlineSpanArr != null && embeddableNewlineSpanArr.length > 0) {
            Arrays.sort(embeddableNewlineSpanArr, new Comparator<EmbeddableNewlineSpan>() {
                public int compare(EmbeddableNewlineSpan embeddableNewlineSpan, EmbeddableNewlineSpan embeddableNewlineSpan2) {
                    return para2.getSpanStart(embeddableNewlineSpan) - para2.getSpanStart(embeddableNewlineSpan2);
                }
            });
            int length = embeddableNewlineSpanArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                EmbeddableNewlineSpan embeddableNewlineSpan = embeddableNewlineSpanArr[i2];
                int spanEnd = para2.getSpanEnd(embeddableNewlineSpan);
                if (spanEnd >= 0 && i3 <= spanEnd) {
                    CharSequence subSequence = para2.subSequence(i3, spanEnd);
                    int i4 = ((embeddableNewlineSpan instanceof NativeArticleListSpan) || (embeddableNewlineSpan instanceof NativeArticleFigureSpan)) ? 7 : 0;
                    if (embeddableNewlineSpan instanceof NativeArticleSideHeaderSpan) {
                        i4 = 8;
                    }
                    if (StringUtil.isNotEmpty(subSequence.toString().trim())) {
                        LineItem lineItem = r10;
                        LineItem lineItem2 = new LineItem((CrossLink) null, subSequence, this.SECTION_FIRST_POSITION, false, false, true);
                        arrayList = adContentItemToList(arrayList, lineItem, i4, false);
                    }
                }
                if (embeddableNewlineSpan instanceof NativeArticleTableSpan) {
                    Table table = this.mContent.tablesMap.get(((NativeArticleTableSpan) embeddableNewlineSpan).tableId);
                    Iterator<ArrayList<Para>> it = table.rows.iterator();
                    int i5 = 0;
                    while (it.hasNext()) {
                        ArrayList next = it.next();
                        boolean z = true;
                        boolean z2 = i5 == table.headerRowIndex;
                        if (i5 == 0) {
                            i = -1;
                        } else {
                            i = i5 == table.rows.size() - 1 ? 1 : 0;
                        }
                        TableRowLineItem tableRowLineItem = new TableRowLineItem((CrossLink) null, next, z2, i, this.SECTION_FIRST_POSITION, false, false, true);
                        if (i5 == 0) {
                            z = false;
                        }
                        arrayList = adContentItemToList(arrayList, tableRowLineItem, 3, z);
                        i5++;
                    }
                    if (StringUtil.isNotEmpty(table.caption.toString())) {
                        arrayList = adContentItemToList(arrayList, new CaptionRowLineItem((CrossLink) null, table.caption, true, this.SECTION_FIRST_POSITION, false, false, true), 9, false);
                    }
                } else if (embeddableNewlineSpan instanceof NativeArticleFigureSpan) {
                    NativeArticleFigureSpan nativeArticleFigureSpan = (NativeArticleFigureSpan) embeddableNewlineSpan;
                    FigureLineItem createFigureLineItem = createFigureLineItem(nativeArticleFigureSpan.figureId);
                    if (createFigureLineItem != null) {
                        arrayList = adContentItemToList(arrayList, createFigureLineItem, 4, false);
                    }
                    ClinicalReferenceContent clinicalReferenceContent = this.mContent;
                    if (!(clinicalReferenceContent == null || clinicalReferenceContent.mediaGalleryMap == null || nativeArticleFigureSpan.figureId == null || !this.mContent.mediaGalleryMap.containsKey(nativeArticleFigureSpan.figureId) || (figure = this.mContent.mediaGalleryMap.get(nativeArticleFigureSpan.figureId)) == null)) {
                        Para para3 = figure.caption;
                        if (StringUtil.isNotEmpty(para3.toString())) {
                            arrayList = adContentItemToList(arrayList, new CaptionRowLineItem((CrossLink) null, para3, false, this.SECTION_FIRST_POSITION, false, false, true), 9, false);
                        }
                    }
                }
                i2++;
                i3 = spanEnd;
            }
            if (i3 >= para.length()) {
                return arrayList;
            }
            CharSequence subSequence2 = para2.subSequence(i3, para.length());
            if (StringUtil.isNotEmpty(subSequence2.toString().trim())) {
                return adContentItemToList(arrayList, new LineItem((CrossLink) null, subSequence2, this.SECTION_FIRST_POSITION, false, false, true), 0, false);
            }
            return arrayList;
        } else if (!StringUtil.isNotEmpty(para.toString().trim())) {
            return arrayList;
        } else {
            return adContentItemToList(arrayList, new LineItem((CrossLink) null, para, this.SECTION_FIRST_POSITION, false, false, true), 0, false);
        }
    }

    private FigureLineItem createFigureLineItem(String str) {
        Figure figure;
        ClinicalReferenceContent clinicalReferenceContent = this.mContent;
        CrossLink crossLink = null;
        if (clinicalReferenceContent == null || clinicalReferenceContent.mediaGalleryMap == null || str == null || (figure = this.mContent.mediaGalleryMap.get(str)) == null) {
            return null;
        }
        if (!(figure.graphic == null || figure.graphic.href == null)) {
            if (figure.isImage()) {
                crossLink = new CrossLink(CrossLink.Type.IMAGE, str);
            } else if (figure.isVideo()) {
                crossLink = new CrossLink(CrossLink.Type.VIDEO, str);
            }
        }
        return new FigureLineItem(crossLink, figure.id, this.SECTION_FIRST_POSITION, false, false, true);
    }

    public void setNextSectionName(String str) {
        this.nextPageName = str;
    }

    public void resetADPlacement() {
        this.itemsADCounter = 0;
        this.previousViewType = -1;
        this.previousSectionFirstPosition = this.SECTION_FIRST_POSITION;
    }

    public void resetADLoad() {
        this.mAdsRequested = 0;
        this.mTotalInlineAds = 0;
        this.mItemsCounter = 0;
        this.mInlineAdViewSet.clear();
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.medscape.android.contentviewer.LineItem> adContentItemToList(java.util.ArrayList<com.medscape.android.contentviewer.LineItem> r12, com.medscape.android.contentviewer.LineItem r13, int r14, boolean r15) {
        /*
            r11 = this;
            r0 = 1
            if (r15 != 0) goto L_0x0008
            int r1 = r11.itemsADCounter
            int r1 = r1 + r0
            r11.itemsADCounter = r1
        L_0x0008:
            int r1 = r11.previousViewType
            r2 = -1
            r3 = 0
            if (r1 == r2) goto L_0x0093
            if (r15 == 0) goto L_0x0012
            goto L_0x0093
        L_0x0012:
            int r15 = r11.itemsADCounter
            r1 = 3
            if (r15 <= r1) goto L_0x008c
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            int r1 = r11.previousViewType
            r15.append(r1)
            java.lang.String r1 = ";"
            r15.append(r1)
            r15.append(r14)
            java.lang.String r15 = r15.toString()
            int r1 = r11.previousViewType
            if (r1 == r0) goto L_0x0085
            r2 = 2
            if (r1 == r2) goto L_0x0085
            r2 = 7
            if (r14 == r2) goto L_0x0085
            r2 = 8
            if (r1 == r2) goto L_0x0085
            r1 = 4
            if (r14 == r1) goto L_0x0085
            r1 = 9
            if (r14 == r1) goto L_0x0085
            java.util.HashSet r1 = r11.notEligibleADTagCombinations()
            boolean r15 = r1.contains(r15)
            if (r15 == 0) goto L_0x004d
            goto L_0x0085
        L_0x004d:
            r11.resetADPlacement()
            int r15 = r13.sectionFirstPosition
            r11.previousSectionFirstPosition = r15
            int r15 = r12.size()
            if (r15 <= 0) goto L_0x0069
            int r15 = r12.size()
            int r15 = r15 - r0
            java.lang.Object r15 = r12.get(r15)
            com.medscape.android.contentviewer.LineItem r15 = (com.medscape.android.contentviewer.LineItem) r15
            int r15 = r15.sectionFirstPosition
            r11.previousSectionFirstPosition = r15
        L_0x0069:
            com.medscape.android.contentviewer.InlineAdLineItem r15 = new com.medscape.android.contentviewer.InlineAdLineItem
            r5 = 0
            r6 = 0
            int r7 = r11.previousSectionFirstPosition
            r8 = 0
            r9 = 0
            r10 = 0
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r12.add(r15)
            int r15 = r11.mItemsCounter
            int r15 = r15 + r0
            r11.mItemsCounter = r15
            int r15 = r11.mTotalInlineAds
            int r15 = r15 + r0
            r11.mTotalInlineAds = r15
            r15 = 1
            goto L_0x009a
        L_0x0085:
            r11.previousViewType = r14
            int r15 = r13.sectionFirstPosition
            r11.previousSectionFirstPosition = r15
            goto L_0x0099
        L_0x008c:
            r11.previousViewType = r14
            int r15 = r13.sectionFirstPosition
            r11.previousSectionFirstPosition = r15
            goto L_0x0099
        L_0x0093:
            r11.previousViewType = r14
            int r15 = r13.sectionFirstPosition
            r11.previousSectionFirstPosition = r15
        L_0x0099:
            r15 = 0
        L_0x009a:
            if (r15 == 0) goto L_0x00aa
            int r15 = r11.itemsADCounter
            int r15 = r15 + r0
            r11.itemsADCounter = r15
            if (r14 != r0) goto L_0x00aa
            int r14 = r11.SECTION_FIRST_POSITION
            int r14 = r14 + r0
            r11.SECTION_FIRST_POSITION = r14
            r13.sectionFirstPosition = r14
        L_0x00aa:
            java.lang.String r14 = r11.mFindQuery
            boolean r14 = com.medscape.android.util.StringUtil.isNotEmpty(r14)
            if (r14 == 0) goto L_0x00d9
            boolean r14 = r13 instanceof com.medscape.android.contentviewer.TableRowLineItem
            if (r14 == 0) goto L_0x00bf
            r14 = r13
            com.medscape.android.contentviewer.TableRowLineItem r14 = (com.medscape.android.contentviewer.TableRowLineItem) r14
            int r15 = r11.mItemsCounter
            r11.addFindPositionMaptoTables(r14, r15)
            goto L_0x00d9
        L_0x00bf:
            java.lang.CharSequence r14 = r13.text
            if (r14 == 0) goto L_0x00d9
            java.lang.CharSequence r14 = r13.text
            java.lang.String r14 = r14.toString()
            boolean r14 = com.medscape.android.util.StringUtil.isNotEmpty(r14)
            if (r14 == 0) goto L_0x00d9
            java.lang.CharSequence r14 = r13.text
            int r15 = r11.mItemsCounter
            java.lang.CharSequence r14 = r11.addFindPositionMap(r14, r15, r3)
            r13.text = r14
        L_0x00d9:
            r12.add(r13)
            int r13 = r11.mItemsCounter
            int r13 = r13 + r0
            r11.mItemsCounter = r13
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.reference.ClinicalReferenceArticleContentDataAdapter.adContentItemToList(java.util.ArrayList, com.medscape.android.contentviewer.LineItem, int, boolean):java.util.ArrayList");
    }

    public HashSet<String> notEligibleADTagCombinations() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1;1");
        hashSet.add("1;8");
        hashSet.add("8;1");
        hashSet.add("0;7");
        return hashSet;
    }

    public void setNightMode(boolean z) {
        this.isNightMode = z;
        resetTableFindHighlights();
        notifyDataSetChanged();
    }

    public void setTextSizeIndex(int i) {
        this.mTextSizeIndex = i;
        notifyDataSetChanged();
    }

    public void requestAdView(int i) {
        LayoutManager layoutManager;
        if (this.mAdsRequested < this.mTotalInlineAds && (layoutManager = this.mLayoutManager) != null && layoutManager.getChildCount() != 0 && this.mItems != null) {
            try {
                this.mAdsRequested++;
                NativeAdAction nativeAdAction = new NativeAdAction(this.mContext, DFPReferenceAdListener.AD_UNIT_ID, (View) null);
                nativeAdAction.prepADWithCombinedRequests(new INativeDFPAdLoadListener() {
                    public void onAdFailedToLoad(int i) {
                    }

                    public void onAdLoaded(NativeDFPAD nativeDFPAD) {
                        ClinicalReferenceArticleContentDataAdapter.this.mInlineAdViewSet.add(nativeDFPAD);
                        ClinicalReferenceArticleContentDataAdapter.this.notifyDataSetChanged();
                    }
                }, new AdSize[]{DFPAdAction.ADSIZE_300x45, DFPAdAction.ADSIZE_300x61, DFPAdAction.ADSIZE_300x76, DFPAdAction.ADSIZE_300x91, DFPAdAction.ADSIZE_300x106, DFPAdAction.ADSIZE_300x121, DFPAdAction.ADSIZE_300x136, DFPAdAction.ADSIZE_300x50, DFPAdAction.ADSIZE_320x50, DFPAdAction.ADSIZE_300x400, DFPAdAction.ADSIZE_3x1, DFPAdAction.ADSIZE_1x3, AdSize.MEDIUM_RECTANGLE});
                nativeAdAction.isInlineAd = true;
                Trace.d("CKB-InlineAD", "AD requested for pos: " + i);
                ((ClinicalReferenceArticleActivity) this.mContext).getAd(nativeAdAction);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    private void addFindPositionMaptoTables(TableRowLineItem tableRowLineItem, int i) {
        Iterator<Para> it = tableRowLineItem.tableColumns.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            addFindPositionMap(it.next(), i, i2, 0);
            i2++;
        }
    }

    public CharSequence addFindPositionMap(CharSequence charSequence, int i, int i2) {
        int indexOf;
        if (charSequence == null || StringUtil.isNullOrEmpty(charSequence.toString()) || (indexOf = charSequence.toString().toLowerCase().indexOf(this.mFindQuery.toLowerCase(), i2)) < 0) {
            return charSequence;
        }
        Para para = new Para();
        para.append(charSequence);
        Para applyTint = applyTint(para, indexOf);
        ReferenceFindPosition referenceFindPosition = new ReferenceFindPosition();
        referenceFindPosition.contentRow = i;
        referenceFindPosition.contentIndex = indexOf;
        addFindPosition(referenceFindPosition);
        return addFindPositionMap(applyTint, i, indexOf + 1);
    }

    private Para addFindPositionMap(Para para, int i, int i2, int i3) {
        int indexOf = para.toString().toLowerCase().indexOf(this.mFindQuery.toLowerCase(), i3);
        if (indexOf < 0) {
            return para;
        }
        Para applyTint = applyTint(para, indexOf);
        ReferenceFindPosition referenceFindPosition = new ReferenceFindPosition();
        referenceFindPosition.contentRow = i;
        referenceFindPosition.contentIndex = indexOf;
        referenceFindPosition.contentTableColumn = i2;
        addFindPosition(referenceFindPosition);
        return addFindPositionMap(applyTint, i, i2, indexOf + 1);
    }

    private Para applyTint(Para para, int i) {
        ArrayList<ReferenceFindPosition> arrayList = this.mFindPositions;
        if (arrayList != null) {
            para.setSpan(new BackgroundColorSpan(ContextCompat.getColor(this.mContext, arrayList.size() == 0 ? R.color.orange_tint : R.color.yellow_tint)), i, this.mFindQuery.length() + i, 512);
        }
        return para;
    }

    private void addFindPosition(ReferenceFindPosition referenceFindPosition) {
        ArrayList<ReferenceFindPosition> arrayList = this.mFindPositions;
        if (arrayList != null) {
            if (arrayList.size() == 0) {
                this.mCurrentFindItem = referenceFindPosition;
            }
            this.mFindPositions.add(referenceFindPosition);
        }
    }

    public void moveFindPosition(boolean z) {
        if (this.mFindPositions != null) {
            int i = z ? this.mCurrentFindPos + 1 : this.mCurrentFindPos - 1;
            if (i >= 0 && i < this.mFindPositions.size()) {
                updateCurrentItemHighLight(true);
                this.mCurrentFindPos = i;
                this.mCurrentFindItem = this.mFindPositions.get(i);
                updateCurrentItemHighLight(false);
            }
            Context context = this.mContext;
            if (context instanceof ClinicalReferenceArticleActivity) {
                ((ClinicalReferenceArticleActivity) context).showCurrentFindPosition(this.mCurrentFindPos, this.mFindPositions.size());
            }
        }
    }

    private void updateCurrentItemHighLight(boolean z) {
        int i;
        ReferenceFindPosition referenceFindPosition = this.mCurrentFindItem;
        if (referenceFindPosition != null && (i = referenceFindPosition.contentRow) >= 0) {
            LineItem lineItem = (LineItem) this.mItems.get(i);
            int i2 = z ? R.color.yellow_tint : R.color.orange_tint;
            if (lineItem instanceof TableRowLineItem) {
                updateTableFindItemColor(this.mCurrentFindItem, i2);
            } else if (lineItem.text != null && StringUtil.isNotEmpty(lineItem.text.toString())) {
                int i3 = this.mCurrentFindItem.contentIndex;
                int length = this.mCurrentFindItem.contentIndex + this.mFindQuery.length();
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(lineItem.text);
                removeBackGroundSpans(spannableStringBuilder, i3, length);
                spannableStringBuilder.setSpan(new BackgroundColorSpan(ContextCompat.getColor(this.mContext, i2)), i3, length, 18);
                lineItem.text = spannableStringBuilder;
                this.mItems.set(i, lineItem);
            }
            notifyDataSetChanged();
        }
    }

    private void resetTableFindHighlights() {
        ClinicalReferenceContent clinicalReferenceContent = this.mContent;
        if (clinicalReferenceContent != null && clinicalReferenceContent.tablesMap != null && this.mContent.tablesMap.size() > 0) {
            for (Map.Entry<String, Table> value : this.mContent.tablesMap.entrySet()) {
                Table table = (Table) value.getValue();
                if (!(table == null || table.rows == null || table.rows.size() <= 0)) {
                    Iterator<ArrayList<Para>> it = table.rows.iterator();
                    while (it.hasNext()) {
                        Iterator it2 = it.next().iterator();
                        while (it2.hasNext()) {
                            Para para = (Para) it2.next();
                            removeBackGroundSpans(para, 0, para.length());
                        }
                    }
                }
            }
        }
    }

    private void updateTableFindItemColor(ReferenceFindPosition referenceFindPosition, int i) {
        Para para = ((TableRowLineItem) this.mItems.get(referenceFindPosition.contentRow)).tableColumns.get(referenceFindPosition.contentTableColumn);
        int i2 = referenceFindPosition.contentIndex;
        int length = referenceFindPosition.contentIndex + this.mFindQuery.length();
        removeBackGroundSpans(para, i2, length);
        para.setSpan(new BackgroundColorSpan(ContextCompat.getColor(this.mContext, i)), i2, length, 512);
    }

    public void onDataUpdated(int i) {
        if (!this.mImageLoads.contains(Integer.valueOf(i))) {
            this.mImageLoads.add(Integer.valueOf(i));
            notifyDataSetChanged();
        }
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        if (viewHolder instanceof FigureDataViewHolder) {
            FigureDataViewHolder figureDataViewHolder = (FigureDataViewHolder) viewHolder;
            figureDataViewHolder.graphic.setImageBitmap((Bitmap) null);
            figureDataViewHolder.setLayoutParams(false);
            figureDataViewHolder.mIsImageLoaded = false;
        }
    }

    private void removeBackGroundSpans(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        BackgroundColorSpan[] backgroundColorSpanArr = (BackgroundColorSpan[]) spannableStringBuilder.getSpans(i, i2, BackgroundColorSpan.class);
        if (backgroundColorSpanArr != null && backgroundColorSpanArr.length > 0) {
            for (BackgroundColorSpan removeSpan : backgroundColorSpanArr) {
                spannableStringBuilder.removeSpan(removeSpan);
            }
        }
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof DataViewHolder) {
            ((DataViewHolder) viewHolder).enableTextSelection();
        }
        if (viewHolder instanceof CaptionViewHolder) {
            ((CaptionViewHolder) viewHolder).enableTextSelection();
        }
    }

    public void setTopAdView(NativeDFPAD nativeDFPAD, boolean z) {
        this.topAdView = nativeDFPAD;
        this.isSharethroughLoading = z;
    }
}
