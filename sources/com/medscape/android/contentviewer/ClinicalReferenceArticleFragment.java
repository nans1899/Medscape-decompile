package com.medscape.android.contentviewer;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.ads.InlineAdTouchHelper;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.interfaces.INightModeListener;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.reference.ClinicalReferenceArticleContentDataAdapter;
import com.medscape.android.util.Util;
import com.tonicartos.superslim.LayoutManager;
import com.webmd.wbmdomnituremanager.WBMDOmniturePaginationHandler;
import com.webmd.wbmdomnituremanager.WBMDPaginationListener;
import java.util.HashMap;
import java.util.Map;
import kotlinx.coroutines.DebugKt;

public class ClinicalReferenceArticleFragment extends Fragment implements WBMDPaginationListener {
    RelativeLayout contentSettingsLayout;
    SeekBar fontSeekBar;
    RelativeLayout functionsLayout;
    ClinicalReferenceArticleContentDataAdapter mCRAContentAdapter;
    RecyclerView mContentSectionView;
    int mCurrentRow;
    ContentSectionDataAdapter mDataAdapter;
    LayoutManager mLayoutManager;
    String mPvid = "";
    String mSectionNameForOmniture;
    INightModeListener nightModeListener;
    SwitchCompat nightSwitch;
    View rootView;

