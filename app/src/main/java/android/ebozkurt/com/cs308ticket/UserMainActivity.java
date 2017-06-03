package android.ebozkurt.com.cs308ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class UserMainActivity extends AppCompatActivity {

    private Button events, mytickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        events = (Button) findViewById(R.id.activity_user_main_events_button);
        mytickets = (Button) findViewById(R.id.activity_user_main_mytickets_button);

    }
}
