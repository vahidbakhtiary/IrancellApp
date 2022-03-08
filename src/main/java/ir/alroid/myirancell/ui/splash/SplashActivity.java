package ir.alroid.myirancell.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.api.ProductApi;
import ir.alroid.myirancell.data.room.entity.Product;
import ir.alroid.myirancell.databinding.ActivitySplashBinding;
import ir.alroid.myirancell.service.MyApiIntentService;
import ir.alroid.myirancell.ui.login.ActivityLogin;
import ir.alroid.myirancell.ui.product.ViewModelProduct;
import ir.alroid.myirancell.utils.AppConstance;
import ir.alroid.myirancell.utils.Tools;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    ActivitySplashBinding binding;
    ViewModelProduct viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tools.setSystemBarColor(this, R.color.logo_yellow);

        // Binding
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // init product View Model
        viewModel = ViewModelProviders.of(this).get(ViewModelProduct.class);

        // View Model Start Service
        viewModel.startApiIntentService(this);
    }

}