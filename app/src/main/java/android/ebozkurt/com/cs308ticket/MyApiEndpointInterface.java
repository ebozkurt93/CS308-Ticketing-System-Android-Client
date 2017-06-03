package android.ebozkurt.com.cs308ticket;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by erdem on 3.06.2017.
 */

public interface MyApiEndpointInterface {

    @POST("user/register")
    Call<User> registerUser(@Body User user);

    @POST("user/login")
    Call<User> loginUser(@Body User user);

}
