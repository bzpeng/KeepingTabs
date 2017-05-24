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
import android.widget.Toast;

/**
 * Created by bpeng on 2017-05-08.
 */

public class ActivityFragment extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Goal Created", Toast.LENGTH_SHORT).show();
//            }
//        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);

        String fragmentType = getIntent().getStringExtra("Type");

        if (fragmentType.equals("newgoal")) {
            FragmentManager fm = getSupportFragmentManager();
            newGoalFragment fragment = newGoalFragment.newInstance();
            fm.beginTransaction().add(fragment, "newGoal")
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Grabbing the id
        int id = item.getItemId();

        if(id == R.id.action_addGoal) {
            Toast.makeText(getApplicationContext(), "Goal Created", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newgoal, menu);
        return true;
    }
}
