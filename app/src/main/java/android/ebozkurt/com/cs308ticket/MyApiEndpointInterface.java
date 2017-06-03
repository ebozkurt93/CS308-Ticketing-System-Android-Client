package android.ebozkurt.com.cs308ticket;

import org.w3c.dom.Text;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by erdem on 3.06.2017.
 */

public interface MyApiEndpointInterface {

    @POST("user/register")
    Call<Void> registerUser(@Body User user);

    @POST("user/login")
    Call<ResponseBody> loginUser(@Body User user);

}
