package com.medscape.android.activity.formulary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.activity.formulary.BrandModelViewHolder;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.DividerLineItem;
import com.medscape.android.contentviewer.interfaces.ILoadNextListener;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectBrandFragment extends Fragment {
    private static String BRAND_URL = "https://api.medscape.com/ws/services/formularyService/getInternalDrugsHavingFormularies?";
    private static final String BRNAD_PARA = "&response=application/json";
    private static final String CONTENT_PARA = "contentId=";
    private static final int SHOW_FORMULARY_NETWORK_ERROR_DIALOG = 109;
    private static final int SHOW_NO_FORMULARY_DIALOG = 110;
    public String TAG = "SelectBrand";
    RecyclerView brandListView;
    boolean isNextPageAvaialable;
    boolean isPreviousPageAvailable;
    private Activity mActivity;
    List<BrandModel> mBrandModelList;
    private String mContentId;
    private DividerLineItem[] mDividerLineItems;
    private String mDrugName;
    private MedscapeException mInternetRequiredException;
    private String mJsonString;
    LinearLayoutManager mLayoutManager;
    ILoadNextListener mLoadMoreListener;
    private ProgressBar mProgressBar;
    private View mRootView;
    /* access modifiers changed from: private */
    public SelectBrandAdapter mSelectBrandAdapter;
    int mTotalItemCount;
    boolean noFormulariesFound = false;

    public void finish() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.select_brand_formulary, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        String str;
        String str2;
        super.onActivityCreated(bundle);
        this.mInternetRequiredException = new MedscapeException(getResources().getString(R.string.internet_required));
        View view = getView();
        this.mRootView = view;
        if (view != null) {
            this.mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
            this.mRootView.findViewById(R.id.brandListView).setVisibility(8);
        }
        this.mActivity = getActivity();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mContentId = arguments.getInt(FormularyMyPlanPage.CONTENT_ID) + "";
            this.mDrugName = arguments.getString("drugName");
            if (arguments.getString("DIVIDER_PREVIOUS") == null) {
                str = "";
            } else {
                str = arguments.getString("DIVIDER_PREVIOUS");
            }
            if (arguments.getString("DIVIDER_NEXT") == null) {
                str2 = "";
            } else {
                str2 = arguments.getString("DIVIDER_NEXT");
            }
            if (str2 != null) {
                this.isNextPageAvaialable = !str2.equals("");
            }
            if (str != null) {
                this.isPreviousPageAvailable = !str.equals("");
            }
            DividerLineItem[] dividerLineItemArr = new DividerLineItem[2];
            this.mDividerLineItems = dividerLineItemArr;
            dividerLineItemArr[0] = new DividerLineItem((CrossLink) null, MedscapeApplication.get().getString(R.string.divider_pull_text), 0, false, false, true, false, 0, str, false);
            this.mDividerLineItems[1] = new DividerLineItem((CrossLink) null, MedscapeApplication.get().getString(R.string.divider_pull_text), 0, false, false, true, false, 0, str2, true);
            BrandModels brandModels = (BrandModels) arguments.getSerializable("BRAND_MODELS");
            List<BrandModel> brandModels2 = brandModels != null ? brandModels.getBrandModels() : new ArrayList<>();
            this.mBrandModelList = brandModels2;
            initializeBrandListView(brandModels2);
        }
    }

    /* access modifiers changed from: private */
    public void setFormularyData() {
        if (!Util.isOnline(getContext())) {
            this.mProgressBar.setVisibility(8);
            initializeBrandListView(new ArrayList());
            this.mInternetRequiredException.showSnackBar(this.mRootView, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
                public void onClick(View view) {
                    SelectBrandFragment.this.setFormularyData();
                }
            });
        } else if (!FormularyDatabaseHelper.getInstance(getContext()).isValidDatabse(getContext())) {
            this.mProgressBar.setVisibility(0);
            GetBrandListTask getBrandListTask = new GetBrandListTask();
            getBrandListTask.execute(new String[]{String.format(BRAND_URL + CONTENT_PARA + "%s" + BRNAD_PARA, new Object[]{this.mContentId})});
        } else {
            List<BrandModel> brandListFromContentId = BrandModel.getBrandListFromContentId(getContext(), this.mContentId);
            initializeBrandListView(brandListFromContentId);
            if (brandListFromContentId.size() == 0) {
                this.noFormulariesFound = true;
                Activity activity = this.mActivity;
                if (activity != null) {
                    activity.showDialog(110);
                }
            }
        }
    }

    public void onResume() {
        super.onResume();
        AppboyEventsHandler.logDailyEvent(getContext(), AppboyConstants.APPBOY_EVENT_FORMULARY_VIEWED, getActivity());
    }

    public void onPause() {
        super.onPause();
        MedscapeException medscapeException = this.mInternetRequiredException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!z) {
            MedscapeException medscapeException = this.mInternetRequiredException;
            if (medscapeException != null) {
                medscapeException.dismissSnackBar();
            }
        } else if (!Util.isOnline(getContext()) && this.mInternetRequiredException != null) {
            this.mProgressBar.setVisibility(8);
            this.mInternetRequiredException.showSnackBar(this.mRootView, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
                public void onClick(View view) {
                    SelectBrandFragment.this.setFormularyData();
                }
            });
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = ((DrugMonographMainActivity) getActivity()).onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i != 110) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.no_formulary_available)).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SelectBrandFragment.this.finish();
            }
        });
        return builder.create();
    }

    /* access modifiers changed from: private */
    public void onDownloadComplete() {
        View view = getView();
        if (view != null) {
            View findViewById = view.findViewById(R.id.progress);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            List<BrandModel> brandList = getBrandList(getJsonString());
            initializeBrandListView(brandList);
            if (brandList.size() == 0) {
                this.noFormulariesFound = true;
                if (getActivity() != null && (getActivity() instanceof DrugMonographMainActivity)) {
                    ((DrugMonographMainActivity) getActivity()).showDialogAsMessage(110);
                }
            }
        }
    }

    private String getJsonString() {
        return getmJsonString();
    }

    public void openFormuarlyDetailPage(BrandModel brandModel) {
        Intent intent = new Intent(getActivity(), FormularyMyPlanPage.class);
        intent.putExtra("TITLE", brandModel.getBrandName());
        intent.putExtra(FormularyMyPlanPage.FORMULARY_BRAND_ID_KEY, brandModel.getBrandId());
        intent.putExtra(FormularyMyPlanPage.CONTENT_ID, this.mContentId);
        intent.putExtra("brand_model_size", this.mBrandModelList.size());
        intent.putExtra("drugName", this.mDrugName);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
    }

    public List<BrandModel> getBrandList(String str) {
        ArrayList arrayList = new ArrayList();
        LogUtil.e(this.TAG, "getBrandList() = %s ", str);
        if (str == null) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            LogUtil.e(this.TAG, "Brand size = %s", Integer.valueOf(jSONArray.length()));
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                LogUtil.e(this.TAG, "Brand name = %s", jSONObject.getString("drugName"));
                arrayList.add(new BrandModel(jSONObject.getString("drugName"), jSONObject.getString("drugId")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static Fragment newInstance(ILoadNextListener iLoadNextListener) {
        SelectBrandFragment selectBrandFragment = new SelectBrandFragment();
        selectBrandFragment.mLoadMoreListener = iLoadNextListener;
        return selectBrandFragment;
    }

    public class SelectBrandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int VIEW_TYPE_BRAND_ITEM = 1;
        private BrandModelViewHolder.BrandListClickListener mBrandClickListener;
        private List<BrandModel> mBrandList;

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemViewType(int i) {
            return 1;
        }

        SelectBrandAdapter(List<BrandModel> list) {
            this.mBrandList = list;
            SelectBrandFragment.this.mTotalItemCount = list.size();
        }

        /* access modifiers changed from: package-private */
        public void setBrandClickListener(BrandModelViewHolder.BrandListClickListener brandListClickListener) {
            this.mBrandClickListener = brandListClickListener;
        }

        public BrandModel getItem(int i) {
            return this.mBrandList.get(i);
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new BrandModelViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reference_search_listview_row, viewGroup, false), this.mBrandClickListener);
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ((BrandModelViewHolder) viewHolder).bindItem(this.mBrandList.get(i));
        }

        public int getItemCount() {
            return SelectBrandFragment.this.mTotalItemCount;
        }
    }

    public String getmJsonString() {
        return this.mJsonString;
    }

    public void setmJsonString(String str) {
        this.mJsonString = str;
    }

    public class GetBrandListTask extends AsyncTask<String, Double, String> {
        public GetBrandListTask() {
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            String str = null;
            try {
                LogUtil.e(SelectBrandFragment.this.TAG, "doInBackground URL =%s", strArr[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                httpURLConnection.setReadTimeout(Util.TIMEOUT);
                InputStream inputStream = httpURLConnection.getInputStream();
                int contentLength = httpURLConnection.getContentLength();
                LogUtil.e(SelectBrandFragment.this.TAG, "doInBackground URL =%s", Integer.valueOf(contentLength));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = inputStream.read();
                    if (read != -1) {
                        byteArrayOutputStream.write(read);
                    } else {
                        String str2 = new String(byteArrayOutputStream.toByteArray());
                        try {
                            LogUtil.e(SelectBrandFragment.this.TAG, "jsonString jsonString =%s", str2);
                            byteArrayOutputStream.flush();
                            byteArrayOutputStream.close();
                            inputStream.close();
                            return str2;
                        } catch (SocketException e) {
                            e = e;
                            str = str2;
                        } catch (SocketTimeoutException e2) {
                            e = e2;
                            str = str2;
                            e.printStackTrace();
                            return str;
                        } catch (UnknownHostException e3) {
                            e = e3;
                            str = str2;
                            e.printStackTrace();
                            return str;
                        } catch (FileNotFoundException e4) {
                            e = e4;
                            str = str2;
                            e.printStackTrace();
                            return str;
                        } catch (Exception e5) {
                            e = e5;
                            str = str2;
                            LogUtil.e(getClass().getName(), "message = %s", e.getMessage());
                            return str;
                        }
                    }
                }
            } catch (SocketException e6) {
                e = e6;
                e.printStackTrace();
                return str;
            } catch (SocketTimeoutException e7) {
                e = e7;
                e.printStackTrace();
                return str;
            } catch (UnknownHostException e8) {
                e = e8;
                e.printStackTrace();
                return str;
            } catch (FileNotFoundException e9) {
                e = e9;
                e.printStackTrace();
                return str;
            } catch (Exception e10) {
                e = e10;
                LogUtil.e(getClass().getName(), "message = %s", e.getMessage());
                return str;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            if (str != null) {
                SelectBrandFragment.this.setmJsonString(str.substring(str.indexOf("["), str.indexOf("]") + 1).trim());
                SelectBrandFragment.this.onDownloadComplete();
            } else if (!SelectBrandFragment.this.getActivity().isFinishing()) {
                SelectBrandFragment.this.getActivity().showDialog(109);
            }
        }
    }

    public void initializeBrandListView(List<BrandModel> list) {
        if (getView() == null) {
            return;
        }
        if (list != null && list.size() > 1) {
            this.mSelectBrandAdapter = new SelectBrandAdapter(list);
            RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.brandRecyclerViewList);
            this.brandListView = recyclerView;
            recyclerView.setVisibility(0);
            this.mSelectBrandAdapter.setBrandClickListener(new BrandModelViewHolder.BrandListClickListener() {
                public void onBrandItemClicked(int i) {
                    SelectBrandFragment selectBrandFragment = SelectBrandFragment.this;
                    selectBrandFragment.openFormuarlyDetailPage(selectBrandFragment.mSelectBrandAdapter.getItem(i));
                }
            });
            this.mLayoutManager = new LinearLayoutManager(getActivity());
            this.brandListView.setAdapter(this.mSelectBrandAdapter);
            this.brandListView.setLayoutManager(this.mLayoutManager);
        } else if (list != null && list.get(0) != null) {
            openFormuarlyDetailPage(list.get(0));
        }
    }
}
