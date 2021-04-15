package com.example.fragmentcodingchallenge;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class FeedbackFragment extends Fragment {

    // radio button choice states: 0 = yes, 1 = no, 2 = no choice(default)
    public static final int YES = 0;
    public static final int NO = 1;


    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        // add radio group reference for the view
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        // add rating reference for the view
        final RatingBar ratingBar = rootView.findViewById(R.id.ratingBar);

        // radio button event click listener
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {
                        View radioButton = radioGroup.findViewById(checkedId);
                        int index = radioGroup.indexOfChild(radioButton);
                        TextView textView =
                                rootView.findViewById(R.id.fragment_header);
                        switch (index) {
                            case YES: // User chose "Yes".
                                textView.setText(R.string.yes_message);
                                break;
                            case NO: // User chose "No".
                                textView.setText(R.string.no_message);
                                break;
                            default: // No choice made.
                                // Do nothing.
                                break;
                        }
                    }
                });

        // rating button event click listener
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String myRating =
                        (getString(R.string.my_rating)) + String.valueOf(ratingBar.getRating());

                // show a toast message for rating
                Toast.makeText(getContext(), myRating, Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}