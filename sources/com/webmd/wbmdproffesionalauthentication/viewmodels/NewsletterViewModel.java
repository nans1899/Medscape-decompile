package com.webmd.wbmdproffesionalauthentication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.util.Arrays;
import java.util.List;

public class NewsletterViewModel extends ViewModel {
    private MutableLiveData<String> countryCode = new MutableLiveData<>();
    private List<String> countryCodesToShowToggle = Arrays.asList(new String[]{"ac", "ic", "md", "fo", "gg", "je", "mk", "va", "bg", "by", "cz", "hu", "pl", "ro", "ru", "sk", "ua", "ax", "dk", "ee", "fi", "gb", "ie", "im", "is", "lt", "lv", "no", "se", "ad", "al", "ba", "es", "gi", "gr", "hr", "it", "me", "mt", AdParameterKeys.PRIMARY_ID, "rs", "si", "sm", "at", "be", "ch", "de", "fr", "li", "lu", "mc", "nl"});
    private MutableLiveData<Boolean> newsLetterToggleStatus = new MutableLiveData<>();
    private MutableLiveData<Boolean> showNewsletterToggle = new MutableLiveData<>();

    public NewsletterViewModel() {
        setCountryCode("us");
    }

    public void setCountryCode(String str) {
        if (str != null) {
            this.countryCode.setValue(str);
            this.showNewsletterToggle.setValue(Boolean.valueOf(this.countryCodesToShowToggle.contains(str)));
            setNewsLetterToggleStatus(false);
        }
    }

    public LiveData<Boolean> getShowNewsletterToggle() {
        return this.showNewsletterToggle;
    }

    public MutableLiveData<String> getCountryCode() {
        return this.countryCode;
    }

    public MutableLiveData<Boolean> getNewsLetterToggleStatus() {
        return this.newsLetterToggleStatus;
    }

    public void setNewsLetterToggleStatus(boolean z) {
        this.newsLetterToggleStatus.setValue(Boolean.valueOf(z));
        UserProfile.getInstance().setAddPromoFlag(this.showNewsletterToggle.getValue().booleanValue());
        UserProfile.getInstance().setPromoEmailOptOut(!z);
    }

    public List<String> getCountryCodesToShowToggle() {
        return this.countryCodesToShowToggle;
    }

    public void setShowNewsletterToggle(Boolean bool) {
        this.showNewsletterToggle.setValue(bool);
    }
}
