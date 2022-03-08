package ir.alroid.myirancell.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import ir.alroid.myirancell.MainActivity;
import ir.alroid.myirancell.R;
import ir.alroid.myirancell.databinding.ActivityLoginBinding;
import ir.alroid.myirancell.utils.AppConstance;
import ir.alroid.myirancell.utils.Tools;

public class ActivityLogin extends AppCompatActivity {

    ActivityLoginBinding binding;
    ViewModelLogin viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Tools.setSystemBarColor(this, R.color.logo_yellow);

        // init viewModel
        viewModel = ViewModelProviders.of(this).get(ViewModelLogin.class);

        // check register
        viewModel.checkRegister(ActivityLogin.this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.onLogin(ActivityLogin.this, binding.etEmail, binding.etPassword);
            }
        });
    }

}