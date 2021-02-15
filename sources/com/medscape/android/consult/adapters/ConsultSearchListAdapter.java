package com.medscape.android.consult.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.consult.interfaces.IPostDeletedListener;
import com.medscape.android.consult.interfaces.ISetVisibilityOfLowQualityPosts;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultTag;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.consult.viewholders.ConsultDummyViewHolder;
import com.medscape.android.consult.viewholders.ConsultHeaderViewHolder;
import com.medscape.android.consult.viewholders.ConsultLoadMoreViewHolder;
import com.medscape.android.consult.viewholders.ConsultSponsoredPostViewHolder;
import com.medscape.android.consult.viewholders.ConsultTagViewHolder;
import com.medscape.android.consult.viewholders.ConsultTextViewHolder;
import com.medscape.android.consult.viewholders.ConsultTimelinePostBodyViewHolder;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class ConsultSearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ISetVisibilityOfLowQualityPosts {
    private static final int DEFAULT_ITEM_MULTIPLIER = 1;
    private static final int ITEM_MULTIPLIER_FOR_POST = 2;
    private static final int VIEW_TYPE_DEFAULT = 6;
    private static final int VIEW_TYPE_POST_BODY = 2;
    private static final int VIEW_TYPE_POST_HEADER = 1;
    private static final int VIEW_TYPE_SEARCH_LIST_EMPTY = 0;
    private static final int VIEW_TYPE_SEARCH_LIST_EMPTY_NO_TEXT = 3;
    private static final int VIEW_TYPE_SEARCH_LIST_LOAD_MORE = 4;
    private static final int VIEW_TYPE_SPONSORED_POST = 7;
    private static final int VIEW_TYPE_TAG = 5;
    private Activity mContext;
    private int mCurrentTab = 0;
    private int mItemMultiplier = 1;
    protected List<Object> mItems = new ArrayList();
    private int mListItemCount = 0;
    private ILoadMoreListener mLoadMoreListener;
    protected ConsultPost mSponsoredPost;
    protected int sponsoredPostPosition = -1;

    public ConsultSearchListAdapter(Activity activity, ILoadMoreListener iLoadMoreListener) {
        this.mContext = activity;
        this.mLoadMoreListener = iLoadMoreListener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            return new ConsultDummyViewHolder(from.inflate(R.layout.consult_profile_list_empty, viewGroup, false));
        }
        if (i == 5) {
            return new ConsultTagViewHolder(from.inflate(R.layout.consult_tag_layout, viewGroup, false), this.mContext);
        }
        if (i == 1) {
            return new ConsultHeaderViewHolder(from.inflate(R.layout.consult_timeline_header, viewGroup, false), this.mContext);
        }
        if (i == 2) {
            return new ConsultTimelinePostBodyViewHolder(from.inflate(R.layout.consult_timeline_body, viewGroup, false), this.mContext, new IPostDeletedListener() {
                public void onPostDeleted(int i) {
                    if (i > 0) {
                        ConsultSearchListAdapter.this.mItems.remove(i);
                        ConsultSearchListAdapter.this.mItems.remove(i - 1);
                        if (ConsultSearchListAdapter.this.mItems.size() == 0) {
                            ConsultSearchListAdapter.this.updateListWithSearchItems(new ArrayList(), (String) null);
                        } else {
                            ConsultSearchListAdapter.this.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if (i == 7) {
            return new ConsultSponsoredPostViewHolder(from.inflate(R.layout.consult_sponser_post, viewGroup, false), this.mContext, -1);
        }
        if (i == 3) {
            View inflate = from.inflate(R.layout.consult_profile_list_empty, viewGroup, false);
            inflate.findViewById(R.id.empty_list).setVisibility(8);
            return new ConsultTextViewHolder(inflate);
        } else if (i == 4) {
            return new ConsultLoadMoreViewHolder(from.inflate(R.layout.consult_list_load_more, viewGroup, false), this.mLoadMoreListener);
        } else {
            return new ConsultDummyViewHolder(from.inflate(R.layout.consult_list_loading, viewGroup, false));
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ConsultLoadMoreViewHolder) {
            ((ConsultLoadMoreViewHolder) viewHolder).bindText(this.mContext.getResources().getString(R.string.consult_more_results));
        } else if (viewHolder instanceof ConsultDummyViewHolder) {
            ((ConsultDummyViewHolder) viewHolder).bindText(this.mContext, this.mCurrentTab);
        } else if (i >= this.mItems.size()) {
        } else {
            if (this.mItems.get(i) instanceof ConsultPost) {
                if (viewHolder instanceof ConsultHeaderViewHolder) {
                    ((ConsultHeaderViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), i, this);
                } else if (viewHolder instanceof ConsultSponsoredPostViewHolder) {
                    ((ConsultSponsoredPostViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), this);
                } else if (viewHolder instanceof ConsultTimelinePostBodyViewHolder) {
                    ((ConsultTimelinePostBodyViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), i, this);
                }
            } else if (this.mItems.get(i) instanceof ConsultUser) {
                if (viewHolder instanceof ConsultHeaderViewHolder) {
                    ((ConsultHeaderViewHolder) viewHolder).bindPost((ConsultUser) this.mItems.get(i));
                }
            } else if (this.mItems.get(i) instanceof ConsultTag) {
                if (viewHolder instanceof ConsultTagViewHolder) {
                    ((ConsultTagViewHolder) viewHolder).bindPost(((ConsultTag) this.mItems.get(i)).getUniqueIdentifier());
                }
            } else if ((this.mItems.get(i) instanceof String) && ((String) this.mItems.get(i)).equalsIgnoreCase(Constants.CONSULT_LIST_ERROR) && (viewHolder instanceof ConsultTextViewHolder)) {
                ((ConsultTextViewHolder) viewHolder).bindPost(getMessageForEmptyList());
            }
        }
    }

    public void refreshCurrentTab(int i) {
        this.mCurrentTab = i;
        setMultiplierForCurrentTab(i);
    }

    public void setMultiplierForCurrentTab(int i) {
        this.mCurrentTab = i;
        if (i == 0) {
            this.mItemMultiplier = 2;
        } else {
            this.mItemMultiplier = 1;
        }
    }

    private String getMessageForEmptyList() {
        String string = this.mContext.getString(R.string.consult_profile_no_posts);
        int i = this.mCurrentTab;
        if (i == 1) {
            return this.mContext.getString(R.string.consult_profile_not_following);
        }
        return i == 2 ? this.mContext.getString(R.string.consult_profile_no_followers) : string;
    }

    public int getItemCount() {
        List<Object> list = this.mItems;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return this.mItems.size();
    }

    public void setListItemCount(int i) {
        this.mListItemCount = i;
    }

    public int getItemViewType(int i) {
        if (this.mItems.get(i) instanceof ConsultPost) {
            return getViewTypeForConsultPost((ConsultPost) this.mItems.get(i));
        }
        if (this.mItems.get(i) instanceof ConsultUser) {
            return getViewTypeForConsultUser((ConsultUser) this.mItems.get(i));
        }
        if (this.mItems.get(i) instanceof ConsultTag) {
            return 5;
        }
        if (this.mItems.get(i) instanceof String) {
            return getViewTypeForString((String) this.mItems.get(i));
        }
        return 6;
    }

    private int getViewTypeForConsultPost(ConsultPost consultPost) {
        String postId = consultPost.getPostId();
        if (consultPost.isShowSponsored()) {
            return 7;
        }
        if (!StringUtil.isNotEmpty(postId)) {
            return 6;
        }
        if (postId.equalsIgnoreCase(Constants.CONSULT_LIST_EMPTY)) {
            return 0;
        }
        return consultPost.isHeader() ? 1 : 2;
    }

    private int getViewTypeForConsultUser(ConsultUser consultUser) {
        String userId = consultUser.getUserId();
        if (StringUtil.isNotEmpty(userId)) {
            return userId.equalsIgnoreCase(Constants.CONSULT_LIST_EMPTY) ? 0 : 1;
        }
        return 6;
    }

    private int getViewTypeForString(String str) {
        if (str.equalsIgnoreCase(Constants.CONSULT_LIST_ERROR)) {
            return 3;
        }
        if (!str.equalsIgnoreCase(Constants.CONSULT_LIST_MORE_ERROR) && !str.equalsIgnoreCase(Constants.CONSULT_LIST_MORE)) {
            return 6;
        }
        return 4;
    }

    public void setItems(List<Object> list) {
        this.mItems = list;
        notifyDataSetChanged();
    }

    public void updateListWithSearchItems(List<ConsultFeedItem> list, String str) {
        if (list == null) {
            if (StringUtil.isNotEmpty(str)) {
                refreshDataForMessage(str);
            }
        } else if (list.size() == 0) {
            showNoResults();
        } else if (this.mItems != null) {
            this.mItems = new ArrayList();
            setUpItemsFromList(list);
            int i = 0;
            if (list.size() != this.mListItemCount) {
                this.mItems.add(Constants.CONSULT_LIST_MORE);
                i = 1;
            }
            if (this.mCurrentTab == 1) {
                notifyDataSetChanged();
                return;
            }
            int size = this.mItems.size();
            int i2 = this.mItemMultiplier;
            if (size <= (i2 * 10) + i) {
                notifyDataSetChanged();
                return;
            }
            if (i2 * 10 >= this.mItems.size()) {
                this.mItems.size();
            }
            notifyItemRangeChanged(this.mItems.size() - (this.mItemMultiplier * 10), this.mItems.size());
        }
    }

    private void setUpItemsFromList(List<ConsultFeedItem> list) {
        ConsultPost consultPost;
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            ConsultFeedItem consultFeedItem = list.get(i);
            if (consultFeedItem instanceof ConsultPost) {
                ConsultPost consultPost2 = (ConsultPost) consultFeedItem;
                if (this.sponsoredPostPosition == -1 && i > 1 && !z && !ConsultUtils.isPartnerPost(consultPost2)) {
                    this.sponsoredPostPosition = this.mItems.size();
                }
                z = ConsultUtils.isPartnerPost(consultPost2);
                addItemsForPost(consultPost2);
            } else if (!(consultFeedItem instanceof ConsultUser)) {
                this.mItems.add(consultFeedItem);
            } else if (!ConsultUtils.isSponsoredUser((ConsultUser) consultFeedItem)) {
                this.mItems.add(consultFeedItem);
            }
        }
        if (this.mCurrentTab == 0 && (consultPost = this.mSponsoredPost) != null) {
            addSponsoredPost(consultPost);
        }
        if (this.mCurrentTab == 2 && this.mItems.size() == 0) {
            showNoResults();
        }
    }

    private void addItemsForPost(ConsultPost consultPost) {
        if (!consultPost.isShowSponsored()) {
            ConsultPost consultPost2 = new ConsultPost();
            consultPost2.setPoster(consultPost.getPoster());
            consultPost2.setTimestamp(consultPost.getTimestamp());
            consultPost2.setIsLowQualityPost(consultPost.isLowQualityPost());
            consultPost2.setIsLowQualityPostShown(consultPost.isLowQualityPostShown());
            consultPost2.setDownVoteCount(consultPost.getDownVoteCount());
            consultPost2.setPostId(Constants.CONSULT_POST_HEADER);
            consultPost2.setIsHeader(true);
            this.mItems.add(consultPost2);
        }
        this.mItems.add(consultPost);
    }

    private void showNoResults() {
        List<Object> list = this.mItems;
        if (list != null && list.size() == 0) {
            ConsultPost consultPost = new ConsultPost();
            consultPost.setPostId(Constants.CONSULT_LIST_EMPTY);
            this.mItems.add(consultPost);
            notifyDataSetChanged();
        }
    }

    private void refreshDataForMessage(String str) {
        List<Object> list;
        if (str.equalsIgnoreCase(Constants.CONSULT_LIST_ERROR)) {
            List<Object> list2 = this.mItems;
            if (list2 == null || list2.size() <= 0) {
                List<Object> list3 = this.mItems;
                if (list3 != null && list3.size() == 0) {
                    this.mItems.add(Constants.CONSULT_LIST_ERROR);
                    notifyDataSetChanged();
                    return;
                }
                return;
            }
            List<Object> list4 = this.mItems;
            list4.set(list4.size() - 1, Constants.CONSULT_LIST_MORE_ERROR);
            notifyItemChanged(this.mItems.size() - 1);
        } else if (str.equalsIgnoreCase(Constants.CONSULT_LIST_LOADING) && (list = this.mItems) != null && list.size() > 0) {
            List<Object> list5 = this.mItems;
            list5.set(list5.size() - 1, Constants.CONSULT_LIST_LOADING);
            notifyItemChanged(this.mItems.size() - 1);
        }
    }

    public void setVisibilityOfPost(int i, boolean z) {
        Object obj = this.mItems.get(i);
        if (obj instanceof ConsultPost) {
            ((ConsultPost) obj).setIsLowQualityPostShown(z);
        }
        Object obj2 = this.mItems.get(i + 1);
        if (obj2 instanceof ConsultPost) {
            ((ConsultPost) obj2).setIsLowQualityPostShown(z);
            notifyItemRangeChanged(i, 3);
        }
    }

    public boolean addSponsoredPost(ConsultPost consultPost) {
        int i;
        List<Object> list = this.mItems;
        if (list == null || list.size() <= (i = this.sponsoredPostPosition) || i <= 0) {
            this.mSponsoredPost = consultPost;
            return false;
        }
        if (!(this.mItems.get(i) instanceof ConsultPost) || !((ConsultPost) this.mItems.get(this.sponsoredPostPosition)).isFromAD() || !((ConsultPost) this.mItems.get(this.sponsoredPostPosition)).isShowSponsored()) {
            this.mItems.add(this.sponsoredPostPosition, consultPost);
        } else {
            this.mItems.set(this.sponsoredPostPosition, consultPost);
        }
        this.mSponsoredPost = null;
        return true;
    }

    public void saveSponsoredPost() {
        int i;
        List<Object> list = this.mItems;
        if (list != null && list.size() > (i = this.sponsoredPostPosition) && i > 0 && (this.mItems.get(i) instanceof ConsultPost) && ((ConsultPost) this.mItems.get(this.sponsoredPostPosition)).isShowSponsored()) {
            this.mSponsoredPost = (ConsultPost) this.mItems.get(this.sponsoredPostPosition);
        }
    }

    public void resetSponsoredPost() {
        this.mSponsoredPost = null;
        this.sponsoredPostPosition = -1;
    }
}
