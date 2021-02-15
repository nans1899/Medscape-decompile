package com.wbmd.wbmddatacompliance.gdpr;

import android.content.Context;
import com.wbmd.wbmddatacompliance.callbacks.IEuCallback;
import com.wbmd.wbmddatacompliance.callbacks.IGDPRFailLoggingCallback;
import com.wbmd.wbmddatacompliance.callbacks.IShowAcceptDialogCallback;
import com.wbmd.wbmddatacompliance.http.GeoRepository;
import com.wbmd.wbmddatacompliance.http.IGeoService;
import com.wbmd.wbmddatacompliance.sharepreferences.SharedPreferenceManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GDPRStateManager {
    /* access modifiers changed from: private */
    public Context mContext;
    private IGDPRFailLoggingCallback mFailLogCallback;
    /* access modifiers changed from: private */
    public GDPRState mGDPRState;
    /* access modifiers changed from: private */
    public IShowAcceptDialogCallback mGeoCallback;
    private GeoRepository mGeoRepository;

    public GDPRStateManager(Context context) {
        this(context, (IGDPRFailLoggingCallback) null);
    }

    public GDPRStateManager(Context context, IGDPRFailLoggingCallback iGDPRFailLoggingCallback) {
        this.mGDPRState = GDPRState.getInstance(context);
        this.mContext = context;
        this.mFailLogCallback = iGDPRFailLoggingCallback;
        this.mGeoRepository = new GeoRepository((IGeoService) new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://www.webmd.com/").build().create(IGeoService.class), context, this.mFailLogCallback);
    }

    public GDPRStateManager(GDPRState gDPRState, GeoRepository geoRepository, Context context) {
        this.mGDPRState = gDPRState;
        this.mGeoRepository = geoRepository;
        this.mContext = context;
    }

    public void shouldShowAcceptancePrompt(IShowAcceptDialogCallback iShowAcceptDialogCallback) {
        this.mGeoCallback = iShowAcceptDialogCallback;
        if (this.mGDPRState.isAccepted()) {
            this.mGeoCallback.shouldShowAcceptancePromptResult(false);
        } else if (this.mGDPRState.isForceShowOverride()) {
            this.mGeoCallback.shouldShowAcceptancePromptResult(true);
        } else {
            checkGeoLocated();
        }
    }

    public static void onPromptAccepted(Context context) {
        GDPRState instance = GDPRState.getInstance(context);
        instance.setIsAccepted(true);
        new SharedPreferenceManager().saveGDRPState(instance, context);
    }

    private void checkGeoLocated() {
        if (!this.mGDPRState.isGeoLocated()) {
            try {
                this.mGeoRepository.makeGeoApiRequest(new IEuCallback() {
                    public void onIsEuResponse(boolean z) {
                        if (z) {
                            GDPRStateManager.this.mGDPRState.setGeoLocated(true);
                            GDPRStateManager.this.mGDPRState.setGeoCodeEU(true);
                            new SharedPreferenceManager().saveGDRPState(GDPRStateManager.this.mGDPRState, GDPRStateManager.this.mContext);
                            GDPRStateManager.this.mGeoCallback.shouldShowAcceptancePromptResult(true);
                            return;
                        }
                        GDPRStateManager.this.mGDPRState.setGeoLocated(true);
                        GDPRStateManager.this.mGDPRState.setGeoCodeEU(false);
                        new SharedPreferenceManager().saveGDRPState(GDPRStateManager.this.mGDPRState, GDPRStateManager.this.mContext);
                        GDPRStateManager.this.mGeoCallback.shouldShowAcceptancePromptResult(false);
                    }

                    public void onError() {
                        GDPRStateManager.this.mGeoCallback.shouldShowAcceptancePromptResult(false);
                    }
                });
            } catch (Exception e) {
                IGDPRFailLoggingCallback iGDPRFailLoggingCallback = this.mFailLogCallback;
                if (iGDPRFailLoggingCallback != null) {
                    iGDPRFailLoggingCallback.sendErrorLog((String) null, "checkGeoLocated_exception", e);
                }
                this.mGeoCallback.shouldShowAcceptancePromptResult(false);
            }
        } else {
            this.mGeoCallback.shouldShowAcceptancePromptResult(this.mGDPRState.isGeoCodeEU());
        }
    }
}
