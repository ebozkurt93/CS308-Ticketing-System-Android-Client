package android.ebozkurt.com.cs308ticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    private Button getallusers, addadmin, removeadmin, addevent;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getallusers = (Button) findViewById(R.id.activity_admin_getallusers_button);
        addadmin = (Button) findViewById(R.id.activity_admin_addadmin_button);
        removeadmin = (Button) findViewById(R.id.activity_admin_removeadmin_button);
        email = (EditText) findViewById(R.id.activity_admin_email_edittext);
        addevent = (Button) findViewById(R.id.activity_admin_addevent_button);

        getallusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("keys", Context.MODE_PRIVATE);
                String jwt = sharedPreferences.getString("jwt", "");
                TicketApiInterface apiService = RetrofitBuilder.returnService();
                Call<ArrayList<User>> call = apiService.getAllUsers(jwt);
                Log.i("dev", jwt + " asdasda");
                call.enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {


                        Log.i("dev", response.toString());
                        ArrayList<User> users = response.body();

                        Intent i = new Intent(AdminActivity.this, UserListActivity.class);
                        i.putExtra("userlist", users);
                        startActivity(i);

                        /*
                        for( int i = 0; i < users.size(); i++) {
                            User user = users.get(i);
                            Log.i("dev", user.toString());

                        }
                        Log.i("dev", users.toString());
                        */
                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                        Toast.makeText(AdminActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

addadmin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("keys", Context.MODE_PRIVATE);
        String jwt = sharedPreferences.getString("jwt", "");
        TicketApiInterface apiService = RetrofitBuilder.returnService();

        User user = new User();
        user.setMail(email.getText().toString());
        user.setRole("ADMIN");
        Log.i("dev", user.getMail().toString());
        Call<ArrayList<User>> call = apiService.addAdminByEmail(jwt, user);
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                Toast.makeText(AdminActivity.this, "Successful", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(AdminActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
});

        removeadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("keys", Context.MODE_PRIVATE);
                String jwt = sharedPreferences.getString("jwt", "");
                TicketApiInterface apiService = RetrofitBuilder.returnService();

                User user = new User();
                user.setMail(email.getText().toString());
                user.setRole("USER");
                Log.i("dev", user.getMail());
                Call<ArrayList<User>> call = apiService.removeAdminByEmail(jwt, user);
                call.enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                        Toast.makeText(AdminActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                        Toast.makeText(AdminActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, AddEventActivity.class);
                startActivity(i);
            }
        });

    }
}
