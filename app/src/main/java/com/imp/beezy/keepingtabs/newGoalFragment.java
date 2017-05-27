package com.imp.beezy.keepingtabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class newGoalFragment extends Fragment implements View.OnClickListener {

    private DialogFragment datePicker;
    private DialogFragment datePicker2;
    private SimpleDateFormat sdf;
    private int clicked;
    private boolean firstDP;
    private View rootView;

    private String goalName;
    private String startDate;
    private String endDate;
    private String goalDescription;
    private boolean access;
    private Map<String, Boolean> map;

    private boolean [] tempstorage;

    public static final int REQ_DATE = 100;

    public static newGoalFragment newInstance() {
        newGoalFragment fragment = new newGoalFragment();
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Grabbing the id
        int id = item.getItemId();

        if(id == R.id.action_addGoal) {
            createGoal();
            //Toast.makeText(getApplicationContext(), et.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_newgoal, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    public void createGoal() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference().getRoot();

        EditText ET = (EditText) rootView.findViewById(R.id.goalTitle);
        goalName = ET.getText().toString();
//
//        Toast.makeText(getContext(), goalName, Toast.LENGTH_LONG).show();
//        Toast.makeText(getContext(), startDate, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), endDate, Toast.LENGTH_SHORT).show();

        EditText description = (EditText) rootView.findViewById(R.id.description);
        goalDescription = description.getText().toString();

        TextView tv = (TextView) rootView.findViewById(R.id.dateTextView);
        startDate = tv.getText().toString();

        TextView tv2 = (TextView) rootView.findViewById(R.id.dateTextView2);
        endDate = tv2.getText().toString();

        Switch sw = (Switch)rootView.findViewById(R.id.accessSwitch);
        access = sw.isChecked();

        GridLayout icons = (GridLayout) rootView.findViewById(R.id.categoriesIcons);
        map = new HashMap<>(icons.getChildCount());

        for (int i = 0; i < icons.getChildCount(); i++) {
            ImageView im = (ImageView) icons.getChildAt(i);
            if (im instanceof ImageView) {
                String tag = im.getTag().toString();
                map.put(tag, tempstorage[i]);
                Toast.makeText(getContext(), tag + " : " + tempstorage[i], Toast.LENGTH_SHORT);
            }
        }

        Map<String, Object> temp = new HashMap<>();
        db.child("Goals").setValue(new Goal(goalName, startDate, endDate, goalDescription, access, map));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_newgoals, container, false);

        Calendar calendar = Calendar.getInstance();
        Date currDate = calendar.getTime();

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(currDate);

        //Date TextView
        TextView dateTextView = (TextView) rootView.findViewById(R.id.dateTextView);
        dateTextView.setText(date);
        dateTextView.setOnClickListener(this);

        TextView dateTextView2 = (TextView) rootView.findViewById(R.id.dateTextView2);
        dateTextView2.setText(date);
        dateTextView2.setOnClickListener(this);

        //Loading Grid
        GridLayout gridLayout = (GridLayout) rootView.findViewById(R.id.categoriesIcons);
        tempstorage = new boolean[gridLayout.getChildCount()];
        Arrays.fill(tempstorage, Boolean.FALSE);

        for(int i=0; i < gridLayout.getChildCount(); i++) {
            final ImageView iv = (ImageView) gridLayout.getChildAt(i);
            final int j = i;
            iv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ImageView tempIV = iv;
                    String str = iv.getTag().toString();
                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempstorage[j] = !tempstorage[j];
                    if(tempstorage[j]) {
                        iv.setBackgroundResource(R.drawable.image_border);
                    } else {
                        iv.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            });
        }


        return rootView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        datePicker = new DatePickerFragment();
        datePicker.setTargetFragment(this, REQ_DATE);

        datePicker2 = new DatePickerFragment();
        datePicker2.setTargetFragment(this, REQ_DATE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

//    public void updateDate(int year, int month, int day, int id) {
//        String newDate = year+"-"+month+"-"+day;
//        TextView tv = (TextView) rootView.findViewById(clicked);
//        tv.setText(newDate);
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_DATE) {
            if (resultCode == Activity.RESULT_OK) {
                int year = data.getIntExtra("year", 0);
                int month = data.getIntExtra("month", 0);
                int day = data.getIntExtra("day", 0);
                String newDate = year+"-"+month+"-"+day;
                TextView dateTextView = (TextView) rootView.findViewById(clicked);

                dateTextView.setText(newDate);
                if(firstDP) {
                    startDate = newDate;
                } else {
                    endDate = newDate;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.dateTextView: {
                datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
                clicked = R.id.dateTextView;
                firstDP = true;
                break;
            }
            case R.id.dateTextView2: {
                datePicker2.show(getActivity().getSupportFragmentManager(), "datePicker");
                clicked = R.id.dateTextView2;
                firstDP = false;
                break;
            }
        }
    }
}
