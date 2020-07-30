package com.technology.givol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
//        String getArgument = getArguments().getString("key_value");
//        Toast.makeText(getActivity(),"V"+getArgument,Toast.LENGTH_SHORT).show();
//        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
//
//        ViewPagerAdapterdot viewPagerAdapter = new ViewPagerAdapterdot(context);
//
//        viewPager.setAdapter(viewPagerAdapter);
//        // Inflate the layout for this fragment
//        return view;
    }


}
