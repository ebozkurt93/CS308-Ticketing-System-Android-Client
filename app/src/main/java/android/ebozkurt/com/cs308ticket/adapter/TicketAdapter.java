package android.ebozkurt.com.cs308ticket.adapter;

import android.content.Context;
import android.ebozkurt.com.cs308ticket.R;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.ebozkurt.com.cs308ticket.domain.Ticket;
import android.ebozkurt.com.cs308ticket.network.RetrofitBuilder;
import android.ebozkurt.com.cs308ticket.network.TicketApiInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by erdem on 4.06.2017.
 */

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name, email, category, price;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.ticket_card_name);
            email = (TextView) itemView.findViewById(R.id.ticket_card_email);
            category = (TextView) itemView.findViewById(R.id.ticket_card_category);
            price = (TextView) itemView.findViewById(R.id.ticket_card_price);
        }
    }

    private ArrayList<Ticket> ticketList;
    private Context context;

    public TicketAdapter(Context context, ArrayList<Ticket> ticketList) {
        this.ticketList = ticketList;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.ticket_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Ticket ticket = ticketList.get(position);

        // Set item views based on your views and data model
        final TextView nameTextView = viewHolder.name;
        TextView emailTextView = viewHolder.email;
        TextView categoryTextView = viewHolder.category;
        TextView priceTextView = viewHolder.price;


        categoryTextView.setText(ticket.getCategory().getName());
        priceTextView.setText(ticket.getCategory().getPrice());
        emailTextView.setText(ticket.getUser().getMail());

        TicketApiInterface apiService = RetrofitBuilder.returnService();
        Call<Event> call = apiService.getEvent(ticket);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                nameTextView.setText(response.body().getName());

            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                nameTextView.setText("");

            }
        });

    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }
}