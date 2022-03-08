package ir.alroid.myirancell.di.module;

import android.app.Activity;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.ui.home.slider.AdapterImageSlider;
import ir.alroid.myirancell.ui.home.slider.ImageSlider;

@Module
public class AdapterImageSliderModule {

    Activity activity;
    List<ImageSlider> images;

    // Constructor
    public AdapterImageSliderModule(Activity activity, List<ImageSlider> images) {
        this.activity = activity;
        this.images = images;
    }

    @Provides
    public AdapterImageSlider provideAdapterImageSlider() {
        return new AdapterImageSlider(activity, images);
    }
}
