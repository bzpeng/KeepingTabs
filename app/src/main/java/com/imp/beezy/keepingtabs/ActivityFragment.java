package com.imp.beezy.keepingtabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bpeng on 2017-05-08.
 */

public class ActivityFragment extends AppCompatActivity {

    private newGoalFragment fragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
//
        String fragmentType = getIntent().getStringExtra("Type");
        FragmentManager fm = getSupportFragmentManager();

        if (fragmentType.equals("newgoal")) {
            fragment = newGoalFragment.newInstance();
            fm.beginTransaction().add(fragment, "newGoal")
                    .commit();
        }
    }

}
