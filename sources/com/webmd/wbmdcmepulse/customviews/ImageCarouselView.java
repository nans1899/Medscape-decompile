package com.webmd.wbmdcmepulse.customviews;

import android.content.Context;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import com.squareup.picasso.Picasso;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import java.util.List;

public class ImageCarouselView extends AppCompatImageView {
    private final String TAG = ImageCarouselView.class.getSimpleName();
    private Context mContext;
    private int mCurrentImageIndex;
    private FragmentManager mFragmentManager;
    private List<String> mImageUrls;
    private ImageView mImageView;
    private boolean mIsFirstRotation = true;

    public ImageCarouselView(Context context, ImageView imageView, List<String> list, FragmentManager fragmentManager) {
        super(context);
        this.mContext = context;
        this.mImageUrls = list;
        this.mCurrentImageIndex = 0;
        this.mFragmentManager = fragmentManager;
        this.mImageView = imageView;
    }

    public void setCurrentImage() throws Exception {
        try {
            if (this.mImageUrls != null) {
                if (this.mImageUrls.size() > 0) {
                    setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.cme_placeholder_image));
                    setVisibility(0);
                    setTag(this.mImageUrls.get(this.mCurrentImageIndex));
                    String str = this.TAG;
                    Trace.d(str, "Carousel Image: " + this.mImageUrls.get(this.mCurrentImageIndex));
                    if (this.mCurrentImageIndex == 0) {
                        Picasso.get().load(this.mImageUrls.get(this.mCurrentImageIndex)).placeholder(R.drawable.cme_placeholder_image).into(this.mImageView);
                    } else {
                        Picasso.get().load(this.mImageUrls.get(this.mCurrentImageIndex)).into(this.mImageView);
                    }
                    int i = this.mCurrentImageIndex + 1;
                    this.mCurrentImageIndex = i;
                    if (i == this.mImageUrls.size()) {
                        this.mCurrentImageIndex = 0;
                        return;
                    }
                    return;
                }
            }
            setTag(this.mImageUrls.get(this.mCurrentImageIndex));
            setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.cme_placeholder_image));
        } catch (Exception e) {
            throw e;
        }
    }
}
