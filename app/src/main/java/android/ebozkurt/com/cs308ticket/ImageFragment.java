package android.ebozkurt.com.cs308ticket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by erdem on 4.06.2017.
 */

public class ImageFragment extends Fragment {

    // Store instance variables
    private String image;

    // newInstance constructor for creating fragment with arguments
    public static ImageFragment newInstance(String image) {
        ImageFragment fragmentFirst = new ImageFragment();
        Bundle args = new Bundle();
        args.putString("image", image);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getArguments().getString("image");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_viewpager, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_image_view_pager_image);
        return view;
    }
}
