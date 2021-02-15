package net.media.android.bidder.base.error;

public enum ErrorMessage {
    NO_FILL("No ad found"),
    INVALID_AD_UNIT_ID("Ad unit ID is not set or is not valid"),
    AD_VIEW_NOT_VISIBLE("Ad view is not visible"),
    ONGOING_AD_REQUEST("Only one Ad request is allowed at a time"),
    EMPTY_AD_SIZE("Ad size is not specified"),
    AD_SIZE_ERROR("Not enough space for ad to show. Expected width x height : %d x %d, got %d x %d"),
    NETWORK_ERROR("No Internet connection"),
    INTERNAL_ERROR("Internal error"),
    AD_LOAD_ERROR("Error while loading ad"),
    AD_ALREADY_SHOWN("Ad already shown"),
    AD_NOT_READY("Ad is not ready"),
    REWARD_NOT_SET("Reward must be set before making a request."),
    CANNOT_SET_REWARD_ON_ONGOING_REQUEST("Can not set reward on ongoing request."),
    CALL_PERMISSION_NOT_GRANTED("Permission for calling is not granted"),
    STORE_PICTURE_PERMISSION_NOT_GRANTED("Permission for writing/storing files is not granted");
    
    private final String message;

    private ErrorMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return this.message;
    }
}
