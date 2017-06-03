package android.ebozkurt.com.cs308ticket.adapter;

import android.content.Context;
import android.content.Intent;
import android.ebozkurt.com.cs308ticket.EventActivity;
import android.ebozkurt.com.cs308ticket.R;
import android.ebozkurt.com.cs308ticket.domain.Category;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by erdem on 3.06.2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name, info, actor;
        public ImageView image;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.event_card_name);
            info = (TextView) itemView.findViewById(R.id.event_card_info);
            actor = (TextView) itemView.findViewById(R.id.event_card_actor);

            image = (ImageView) itemView.findViewById(R.id.event_card_imageview);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), EventActivity.class);
                    Event event = new Event();
                    //todo 
                    //i.putExtra("event", event);
                    context.startActivity(i);

                }
            });
        }
    }

    private ArrayList<Event> eventlist;
    private Context context;

    public EventAdapter(Context context, ArrayList<Event> eventlist) {
        this.eventlist = eventlist;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View eventView = inflater.inflate(R.layout.event_card, parent, false);

        // Return a new holder instance
        EventAdapter.ViewHolder viewHolder = new EventAdapter.ViewHolder(eventView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        Event event = eventlist.get(position);

        // Set item views based on your views and data model
        TextView nameTextView = viewHolder.name;
        TextView infoTextView = viewHolder.info;
        TextView actorTextView = viewHolder.actor;
        ImageView imageImageView = viewHolder.image;


        nameTextView.setText(event.getName());
        infoTextView.setText(event.getInfo());
        actorTextView.setText(event.getActor());
        Picasso.with(context).load(event.getImageUrl1()).into(imageImageView);


    }

    @Override
    public int getItemCount() {
        return eventlist.size();
    }

}
