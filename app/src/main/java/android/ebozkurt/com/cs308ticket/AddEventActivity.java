package android.ebozkurt.com.cs308ticket;

import android.ebozkurt.com.cs308ticket.adapter.CategoryAdapter;
import android.ebozkurt.com.cs308ticket.adapter.UserAdapter;
import android.ebozkurt.com.cs308ticket.domain.Category;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

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
                
            }
        });

    }
}
