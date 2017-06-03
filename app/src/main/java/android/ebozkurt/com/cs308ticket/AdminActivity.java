package android.ebozkurt.com.cs308ticket;

import android.ebozkurt.com.cs308ticket.domain.User;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    private Button getallusers, addadmin, removeadmin;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getallusers = (Button) findViewById(R.id.activity_admin_getallusers_button);
        addadmin = (Button) findViewById(R.id.activity_admin_addadmin_button);
        removeadmin = (Button) findViewById(R.id.activity_admin_removeadmin_button);
        email = (EditText) findViewById(R.id.activity_admin_email_edittext);

        getallusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketApiInterface apiService = RetrofitBuilder.returnService();
                Call<ArrayList<User>> call = apiService.getAllUsers();
                call.enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                    }
                });
            }
        });

    }
}
