package com.example.thefixer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FixerHomepage extends Fragment {

    public FixerHomepage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fixer_homepage, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}