package com.imp.beezy.keepingtabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpeng on 2017-05-04.
 */

public class GoalsFragment extends Fragment {

    List<Goal> goalList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private FirebaseDatabase database;
    private DatabaseReference ref;

    public GoalsFragment() {}

    public static GoalsFragment newInstance() {
        GoalsFragment fragment = new GoalsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().getRoot().child("Goals");

        View rootView = inflater.inflate(R.layout.fragment_goals, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.goals_recyclerView);

        GridLayoutManager llm = new GridLayoutManager(getContext(),1);
        llm.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count: ", ""+dataSnapshot.getChildrenCount());
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Goal goal = ds.getValue(Goal.class);
                    if(!updateNew(goal)) {
                        goalList.add(goal);
                        Log.e("Goal Name", goal.getGoalName());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }
        });

        mRecyclerView.setAdapter(new goalAdapter(goalList));


        return rootView;
    }

    private boolean updateNew(Goal goal) {
        boolean exists = false;
        for (Goal g : goalList) {
            Log.e("Goal/newGoal: ", g.getGoalName() + " / " + goal.getGoalName());
            if (goal.equals(g)) {
                exists = true;
                return exists;
            }
        }
        return exists;
    }

    public static class GoalViewHolder extends RecyclerView.ViewHolder {
        protected TextView goalName;



        public GoalViewHolder(View itemView) {
            super(itemView);
            goalName = (TextView) itemView.findViewById(R.id.goalName);
        }
    }

    public class goalAdapter extends RecyclerView.Adapter<GoalViewHolder> {

        private List<Goal> goalList;
        public goalAdapter(List<Goal> goalList) {
            this.goalList = goalList;
        }


        @Override
        public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_goals_cardview, parent, false);
            GoalViewHolder vh = new GoalViewHolder(itemView);

            return vh;
        }

        @Override
        public void onBindViewHolder(GoalViewHolder holder, int position) {
            String txt = goalList.get(position).getGoalName();
            holder.goalName.setText(txt);
        }

        @Override
        public int getItemCount() {
            //return goalList.size();
            return goalList.size();
        }
    }
}
