package ir.alroid.myirancell.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.data.repository.ProductRepository;

@Module
public class ProductRepositoryModule {

    private Application application;

    // Constructor
    public ProductRepositoryModule(Application application) {
        this.application = application;
    }

    @Provides
    public ProductRepository provideProductRepository() {
        return new ProductRepository(application);
    }
}
