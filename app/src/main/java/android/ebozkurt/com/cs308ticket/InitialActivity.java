package android.ebozkurt.com.cs308ticket;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class InitialActivity extends AppCompatActivity {

    Button login, signup;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        login = (Button) findViewById(R.id.activity_initial_login);
        signup = (Button) findViewById(R.id.activity_initial_signup);
        logo = (ImageView) findViewById(R.id.activity_initial_logo);
        Picasso.with(InitialActivity.this)
                .load("file:///android_asset/logo.png").into(logo);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InitialActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InitialActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
