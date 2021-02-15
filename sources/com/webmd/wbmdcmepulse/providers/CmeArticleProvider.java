package com.webmd.wbmdcmepulse.providers;

import android.content.Context;
import android.os.Build;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.exceptions.IncompatibleArticleException;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.articles.QuestionRequest;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import com.webmd.wbmdcmepulse.models.articles.Version;
import com.webmd.wbmdcmepulse.models.interfaces.ICmeArticleProvider;
import com.webmd.wbmdcmepulse.models.parsers.articles.CMEArticleParser;
import com.webmd.wbmdcmepulse.models.parsers.articles.QuestionnaireParser;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.JsonObjectConverter;
import com.webmd.wbmdcmepulse.models.utils.RequestHelper;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.models.video.VideoChapters;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

public class CmeArticleProvider implements ICmeArticleProvider {
    private final String TAG = CmeArticleProvider.class.getSimpleName();
    Context mContext;

    public CmeArticleProvider(Context context) {
        this.mContext = context;
    }

    public void getArticle(String str, final ICallbackEvent<Article, CMEPulseException> iCallbackEvent) {
        int i = Build.VERSION.SDK_INT;
        String str2 = "1.0";
        try {
            if (this.mContext != null) {
                str2 = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            RequestHelper.getInstance(this.mContext).addStringRequest(0, Utilities.generateEnvironment(this.mContext, "https://api%s.medscape.com/contentservice/render/cme/article/") + str + "?devicetype=android&src=cmepulseapp-android&output_version=appxml&token=loYULQCrqlDRLsFt2BDIVQ==&osversion=" + i + "&fl=en_us&appversion=" + str2, (Map<String, String>) null, (Map<String, String>) null, new ICallbackEvent<String, String>() {
                public void onCompleted(String str) {
                    if (!Extensions.isStringNullOrEmpty(str)) {
                        try {
                            final Article Parse = new CMEArticleParser(str).Parse();
                            if (Extensions.isStringNullOrEmpty(Parse.videoUrl)) {
                                Iterator<Version> it = Parse.versions.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    Version next = it.next();
                                    if (next.isPrimary && next.media != null) {
                                        Parse.videoUrl = next.media.videoRssLocation;
                                        break;
                                    }
                                }
                            }
                            if (!Extensions.isStringNullOrEmpty(Parse.videoConfigURL)) {
                                RequestHelper.getInstance(CmeArticleProvider.this.mContext).addGSONObjectRequest(Utilities.generateEnvironment(CmeArticleProvider.this.mContext, "https://api%s.medscape.com/contentservice/video/content/") + Parse.videoConfigURL, (Map<String, String>) null, new ICallbackEvent() {
                                    public void onError(Object obj) {
                                        iCallbackEvent.onCompleted(Parse);
                                    }

                                    public void onCompleted(Object obj) {
                                        if (obj != null && (obj instanceof VideoChapters)) {
                                            Parse.videoChapters = (VideoChapters) obj;
                                            if (Parse.videoChapters.getConfig() != null && !Extensions.isStringNullOrEmpty(Parse.videoChapters.getConfig().getSource())) {
                                                if (Extensions.isStringNullOrEmpty(Parse.videoUrl)) {
                                                    String[] split = Parse.videoChapters.getConfig().getSource().split(",");
                                                    Parse.videoUrl = "https://webmd-a.akamaihd.net/delivery";
                                                    Article article = Parse;
                                                    article.videoUrl = Parse.videoUrl + split[0];
                                                    int length = split.length;
                                                    if (length > 1) {
                                                        Article article2 = Parse;
                                                        article2.videoUrl = Parse.videoUrl + "1000k" + split[length - 1];
                                                    }
                                                }
                                                if (Parse.videoChapters.getCcInfo() != null) {
                                                    Parse.videoCCURL = Utilities.generateEnvironment(CmeArticleProvider.this.mContext, "https://img%s.medscape.com");
                                                    Article article3 = Parse;
                                                    article3.videoCCURL = Parse.videoCCURL + Parse.videoChapters.getConfig().getCcFileRoot().replace(Parse.videoChapters.getConfig().getCcDefaultLang(), "");
                                                    Article article4 = Parse;
                                                    article4.videoCCURL = Parse.videoCCURL + Parse.videoChapters.getCcInfo().getEn();
                                                }
                                            }
                                        }
                                        iCallbackEvent.onCompleted(Parse);
                                    }
                                }, VideoChapters.class);
                                return;
                            }
                            iCallbackEvent.onCompleted(Parse);
                        } catch (IncompatibleArticleException unused) {
                            onError(IncompatibleArticleException.CLASS_NAME);
                        }
                    } else {
                        iCallbackEvent.onError(new CMEPulseException("empty article"));
                    }
                }

                public void onError(String str) {
                    ICallbackEvent iCallbackEvent = iCallbackEvent;
                    if (iCallbackEvent != null) {
                        iCallbackEvent.onError(new CMEPulseException(str));
                    }
                }
            });
        } catch (Exception e2) {
            if (iCallbackEvent != null) {
                iCallbackEvent.onError(new CMEPulseException("Error getting article." + e2.getMessage()));
                Trace.e(this.TAG, e2.getMessage());
            }
        }
    }

