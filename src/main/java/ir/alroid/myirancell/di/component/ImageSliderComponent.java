package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.ImageSliderModule;
import ir.alroid.myirancell.ui.home.slider.ImageSlider;

@Component(modules = ImageSliderModule.class)
public interface ImageSliderComponent {

    ImageSlider getImageSlider();
}
