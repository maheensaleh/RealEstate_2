package com.example.realestate_2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class tab1 extends Fragment {




    Intent gotoprop;

    private OnFragmentInteractionListener mListener;

    public tab1() {
        // Required empty public constructor
    }

    public static tab1 newInstance(String param1, String param2) {
        tab1 fragment = new tab1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // MAIN WORK.//
    @Override  // this method shows the content of the  fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentbh

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);



        return view;

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
