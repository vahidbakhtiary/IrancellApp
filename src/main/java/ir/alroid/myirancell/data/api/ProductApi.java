package ir.alroid.myirancell.data.api;

import java.util.List;

import ir.alroid.myirancell.data.room.entity.IrancellService;
import ir.alroid.myirancell.data.room.entity.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    String BASE_URL = "https://run.mocky.io/";

    @GET("v3/cf739236-df0b-4987-8b2f-507fbae51cb3")
    Call<List<Product>> selectProductsApi();
}
