package ir.alroid.myirancell.ui.home.slider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.utils.Tools;

public class AdapterImageSlider extends PagerAdapter {

    private Activity activity;
    private List<ImageSlider> imageList;
    private OnItemClickListener mOnItemClickListener;

    private interface OnItemClickListener {
        void onItemClick(View view, ImageSlider image);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    // Constructor
    public AdapterImageSlider(Activity activity, List<ImageSlider> images) {
        this.activity = activity;
        this.imageList = images;
    }

    @Override
    public int getCount() {
        return this.imageList.size();
    }

    public ImageSlider getItem(int position) {
        return imageList.get(position);
    }

    public void setItems(List<ImageSlider> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final ImageSlider image = imageList.get(position);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_slider_image, container, false);

        ImageView imageView = view.findViewById(R.id.image);
        MaterialRippleLayout lyt_parent = view.findViewById(R.id.lyt_parent);
        //Tools.displayImageOriginal(activity, imageView, image.image);

        lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://irancell.ir/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                activity.startActivity(i);

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, image);
                }
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
