package android.ebozkurt.com.cs308ticket;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by erdem on 2.06.2017.
 */

public class RetrofitBuilder {

    // Trailing slash is needed
    public static final String BASE_URL = "http://10.0.2.2:8080/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static MyApiEndpointInterface returnService () {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Retrofit retrofit = retrofitBuilder.retrofit;
        MyApiEndpointInterface apiService =
                retrofit.create(MyApiEndpointInterface.class);
        return apiService;
    }
}
