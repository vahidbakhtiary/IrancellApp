package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.data.repository.ProductRepository;
import ir.alroid.myirancell.di.module.ProductRepositoryModule;

@Component(modules = ProductRepositoryModule.class)
public interface ProductRepositoryComponent {

    ProductRepository getProductRepository();
}
