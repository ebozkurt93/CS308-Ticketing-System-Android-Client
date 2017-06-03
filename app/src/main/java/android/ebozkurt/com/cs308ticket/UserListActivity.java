package android.ebozkurt.com.cs308ticket;

import android.content.Intent;
import android.ebozkurt.com.cs308ticket.adapter.UserAdapter;
import android.ebozkurt.com.cs308ticket.domain.User;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Intent i = getIntent();
        users = (ArrayList<User>) i.getSerializableExtra("userlist");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.userlist_activity_recyclerview);
        UserAdapter userAdapter = new UserAdapter(this, users);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
