package com.medscape.android.pillid;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import java.util.ArrayList;
import java.util.Map;

public class ShapeFilterFragment extends Fragment {
    ArrayList<PillFilterItem> gridArray = new ArrayList<>();
    GridView gridView;
    OnFilterSelectedListener mListener;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnFilterSelectedListener) activity;
        } catch (ClassCastException unused) {
            throw new ClassCastException(activity.toString() + " must implement OnFilterSelectedListener");
        }
    }

    public void onCreate(Bundle bundle) {
        setHasOptionsMenu(true);
        super.onCreate(bundle);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_pill_identifier_filter, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActionBar supportActionBar = ((PillIdentifierActivity) getActivity()).getSupportActionBar();
        supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.pill_identifier_filter_shape_title) + "</font>"));
        this.gridArray.add(new PillFilterItem("Round", R.drawable.pill_id_shape_201, "201"));
        this.gridArray.add(new PillFilterItem("Oblong", R.drawable.pill_id_shape_202, "202"));
        this.gridArray.add(new PillFilterItem("Oval", R.drawable.pill_id_shape_203, "203"));
        this.gridArray.add(new PillFilterItem("Triangle", R.drawable.pill_id_shape_204, "204"));
        this.gridArray.add(new PillFilterItem("Square", R.drawable.pill_id_shape_205, "205"));
        this.gridArray.add(new PillFilterItem("Rectangle", R.drawable.pill_id_shape_206, "206"));
        this.gridArray.add(new PillFilterItem("Diamond", R.drawable.pill_id_shape_207, "207"));
        this.gridArray.add(new PillFilterItem("5 Sided", R.drawable.pill_id_shape_208, "208"));
        this.gridArray.add(new PillFilterItem("6+ Sided", R.drawable.pill_id_shape_209, "209"));
        this.gridArray.add(new PillFilterItem("Other", R.drawable.pill_id_shape_210, "210"));
        GridView gridView2 = (GridView) getView().findViewById(R.id.gridView1);
        this.gridView = gridView2;
        gridView2.setAdapter(new ArrayAdapter<PillFilterItem>(getActivity(), R.layout.pillid_filter_item, this.gridArray) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                RecordHolder recordHolder;
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(Build.VERSION.SDK_INT < 14 ? R.layout.pillid_filter_item_sdk10 : R.layout.pillid_filter_item, viewGroup, false);
                    recordHolder = new RecordHolder();
                    recordHolder.title = (TextView) view.findViewById(R.id.title);
                    recordHolder.image = (ImageView) view.findViewById(R.id.image);
                    view.setTag(recordHolder);
                } else {
                    recordHolder = (RecordHolder) view.getTag();
                }
                PillFilterItem pillFilterItem = ShapeFilterFragment.this.gridArray.get(i);
                recordHolder.title.setText(pillFilterItem.mTitle);
                recordHolder.image.setImageResource(pillFilterItem.mResId);
                recordHolder.itemId = "" + pillFilterItem.mResId;
                recordHolder.value = pillFilterItem.mId;
                return view;
            }
        });
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                OmnitureManager.get().markModule("pillfilter", FilterType.SHAPE.getName().toLowerCase(), (Map<String, Object>) null);
                ShapeFilterFragment.this.mListener.onFilterSelected(FilterType.SHAPE, ((RecordHolder) view.getTag()).itemId, ((RecordHolder) view.getTag()).value);
            }
        });
        ((BaseActivity) getActivity()).setCurrentPvid(OmnitureManager.get().trackPageView(getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, "pill-identifier", "view", (String) null, "shape", (Map<String, Object>) null));
    }

    public class RecordHolder {
        ImageView image;
        String itemId;
        TextView title;
        String value;

        public RecordHolder() {
        }
    }

    public class PillFilterItem {
        String mId;
        int mResId;
        String mTitle;

        public PillFilterItem(String str, int i, String str2) {
            this.mTitle = str;
            this.mResId = i;
            this.mId = str2;
        }
    }

    public static ShapeFilterFragment newInstance() {
        return new ShapeFilterFragment();
    }
}
