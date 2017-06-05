package android.ebozkurt.com.cs308ticket.adapter;

/**
 * Created by erdem on 3.06.2017.
 */


import android.content.Context;
import android.ebozkurt.com.cs308ticket.AddEventActivity;
import android.ebozkurt.com.cs308ticket.R;
import android.ebozkurt.com.cs308ticket.domain.Category;
import android.ebozkurt.com.cs308ticket.domain.User;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by erdem on 3.06.2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name, price, startseat, endseat;
        public ImageButton add, remove;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.category_add_card_name_edittext);
            price = (TextView) itemView.findViewById(R.id.category_add_card_price_edittext);
            startseat = (TextView) itemView.findViewById(R.id.category_add_card_startseat_edittext);
            endseat = (TextView) itemView.findViewById(R.id.category_add_card_endseat_edittext);
            add = (ImageButton) itemView.findViewById(R.id.category_add_card_add_button);
            remove = (ImageButton) itemView.findViewById(R.id.category_add_card_remove_button);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   //todo categorylist = getAllCategories();
                    //notifyDataSetChanged();
                    Category c = new Category();
                    categorylist.add(c);
                    notifyItemInserted(getItemCount() - 1);
                    notifyDataSetChanged();
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categorylist.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }


    }

    private ArrayList<Category> categorylist;
    private Context context;

    public CategoryAdapter(Context context, ArrayList<Category> categorylist) {
        this.categorylist = categorylist;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View categoryView = inflater.inflate(R.layout.category_add_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final Category category = categorylist.get(position);


        // Set item views based on your views and data model
        TextView nameTextView = viewHolder.name;
        TextView priceTextView = viewHolder.price;
        TextView startSeatTextView = viewHolder.startseat;
        final TextView endSeatTextView = viewHolder.endseat;
        ImageButton addButton = viewHolder.add;
        final ImageButton removeButton = viewHolder.remove;


        nameTextView.setText(category.getName());
        priceTextView.setText(category.getPrice());
        startSeatTextView.setText(category.getStartSeat());
        endSeatTextView.setText(category.getEndSeat());
/*
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorylist = getAllCategories(viewHolder);
                Category c = new Category();
                categorylist.add(c);
                notifyItemInserted(getItemCount() - 1);
                notifyDataSetChanged();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorylist.remove(position);
                notifyDataSetChanged();
            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }


    public ArrayList<Category> getAllCategories(CategoryAdapter.ViewHolder viewHolder) {
        ArrayList<Category> categories = new ArrayList<>();

        for (int i = 0; i < getItemCount(); i++) {
            //Category c = getCategory(viewHolder);
            onBindViewHolder(viewHolder, i);
            Category c = new Category(viewHolder.startseat.getText().toString(), viewHolder.endseat.getText().toString(), viewHolder.name.getText().toString(), viewHolder.price.getText().toString());
            categories.add(c);
        }

        return categories;
    }

    public Category getCategory(CategoryAdapter.ViewHolder viewHolder) {
        Category c = new Category(viewHolder.startseat.getText().toString(), viewHolder.endseat.getText().toString(), viewHolder.name.getText().toString(), viewHolder.price.getText().toString());
        return c;
    }


}
