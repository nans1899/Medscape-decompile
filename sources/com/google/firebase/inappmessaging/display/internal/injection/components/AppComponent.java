package com.google.firebase.inappmessaging.display.internal.injection.components;

import com.google.firebase.inappmessaging.display.FirebaseInAppMessagingDisplay;
import com.google.firebase.inappmessaging.display.internal.FiamImageLoader;
import com.google.firebase.inappmessaging.display.internal.PicassoErrorListener;
import com.google.firebase.inappmessaging.display.internal.injection.modules.HeadlessInAppMessagingModule;
import com.google.firebase.inappmessaging.display.internal.injection.modules.PicassoModule;
import dagger.Component;

@Component(dependencies = {UniversalComponent.class}, modules = {HeadlessInAppMessagingModule.class, PicassoModule.class})
/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public interface AppComponent {
    FiamImageLoader fiamImageLoader();

    PicassoErrorListener picassoErrorListener();

    FirebaseInAppMessagingDisplay providesFirebaseInAppMessagingUI();
}
