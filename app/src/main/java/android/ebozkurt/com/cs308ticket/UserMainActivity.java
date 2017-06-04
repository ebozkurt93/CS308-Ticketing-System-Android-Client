package android.ebozkurt.com.cs308ticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.ebozkurt.com.cs308ticket.domain.Ticket;
import android.ebozkurt.com.cs308ticket.domain.User;
import android.ebozkurt.com.cs308ticket.network.RetrofitBuilder;
import android.ebozkurt.com.cs308ticket.network.TicketApiInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserMainActivity extends AppCompatActivity {

    private Button events, mytickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        events = (Button) findViewById(R.id.activity_user_main_events_button);
        mytickets = (Button) findViewById(R.id.activity_user_main_mytickets_button);


        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketApiInterface apiService = RetrofitBuilder.returnService();
                Call<ArrayList<Event>> call = apiService.getAllEvents();
                call.enqueue(new Callback<ArrayList<Event>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                        Toast.makeText(UserMainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UserMainActivity.this, EventListActivity.class);
                        ArrayList<Event> events = response.body();
                        i.putExtra("eventlist", events);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                        Toast.makeText(UserMainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mytickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("keys", Context.MODE_PRIVATE);
                String jwt = sharedPreferences.getString("jwt", "");
                String id = sharedPreferences.getString("id", "");
                TicketApiInterface apiService = RetrofitBuilder.returnService();
                User user = new User();
                user.setId(id);
                Call<ArrayList<Ticket>> call = apiService.getAllTicketsForUser(jwt, user);
                call.enqueue(new Callback<ArrayList<Ticket>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Ticket>> call, Response<ArrayList<Ticket>> response) {
                        Toast.makeText(UserMainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        ArrayList<Ticket> tickets = response.body();
                        //todo add ticket screen
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Ticket>> call, Throwable t) {
                        Toast.makeText(UserMainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}
