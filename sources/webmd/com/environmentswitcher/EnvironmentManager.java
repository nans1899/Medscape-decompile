package webmd.com.environmentswitcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.medscape.android.EnvironmentConfig;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class EnvironmentManager {
    private static final String HASH_ALGORITHM = "HmacSHA256";
    static String TAG = EnvironmentManager.class.getSimpleName();
    private static EnvironmentManagerData environmentData;
    private static Context mContext;
    private static EnvironmentManager mManager;

    private EnvironmentManager() {
    }

    public void saveEnvironment(String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
        edit.putString(Constants.pref_switcher_option, str);
        edit.commit();
    }

    public String getSavedEnvironment() {
        return PreferenceManager.getDefaultSharedPreferences(mContext).getString(Constants.pref_switcher_option, EnvironmentConfig.ENV_PROD);
    }

    public static EnvironmentManager getInstance(Context context) {
        if (mManager == null) {
            mManager = new EnvironmentManager();
        }
        mManager.setContext(context);
        environmentData = new EnvironmentManagerData(mContext);
        return mManager;
    }

    private void setContext(Context context) {
        mContext = context;
    }

    public String getClientSecretHashForTimestamp(long j) {
        return hashString(String.format("{{timestamp:%s,client_id:%s}}", new Object[]{Long.valueOf(j), getClientId()}), HASH_ALGORITHM, environmentData.getSecretId());
    }

    public String getClientId() {
        return environmentData.getClientId();
    }

    public String getSecretId() {
        return environmentData.getSecretId();
    }

    public String getSearchClientId() {
        return environmentData.getSearchClientId();
    }

    public String getSearchSecretId() {
        return environmentData.getSearchSecretId();
    }

    public String getRequestLink(String str, EnvironmentManagerData environmentManagerData) {
        if (environmentManagerData != null) {
            return environmentManagerData.getRequestLink(str);
        }
        return environmentData.getRequestLink(str);
    }

    public String getRequestLink(String str) {
        return environmentData.getRequestLink(str);
    }

    public String getSymptomCheckerConditionsLink() {
        return environmentData.getSymptomCheckerConditionsLink();
    }

    public String getSymptomCheckerSymptomsLink() {
        return environmentData.getSymptomCheckerSymptomsLink();
    }

    public String getSymptomCheckerMedicationsLink() {
        return environmentData.getSymptomCheckerMedicationsLink();
    }

    public String getSymptomCheckerPreExistingConditionsLink() {
        return environmentData.getSymptomCheckerPreExistingConditionsLink();
    }

    public String getSymptomCheckerBodyTypeAheadLink() {
        return environmentData.getSymptomCheckerBodyTypeAheadLink();
    }

    public String getMonographLink() {
        return environmentData.getMonographLink();
    }

    public String getDrugListLink() {
        return environmentData.getDrugListLink();
    }

    public String getDrugImageOverrideURL() {
        return environmentData.getDrugImageOverrideURL();
    }

    public String getConditionsListLink() {
        return environmentData.getConditionsListLink();
    }

    public String getTTSDrugsLink() {
        return environmentData.getTTSDrugsLink();
    }

    public String getTTSDrugsStagingLink() {
        return environmentData.getTTSDrugsStagingLink();
    }

    public String getDrugFormUrl() {
        return environmentData.getDrugFormUrl();
    }

    public String getRxTypeAheadLink() {
        return environmentData.getRxTypeAheadLink();
    }

    public String getDrugPricingUrl() {
        return environmentData.getDrugPricingUrl();
    }

    public String getRxBaseUrl() {
        return environmentData.getRxBaseUrl();
    }

    public String getArticleLink() {
        return environmentData.getArticleLink();
    }

    public String getQnaEditorialArticleLink() {
        return environmentData.getQnaEditorialArticleLink();
    }

    public String getQnaSponsoredArticleLink() {
        return environmentData.getQnaSponsoredArticleLink();
    }

    private String hashString(String str, String str2, String str3) {
        try {
            Mac instance = Mac.getInstance(str2);
            instance.init(new SecretKeySpec(str3.getBytes(), str2));
            return Base64.encodeToString(instance.doFinal(str.getBytes()), 2);
        } catch (Exception unused) {
            return null;
        }
    }

    public Map<String, String> getAuthHeaders() {
        long currentTimeMillis = System.currentTimeMillis();
        String clientSecretHashForTimestamp = getClientSecretHashForTimestamp(currentTimeMillis);
        HashMap hashMap = new HashMap();
        hashMap.put("enc_data", clientSecretHashForTimestamp);
        hashMap.put("timestamp", String.format("%s", new Object[]{Long.valueOf(currentTimeMillis)}));
        hashMap.put("client_id", getClientId());
        hashMap.put("Accept", AbstractSpiCall.ACCEPT_JSON_VALUE);
        return hashMap;
    }

    public String getNewsTopStoriesLink() {
        return environmentData.getNewsTopStoriesLink();
    }

    public String getNewsCategoriesLink() {
        return environmentData.getNewsCategoriesLink();
    }

    public String getNewsLink() {
        return environmentData.getNewsLink();
    }

    public String getRelatedContentLink() {
        return environmentData.getRelatedContentLink();
    }

    public String getSearchLink() {
        return environmentData.getSearchLink();
    }

    public String getBrowseSymptomsLink() {
        return environmentData.getBrowseSymptomsLink();
    }
}
