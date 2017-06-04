package android.ebozkurt.com.cs308ticket;

import android.content.Intent;
import android.ebozkurt.com.cs308ticket.adapter.EventAdapter;
import android.ebozkurt.com.cs308ticket.adapter.TicketAdapter;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.ebozkurt.com.cs308ticket.domain.Ticket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TicketListActivity extends AppCompatActivity {

    ArrayList<Ticket> tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        Intent i = getIntent();
        tickets = (ArrayList<Ticket>) i.getSerializableExtra("ticketlist");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_ticket_list_recyclerview);
        TicketAdapter ticketAdapter = new TicketAdapter(this, tickets);
        recyclerView.setAdapter(ticketAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
