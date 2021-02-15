package com.medscape.android.contentviewer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.contentviewer.ContentSectionDataAdapter;

public class DrugClassesFragment extends Fragment {
    ContentSectionDataAdapter mDataAdapter;
    RecyclerView mRecyclerView;
    View rootView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_drug_classes, viewGroup, false);
        setUpRecyclerView();
        return this.rootView;
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) this.rootView.findViewById(R.id.drug_classes_recycler_view);
        this.mRecyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mRecyclerView.setAdapter(this.mDataAdapter);
    }

    public void setDataAdapter(ContentSectionDataAdapter contentSectionDataAdapter) {
        this.mDataAdapter = contentSectionDataAdapter;
        contentSectionDataAdapter.setTextSelectable(false);
        this.mDataAdapter.setShowArrow(true);
    }

    public static DrugClassesFragment newInstance(Bundle bundle) {
        DrugClassesFragment drugClassesFragment = new DrugClassesFragment();
        drugClassesFragment.setArguments(bundle);
        return drugClassesFragment;
    }
}
