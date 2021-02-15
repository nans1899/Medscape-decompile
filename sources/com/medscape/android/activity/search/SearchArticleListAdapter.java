package com.medscape.android.activity.search;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.medscape.android.R;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.ads.ShareThroughNativeADViewHolder;
import com.medscape.android.interfaces.RefreshableAdapter;
import com.medscape.android.parser.model.Article;
import com.medscape.android.search.SearchMsgViewHolder;
import com.medscape.android.util.Util;
import java.util.List;

public class SearchArticleListAdapter extends ArrayAdapter<Article> implements RefreshableAdapter<Article> {
    Activity mActivity;
    Article mInlineADItem;
    int mSearchMode;
    NativeDFPAD mShareThroughAd = null;

    public SearchArticleListAdapter(Activity activity, List<Article> list, int i) {
        super(activity, 0, list);
        this.mActivity = activity;
        this.mSearchMode = i;
    }

    public void refreshList(List<Article> list) {
        clear();
        if (Build.VERSION.SDK_INT >= 11) {
            super.addAll(list);
        } else {
            for (Article add : list) {
                super.add(add);
            }
        }
        notifyDataSetChanged();
    }

    public void addToList(Article article) {
        add(article);
    }

    public void removeInlineAD() {
        if (this.mInlineADItem != null) {
            this.mShareThroughAd = null;
            notifyDataSetChanged();
        }
    }

    public void setInlineAD(NativeDFPAD nativeDFPAD) {
        this.mShareThroughAd = nativeDFPAD;
        notifyDataSetChanged();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Article article = (Article) getItem(i);
        if (article != null) {
            if (article.mIsInlineAD) {
                this.mInlineADItem = article;
                View inflate = View.inflate(this.mActivity, R.layout.sharethrough_srch_inline_ad, (ViewGroup) null);
                ShareThroughNativeADViewHolder shareThroughNativeADViewHolder = new ShareThroughNativeADViewHolder(inflate);
                if (inflate != null) {
                    shareThroughNativeADViewHolder.applyPadding(Util.dpToPixel(inflate.getContext(), 10));
                }
                NativeDFPAD nativeDFPAD = this.mShareThroughAd;
                if (nativeDFPAD == null) {
                    return inflate;
                }
                shareThroughNativeADViewHolder.onBind(nativeDFPAD, false);
                return inflate;
            } else if (article.mSearchSuggestionMsg != null) {
                return new SearchMsgViewHolder(View.inflate(this.mActivity, R.layout.search_user_msg_item, (ViewGroup) null), article.mSearchSuggestionMsg, this.mActivity, this.mSearchMode).bindView();
            } else {
                if (view == null || view.findViewById(R.id.list_title) == null) {
                    view = View.inflate(this.mActivity, R.layout.search_result_item, (ViewGroup) null);
                }
                View findViewById = view.findViewById(R.id.body_layout);
                TextView textView = (TextView) view.findViewById(R.id.list_title);
                View findViewById2 = view.findViewById(R.id.arrow);
                TextView textView2 = (TextView) view.findViewById(R.id.content_type_date);
                if (article != null) {
                    textView.setText(article.mTitle);
                    textView2.setText(article.mContentTypeDate);
                    findViewById.setVisibility(0);
                    if (article.mCategory.equalsIgnoreCase("nr")) {
                        findViewById2.setVisibility(8);
                        return view;
                    }
                    findViewById2.setVisibility(0);
                    return view;
                }
                findViewById.setVisibility(8);
                return view;
            }
        } else if (view == null) {
            return view;
        } else {
            view.setVisibility(8);
            return view;
        }
    }
}
