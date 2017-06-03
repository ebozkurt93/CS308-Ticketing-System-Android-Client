package android.ebozkurt.com.cs308ticket;

import android.content.Intent;
import android.ebozkurt.com.cs308ticket.domain.Event;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventActivity extends AppCompatActivity {

    private FragmentPagerAdapter adapterViewPager;
    private static Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent i = getIntent();
        event = (Event) i.getSerializableExtra("event");
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_event_image_viewpager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
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
