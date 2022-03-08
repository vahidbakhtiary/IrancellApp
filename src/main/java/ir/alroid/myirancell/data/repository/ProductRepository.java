package ir.alroid.myirancell.data.repository;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import ir.alroid.myirancell.broadcast.NetworkBroadcast;
import ir.alroid.myirancell.data.api.ProductApi;
import ir.alroid.myirancell.data.room.AppDatabase;
import ir.alroid.myirancell.data.room.dao.ProductDao;
import ir.alroid.myirancell.data.room.entity.Product;
import ir.alroid.myirancell.di.component.DaggerRetrofitComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<Product>> productLiveData;
    private ProductApi api;

    // Constructor
    public ProductRepository(Application application) {

        this.productDao = AppDatabase.getINSTANCE(application).getProductDao();

        this.productLiveData = productDao.select();

        // init Retrofit with Dagger
        Retrofit retrofit = DaggerRetrofitComponent.create().getRetrofit();

        this.api = retrofit.create(ProductApi.class);
    }

    public Completable insert(Product product) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                productDao.insert(product);

                observer.onComplete();
            }
        };

        return completable;
    }

    public Completable delete(Product product) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                productDao.delete(product);

                observer.onComplete();
            }
        };

        return completable;
    }

    public Completable update(Product product) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                productDao.update(product);

                observer.onComplete();
            }
        };

        return completable;
    }

    public LiveData<List<Product>> select() {
        return productLiveData;
    }

    public void registerNetworkReceiver(Context context) {

        NetworkBroadcast networkBroadcast = new NetworkBroadcast(context);

        context.registerReceiver(networkBroadcast, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public Completable updateProduct(int id, String day, String gigabyte, String paice) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                productDao.updateProduct(id, day, gigabyte, paice);

                observer.onComplete();
            }
        };

        return completable;
    }

}

