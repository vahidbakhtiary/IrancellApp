package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.RetrofitModule;
import retrofit2.Retrofit;

@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {

    Retrofit getRetrofit();
}
