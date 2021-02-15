package com.medscape.android.pillid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.drugs.model.DrugsContract;
import com.medscape.android.pillid.PillSearchResultsPreviewFragment;
import com.medscape.android.view.CacheImageView;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PillDetailFragment extends Fragment {
    static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    ArrayList<ListItemView> mList;
    /* access modifiers changed from: private */
    public PillSearchResultsPreviewFragment.PillSearchResultItem mPillDetails;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.layout_pill_detail, viewGroup, false);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initializeList();
    }

    public void initializeList() {
        ArrayList<ListItemView> arrayList = new ArrayList<>();
        this.mList = arrayList;
        PillSearchResultsPreviewFragment.PillSearchResultItem pillSearchResultItem = this.mPillDetails;
        if (pillSearchResultItem != null) {
            arrayList.add(new ListItemView(DrugsContract.Drug.GENERIC, pillSearchResultItem.mGenericName));
            this.mList.add(new ListItemView("Imprint", this.mPillDetails.mImprint));
            this.mList.add(new ListItemView("Strength", this.mPillDetails.mStrength));
            this.mList.add(new ListItemView("Shape", this.mPillDetails.mShapeNames));
            this.mList.add(new ListItemView("Color", this.mPillDetails.mColorNames));
            this.mList.add(new ListItemView("Form", this.mPillDetails.mFormNames));
            this.mList.add(new ListItemView("Manufacturer", this.mPillDetails.mManufacturer));
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActionBar supportActionBar = ((PillIdentifierActivity) getActivity()).getSupportActionBar();
        supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + this.mPillDetails.mGenericName + "</font>"));
        ListView listView = (ListView) getView().findViewById(R.id.list);
        listView.setAdapter(new SimpleListAdapter(getActivity(), R.layout.pill_detail_list_item, this.mList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0 && PillDetailFragment.this.mPillDetails.mMedscapeDrugId > 0) {
                    Intent intent = new Intent(PillDetailFragment.this.getActivity(), DrugMonographMainActivity.class);
                    intent.putExtra(Constants.EXTRA_CONTENT_ID, PillDetailFragment.this.mPillDetails.mMedscapeDrugId);
                    intent.putExtra("drugName", PillDetailFragment.this.mPillDetails.mGenericName);
                    PillDetailFragment.this.getActivity().startActivity(intent);
                }
            }
        });
        CacheImageView cacheImageView = (CacheImageView) getView().findViewById(R.id.image);
        if (this.mPillDetails.mImageUrl != null) {
            cacheImageView.configure(this.mPillDetails.mImageUrl, R.drawable.placeholder_image);
        } else {
            cacheImageView.setImageResource(R.drawable.drug_image_unavailable2x);
        }
    }

    public void sendOmniturePing() {
        Fragment findFragmentById;
        if (this.mPillDetails != null && (findFragmentById = getActivity().getSupportFragmentManager().findFragmentById(R.id.container)) != null && (findFragmentById instanceof PillDetailFragment) && !findFragmentById.isRemoving()) {
            OmnitureManager omnitureManager = OmnitureManager.get();
            FragmentActivity activity = getActivity();
            ((BaseActivity) getActivity()).setCurrentPvid(omnitureManager.trackPageView(activity, Constants.OMNITURE_CHANNEL_REFERENCE, "pill-identifier", "view", "result", "" + this.mPillDetails.mMedscapeDrugId, (Map<String, Object>) null));
        }
    }

    public void onResume() {
        super.onResume();
        sendOmniturePing();
    }

    public class ViewHolder {
        TextView detail;
        TextView name;

        public ViewHolder() {
        }
    }

    public class ListItemView {
        String mDetail;
        String mName;

        public ListItemView(String str, String str2) {
            this.mName = str;
            this.mDetail = str2;
        }
    }

    public static PillDetailFragment newInstance(PillSearchResultsPreviewFragment.PillSearchResultItem pillSearchResultItem) {
        PillDetailFragment pillDetailFragment = new PillDetailFragment();
        pillDetailFragment.mPillDetails = pillSearchResultItem;
        return pillDetailFragment;
    }

    public class SimpleListAdapter extends ArrayAdapter<ListItemView> {
        Context context;
        ArrayList<ListItemView> items;
        int layoutResourceId;

        public SimpleListAdapter(Context context2, int i, ArrayList<ListItemView> arrayList) {
            super(context2, i, arrayList);
            this.layoutResourceId = i;
            this.context = context2;
            this.items = arrayList;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.pill_detail_list_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.name);
                viewHolder.detail = (TextView) view.findViewById(R.id.detail);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            ListItemView item = getItem(i);
            if (i == 0 && PillDetailFragment.this.mPillDetails.mMedscapeDrugId > 0) {
                viewHolder.detail.setTextColor(Color.parseColor("#005982"));
            }
            viewHolder.name.setText(item.mName);
            viewHolder.detail.setText(item.mDetail);
            return view;
        }

        public int getCount() {
            return this.items.size();
        }

        public ListItemView getItem(int i) {
            return this.items.get(i);
        }
    }
}
