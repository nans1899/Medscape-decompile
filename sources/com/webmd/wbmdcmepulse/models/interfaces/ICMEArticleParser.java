package com.webmd.wbmdcmepulse.models.interfaces;

import com.webmd.wbmdcmepulse.exceptions.IncompatibleArticleException;
import com.webmd.wbmdcmepulse.models.articles.Article;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public interface ICMEArticleParser {
    Article Parse() throws XmlPullParserException, IOException, IncompatibleArticleException;
}
