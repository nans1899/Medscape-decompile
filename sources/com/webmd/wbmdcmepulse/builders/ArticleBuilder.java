package com.webmd.wbmdcmepulse.builders;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CmeImageViewerActivity;
import com.webmd.wbmdcmepulse.customviews.HtmlListView;
import com.webmd.wbmdcmepulse.models.articles.Graphic;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import com.webmd.wbmdcmepulse.models.articles.HtmlRowItem;
import com.webmd.wbmdcmepulse.models.articles.HtmlTable;
import com.webmd.wbmdcmepulse.models.articles.HtmlTableRow;
import com.webmd.wbmdcmepulse.models.articles.Slide;
import com.webmd.wbmdcmepulse.models.interfaces.IImageLoadedEvent;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import com.webmd.wbmdcmepulse.models.parsers.articles.HtmlParser;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

public class ArticleBuilder extends HtmlBuilder {
    private static final String TAG = ArticleBuilder.class.getSimpleName();

    public static HtmlTable convertHtmlObjectToTable(HtmlObject htmlObject) {
        try {
            return new HtmlParser().parseHtmlTable(initalizeXmlParser(htmlObject.content));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static TableLayout buildTableLayout(HtmlObject htmlObject, Context context, IImageLoadedEvent iImageLoadedEvent) {
        Iterator<HtmlRowItem> it;
        Iterator<HtmlTableRow> it2;
        int i;
        String str;
        String str2;
        final Context context2 = context;
        TableLayout tableLayout = new TableLayout(context2);
        float f = 100.0f;
        tableLayout.setWeightSum(100.0f);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 15);
        tableLayout.setLayoutParams(layoutParams);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);
        HtmlTable convertHtmlObjectToTable = convertHtmlObjectToTable(htmlObject);
        if (convertHtmlObjectToTable != null) {
            Iterator<HtmlTableRow> it3 = convertHtmlObjectToTable.tableRows.iterator();
            while (it3.hasNext()) {
                HtmlTableRow next = it3.next();
                if (next != null) {
                    TableRow tableRow = new TableRow(context2);
                    tableRow.setWeightSum(f);
                    if (next.getRowType().equals(HtmlTableRow.ROW_TYPE_TABLE_HEADER)) {
                        i = R.drawable.table_header_row;
                    } else {
                        i = R.drawable.table_row_body;
                    }
                    int i2 = i;
                    Drawable drawable = ContextCompat.getDrawable(context2, i2);
                    Iterator<HtmlRowItem> it4 = next.getRowItems().iterator();
                    while (it4.hasNext()) {
                        HtmlRowItem next2 = it4.next();
                        String str3 = "fig";
                        if (next2.value.contains(str3)) {
                            String str4 = "graphic";
                            if (next2.value.contains(str4)) {
                                String[] split = next2.value.split("<break ></break>");
                                int i3 = 0;
                                while (i3 < split.length) {
                                    if (!split[i3].contains(str3) || !split[i3].contains(str4)) {
                                        str2 = str4;
                                        it2 = it3;
                                        it = it4;
                                        str = str3;
                                        IImageLoadedEvent iImageLoadedEvent2 = iImageLoadedEvent;
                                        TextView textView = new TextView(context2);
                                        TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams(-2, -1);
                                        layoutParams2.span = 100 / next.getRowItems().size();
                                        textView.setLayoutParams(layoutParams2);
                                        if (next.getRowType().equals(HtmlTableRow.ROW_TYPE_TABLE_HEADER)) {
                                            textView.setTextColor(-1);
                                        } else {
                                            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                        }
                                        textView.setText(Html.fromHtml(next2.value));
                                        if (Build.VERSION.SDK_INT >= 16) {
                                            textView.setBackground(drawable);
                                        } else {
                                            textView.setBackgroundResource(i2);
                                        }
                                        tableRow.addView(textView);
                                    } else {
                                        try {
                                            final Graphic Parse = new GraphicParser(split[i3]).Parse();
                                            if (!Extensions.isStringNullOrEmpty(Parse.label)) {
                                                TextView textView2 = new TextView(context2);
                                                str2 = str4;
                                                try {
                                                    StringBuilder sb = new StringBuilder();
                                                    it2 = it3;
                                                    try {
                                                        sb.append("<p><strong>");
                                                        sb.append(Parse.label);
                                                        sb.append("</strong></p>");
                                                        textView2.setText(Html.fromHtml(sb.toString()));
                                                        it = it4;
                                                    } catch (XmlPullParserException e) {
                                                        e = e;
                                                        it = it4;
                                                        str = str3;
                                                        IImageLoadedEvent iImageLoadedEvent3 = iImageLoadedEvent;
                                                        Trace.e(TAG, e.getMessage());
                                                        i3++;
                                                        str4 = str2;
                                                        it3 = it2;
                                                        it4 = it;
                                                        str3 = str;
                                                    } catch (IOException e2) {
                                                        e = e2;
                                                        it = it4;
                                                        str = str3;
                                                        IImageLoadedEvent iImageLoadedEvent4 = iImageLoadedEvent;
                                                        Trace.e(TAG, e.getMessage());
                                                        i3++;
                                                        str4 = str2;
                                                        it3 = it2;
                                                        it4 = it;
                                                        str3 = str;
                                                    }
                                                } catch (XmlPullParserException e3) {
                                                    e = e3;
                                                    it2 = it3;
                                                    it = it4;
                                                    str = str3;
                                                    IImageLoadedEvent iImageLoadedEvent32 = iImageLoadedEvent;
                                                    Trace.e(TAG, e.getMessage());
                                                    i3++;
                                                    str4 = str2;
                                                    it3 = it2;
                                                    it4 = it;
                                                    str3 = str;
                                                } catch (IOException e4) {
                                                    e = e4;
                                                    it2 = it3;
                                                    it = it4;
                                                    str = str3;
                                                    IImageLoadedEvent iImageLoadedEvent42 = iImageLoadedEvent;
                                                    Trace.e(TAG, e.getMessage());
                                                    i3++;
                                                    str4 = str2;
                                                    it3 = it2;
                                                    it4 = it;
                                                    str3 = str;
                                                }
                                                try {
                                                    TableRow.LayoutParams layoutParams3 = new TableRow.LayoutParams(-2, -1);
                                                    layoutParams3.span = (100 / next.getRowItems().size()) / split.length;
                                                    textView2.setLayoutParams(layoutParams3);
                                                    tableRow.addView(textView2);
                                                } catch (XmlPullParserException e5) {
                                                    e = e5;
                                                    str = str3;
                                                    IImageLoadedEvent iImageLoadedEvent322 = iImageLoadedEvent;
                                                    Trace.e(TAG, e.getMessage());
                                                    i3++;
                                                    str4 = str2;
                                                    it3 = it2;
                                                    it4 = it;
                                                    str3 = str;
                                                } catch (IOException e6) {
                                                    e = e6;
                                                    str = str3;
                                                    IImageLoadedEvent iImageLoadedEvent422 = iImageLoadedEvent;
                                                    Trace.e(TAG, e.getMessage());
                                                    i3++;
                                                    str4 = str2;
                                                    it3 = it2;
                                                    it4 = it;
                                                    str3 = str;
                                                }
                                            } else {
                                                str2 = str4;
                                                it2 = it3;
                                                it = it4;
                                            }
                                            ImageView imageView = new ImageView(context2);
                                            Parse.url = Parse.url.replace("content/", "");
                                            imageView.setTag(Parse.url);
                                            str = str3;
                                            try {
                                                iImageLoadedEvent.loadImage(Parse.url, imageView, Extensions.tryParseInteger(Parse.width) ? Integer.parseInt(Parse.width) : -1, Extensions.tryParseInteger(Parse.height) ? Integer.parseInt(Parse.height) : -1);
                                                imageView.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View view) {
                                                        ArrayList arrayList = new ArrayList();
                                                        Slide slide = new Slide();
                                                        slide.graphicUrl = Parse.url;
                                                        arrayList.add(slide);
                                                        Intent intent = new Intent(context2, CmeImageViewerActivity.class);
                                                        intent.putParcelableArrayListExtra("bundle_key_image_slides", arrayList);
                                                        intent.putExtra("bundle_key_image_slide_position", 0);
                                                        intent.putExtra(Constants.BUNDLE_KEY_IMAGE_WIDTH, Parse.width);
                                                        intent.putExtra(Constants.BUNDLE_KEY_IMAGE_HEIGHT, Parse.height);
                                                        context2.startActivity(intent);
                                                    }
                                                });
                                                TableRow.LayoutParams layoutParams4 = new TableRow.LayoutParams(-2, -1);
                                                layoutParams4.span = (100 / next.getRowItems().size()) / split.length;
                                                imageView.setLayoutParams(layoutParams4);
                                                if (Build.VERSION.SDK_INT >= 16) {
                                                    tableRow.setBackground(drawable);
                                                } else {
                                                    tableRow.setBackgroundResource(i2);
                                                }
                                                tableRow.addView(imageView);
                                            } catch (XmlPullParserException e7) {
                                                e = e7;
                                                Trace.e(TAG, e.getMessage());
                                                i3++;
                                                str4 = str2;
                                                it3 = it2;
                                                it4 = it;
                                                str3 = str;
                                            } catch (IOException e8) {
                                                e = e8;
                                                Trace.e(TAG, e.getMessage());
                                                i3++;
                                                str4 = str2;
                                                it3 = it2;
                                                it4 = it;
                                                str3 = str;
                                            }
                                        } catch (XmlPullParserException e9) {
                                            e = e9;
                                            str2 = str4;
                                            it2 = it3;
                                            it = it4;
                                            str = str3;
                                            IImageLoadedEvent iImageLoadedEvent3222 = iImageLoadedEvent;
                                            Trace.e(TAG, e.getMessage());
                                            i3++;
                                            str4 = str2;
                                            it3 = it2;
                                            it4 = it;
                                            str3 = str;
                                        } catch (IOException e10) {
                                            e = e10;
                                            str2 = str4;
                                            it2 = it3;
                                            it = it4;
                                            str = str3;
                                            IImageLoadedEvent iImageLoadedEvent4222 = iImageLoadedEvent;
                                            Trace.e(TAG, e.getMessage());
                                            i3++;
                                            str4 = str2;
                                            it3 = it2;
                                            it4 = it;
                                            str3 = str;
                                        }
                                    }
                                    i3++;
                                    str4 = str2;
                                    it3 = it2;
                                    it4 = it;
                                    str3 = str;
                                }
                                IImageLoadedEvent iImageLoadedEvent5 = iImageLoadedEvent;
                                it2 = it3;
                                it = it4;
                                it3 = it2;
                                it4 = it;
                            }
                        }
                        IImageLoadedEvent iImageLoadedEvent6 = iImageLoadedEvent;
                        it2 = it3;
                        it = it4;
                        if (next2.value.contains("<list")) {
                            try {
                                List<String> parseXmlListWithHtmlContent = new HtmlParser().parseXmlListWithHtmlContent(next2.value, Constants.XML_LIST, Constants.XML_LIST_ITEM);
                                HtmlListView htmlListView = new HtmlListView(context2);
                                if (next2.value.equals("order")) {
                                    htmlListView.inflate(parseXmlListWithHtmlContent, "ol");
                                } else {
                                    htmlListView.inflate(parseXmlListWithHtmlContent, "ul");
                                }
                                TableRow.LayoutParams layoutParams5 = new TableRow.LayoutParams(-2, -1);
                                layoutParams5.span = 100 / next.getRowItems().size();
                                htmlListView.setLayoutParams(layoutParams5);
                                if (Build.VERSION.SDK_INT >= 16) {
                                    htmlListView.setBackground(drawable);
                                } else {
                                    htmlListView.setBackgroundResource(i2);
                                }
                                tableRow.addView(htmlListView);
                            } catch (Exception e11) {
                                Trace.e(TAG, e11.getMessage());
                            }
                        } else {
                            TextView textView3 = new TextView(context2);
                            TableRow.LayoutParams layoutParams6 = new TableRow.LayoutParams(-2, -1);
                            layoutParams6.span = 100 / next.getRowItems().size();
                            textView3.setLayoutParams(layoutParams6);
                            if (next.getRowType().equals(HtmlTableRow.ROW_TYPE_TABLE_HEADER)) {
                                textView3.setTextColor(-1);
                            } else {
                                textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            }
                            textView3.setText(Html.fromHtml(next2.value));
                            if (Build.VERSION.SDK_INT >= 16) {
                                textView3.setBackground(drawable);
                            } else {
                                textView3.setBackgroundResource(i2);
                            }
                            tableRow.addView(textView3);
                        }
                        it3 = it2;
                        it4 = it;
                    }
                    IImageLoadedEvent iImageLoadedEvent7 = iImageLoadedEvent;
                    it2 = it3;
                    if (tableRow.getChildCount() > 0) {
                        tableLayout.addView(tableRow, new TableLayout.LayoutParams(-1, -2));
                    }
                    it3 = it2;
                    f = 100.0f;
                }
            }
        }
        return tableLayout;
    }
}
