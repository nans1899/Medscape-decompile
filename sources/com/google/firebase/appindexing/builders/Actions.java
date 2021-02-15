package com.google.firebase.appindexing.builders;

import com.google.firebase.appindexing.Action;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class Actions {
    public static Action newView(String str, String str2) {
        return new Action.Builder(Action.Builder.VIEW_ACTION).setObject(str, str2).build();
    }
}
