package com.example.brocks.blount_hw4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class infoFragment extends Fragment {
    WebView browser;
    static TextView txt1;
    static TextView txt2;
    static TextView txt3;
    static TextView txt4;
    static TextView txt5;
    Info info;
    public infoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info, container, false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
