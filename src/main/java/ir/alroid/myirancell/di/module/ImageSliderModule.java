package ir.alroid.myirancell.di.module;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.ui.home.slider.ImageSlider;

@Module
public class ImageSliderModule {

    @Provides
    public ImageSlider provideImageSlider() {
        return new ImageSlider();
    }
}
