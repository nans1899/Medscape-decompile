package com.webmd.wbmdcmepulse.customviews;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.core.content.ContextCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CmeWebActivity;
import com.webmd.wbmdcmepulse.builders.HtmlBuilder;
import com.webmd.wbmdcmepulse.models.articles.HtmlListItem;
import com.webmd.wbmdcmepulse.models.parsers.articles.ContentValueTypePair;
import com.webmd.wbmdcmepulse.models.parsers.articles.HtmlParser;
import com.webmd.wbmdcmepulse.models.parsers.articles.ListItem;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlPullParserException;

public class HtmlListView extends TableLayout {
    public static final String BULLET_SYMBOL_ENCODING = "&#8226;";
    /* access modifiers changed from: private */
    public static final String TAG = HtmlListView.class.getSimpleName();
    public static final String TYPE_ORDER = "order";
    public static final String TYPE_UN_ORDERDED = "unordered";
    int mColor;
    /* access modifiers changed from: private */
    public Context mContext;
    private List<String> mListItems;
    private String mListType;

    public HtmlListView(Context context) {
        super(context);
        this.mContext = context;
        this.mColor = ContextCompat.getColor(context, R.color.black);
    }

    public HtmlListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mColor = ContextCompat.getColor(context, R.color.black);
    }

    public void setListType(String str) {
        this.mListType = str;
    }

    public void inflate(List<HtmlListItem> list) {
        this.mColor = ContextCompat.getColor(this.mContext, R.color.general_grey);
        for (HtmlListItem next : list) {
            TableRow tableRow = (TableRow) inflateListItem(next);
            tableRow.setPadding(next.depth * 50, 0, 0, 0);
            addView(tableRow);
        }
    }

    public void inflateSubLists(ArrayList<ContentValueTypePair> arrayList) {
        this.mColor = ContextCompat.getColor(this.mContext, R.color.general_grey);
        Iterator<ContentValueTypePair> it = arrayList.iterator();
        while (it.hasNext()) {
            ListItem listItem = (ListItem) it.next().getValue();
            TableRow tableRow = (TableRow) inflateSubListItem(listItem, listItem.getOrder());
            tableRow.setPadding(listItem.getDepth() * 50, 0, 0, 0);
            addView(tableRow);
        }
    }

    private View inflateSubListItem(ListItem listItem, int i) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.cme_html_list_item, this, false);
        ArticleCopyTextView articleCopyTextView = (ArticleCopyTextView) inflate.findViewById(R.id.item_marker);
        articleCopyTextView.setTextColor(this.mColor);
        if (listItem.getType().equalsIgnoreCase("ul")) {
            articleCopyTextView.setText(Html.fromHtml("&#8226;"));
        } else {
            articleCopyTextView.setText(Html.fromHtml(i + ". "));
        }
        ArticleCopyTextView articleCopyTextView2 = (ArticleCopyTextView) inflate.findViewById(R.id.item_text);
        articleCopyTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        articleCopyTextView2.setText(HtmlBuilder.removeTrailingLineBreaks(Utilities.getFormattedText(listItem.getText())));
        articleCopyTextView2.setTextColor(this.mColor);
        ViewGroup viewGroup = (ViewGroup) inflate.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(inflate);
        }
        return inflate;
    }

    public void inflate(List<String> list, String str) {
        this.mListItems = list;
        this.mListType = str;
        this.mColor = ContextCompat.getColor(this.mContext, R.color.general_grey);
        int i = 1;
        for (String inflateListItem : this.mListItems) {
            TableRow tableRow = (TableRow) inflateListItem(inflateListItem, i);
            tableRow.setPadding(0, 0, 0, 0);
            addView(tableRow);
            tableRow.getLayoutParams().width = -2;
            i++;
        }
    }

    private View inflateListItem(HtmlListItem htmlListItem) {
        ViewGroup viewGroup;
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.cme_html_list_item, this, false);
        ArticleCopyTextView articleCopyTextView = (ArticleCopyTextView) inflate.findViewById(R.id.item_marker);
        articleCopyTextView.setTextColor(this.mColor);
        articleCopyTextView.setText(Html.fromHtml(htmlListItem.marker));
        ArticleCopyTextView articleCopyTextView2 = (ArticleCopyTextView) inflate.findViewById(R.id.item_text);
        articleCopyTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        articleCopyTextView2.setText(HtmlBuilder.removeTrailingLineBreaks(Utilities.getFormattedText(htmlListItem.value)));
        articleCopyTextView2.setTextColor(this.mColor);
        if (!(inflate == null || (viewGroup = (ViewGroup) inflate.getParent()) == null)) {
            viewGroup.removeView(inflate);
        }
        return inflate;
    }

    private View inflateListItem(String str, int i) {
        String str2;
        ViewGroup viewGroup;
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.cme_html_list_item, this, false);
        List<String> list = null;
        if (!str.contains("<list") || !str.contains("</list>")) {
            str2 = null;
        } else {
            str2 = Extensions.getSubstring(str, "<list", "</list>");
            try {
                list = new HtmlParser().parseHtmlList(str2, Constants.XML_LIST, Constants.XML_LIST_ITEM);
            } catch (IOException e) {
                Trace.e(TAG, e.getMessage());
            } catch (XmlPullParserException e2) {
                Trace.e(TAG, e2.getMessage());
            }
        }
        ArticleCopyTextView articleCopyTextView = (ArticleCopyTextView) inflate.findViewById(R.id.item_marker);
        if (this.mListType.equals("order") || this.mListType.equals("ol")) {
            articleCopyTextView.setText(String.valueOf(i) + ".");
        } else if (this.mListType.equals(TYPE_UN_ORDERDED) || this.mListType.equals("ul")) {
            articleCopyTextView.setText(HtmlBuilder.removeTrailingLineBreaks(Html.fromHtml("&#8226;")));
        } else {
            articleCopyTextView.setVisibility(8);
        }
        ArticleCopyTextView articleCopyTextView2 = (ArticleCopyTextView) inflate.findViewById(R.id.item_text);
        articleCopyTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        if (list != null) {
            if (!Extensions.isStringNullOrEmpty(str2)) {
                articleCopyTextView2.setText(makeLinkClickable(Utilities.getFormattedText(str.replace(str2, "")), "Abstract"));
            }
            for (String str3 : list) {
                articleCopyTextView2.append(IOUtils.LINE_SEPARATOR_UNIX);
                articleCopyTextView2.append(HtmlBuilder.removeTrailingLineBreaks(Html.fromHtml("&#8226;")) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str3);
            }
        } else {
            String replace = HtmlParser.unescapeHtml(str).replace("/medline/abstract/", Utilities.generateEnvironment(getContext(), "abstract://www%s.medscape.org/medline/abstract/"));
            articleCopyTextView2.setAutoLinkMask(0);
            articleCopyTextView2.setClickable(true);
            Linkify.addLinks(articleCopyTextView2, Patterns.WEB_URL, "abstract://");
            articleCopyTextView2.setText(Html.fromHtml(replace));
        }
        articleCopyTextView2.setTextColor(this.mColor);
        articleCopyTextView.setTextColor(this.mColor);
        if (!(inflate == null || (viewGroup = (ViewGroup) inflate.getParent()) == null)) {
            viewGroup.removeView(inflate);
        }
        return inflate;
    }

    private SpannableStringBuilder makeLinkClickable(Spanned spanned, String str) {
        String removeTrailingLineBreaks = HtmlBuilder.removeTrailingLineBreaks(spanned);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(removeTrailingLineBreaks);
        for (URLSpan handleLinkClick : (URLSpan[]) spannableStringBuilder.getSpans(0, removeTrailingLineBreaks.length(), URLSpan.class)) {
            handleLinkClick(spannableStringBuilder, handleLinkClick, str);
        }
        return spannableStringBuilder;
    }

    private void handleLinkClick(SpannableStringBuilder spannableStringBuilder, final URLSpan uRLSpan, final String str) {
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                String access$000 = HtmlListView.TAG;
                Trace.i(access$000, "Clicked: " + uRLSpan.getURL());
                String replace = uRLSpan.getURL().replace("ref://", "").replace("/medline/abstract/", Utilities.generateEnvironment(HtmlListView.this.mContext, "https://www%s.medscape.org/medline/abstract/"));
                String access$0002 = HtmlListView.TAG;
                Trace.i(access$0002, "Parsed: " + replace);
                Intent intent = new Intent(HtmlListView.this.mContext, CmeWebActivity.class);
                intent.putExtra(Constants.WEB_VIEW_URL_KEY, replace);
                intent.putExtra(Constants.WEB_VIEW_TITLE_KEY, str);
                HtmlListView.this.mContext.startActivity(intent);
            }
        }, spannableStringBuilder.getSpanStart(uRLSpan), spannableStringBuilder.getSpanEnd(uRLSpan), spannableStringBuilder.getSpanFlags(uRLSpan));
        spannableStringBuilder.removeSpan(uRLSpan);
    }

    public void setColor(int i) {
        this.mColor = ContextCompat.getColor(this.mContext, i);
    }
}
