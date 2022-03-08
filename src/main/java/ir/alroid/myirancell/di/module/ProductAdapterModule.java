package ir.alroid.myirancell.di.module;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.ui.product.AdapterProduct;
import ir.alroid.myirancell.ui.product.ViewModelProduct;

@Module
public class ProductAdapterModule {

    private ViewModelProduct viewModel;

    // Constructor
    public ProductAdapterModule(ViewModelProduct viewModel) {
        this.viewModel = viewModel;
    }

    @Provides
    public AdapterProduct provideAdapterProduct() {

        return new AdapterProduct(viewModel);
    }
}
