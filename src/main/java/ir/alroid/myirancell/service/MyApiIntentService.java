package ir.alroid.myirancell.service;

import android.app.Activity;
import android.app.Application;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.alroid.myirancell.data.api.ProductApi;
import ir.alroid.myirancell.data.repository.ProductRepository;
import ir.alroid.myirancell.data.room.AppDatabase;
import ir.alroid.myirancell.data.room.entity.Product;
import ir.alroid.myirancell.di.component.DaggerProductRepositoryComponent;
import ir.alroid.myirancell.di.module.ProductRepositoryModule;
import ir.alroid.myirancell.ui.product.ViewModelProduct;
import ir.alroid.myirancell.utils.AppConstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyApiIntentService extends IntentService {

    private static final String TAG = MyApiIntentService.class.getSimpleName();

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.mvvmtaskmft.service.action.FOO";
    private static final String ACTION_BAZ = "com.example.mvvmtaskmft.service.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.mvvmtaskmft.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.mvvmtaskmft.service.extra.PARAM2";

    public MyApiIntentService() {
        super("MyApiIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyApiIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyApiIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "\n\nonHandleIntent ------------------------------------------------\n\n");

        getApiProducts();

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private void getApiProducts() {
        //getApi
        //store in Database

        // 1) make instance of Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2) make Api
        ProductApi api = retrofit.create(ProductApi.class);

        // 3) make Request
        Call<List<Product>> request = api.selectProductsApi();

        // 4) callback
        request.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.e(TAG, "onResponse: " + response.message());

                // web service called and now import data into database:
                if (checkLogin()) {
                    Log.e(TAG, "onResponse: data exist in db and INSERT_Into_DATABASE() method did not called again.");

                } else {

                    if (response.code() == 200) {

                        ViewModelProduct viewModelProduct = new ViewModelProduct(getApplication());

                        for (int i = 0; i < response.body().size(); i++) {
                            Product product = new Product(
                                    response.body().get(i).getDay(),
                                    response.body().get(i).getGigabyte(),
                                    response.body().get(i).getPrice());

                            //AppDatabase.getINSTANCE(getApplication()).getProductDao().insert(product);

                            // insert into Room with RxJava
                            viewModelProduct.insert(product)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe();

                            Log.e(TAG, "onResponse: \b Insert Product >>>>>>>>> id : " + response.body().get(i).getId() + "\n");
                        }
                    }
                }

                //api_product = true;
                sendBroadcast(new Intent("SuccessApi"));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private boolean checkLogin() {
        Log.e(TAG, "check login: ");

        SharedPreferences myPrefs = this.getSharedPreferences(AppConstance.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean login = myPrefs.contains(AppConstance.SHARED_PREF_KEY);

        Log.e(TAG, "--------------------- login ---------------------------> " + login);

        return login;
    }


} // end intent service