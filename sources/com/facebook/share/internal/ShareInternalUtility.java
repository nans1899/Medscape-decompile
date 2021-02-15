package com.facebook.share.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.NativeAppCallAttachmentStore;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.share.Sharer;
import com.facebook.share.internal.OpenGraphJSONUtility;
import com.facebook.share.model.CameraEffectTextures;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.LikeView;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ShareInternalUtility {
    public static final String MY_PHOTOS = "me/photos";
    private static final String MY_STAGING_RESOURCES = "me/staging_resources";
    private static final String STAGING_PARAM = "file";

    public static void invokeCallbackWithException(FacebookCallback<Sharer.Result> facebookCallback, Exception exc) {
        if (exc instanceof FacebookException) {
            invokeOnErrorCallback(facebookCallback, (FacebookException) exc);
            return;
        }
        invokeCallbackWithError(facebookCallback, "Error preparing share content: " + exc.getLocalizedMessage());
    }

    public static void invokeCallbackWithError(FacebookCallback<Sharer.Result> facebookCallback, String str) {
        invokeOnErrorCallback(facebookCallback, str);
    }

    public static void invokeCallbackWithResults(FacebookCallback<Sharer.Result> facebookCallback, String str, GraphResponse graphResponse) {
        FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            String errorMessage = error.getErrorMessage();
            if (Utility.isNullOrEmpty(errorMessage)) {
                errorMessage = "Unexpected error sharing.";
            }
            invokeOnErrorCallback(facebookCallback, graphResponse, errorMessage);
            return;
        }
        invokeOnSuccessCallback(facebookCallback, str);
    }

    public static String getNativeDialogCompletionGesture(Bundle bundle) {
        if (bundle.containsKey(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY)) {
            return bundle.getString(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY);
        }
        return bundle.getString(NativeProtocol.EXTRA_DIALOG_COMPLETION_GESTURE_KEY);
    }

    public static String getShareDialogPostId(Bundle bundle) {
        if (bundle.containsKey(ShareConstants.RESULT_POST_ID)) {
            return bundle.getString(ShareConstants.RESULT_POST_ID);
        }
        if (bundle.containsKey(ShareConstants.EXTRA_RESULT_POST_ID)) {
            return bundle.getString(ShareConstants.EXTRA_RESULT_POST_ID);
        }
        return bundle.getString(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID);
    }

    public static boolean handleActivityResult(int i, int i2, Intent intent, ResultProcessor resultProcessor) {
        AppCall appCallFromActivityResult = getAppCallFromActivityResult(i, i2, intent);
        if (appCallFromActivityResult == null) {
            return false;
        }
        NativeAppCallAttachmentStore.cleanupAttachmentsForCall(appCallFromActivityResult.getCallId());
        if (resultProcessor == null) {
            return true;
        }
        FacebookException exceptionFromErrorData = NativeProtocol.getExceptionFromErrorData(NativeProtocol.getErrorDataFromResultIntent(intent));
        if (exceptionFromErrorData == null) {
            resultProcessor.onSuccess(appCallFromActivityResult, NativeProtocol.getSuccessResultsFromIntent(intent));
        } else if (exceptionFromErrorData instanceof FacebookOperationCanceledException) {
            resultProcessor.onCancel(appCallFromActivityResult);
        } else {
            resultProcessor.onError(appCallFromActivityResult, exceptionFromErrorData);
        }
        return true;
    }

    public static ResultProcessor getShareResultProcessor(final FacebookCallback<Sharer.Result> facebookCallback) {
        return new ResultProcessor(facebookCallback) {
            public void onSuccess(AppCall appCall, Bundle bundle) {
                if (bundle != null) {
                    String nativeDialogCompletionGesture = ShareInternalUtility.getNativeDialogCompletionGesture(bundle);
                    if (nativeDialogCompletionGesture == null || "post".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                        ShareInternalUtility.invokeOnSuccessCallback(facebookCallback, ShareInternalUtility.getShareDialogPostId(bundle));
                    } else if ("cancel".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                        ShareInternalUtility.invokeOnCancelCallback(facebookCallback);
                    } else {
                        ShareInternalUtility.invokeOnErrorCallback((FacebookCallback<Sharer.Result>) facebookCallback, new FacebookException(NativeProtocol.ERROR_UNKNOWN_ERROR));
                    }
                }
            }

            public void onCancel(AppCall appCall) {
                ShareInternalUtility.invokeOnCancelCallback(facebookCallback);
            }

            public void onError(AppCall appCall, FacebookException facebookException) {
                ShareInternalUtility.invokeOnErrorCallback((FacebookCallback<Sharer.Result>) facebookCallback, facebookException);
            }
        };
    }

    private static AppCall getAppCallFromActivityResult(int i, int i2, Intent intent) {
        UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(intent);
        if (callIdFromIntent == null) {
            return null;
        }
        return AppCall.finishPendingCall(callIdFromIntent, i);
    }

    public static void registerStaticShareCallback(final int i) {
        CallbackManagerImpl.registerStaticCallback(i, new CallbackManagerImpl.Callback() {
            public boolean onActivityResult(int i, Intent intent) {
                return ShareInternalUtility.handleActivityResult(i, i, intent, ShareInternalUtility.getShareResultProcessor((FacebookCallback<Sharer.Result>) null));
            }
        });
    }

    public static void registerSharerCallback(final int i, CallbackManager callbackManager, final FacebookCallback<Sharer.Result> facebookCallback) {
        if (callbackManager instanceof CallbackManagerImpl) {
            ((CallbackManagerImpl) callbackManager).registerCallback(i, new CallbackManagerImpl.Callback() {
                public boolean onActivityResult(int i, Intent intent) {
                    return ShareInternalUtility.handleActivityResult(i, i, intent, ShareInternalUtility.getShareResultProcessor(facebookCallback));
                }
            });
            return;
        }
        throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
    }

    public static List<String> getPhotoUrls(SharePhotoContent sharePhotoContent, final UUID uuid) {
        List<SharePhoto> photos;
        if (sharePhotoContent == null || (photos = sharePhotoContent.getPhotos()) == null) {
            return null;
        }
        List<K> map = Utility.map(photos, new Utility.Mapper<SharePhoto, NativeAppCallAttachmentStore.Attachment>() {
            public NativeAppCallAttachmentStore.Attachment apply(SharePhoto sharePhoto) {
                return ShareInternalUtility.getAttachment(uuid, sharePhoto);
            }
        });
        List<String> map2 = Utility.map(map, new Utility.Mapper<NativeAppCallAttachmentStore.Attachment, String>() {
            public String apply(NativeAppCallAttachmentStore.Attachment attachment) {
                return attachment.getAttachmentUrl();
            }
        });
        NativeAppCallAttachmentStore.addAttachments(map);
        return map2;
    }

    public static String getVideoUrl(ShareVideoContent shareVideoContent, UUID uuid) {
        if (shareVideoContent == null || shareVideoContent.getVideo() == null) {
            return null;
        }
        NativeAppCallAttachmentStore.Attachment createAttachment = NativeAppCallAttachmentStore.createAttachment(uuid, shareVideoContent.getVideo().getLocalUrl());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(createAttachment);
        NativeAppCallAttachmentStore.addAttachments(arrayList);
        return createAttachment.getAttachmentUrl();
    }

    public static List<Bundle> getMediaInfos(ShareMediaContent shareMediaContent, final UUID uuid) {
        List<ShareMedia> media;
        if (shareMediaContent == null || (media = shareMediaContent.getMedia()) == null) {
            return null;
        }
        final ArrayList arrayList = new ArrayList();
        List<Bundle> map = Utility.map(media, new Utility.Mapper<ShareMedia, Bundle>() {
            public Bundle apply(ShareMedia shareMedia) {
                NativeAppCallAttachmentStore.Attachment access$000 = ShareInternalUtility.getAttachment(uuid, shareMedia);
                arrayList.add(access$000);
                Bundle bundle = new Bundle();
                bundle.putString("type", shareMedia.getMediaType().name());
                bundle.putString("uri", access$000.getAttachmentUrl());
                return bundle;
            }
        });
        NativeAppCallAttachmentStore.addAttachments(arrayList);
        return map;
    }

    public static Bundle getTextureUrlBundle(ShareCameraEffectContent shareCameraEffectContent, UUID uuid) {
        CameraEffectTextures textures;
        if (shareCameraEffectContent == null || (textures = shareCameraEffectContent.getTextures()) == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        for (String next : textures.keySet()) {
            NativeAppCallAttachmentStore.Attachment attachment = getAttachment(uuid, textures.getTextureUri(next), textures.getTextureBitmap(next));
            arrayList.add(attachment);
            bundle.putString(next, attachment.getAttachmentUrl());
        }
        NativeAppCallAttachmentStore.addAttachments(arrayList);
        return bundle;
    }

    public static JSONObject toJSONObjectForCall(final UUID uuid, ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        Set set;
        ShareOpenGraphAction action = shareOpenGraphContent.getAction();
        final ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = OpenGraphJSONUtility.toJSONObject(action, (OpenGraphJSONUtility.PhotoJSONProcessor) new OpenGraphJSONUtility.PhotoJSONProcessor() {
            public JSONObject toJSONObject(SharePhoto sharePhoto) {
                NativeAppCallAttachmentStore.Attachment access$000 = ShareInternalUtility.getAttachment(uuid, sharePhoto);
                if (access$000 == null) {
                    return null;
                }
                arrayList.add(access$000);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", access$000.getAttachmentUrl());
                    if (sharePhoto.getUserGenerated()) {
                        jSONObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                    }
                    return jSONObject;
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", (Throwable) e);
                }
            }
        });
        NativeAppCallAttachmentStore.addAttachments(arrayList);
        if (shareOpenGraphContent.getPlaceId() != null && Utility.isNullOrEmpty(jSONObject.optString("place"))) {
            jSONObject.put("place", shareOpenGraphContent.getPlaceId());
        }
        if (shareOpenGraphContent.getPeopleIds() != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(ContentParser.TAGS);
            if (optJSONArray == null) {
                set = new HashSet();
            } else {
                set = Utility.jsonArrayToSet(optJSONArray);
            }
            for (String add : shareOpenGraphContent.getPeopleIds()) {
                set.add(add);
            }
            jSONObject.put(ContentParser.TAGS, new JSONArray(set));
        }
        return jSONObject;
    }

    public static JSONObject toJSONObjectForWeb(ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return OpenGraphJSONUtility.toJSONObject(shareOpenGraphContent.getAction(), (OpenGraphJSONUtility.PhotoJSONProcessor) new OpenGraphJSONUtility.PhotoJSONProcessor() {
            public JSONObject toJSONObject(SharePhoto sharePhoto) {
                Uri imageUrl = sharePhoto.getImageUrl();
                if (Utility.isWebUri(imageUrl)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("url", imageUrl.toString());
                        return jSONObject;
                    } catch (JSONException e) {
                        throw new FacebookException("Unable to attach images", (Throwable) e);
                    }
                } else {
                    throw new FacebookException("Only web images may be used in OG objects shared via the web dialog");
                }
            }
        });
    }

    public static JSONArray removeNamespacesFromOGJsonArray(JSONArray jSONArray, boolean z) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = removeNamespacesFromOGJsonArray((JSONArray) obj, z);
            } else if (obj instanceof JSONObject) {
                obj = removeNamespacesFromOGJsonObject((JSONObject) obj, z);
            }
            jSONArray2.put(obj);
        }
        return jSONArray2;
    }

    public static JSONObject removeNamespacesFromOGJsonObject(JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONArray names = jSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                String string = names.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    obj = removeNamespacesFromOGJsonObject((JSONObject) obj, true);
                } else if (obj instanceof JSONArray) {
                    obj = removeNamespacesFromOGJsonArray((JSONArray) obj, true);
                }
                Pair<String, String> fieldNameAndNamespaceFromFullName = getFieldNameAndNamespaceFromFullName(string);
                String str = (String) fieldNameAndNamespaceFromFullName.first;
                String str2 = (String) fieldNameAndNamespaceFromFullName.second;
                if (z) {
                    if (str == null || !str.equals("fbsdk")) {
                        if (str != null) {
                            if (!str.equals("og")) {
                                jSONObject3.put(str2, obj);
                            }
                        }
                        jSONObject2.put(str2, obj);
                    } else {
                        jSONObject2.put(string, obj);
                    }
                } else if (str == null || !str.equals("fb")) {
                    jSONObject2.put(str2, obj);
                } else {
                    jSONObject2.put(string, obj);
                }
            }
            if (jSONObject3.length() > 0) {
                jSONObject2.put("data", jSONObject3);
            }
            return jSONObject2;
        } catch (JSONException unused) {
            throw new FacebookException("Failed to create json object from share content");
        }
    }

    public static Pair<String, String> getFieldNameAndNamespaceFromFullName(String str) {
        String str2;
        int i;
        int indexOf = str.indexOf(58);
        if (indexOf == -1 || str.length() <= (i = indexOf + 1)) {
            str2 = null;
        } else {
            str2 = str.substring(0, indexOf);
            str = str.substring(i);
        }
        return new Pair<>(str2, str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: android.net.Uri} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.graphics.Bitmap] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.internal.NativeAppCallAttachmentStore.Attachment getAttachment(java.util.UUID r3, com.facebook.share.model.ShareMedia r4) {
        /*
            boolean r0 = r4 instanceof com.facebook.share.model.SharePhoto
            r1 = 0
            if (r0 == 0) goto L_0x0013
            com.facebook.share.model.SharePhoto r4 = (com.facebook.share.model.SharePhoto) r4
            android.graphics.Bitmap r1 = r4.getBitmap()
            android.net.Uri r4 = r4.getImageUrl()
        L_0x000f:
            r2 = r1
            r1 = r4
            r4 = r2
            goto L_0x001f
        L_0x0013:
            boolean r0 = r4 instanceof com.facebook.share.model.ShareVideo
            if (r0 == 0) goto L_0x001e
            com.facebook.share.model.ShareVideo r4 = (com.facebook.share.model.ShareVideo) r4
            android.net.Uri r4 = r4.getLocalUrl()
            goto L_0x000f
        L_0x001e:
            r4 = r1
        L_0x001f:
            com.facebook.internal.NativeAppCallAttachmentStore$Attachment r3 = getAttachment(r3, r1, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.ShareInternalUtility.getAttachment(java.util.UUID, com.facebook.share.model.ShareMedia):com.facebook.internal.NativeAppCallAttachmentStore$Attachment");
    }

    private static NativeAppCallAttachmentStore.Attachment getAttachment(UUID uuid, Uri uri, Bitmap bitmap) {
        if (bitmap != null) {
            return NativeAppCallAttachmentStore.createAttachment(uuid, bitmap);
        }
        if (uri != null) {
            return NativeAppCallAttachmentStore.createAttachment(uuid, uri);
        }
        return null;
    }

    static void invokeOnCancelCallback(FacebookCallback<Sharer.Result> facebookCallback) {
        logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, (String) null);
        if (facebookCallback != null) {
            facebookCallback.onCancel();
        }
    }

    static void invokeOnSuccessCallback(FacebookCallback<Sharer.Result> facebookCallback, String str) {
        logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED, (String) null);
        if (facebookCallback != null) {
            facebookCallback.onSuccess(new Sharer.Result(str));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> facebookCallback, GraphResponse graphResponse, String str) {
        logShareResult("error", str);
        if (facebookCallback != null) {
            facebookCallback.onError(new FacebookGraphResponseException(graphResponse, str));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> facebookCallback, String str) {
        logShareResult("error", str);
        if (facebookCallback != null) {
            facebookCallback.onError(new FacebookException(str));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> facebookCallback, FacebookException facebookException) {
        logShareResult("error", facebookException.getMessage());
        if (facebookCallback != null) {
            facebookCallback.onError(facebookException);
        }
    }

    private static void logShareResult(String str, String str2) {
        InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(FacebookSdk.getApplicationContext());
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_SHARE_OUTCOME, str);
        if (str2 != null) {
            bundle.putString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, str2);
        }
        internalAppEventsLogger.logEventImplicitly(AnalyticsEvents.EVENT_SHARE_RESULT, bundle);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Bitmap bitmap, GraphRequest.Callback callback) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(STAGING_PARAM, bitmap);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, File file, GraphRequest.Callback callback) throws FileNotFoundException {
        GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType(ParcelFileDescriptor.open(file, 268435456), "image/png");
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(STAGING_PARAM, parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Uri uri, GraphRequest.Callback callback) throws FileNotFoundException {
        if (Utility.isFileUri(uri)) {
            return newUploadStagingResourceWithImageRequest(accessToken, new File(uri.getPath()), callback);
        }
        if (Utility.isContentUri(uri)) {
            GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType(uri, "image/png");
            Bundle bundle = new Bundle(1);
            bundle.putParcelable(STAGING_PARAM, parcelableResourceWithMimeType);
            return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback);
        }
        throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
    }

    public static LikeView.ObjectType getMostSpecificObjectType(LikeView.ObjectType objectType, LikeView.ObjectType objectType2) {
        if (objectType == objectType2) {
            return objectType;
        }
        if (objectType == LikeView.ObjectType.UNKNOWN) {
            return objectType2;
        }
        if (objectType2 == LikeView.ObjectType.UNKNOWN) {
            return objectType;
        }
        return null;
    }

    public static Bundle getStickerUrl(ShareStoryContent shareStoryContent, final UUID uuid) {
        if (shareStoryContent == null || shareStoryContent.getStickerAsset() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(shareStoryContent.getStickerAsset());
        List map = Utility.map(arrayList, new Utility.Mapper<SharePhoto, NativeAppCallAttachmentStore.Attachment>() {
            public NativeAppCallAttachmentStore.Attachment apply(SharePhoto sharePhoto) {
                return ShareInternalUtility.getAttachment(uuid, sharePhoto);
            }
        });
        List map2 = Utility.map(map, new Utility.Mapper<NativeAppCallAttachmentStore.Attachment, Bundle>() {
            public Bundle apply(NativeAppCallAttachmentStore.Attachment attachment) {
                Bundle bundle = new Bundle();
                bundle.putString("uri", attachment.getAttachmentUrl());
                String uriExtension = ShareInternalUtility.getUriExtension(attachment.getOriginalUri());
                if (uriExtension != null) {
                    Utility.putNonEmptyString(bundle, ShareConstants.MEDIA_EXTENSION, uriExtension);
                }
                return bundle;
            }
        });
        NativeAppCallAttachmentStore.addAttachments(map);
        return (Bundle) map2.get(0);
    }

    public static Bundle getBackgroundAssetMediaInfo(ShareStoryContent shareStoryContent, final UUID uuid) {
        if (shareStoryContent == null || shareStoryContent.getBackgroundAsset() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(shareStoryContent.getBackgroundAsset());
        final ArrayList arrayList2 = new ArrayList();
        List map = Utility.map(arrayList, new Utility.Mapper<ShareMedia, Bundle>() {
            public Bundle apply(ShareMedia shareMedia) {
                NativeAppCallAttachmentStore.Attachment access$000 = ShareInternalUtility.getAttachment(uuid, shareMedia);
                arrayList2.add(access$000);
                Bundle bundle = new Bundle();
                bundle.putString("type", shareMedia.getMediaType().name());
                bundle.putString("uri", access$000.getAttachmentUrl());
                String uriExtension = ShareInternalUtility.getUriExtension(access$000.getOriginalUri());
                if (uriExtension != null) {
                    Utility.putNonEmptyString(bundle, ShareConstants.MEDIA_EXTENSION, uriExtension);
                }
                return bundle;
            }
        });
        NativeAppCallAttachmentStore.addAttachments(arrayList2);
        return (Bundle) map.get(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r3 = r3.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getUriExtension(android.net.Uri r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r3 = r3.toString()
            r1 = 46
            int r1 = r3.lastIndexOf(r1)
            r2 = -1
            if (r1 != r2) goto L_0x0012
            return r0
        L_0x0012:
            java.lang.String r3 = r3.substring(r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.ShareInternalUtility.getUriExtension(android.net.Uri):java.lang.String");
    }
}
