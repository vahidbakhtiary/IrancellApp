package ir.alroid.myirancell.ui.product;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import ir.alroid.myirancell.broadcast.NetworkBroadcast;
import ir.alroid.myirancell.broadcast.SuccessApi;
import ir.alroid.myirancell.data.repository.ProductRepository;
import ir.alroid.myirancell.data.room.entity.Product;
import ir.alroid.myirancell.di.component.DaggerProductRepositoryComponent;
import ir.alroid.myirancell.di.module.ProductRepositoryModule;
import ir.alroid.myirancell.service.MyApiIntentService;

public class ViewModelProduct extends AndroidViewModel {

    private static final String TAG = ViewModelProduct.class.getSimpleName();

    ProductRepository repository;
    LiveData<List<Product>> liveData;

    // Constructor
    public ViewModelProduct(@NonNull Application application) {
        super(application);

        // init repository with Dagger
        repository = DaggerProductRepositoryComponent.builder()
                .productRepositoryModule(new ProductRepositoryModule(application))
                .build()
                .getProductRepository();
        //this.repository = new ProductRepository(application);

        this.liveData = repository.select();
    }

    public Completable insert(Product product) {
        return repository.insert(product);
    }

    public Completable delete(Product product) {
        return repository.delete(product);
    }

    public Completable update(Product product) {
        return repository.update(product);
    }

    public LiveData<List<Product>> select() {
        return liveData;
    }

    public void startApiIntentService(Context context) {
        // register BroadCast :
        SuccessApi successApi = new SuccessApi();
        context.registerReceiver(successApi, new IntentFilter("SuccessApi"));
        context.startService(new Intent(context, MyApiIntentService.class));
    }

    //registerNetworkReceiver
    public void registerNetworkReceiver(Context context) {

        NetworkBroadcast networkBroadcast = new NetworkBroadcast(context);

        context.registerReceiver(networkBroadcast, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public Completable updateProduct(int id, String day, String gigabyte, String price) {
        return repository.updateProduct(id, day, gigabyte, price);
    }

}
