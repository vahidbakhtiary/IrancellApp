package ir.alroid.myirancell.ui.home.guide;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import ir.alroid.myirancell.R;

public class AdapterStepper extends PagerAdapter {

    Context ctx;
    private LayoutInflater layoutInflater;

    // Constructor
    public AdapterStepper(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Typeface font_bold = Typeface.createFromAsset(ctx.getAssets(), "fonts/my_irancell_font_bold.ttf");
        Typeface font_light = Typeface.createFromAsset(ctx.getAssets(), "fonts/my_irancell_font_light.ttf");

        View view = layoutInflater.inflate(R.layout.item_stepper_wizard, container, false);
        ((TextView) view.findViewById(R.id.title)).setText(about_title_array[position]);
        ((TextView) view.findViewById(R.id.title)).setTypeface(font_bold);

        ((TextView) view.findViewById(R.id.description)).setText(about_description_array[position]);
        ((TextView) view.findViewById(R.id.description)).setTypeface(font_light);


        ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return about_title_array.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    private String about_title_array[] = {
            "پوشش گسترده",
            "هدیه ویژه",
            "سرویس رومینگ",
            "تعطیلات رویایی"
    };

    private String about_description_array[] = {
            "ما تلاش کرده ایم گسترده ترین پوشش دهی اینترنت در کشور را برایتان فراهم کنیم",
            "با اولین ورود به سوپر اپلیکیشن ایرانسل من به مدت یک هفته اینترنت رایگان خواهید داشت",
            "میتوانید در هر کشوری که با ایرانسل دارای توافق نامه خدمات رومینگ است از تمامی خدمات سیم کارت بهره مند شوید",
            "امکان رزرو آنلاین هتل با دسترسی به اطلاعات بیش از پانصد اقامتگاه در کشور",
    };

    private int about_images_array[] = {
            R.drawable.img_wizard_1,
            R.drawable.img_wizard_2,
            R.drawable.img_wizard_3,
            R.drawable.img_wizard_4
    };
}
