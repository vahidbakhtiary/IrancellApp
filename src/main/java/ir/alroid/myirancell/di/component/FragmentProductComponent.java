package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.FragmentProductModule;
import ir.alroid.myirancell.ui.product.FragmentProduct;

@Component(modules = FragmentProductModule.class)
public interface FragmentProductComponent {

    FragmentProduct getFragmentProduct();
}
