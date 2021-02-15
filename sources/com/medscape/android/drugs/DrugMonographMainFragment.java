package com.medscape.android.drugs;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractIndexFragment;
import com.medscape.android.activity.formulary.FormularyFinder;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.ads.ShareThroughNativeADViewHolder;
import com.medscape.android.db.DrugDataHelper;
import com.medscape.android.drugs.helper.DrugMonographViewHelper;
import com.medscape.android.drugs.model.DrugManufactureAd;
import com.medscape.android.drugs.model.DrugMonograph;
import com.medscape.android.drugs.model.DrugMonographIndexElement;
import com.medscape.android.drugs.viewholders.DrugManufacturerViewHolder;
import com.medscape.android.drugs.viewholders.DrugsDisclaimerViewHolder;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import com.medscape.android.util.ViewHelper;
import com.medscape.android.webmdrx.RxLauncher;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LinearSLM;
import java.util.ArrayList;

public class DrugMonographMainFragment extends AbstractIndexFragment {
    /* access modifiers changed from: private */
    public DrugManufactureAd drugManufactureAd;
    DrugSectionDataAdapter drugSectionDataAdapter;
    private DrugMonograph mDrugMonograph;
    private RecyclerView mDrugMonographSectionsViewer;
    private DrugMonographViewHelper mDrugMonographViewHelper;
    FormularyFinder mFormularyFinder;
    int mFormularyViewID = -1;
    /* access modifiers changed from: private */
    public ArrayList<DrugMonographIndexElement> mIndexElements;
    private MedscapeException mInternetRequiredException;
    boolean mIsItemsClickable;
    private LinearLayoutManager mLayoutManager;
    View mRootView;
    int mRxDrugViewID = -1;
    /* access modifiers changed from: private */
    public NativeDFPAD nativeDFPAD;
    TextView tvDrugBrandNames;
    TextView tvDrugClasses;
    TextView tvDrugGenericName;
    TextView tvDrugManufacturerSubTitle;
    TextView tvDrugManufacturerTitle;

