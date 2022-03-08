package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.ProductAdapterModule;
import ir.alroid.myirancell.ui.product.FragmentProduct;

@Component(modules = ProductAdapterModule.class)
public interface ProductAdapterComponent {

    void inject(FragmentProduct fragmentProduct);
}
