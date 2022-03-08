package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.AdapterImageSliderModule;
import ir.alroid.myirancell.ui.home.FragmentHome;
import ir.alroid.myirancell.ui.home.slider.AdapterImageSlider;

@Component(modules = AdapterImageSliderModule.class)
public interface AdapterImageSliderComponent {

    void inject(FragmentHome fragmentHome);
}
