package com.medscape.android.reference.interfaces;

import android.view.View;
import com.medscape.android.reference.model.ClinicalReference;
import com.medscape.android.reference.model.ClinicalReferenceArticle;

public interface IFolderItemClickCallback {
    void onItemClicked(ClinicalReference clinicalReference, int i, View view);

    void onItemClicked(ClinicalReferenceArticle clinicalReferenceArticle, int i, View view);
}
