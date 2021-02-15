package com.webmd.wbmdlanguages.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.webmd.wbmdlanguages.R;
import com.webmd.wbmdlanguages.models.LanguageModel;

public abstract class LanguageListItemBinding extends ViewDataBinding {
    public final ImageView logo;
    @Bindable
    protected LanguageModel mLanguageModel;
    public final TextView rowTitle;

    public abstract void setLanguageModel(LanguageModel languageModel);

    protected LanguageListItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.logo = imageView;
        this.rowTitle = textView;
    }

    public LanguageModel getLanguageModel() {
        return this.mLanguageModel;
    }

    public static LanguageListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LanguageListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LanguageListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.language_list_item, viewGroup, z, obj);
    }

    public static LanguageListItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LanguageListItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LanguageListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.language_list_item, (ViewGroup) null, false, obj);
    }

    public static LanguageListItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LanguageListItemBinding bind(View view, Object obj) {
        return (LanguageListItemBinding) bind(obj, view, R.layout.language_list_item);
    }
}
