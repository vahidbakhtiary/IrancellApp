package ir.alroid.myirancell.di.module;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.ui.product.FragmentProduct;

@Module
public class FragmentProductModule {

    @Provides
    public FragmentProduct provideFragmentProduct() {
        return new FragmentProduct();
    }
}