    public void getQna(String str, final ICallbackEvent<Questionnaire, CMEPulseException> iCallbackEvent, Context context) {
        try {
            RequestHelper.getInstance(context).addStringRequest(0, Utilities.generateEnvironment(context, "https://www%s.medscape.com/qnaService?questionnaireID=") + str, (Map<String, String>) null, (Map<String, String>) null, new ICallbackEvent<String, String>() {
                public void onError(String str) {
                    ICallbackEvent iCallbackEvent = iCallbackEvent;
                    if (iCallbackEvent != null) {
                        iCallbackEvent.onError(new CMEPulseException("Service Unavailable"));
                    }
                }

                public void onCompleted(String str) {
                    try {
                        iCallbackEvent.onCompleted(new QuestionnaireParser(str).parse());
                    } catch (IOException unused) {
                        ICallbackEvent iCallbackEvent = iCallbackEvent;
                        iCallbackEvent.onError(new CMEPulseException("Error getting qna " + str));
                    } catch (XmlPullParserException unused2) {
                        ICallbackEvent iCallbackEvent2 = iCallbackEvent;
                        iCallbackEvent2.onError(new CMEPulseException("Error parsing qna " + str));
                    }
                }
            });
        } catch (Exception e) {
            if (iCallbackEvent != null) {
                iCallbackEvent.onError(new CMEPulseException("Error getting Qna " + e.getMessage()));
                Trace.e(this.TAG, e.getMessage());
            }
        }
    }

    public void submitQuestionResponseList(QuestionRequest questionRequest, final ICallbackEvent<String, CMEPulseException> iCallbackEvent, Context context) {
        try {
            JSONObject convertQuestionRequestToJsonObject = new JsonObjectConverter().convertQuestionRequestToJsonObject(questionRequest);
            RequestHelper.getInstance(context).addJSONObjectRequest(1, Utilities.generateEnvironment(context, "https://www%s.medscape.org/questionnaire/submitForm/") + questionRequest.questionType, convertQuestionRequestToJsonObject, (ICallbackEvent<JSONObject, String>) new ICallbackEvent<JSONObject, String>() {
                public void onError(String str) {
                    iCallbackEvent.onError(new CMEPulseException(str));
                }

                public void onCompleted(JSONObject jSONObject) {
                    iCallbackEvent.onCompleted(jSONObject.toString());
                }
            });
        } catch (Exception e) {
            if (iCallbackEvent != null) {
                iCallbackEvent.onError(new CMEPulseException(e.getMessage()));
            }
        }
    }
}
