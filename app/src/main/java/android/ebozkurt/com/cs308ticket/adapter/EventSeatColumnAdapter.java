package android.ebozkurt.com.cs308ticket.adapter;

import android.content.Context;
import android.ebozkurt.com.cs308ticket.R;
import android.ebozkurt.com.cs308ticket.domain.Category;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.ebozkurt.com.cs308ticket.domain.Seat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by erdem on 4.06.2017.
 */

public class EventSeatColumnAdapter extends RecyclerView.Adapter<EventSeatColumnAdapter.ViewHolder> {


public class ViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public View seat;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);

        seat = (View) itemView.findViewById(R.id.event_seat_column_seat);

    }
}

    private ArrayList<Seat> seatList;
    private Context context;

    public EventSeatColumnAdapter(Context context, ArrayList<Seat> seatList) {
        this.seatList = seatList;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public EventSeatColumnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View eventSeatColumnView = inflater.inflate(R.layout.event_seat_column, parent, false);

        // Return a new holder instance
        EventSeatColumnAdapter.ViewHolder viewHolder = new EventSeatColumnAdapter.ViewHolder(eventSeatColumnView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventSeatColumnAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final Seat seat = seatList.get(position);

        // Set item views based on your views and data model
        View seatView = viewHolder.seat;
        seatView.setBackgroundColor(context.getResources().getColor(R.color.dark_purple));

    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

}