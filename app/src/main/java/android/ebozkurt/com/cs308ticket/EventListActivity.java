package android.ebozkurt.com.cs308ticket;

import android.content.Intent;
import android.ebozkurt.com.cs308ticket.adapter.EventAdapter;
import android.ebozkurt.com.cs308ticket.adapter.UserAdapter;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.ebozkurt.com.cs308ticket.domain.User;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Intent i = getIntent();
        events = (ArrayList<Event>) i.getSerializableExtra("eventlist");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_event_list_recyclerview);
        EventAdapter eventAdapter = new EventAdapter(this, events);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

}
