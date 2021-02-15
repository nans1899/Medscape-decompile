package com.medscape.android.consult.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IProfileTabChangedListener;
import com.medscape.android.consult.models.ConsultUser;

public class ConsultProfileTabViewHolder extends RecyclerView.ViewHolder {
    static final String TAG = ConsultProfileTabViewHolder.class.getSimpleName();
    private Context mContext;
    /* access modifiers changed from: private */
    public IProfileTabChangedListener mTabChangedListener;
    private TabLayout mTabLayout;

    public ConsultProfileTabViewHolder(View view, Context context, IProfileTabChangedListener iProfileTabChangedListener) {
        super(view);
        this.mTabChangedListener = iProfileTabChangedListener;
        this.mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        this.mContext = context;
    }

    public void bindPost(Object obj, int i, boolean z) {
        if (obj != null && (obj instanceof ConsultUser)) {
            setTabLayout((ConsultUser) obj, i);
            setTabLayoutEnabled(z);
        }
    }

    /* access modifiers changed from: private */
    public void setTabLayoutEnabled(boolean z) {
        LinearLayout linearLayout = (LinearLayout) this.mTabLayout.getChildAt(0);
        linearLayout.setEnabled(z);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            linearLayout.getChildAt(i).setClickable(z);
        }
    }

    private void setTabLayout(ConsultUser consultUser, int i) {
        if (this.mTabLayout != null) {
            setUpPostsTab(consultUser, i);
            setUpResponsesTab(consultUser, i);
            setUpFollowingTab(consultUser, i);
            setUpFollowersTab(consultUser, i);
            this.mTabLayout.setTabGravity(0);
            addTabChangedListener();
        }
    }

    private void setUpFollowersTab(ConsultUser consultUser, int i) {
        if (this.mTabLayout.getTabCount() == 3) {
            TabLayout tabLayout = this.mTabLayout;
            tabLayout.addTab(tabLayout.newTab());
        }
        TabLayout.Tab tabAt = this.mTabLayout.getTabAt(3);
        if (tabAt != null) {
            if (i == 3) {
                tabAt.select();
            }
            if (tabAt.getCustomView() == null) {
                tabAt.setCustomView((int) R.layout.profile_tab_layout);
            }
            View customView = tabAt.getCustomView();
            if (customView != null) {
                ((TextView) customView.findViewById(R.id.tab_count)).setText(String.format("%s" + consultUser.getFollowerCount(), new Object[]{""}));
                ((TextView) customView.findViewById(R.id.tab_title)).setText(this.mContext.getString(R.string.profile_followers));
            }
        }
    }

    private void setUpFollowingTab(ConsultUser consultUser, int i) {
        if (this.mTabLayout.getTabCount() == 2) {
            TabLayout tabLayout = this.mTabLayout;
            tabLayout.addTab(tabLayout.newTab());
        }
        TabLayout.Tab tabAt = this.mTabLayout.getTabAt(2);
        if (tabAt != null) {
            if (i == 2) {
                tabAt.select();
            }
            if (tabAt.getCustomView() == null) {
                tabAt.setCustomView((int) R.layout.profile_tab_layout);
            }
            View customView = tabAt.getCustomView();
            if (customView != null) {
                ((TextView) customView.findViewById(R.id.tab_count)).setText(String.format("%s" + consultUser.getFollowingCount(), new Object[]{""}));
                ((TextView) customView.findViewById(R.id.tab_title)).setText(this.mContext.getString(R.string.profile_following));
            }
        }
    }

    private void setUpResponsesTab(ConsultUser consultUser, int i) {
        if (this.mTabLayout.getTabCount() == 1) {
            TabLayout tabLayout = this.mTabLayout;
            tabLayout.addTab(tabLayout.newTab());
        }
        TabLayout.Tab tabAt = this.mTabLayout.getTabAt(1);
        if (tabAt != null) {
            if (i == 1) {
                tabAt.select();
            }
            if (tabAt.getCustomView() == null) {
                tabAt.setCustomView((int) R.layout.profile_tab_layout);
            }
            View customView = tabAt.getCustomView();
            if (customView != null) {
                ((TextView) customView.findViewById(R.id.tab_count)).setText(String.format("%s" + consultUser.getResponsesCount(), new Object[]{""}));
                ((TextView) customView.findViewById(R.id.tab_title)).setText(this.mContext.getString(R.string.profile_responses));
            }
        }
    }

    private void setUpPostsTab(ConsultUser consultUser, int i) {
        if (this.mTabLayout.getTabCount() == 0) {
            TabLayout tabLayout = this.mTabLayout;
            tabLayout.addTab(tabLayout.newTab());
        }
        TabLayout.Tab tabAt = this.mTabLayout.getTabAt(0);
        if (tabAt != null) {
            if (i == 0) {
                tabAt.select();
            }
            if (tabAt.getCustomView() == null) {
                tabAt.setCustomView((int) R.layout.profile_tab_layout);
            }
            View customView = tabAt.getCustomView();
            if (customView != null) {
                ((TextView) customView.findViewById(R.id.tab_count)).setText(String.format("%s" + consultUser.getPostCount(), new Object[]{""}));
                ((TextView) customView.findViewById(R.id.tab_title)).setText(this.mContext.getString(R.string.profile_posts));
            }
        }
    }

    private void addTabChangedListener() {
        TabLayout tabLayout = this.mTabLayout;
        if (tabLayout != null) {
            tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
                public void onTabReselected(TabLayout.Tab tab) {
                }

                public void onTabUnselected(TabLayout.Tab tab) {
                }

                public void onTabSelected(TabLayout.Tab tab) {
                    ConsultProfileTabViewHolder.this.setTabLayoutEnabled(false);
                    ConsultProfileTabViewHolder.this.mTabChangedListener.onTabChanged(tab.getPosition());
                }
            });
        }
    }
}
