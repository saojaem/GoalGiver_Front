package com.example.goalgiver.ui.main.goal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalgiver.R;

import java.util.ArrayList;

public class GoalSetAdapter extends RecyclerView.Adapter<GoalSetAdapter.GoalViewHolder> {

    private Context context;
    private ArrayList<GoalSetItem> goalList;

    public GoalSetAdapter(Context context, ArrayList<GoalSetItem> goalList) {
        this.context = context;
        this.goalList = goalList;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teamprogress, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        GoalSetItem currentGoal = goalList.get(position);

        holder.goalIcon.setText(currentGoal.getGoalIcon());
        holder.goalTitle.setText(currentGoal.getGoalTitle());
        holder.goalDDay.setText(currentGoal.getGoalDDay());
        holder.goalPoints.setText(currentGoal.getGoalPoints());
        holder.goalProgressText.setText(currentGoal.getGoalProgressText());
        holder.goalProgress.setProgress(currentGoal.getGoalProgress());

        // Set arrow icon and point icon if necessary
        holder.goalArrow.setImageResource(R.drawable.icn_arrow);
        holder.ivTeamProgressPoint.setImageResource(R.drawable.icn_point);
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    public static class GoalViewHolder extends RecyclerView.ViewHolder {

        TextView goalIcon;
        TextView goalTitle;
        TextView goalDDay;
        TextView goalPoints;
        TextView goalProgressText;
        ProgressBar goalProgress;
        ImageView goalArrow;
        ImageView ivTeamProgressPoint;

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);

            goalIcon = itemView.findViewById(R.id.goal_goalIcon);
            goalTitle = itemView.findViewById(R.id.goal_goalTitle);
            goalDDay = itemView.findViewById(R.id.goal_goalDDay);
            goalPoints = itemView.findViewById(R.id.goal_goalPoints);
            goalProgressText = itemView.findViewById(R.id.goal_goalProgressText);
            goalProgress = itemView.findViewById(R.id.goal_goalProgress);
            goalArrow = itemView.findViewById(R.id.goal_goalArrow);
            ivTeamProgressPoint = itemView.findViewById(R.id.iv_teamprogress_point);
        }
    }


    // Optional: Method to update the data in the adapter
    public void updateGoalList(ArrayList<GoalSetItem> newGoalList) {
        goalList.clear();
        goalList.addAll(newGoalList);
        notifyDataSetChanged();
    }
}
