package com.imp.beezy.keepingtabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bpeng on 2017-05-08.
 */

public class ActivityFragment extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        String fragmentType = getIntent().getStringExtra("Type");

 //       newGoalFragment fragment = (newGoalFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        if (fragmentType.equals("newgoal")) {
            FragmentManager fm = getSupportFragmentManager();
            newGoalFragment fragment = newGoalFragment.newInstance();
            fm.beginTransaction().add(fragment, "newGoal")
                    .commit();
        }
    }

}
