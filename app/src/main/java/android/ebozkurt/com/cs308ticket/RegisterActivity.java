package android.ebozkurt.com.cs308ticket;

import android.ebozkurt.com.cs308ticket.domain.User;
import android.ebozkurt.com.cs308ticket.network.RetrofitBuilder;
import android.ebozkurt.com.cs308ticket.network.TicketApiInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

                User user = new User(name.getText().toString().trim(), lastname.getText().toString().trim(), password.getText().toString().trim(), email.getText().toString().trim(), address.getText().toString().trim());
                TicketApiInterface apiService = RetrofitBuilder.returnService();

                Call<Void> call = apiService.registerUser(user);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("dev", response.toString());
                        Log.i("dev", "onResponse: ");
                        Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("dev", t.toString());
                        Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }


}
