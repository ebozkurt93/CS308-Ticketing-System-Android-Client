package android.ebozkurt.com.cs308ticket;

import android.content.Context;
import android.content.SharedPreferences;
import android.ebozkurt.com.cs308ticket.adapter.CategoryAdapter;
import android.ebozkurt.com.cs308ticket.adapter.UserAdapter;
import android.ebozkurt.com.cs308ticket.domain.Category;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.ebozkurt.com.cs308ticket.domain.User;
import android.ebozkurt.com.cs308ticket.network.RetrofitBuilder;
import android.ebozkurt.com.cs308ticket.network.TicketApiInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {

    private ArrayList<Category> categorylist;
    private EditText name, info, actor, imageURL1, imageURL2, videoURL;
    private Button createEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        categorylist = new ArrayList<Category>();
        Category c = new Category("1","20","VIP","100");
        categorylist.add(c);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_add_category_recyclerview);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categorylist);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        name = (EditText) findViewById(R.id.activity_add_event_name_edittext);
        info = (EditText) findViewById(R.id.activity_add_event_info_edittext);
        actor = (EditText) findViewById(R.id.activity_add_event_actor_edittext);
        imageURL1 = (EditText) findViewById(R.id.activity_add_event_imageURL1_edittext);
        imageURL2 = (EditText) findViewById(R.id.activity_add_event_imageURL2_edittext);
        videoURL = (EditText) findViewById(R.id.activity_add_event_videoURL1_edittext);

        createEvent = (Button) findViewById(R.id.activity_add_event_createevent_button);




        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("keys", Context.MODE_PRIVATE);
                String jwt = sharedPreferences.getString("jwt", "");
                TicketApiInterface apiService = RetrofitBuilder.returnService();



                Event event = new Event();
                event.setName(name.getText().toString());
                event.setInfo(info.getText().toString());
                event.setActor(actor.getText().toString());
                event.setImageUrl1(imageURL1.getText().toString());
                event.setImageUrl2(imageURL2.getText().toString());
                event.setVideoUrl1(videoURL.getText().toString());
                event.setCategory(categorylist);


                Call<Void> call = apiService.addEvent(jwt, event);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(AddEventActivity.this, "Successful", Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AddEventActivity.this, "Failed", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });

    }
}
