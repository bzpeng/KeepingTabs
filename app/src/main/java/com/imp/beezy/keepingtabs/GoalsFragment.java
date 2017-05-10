package com.imp.beezy.keepingtabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bpeng on 2017-05-04.
 */

public class GoalsFragment extends Fragment {

    public GoalsFragment() {}

    public static GoalsFragment newInstance() {
        GoalsFragment fragment = new GoalsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_goals, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("Page 2");
        return rootView;
    }
}
