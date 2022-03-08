package ir.alroid.myirancell.di.module;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.data.api.ProductApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ProductApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
