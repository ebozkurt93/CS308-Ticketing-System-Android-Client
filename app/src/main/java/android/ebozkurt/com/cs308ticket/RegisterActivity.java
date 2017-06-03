package android.ebozkurt.com.cs308ticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RegisterActivity extends AppCompatActivity {

    private EditText name, password, lastname, email, address;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name = (EditText) findViewById(R.id.activity_register_name_edittext);
        final EditText password = (EditText) findViewById(R.id.activity_register_password_edittext);
        final EditText lastname = (EditText) findViewById(R.id.activity_register_lastname_edittext);
        final EditText email = (EditText) findViewById(R.id.activity_register_email_edittext);
        final EditText address = (EditText) findViewById(R.id.activity_register_address_edittext);

        final Button register = (Button) findViewById(R.id.activity_register_register_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", name.getText());
                    jsonObject.put("surname", lastname.getText());
                    jsonObject.put("password", password.getText());
                    jsonObject.put("mail", email.getText());
                    jsonObject.put("address", address.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //   JSONObject asd = WebserviceHelper.postJSONObject(getResources().getString(R.string.main_url) + "/user/register", jsonObject);

*/

                User user = new User(name.getText().toString().trim(), lastname.getText().toString().trim(), password.getText().toString().trim(), email.getText().toString().trim(), address.getText().toString().trim());
                MyApiEndpointInterface apiService = RetrofitBuilder.returnService();

                Call<User> call = apiService.registerUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.i("dev", response.toString());
                        Log.i("dev", "onResponse: ");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("dev", t.toString());

                    }
                });
                //retrofitBuilder
            }
        });
    }


}
