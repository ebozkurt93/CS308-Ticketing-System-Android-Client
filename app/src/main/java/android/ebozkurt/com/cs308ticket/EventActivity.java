package android.ebozkurt.com.cs308ticket;

import android.content.Intent;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    private FragmentPagerAdapter adapterViewPager;
    private static Event event;
    private TextView name, info, actor;
    private Button selectSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent i = getIntent();
        event = (Event) i.getSerializableExtra("event");
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_event_image_viewpager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        name = (TextView) findViewById(R.id.activity_event_name_textview);
        info = (TextView) findViewById(R.id.activity_event_info_textview);
        actor = (TextView) findViewById(R.id.activity_event_actor_textview);

        name.setText(Html.fromHtml("<b>Name:</b> " + event.getName()));
        info.setText(Html.fromHtml("<b>Info:</b> " + event.getInfo()));
        actor.setText(Html.fromHtml("<b>Actor:</b> " + event.getActor()));

        selectSeat = (Button) findViewById(R.id.activity_event_selectseat_button);
        selectSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventActivity.this, SelectSeatActivity.class);
                i.putExtra("event", event);
                startActivity(i);
            }
        });

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return ImageFragment.newInstance(event.getImageUrl1());
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return ImageFragment.newInstance(event.getImageUrl2());
                // case 2: // Fragment # 1 - This will show SecondFragment
                // return ImageFragment.newInstance(event.getVideoUrl1());
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

    }

}
