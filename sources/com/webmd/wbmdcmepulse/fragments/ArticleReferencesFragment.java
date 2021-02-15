package com.webmd.wbmdcmepulse.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.customviews.HtmlListView;
import com.webmd.wbmdcmepulse.models.articles.References;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import java.util.Arrays;
import java.util.List;

public class ArticleReferencesFragment extends DialogFragment {
    private final String TAG = getClass().getSimpleName();
    private Context mContext;
    private List<String> mReferences;
    private View mRootView;
    private String mTitle;
    private String mType;

    public static ArticleReferencesFragment newInstance(String[] strArr, String str) {
        ArticleReferencesFragment articleReferencesFragment = new ArticleReferencesFragment();
        articleReferencesFragment.mReferences = Arrays.asList(strArr);
        articleReferencesFragment.mType = HtmlListView.TYPE_UN_ORDERDED;
        articleReferencesFragment.mTitle = str;
        return articleReferencesFragment;
    }

    public static ArticleReferencesFragment newInstance(References references) {
        ArticleReferencesFragment articleReferencesFragment = new ArticleReferencesFragment();
        articleReferencesFragment.mReferences = references.content;
        articleReferencesFragment.mType = references.displayType;
        return articleReferencesFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cme_fragment_article_references, viewGroup, false);
        this.mRootView = inflate;
        this.mContext = getContext();
        ((HtmlListView) inflate.findViewById(R.id.references_lv)).inflate(this.mReferences, this.mType);
        getDialog().setCanceledOnTouchOutside(true);
        return this.mRootView;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        if (!Extensions.isStringNullOrEmpty(this.mTitle)) {
            onCreateDialog.setTitle(this.mTitle);
        }
        return onCreateDialog;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialogInterface);
        }
    }
}
