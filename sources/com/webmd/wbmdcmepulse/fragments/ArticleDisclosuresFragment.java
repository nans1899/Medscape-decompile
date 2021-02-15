package com.webmd.wbmdcmepulse.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.squareup.picasso.Picasso;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.builders.HtmlBuilder;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;
import com.webmd.wbmdcmepulse.models.articles.Contributor;
import com.webmd.wbmdcmepulse.models.articles.ContributorComment;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import java.util.List;

public class ArticleDisclosuresFragment extends Fragment {
    private static Context mContext;
    private static List<Contributor> mContributors;
    private static FragmentManager mFragmentManager;
    private final String TAG = getClass().getSimpleName();
    private View mRootView;

    public static ArticleDisclosuresFragment newInstance(List<Contributor> list, FragmentManager fragmentManager) {
        ArticleDisclosuresFragment articleDisclosuresFragment = new ArticleDisclosuresFragment();
        mFragmentManager = fragmentManager;
        mContributors = list;
        return articleDisclosuresFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.cme_fragment_article_disclosures, viewGroup, false);
        mContext = getContext();
        setUpViewsForContributors();
        return this.mRootView;
    }

    private void setUpViewsForContributors() {
        LinearLayout linearLayout = (LinearLayout) this.mRootView.findViewById(R.id.disclosures_layout);
        LinearLayout viewsForContributors = getViewsForContributors(mContributors);
        if (viewsForContributors != null) {
            linearLayout.addView(viewsForContributors);
        }
    }

    private LinearLayout getViewsForContributors(List<Contributor> list) {
        if (list.size() <= 0) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(1);
        for (int size = list.size() - 1; size >= 0; size--) {
            Contributor contributor = list.get(size);
            ArticleCopyTextView articleCopyTextView = new ArticleCopyTextView(mContext);
            articleCopyTextView.setText(contributor.name);
            articleCopyTextView.setTextColor(getResources().getColor(R.color.black));
            articleCopyTextView.setTypeface(Typeface.DEFAULT_BOLD);
            linearLayout.addView(articleCopyTextView);
            ArticleCopyTextView articleCopyTextView2 = new ArticleCopyTextView(mContext);
            articleCopyTextView2.setText(contributor.contribType);
            articleCopyTextView2.setTextColor(getResources().getColor(R.color.black));
            if (!Extensions.isStringNullOrEmpty(contributor.image)) {
                ImageView imageView = new ImageView(mContext);
                imageView.setVisibility(0);
                imageView.setMinimumWidth(200);
                imageView.setMinimumHeight(300);
                imageView.setTag(contributor.image);
                imageView.setPadding(0, 15, 0, 0);
                Picasso.get().load(contributor.image).placeholder(R.drawable.cme_placeholder_image).into(imageView);
                linearLayout.addView(imageView);
            }
            if (contributor.comments.size() > 0) {
                StringBuilder sb = new StringBuilder();
                String str = "";
                String str2 = str;
                for (int i = 0; i < contributor.comments.size(); i++) {
                    ContributorComment contributorComment = contributor.comments.get(i);
                    if (contributorComment.title.equals("Title")) {
                        str = contributorComment.body;
                    } else if (contributorComment.title.equals("Disclosure")) {
                        str2 = contributorComment.body;
                    } else {
                        sb.append(contributorComment.body);
                    }
                }
                if (!Extensions.isStringNullOrEmpty(str)) {
                    ArticleCopyTextView articleCopyTextView3 = new ArticleCopyTextView(mContext);
                    articleCopyTextView3.setText(str);
                    articleCopyTextView3.setPadding(0, 30, 0, 0);
                    articleCopyTextView3.setTextColor(getResources().getColor(R.color.black));
                    linearLayout.addView(articleCopyTextView3);
                }
                if (!Extensions.isStringNullOrEmpty(str2)) {
                    ArticleCopyTextView articleCopyTextView4 = new ArticleCopyTextView(mContext);
                    articleCopyTextView4.setText(str2);
                    articleCopyTextView4.setPadding(0, 30, 0, 30);
                    articleCopyTextView4.setTextColor(getResources().getColor(R.color.black));
                    linearLayout.addView(articleCopyTextView4);
                }
                if (!Extensions.isStringNullOrEmpty(sb.toString())) {
                    ArticleCopyTextView articleCopyTextView5 = new ArticleCopyTextView(mContext);
                    articleCopyTextView5.setPadding(0, 0, 0, 30);
                    articleCopyTextView5.append(Html.fromHtml(HtmlBuilder.removeTrailingLineBreaks(sb.toString())));
                    articleCopyTextView2.setTextColor(getResources().getColor(R.color.black));
                    linearLayout.addView(articleCopyTextView5);
                }
            }
        }
        return linearLayout;
    }

    private String getColoredString(String str, int i) {
        return getColoredString(str, i, 4);
    }

    private String getColoredString(String str, int i, int i2) {
        return "<font size='" + i2 + "' color='" + i + "'>" + str + "</font>";
    }
}
