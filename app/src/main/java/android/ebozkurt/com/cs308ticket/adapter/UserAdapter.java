package android.ebozkurt.com.cs308ticket.adapter;

import android.content.Context;
import android.ebozkurt.com.cs308ticket.R;
import android.ebozkurt.com.cs308ticket.domain.User;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by erdem on 3.06.2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView id, name, lastname, email, password, role, address;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.user_card_id);
            name = (TextView) itemView.findViewById(R.id.user_card_name);
            lastname = (TextView) itemView.findViewById(R.id.user_card_lastname);
            email = (TextView) itemView.findViewById(R.id.user_card_email);
            password = (TextView) itemView.findViewById(R.id.user_card_password);
            role = (TextView) itemView.findViewById(R.id.user_card_role);
            address = (TextView) itemView.findViewById(R.id.user_card_address);
        }
    }

    private ArrayList<User> userlist;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> userlist) {
        this.userlist = userlist;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.user_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        User user = userlist.get(position);

        // Set item views based on your views and data model
        TextView nameTextView = viewHolder.name;
        TextView lastnameTextView = viewHolder.lastname;
        TextView idTextView = viewHolder.id;
        TextView roleTextView = viewHolder.role;
        TextView addressTextView = viewHolder.address;
        TextView emailTextView = viewHolder.email;
        TextView passwordTextView = viewHolder.password;


        nameTextView.setText("Name: " + user.getName());
        lastnameTextView.setText("Lastname: " + user.getSurname());
        emailTextView.setText("Email: " + user.getMail());
        passwordTextView.setText("Password: " + user.getPassword());
        idTextView.setText("Id: " + user.getId());
        roleTextView.setText("Role: " + user.getRole());
        //Log.i("dev", user.getRole());
      //  nameTextView.setText(user.getName());
      //  addressTextView.setText(user.getAddress());
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
}
