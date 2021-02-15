package com.medscape.android.pillid;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

public class ColorFilterFragment extends Fragment {
    ArrayList<ColorFilterItem> gridArray = new ArrayList<>();
    GridView gridView;
    /* access modifiers changed from: private */
    public OnFilterSelectedListener mListener;

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
        supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.pill_identifier_filter_color_title) + "</font>"));
        this.gridView = (GridView) getView().findViewById(R.id.gridView1);
        setUpGridview();
    }

    private void setUpGridview() {
        this.gridArray.add(new ColorFilterItem("White", "#ffffff", "301"));
        this.gridArray.add(new ColorFilterItem("Off White", "#fdeddb", "302"));
        this.gridArray.add(new ColorFilterItem("Clear", "#fbfbfb", "303"));
        this.gridArray.add(new ColorFilterItem("Gray", "#acacac", "304"));
        this.gridArray.add(new ColorFilterItem("Black", "#000000", "305"));
        this.gridArray.add(new ColorFilterItem("Tan", "#d9af7f", "306"));
        this.gridArray.add(new ColorFilterItem("Brown", "#754c24", "307"));
        this.gridArray.add(new ColorFilterItem("Red", "#ed1c24", "308"));
        this.gridArray.add(new ColorFilterItem("Pink", "#f5989d", "309"));
        this.gridArray.add(new ColorFilterItem("Orange", "#f26522", "310"));
        this.gridArray.add(new ColorFilterItem("Peach", "#f9ad81", "311"));
        this.gridArray.add(new ColorFilterItem("Yellow", "#fff200", "312"));
        this.gridArray.add(new ColorFilterItem("Green", "#39b54a", "313"));
        this.gridArray.add(new ColorFilterItem("Blue", "#0072bc", "314"));
        this.gridArray.add(new ColorFilterItem("Purple", "#a864a8", "315"));
        this.gridView.setAdapter(new ArrayAdapter<ColorFilterItem>(getActivity(), R.layout.pillid_filter_blank_item, this.gridArray) {
            private String doMatch(RecordHolder recordHolder, ColorFilterItem colorFilterItem) {
                return "";
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                RecordHolder recordHolder;
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(Build.VERSION.SDK_INT < 14 ? R.layout.pillid_filter_blank_item_sdk10 : R.layout.pillid_filter_blank_item, viewGroup, false);
                    recordHolder = new RecordHolder();
                    recordHolder.title = (TextView) view.findViewById(R.id.title);
                    recordHolder.text = (TextView) view.findViewById(R.id.clearText);
                    recordHolder.image = (ImageView) view.findViewById(R.id.image);
                    view.setTag(recordHolder);
                } else {
                    recordHolder = (RecordHolder) view.getTag();
                }
                ColorFilterItem colorFilterItem = ColorFilterFragment.this.gridArray.get(i);
                TextView textView = recordHolder.title;
                textView.setText(colorFilterItem.mTitle + doMatch(recordHolder, colorFilterItem));
                recordHolder.text.setVisibility(8);
                if (colorFilterItem.mColorHex.equals("#ffffff")) {
                    recordHolder.image.setImageResource(R.drawable.pill_color_picker_ring);
                } else if (colorFilterItem.mColorHex.equals("#fbfbfb")) {
                    recordHolder.image.setImageResource(R.drawable.pill_color_picker_ring);
                    recordHolder.text.setVisibility(0);
                } else {
                    recordHolder.image.setImageResource(R.drawable.pill_color_picker_shape);
                    recordHolder.image.getDrawable().setColorFilter(Color.parseColor(colorFilterItem.mColorHex), PorterDuff.Mode.SRC_ATOP);
                }
                recordHolder.itemId = colorFilterItem.mColorHex;
                recordHolder.value = colorFilterItem.mId;
                return view;
            }
        });
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                OmnitureManager.get().markModule("pillfilter", FilterType.COLOR.getName().toLowerCase(), (Map<String, Object>) null);
                ColorFilterFragment.this.mListener.onFilterSelected(FilterType.COLOR, ((RecordHolder) view.getTag()).itemId, ((RecordHolder) view.getTag()).value);
            }
        });
        ((BaseActivity) getActivity()).setCurrentPvid(OmnitureManager.get().trackPageView(getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, "pill-identifier", "view", (String) null, "color", (Map<String, Object>) null));
    }

    public class RecordHolder {
        ImageView image;
        String itemId;
        TextView text;
        TextView title;
        String value;

        public RecordHolder() {
        }
    }

    public class ColorFilterItem {
        String mColorHex;
        String mId;
        String mTitle;

        public ColorFilterItem(String str, String str2, String str3) {
            this.mTitle = str;
            this.mColorHex = str2;
            this.mId = str3;
        }
    }

    public static ColorFilterFragment newInstance() {
        return new ColorFilterFragment();
    }
}
