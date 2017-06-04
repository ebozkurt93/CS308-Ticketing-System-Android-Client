package android.ebozkurt.com.cs308ticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.ebozkurt.com.cs308ticket.domain.Category;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.ebozkurt.com.cs308ticket.domain.Seat;
import android.ebozkurt.com.cs308ticket.domain.Ticket;
import android.ebozkurt.com.cs308ticket.domain.User;
import android.ebozkurt.com.cs308ticket.network.RetrofitBuilder;
import android.ebozkurt.com.cs308ticket.network.TicketApiInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SelectSeatActivity extends AppCompatActivity {

    private ArrayList<Seat> seats;
    private ArrayList<Ticket> tickets;
    private ArrayList<String> soldSeats;
    private Category selectedCategory;


    private Event event;
    private Spinner categorySpinner;
    private TextView categoryDetailsTextView;
    private EditText seatno;
    private Button buyticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        categorySpinner = (Spinner) findViewById(R.id.activity_select_seat_category_spinner);
        categoryDetailsTextView = (TextView) findViewById(R.id.activity_select_seat_category_details_edittext);
        seatno = (EditText) findViewById(R.id.activity_select_seat_seatno_edittext);
        buyticket = (Button) findViewById(R.id.activity_select_seat_buyticket_button);

        seatno.setVisibility(View.INVISIBLE);
        buyticket.setVisibility(View.INVISIBLE);

        soldSeats = new ArrayList<String>();

        Intent i = getIntent();
        event = (Event) i.getSerializableExtra("event");

        final List<String> categorynames = new ArrayList<String>();
        categorynames.add("-");
        final List<Category> categories = event.getCategory();
        for (int j = 0; j < categories.size(); j++) {
            String name = categories.get(j).getName();
            categorynames.add(name);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorynames);
        categorySpinner.setAdapter(dataAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("keys", Context.MODE_PRIVATE);
        String jwt = sharedPreferences.getString("jwt", "");
        TicketApiInterface apiService = RetrofitBuilder.returnService();
        Call<ArrayList<Ticket>> call = apiService.getAllTicketsForEvent(jwt, event);
        call.enqueue(new Callback<ArrayList<Ticket>>() {
            @Override
            public void onResponse(Call<ArrayList<Ticket>> call, Response<ArrayList<Ticket>> response) {
                Toast.makeText(SelectSeatActivity.this, "Retrieved sold tickets", Toast.LENGTH_SHORT).show();
                tickets = response.body();
             //   Log.i("dev", response.body().get(0).getSeatName().toString() + "\n" + response.code());
            }

            @Override
            public void onFailure(Call<ArrayList<Ticket>> call, Throwable t) {
                Toast.makeText(SelectSeatActivity.this, "Failed to retreive sold tickets, going back...", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    // Category category = categories.get(position - 1);
                    selectedCategory = new Category("-", "-", "-", "-");
                    String categoryName = categorynames.get(position);
                    for (int x = 0; x < categories.size(); x++) {
                        if (categories.get(x).getName().equals(categoryName)) {
                            selectedCategory = categories.get(x);
                            categoryDetailsTextView.setText("Name: " + selectedCategory.getName() + "\nPrice: " + selectedCategory.getPrice() + "\nCategory Start-End Seats: " + selectedCategory.getStartSeat() + "-" + selectedCategory.getEndSeat() + "\nSold seats: " + soldSeats);
                        }

                    }


                    for (int y = 0; y < tickets.size(); y++) {
                        Ticket t = tickets.get(y);
                        soldSeats.add(t.getSeatName());
                    }

                    seatno.setVisibility(View.VISIBLE);
                    buyticket.setVisibility(View.VISIBLE);
                    seatno.setText("");
                } else {
                    categoryDetailsTextView.setText("");
                    seatno.setVisibility(View.INVISIBLE);
                    buyticket.setVisibility(View.INVISIBLE);
                    seatno.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("keys", Context.MODE_PRIVATE);
                String jwt = sharedPreferences.getString("jwt", "");
                String id = sharedPreferences.getString("id","");
                User user = new User();
               // user.setMail(mail);
                user.setId(id);
                Ticket ticket = new Ticket(seatno.getText().toString(),selectedCategory, user);

                TicketApiInterface apiService = RetrofitBuilder.returnService();
                Call<Void> call = apiService.createTicket(jwt, ticket);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(SelectSeatActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(SelectSeatActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });











/*
        seats = new ArrayList<Seat>();
        Seat s1 = new Seat(1, "VIP", false);
        Seat s2 = new Seat(2, "VIP", false);
        seats.add(s1);
        seats.add(s2);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_select_seat_column_recyclerview);
        EventSeatColumnAdapter eventSeatColumnAdapter = new EventSeatColumnAdapter(this, seats);
        recyclerView.setAdapter(eventSeatColumnAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
*/


    }
}
