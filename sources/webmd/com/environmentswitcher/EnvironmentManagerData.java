package webmd.com.environmentswitcher;

import android.content.Context;
import android.preference.PreferenceManager;
import com.medscape.android.EnvironmentConfig;
import java.util.HashMap;
import java.util.Map;

public class EnvironmentManagerData {
    private Context context;
    private Map<String, String> dev01 = new HashMap();
    private Map<String, String> dev02 = new HashMap();
    private Map<String, String> devint = new HashMap();
    private Map<String, String> perf = new HashMap();
    private Map<String, String> prod = new HashMap();
    private Map<String, String> qa00 = new HashMap();
    private Map<String, String> qa01 = new HashMap();
    private Map<String, String> qa02 = new HashMap();

    public EnvironmentManagerData(Context context2) {
        this.context = context2;
        this.qa00.put("secretId", "080ebfcb-1795-4f39-aa09-1c21338b00ee");
        this.qa00.put("clientId", "b29cbd98-81b1-43d7-a04d-58ec0d13ba58");
        this.qa00.put("WBMD_DRUG_DETAIL_URL", "https://www.qa00.webmd.com/search/2/api/drug_detail");
        this.qa00.put("WBMD_DRUG_LIST_URL", "https://www.qa00.webmd.com/search/2/passthrough/drugs_content/");
        this.qa00.put("ARTICLE_DOCUMENTUM_URL", "https://www.qa00.webmd.com/search/2/passthrough/cms_content/");
        this.qa00.put("WBMD_AUTH_REQUEST", "https://www.qa00.webmd.com/api/reg/regapi.svc/json/login-oauth");
        this.qa00.put("WBMD_REG_REQUEST", "https://www.qa00.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.qa00.put("WBMD_TOKEN_REQUEST", "https://oauthapi.qa00.webmd.com/oauth2api.svc/token");
        this.qa00.put("WBMD_PAPIX_REQUEST", "https://www.qa00.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/query?count=999999");
        this.qa00.put("WBMD_PAPIX_SINGLE_SAVE_REQUEST", "https://www.qa00.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent");
        this.qa00.put("WBMD_PAPIX_MULTIPLE_SAVE_REQUEST", "https://www.qa00.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/coll");
        this.qa00.put("WBMD_PAPIX_UPDATE_REQUEST", "https://www.qa00.webmd.com/api/svcgateway/papix/papi.svc/update/savedcontent");
        this.qa00.put("WBMD_PAPIX_DELETE_REQUEST", "https://www.qa00.webmd.com/api/svcgateway/papix/papi.svc/delete/savedcontent");
        Object obj = "WBMD_PAPIX_DELETE_REQUEST";
        this.qa00.put("WBMD_REGISTER_REQUEST", "https://www.qa00.webmd.com/api/reg/regapi.svc/json/register-oauth");
        Object obj2 = "WBMD_REGISTER_REQUEST";
        this.qa00.put("WBMD_PASSWORD_RESET_URL", "https://member.qa00.webmd.com/password-reset");
        Object obj3 = "WBMD_PASSWORD_RESET_URL";
        this.qa00.put("WBMD_DRUG_IMAGE_OVERRIDE_URL_FORMAT", "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/mobiledrugimages/%s.png");
        Object obj4 = "WBMD_DRUG_IMAGE_OVERRIDE_URL_FORMAT";
        this.qa00.put("WBMD_DRUG_INTERACTION_URL", "http://www.qa00.webmd.com/drugs/api/DrugInteractionChecker.svc/drugsinteraction");
        Object obj5 = "WBMD_DRUG_INTERACTION_URL";
        this.qa00.put("WBMD_CONDITIONS_LINK_URL", "https://www.qa00.webmd.com/search/2/passthrough/lookup/");
        Object obj6 = "WBMD_CONDITIONS_LINK_URL";
        this.qa00.put("WBMD_NEWS_URL", "https://www.qa00.webmd.com/search/2/passthrough/cms_content/");
        Object obj7 = "WBMD_NEWS_URL";
        this.qa00.put("WBMD_TTS_DRUGS_URL", "http://img.qa00.webmd.com/tug/tts_drugs.wxml");
        Object obj8 = "WBMD_TTS_DRUGS_URL";
        this.qa00.put("WBMD_TTS_DRUGS_STAGING_URL", "http://img.qa00.webmd.com/tug/tts_drugs_staging.wxml");
        Object obj9 = "WBMD_TTS_DRUGS_STAGING_URL";
        this.qa00.put("WBMD_SC_CONDITIONS_URL", "http://www.qa00.webmd.com/search/2/api/scapp/conditions");
        this.qa00.put("WBMD_SC_SYMPTOMS_URL", "http://www.qa00.webmd.com/search/2/api/sctypeahead?&cache_2=true&count=%s&q=%s");
        this.qa00.put("WBMD_SC_MEDICATION_URL", "http://www.qa00.webmd.com/search/2/api/sc_medication?&cache_2=true&count=%s&q=%s");
        this.qa00.put("WBMD_SC_PRE_EXISTING_CONDITIONS_URL", "http://www.qa00.webmd.com/search/2/api/sc_preexisting?&cache_2=true&count=%s&q=%s");
        this.qa00.put("WBMD_SC_BODY_TYPEAHEAD_URL", "http://www.qa00.webmd.com/search/2/api/scbodytypeahead?cache_2=true&gender=%s&part=%s&q=%s&count=500");
        this.qa00.put("WBMD_MEMBER_ID_URL", "http://www.qa00.webmd.com/search/2/api/rx/memberid");
        Object obj10 = "WBMD_MEMBER_ID_URL";
        this.qa00.put("WBMD_SEARCH_URL", "https://www.qa00.webmd.com/search/2/api/mobileapp");
        this.qa00.put("WBMD_PILL_ID_SEARCH", "http://www.qa00.webmd.com/search/2/api/");
        this.qa00.put("WBMD_QNA_EDITORIAL_URL", "https://www.qa00.webmd.com/search/2/kaleo/getsequence?cid=%s&topicid=%s");
        this.qa00.put("WBMD_QNA_SPONSORED_URL", "https://www.qa00.webmd.com/search/2/api/sponsoredkaleo?current=%s&count=100&channel=channel_id:%s");
        this.qa00.put("WBMD_RELATED_CONTENT_URL", "https://www.qa00.webmd.com/search/2/api/related_content?id=%s&ect=%s");
        this.qa00.put("WBMD_SC_BROWSE_SYMPTOMS", "http://www.qa00.webmd.com/search/2/api/scbodylist?count=500&cache_2=true");
        this.qa00.put("WRX_DRUG_FORMS_URL", "http://www.qa00.webmd.com/search/2/api/rx/forms/");
        this.qa00.put("WRX_DRUG_PRICING_URL", "http://www.qa00.webmd.com/search/2/api/rx/pricing/");
        this.qa00.put("WBMD_RX_BASE_URL", "http://www.qa00.webmd.com/search/2/api/");
        this.qa00.put("WRX_TYPEAHEAD_URL", "http://www.qa00.webmd.com/search/2/api/rx/find/");
        this.devint.put("secretId", "9d75e92b-11e9-45e0-88e2-6602869dfb82");
        this.devint.put("clientId", "28c8b711-b6b8-440c-9e78-d56a1bcc4aa2");
        this.devint.put("WBMD_DRUG_DETAIL_URL", "https://www.devint.webmd.com/search/2/api/drug_detail");
        this.devint.put("WBMD_DRUG_LIST_URL", "https://www.devint.webmd.com/search/2/passthrough/drugs_content/");
        this.devint.put("ARTICLE_DOCUMENTUM_URL", "https://www.devint.webmd.com/search/2/passthrough/cms_content/");
        this.devint.put("WBMD_AUTH_REQUEST", "https://www.devint.webmd.com/api/reg/regapi.svc/json/login-oauth");
        this.devint.put("WBMD_REG_REQUEST", "https://www.devint.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.devint.put("WBMD_TOKEN_REQUEST", "https://oauthapi.devint.webmd.com/oauth2api.svc/token");
        this.devint.put("WBMD_PAPIX_REQUEST", "https://www.devint.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/query?count=999999");
        this.devint.put("WBMD_PAPIX_SINGLE_SAVE_REQUEST", "https://www.devint.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent");
        this.devint.put("WBMD_PAPIX_MULTIPLE_SAVE_REQUEST", "https://www.devint.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/coll");
        this.devint.put("WBMD_PAPIX_UPDATE_REQUEST", "https://www.devint.webmd.com/api/svcgateway/papix/papi.svc/update/savedcontent");
        this.devint.put(obj, "https://www.devint.webmd.com/api/svcgateway/papix/papi.svc/delete/savedcontent");
        this.devint.put(obj2, "https://www.devint.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.devint.put(obj3, "https://member.devint.webmd.com/password-reset");
        this.devint.put(obj4, "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/mobiledrugimages/%s.png");
        this.devint.put(obj5, "http://www.devint.webmd.com/drugs/api/DrugInteractionChecker.svc/drugsinteraction");
        this.devint.put(obj6, "https://www.devint.webmd.com/search/2/passthrough/lookup/");
        this.devint.put(obj8, "http://img.devint.webmd.com/tug/tts_drugs.wxml");
        this.devint.put(obj9, "http://img.devint.webmd.com/tug/tts_drugs_staging.wxml");
        this.devint.put(obj10, "http://www.devint.webmd.com/search/2/api/rx/memberid");
        this.devint.put(obj7, "https://www.devint.webmd.com/search/2/passthrough/cms_content/");
        this.devint.put("WBMD_NEWS_TOP_STORIES_URL", "http://www.devint.webmd.com/search/2/api/mobileapp_topstories");
        this.devint.put("WBMD_NEWS_CATEGORIES_URL", "http://www.devint.webmd.com/search/2/api/mobileapp_newscategories");
        this.devint.put("WBMD_SEARCH_URL", "https://www.devint.webmd.com/search/2/api/mobileapp");
        this.devint.put("WBMD_PILL_ID_SEARCH", "http://www.devint.webmd.com/search/2/api/");
        this.devint.put("WBMD_QNA_EDITORIAL_URL", "https://www.devint.webmd.com/search/2/kaleo/getsequence?cid=%s&topicid=%s");
        this.devint.put("WBMD_QNA_SPONSORED_URL", "https://www.devint.webmd.com/search/2/api/sponsoredkaleo?current=%s&count=100&channel=channel_id:%s");
        this.devint.put("WBMD_RELATED_CONTENT_URL", "https://www.devint.webmd.com/search/2/api/related_content?id=%s&ect=%s");
        this.devint.put("WBMD_SC_BROWSE_SYMPTOMS", "http://www.devint.webmd.com/search/2/api/scbodylist?count=500&cache_2=true");
        this.devint.put("WRX_DRUG_FORMS_URL", "http://www.devint.webmd.com/search/2/api/rx/forms/");
        this.devint.put("WRX_DRUG_PRICING_URL", "http://www.devint.webmd.com/search/2/api/rx/pricing/");
        this.devint.put("WBMD_RX_BASE_URL", "http://www.devint.webmd.com/search/2/api/");
        this.devint.put("WRX_TYPEAHEAD_URL", "http://www.devint.webmd.com/search/2/api/rx/find/");
        this.dev02.put("secretId", "751ffcf3-c423-4a17-a97f-6e0ed9a76af6");
        this.dev02.put("clientId", "7370891a-3463-4841-a3da-db60c5b8f3e9");
        this.dev02.put("WBMD_DRUG_DETAIL_URL", "https://www.dev02.webmd.com/search/2/api/drug_detail");
        this.dev02.put("WBMD_DRUG_LIST_URL", "https://www.dev02.webmd.com/search/2/passthrough/drugs_content/");
        this.dev02.put("ARTICLE_DOCUMENTUM_URL", "https://www.dev02.webmd.com/search/2/passthrough/cms_content/");
        this.dev02.put("WBMD_AUTH_REQUEST", "https://www.dev02.webmd.com/api/reg/regapi.svc/json/login-oauth");
        this.dev02.put("WBMD_REG_REQUEST", "https://www.dev02.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.dev02.put("WBMD_TOKEN_REQUEST", "https://oauthapi.dev02.webmd.com/oauth2api.svc/token");
        this.dev02.put("WBMD_PAPIX_REQUEST", "https://www.dev02.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/query?count=999999");
        this.dev02.put("WBMD_PAPIX_SINGLE_SAVE_REQUEST", "https://www.dev02.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent");
        this.dev02.put("WBMD_PAPIX_MULTIPLE_SAVE_REQUEST", "https://www.dev02.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/coll");
        this.dev02.put("WBMD_PAPIX_UPDATE_REQUEST", "https://www.dev02.webmd.com/api/svcgateway/papix/papi.svc/update/savedcontent");
        this.dev02.put(obj, "https://www.dev02.webmd.com/api/svcgateway/papix/papi.svc/delete/savedcontent");
        this.dev02.put(obj2, "https://www.dev02.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.dev02.put(obj3, "https://member.dev02.webmd.com/password-reset");
        this.dev02.put(obj4, "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/mobiledrugimages/%s.png");
        this.dev02.put(obj5, "http://www.dev02.webmd.com/drugs/api/DrugInteractionChecker.svc/drugsinteraction");
        this.dev02.put(obj6, "https://www.dev02.webmd.com/search/2/passthrough/lookup/");
        this.dev02.put(obj8, "http://img.dev02.webmd.com/tug/tts_drugs.wxml");
        this.dev02.put(obj9, "http://img.dev02.webmd.com/tug/tts_drugs_staging.wxml");
        this.dev02.put(obj10, "http://www.dev02.webmd.com/search/2/api/rx/memberid");
        this.dev02.put(obj7, "https://www.dev02.webmd.com/search/2/passthrough/cms_content/");
        this.dev02.put("WBMD_NEWS_TOP_STORIES_URL", "http://www.dev02.webmd.com/search/2/api/mobileapp_topstories");
        this.dev02.put("WBMD_NEWS_CATEGORIES_URL", "http://www.dev02.webmd.com/search/2/api/mobileapp_newscategories");
        this.dev02.put("WBMD_SEARCH_URL", "https://www.dev02.webmd.com/search/2/api/mobileapp");
        this.dev02.put("WBMD_PILL_ID_SEARCH", "http://www.dev02.webmd.com/search/2/api/");
        this.dev02.put("WBMD_QNA_EDITORIAL_URL", "https://www.dev02.webmd.com/search/2/kaleo/getsequence?cid=%s&topicid=%s");
        this.dev02.put("WBMD_QNA_SPONSORED_URL", "https://www.dev02.webmd.com/search/2/api/sponsoredkaleo?current=%s&count=100&channel=channel_id:%s");
        this.dev02.put("WBMD_RELATED_CONTENT_URL", "https://www.dev02.webmd.com/search/2/api/related_content?id=%s&ect=%s");
        this.dev02.put("WBMD_SC_BROWSE_SYMPTOMS", "http://www.dev02.webmd.com/search/2/api/scbodylist?count=500&cache_2=true");
        this.dev02.put("WRX_DRUG_FORMS_URL", "http://www.dev02.webmd.com/search/2/api/rx/forms/");
        this.dev02.put("WRX_DRUG_PRICING_URL", "http://www.dev02.webmd.com/search/2/api/rx/pricing/");
        this.dev02.put("WBMD_RX_BASE_URL", "http://www.dev02.webmd.com/search/2/api/");
        this.dev02.put("WRX_TYPEAHEAD_URL", "http://www.dev02.webmd.com/search/2/api/rx/find/");
        this.qa01.put("secretId", "28d3027f-75c7-4e1a-9806-3c790c430d95");
        this.qa01.put("clientId", "28c8b711-b6b8-440c-9e78-d56a1bcc4aa2");
        this.qa01.put("WBMD_DRUG_DETAIL_URL", "http://www.qa01.webmd.com/search/2/api/drug_detail");
        this.qa01.put("WBMD_DRUG_LIST_URL", "http://www.qa01.webmd.com/search/2/passthrough/drugs_content/");
        this.qa01.put("ARTICLE_DOCUMENTUM_URL", "http://www.qa01.webmd.com/search/2/passthrough/cms_content/");
        this.qa01.put("WBMD_AUTH_REQUEST", "http://regapi.qa01.webmd.com/RegAPI.svc/json/login-oauth");
        this.qa01.put("WBMD_REG_REQUEST", "https://regapi.qa01.webmd.com/RegAPI.svc");
        this.qa01.put("WBMD_TOKEN_REQUEST", "http://oauthapi.qa01.webmd.com/oauth2api.svc/token");
        this.qa01.put("WBMD_PAPIX_REQUEST", "http://www.qa01.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/query?count=999999");
        this.qa01.put("WBMD_PAPIX_SINGLE_SAVE_REQUEST", "http://www.qa01.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent");
        this.qa01.put("WBMD_PAPIX_MULTIPLE_SAVE_REQUEST", "http://www.qa01.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/coll");
        this.qa01.put("WBMD_PAPIX_UPDATE_REQUEST", "http://www.qa01.webmd.com/api/svcgateway/papix/papi.svc/update/savedcontent");
        this.qa01.put(obj, "http://www.qa01.webmd.com/api/svcgateway/papix/papi.svc/delete/savedcontent");
        this.qa01.put(obj2, "https://regapi.qa01.webmd.com/RegAPI.svc");
        this.qa01.put(obj3, "http://member.qa01.webmd.com/password-reset");
        this.qa01.put(obj5, "http://www.qa01.webmd.com/drugs/api/DrugInteractionChecker.svc/drugsinteraction");
        this.qa01.put(obj4, "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/mobiledrugimages/%s.png");
        this.qa01.put(obj6, "https://www.qa01.webmd.com/search/2/passthrough/lookup/");
        this.qa01.put(obj8, "http://img.qa01.webmd.com/tug/tts_drugs.wxml");
        this.qa01.put(obj9, "http://img.qa01.webmd.com/tug/tts_drugs_staging.wxml");
        this.qa01.put("WBMD_SC_CONDITIONS_URL", "http://www.qa01.webmd.com/search/2/api/scapp/conditions");
        this.qa01.put("WBMD_SC_SYMPTOMS_URL", "http://www.qa01.webmd.com/search/2/api/sctypeahead?&cache_2=true&count=%s&q=%s");
        this.qa01.put("WBMD_SC_MEDICATION_URL", "http://www.qa01.webmd.com/search/2/api/sc_medication?&cache_2=true&count=%s&q=%s");
        this.qa01.put("WBMD_SC_PRE_EXISTING_CONDITIONS_URL", "http://www.qa01.webmd.com/search/2/api/sc_preexisting?&cache_2=true&count=%s&q=%s");
        this.qa01.put("WBMD_SC_BODY_TYPEAHEAD_URL", "http://www.qa01.webmd.com/search/2/api/scbodytypeahead?cache_2=true&gender=%s&part=%s&q=%s&count=500");
        this.qa01.put(obj10, "http://www.qa01.webmd.com/search/2/api/rx/memberid");
        this.qa01.put(obj7, "https://www.qa01.webmd.com/search/2/passthrough/cms_content/");
        this.qa01.put("WBMD_NEWS_TOP_STORIES_URL", "http://www.qa01webmd.com/search/2/api/mobileapp_topstories");
        this.qa01.put("WBMD_NEWS_CATEGORIES_URL", "http://www.qa01.webmd.com/search/2/api/mobileapp_newscategories");
        this.qa01.put("WBMD_SEARCH_URL", "https://www.qa01.webmd.com/search/2/api/mobileapp");
        this.qa01.put("WBMD_PILL_ID_SEARCH", "http://www.qa01.webmd.com/search/2/api/");
        this.qa01.put("WBMD_QNA_EDITORIAL_URL", "https://www.qa01.webmd.com/search/2/kaleo/getsequence?cid=%s&topicid=%s");
        this.qa01.put("WBMD_QNA_SPONSORED_URL", "https://www.qa01.webmd.com/search/2/api/sponsoredkaleo?current=%s&count=100&channel=channel_id:%s");
        this.qa01.put("WBMD_RELATED_CONTENT_URL", "https://www.qa01.webmd.com/search/2/api/related_content?id=%s&ect=%s");
        this.qa01.put("WBMD_SC_BROWSE_SYMPTOMS", "http://www.qa01.webmd.com/search/2/api/scbodylist?count=500&cache_2=true");
        this.qa01.put("WRX_DRUG_FORMS_URL", "http://www.qa01.webmd.com/search/2/api/rx/forms/");
        this.qa01.put("WRX_DRUG_PRICING_URL", "http://www.qa01.webmd.com/search/2/api/rx/pricing/");
        this.qa01.put("WBMD_RX_BASE_URL", "http://www.qa01.webmd.com/search/2/api/");
        this.qa01.put("WRX_TYPEAHEAD_URL", "http://www.qa01.webmd.com/search/2/api/rx/find/");
        this.qa02.put("secretId", "f3606859-2fdf-4383-960d-23527620af1d");
        this.qa02.put("clientId", "28c8b711-b6b8-440c-9e78-d56a1bcc4aa2");
        this.qa02.put("WBMD_DRUG_DETAIL_URL", "https://www.qa02.webmd.com/search/2/api/drug_detail");
        this.qa02.put("WBMD_DRUG_LIST_URL", "https://www.qa02.webmd.com/search/2/passthrough/drugs_content/");
        this.qa02.put("ARTICLE_DOCUMENTUM_URL", "https://www.qa02.webmd.com/search/2/passthrough/cms_content/");
        this.qa02.put("WBMD_AUTH_REQUEST", "https://www.qa02.webmd.com/api/reg/regapi.svc/json/login-oauth");
        this.qa02.put("WBMD_REG_REQUEST", "https://www.qa02.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.qa02.put("WBMD_TOKEN_REQUEST", "https://oauthapi.qa02.webmd.com/oauth2api.svc/token");
        this.qa02.put("WBMD_PAPIX_REQUEST", "https://www.qa02.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/query?count=999999");
        this.qa02.put("WBMD_PAPIX_SINGLE_SAVE_REQUEST", "https://www.qa02.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent");
        this.qa02.put("WBMD_PAPIX_MULTIPLE_SAVE_REQUEST", "https://www.qa02.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/coll");
        this.qa02.put("WBMD_PAPIX_UPDATE_REQUEST", "https://www.qa02.webmd.com/api/svcgateway/papix/papi.svc/update/savedcontent");
        this.qa02.put(obj, "https://www.qa02.webmd.com/api/svcgateway/papix/papi.svc/delete/savedcontent");
        this.qa02.put(obj2, "https://www.qa02.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.qa02.put(obj3, "https://member.qa02.webmd.com/password-reset");
        this.qa02.put(obj4, "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/mobiledrugimages/%s.png");
        this.qa02.put(obj5, "http://www.qa02.webmd.com/drugs/api/DrugInteractionChecker.svc/drugsinteraction");
        this.qa02.put(obj6, "https://www.qa02webmd.com/search/2/passthrough/lookup/");
        this.qa02.put(obj8, "http://img.qa02.webmd.com/tug/tts_drugs.wxml");
        this.qa02.put(obj9, "http://img.qa02.webmd.com/tug/tts_drugs_staging.wxml");
        this.qa02.put(obj10, "http://www.qa02.webmd.com/search/2/api/rx/memberid");
        this.qa02.put(obj7, "https://www.qa02.webmd.com/search/2/passthrough/cms_content/");
        this.qa02.put("WBMD_NEWS_TOP_STORIES_URL", "http://www.qa02.webmd.com/search/2/api/mobileapp_topstories");
        this.qa02.put("WBMD_NEWS_CATEGORIES_URL", "http://www.qa02.webmd.com/search/2/api/mobileapp_newscategories");
        this.qa02.put("WBMD_SEARCH_URL", "https://www.qa02.webmd.com/search/2/api/mobileapp");
        this.qa02.put("WBMD_PILL_ID_SEARCH", "http://www.qa02.webmd.com/search/2/api/");
        this.qa02.put("WBMD_QNA_EDITORIAL_URL", "https://www.qa02.webmd.com/search/2/kaleo/getsequence?cid=%s&topicid=%s");
        this.qa02.put("WBMD_QNA_SPONSORED_URL", "https://www.qa02.webmd.com/search/2/api/sponsoredkaleo?current=%s&count=100&channel=channel_id:%s");
        this.qa02.put("WBMD_RELATED_CONTENT_URL", "https://www.qa02.webmd.com/search/2/api/related_content?id=%s&ect=%s");
        this.qa02.put("WBMD_SC_BROWSE_SYMPTOMS", "http://www.qa02.webmd.com/search/2/api/scbodylist?count=500&cache_2=true");
        this.qa02.put("WRX_DRUG_FORMS_URL", "http://www.qa02.webmd.com/search/2/api/rx/forms/");
        this.qa02.put("WRX_DRUG_PRICING_URL", "http://www.qa02.webmd.com/search/2/api/rx/pricing/");
        this.qa02.put("WBMD_RX_BASE_URL", "http://www.qa02.webmd.com/search/2/api/");
        this.qa02.put("WRX_TYPEAHEAD_URL", "http://www.qa02.webmd.com/search/2/api/rx/find/");
        this.dev01.put("secretId", "49b9f6b6-3f3f-449b-971f-4b619c3a6869");
        this.dev01.put("clientId", "c3b81599-263b-4cb4-8e4a-87c19dfab8c9");
        this.dev01.put("WBMD_DRUG_DETAIL_URL", "http://www.dev01.webmd.com/search/2/api/drug_detail");
        this.dev01.put("WBMD_DRUG_LIST_URL", "http://www.dev01.webmd.com/search/2/passthrough/drugs_content/");
        this.dev01.put("ARTICLE_DOCUMENTUM_URL", "http://www.dev01.webmd.com/search/2/passthrough/cms_content/");
        this.dev01.put("WBMD_AUTH_REQUEST", "http://www.dev01.webmd.com/api/reg/regapi.svc/json/login-oauth");
        this.dev01.put("WBMD_REG_REQUEST", "http://www.dev01.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.dev01.put("WBMD_TOKEN_REQUEST", "http://oauthapi.dev01.webmd.com/oauth2api.svc/token");
        this.dev01.put("WBMD_PAPIX_REQUEST", "http://www.dev01.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/query?count=999999");
        this.dev01.put("WBMD_PAPIX_SINGLE_SAVE_REQUEST", "http://www.dev01.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent");
        this.dev01.put("WBMD_PAPIX_MULTIPLE_SAVE_REQUEST", "http://www.dev01.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/coll");
        this.dev01.put("WBMD_PAPIX_UPDATE_REQUEST", "http://www.dev01.webmd.com/api/svcgateway/papix/papi.svc/update/savedcontent");
        this.dev01.put(obj, "http://www.dev01.webmd.com/api/svcgateway/papix/papi.svc/delete/savedcontent");
        this.dev01.put(obj2, "http://www.dev01.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.dev01.put(obj3, "http://member.dev01.webmd.com/password-reset");
        this.dev01.put(obj5, "http://www.dev01.webmd.com/drugs/api/DrugInteractionChecker.svc/drugsinteraction");
        this.dev01.put(obj4, "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/mobiledrugimages/%s.png");
        this.dev01.put(obj6, "https://www.dev01.webmd.com/search/2/passthrough/lookup/");
        this.dev01.put(obj8, "http://img.dev01.webmd.com/tug/tts_drugs.wxml");
        this.dev01.put(obj9, "http://img.dev01.webmd.com/tug/tts_drugs_staging.wxml");
        this.dev01.put(obj10, "http://www.dev01.webmd.com/search/2/api/rx/memberid");
        this.dev01.put(obj7, "https://www.dev01.webmd.com/search/2/passthrough/cms_content/");
        this.dev01.put("WBMD_NEWS_TOP_STORIES_URL", "http://www.dev01.webmd.com/search/2/api/mobileapp_topstories");
        this.dev01.put("WBMD_NEWS_CATEGORIES_URL", "http://www.dev01.webmd.com/search/2/api/mobileapp_newscategories");
        this.dev01.put("WBMD_SEARCH_URL", "https://www.dev01.webmd.com/search/2/api/mobileapp");
        this.dev01.put("WBMD_PILL_ID_SEARCH", "http://www.dev01.webmd.com/search/2/api/");
        this.dev01.put("WBMD_QNA_EDITORIAL_URL", "https://www.dev01.webmd.com/search/2/kaleo/getsequence?cid=%s&topicid=%s");
        this.dev01.put("WBMD_QNA_SPONSORED_URL", "https://www.dev01.webmd.com/search/2/api/sponsoredkaleo?current=%s&count=100&channel=channel_id:%s");
        this.dev01.put("WBMD_RELATED_CONTENT_URL", "https://www.dev01.webmd.com/search/2/api/related_content?id=%s&ect=%s");
        this.dev01.put("WBMD_SC_BROWSE_SYMPTOMS", "http://www.dev01.webmd.com/search/2/api/scbodylist?count=500&cache_2=true");
        this.dev01.put("WRX_DRUG_FORMS_URL", "http://www.dev01.webmd.com/search/2/api/rx/forms/");
        this.dev01.put("WRX_DRUG_PRICING_URL", "http://www.dev01.webmd.com/search/2/api/rx/pricing/");
        this.dev01.put("WBMD_RX_BASE_URL", "http://www.dev01.webmd.com/search/2/api/");
        this.dev01.put("WRX_TYPEAHEAD_URL", "http://www.dev01.webmd.com/search/2/api/rx/find/");
        this.perf.put("secretId", "8b3daa0a-3040-4cec-ba6c-a88caf1914b1");
        this.perf.put("clientId", "d0a8a6d6-c370-4cba-8dfb-54a7868e2551");
        this.perf.put("WBMD_DRUG_DETAIL_URL", "https://www.perf.webmd.com/search/2/api/drug_detail");
        this.perf.put("WBMD_DRUG_LIST_URL", "https://www.perf.webmd.com/search/2/passthrough/drugs_content/");
        this.perf.put("ARTICLE_DOCUMENTUM_URL", "https://www.pref.webmd.com/search/2/passthrough/cms_content/");
        this.perf.put("WBMD_AUTH_REQUEST", "https://www.perf.webmd.com/api/reg/regapi.svc/json/login-oauth");
        this.perf.put("WBMD_REG_REQUEST", "https://www.perf.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.perf.put("WBMD_TOKEN_REQUEST", "https://oauthapi.perf.webmd.com/oauth2api.svc/token");
        this.perf.put("WBMD_PAPIX_REQUEST", "https://www.perf.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/query?count=999999");
        this.perf.put("WBMD_PAPIX_SINGLE_SAVE_REQUEST", "https://www.perf.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent");
        this.perf.put("WBMD_PAPIX_MULTIPLE_SAVE_REQUEST", "https://www.perf.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/coll");
        this.perf.put("WBMD_PAPIX_UPDATE_REQUEST", "https://www.perf.webmd.com/api/svcgateway/papix/papi.svc/update/savedcontent");
        this.perf.put(obj, "https://www.perf.webmd.com/api/svcgateway/papix/papi.svc/delete/savedcontent");
        this.perf.put(obj2, "https://www.perf.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.perf.put(obj3, "https://member.perf.webmd.com/password-reset");
        this.perf.put(obj4, "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/mobiledrugimages/%s.png");
        this.perf.put(obj5, "http://www.perf.webmd.com/drugs/api/DrugInteractionChecker.svc/drugsinteraction");
        this.perf.put(obj6, "https://www.perf.webmd.com/search/2/passthrough/lookup/");
        this.perf.put(obj8, "http://img.perf.webmd.com/tug/tts_drugs.wxml");
        this.perf.put(obj9, "http://img.perf.webmd.com/tug/tts_drugs_staging.wxml");
        this.perf.put("WBMD_SC_CONDITIONS_URL", "http://www.perf.webmd.com/search/2/api/scapp/conditions");
        this.perf.put("WBMD_SC_SYMPTOMS_URL", "http://www.perf.webmd.com/search/2/api/sctypeahead?&cache_2=true&count=%s&q=%s");
        this.perf.put("WBMD_SC_MEDICATION_URL", "http://www.perf.webmd.com/search/2/api/sc_medication?&cache_2=true&count=%s&q=%s");
        this.perf.put("WBMD_SC_PRE_EXISTING_CONDITIONS_URL", "http://www.perf.webmd.com/search/2/api/sc_preexisting?&cache_2=true&count=%s&q=%s");
        this.perf.put("WBMD_SC_BODY_TYPEAHEAD_URL", "http://www.perf.webmd.com/search/2/api/scbodytypeahead?cache_2=true&gender=%s&part=%s&q=%s&count=500");
        this.perf.put(obj10, "http://www.perf.webmd.com/search/2/api/rx/memberid");
        this.perf.put(obj7, "https://www.perf.webmd.com/search/2/passthrough/cms_content/");
        this.perf.put("WBMD_NEWS_TOP_STORIES_URL", "http://www.perf.webmd.com/search/2/api/mobileapp_topstories");
        this.perf.put("WBMD_NEWS_CATEGORIES_URL", "http://www.perf.webmd.com/search/2/api/mobileapp_newscategories");
        this.perf.put("WBMD_SEARCH_URL", "https://www.perf.webmd.com/search/2/api/mobileapp");
        this.perf.put("WBMD_PILL_ID_SEARCH", "http://www.perf.webmd.com/search/2/api/");
        this.perf.put("WBMD_QNA_EDITORIAL_URL", "https://www.perf.webmd.com/search/2/kaleo/getsequence?cid=%s&topicid=%s");
        this.perf.put("WBMD_QNA_SPONSORED_URL", "https://www.perf.webmd.com/search/2/api/sponsoredkaleo?current=%s&count=100&channel=channel_id:%s");
        this.perf.put("WBMD_RELATED_CONTENT_URL", "https://www.perf.webmd.com/search/2/api/related_content?id=%s&ect=%s");
        this.perf.put("WBMD_SC_BROWSE_SYMPTOMS", "http://www.perf.webmd.com/search/2/api/scbodylist?count=500&cache_2=true");
        this.perf.put("WRX_DRUG_FORMS_URL", "http://www.perf.webmd.com/search/2/api/rx/forms/");
        this.perf.put("WRX_DRUG_PRICING_URL", "http://www.perf.webmd.com/search/2/api/rx/pricing/");
        this.perf.put("WBMD_RX_BASE_URL", "http://www.perf.webmd.com/search/2/api/");
        this.perf.put("WRX_TYPEAHEAD_URL", "http://www.perf.webmd.com/search/2/api/rx/find/");
        this.prod.put("secretId", "8be2e6a4-8099-482c-8dac-ccd022581419");
        this.prod.put("clientId", "3454df96-c7a5-47bb-a74e-890fb3c30a0d");
        this.prod.put("WBMD_DRUG_DETAIL_URL", "https://www.webmd.com/search/2/api/drug_detail");
        this.prod.put("WBMD_DRUG_LIST_URL", "https://www.webmd.com/search/2/passthrough/drugs_content/");
        this.prod.put("ARTICLE_DOCUMENTUM_URL", "https://www.webmd.com/search/2/passthrough/cms_content/");
        this.prod.put("WBMD_AUTH_REQUEST", "https://www.webmd.com/api/reg/regapi.svc/json/login-oauth");
        this.prod.put("WBMD_REG_REQUEST", "https://www.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.prod.put("WBMD_TOKEN_REQUEST", "https://oauthapi.webmd.com/oauth2api.svc/token");
        this.prod.put("WBMD_PAPIX_REQUEST", "https://www.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/query?count=999999");
        this.prod.put("WBMD_PAPIX_SINGLE_SAVE_REQUEST", "https://www.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent");
        this.prod.put("WBMD_PAPIX_MULTIPLE_SAVE_REQUEST", "https://www.webmd.com/api/svcgateway/papix/papi.svc/data/savedcontent/coll");
        this.prod.put("WBMD_PAPIX_UPDATE_REQUEST", "https://www.webmd.com/api/svcgateway/papix/papi.svc/update/savedcontent");
        this.prod.put(obj, "https://www.webmd.com/api/svcgateway/papix/papi.svc/delete/savedcontent");
        this.prod.put(obj2, "https://www.webmd.com/api/reg/regapi.svc/json/register-oauth");
        this.prod.put(obj3, "https://member.webmd.com/password-reset");
        this.prod.put(obj4, "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/mobiledrugimages/%s.png");
        this.prod.put(obj5, "https://www.webmd.com/drugs/api/DrugInteractionChecker.svc/drugsinteraction");
        this.prod.put(obj6, "https://www.webmd.com/search/2/passthrough/lookup/");
        this.prod.put(obj8, "http://origin-img.webmd.com/tug/tts_drugs.wxml");
        this.prod.put(obj9, "http://origin-img.webmd.com/tug/tts_drugs_staging.wxml");
        this.prod.put("WBMD_SC_CONDITIONS_URL", "http://www.webmd.com/search/2/api/scapp/conditions");
        this.prod.put("WBMD_SC_SYMPTOMS_URL", "http://www.webmd.com/search/2/api/sctypeahead?&cache_2=true&count=%s&q=%s");
        this.prod.put("WBMD_SC_BODY_TYPEAHEAD_URL", "https://www.webmd.com/search/2/api/scbodytypeahead?cache_2=true&gender=%s&part=%s&q=%s&count=500");
        this.prod.put("WBMD_SC_MEDICATION_URL", "http://www.webmd.com/search/2/api/sc_medication?&cache_2=true&count=%s&q=%s");
        this.prod.put("WBMD_SC_PRE_EXISTING_CONDITIONS_URL", "http://www.webmd.com/search/2/api/sc_preexisting?&cache_2=true&count=%s&q=%s");
        this.prod.put(obj10, "http://www.webmd.com/search/2/api/rx/memberid");
        this.prod.put(obj7, "https://www.webmd.com/search/2/passthrough/cms_content/");
        this.prod.put("WBMD_NEWS_TOP_STORIES_URL", "http://www.webmd.com/search/2/api/mobileapp_topstories");
        this.prod.put("WBMD_NEWS_CATEGORIES_URL", "http://www.webmd.com/search/2/api/mobileapp_newscategories");
        this.prod.put("WBMD_PILL_ID_SEARCH", "http://www.webmd.com/search/2/api/");
        this.prod.put("WBMD_SEARCH_URL", "https://www.webmd.com/search/2/api/mobileapp");
        this.prod.put("WBMD_QNA_EDITORIAL_URL", "https://www.webmd.com/search/2/kaleo/getsequence?cid=%s&topicid=%s");
        this.prod.put("WBMD_QNA_SPONSORED_URL", "https://www.webmd.com/search/2/api/sponsoredkaleo?current=%s&count=100&channel=channel_id:%s");
        this.prod.put("WBMD_RELATED_CONTENT_URL", "https://www.webmd.com/search/2/api/related_content?id=%s&ect=%s");
        this.prod.put("WBMD_SC_BROWSE_SYMPTOMS", "https://www.webmd.com/search/2/api/scbodylist?count=500&cache_2=true");
        this.prod.put("WRX_DRUG_FORMS_URL", "https://www.webmd.com/search/2/api/rx/forms/");
        this.prod.put("WRX_DRUG_PRICING_URL", "https://www.webmd.com/search/2/api/rx/pricing/");
        this.prod.put("WBMD_RX_BASE_URL", "https://www.webmd.com/search/2/api/");
        this.prod.put("WRX_TYPEAHEAD_URL", "http://www.webmd.com/search/2/api/rx/find/");
    }

    public String getEnvironment() {
        return PreferenceManager.getDefaultSharedPreferences(this.context).getString(Constants.pref_switcher_option, EnvironmentConfig.ENV_PROD);
    }

    public String getSearchEnvironment() {
        return PreferenceManager.getDefaultSharedPreferences(this.context).getString("wbmd_env_search", EnvironmentConfig.ENV_PROD);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.String> getMapForEnvironment() {
        /*
            r2 = this;
            java.lang.String r0 = r2.getEnvironment()
            int r1 = r0.hashCode()
            switch(r1) {
                case 2452201: goto L_0x0052;
                case 2464599: goto L_0x0048;
                case 2477072: goto L_0x003e;
                case 2477073: goto L_0x0034;
                case 2477074: goto L_0x002a;
                case 64939190: goto L_0x0020;
                case 64939191: goto L_0x0016;
                case 2013139898: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x005c
        L_0x000c:
            java.lang.String r1 = "DEVINT"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 7
            goto L_0x005d
        L_0x0016:
            java.lang.String r1 = "DEV02"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 6
            goto L_0x005d
        L_0x0020:
            java.lang.String r1 = "DEV01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 2
            goto L_0x005d
        L_0x002a:
            java.lang.String r1 = "QA02"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 3
            goto L_0x005d
        L_0x0034:
            java.lang.String r1 = "QA01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 4
            goto L_0x005d
        L_0x003e:
            java.lang.String r1 = "QA00"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 5
            goto L_0x005d
        L_0x0048:
            java.lang.String r1 = "PROD"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 0
            goto L_0x005d
        L_0x0052:
            java.lang.String r1 = "PERF"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 1
            goto L_0x005d
        L_0x005c:
            r0 = -1
        L_0x005d:
            switch(r0) {
                case 0: goto L_0x0077;
                case 1: goto L_0x0074;
                case 2: goto L_0x0071;
                case 3: goto L_0x006e;
                case 4: goto L_0x006b;
                case 5: goto L_0x0068;
                case 6: goto L_0x0065;
                case 7: goto L_0x0062;
                default: goto L_0x0060;
            }
        L_0x0060:
            r0 = 0
            return r0
        L_0x0062:
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.devint
            return r0
        L_0x0065:
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.dev02
            return r0
        L_0x0068:
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.qa00
            return r0
        L_0x006b:
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.qa01
            return r0
        L_0x006e:
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.qa02
            return r0
        L_0x0071:
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.dev01
            return r0
        L_0x0074:
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.perf
            return r0
        L_0x0077:
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.prod
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: webmd.com.environmentswitcher.EnvironmentManagerData.getMapForEnvironment():java.util.Map");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.String> getMapForEnvironment(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case 2452201: goto L_0x004e;
                case 2464599: goto L_0x0044;
                case 2477072: goto L_0x003a;
                case 2477073: goto L_0x0030;
                case 2477074: goto L_0x0026;
                case 64939190: goto L_0x001c;
                case 64939191: goto L_0x0012;
                case 2013139898: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0058
        L_0x0008:
            java.lang.String r0 = "DEVINT"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0058
            r2 = 7
            goto L_0x0059
        L_0x0012:
            java.lang.String r0 = "DEV02"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0058
            r2 = 6
            goto L_0x0059
        L_0x001c:
            java.lang.String r0 = "DEV01"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0058
            r2 = 2
            goto L_0x0059
        L_0x0026:
            java.lang.String r0 = "QA02"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0058
            r2 = 3
            goto L_0x0059
        L_0x0030:
            java.lang.String r0 = "QA01"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0058
            r2 = 4
            goto L_0x0059
        L_0x003a:
            java.lang.String r0 = "QA00"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0058
            r2 = 5
            goto L_0x0059
        L_0x0044:
            java.lang.String r0 = "PROD"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0058
            r2 = 0
            goto L_0x0059
        L_0x004e:
            java.lang.String r0 = "PERF"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0058
            r2 = 1
            goto L_0x0059
        L_0x0058:
            r2 = -1
        L_0x0059:
            switch(r2) {
                case 0: goto L_0x0073;
                case 1: goto L_0x0070;
                case 2: goto L_0x006d;
                case 3: goto L_0x006a;
                case 4: goto L_0x0067;
                case 5: goto L_0x0064;
                case 6: goto L_0x0061;
                case 7: goto L_0x005e;
                default: goto L_0x005c;
            }
        L_0x005c:
            r2 = 0
            return r2
        L_0x005e:
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.devint
            return r2
        L_0x0061:
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.dev02
            return r2
        L_0x0064:
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.qa00
            return r2
        L_0x0067:
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.qa01
            return r2
        L_0x006a:
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.qa02
            return r2
        L_0x006d:
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.dev01
            return r2
        L_0x0070:
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.perf
            return r2
        L_0x0073:
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.prod
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: webmd.com.environmentswitcher.EnvironmentManagerData.getMapForEnvironment(java.lang.String):java.util.Map");
    }

    public void addAppCustomLink(String str, String str2) {
        if (str != null && str2 != null) {
            Map<String, String> map = this.prod;
            map.put(str, "https://www.webmd.com/" + str2);
            Map<String, String> map2 = this.perf;
            map2.put(str, "https://www.perf.webmd.com/" + str2);
            Map<String, String> map3 = this.dev01;
            map3.put(str, "http://www.dev01.webmd.com/" + str2);
            Map<String, String> map4 = this.qa02;
            map4.put(str, "https://www.qa02.webmd.com/" + str2);
            Map<String, String> map5 = this.qa01;
            map5.put(str, "https://www.qa01.webmd.com/" + str2);
            Map<String, String> map6 = this.qa00;
            map6.put(str, "https://www.qa00.webmd.com/" + str2);
            Map<String, String> map7 = this.dev02;
            map7.put(str, "https://www.dev02.webmd.com/" + str2);
            Map<String, String> map8 = this.devint;
            map8.put(str, "https://www.devint.webmd.com/" + str2);
        }
    }

    public String getRequestLink(String str) {
        return getMapForEnvironment().get(str);
    }

    public String getRxTypeAheadLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WRX_TYPEAHEAD_URL");
    }

    public String getDrugFormUrl() {
        return getMapForEnvironment(getSearchEnvironment()).get("WRX_DRUG_FORMS_URL");
    }

    public String getDrugPricingUrl() {
        return getMapForEnvironment(getSearchEnvironment()).get("WRX_DRUG_PRICING_URL");
    }

    public String getRxBaseUrl() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_RX_BASE_URL");
    }

    public String getSecretId() {
        return getMapForEnvironment(getSearchEnvironment()).get("secretId");
    }

    public String getClientId() {
        return getMapForEnvironment(getSearchEnvironment()).get("clientId");
    }

    public String getSearchSecretId() {
        return getMapForEnvironment(getSearchEnvironment()).get("secretId");
    }

    public String getSearchClientId() {
        return getMapForEnvironment(getSearchEnvironment()).get("clientId");
    }

    public String getMonographLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_DRUG_DETAIL_URL");
    }

    public String getDrugListLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_DRUG_LIST_URL");
    }

    public String getSymptomCheckerConditionsLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_SC_CONDITIONS_URL");
    }

    public String getSymptomCheckerSymptomsLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_SC_SYMPTOMS_URL");
    }

    public String getSymptomCheckerMedicationsLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_SC_MEDICATION_URL");
    }

    public String getSymptomCheckerPreExistingConditionsLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_SC_PRE_EXISTING_CONDITIONS_URL");
    }

    public String getSymptomCheckerBodyTypeAheadLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_SC_BODY_TYPEAHEAD_URL");
    }

    public String getDrugImageOverrideURL() {
        return getMapForEnvironment().get("WBMD_DRUG_IMAGE_OVERRIDE_URL_FORMAT");
    }

    public String getConditionsListLink() {
        return getMapForEnvironment().get("WBMD_CONDITIONS_LINK_URL");
    }

    public String getArticleLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("ARTICLE_DOCUMENTUM_URL");
    }

    public String getTTSDrugsLink() {
        return getMapForEnvironment().get("WBMD_TTS_DRUGS_URL");
    }

    public String getTTSDrugsStagingLink() {
        return getMapForEnvironment().get("WBMD_TTS_DRUGS_STAGING_URL");
    }

    public String getSearchLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_SEARCH_URL");
    }

    public String getNewsLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_NEWS_URL");
    }

    public String getRelatedContentLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_RELATED_CONTENT_URL");
    }

    public String getQnaEditorialArticleLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_QNA_EDITORIAL_URL");
    }

    public String getQnaSponsoredArticleLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_QNA_SPONSORED_URL");
    }

    public String getNewsTopStoriesLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_NEWS_TOP_STORIES_URL");
    }

    public String getNewsCategoriesLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_NEWS_CATEGORIES_URL");
    }

    public String getBrowseSymptomsLink() {
        return getMapForEnvironment(getSearchEnvironment()).get("WBMD_SC_BROWSE_SYMPTOMS");
    }
}
