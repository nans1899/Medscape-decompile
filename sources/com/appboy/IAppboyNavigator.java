package com.appboy;

import android.content.Context;
import com.appboy.ui.actions.NewsfeedAction;
import com.appboy.ui.actions.UriAction;

public interface IAppboyNavigator {
    void gotoNewsFeed(Context context, NewsfeedAction newsfeedAction);

    void gotoUri(Context context, UriAction uriAction);
}
