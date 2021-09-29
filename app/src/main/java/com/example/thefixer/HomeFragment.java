package com.example.thefixer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.thefixer.R;
import com.example.thefixer.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private ViewFlipper vfBanner;
    public HomeFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        vfBanner = view.findViewById(R.id.vfBanner);

        int images[] = {R.drawable.home_banner1, R.drawable.home_banner2, R.drawable.home_banner3};
        String titlesBanner[] = {"ELECTRONIC SALE OFF 50%", "MOBILE SALE OFF 10%", "PLUMBER SALE OFF 50%"};

        for (int i = 0; i < images.length; i++) {
            if(titlesBanner[i] != null) {
                flipperBanner(images[i], titlesBanner[i]);
            }
        }
        return view;
    }

    public void flipperBanner(int image, String title){
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params);

        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        imageView.setLayoutParams(imageParams);

        TextView titleBanner = new TextView(getActivity());
        titleBanner.setText(title);
        titleBanner.setTextSize(18);
        titleBanner.setGravity(Gravity.CENTER);
        titleBanner.setTypeface(Typeface.DEFAULT_BOLD);
        titleBanner.setLayoutParams(params);

        linearLayout.addView(imageView);
        linearLayout.addView(titleBanner);

        vfBanner.addView(linearLayout);
        vfBanner.setFlipInterval(4000);
        vfBanner.setAutoStart(true);

        vfBanner.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        vfBanner.setInAnimation(getActivity(), android.R.anim.slide_out_right);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}