package com.imp.beezy.keepingtabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by bpeng on 2017-05-04.
 */

public class GoalsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

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
        //Toast.makeText(getContext(),"GoalsFragment", Toast.LENGTH_SHORT).show();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.goals_recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        List<Goal> goalList = null;

        mRecyclerView.setAdapter(new goalAdapter(goalList));
        return rootView;
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
            holder.goalName.setText("hey there");
        }

        @Override
        public int getItemCount() {
            //return goalList.size();
            return 10;
        }
    }
}
