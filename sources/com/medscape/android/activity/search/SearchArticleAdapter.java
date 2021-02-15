package com.medscape.android.activity.search;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.DownloadCarouselThumbnailTask;
import com.medscape.android.R;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.interfaces.RefreshableAdapter;
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.Util;
import java.util.List;
import java.util.Map;

public class SearchArticleAdapter extends ArrayAdapter<Article> implements RefreshableAdapter<Article> {
    private Context mContext;

    public void removeInlineAD() {
    }

    public void setInlineAD(NativeDFPAD nativeDFPAD) {
    }

    SearchArticleAdapter(Context context, List<Article> list) {
        super(context, 0, list);
        this.mContext = context;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (view == null) {
            view = View.inflate(this.mContext, R.layout.rss_article_row, viewGroup);
        }
        Article article = (Article) getItem(i);
        int i2 = 0;
        if ("nr".equals(article.mCategory)) {
            view.setClickable(false);
            view.setEnabled(false);
            view.setTag("noresults");
            view.setBackgroundColor(this.mContext.getResources().getColor(17170443));
            view.findViewById(R.id.header).setVisibility(8);
            view.findViewById(R.id.content).setVisibility(8);
            view.findViewById(R.id.divider).setVisibility(8);
            view.findViewById(R.id.arrow).setVisibility(8);
            view.findViewById(R.id.no_results).setVisibility(0);
            ((TextView) view.findViewById(R.id.no_results_message)).setText(article.mTitle);
        } else if ("spc".equals(article.mCategory)) {
            view.setTag(ShareConstants.WEB_DIALOG_PARAM_SUGGESTIONS);
            if (i == 0) {
                view.findViewById(R.id.didyoumean).setVisibility(0);
            } else {
                view.findViewById(R.id.didyoumean).setVisibility(4);
            }
            view.setBackgroundColor(this.mContext.getResources().getColor(17170443));
            view.findViewById(R.id.header).setVisibility(8);
            view.findViewById(R.id.content).setVisibility(8);
            view.findViewById(R.id.divider).setVisibility(8);
            view.findViewById(R.id.arrow).setVisibility(8);
            view.findViewById(R.id.spelling_suggestion).setVisibility(0);
            ((TextView) view.findViewById(R.id.suggestion)).setText(article.mTitle);
        } else {
            view.findViewById(R.id.header).setVisibility(8);
            view.findViewById(R.id.divider).setVisibility(8);
            view.findViewById(R.id.content).setBackgroundResource((article.mCellType != 1 || article.mLegacy) ? R.drawable.default_list_view : R.drawable.default_info_from_industry);
            int dpToPixel = (int) Util.dpToPixel(this.mContext, 5);
            view.findViewById(R.id.content).setPadding(dpToPixel, dpToPixel, dpToPixel, dpToPixel);
            StringBuilder sb = new StringBuilder();
            if (article.mLegacy) {
                str = "";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(article.mConfTag);
                sb2.append(article.mConfTag.length() == 0 ? "" : MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                str = sb2.toString();
            }
            sb.append(str);
            sb.append(article.mTitle);
            SpannableString spannableString = new SpannableString(sb.toString());
            if (article.mLegacy || article.mConfTag.length() <= 0) {
                ((TextView) view.findViewById(R.id.articleTitle)).setText(Html.fromHtml(article.mTitle));
            } else {
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#228B22"));
                if (article.mLegacy) {
                    str5 = "";
                } else {
                    str5 = article.mConfTag;
                }
                spannableString.setSpan(foregroundColorSpan, 0, str5.length(), 0);
                ((TextView) view.findViewById(R.id.articleTitle)).setText(spannableString);
            }
            TextView textView = (TextView) view.findViewById(R.id.articlePubDate);
            if (article.mLegacy) {
                str2 = "";
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(article.mPublication);
                if (article.mCellType == 1 || article.getDate().length() == 0) {
                    str3 = "";
                } else {
                    StringBuilder sb4 = new StringBuilder();
                    if (article.mPublication.length() > 0) {
                        str4 = ", ";
                    } else {
                        str4 = "";
                    }
                    sb4.append(str4);
                    sb4.append(article.getDate());
                    str3 = sb4.toString();
                }
                sb3.append(str3);
                str2 = sb3.toString();
            }
            textView.setText(str2);
            view.findViewById(R.id.articlePubDate).setVisibility(article.mLegacy ? 8 : 0);
            ImageView imageView = (ImageView) view.findViewById(R.id.logo);
            if (article.mArticleImage == null || article.mArticleImage.length() <= 0 || (!(i % 3 == 0 || article.mCellType == 1) || article.mLegacy)) {
                imageView.setVisibility(8);
                imageView.setImageBitmap((Bitmap) null);
            } else {
                imageView.setVisibility(0);
                imageView.setTag(article.mArticleImage);
                imageView.setImageResource(R.drawable.carousel_thumbnail_place_holder);
                new DownloadCarouselThumbnailTask(getContext(), (Map<String, Bitmap>) null).execute(new ImageView[]{imageView});
            }
            view.setTag("");
            View findViewById = view.findViewById(R.id.arrow);
            if (!article.mLegacy) {
                i2 = 8;
            }
            findViewById.setVisibility(i2);
        }
        return view;
    }

    public void refreshList(List<Article> list) {
        clear();
        super.addAll(list);
        notifyDataSetChanged();
    }

    public void addToList(Article article) {
        add(article);
    }
}
