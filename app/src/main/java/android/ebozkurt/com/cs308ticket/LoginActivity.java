package android.ebozkurt.com.cs308ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.activity_login_email_edittext);
        password = (EditText) findViewById(R.id.activity_login_password_edittext);
        login = (Button) findViewById(R.id.activity_login_login_button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(email.getText().toString().trim(), password.getText().toString().trim());
                MyApiEndpointInterface apiService = RetrofitBuilder.returnService();

                Call<User> call = apiService.loginUser(user);
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
            }
        });

    }
}
