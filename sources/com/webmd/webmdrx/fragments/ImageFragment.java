package com.webmd.webmdrx.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;
import com.webmd.webmdrx.R;

public class ImageFragment extends Fragment {
    private ImageView image;
    private String imageLink;
    private View rootView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image, viewGroup, false);
        this.rootView = inflate;
        this.image = (ImageView) inflate.findViewById(R.id.image_fragment_image);
        String str = this.imageLink;
        if (str == null || str.isEmpty()) {
            Picasso.get().load(R.drawable.rx_profile_empty_set).placeholder(R.drawable.rx_profile_empty_set).into(this.image);
        } else {
            Picasso.get().load(this.imageLink).placeholder(R.drawable.rx_profile_empty_set).into(this.image);
        }
        return this.rootView;
    }

    public void setImageLink(String str) {
        this.imageLink = str;
    }
}
