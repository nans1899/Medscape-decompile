package com.webmd.wbmdcmepulse.models.parsers.articles;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.exceptions.IncompatibleArticleException;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.interfaces.ICMEArticleParser;
import java.io.IOException;
import java.util.LinkedList;
import org.xmlpull.v1.XmlPullParserException;

public class CMEArticleParser extends ArticleParserBase implements ICMEArticleParser {
    private static final String TAG = CMEArticleParser.class.getSimpleName();
    private final String XML;

    public CMEArticleParser(String str) {
        this.XML = str;
    }

    public Article Parse() throws IncompatibleArticleException {
        this.mArticle = new Article();
        this.mArticle.authors = new LinkedList();
        try {
            try {
                parseDocument(initializeXmlParser(this.XML));
                return this.mArticle;
            } catch (IOException | IllegalStateException | XmlPullParserException e) {
                if (e instanceof IllegalStateException) {
                    FirebaseCrashlytics.getInstance().recordException(e);
                }
                Trace.e(TAG, e.getMessage());
                throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(7));
            }
        } catch (IOException | IllegalStateException | XmlPullParserException e2) {
            if (e2 instanceof IllegalStateException) {
                FirebaseCrashlytics.getInstance().recordException(e2);
            }
            Trace.e(TAG, e2.getMessage());
            throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(7));
        }
    }
}