    public static DrugMonographMainFragment newInstance() {
        DrugMonographMainFragment drugMonographMainFragment = new DrugMonographMainFragment();
        drugMonographMainFragment.drugManufactureAd = new DrugManufactureAd();
        return drugMonographMainFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_drug_main, viewGroup, false);
    }

    public void onResume() {
        super.onResume();
        DrugSectionDataAdapter drugSectionDataAdapter2 = new DrugSectionDataAdapter(this.mIndexElements);
        this.drugSectionDataAdapter = drugSectionDataAdapter2;
        this.mDrugMonographSectionsViewer.setAdapter(drugSectionDataAdapter2);
        DrugMonograph drugMonograph = this.mDrugMonograph;
        if (drugMonograph != null && drugMonograph.getSections() != null && !this.mDrugMonograph.getSections().isEmpty() && isAdded() && (getActivity() instanceof DrugMonographMainActivity)) {
            ((DrugMonographMainActivity) getActivity()).makeNativeDrugManufactureAd();
            ((DrugMonographMainActivity) getActivity()).makePopupAdRequest();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mRootView = getView();
        this.mInternetRequiredException = new MedscapeException(getResources().getString(R.string.internet_required));
        Bundle arguments = getArguments();
        if (arguments != null) {
            DrugMonograph drugMonograph = (DrugMonograph) arguments.getSerializable("drug");
            this.mDrugMonograph = drugMonograph;
            this.mDrugMonographViewHelper = new DrugMonographViewHelper(drugMonograph);
            this.mIndexElements = arguments.getParcelableArrayList("sectionElements");
        }
        if (getActivity() != null && isAdded()) {
            getActivity().setTitle(this.mDrugMonograph.getHeader().getGc());
        }
        this.mDrugMonographSectionsViewer = (RecyclerView) this.mRootView.findViewById(R.id.drugMonographSections);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mLayoutManager = linearLayoutManager;
        this.mDrugMonographSectionsViewer.setLayoutManager(linearLayoutManager);
        this.mDrugMonographSectionsViewer.setVisibility(0);
        this.tvDrugGenericName = (TextView) this.mRootView.findViewById(R.id.drugGenericName);
        this.tvDrugBrandNames = (TextView) this.mRootView.findViewById(R.id.drugBrandNames);
        this.tvDrugClasses = (TextView) this.mRootView.findViewById(R.id.drugClasses);
        this.tvDrugGenericName.setText(this.mDrugMonographViewHelper.getGenericNameHeader());
        this.tvDrugBrandNames.setText(this.mDrugMonograph.getHeader().getBr());
        this.tvDrugClasses.setText(this.mDrugMonographViewHelper.getClassNamesHeader());
        this.mIsItemsClickable = true;
    }

    public void onListItemClick(final int i) {
        if (!this.mIsItemsClickable) {
            return;
        }
        if (i == this.mFormularyViewID) {
            FormularyFinder formularyFinder = this.mFormularyFinder;
            if (formularyFinder != null) {
                if (formularyFinder.isDownloadComplete()) {
                    if (this.mFormularyFinder.hasFormulary()) {
                        openFormulary();
                        return;
                    }
                    DrugMonographIndexElement.FORMULARY.isProgressRequired = false;
                    this.drugSectionDataAdapter.setData(this.mIndexElements);
                    Toast.makeText(getActivity(), getString(R.string.no_formulary_available), 0).show();
                } else if (Util.isOnline(getActivity())) {
                    this.mIsItemsClickable = false;
                    DrugMonographIndexElement.FORMULARY.isProgressRequired = true;
                    this.drugSectionDataAdapter.setData(this.mIndexElements);
                    this.mFormularyFinder.setCallBack(new FormularyFinder.Callbacks() {
                        public void onFormularyDownloaded(boolean z) {
                            DrugMonographMainFragment.this.mIsItemsClickable = true;
                            DrugMonographIndexElement.FORMULARY.isProgressRequired = false;
                            DrugMonographMainFragment.this.drugSectionDataAdapter.setData(DrugMonographMainFragment.this.mIndexElements);
                            if (DrugMonographMainFragment.this.isAdded() && DrugMonographMainFragment.this.getActivity() != null) {
                                if (!z || DrugMonographMainFragment.this.mFormularyFinder.getBrandModelList() == null || DrugMonographMainFragment.this.mFormularyFinder.getBrandModelList().size() <= 0) {
                                    Toast.makeText(DrugMonographMainFragment.this.getActivity(), DrugMonographMainFragment.this.getString(R.string.no_formulary_available), 0).show();
                                } else {
                                    DrugMonographMainFragment.this.openFormulary();
                                }
                            }
                        }
                    });
                    if (isAdded() && getActivity() != null) {
                        this.mFormularyFinder.checkForFormularies(((DrugMonographMainActivity) getActivity()).getContentId());
                    }
                } else {
                    this.mInternetRequiredException.showSnackBar(this.mRootView, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
                        public void onClick(View view) {
                            DrugMonographMainFragment.this.onListItemClick(i);
                        }
                    });
                }
            }
        } else if (i == this.mRxDrugViewID) {
            String gc = this.mDrugMonograph.getHeader().getGc();
            if (isAdded() && getActivity() != null && (getActivity() instanceof DrugMonographMainActivity)) {
                gc = ((DrugMonographMainActivity) getActivity()).getmDrugName();
            }
            if (getActivity() != null && (getActivity() instanceof DrugMonographMainActivity)) {
                int contentId = ((DrugMonographMainActivity) getActivity()).getContentId();
                int findGenericIdFromContentId = DrugDataHelper.findGenericIdFromContentId(getActivity(), contentId, gc);
                ViewHelper.findById((Activity) getActivity(), 16908301).setVisibility(0);
                RxLauncher.Companion.launchRxDrug(gc, String.valueOf(findGenericIdFromContentId), String.valueOf(contentId), getActivity());
            }
        } else {
            this.mCallbacks.onItemSelected(i);
        }
    }

    class DrugSectionDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int DISCLAIMER_ITEM_VIEW = 2;
        int VIEW_AD_ITEM_TYPE = 0;
        int VIEW_LINE_ITEM_TYPE = 1;
        int VIEW_SHARE_THROUGH_AD_ITEM_TYPE = 3;
        private ArrayList<DrugMonographIndexElement> mIndexElements;

        DrugSectionDataAdapter(ArrayList<DrugMonographIndexElement> arrayList) {
            this.mIndexElements = arrayList;
        }

        public int getItemViewType(int i) {
            if (i != 0 || !this.mIndexElements.get(i).isAD) {
                if (i == this.mIndexElements.size() - 1) {
                    return 2;
                }
                return this.VIEW_LINE_ITEM_TYPE;
            } else if (DrugMonographMainFragment.this.nativeDFPAD == null) {
                return this.VIEW_AD_ITEM_TYPE;
            } else {
                return this.VIEW_SHARE_THROUGH_AD_ITEM_TYPE;
            }
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            if (i == this.VIEW_AD_ITEM_TYPE) {
                return DrugManufacturerViewHolder.Companion.create(from, viewGroup);
            }
            if (i == this.VIEW_SHARE_THROUGH_AD_ITEM_TYPE) {
                View inflate = from.inflate(R.layout.sharethrough_reference_inline_ad, viewGroup, false);
                ShareThroughNativeADViewHolder shareThroughNativeADViewHolder = new ShareThroughNativeADViewHolder(inflate);
                shareThroughNativeADViewHolder.applyPadding(Util.dpToPixel(inflate.getContext(), 16));
                return shareThroughNativeADViewHolder;
            } else if (i == 2) {
                return new DrugsDisclaimerViewHolder(from.inflate(R.layout.drug_monograph_disclaimer, viewGroup, false));
            } else {
                return new ViewHolder(from.inflate(R.layout.text_line_item, viewGroup, false));
            }
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            boolean z;
            if (viewHolder instanceof ViewHolder) {
                ViewHolder viewHolder2 = (ViewHolder) viewHolder;
                viewHolder2.tvTitle.setText(this.mIndexElements.get(i).title);
                viewHolder2.tvTitle.setTag(Integer.valueOf(i));
                viewHolder2.progressBar.setVisibility(this.mIndexElements.get(i).isProgressRequired ? 0 : 8);
                if (this.mIndexElements.get(i).equals(DrugMonographIndexElement.FORMULARY)) {
                    DrugMonographMainFragment.this.mFormularyViewID = ((Integer) viewHolder2.tvTitle.getTag()).intValue();
                } else if (this.mIndexElements.get(i).equals(DrugMonographIndexElement.PRICINGSAVINGS)) {
                    DrugMonographMainFragment.this.mRxDrugViewID = ((Integer) viewHolder2.tvTitle.getTag()).intValue();
                }
            } else if (viewHolder instanceof DrugManufacturerViewHolder) {
                z = ((DrugManufacturerViewHolder) viewHolder).onBind(DrugMonographMainFragment.this.getContext(), DrugMonographMainFragment.this.drugManufactureAd);
                View view = viewHolder.itemView;
                GridSLM.LayoutParams from = GridSLM.LayoutParams.from(view.getLayoutParams());
                from.setSlm(LinearSLM.ID);
                from.setFirstPosition(0);
                from.isHeader = z;
                view.setLayoutParams(from);
            } else if (viewHolder instanceof ShareThroughNativeADViewHolder) {
                ((ShareThroughNativeADViewHolder) viewHolder).onBind(DrugMonographMainFragment.this.nativeDFPAD, true);
            }
            z = false;
            View view2 = viewHolder.itemView;
            GridSLM.LayoutParams from2 = GridSLM.LayoutParams.from(view2.getLayoutParams());
            from2.setSlm(LinearSLM.ID);
            from2.setFirstPosition(0);
            from2.isHeader = z;
            view2.setLayoutParams(from2);
        }

        public int getItemCount() {
            return this.mIndexElements.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ProgressBar progressBar;
            TextView tvTitle;

            public ViewHolder(View view) {
                super(view);
                this.tvTitle = (TextView) view.findViewById(R.id.text);
                this.progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
                this.tvTitle.setOnClickListener(new View.OnClickListener(DrugSectionDataAdapter.this) {
                    public void onClick(View view) {
                        DrugMonographMainFragment.this.onListItemClick(((Integer) view.getTag()).intValue());
                    }
                });
            }
        }

        public void setData(ArrayList<DrugMonographIndexElement> arrayList) {
            this.mIndexElements = arrayList;
            notifyDataSetChanged();
        }
    }

    public void setFormularyFinder(FormularyFinder formularyFinder) {
        this.mFormularyFinder = formularyFinder;
    }

    public void setmanufactureAd(DrugManufactureAd drugManufactureAd2) {
        this.drugManufactureAd = drugManufactureAd2;
        this.nativeDFPAD = drugManufactureAd2.getAd();
        this.drugSectionDataAdapter.notifyItemChanged(0);
    }

    public void onPause() {
        super.onPause();
        MedscapeException medscapeException = this.mInternetRequiredException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
    }

    public void openFormulary() {
        if (isAdded() && getActivity() != null) {
            ((DrugMonographMainActivity) getActivity()).openFormulary(this.mFormularyFinder.getBrandModelList());
        }
    }
}