    public void onDebugOptions(int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.content_reference_page, viewGroup, false);
        setUpViews();
        setUpListeners();
        return this.rootView;
    }

    private void setUpViews() {
        this.contentSettingsLayout = (RelativeLayout) this.rootView.findViewById(R.id.content_setting_layout);
        this.functionsLayout = (RelativeLayout) this.rootView.findViewById(R.id.content_settings_functions);
        this.fontSeekBar = (SeekBar) this.rootView.findViewById(R.id.content_setting_seek_bar);
        this.nightSwitch = (SwitchCompat) this.rootView.findViewById(R.id.content_settings_night_switch);
    }

    private void setUpListeners() {
        this.contentSettingsLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClinicalReferenceArticleFragment.this.handleTextOptions("close");
            }
        });
        this.fontSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    if (ClinicalReferenceArticleFragment.this.mCRAContentAdapter != null) {
                        ClinicalReferenceArticleFragment.this.mCRAContentAdapter.setTextSizeIndex(i);
                    }
                    if (ClinicalReferenceArticleFragment.this.mDataAdapter != null) {
                        ClinicalReferenceArticleFragment.this.mDataAdapter.setTextSizeIndex(i);
                    }
                    SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
                    sharedPreferenceProvider.save(Constants.PREF_REFERENCE_TEXT_SIZE_INDEX + AuthenticationManager.getInstance(ClinicalReferenceArticleFragment.this.getContext()).getMaskedGuid(), ClinicalReferenceArticleFragment.this.fontSeekBar.getProgress());
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                OmnitureManager.get().trackModule(ClinicalReferenceArticleFragment.this.getContext(), Constants.OMNITURE_CHANNEL_REFERENCE, "font-resizer", ContentUtils.getOmnitureValueForFontSize(seekBar.getProgress()), (Map<String, Object>) null);
            }
        });
        this.nightSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ClinicalReferenceArticleFragment.this.nightSwitch.isChecked()) {
                    if (ClinicalReferenceArticleFragment.this.mCRAContentAdapter != null) {
                        ClinicalReferenceArticleFragment.this.mCRAContentAdapter.setNightMode(true);
                    }
                    if (ClinicalReferenceArticleFragment.this.mDataAdapter != null) {
                        ClinicalReferenceArticleFragment.this.mDataAdapter.setNightMode(true);
                    }
                    OmnitureManager.get().trackModule(ClinicalReferenceArticleFragment.this.getContext(), Constants.OMNITURE_CHANNEL_REFERENCE, "night-mode", DebugKt.DEBUG_PROPERTY_VALUE_ON, (Map<String, Object>) null);
                    SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
                    sharedPreferenceProvider.save(Constants.PREF_REFERENCE_NIGHT_MODE + AuthenticationManager.getInstance(ClinicalReferenceArticleFragment.this.getContext()).getMaskedGuid(), 1);
                    ClinicalReferenceArticleFragment.this.mContentSectionView.setBackgroundColor(ClinicalReferenceArticleFragment.this.getContext().getResources().getColor(R.color.black));
                } else {
                    if (ClinicalReferenceArticleFragment.this.mCRAContentAdapter != null) {
                        ClinicalReferenceArticleFragment.this.mCRAContentAdapter.setNightMode(false);
                    }
                    if (ClinicalReferenceArticleFragment.this.mDataAdapter != null) {
                        ClinicalReferenceArticleFragment.this.mDataAdapter.setNightMode(false);
                    }
                    OmnitureManager.get().trackModule(ClinicalReferenceArticleFragment.this.getContext(), Constants.OMNITURE_CHANNEL_REFERENCE, "night-mode", DebugKt.DEBUG_PROPERTY_VALUE_OFF, (Map<String, Object>) null);
                    SharedPreferenceProvider sharedPreferenceProvider2 = SharedPreferenceProvider.get();
                    sharedPreferenceProvider2.save(Constants.PREF_REFERENCE_NIGHT_MODE + AuthenticationManager.getInstance(ClinicalReferenceArticleFragment.this.getContext()).getMaskedGuid(), 0);
                    ClinicalReferenceArticleFragment.this.mContentSectionView.setBackgroundColor(ClinicalReferenceArticleFragment.this.getContext().getResources().getColor(R.color.white));
                }
                if (ClinicalReferenceArticleFragment.this.nightModeListener != null) {
                    ClinicalReferenceArticleFragment.this.nightModeListener.onNightModeChanged(ClinicalReferenceArticleFragment.this.nightSwitch.isChecked());
                }
            }
        });
    }

    private void fillViews() {
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        boolean z = false;
        int i = sharedPreferenceProvider.get(Constants.PREF_REFERENCE_NIGHT_MODE + AuthenticationManager.getInstance(getContext()).getMaskedGuid(), 0);
        SharedPreferenceProvider sharedPreferenceProvider2 = SharedPreferenceProvider.get();
        int i2 = sharedPreferenceProvider2.get(Constants.PREF_REFERENCE_TEXT_SIZE_INDEX + AuthenticationManager.getInstance(getContext()).getMaskedGuid(), -1);
        if (i2 > -1) {
            this.fontSeekBar.setProgress(i2);
            ClinicalReferenceArticleContentDataAdapter clinicalReferenceArticleContentDataAdapter = this.mCRAContentAdapter;
            if (clinicalReferenceArticleContentDataAdapter != null) {
                clinicalReferenceArticleContentDataAdapter.setTextSizeIndex(i2);
            }
            ContentSectionDataAdapter contentSectionDataAdapter = this.mDataAdapter;
            if (contentSectionDataAdapter != null) {
                contentSectionDataAdapter.setTextSizeIndex(i2);
            }
        }
        if (i == 1) {
            this.nightSwitch.setChecked(true);
            RecyclerView recyclerView = this.mContentSectionView;
            if (recyclerView != null) {
                recyclerView.setBackgroundColor(getContext().getResources().getColor(R.color.black));
            }
        } else {
            this.nightSwitch.setChecked(false);
            RecyclerView recyclerView2 = this.mContentSectionView;
            if (recyclerView2 != null) {
                recyclerView2.setBackgroundColor(getContext().getResources().getColor(R.color.white));
            }
        }
        INightModeListener iNightModeListener = this.nightModeListener;
        if (iNightModeListener != null) {
            if (i == 1) {
                z = true;
            }
            iNightModeListener.onNightModeChanged(z);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        int i = sharedPreferenceProvider.get(Constants.PREF_REFERENCE_NIGHT_MODE + AuthenticationManager.getInstance(getContext()).getMaskedGuid(), 0);
        SharedPreferenceProvider sharedPreferenceProvider2 = SharedPreferenceProvider.get();
        int i2 = sharedPreferenceProvider2.get(Constants.PREF_REFERENCE_TEXT_SIZE_INDEX + AuthenticationManager.getInstance(getContext()).getMaskedGuid(), -1);
        if (arguments != null) {
            RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
            this.mContentSectionView = recyclerView;
            recyclerView.setHasFixedSize(true);
            LayoutManager layoutManager = new LayoutManager((Context) getActivity());
            this.mLayoutManager = layoutManager;
            layoutManager.canScrollVertically();
            ContentSectionDataAdapter contentSectionDataAdapter = this.mDataAdapter;
            if (contentSectionDataAdapter != null) {
                if (contentSectionDataAdapter instanceof ClinicalReferenceArticleContentDataAdapter) {
                    ClinicalReferenceArticleContentDataAdapter clinicalReferenceArticleContentDataAdapter = (ClinicalReferenceArticleContentDataAdapter) contentSectionDataAdapter;
                    this.mCRAContentAdapter = clinicalReferenceArticleContentDataAdapter;
                    if (i == 1) {
                        clinicalReferenceArticleContentDataAdapter.setNightMode(true);
                        this.mContentSectionView.setBackgroundColor(getContext().getResources().getColor(R.color.black));
                    } else {
                        clinicalReferenceArticleContentDataAdapter.setNightMode(false);
                        this.mContentSectionView.setBackgroundColor(getContext().getResources().getColor(R.color.white));
                    }
                    this.mCRAContentAdapter.setTextSizeIndex(i2);
                    this.mCRAContentAdapter.setLayoutManager(this.mLayoutManager);
                    this.mContentSectionView.setLayoutManager(this.mLayoutManager);
                    this.mContentSectionView.setAdapter(this.mCRAContentAdapter);
                } else {
                    contentSectionDataAdapter.setTextSizeIndex(i2);
                    this.mContentSectionView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    this.mContentSectionView.setAdapter(this.mDataAdapter);
                }
            }
        }
        new WBMDOmniturePaginationHandler((Activity) getActivity(), this.mContentSectionView, 1.0d, (WBMDPaginationListener) this);
        new InlineAdTouchHelper().applyTouchListener(this.mContentSectionView, false);
    }

    public static ClinicalReferenceArticleFragment newInstance(Bundle bundle) {
        ClinicalReferenceArticleFragment clinicalReferenceArticleFragment = new ClinicalReferenceArticleFragment();
        clinicalReferenceArticleFragment.setArguments(bundle);
        return clinicalReferenceArticleFragment;
    }

    public void setAdapter(ContentSectionDataAdapter contentSectionDataAdapter) {
        this.mDataAdapter = contentSectionDataAdapter;
    }

    public void onResume() {
        super.onResume();
        fillViews();
        if (getActivity() == null) {
            return;
        }
        if (getActivity() instanceof DrugMonographMainActivity) {
            DrugMonographMainActivity drugMonographMainActivity = (DrugMonographMainActivity) getActivity();
            OmnitureManager omnitureManager = OmnitureManager.get();
            String trackPageView = omnitureManager.trackPageView(drugMonographMainActivity, Constants.OMNITURE_CHANNEL_REFERENCE, "drug", "view", "" + drugMonographMainActivity.getContentId(), "98-header", drugMonographMainActivity.mOmnitureContentData, false, this.mPvid);
            this.mPvid = trackPageView;
            drugMonographMainActivity.setCurrentPvid(trackPageView);
        } else if (getActivity() instanceof ClinicalReferenceArticleActivity) {
            ((ClinicalReferenceArticleActivity) getActivity()).sendOmniturePing(this.mSectionNameForOmniture);
            this.mPvid = ((ClinicalReferenceArticleActivity) getActivity()).getCurrentPvid();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_content_setting) {
            handleTextOptions("close");
            return super.onOptionsItemSelected(menuItem);
        } else if (this.contentSettingsLayout.getVisibility() == 0) {
            handleTextOptions("close");
            return true;
        } else {
            this.contentSettingsLayout.setVisibility(0);
            OmnitureManager.get().trackModule(getContext(), Constants.OMNITURE_CHANNEL_REFERENCE, "ckb-drawer", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
            this.functionsLayout.animate().translationY(Util.dpToPixel(getContext(), 70)).setListener((Animator.AnimatorListener) null).start();
            return true;
        }
    }

    public void handleTextOptions(String str) {
        OmnitureManager.get().trackModule(getContext(), Constants.OMNITURE_CHANNEL_REFERENCE, "ckb-drawer", str, (Map<String, Object>) null);
        RelativeLayout relativeLayout = this.functionsLayout;
        if (relativeLayout != null) {
            relativeLayout.animate().translationY(Util.dpToPixel(getContext(), -70)).setListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    if (ClinicalReferenceArticleFragment.this.contentSettingsLayout != null) {
                        ClinicalReferenceArticleFragment.this.contentSettingsLayout.setVisibility(8);
                    }
                }
            }).start();
        }
    }

    public void sendOmniture(int i, int i2) {
        if (getActivity() != null && (getActivity() instanceof ClinicalReferenceArticleActivity)) {
            ClinicalReferenceArticleActivity clinicalReferenceArticleActivity = (ClinicalReferenceArticleActivity) getActivity();
            String buildSectionNameForPing = clinicalReferenceArticleActivity.buildSectionNameForPing(clinicalReferenceArticleActivity.getCurrentSectionIndex());
            HashMap hashMap = new HashMap();
            hashMap.put("wapp.pagination", Integer.toString(i2));
            OmnitureManager omnitureManager = OmnitureManager.get();
            omnitureManager.markModule("app-swipe", i + "", hashMap);
            OmnitureManager omnitureManager2 = OmnitureManager.get();
            Context context = getContext();
            this.mPvid = omnitureManager2.trackPageView(context, Constants.OMNITURE_CHANNEL_REFERENCE, "clin-ref-article/view/" + buildSectionNameForPing, (String) null, (String) null, (String) null, clinicalReferenceArticleActivity.mOmnitureContentData, false, this.mPvid);
        }
    }

    public void moveCurrentFind(boolean z) {
        if (isAdded()) {
            ContentSectionDataAdapter contentSectionDataAdapter = this.mDataAdapter;
            if (contentSectionDataAdapter instanceof ClinicalReferenceArticleContentDataAdapter) {
                this.mCurrentRow = ((ClinicalReferenceArticleContentDataAdapter) contentSectionDataAdapter).mCurrentFindItem.contentRow;
                ((ClinicalReferenceArticleContentDataAdapter) this.mDataAdapter).moveFindPosition(z);
                this.mContentSectionView.postDelayed(new Runnable(z) {
                    public final /* synthetic */ boolean f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ClinicalReferenceArticleFragment.this.lambda$moveCurrentFind$0$ClinicalReferenceArticleFragment(this.f$1);
                    }
                }, 100);
            }
        }
    }

    public /* synthetic */ void lambda$moveCurrentFind$0$ClinicalReferenceArticleFragment(boolean z) {
        int i = ((ClinicalReferenceArticleContentDataAdapter) this.mDataAdapter).mCurrentFindItem.contentRow;
        if (i < this.mLayoutManager.findFirstCompletelyVisibleItemPosition() || i > this.mLayoutManager.findLastCompletelyVisibleItemPosition()) {
            scrollToPosition(i);
        } else if (!z && this.mLayoutManager.findFirstCompletelyVisibleItemPosition() + 1 == i) {
            scrollToPosition(i);
        }
    }

    public void scrollToPosition(int i) {
        this.mLayoutManager.scrollToPosition(i);
        this.mContentSectionView.postDelayed(new Runnable((int) (-Util.dpToPixel(getActivity(), 45))) {
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ClinicalReferenceArticleFragment.this.lambda$scrollToPosition$1$ClinicalReferenceArticleFragment(this.f$1);
            }
        }, 10);
    }

    public /* synthetic */ void lambda$scrollToPosition$1$ClinicalReferenceArticleFragment(int i) {
        this.mContentSectionView.scrollBy(0, i);
    }

    public void setNightModeListener(INightModeListener iNightModeListener) {
        this.nightModeListener = iNightModeListener;
    }

    public void setSectionNameForOmniture(String str) {
        this.mSectionNameForOmniture = str;
    }

    public void clearFocusFromRecyclerView() {
        RecyclerView recyclerView = this.mContentSectionView;
        if (recyclerView != null) {
            recyclerView.clearFocus();
        }
    }
}
