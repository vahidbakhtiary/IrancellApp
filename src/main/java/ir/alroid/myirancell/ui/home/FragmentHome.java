package ir.alroid.myirancell.ui.home;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.room.entity.DeviceInfo;
import ir.alroid.myirancell.data.room.entity.SliderItem;
import ir.alroid.myirancell.databinding.FragmentHomeBinding;
import ir.alroid.myirancell.di.component.DaggerAdapterImageSliderComponent;
import ir.alroid.myirancell.di.component.DaggerImageSliderComponent;
import ir.alroid.myirancell.di.module.AdapterImageSliderModule;
import ir.alroid.myirancell.ui.home.guide.StepperWizardLightActivity;
import ir.alroid.myirancell.ui.home.slider.AdapterImageSlider;
import ir.alroid.myirancell.ui.home.slider.ImageSlider;
import ir.alroid.myirancell.ui.home.slider.SliderAdapter;
import ir.alroid.myirancell.ui.product.ActionBottomDialogFragment;
import ir.alroid.myirancell.utils.Tools;

public class FragmentHome extends Fragment {

    FragmentHomeBinding binding;

    @Inject
    AdapterImageSlider adapterImageSlider;

    private Runnable runnable = null;
    private Handler handler = new Handler();

    SliderView sliderView;
    private SliderAdapter sliderAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Tools.setSystemBarColor(getActivity(), R.color.logo_yellow);

        // internet progressBar animation
        ProgressBarAnimation anim = new ProgressBarAnimation(binding.progressInternet, 0, 48);
        anim.setDuration(2000);
        binding.progressInternet.startAnimation(anim);

        setupSliderLib();

        binding.ivGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StepperWizardLightActivity.class));
            }
        });

        binding.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("نسخه ی 1.2.0", R.color.snackBar_blue);
            }
        });

        binding.tvScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("مسابقه و امتیاز به زودی ...", R.color.snackBar_pink);
            }
        });

        binding.ivRoaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("رومینگ به زودی ...", R.color.snackBar_blue);
            }
        });

        binding.ivMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("پروفایل کاربری در دست ساخت", R.color.snackBar_orange);
            }
        });

        binding.ivSimCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("تنظیمات سیم کارت به زودی ...", R.color.snackBar_green);
            }
        });

        binding.lytInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetInternet(v);
            }
        });

        binding.lytPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showBottomSheetPhoneNumber(v);
            }
        });

        binding.btnWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetWallet(v);
            }
        });

        binding.lytWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetWallet(v);
            }
        });


        binding.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetMyinfo(v);
            }
        });

    }

    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    public void showSnackBar(String message, int colorId) {
        View parent_view = getActivity().findViewById(android.R.id.content);

        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_SHORT);

        // inflate View
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        // Customize
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        //((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        //(custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(R.color.green_500));
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(colorId));

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    // Bottom Sheet (for Internet layout in home):
    public void showBottomSheetInternet(View view) {
        ActionBottomDialogInternet bottomDialogInternet =
                ActionBottomDialogInternet.newInstance();

        bottomDialogInternet.show(((AppCompatActivity) view.getContext()).getSupportFragmentManager(),
                ActionBottomDialogInternet.TAG);
    }

    // Bottom Sheet (for Phone Number in home Toolbar):
    public void showBottomSheetPhoneNumber(View view) {
        ActionBottomDialogPhoneNumber bottomDialogPhoneNumber =
                ActionBottomDialogPhoneNumber.newInstance();

        bottomDialogPhoneNumber.show(((AppCompatActivity) view.getContext()).getSupportFragmentManager(),
                ActionBottomDialogPhoneNumber.TAG);
    }

    // Bottom Sheet (for Wallet layout in home):
    public void showBottomSheetWallet(View view) {
        ActionBottomDialogWallet bottomDialogWallet =
                ActionBottomDialogWallet.newInstance();

        bottomDialogWallet.show(((AppCompatActivity) view.getContext()).getSupportFragmentManager(),
                ActionBottomDialogWallet.TAG);
    }

    // Bottom Sheet (for my information in home):
    public void showBottomSheetMyinfo(View view) {
        ActionBottomDialogMe bottomDialogMe =
                ActionBottomDialogMe.newInstance();

        bottomDialogMe.show(((AppCompatActivity) view.getContext()).getSupportFragmentManager(),
                ActionBottomDialogMe.TAG);
    }

    private void setupSliderLib() {
        sliderView = binding.imageSlider;
        sliderAdapter = new SliderAdapter(getActivity());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        renewItems(sliderView);
        removeLastItem(sliderView);
        addNewItem(sliderView);
    }

    public void renewItems(View view) {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 3; i++) {
            SliderItem sliderItem = new SliderItem();
            //sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://alialahverdi.ir/wp-content/uploads/2021/09/s1_irancell.jpg");
            } else {
                sliderItem.setImageUrl("https://alialahverdi.ir/wp-content/uploads/2021/09/s3_irancell.jpg");
            }
            sliderItemList.add(sliderItem);
        }
        sliderAdapter.renewItems(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (sliderAdapter.getCount() - 1 >= 0)
            sliderAdapter.deleteItem(sliderAdapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        //sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://alialahverdi.ir/wp-content/uploads/2021/09/s2_irancell.jpg");
        sliderAdapter.addItem(sliderItem);
    }

}